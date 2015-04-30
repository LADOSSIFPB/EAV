package managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.el.ValueExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import service.EAVService;
import service.ProviderServiceFactory;
import br.edu.entidade.Assunto;
import br.edu.entidade.Disciplina;
import br.edu.entidade.Opcao;
import br.edu.entidade.Questao;

@ManagedBean
@ViewScoped
public class QuestaoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EAVService service = ProviderServiceFactory
			.createServiceClient(EAVService.class);

	private Questao questao;
	private List<SelectItem> disciplinas;
	private List<SelectItem> assuntos;
	private List<String> opcoes = new LinkedList<String>();

	public QuestaoBean() {
		this.setQuestao(new Questao());
		questao.setAssunto(new Assunto());
		questao.setOpcoes(new LinkedList<Opcao>());
		questao.getAssunto().setDisciplina(new Disciplina());
	}

	public void cadastrarQuestao() {

		Opcao opcao;

		for (String nomeOpcao : opcoes) {
			opcao = new Opcao();
			opcao.setNomeOpcao(nomeOpcao);
		}
		service.cadastrarQuestao(questao);

	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public List<SelectItem> getDisciplinas() {
		if (disciplinas != null) {

			return disciplinas;

		} else {

			List<Disciplina> disciplinaConsulta = service.disciplinasGetAll();

			disciplinas = new ArrayList<SelectItem>();

			if (!disciplinaConsulta.isEmpty()) {

				for (Disciplina disciplina : disciplinaConsulta) {

					SelectItem selectItem = new SelectItem();
					selectItem.setValue(disciplina.getIdDisciplina());
					selectItem.setLabel(disciplina.getNomeDisciplina());

					disciplinas.add(selectItem);
				}
			}

			return disciplinas;
		}
	}

	public void setDisciplinas(List<SelectItem> disciplinas) {
		this.disciplinas = disciplinas;
	}

	private HtmlPanelGrid htmlPanelGrid = new HtmlPanelGrid();

	public HtmlPanelGrid getHtmlPanelGrid() {
		return htmlPanelGrid;
	}

	public void setHtmlPanelGrid(HtmlPanelGrid htmlPanelGrid) {
		this.htmlPanelGrid = htmlPanelGrid;
	}

	public void adiciona() {
		HtmlInputText htmlInputText = new HtmlInputText();
		htmlInputText.setValueExpression(
				"value",
				createValueExpression(
						"#{questaoBean.opcoes[" + (getOpcoes().size()) + "]}",
						String.class));
		htmlInputText.setStyleClass("form-control");
		getOpcoes().add("");
		this.getHtmlPanelGrid().getChildren().add(htmlInputText);
	}

	public ValueExpression createValueExpression(String elExpression,
			Class<?> elClass) {
		return FacesContext
				.getCurrentInstance()
				.getApplication()
				.getExpressionFactory()
				.createValueExpression(
						FacesContext.getCurrentInstance().getELContext(),
						elExpression, elClass);
	}

	public List<String> getOpcoes() {
		return opcoes;
	}

	public void setOpcoes(List<String> opcoes) {
		this.opcoes = opcoes;
	}

	public List<SelectItem> getAssuntos() {
		
		List<Assunto> assuntoConsulta = service.assuntoGetByDisciplina(questao.getAssunto().getDisciplina());

		assuntos = new ArrayList<SelectItem>();

		if (!assuntoConsulta.isEmpty()) {

			for (Assunto assunto : assuntoConsulta) {

				SelectItem selectItem = new SelectItem();
				selectItem.setValue(assunto.getIdAssunto());
				selectItem.setLabel(assunto.getNomeAssunto());

				assuntos.add(selectItem);
			}
		}

		return assuntos;
	}

	public void setAssuntos(List<SelectItem> assuntos) {
		this.assuntos = assuntos;
	}

}
