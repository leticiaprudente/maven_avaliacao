package br.gov.sp.fatec.avaliacao.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="pro_professor")

public class Professor extends Usuario{
	
	
	@Column(name="pro_titulo")
	private String titulo;


	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	/*como o fetch na Classe Trabalho está configurado como EAGER, aqui deve ser usado o LAZY
	 * não mapear novamente, apenas indicar o atributo que está guardando o mapeamento*/
	@OneToMany(fetch = FetchType.LAZY, mappedBy="professor")
	private Set<Trabalho> trabalhosAvaliados;

	public Set<Trabalho> getTrabalhosAvaliados() {
		return trabalhosAvaliados;
	}
	
	public void setTrabalhosAvaliados(Set<Trabalho> trabalhosAvaliados) {
		this.trabalhosAvaliados = trabalhosAvaliados;
	}
	
	
	
}
