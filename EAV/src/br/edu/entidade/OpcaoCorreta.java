package br.edu.entidade;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "tb_opcao_correta")
@NamedQueries({
		@NamedQuery(name = "OpcaoCorreta.findAll", query = "SELECT o FROM OpcaoCorreta o"),
		@NamedQuery(name = "OpcaoCorreta.resposta", query = "SELECT o FROM OpcaoCorreta o WHERE o.opcao = :opcao_id AND o.questao = :questao_id")
})
@XmlRootElement(name = "opcaoCorreta")
public class OpcaoCorreta implements Serializable {

	private static final long serialVersionUID = 4763951060486254812L;

	@Id
	@OneToOne
	@JoinColumn(name = "questao_id")
	Questao questao;
	@OneToOne
	@JoinColumn(name = "opcao_id", nullable = false)
	Opcao opcao;

	public OpcaoCorreta() {
		this.opcao = new Opcao();
		this.questao = new Questao();
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public Opcao getOpcao() {
		return opcao;
	}

	public void setOpcao(Opcao opcao) {
		this.opcao = opcao;
	}

}
