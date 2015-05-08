package managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import service.EAVService;
import service.ProviderServiceFactory;
import br.edu.entidade.Assunto;
import br.edu.entidade.Disciplina;
import br.edu.entidade.Opcao;
import br.edu.entidade.Questao;
import br.edu.entidade.TiposDisciplinas;

@ManagedBean
@ViewScoped
public class ConteudoNavegacaoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EAVService service = ProviderServiceFactory
			.createServiceClient(EAVService.class);

	private List<SelectItem> assuntosSelect;
	private int[] idAssuntos;
	private List<Questao> questoes;
	Disciplina navegacaoAerea;

	public ConteudoNavegacaoBean() {
		setAssuntosSelect(new LinkedList<SelectItem>());
		setQuestoes(new LinkedList<Questao>());
		navegacaoAerea = new Disciplina();
		navegacaoAerea.setIdDisciplina(TiposDisciplinas.navegacaoAerea);
	}

	@PostConstruct
	public void buscarAssuntos() {
				
		List<Assunto> assuntosConsulta = service.assuntoGetByDisciplina(navegacaoAerea);
		
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

	public void iniciarSimulado(){
		
		Assunto assunto;
		List<Assunto> assuntos = new LinkedList<Assunto>();
		List<Questao> questoesConsulta = new LinkedList<Questao>();
		
		for (int idAssunto : idAssuntos) {
			assunto = new Assunto();
			assunto.setIdAssunto(idAssunto);
			assunto.setDisciplina(navegacaoAerea);
			assuntos.add(assunto);
		}
		
		questoesConsulta = service.questoesNavegacao(assuntos);
		
		for (Questao questao : questoesConsulta) {
			questao.setOpcoesSelectItem(new LinkedList<SelectItem>());
			questao.getOpcoesSelectItem().addAll(
					preencherSelectItem(questao.getOpcoes()));
			questoes.add(questao);
		}
		
		GenericBean.resetSessionScopedBean("simuladoNavegacaoBean");
		SimuladoNavegacaoBean simuladoNavegacaoBean = new SimuladoNavegacaoBean(questoes);
		GenericBean.setSessionValue("simuladoNavegacaoBean", simuladoNavegacaoBean);
		
		GenericBean.sendRedirect("simuladoNavegacao.xhtml");
		
		
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
