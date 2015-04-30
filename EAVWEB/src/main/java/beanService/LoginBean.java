package beanService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import managedBean.GenericBean;
import managedBean.PathRedirect;
import managedBean.UsuarioBean;
import service.EAVService;
import service.ProviderServiceFactory;
import br.edu.entidade.Usuario;

@ManagedBean
@ViewScoped
public class LoginBean {

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

		usuario = service.login(this.usuario);
		if (usuario != null) {
			UsuarioBean usuarioBean = new UsuarioBean(usuario);
			GenericBean.setSessionValue("usuarioBean", usuarioBean);
			GenericBean.sendRedirect(PathRedirect.indexToUsuario);
		}
		GenericBean.setMessage("Login incorreto", FacesMessage.SEVERITY_ERROR);

	}

	public void logout() {

		// Finalizando sessão para o usuário logado.
		GenericBean.resetSessionScopedBean("usuarioBean");

		// Redirecionar para a página inicial.
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
