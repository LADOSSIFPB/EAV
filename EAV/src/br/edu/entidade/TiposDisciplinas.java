package br.edu.entidade;

import java.util.HashMap;
import java.util.Map;

public class TiposDisciplinas {

	public static final int navegacaoAerea = 1;
	public static final int teoriaVoo = 2;
	public static final int meteorologia = 3;
	public static final int conhecimentosTecnicos = 4;
	public static final int regulamentoTrafego = 5;

	// MAPA DE ERROS
	private static final Map<Integer, String> disciplinas = new HashMap<Integer, String>();
	static {
		// Usuário
		disciplinas.put(navegacaoAerea, "Navegação aerea");
		disciplinas.put(teoriaVoo, "Teoria de voo");
		disciplinas.put(meteorologia, "Meteorologia");
		disciplinas.put(conhecimentosTecnicos, "Conhecimentos técnicos");
		disciplinas.put(regulamentoTrafego, "Regulamento de tráfego aereo");
	}
	
	public static String recuperarDisciplina(int codigoErro) {
		String mensagem = disciplinas.get(codigoErro);
		return mensagem != null ? mensagem : "Erro interno no sistema.";
	}

}
