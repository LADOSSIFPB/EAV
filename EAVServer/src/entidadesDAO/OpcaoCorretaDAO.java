package entidadesDAO;

import javax.persistence.EntityExistsException;

import br.edu.entidade.OpcaoCorreta;

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

	public OpcaoCorreta findById(OpcaoCorreta opcaoCorreta) {
		ServiceDAO.iniciarConexao();

		try {
			opcaoCorreta = em.find(OpcaoCorreta.class, opcaoCorreta.getQuestao());
		} catch (Exception e) {
			// TODO: Verificar se o hibernate possui um status de falha ou
			// sucesso
			opcaoCorreta = null;
		} finally {
			ServiceDAO.fecharConexao();
		}

		return opcaoCorreta;
	}

}
