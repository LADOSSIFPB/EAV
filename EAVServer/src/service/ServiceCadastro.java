package service;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.http.HttpStatus;

import br.edu.entidade.Assunto;
import br.edu.entidade.Disciplina;
import br.edu.entidade.Opcao;
import br.edu.entidade.OpcaoCorreta;
import br.edu.entidade.Questao;
import br.edu.entidade.Usuario;
import br.edu.util.EAVException;
import br.edu.util.Erro;
import br.edu.util.StringUtil;
import entidadesDAO.AssuntoDAO;
import entidadesDAO.DisciplinaDAO;
import entidadesDAO.OpcaoCorretaDAO;
import entidadesDAO.OpcaoDAO;
import entidadesDAO.QuestaoDAO;
import entidadesDAO.UsuarioDAO;

@Path("cadastro")
public class ServiceCadastro {

	public static final int elementoInicial = 0;

	@POST
	@Path("/usuario")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarUsuario(Usuario usuario) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		String cpfSemMascara = StringUtil.tirarMascaraCPF(usuario.getCpf());
		usuario.setCpf(cpfSemMascara);
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());
			
		Usuario usuarioConsulta;
		
		try {
			usuarioConsulta = usuarioDAO.create(usuario);
			if (usuarioConsulta != null) {
				
				builder.status(HttpStatus.SC_ACCEPTED);
				builder.entity(usuarioConsulta);
				
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

	@POST
	@Path("/assunto")
	@Consumes("application/json")
	@Produces("application/json")
	public Assunto cadastrarAssunto(Assunto assunto) {

		AssuntoDAO assuntoDAO = new AssuntoDAO();

		assunto = assuntoDAO.create(assunto);

		return assunto;
	}

	@POST
	@Path("/disciplina")
	@Consumes("application/json")
	@Produces("application/json")
	public Disciplina cadastrarDisciplina(Disciplina disciplina) {

		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

		disciplina = disciplinaDAO.create(disciplina);

		return disciplina;
	}

	@POST
	@Path("/questao")
	@Consumes("application/json")
	@Produces("application/json")
	public Questao cadastrarQuestao(Questao questao) {

		QuestaoDAO questaoDAO = new QuestaoDAO();
		questaoDAO.create(questao);
		OpcaoCorretaDAO opcaoCorretaDAO = new OpcaoCorretaDAO();
		OpcaoDAO opcaoDAO = new OpcaoDAO();

		OpcaoCorreta opcaoCorreta = new OpcaoCorreta();

		for (Opcao opcaoParaCadastro : questao.getOpcoes()) {
			opcaoParaCadastro.setQuestao(questao);
			opcaoDAO.create(opcaoParaCadastro);
			if (opcaoParaCadastro.isOpcaoCorreta()) {
				opcaoCorreta.setQuestao(questao);
				opcaoCorreta.setOpcao(opcaoParaCadastro);
				opcaoCorretaDAO.create(opcaoCorreta);

			}

		}

		return questao;
	}

}
