//package service;
//
//import static org.junit.Assert.*;
//
//import java.util.LinkedList;
//import java.util.List;
//
//import org.junit.Test;
//
//import br.edu.entidade.Assunto;
//import br.edu.entidade.Disciplina;
//import br.edu.entidade.Opcao;
//import br.edu.entidade.Questao;
//import br.edu.entidade.Usuario;
//import entidadesDAO.UsuarioDAO;
//
//public class TestAll {
//
//	ServiceCadastro cadastro = new ServiceCadastro();
//	ServiceConsulta consulta = new ServiceConsulta();
//
//	@Test
//	public void testCadastraUsuario() throws Exception {
//		// cadastro correto do usuário
//		Usuario usuario = new Usuario();
//		usuario.setCpf("07667939403");
//		usuario.setEmail("felipe@gmail.com");
//		usuario.setNomeUsuario("Felipe");
//		usuario.setSenha("101213");
//
//		cadastro.cadastrarUsuario(usuario);
//		
//		// cadastro errado do usuário
//		try {
//			Usuario usuarioDuplicado = new Usuario();
//			usuarioDuplicado.setCpf("07667939403");
//			usuarioDuplicado.setEmail("felipe@gmail.com");
//			usuarioDuplicado.setNomeUsuario("Felipe");
//			usuarioDuplicado.setSenha("101213");
//			cadastro.cadastrarUsuario(usuarioDuplicado);
//			fail();
//		} catch(Exception e) {
//			// tudo certo
//		}
//		
//		// login correto
//		Usuario usuarioConsulta = new Usuario();
//		usuarioConsulta.setEmail("felipe@gmail.com");
//		usuarioConsulta.setSenha("101213");
//		Usuario usuarioEncontrado = consulta.login(usuarioConsulta);
//		assertTrue(usuario.equals(usuarioEncontrado));
//		
//		// login errado
//		usuarioConsulta.setEmail("felipe@gmail.com");
//		usuarioConsulta.setSenha("111213");
//		usuarioEncontrado = consulta.login(usuarioConsulta);
//		assertFalse(usuario.equals(usuarioEncontrado));
//		
//		// delete correto
//		UsuarioDAO usuarioDAO = new UsuarioDAO();
//		usuarioDAO.delete(usuario);
//		
//		// delete errado
//		try {
//			usuarioDAO.delete(usuarioConsulta);
//			fail();
//		} catch(Exception e) {
//			// tudo certo
//		}
//		
//	}
//	
////	@Test
////	public void testCadastrarVarios() throws Exception {
////		// Cadastrar Disciplina
////		Disciplina disciplina = new Disciplina();
////		disciplina.setNomeDisciplina("Disciplina 1");
////
////		cadastro.cadastrarDisciplina(disciplina);
////
////		// Cadastrar Assunto
////		Assunto assunto = new Assunto();
////		assunto.setDisciplina(disciplina);
////		assunto.setNomeAssunto("Assunto 1");
////
////		cadastro.cadastrarAssunto(assunto);
////
////		// Cadastrar Questão
////		Questao questao = new Questao();
////		questao.setAssunto(assunto);
////		questao.setNomeQuestao("Questão 1");
////
////		Opcao opcao = new Opcao();
////		opcao.setNomeOpcao("Opção 1");
////
////		Opcao opcao2 = new Opcao();
////		opcao2.setNomeOpcao("Opção 2");
////
////		Opcao opcao3 = new Opcao();
////		opcao3.setNomeOpcao("Opção 3");
////
////		questao.getOpcoes().add(opcao);
////		questao.getOpcoes().add(opcao2);
////		questao.getOpcoes().add(opcao3);
////
////		cadastro.cadastrarQuestao(questao);
////	}
//	
////	@Test
////	public void consultarQuestoesByAssunto() throws Exception {
////		
////		Assunto assunto = new Assunto();
////		assunto.setIdAssunto(1);
////		
////		List<Assunto> assuntos = new LinkedList<Assunto>();
////		
////		consulta.questoesNavegacao(assuntos);
////		
////	}
//
//}
