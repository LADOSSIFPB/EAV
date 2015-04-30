package entidadesDAO;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.edu.entidade.Disciplina;

public class DisciplinaDAO implements GenericDAO<Disciplina> {

	@Override
	public Disciplina create(Disciplina disciplina) {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("EAV");

		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(disciplina);
			em.getTransaction().commit();

		} catch (EntityExistsException e) {
			// TODO: Verificar se o hibernate possui um status de falha ou
			// sucesso

		} finally {
			em.close();
			emf.close();
		}

		return disciplina;
	}

	@Override
	public Disciplina update(Disciplina disciplina) {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("EAV");

		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			em.merge(disciplina);
			em.getTransaction().commit();

		} catch (Exception e) {
			// TODO: Verificar se o hibernate possui um status de falha ou
			// sucesso
			disciplina = null;
		} finally {
			em.close();
			emf.close();
		}

		return disciplina;
	}

	@Override
	public Disciplina findById(Disciplina disciplina) {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("EAV");

		EntityManager em = emf.createEntityManager();

		try {
			disciplina = em
					.find(Disciplina.class, disciplina.getIdDisciplina());
		} catch (Exception e) {
			// TODO: Verificar se o hibernate possui um status de falha ou
			// sucesso
			disciplina = null;
		} finally {
			em.close();
			emf.close();
		}

		return disciplina;

	}

	@Override
	public List<Disciplina> getAll() {

		List<Disciplina> disciplinas = null;

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("EAV");

		EntityManager em = emf.createEntityManager();

		try {
			TypedQuery<Disciplina> query = em.createNamedQuery(
					"Disciplina.findAll", Disciplina.class);
			disciplinas = query.getResultList();

		} catch (Exception e) {
			// TODO: Verificar se o hibernate possui um status de falha ou
			// sucesso
		} finally {
			em.close();
			emf.close();
		}

		return disciplinas;
	}

	@Override
	public int delete(Disciplina entity) {
		// TODO Auto-generated method stub
		return 0;
	}

}
