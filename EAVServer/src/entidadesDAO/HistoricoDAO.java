package entidadesDAO;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import br.edu.entidade.Assunto;
import br.edu.entidade.Disciplina;
import br.edu.entidade.Historico;
import br.edu.entidade.Usuario;
import br.edu.util.EAVException;

public class HistoricoDAO extends ServiceDAO implements GenericDAO<Historico> {

	@Override
	public Historico create(Historico historico) throws EAVException {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("EAV");

		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(historico);
			em.getTransaction().commit();
		} catch (EntityExistsException e) {
			throw new EAVException(EAVException.USUARIO_EXISTENTE);
		} finally {
			em.close();
			emf.close();
		}

		return historico;
	}

	@Override
	public Historico update(Historico historico) throws EAVException {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("EAV");

		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			em.merge(historico);
			em.getTransaction().commit();
		} catch (EntityExistsException e) {
			// TODO: Verificar se o hibernate possui um status de falha ou
			// sucesso

		} finally {
			em.close();
			emf.close();
		}

		return historico;
	}

	@Override
	public void delete(Historico historico) throws EAVException {
		ServiceDAO.iniciarConexao();

		try {
			em.getTransaction().begin();
			em.remove(historico);
			em.getTransaction().commit();
		} catch (EntityExistsException | RollbackException e) {
			throw new EAVException(EAVException.ASSUNTO_INVALIDO);
		} finally {
			ServiceDAO.fecharConexao();
		}

	}

	@Override
	public List<Historico> getAll() throws EAVException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EAV");

		EntityManager em = emf.createEntityManager();
		
		List<Historico> historico = null;

		try {

			TypedQuery<Historico> query = em.createNamedQuery("Historico.findAll",
					Historico.class);
			historico = query.getResultList();

		} catch (Exception e) {
			// TODO: Verificar se o hibernate possui um status de falha ou
			// sucesso
		} finally {
			em.close();
			emf.close();
		}

		return historico;
	}

	@Override
	public Historico findById(Historico historico) throws EAVException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EAV");

		EntityManager em = emf.createEntityManager();
		
		try {
			historico = em.find(Historico.class, historico.getUsuario().getCpf());
		} catch (Exception e) {
			// TODO: Verificar se o hibernate possui um status de falha ou
			// sucesso
			historico = null;
		} finally {
			em.close();
			emf.close();
		}

		return historico;
	}
	
	public List<Historico> findByUsuario(Usuario usuario) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EAV");

		EntityManager em = emf.createEntityManager();
		
		List<Historico> historicos;
		
		try {
			TypedQuery<Historico> query = em.createNamedQuery("Historico.findByUser",
					Historico.class).setParameter("usuario_id", usuario);
			historicos = query.getResultList();
		} catch (Exception e) {
			// TODO: Verificar se o hibernate possui um status de falha ou
			// sucesso
			historicos = null;
		} finally {
			em.close();
			emf.close();
		}

		return historicos;
	}
	
	public List<Assunto> getAssuntos(Historico historico) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EAV");

		EntityManager em = emf.createEntityManager();
		
		List<Assunto> assuntos;
		
		try {
			TypedQuery<Assunto> query = em.createNamedQuery("Historico.getAssuntos",
					Assunto.class).setParameter("historico_id", historico.getIdHistorico());
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
