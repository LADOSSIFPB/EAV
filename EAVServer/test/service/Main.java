package service;

import java.util.LinkedList;
import java.util.List;

import br.edu.entidade.Assunto;
import br.edu.entidade.Disciplina;
import br.edu.entidade.Opcao;
import br.edu.entidade.Questao;
import entidadesDAO.AssuntoDAO;
import entidadesDAO.DisciplinaDAO;
import entidadesDAO.QuestaoDAO;

public class Main {

	public static void main(String[] args) {

		ServiceCadastro cadastro = new ServiceCadastro();
		
		Disciplina disciplina = new Disciplina();
		disciplina.setNomeDisciplina("Navegação aerea");
		cadastro.cadastrarDisciplina(disciplina);
		
		Assunto assunto = new Assunto();
		assunto.setDisciplina(disciplina);
		assunto.setNomeAssunto("LaM");
		cadastro.cadastrarAssunto(assunto);
		
		Questao questao = new Questao();
		questao.setAssunto(assunto);
		questao.setNomeQuestao("Qual a latitude média dos pontos 03° N e 09° N?");
		
		Opcao opcao1 = new Opcao();
		opcao1.setQuestao(questao);
		opcao1.setNomeOpcao("3°");
		
		Opcao opcao2 = new Opcao();
		opcao2.setQuestao(questao);
		opcao2.setNomeOpcao("6°");
		opcao2.setOpcaoCorreta(true);
		
		Opcao opcao3 = new Opcao();
		opcao3.setQuestao(questao);
		opcao3.setNomeOpcao("9°");
		
		Opcao opcao4 = new Opcao();
		opcao4.setQuestao(questao);
		opcao4.setNomeOpcao("12°");
		
		List<Opcao> opcoes = new LinkedList<Opcao>();
		opcoes.add(opcao1);
		opcoes.add(opcao2);
		opcoes.add(opcao3);
		opcoes.add(opcao4);
		
		questao.setOpcoes(opcoes);
		
		cadastro.cadastrarQuestao(questao);
		
		Questao questao2 = new Questao();
		questao2.setAssunto(assunto);
		questao2.setNomeQuestao("Qual a latitude média dos pontos 04° N e 10° N?");
		
		Opcao opcao5 = new Opcao();
		opcao5.setQuestao(questao2);
		opcao5.setNomeOpcao("2°");
		
		Opcao opcao6 = new Opcao();
		opcao6.setQuestao(questao2);
		opcao6.setNomeOpcao("19°");
		opcao6.setOpcaoCorreta(true);
		
		Opcao opcao7 = new Opcao();
		opcao7.setQuestao(questao2);
		opcao7.setNomeOpcao("7°");
		
		Opcao opcao8 = new Opcao();
		opcao8.setQuestao(questao2);
		opcao8.setNomeOpcao("14°");
		
		List<Opcao> opcoes2 = new LinkedList<Opcao>();
		opcoes2.add(opcao5);
		opcoes2.add(opcao6);
		opcoes2.add(opcao7);
		opcoes2.add(opcao8);
		
		questao2.setOpcoes(opcoes2);
		
		cadastro.cadastrarQuestao(questao2);
		
	}
}
