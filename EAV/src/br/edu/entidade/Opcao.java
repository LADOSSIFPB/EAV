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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "tb_opcao")
@NamedQueries({
		@NamedQuery(name = "Opcao.findAll", query = "SELECT o FROM Opcao o"),
		@NamedQuery(name = "Opcao.findByQuestao", query = "SELECT o FROM Opcao o WHERE o.questao = :questao_id") })
@XmlRootElement(name = "opcao")
public class Opcao {

	@Id
	@GeneratedValue
	@Column(name = "id_opcao")
	private int idOpcao;
	@Column(name = "nome_opcao", nullable = false)
	private String nomeOpcao;
	@ManyToOne
	@JoinColumn(name = "questao_id")
	private Questao questao;

	@Transient
	private boolean opcaoCorreta = false;

	public int getIdOpcao() {
		return idOpcao;
	}

	public void setIdOpcao(int idOpcao) {
		this.idOpcao = idOpcao;
	}

	public String getNomeOpcao() {
		return nomeOpcao;
	}

	public void setNomeOpcao(String nomeOpcao) {
		this.nomeOpcao = nomeOpcao;
	}

	public boolean isOpcaoCorreta() {
		return opcaoCorreta;
	}

	public void setOpcaoCorreta(boolean opcaoCorreta) {
		this.opcaoCorreta = opcaoCorreta;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

}
