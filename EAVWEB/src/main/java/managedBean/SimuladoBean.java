package managedBean;

import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.PieChartModel;

import service.EAVService;
import service.ProviderServiceFactory;
import br.edu.entidade.Assunto;
import br.edu.entidade.Historico;
import br.edu.entidade.Questao;
import br.edu.entidade.Resultado;

@ManagedBean
@SessionScoped
public class SimuladoBean {

	private EAVService service = ProviderServiceFactory
			.createServiceClient(EAVService.class);

	private PieChartModel pieChartModel;

	private int numeroQuestao = 0;

	private List<Questao> questoes;
	private List<Assunto> assuntos;

	private String nomeDisciplina;

	private Resultado resultado;

	public SimuladoBean() {
		questoes = new LinkedList<Questao>();
		setResultado(new Resultado());
		assuntos = new LinkedList<Assunto>();
	}

	public SimuladoBean(List<Questao> questoes, String nomeDisciplina, List<Assunto> assuntos) {
		this.questoes = questoes;
		this.nomeDisciplina = nomeDisciplina;
		this.assuntos = assuntos;
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void encerrarProva() {

		setResultado(service.resultadoSimulado(questoes));
		createPieModel1();

		setPieChartModel("Acertos", getResultado().getQuestoesCorretas());
		setPieChartModel("Erros", getResultado().getQuestoesErradas());

		UsuarioBean usuarioBean = (UsuarioBean) GenericBean
				.getSessionValue("usuarioBean");
		Historico historico = new Historico();

		historico.setNotaSimulado(getResultado().getNota());
		historico.setDisciplina(questoes.get(1).getAssunto().getDisciplina());
		historico.setAssuntos(assuntos);
		historico.setUsuario(usuarioBean.getUsuario());
		
		service.cadastrarHistorico(historico);
		
		GenericBean.sendRedirect("resultado.xhtml");

	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}

	public int getNumeroQuestao() {
		return numeroQuestao;
	}

	public void setNumeroQuestao(int numeroQuestao) {
		this.numeroQuestao = numeroQuestao;
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

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

}
