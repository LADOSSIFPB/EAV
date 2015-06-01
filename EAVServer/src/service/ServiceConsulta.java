package service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
import br.edu.entidade.QuestaoResultado;
import br.edu.entidade.Usuario;
import br.edu.util.EAVException;
import br.edu.util.Erro;
import entidadesDAO.AssuntoDAO;
import entidadesDAO.DisciplinaDAO;
import entidadesDAO.OpcaoCorretaDAO;
import entidadesDAO.OpcaoDAO;
import entidadesDAO.QuestaoDAO;
import entidadesDAO.UsuarioDAO;

@Path("consulta")
public class ServiceConsulta {

	@GET
	@Path("/usuarioFindAll")
	@Produces("application/json")
	public List<Usuario> usuarioGetAll() {

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

	@GET
	@Path("/assuntosGetAll")
	@Produces("application/json")
	public List<Assunto> assuntosGetAll() {

		AssuntoDAO assuntoDAO = new AssuntoDAO();

		List<Assunto> assuntos = assuntoDAO.getAll();

		return assuntos;
	}

	@POST
	@Path("/login")
	@Consumes("application/json")
	@Produces("application/json")
	public Response login(Usuario usuario){
		UsuarioDAO dao = new UsuarioDAO();
		
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Usuario usuarioConsulta = dao.findByLogin(usuario);

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
	@Path("/assuntoGetByDisciplina")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Assunto> assuntoGetByDisciplina(Disciplina disciplina) {
		AssuntoDAO dao = new AssuntoDAO();
		List<Assunto> assuntos = new LinkedList<Assunto>();

		if (disciplina.getIdDisciplina() != 0)
			assuntos = dao.findByDisciplina(disciplina);

		return assuntos;
	}

	@POST
	@Path("/questoesNavegacao")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Questao> questoesNavegacao(List<Assunto> assuntos) {

		List<Questao> questoes;
		List<Questao> questoesRetorno = new LinkedList<Questao>();

		AssuntoDAO assuntoDAO = new AssuntoDAO();
		QuestaoDAO questaoDAO = new QuestaoDAO();
		OpcaoDAO opcaoDAO = new OpcaoDAO();
		OpcaoCorretaDAO opcaoCorretaDAO = new OpcaoCorretaDAO();

		OpcaoCorreta opcaoCorreta = new OpcaoCorreta();

		for (Assunto assuntoConsulta : assuntos) {
			questoes = new LinkedList<Questao>();
			assuntoConsulta = assuntoDAO.findById(assuntoConsulta);
			questoes.addAll(questaoDAO.findByAssunto(assuntoConsulta));

			for (Questao questaoConsulta : questoes) {
				questaoConsulta.setOpcoes(opcaoDAO
						.findByQuestao(questaoConsulta));
				opcaoCorreta = opcaoCorretaDAO.findById(questaoConsulta);

				for (Opcao opcaoVerificacao : questaoConsulta.getOpcoes()) {
					if (opcaoCorreta.getOpcao().getIdOpcao() == opcaoVerificacao
							.getIdOpcao())
						opcaoVerificacao.setOpcaoCorreta(true);
				}

				questoesRetorno.add(questaoConsulta);
			}

		}

		return questoesRetorno;
	}

	@POST
	@Path("/resultadoSimulado")
	@Consumes("application/json")
	@Produces("application/json")
	public List<QuestaoResultado> resultadoSimulado(List<Questao> questoes) {

		OpcaoCorretaDAO opcaoCorretaDAO = new OpcaoCorretaDAO();
		OpcaoDAO opcaoDAO = new OpcaoDAO();
		OpcaoCorreta opcaoCorreta;
		
		List<QuestaoResultado> questoesResultado = new LinkedList<QuestaoResultado>();
		QuestaoResultado questaoResultado;

		for (Questao questao : questoes) {
			opcaoCorreta = new OpcaoCorreta();
			opcaoCorreta.getQuestao().setIdQuestao(questao.getIdQuestao());
			opcaoCorreta.getOpcao().setIdOpcao(questao.getResposta());
			
			if (opcaoCorretaDAO.findByResposta(opcaoCorreta)){
				questaoResultado = new QuestaoResultado();
				questaoResultado.setNomeQuestao(questao.getNomeQuestao());
				questaoResultado.setRespostaCorreta("Correto! R: " + opcaoDAO.findById((opcaoCorreta.getOpcao())).getNomeOpcao());
				questaoResultado.setCorreta(true);
				questoesResultado.add(questaoResultado);
			} else{
				questaoResultado = new QuestaoResultado();
				questaoResultado.setNomeQuestao(questao.getNomeQuestao());
				questaoResultado.setRespostaCorreta("Opção correta: " + (opcaoCorretaDAO.findById(questao)).getOpcao().getNomeOpcao());
				questaoResultado.setRespostaErrada("Opção selecionada: " + opcaoDAO.findById((opcaoCorreta.getOpcao())).getNomeOpcao());
				questoesResultado.add(questaoResultado);
				
			}
			
			
		}

		return questoesResultado;
	}

}
