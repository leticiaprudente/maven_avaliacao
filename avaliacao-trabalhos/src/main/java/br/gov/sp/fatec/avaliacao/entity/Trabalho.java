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
	
	/*EAGER: traz tanto os valores do trabalho quanto o professor de uma s� vez, com left join. N�o � perform�tico.
	LAZY: traz todas as informa��es do trabalho e , se necess�rio, faz um select r�pido em professor */
	@ManyToOne (fetch=FetchType.EAGER) 
	@JoinColumn(name="pro_avaliador_id") /*chave estrangeira utilizando OO corretamente*/
	private Professor professor; /*objeto que refer�ncia o professor*/
	
	
	/*a tabela Entrega � resultado de um relacionamento N,N*/
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="ent_entrega",
				joinColumns= {@JoinColumn(name="tra_id")},
				inverseJoinColumns = {@JoinColumn(name="alu_id")}) 
	/*o Hibernate n�o trabalha bem com List*/
	private Set<Aluno> alunos; /*o aluno s� pode estar relacionado 1 vez com cada trabalho, por isso n�o � necess�rio usar List*/


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
