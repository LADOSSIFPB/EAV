package entidadesDAO;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;

import br.edu.entidade.OpcaoCorreta;
import br.edu.entidade.Questao;

public class OpcaoCorretaDAO extends ServiceDAO{

	public OpcaoCorreta create(OpcaoCorreta opcaoCorreta) {
		ServiceDAO.iniciarConexao();

		try {
			em.getTransaction().begin();
			em.persist(opcaoCorreta);
			em.getTransaction().commit();
		} catch (EntityExistsException e) {
			// TODO: Verificar se o hibernate possui um status de falha ou
			// sucesso

		} finally {
			ServiceDAO.fecharConexao();
		}

		return opcaoCorreta;
	}

	public OpcaoCorreta findById(Questao questao) {
		ServiceDAO.iniciarConexao();
		
		OpcaoCorreta opcaoCorreta = new OpcaoCorreta();

		try {
			opcaoCorreta = em.find(OpcaoCorreta.class, questao.getIdQuestao());
		} catch (Exception e) {
			// TODO: Verificar se o hibernate possui um status de falha ou
			// sucesso
			opcaoCorreta = null;
		} finally {
			ServiceDAO.fecharConexao();
		}

		return opcaoCorreta;
	}
	
	public boolean findByResposta(OpcaoCorreta opcaoCorreta) {
		
		ServiceDAO.iniciarConexao();
		
		OpcaoCorreta opcaoConsulta = new OpcaoCorreta();
		

		try {
			opcaoConsulta = em.createNamedQuery("OpcaoCorreta.resposta", OpcaoCorreta.class)
					.setParameter("questao_id", opcaoCorreta.getQuestao())
					.setParameter("opcao_id", opcaoCorreta.getOpcao())
					.getSingleResult();
		} catch (NoResultException nre) {
			// TODO: Verificar se o hibernate possui um status de falha ou
			// sucesso
			opcaoConsulta = null;
			return false;
		} finally {
			ServiceDAO.fecharConexao();
		}

		return true;
	}

}
