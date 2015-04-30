package entidadesDAO;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.edu.entidade.Questao;

public class QuestaoDAO implements GenericDAO<Questao> {

	@Override
	public Questao create(Questao questao) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EAV");

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
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EAV");

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
	public int delete(Questao entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Questao> getAll() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EAV");

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
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EAV");

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

}
