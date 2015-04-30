package teste;

import service.ServiceCadastro;
import br.edu.entidade.Assunto;
import br.edu.entidade.Opcao;
import br.edu.entidade.Questao;

public class Main {
	
	public static void main(String[] args) {
		
		ServiceCadastro sc = new ServiceCadastro();
		
//		Usuario usuario = new Usuario();
//		usuario.setCpf("07667939403");
//		usuario.setEmail("felipe@gmail.com");
//		usuario.setNomeUsuario("Felipe");
//		usuario.setSenha("101213");
//		
//		sc.cadastrarUsuario(usuario);
		
//		Disciplina disciplina = new Disciplina();
//		disciplina.setNomeDisciplina("Disciplina 1");
//		
//		sc.cadastrarDisciplina(disciplina);
//		
//		Assunto assunto = new Assunto();
//		assunto.setDisciplina(disciplina);
//		assunto.setNomeAssunto("Assunto 1");
//		
//		sc.cadastrarAssunto(assunto);

		Assunto assunto = new Assunto();
		assunto.setIdAssunto(1);
		
		Questao questao = new Questao();
		questao.setAssunto(assunto);
		questao.setNomeQuestao("Questão 1");
		
		Opcao opcao = new Opcao();
		opcao.setNomeOpcao("Opção 1");
		
		Opcao opcao2 = new Opcao();
		opcao2.setNomeOpcao("Opção 2");
		
		Opcao opcao3 = new Opcao();
		opcao3.setNomeOpcao("Opção 3");
		
		questao.getOpcoes().add(opcao);
		questao.getOpcoes().add(opcao2);
		questao.getOpcoes().add(opcao3);
		
		sc.cadastrarQuestao(questao);
		
		
		
				
	}

}
