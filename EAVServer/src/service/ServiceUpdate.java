package service;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.http.HttpStatus;

import entidadesDAO.UsuarioDAO;
import br.edu.entidade.NovaSenha;
import br.edu.entidade.Usuario;
import br.edu.util.EAVException;
import br.edu.util.Erro;

@Path("update")
public class ServiceUpdate {

	@POST
	@Path("/senha")
	@Consumes("application/json")
	@Produces("application/json")
	public Response alterarSenha(NovaSenha novaSenha) {

		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = new Usuario();
		usuario.setEmail(novaSenha.getEmail());
		usuario.setSenha(novaSenha.getSenhaatual());

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());
		
		try {

			Usuario usuarioConsulta = dao.usuarioIsExists(usuario);

			if (usuarioConsulta != null) {
				
				usuarioConsulta.setSenha(novaSenha.getSenhanova());
				Usuario usuarioNovaSenha = dao.update(usuarioConsulta);

				builder.status(HttpStatus.SC_ACCEPTED);
				builder.entity(usuarioNovaSenha);

			} else {

				builder.status(HttpStatus.SC_UNAUTHORIZED);
			}

		} catch (EAVException eavException) {

			Erro erro = new Erro();
			erro.setCodigo(eavException.getCodigoErro());
			erro.setMensagem(eavException.getMessage());

			builder.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();

	}

}
