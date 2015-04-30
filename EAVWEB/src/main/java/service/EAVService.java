package service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.edu.entidade.Assunto;
import br.edu.entidade.Disciplina;
import br.edu.entidade.Questao;
import br.edu.entidade.Usuario;


public interface EAVService {

	@POST
	@Path("/consulta/login")
	@Consumes("application/json")
	@Produces("application/json")
	public Usuario login(Usuario usuario);
	
	@POST
	@Path("/cadastro/usuario")
	@Consumes("application/json")
	@Produces("application/json")
	public Usuario cadastrarUsuario(Usuario usuario);
	
	@POST
	@Path("/cadastro/questao")
	@Consumes("application/json")
	@Produces("application/json")
	public Questao cadastrarQuestao(Questao questao); 
	
	@GET
	@Path("/consulta/disciplinasGetAll")
	@Produces("application/json")
	public List<Disciplina> disciplinasGetAll();
	
	@POST
	@Path("/consulta/assuntoGetByDisciplina")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Assunto> assuntoGetByDisciplina(Disciplina disciplina);

}