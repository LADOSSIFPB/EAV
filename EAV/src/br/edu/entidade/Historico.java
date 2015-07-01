package br.edu.entidade;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQueries({
		@NamedQuery(name = "Historico.findAll", query = "SELECT h FROM Historico h"),
		@NamedQuery(name = "Historico.findByUser", query = "SELECT h FROM Historico h WHERE h.usuario = :usuario_id"),
		@NamedQuery(name = "Historico.getAssuntos", query = "SELECT a FROM Historico h JOIN h.assuntos a WHERE h.idHistorico = :historico_id")})
@Table(name = "tb_historico")
@XmlRootElement(name = "historico")
public class Historico {

	@Id
	@GeneratedValue
	@Column(name = "id_historico")
	private int idHistorico;
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	Usuario usuario;
	@OneToOne
	@JoinColumn(name = "disciplina_id")
	private Disciplina disciplina;
	@Column(name = "nota_simulado", nullable = false)
	private float notaSimulado;
	@Column(name = "dt_simulado", nullable = false)
	private Date dataSimulado;

	@ManyToMany
	@JoinTable(name = "tb_assuntos_historico", joinColumns = { @JoinColumn(name = "historico_id") }, inverseJoinColumns = { @JoinColumn(name = "assunto_id") })
	private List<Assunto> assuntos;

	public Historico() {
		usuario = new Usuario();
		assuntos = new LinkedList<Assunto>();
	}

	@XmlElement
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@XmlElement
	public float getNotaSimulado() {
		return notaSimulado;
	}

	public void setNotaSimulado(float notaSimulado) {
		this.notaSimulado = notaSimulado;
	}

	@XmlElement
	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	@XmlElement
	public List<Assunto> getAssuntos() {
		return assuntos;
	}

	public void setAssuntos(List<Assunto> assuntos) {
		this.assuntos = assuntos;
	}

	@XmlElement
	public Date getDataSimulado() {
		return dataSimulado;
	}

	public void setDataSimulado(Date dataSimulado) {
		this.dataSimulado = dataSimulado;
	}

	@XmlElement
	public int getIdHistorico() {
		return idHistorico;
	}

	public void setIdHistorico(int idHistorico) {
		this.idHistorico = idHistorico;
	}

}
