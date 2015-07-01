package br.edu.entidade;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class QuestaoResultado {

	private String nomeQuestao;
	private String respostaCorreta;
	private String respostaErrada;
	private boolean correta = false;

	@XmlElement
	public String getNomeQuestao() {
		return nomeQuestao;
	}

	public void setNomeQuestao(String nomeQuestao) {
		this.nomeQuestao = nomeQuestao;
	}

	@XmlElement
	public String getRespostaCorreta() {
		return respostaCorreta;
	}

	public void setRespostaCorreta(String respostaCorreta) {
		this.respostaCorreta = respostaCorreta;
	}

	@XmlElement
	public String getRespostaErrada() {
		return respostaErrada;
	}

	public void setRespostaErrada(String respostaErrada) {
		this.respostaErrada = respostaErrada;
	}

	@XmlElement
	public boolean isCorreta() {
		return correta;
	}

	public void setCorreta(boolean correta) {
		this.correta = correta;
	}

}
