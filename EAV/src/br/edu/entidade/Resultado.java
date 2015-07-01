package br.edu.entidade;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Resultado {

	private float nota;
	private int questoesCorretas;
	private int questoesErradas;
	private List<QuestaoResultado> questoesResultado;

	public Resultado() {
		questoesResultado = new LinkedList<QuestaoResultado>();
	}
	
	@XmlElement
	public float getNota() {
		return nota;
	}

	public void setNota(float nota) {
		this.nota = nota;
	}
	
	@XmlElement
	public int getQuestoesCorretas() {
		return questoesCorretas;
	}

	public void setQuestoesCorretas(int questoesCorretas) {
		this.questoesCorretas = questoesCorretas;
	}
	
	@XmlElement
	public int getQuestoesErradas() {
		return questoesErradas;
	}

	public void setQuestoesErradas(int questoesErradas) {
		this.questoesErradas = questoesErradas;
	}

	@XmlElement
	public List<QuestaoResultado> getQuestoesResultado() {
		return questoesResultado;
	}

	public void setQuestoesResultado(List<QuestaoResultado> questoesResultado) {
		this.questoesResultado = questoesResultado;
	}
}