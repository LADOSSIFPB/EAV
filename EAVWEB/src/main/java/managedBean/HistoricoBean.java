package managedBean;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import service.EAVService;
import service.ProviderServiceFactory;
import br.edu.entidade.Historico;
import br.edu.entidade.Usuario;

@ManagedBean
@RequestScoped
public class HistoricoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EAVService service = ProviderServiceFactory
			.createServiceClient(EAVService.class);

	private List<Historico> historicos;

	public HistoricoBean() {
		this.historicos = new LinkedList<Historico>();
	}

	public List<Historico> getHistoricos() {

		if (historicos != null) {
			Usuario usuario = ((UsuarioBean) GenericBean
					.getSessionValue("usuarioBean")).getUsuario();

			this.historicos = service.buscarHistorico(usuario);
		}

		return historicos;
	}

	public void setHistoricos(List<Historico> historicos) {
		this.historicos = historicos;
	}

}
