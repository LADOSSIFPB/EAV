package managedBean;

import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.management.Query;

import org.primefaces.model.chart.PieChartModel;

import service.EAVService;
import service.ProviderServiceFactory;
import br.edu.entidade.OpcaoCorreta;
import br.edu.entidade.Questao;
import br.edu.entidade.QuestaoResultado;

@ManagedBean
@SessionScoped
public class SimuladoNavegacaoBean {

	private EAVService service = ProviderServiceFactory
			.createServiceClient(EAVService.class);

	private PieChartModel pieChartModel;
	private float resultado;

	private int numeroQuestao = 0;

	private List<Questao> questoes;

	private List<QuestaoResultado> questoesResultados;

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

		this.setQuestoesResultados(service.resultadoSimulado(questoes));
		createPieModel1();
		
		int corretas = 0;
		int erradas = 0;
		
		for (QuestaoResultado questaoResultado : questoesResultados) {
			if(questaoResultado.isCorreta()){
				corretas++;
			}else{
				erradas++;
			}
		}
		
		setPieChartModel("Acertos", corretas);
		setPieChartModel("Erros", erradas);
		
		resultado = (float) (10.0 / questoesResultados.size());
		resultado = resultado * corretas;

		GenericBean.sendRedirect("resultado.xhtml");

	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}

	public float getResultado() {
		return resultado;
	}

	public void setResultado(float resultado) {
		this.resultado = resultado;
	}

	public int getNumeroQuestao() {
		return numeroQuestao;
	}

	public void setNumeroQuestao(int numeroQuestao) {
		this.numeroQuestao = numeroQuestao;
	}

	public List<QuestaoResultado> getQuestoesResultados() {
		return questoesResultados;
	}

	public void setQuestoesResultados(List<QuestaoResultado> questoesResultados) {
		this.questoesResultados = questoesResultados;
	}

	private void createPieModel1() {
		pieChartModel = new PieChartModel();

		pieChartModel.setShowDataLabels(true);
		pieChartModel.setMouseoverHighlight(true);
		
		pieChartModel.setTitle("Resultado");
		pieChartModel.setLegendPosition("w");
		
	}

	public PieChartModel getPieChartModel() {
		return pieChartModel;
	}

	public void setPieChartModel(PieChartModel pieChartModel) {
		this.pieChartModel = pieChartModel;
	}
	
	public void setPieChartModel(String legend, int value) {
		pieChartModel.set(legend, value);
	}

}
