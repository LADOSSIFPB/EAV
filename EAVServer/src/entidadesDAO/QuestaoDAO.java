package entidadesDAO;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import br.edu.entidade.Assunto;
import br.edu.entidade.Questao;
import br.edu.util.EAVException;

public class QuestaoDAO extends ServiceDAO implements GenericDAO<Questao> {

	@Override
	public Questao create(Questao questao) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("EAV");

		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(questao);
			em.getTransaction().commit();
		} catch (EntityExistsException e) {
			// TODO: Verificar se o hibernate possui um status de falha ou
			// sucesso

		} finally {
			em.close();
			emf.close();
		}

		return questao;
	}

	@Override
	public Questao update(Questao questao) {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("EAV");

		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			em.merge(questao);
			em.getTransaction().commit();

		} catch (Exception e) {
			// TODO: Verificar se o hibernate possui um status de falha ou
			// sucesso
			questao = null;
		} finally {
			em.close();
			emf.close();
		}

		return questao;
	}

	@Override
	public void delete(Questao questao) throws EAVException {
		ServiceDAO.iniciarConexao();

		try {
			em.getTransaction().begin();
			em.remove(questao);
			em.getTransaction().commit();
		} catch (EntityExistsException | RollbackException e) {
			throw new EAVException(EAVException.QUESTAO_INVALIDA);
		} finally {
			ServiceDAO.fecharConexao();
		}
	}

	@Override
	public List<Questao> getAll() {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("EAV");

		EntityManager em = emf.createEntityManager();

		List<Questao> questao = null;

		try {

			TypedQuery<Questao> query = em.createNamedQuery("Questao.findAll",
					Questao.class);
			questao = query.getResultList();

		} catch (Exception e) {
			// TODO: Verificar se o hibernate possui um status de falha ou
			// sucesso
		} finally {
			em.close();
			emf.close();
		}

		return questao;
	}

	@Override
	public Questao findById(Questao questao) {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("EAV");

		EntityManager em = emf.createEntityManager();

		try {
			questao = em.find(Questao.class, questao.getIdQuestao());
		} catch (Exception e) {
			// TODO: Verificar se o hibernate possui um status de falha ou
			// sucesso
			questao = null;
		} finally {
			em.close();
			emf.close();
		}

		return questao;
	}

	public List<Questao> findByAssunto(Assunto assunto) {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("EAV");

		EntityManager em = emf.createEntityManager();
		
		List<Questao> questoes = new LinkedList<Questao>();

		try {
			TypedQuery<Questao> query = em.createNamedQuery("Questao.findByAssunto",
					Questao.class).setParameter("assunto_id", assunto);
			questoes = query.getResultList();
		} catch (Exception e) {
			// TODO: Verificar se o hibernate possui um status de falha ou
			// sucesso
			assunto = null;
		} finally {
			em.close();
			emf.close();
		}

		return questoes;
	}

}
