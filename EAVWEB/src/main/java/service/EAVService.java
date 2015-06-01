package service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.edu.entidade.Assunto;
import br.edu.entidade.Disciplina;
import br.edu.entidade.NovaSenha;
import br.edu.entidade.Questao;
import br.edu.entidade.QuestaoResultado;
import br.edu.entidade.Usuario;


public interface EAVService {

	@POST
	@Path("/consulta/login")
	@Consumes("application/json")
	@Produces("application/json")
	public Response login(Usuario usuario);
	
	@POST
	@Path("/cadastro/usuario")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarUsuario(Usuario usuario);
	
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
	
	@GET
	@Path("/consulta/assuntosGetAll")
	@Produces("application/json")
	public List<Assunto> assuntosGetAll();
	
	@POST
	@Path("/consulta/questoesNavegacao")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Questao> questoesNavegacao(List<Assunto> assuntos);
	
	@POST
	@Path("/consulta/resultadoSimulado")
	@Consumes("application/json")
	@Produces("application/json")
	public List<QuestaoResultado> resultadoSimulado(List<Questao> questoes);
	
	@POST
	@Path("/update/senha")
	@Consumes("application/json")
	@Produces("application/json")
	public Response alterarSenha(NovaSenha novaSenha);

}