package managedBean;

import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import service.EAVService;
import service.ProviderServiceFactory;
import br.edu.entidade.OpcaoCorreta;
import br.edu.entidade.Questao;

@ManagedBean
@SessionScoped
public class SimuladoNavegacaoBean {

	private EAVService service = ProviderServiceFactory
			.createServiceClient(EAVService.class);

	private int resultado;
	
	private int numeroQuestao = 0;

	private List<Questao> questoes;

	public SimuladoNavegacaoBean() {
		questoes = new LinkedList<Questao>();
	}
	
	public SimuladoNavegacaoBean(List<Questao> questoes) {
		this.questoes = questoes;
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void encerrarProva() {

		List<OpcaoCorreta> respostas = new LinkedList<OpcaoCorreta>();
		OpcaoCorreta opcaoCorreta;

		for (Questao questao : questoes) {
			opcaoCorreta = new OpcaoCorreta();
			opcaoCorreta.getQuestao().setIdQuestao(questao.getIdQuestao());
			opcaoCorreta.getOpcao().setIdOpcao(questao.getResposta());
			respostas.add(opcaoCorreta);
		}

		this.setResultado(service.resultadoSimulado(respostas));

		GenericBean.sendRedirect("resultado.xhtml");

	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}

	public int getResultado() {
		return resultado;
	}

	public void setResultado(int resultado) {
		this.resultado = resultado;
	}

	public int getNumeroQuestao() {
		return numeroQuestao;
	}

	public void setNumeroQuestao(int numeroQuestao) {
		this.numeroQuestao = numeroQuestao;
	}

}
