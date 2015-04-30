package service;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.edu.entidade.Assunto;
import br.edu.entidade.Disciplina;
import br.edu.entidade.Usuario;
import entidadesDAO.AssuntoDAO;
import entidadesDAO.DisciplinaDAO;
import entidadesDAO.UsuarioDAO;

@Path("consulta")
public class ServiceConsulta {

	@GET
	@Path("/usuarioFindAll")
	@Produces("application/json")
	public List<Usuario> getAll() {

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		List<Usuario> usuarios = usuarioDAO.getAll();

		return usuarios;
	}

	@GET
	@Path("/disciplinasGetAll")
	@Produces("application/json")
	public List<Disciplina> disciplinasGetAll() {

		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

		List<Disciplina> disciplinas = disciplinaDAO.getAll();

		return disciplinas;
	}

	@POST
	@Path("/login")
	@Consumes("application/json")
	@Produces("application/json")
	public Usuario login(Usuario usuario) {
		UsuarioDAO dao = new UsuarioDAO();
		usuario = dao.findByLogin(usuario);
		return usuario;
	}

	@POST
	@Path("/assuntoGetByDisciplina")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Assunto> assuntoGetByDisciplina(Disciplina disciplina) {
		AssuntoDAO dao = new AssuntoDAO();
		List<Assunto> assuntos = new LinkedList<Assunto>();
		
		if(disciplina.getIdDisciplina() != 0)
		assuntos = dao.findByDisciplina(disciplina);
		
		return assuntos;
	}

}
