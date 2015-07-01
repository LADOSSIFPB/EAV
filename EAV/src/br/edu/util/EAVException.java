package br.edu.util;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.HashMap;
import java.util.Map;

@XmlRootElement(name = "erro")
public class EAVException extends Exception {

	private static final long serialVersionUID = -7222803777105678742L;

	private int codigoErro;

	public EAVException(int codigoErro) {
		super(recuperarMensagem(codigoErro));
		this.codigoErro = codigoErro;
	}

	public EAVException() {
		// TODO Auto-generated constructor stub
	}

	@XmlElement
	public int getCodigoErro() {
		return codigoErro;
	}

	@XmlElement
	public String getMensagem() {
		return recuperarMensagem(getCodigoErro());
	}

	private static String recuperarMensagem(int codigoErro) {
		String mensagem = erros.get(codigoErro);
		return mensagem != null ? mensagem : "Erro interno no sistema.";
	}
	
	// CÓDIGOS DE ERROS
	public static final int USUARIO_INVALIDO = 1;
	public static final int DISCIPLINA_INVALIDA = 2;
	public static final int ASSUNTO_INVALIDO = 3;
	public static final int QUESTAO_INVALIDA = 4;
	public static final int OPCAO_INVALIDA = 5;
	public static final int USUARIO_EXISTENTE = 6;
	public static final int SENHA_INCORRETA = 7;
	public static final int ERRO_CRIPITOGRAFAR = 100;
	public static final int FALHA_CADASTRO_HISTORICO = 101;
	
	
	// MAPA DE ERROS
	private static final Map<Integer, String> erros = new HashMap<Integer, String>();
	static {
		// Usuário
		erros.put(USUARIO_INVALIDO, "Usuário inválido!");
		erros.put(DISCIPLINA_INVALIDA, "Disciplina inválida!");
		erros.put(ASSUNTO_INVALIDO, "Assunto inválido!");
		erros.put(QUESTAO_INVALIDA, "Questão inválida!");
		erros.put(OPCAO_INVALIDA, "Opção inválida!");
		erros.put(USUARIO_EXISTENTE, "O usuário já existe!");
		erros.put(SENHA_INCORRETA, "Senha incorreta!");
		erros.put(ERRO_CRIPITOGRAFAR, "Erro durante a criptografia!");
		erros.put(FALHA_CADASTRO_HISTORICO, "Erro durante o cadastro do histórico!");
	}

}
