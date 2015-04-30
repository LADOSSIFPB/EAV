package managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.entidade.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioBean {

	private Usuario usuario;
	
	public UsuarioBean() {
		setUsuario(new Usuario());
	}
	
	public UsuarioBean(Usuario usuario) {
		this.setUsuario(usuario);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
