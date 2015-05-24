package beanService;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.core.Response;

import managedBean.GenericBean;
import managedBean.PathRedirect;
import managedBean.UsuarioBean;

import org.apache.http.HttpStatus;

import service.EAVService;
import service.ProviderServiceFactory;
import br.edu.entidade.Usuario;
import br.edu.util.Erro;

@ManagedBean
@ViewScoped
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EAVService service = ProviderServiceFactory
			.createServiceClient(EAVService.class);

	private Usuario usuario;

	public LoginBean(Usuario usuario) {
		this.setUsuario(usuario);
	}

	public LoginBean() {
		this.usuario = new Usuario();
	}

	public void fazerLogin() {

		String pageredirect = null;

		Response response = service.login(this.usuario);

		int status = response.getStatus();

		if (status == HttpStatus.SC_ACCEPTED) {

			usuario = response.readEntity(Usuario.class);

			UsuarioBean usuarioBean = new UsuarioBean(usuario);
			GenericBean.setSessionValue("usuarioBean", usuarioBean);
			GenericBean.sendRedirect(PathRedirect.indexToUsuario);
		} else {
			Erro erro = response.readEntity(Erro.class);

			GenericBean.setMessage(erro.getMensagem(),
					FacesMessage.SEVERITY_ERROR);
		}

	}

	public void logout() {

		// Finalizando sess�o para o usu�rio logado.
		GenericBean.resetSessionScopedBean("usuarioBean");

		// Redirecionar para a p�gina inicial.
		GenericBean.sendRedirect(PathRedirect.paginaInicial);

	}

	public void singup() {
		service.cadastrarUsuario(this.usuario);
		GenericBean.sendRedirect(PathRedirect.paginaInicial);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
