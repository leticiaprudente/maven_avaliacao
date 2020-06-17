package br.gov.sp.fatec.avaliacao.entity;

import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="alu_aluno")
public class Aluno extends Usuario {

	
	@Column(name="alu_ra")
	private Long ra;
	

	public Long getRa() {
		return ra;
	}

	public void setRa(Long ra) {
		this.ra = ra;
	}
	
	/*mapeamento de lista de Trabalhos por aluno, sem necessidade de construir um select*/
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "alunos")
	private Set<Trabalho> trabalhosEntregues;
	
	public Set<Trabalho> getTrabalhosEntregues() {
		return trabalhosEntregues;
	}

	public void setTrabalhosEntregues(Set<Trabalho> trabalhosEntregues) {
		this.trabalhosEntregues = trabalhosEntregues;
	}
	
}
