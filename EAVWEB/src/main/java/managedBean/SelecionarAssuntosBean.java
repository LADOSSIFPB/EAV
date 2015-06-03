package managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import service.EAVService;
import service.ProviderServiceFactory;
import br.edu.entidade.Assunto;
import br.edu.entidade.Disciplina;
import br.edu.entidade.Opcao;
import br.edu.entidade.Questao;
import br.edu.entidade.TiposDisciplinas;

@ManagedBean
@SessionScoped
public class SelecionarAssuntosBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EAVService service = ProviderServiceFactory
			.createServiceClient(EAVService.class);

	private List<SelectItem> assuntosSelect;
	private int[] idAssuntos;
	private List<Questao> questoes;
	Disciplina disciplina;

	public SelecionarAssuntosBean() {
		setAssuntosSelect(new LinkedList<SelectItem>());
		setQuestoes(new LinkedList<Questao>());
		disciplina = new Disciplina();
	}

	public String buscarAssuntos(String tipoDisciplina) {

		int idDisciplina = Integer.parseInt(tipoDisciplina);

		if (idDisciplina == TiposDisciplinas.navegacaoAerea) {
			disciplina.setIdDisciplina(idDisciplina);
			List<Assunto> assuntosConsulta = service
					.assuntoGetByDisciplina(disciplina);

			this.idAssuntos = new int[assuntosConsulta.size()];

			if (!assuntosConsulta.isEmpty()) {

				for (Assunto assunto : assuntosConsulta) {

					SelectItem selectItem = new SelectItem();
					selectItem.setValue(assunto.getIdAssunto());
					selectItem.setLabel(assunto.getNomeAssunto());

					this.assuntosSelect.add(selectItem);
				}
			}
		}

		return "selecionarconteudo.jsf";
	}

	public void iniciarSimulado() {

		Assunto assunto;
		List<Assunto> assuntos = new LinkedList<Assunto>();
		List<Questao> questoesConsulta = new LinkedList<Questao>();

		for (int idAssunto : idAssuntos) {
			assunto = new Assunto();
			assunto.setIdAssunto(idAssunto);
			assunto.setDisciplina(disciplina);
			assuntos.add(assunto);
		}

		questoesConsulta = service.questoesNavegacao(assuntos);

		for (Questao questao : questoesConsulta) {
			questao.setOpcoesSelectItem(new LinkedList<SelectItem>());
			questao.getOpcoesSelectItem().addAll(
					preencherSelectItem(questao.getOpcoes()));
			questoes.add(questao);
		}

		GenericBean.resetSessionScopedBean("simuladoBean");
		SimuladoBean simuladoNavegacaoBean = new SimuladoBean(questoes,
				TiposDisciplinas.recuperarDisciplina(disciplina
						.getIdDisciplina()));
		GenericBean.setSessionValue("simuladoBean", simuladoNavegacaoBean);

		GenericBean.resetSessionScopedBean("selecionarAssuntosBean");
		GenericBean.sendRedirect("simulado.xhtml");

	}

	public List<SelectItem> preencherSelectItem(List<Opcao> opcoes) {

		List<SelectItem> opcoesSelectItems = new ArrayList<SelectItem>();

		if (!opcoes.isEmpty()) {

			for (Opcao opcao : opcoes) {

				SelectItem selectItem = new SelectItem();
				selectItem.setValue(opcao.getIdOpcao());
				selectItem.setLabel(opcao.getNomeOpcao());

				opcoesSelectItems.add(selectItem);
			}
		}

		return opcoesSelectItems;

	}

	public List<SelectItem> getAssuntosSelect() {
		return assuntosSelect;
	}

	public void setAssuntosSelect(List<SelectItem> assuntosSelect) {
		this.assuntosSelect = assuntosSelect;
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}

	public void setIdAssuntos(int[] idAssuntos) {
		this.idAssuntos = idAssuntos;
	}

	public int[] getIdAssuntos() {
		return idAssuntos;
	}

}
