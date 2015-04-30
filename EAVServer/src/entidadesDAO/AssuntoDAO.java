package entidadesDAO;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.edu.entidade.Assunto;
import br.edu.entidade.Disciplina;

public class AssuntoDAO implements GenericDAO<Assunto> {

	@Override
	public Assunto create(Assunto assunto) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("EAV");

		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(assunto);
			em.getTransaction().commit();
		} catch (EntityExistsException e) {
			// TODO: Verificar se o hibernate possui um status de falha ou
			// sucesso

		} finally {
			em.close();
			emf.close();
		}

		return assunto;

	}

	@Override
	public Assunto update(Assunto assunto) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EAV");

		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			em.merge(assunto);
			em.getTransaction().commit();

		} catch (Exception e) {
			// TODO: Verificar se o hibernate possui um status de falha ou
			// sucesso
			assunto = null;
		} finally {
			em.close();
			emf.close();
		}

		return assunto;
	}

	@Override
	public int delete(Assunto assunto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Assunto> getAll() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EAV");

		EntityManager em = emf.createEntityManager();
		
		List<Assunto> assunto = null;

		try {

			TypedQuery<Assunto> query = em.createNamedQuery("Assunto.findAll",
					Assunto.class);
			assunto = query.getResultList();

		} catch (Exception e) {
			// TODO: Verificar se o hibernate possui um status de falha ou
			// sucesso
		} finally {
			em.close();
			emf.close();
		}

		return assunto;
	}

	@Override
	public Assunto findById(Assunto assunto) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EAV");

		EntityManager em = emf.createEntityManager();
		
		try {
			assunto = em.find(Assunto.class, assunto.getIdAssunto());
		} catch (Exception e) {
			// TODO: Verificar se o hibernate possui um status de falha ou
			// sucesso
			assunto = null;
		} finally {
			em.close();
			emf.close();
		}

		return assunto;
	}
	
	public List<Assunto> findByDisciplina(Disciplina disciplina) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EAV");

		EntityManager em = emf.createEntityManager();
		
		List<Assunto> assuntos;
		
		try {
			TypedQuery<Assunto> query = em.createNamedQuery("Assunto.findByDisciplina",
					Assunto.class).setParameter("discilpina_id", disciplina);
			assuntos = query.getResultList();
		} catch (Exception e) {
			// TODO: Verificar se o hibernate possui um status de falha ou
			// sucesso
			assuntos = null;
		} finally {
			em.close();
			emf.close();
		}

		return assuntos;
	}

}
