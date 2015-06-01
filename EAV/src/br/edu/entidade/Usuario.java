package br.edu.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.edu.util.StringUtil;

@Entity
@NamedQueries({
	@NamedQuery(name = "Usuario.findByLogin", query = "SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha"),
	@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
	@NamedQuery(name = "Usuario.update", query = "SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha")
})
@Table(name = "tb_usuario")
@XmlRootElement(name = "usuario")
public class Usuario {

	@Id
	@Column(name = "nr_cpf", columnDefinition = "CHAR(11)")
	private String cpf;
	@Column(name = "nome_usuario", nullable = false)
	private String nomeUsuario;
	@Column(name="nm_email", nullable = false, unique = true, length = 50)
	private String email;
	@Column(name = "nm_senha", nullable = false, length = 255)
	private String senha;
	
	public Usuario() {
	}
	
	@XmlElement
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	@XmlElement
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = StringUtil.tirarMascaraCPF(cpf);
	}
	
	@XmlElement
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@XmlElement
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}

}
