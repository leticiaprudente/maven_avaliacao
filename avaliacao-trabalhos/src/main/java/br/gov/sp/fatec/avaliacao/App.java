package br.gov.sp.fatec.avaliacao;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.gov.sp.fatec.avaliacao.entity.*;
import br.gov.sp.fatec.avaliacaotrabalhos.dao.PersistenceManager;

public class App 
{
	public static void main( String[] args )
	{
		EntityManager manager = PersistenceManager.getInstance().getEntityManager();
		
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
		
		/*limpa o cache e força o hibernate e buscar pelos dados tabela a tabela, respeitando os joins, 
		 * sem utilizar o cache, já que dessa forma os dados retornados podem estar desatualizados
		 * foi utilizado para o for que printa as informações no console*/
		manager.clear();
		
		/*JPQL - SQL orientado à objeto*/
		String queryText = "select t " +
				"from Trabalho t " +
				"where t.titulo = :titulo";
		
		/*para fazer consulta não é necessário abrir uma transação*/
		/*Para consultas:
		 * 1 - criar o parâmetro*/
		Query query = manager.createQuery(queryText);
		/*2 - setar o parâmetro*/
		query.setParameter("titulo", "JPA");
		@SuppressWarnings("unchecked")
		/*retorna o select*/
		List<Trabalho> resultados = query.getResultList();
		
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
		/*fechando a conexão*/
		manager.close();
	}
}
