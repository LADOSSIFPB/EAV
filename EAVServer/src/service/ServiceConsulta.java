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
import br.edu.entidade.Opcao;
import br.edu.entidade.OpcaoCorreta;
import br.edu.entidade.Questao;
import br.edu.entidade.Usuario;
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
	public int resultadoSimulado(List<OpcaoCorreta> opcaoCorretas) {

		int acertos = 0;
		
		OpcaoCorretaDAO opcaoCorretaDAO = new OpcaoCorretaDAO();
		
		for (OpcaoCorreta opcaoCorreta : opcaoCorretas) {
			if(opcaoCorretaDAO.findByResposta(opcaoCorreta))
				acertos++;
			
		}
		
		return acertos;
	}

}
