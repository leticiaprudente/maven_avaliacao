package br.gov.sp.fatec.avaliacao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**/

@Entity
@Table(name="usu_usuario")
//herança , join entre as tabelas pai e filha
@Inheritance(strategy = InheritanceType.JOINED)
//não é possível instanciar um usuário diretamente
public abstract class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="usu_id")
	private Long id;
	
	@Column(name="usu_nome_usuario")
	private String nomeUsuario;
	
	@Column(name="usu_senha")
	private String senha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
