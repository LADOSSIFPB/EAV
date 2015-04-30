package br.edu.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQueries({ @NamedQuery(name = "Disciplina.findAll", query = "SELECT d FROM Disciplina d") })
@Table(name = "tb_disciplina")
@XmlRootElement(name = "disciplina")
public class Disciplina {

	@Id
	@GeneratedValue
	private int idDisciplina;
	@Column(name = "nm_discilpina")
	private String nomeDisciplina;

	public int getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(int idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

}
