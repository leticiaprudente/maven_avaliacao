package br.gov.sp.fatec.avaliacao.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tra_trabalho")
public class Trabalho {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tra_id")
	private Long id;
	
	@Column(name="tra_titulo")
	private String titulo;
	
	@Column(name="tra_data_hora_entrega")
	private Date dataHoraEntrega;
	
	@Column(name="tra_local_arquivo", length=200)
	private String localArquivo;
	
	/*1 prof tem n trabalhos, n trabalhos tem 1 prof, por isso usar ManyToOne*/
	@ManyToOne (fetch=FetchType.EAGER) /*EAGER: traz tanto os valores do trabalho quanto o professor de uma só vez, com left join. Não é performático.
										LAZY: traz todas as informações do trabalho e , se necessário, faz um select rápido em professor */
	
	@JoinColumn(name="pro_avaliador") /*chave estrangeira utilizando OO corretamente*/
	private Professor professor; /*objeto que referência o professor*/
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	/*a tabela Entrega é resultado de um relacionamento N,N*/
	@JoinTable(name="ent_entrega",
				joinColumns= {@JoinColumn(name="tra_id")},
				inverseJoinColumns = {@JoinColumn(name="alu_id")}) 
	/*o Hibernate não trabalha bem com List*/
	private Set<Aluno> alunos; /*o aluno só pode estar relacionado 1 vez por cada trabalho, por isso não é necessário usar List*/


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public Date getDataHoraEntrega() {
		return dataHoraEntrega;
	}


	public void setDataHoraEntrega(Date dataHoraEntrega) {
		this.dataHoraEntrega = dataHoraEntrega;
	}


	public String getLocalArquivo() {
		return localArquivo;
	}


	public void setLocalArquivo(String localArquivo) {
		this.localArquivo = localArquivo;
	}


	public Professor getProfessor() {
		return professor;
	}


	public void setProfessor(Professor professor) {
		this.professor = professor;
	}


	public Set<Aluno> getAlunos() {
		return alunos;
	}


	public void setAlunos(Set<Aluno> alunos) {
		this.alunos = alunos;
	}
	
	
}
