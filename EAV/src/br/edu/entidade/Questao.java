package br.edu.entidade;

import java.util.LinkedList;
import java.util.List;

import javax.faces.model.SelectItem;
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
@NamedQueries({
		@NamedQuery(name = "Questao.findAll", query = "SELECT q FROM Questao q"),
		@NamedQuery(name = "Questao.findByAssunto", query = "SELECT q FROM Questao q WHERE q.assunto = :assunto_id"),
		@NamedQuery(name = "Questao.update", query = "SELECT q FROM Questao q WHERE q.idQuestao = :idQuestao") })
@Table(name = "tb_questao")
@XmlRootElement(name = "questao")
public class Questao {

	@Id
	@GeneratedValue
	@Column(name = "id_questao")
	private int idQuestao;
	@Column(name = "nome_questao", length = 900, nullable = false)
	private String nomeQuestao;
	@ManyToOne
	@JoinColumn(name = "assunto_id")
	private Assunto assunto;

	@Transient
	private List<Opcao> opcoes = new LinkedList<Opcao>();

	@Transient
	private List<SelectItem> opcoesSelectItem;
	
	@Transient
	private int resposta;

	public int getIdQuestao() {
		return idQuestao;
	}

	public void setIdQuestao(int idQuestao) {
		this.idQuestao = idQuestao;
	}

	public String getNomeQuestao() {
		return nomeQuestao;
	}

	public void setNomeQuestao(String nomeQuestao) {
		this.nomeQuestao = nomeQuestao;
	}

	public Assunto getAssunto() {
		return assunto;
	}

	public void setAssunto(Assunto assunto) {
		this.assunto = assunto;
	}

	public List<Opcao> getOpcoes() {
		return opcoes;
	}

	public void setOpcoes(List<Opcao> opcoes) {
		this.opcoes = opcoes;
	}

	public List<SelectItem> getOpcoesSelectItem() {
		return opcoesSelectItem;
	}

	public void setOpcoesSelectItem(List<SelectItem> opcoesSelectItem) {
		this.opcoesSelectItem = opcoesSelectItem;
	}

	public int getResposta() {
		return resposta;
	}

	public void setResposta(int resposta) {
		this.resposta = resposta;
	}

}
