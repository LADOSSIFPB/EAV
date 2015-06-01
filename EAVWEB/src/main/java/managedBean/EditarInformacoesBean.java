package managedBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import service.EAVService;
import service.ProviderServiceFactory;
import br.edu.entidade.NovaSenha;
import br.edu.util.Erro;

@ManagedBean
@ViewScoped
public class EditarInformacoesBean {
	
	private EAVService service = ProviderServiceFactory
			.createServiceClient(EAVService.class);
	
	private NovaSenha novaSenha;
	
	public EditarInformacoesBean() {
		setNovaSenha(new NovaSenha());
	}

	public NovaSenha getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(NovaSenha novaSenha) {
		this.novaSenha = novaSenha;
	}
	
	public void trocarSenha(){
		
		UsuarioBean usuarioBean = (UsuarioBean) GenericBean.getSessionValue("usuarioBean");
		novaSenha.setEmail(usuarioBean.getUsuario().getEmail());
		
		Response response = service.alterarSenha(novaSenha);
		
		int status = response.getStatus();

		if (status == HttpStatus.SC_ACCEPTED) {
			GenericBean.setMessage("Senha alterada!",
					FacesMessage.SEVERITY_INFO);
		} else{
			Erro erro = response.readEntity(Erro.class);

			GenericBean.setMessage(erro.getMensagem(),
					FacesMessage.SEVERITY_ERROR);
		}
	}

}
