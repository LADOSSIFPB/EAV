package br.edu.entidade;

public class QuestaoResultado {
	
	private String nomeQuestao;
	private String respostaCorreta;
	private String respostaErrada;
	private boolean correta = false;
	
	public String getNomeQuestao() {
		return nomeQuestao;
	}
	public void setNomeQuestao(String nomeQuestao) {
		this.nomeQuestao = nomeQuestao;
	}
	public String getRespostaCorreta() {
		return respostaCorreta;
	}
	public void setRespostaCorreta(String respostaCorreta) {
		this.respostaCorreta = respostaCorreta;
	}
	public String getRespostaErrada() {
		return respostaErrada;
	}
	public void setRespostaErrada(String respostaErrada) {
		this.respostaErrada = respostaErrada;
	}
	public boolean isCorreta() {
		return correta;
	}
	public void setCorreta(boolean correta) {
		this.correta = correta;
	}

}
