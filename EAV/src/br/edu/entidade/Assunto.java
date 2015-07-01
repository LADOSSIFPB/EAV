package br.edu.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQueries({
		@NamedQuery(name = "Assunto.findAll", query = "SELECT a FROM Assunto a"),
		@NamedQuery(name = "Assunto.findByDisciplina", query = "SELECT a FROM Assunto a WHERE a.disciplina = :disciplina_id"),
		@NamedQuery(name = "Assunto.update", query = "SELECT a FROM Assunto a WHERE a.idAssunto = :id_ssunto") })
@Table(name = "tb_assunto")
@XmlRootElement(name = "assunto")
public class Assunto {

	@Id
	@GeneratedValue
	@Column(name = "id_assunto")
	private int idAssunto;
	@Column(name = "nm_assunto", nullable = false)
	private String nomeAssunto;
	@ManyToOne
	@JoinColumn(name = "disciplina_id")
	private Disciplina disciplina;

	public Assunto() {
		disciplina = new Disciplina();

	}

	public int getIdAssunto() {
		return idAssunto;
	}

	public void setIdAssunto(int idAssunto) {
		this.idAssunto = idAssunto;
	}

	public String getNomeAssunto() {
		return nomeAssunto;
	}

	public void setNomeAssunto(String nomeAssunto) {
		this.nomeAssunto = nomeAssunto;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

}
