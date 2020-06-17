package br.gov.sp.fatec.avaliacao;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.avaliacao.entity.Aluno;
import br.gov.sp.fatec.avaliacao.entity.Professor;
import br.gov.sp.fatec.avaliacao.entity.Trabalho;
import br.gov.sp.fatec.avaliacaotrabalhos.dao.PersistenceManager;

public class App 
{
	public static void main( String[] args )
	{
		EntityManager manager = PersistenceManager.getInstance().getEntityManager();
	
		cadastraRegistros(manager);
		
		
		/*limpa o cache e força o hibernate e buscar pelos dados tabela a tabela, respeitando os joins, 
		 * utilizando o cache, os dados retornados podem estar desatualizados*/
		manager.clear();
		
		
		
		/*JPQL - SQL orientado à objeto*/
		String queryText = "select t " +
				"from Trabalho t " +
				"where t.titulo = :titulo";
		
		/*para fazer consulta não é necessário abrir uma transação*/
		/*Para consultas:
		 * 1 - criar o parâmetro*/
																/*Tipo de Query, Tipo de retorno*/
		TypedQuery<Trabalho> queryTrabalho = manager.createQuery(queryText, Trabalho.class);
		/*2 - setar o parâmetro*/
		queryTrabalho.setParameter("titulo", "JPA");
		@SuppressWarnings("unchecked")
		/*retorna o select*/
		List<Trabalho> resultados = queryTrabalho.getResultList();
		
		/*O Hibernate possui um Hash para cada dado. 
		 * não é preciso puxar dados de diversas tabelas, ele já faz isso automaticamente comparando os hashs*/
		for(Trabalho trab: resultados) {
			System.out.println("Título: " + trab.getTitulo());
			System.out.println("Path: " + trab.getLocalArquivo());
			System.out.println("Avaliador: " +
			trab.getProfessor().getNomeUsuario());
			for(Aluno al: trab.getAlunos()) {
				System.out.println("Aluno: " +
						al.getNomeUsuario());
			}
		}
		
		manager.clear();
		queryText= "select p from Professor p where p.nomeUsuario LIKE :nome";
		
		TypedQuery<Professor> queryProfessor = manager.createQuery(queryText, Professor.class);
		queryProfessor.setParameter("nome", "%ed%");
		
		List<Professor> professores = queryProfessor.getResultList();

			for(Professor prof: professores) {
				System.out.println("Professor: " + prof.getNomeUsuario());
			
				for(Trabalho trab: prof.getTrabalhosAvaliados()) {
					System.out.println("Título: " + trab.getTitulo());
					System.out.println("Path: " + trab.getLocalArquivo());
					for(Aluno al: trab.getAlunos()) {
						System.out.println("Aluno: " +
						al.getNomeUsuario());
					}
				}
			}
		
		
		
		/*fechando a conexão*/
		manager.close();
	}
	
	private static void cadastraRegistros(EntityManager manager) {
		Professor professor = new Professor();
		professor.setNomeUsuario("mineda");
		professor.setSenha("senha");
		
		Aluno aluno = new Aluno();
		aluno.setNomeUsuario("aluno");
		aluno.setSenha("senha");
		aluno.setRa(1234567891011L);
		
		Trabalho trabalho = new Trabalho();
		trabalho.setTitulo("JPA");
		trabalho.setLocalArquivo("\\uploads\\trab1.pdf");
		trabalho.setProfessor(professor); /*avaliador*/
		trabalho.setDataHoraEntrega(new Date());
		trabalho.setAlunos(new HashSet<Aluno>()); 
		trabalho.getAlunos().add(aluno);
		
		/*abrindo uma conexão com o banco de dados e iniciando uma transação*/
		manager.getTransaction().begin();
		/*persiste a classe*/
		manager.persist(professor);
		manager.persist(aluno);
		manager.persist(trabalho);
		manager.getTransaction().commit();
		/*ao analisar os erros, procurar pelo ultimo*/
	}
}

