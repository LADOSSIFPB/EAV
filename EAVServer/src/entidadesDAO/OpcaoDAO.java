package entidadesDAO;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import br.edu.entidade.Opcao;
import br.edu.entidade.Questao;
import br.edu.util.EAVException;

public class OpcaoDAO extends ServiceDAO implements GenericDAO<Opcao> {

	@Override
	public Opcao create(Opcao opcao) {
		ServiceDAO.iniciarConexao();

		try {
			em.getTransaction().begin();
			em.persist(opcao);
			em.getTransaction().commit();
		} catch (EntityExistsException e) {
			// TODO: Verificar se o hibernate possui um status de falha ou
			// sucesso

		} finally {
			ServiceDAO.fecharConexao();
		}

		return opcao;
	}

	@Override
	public Opcao update(Opcao opcao) {
		ServiceDAO.iniciarConexao();

		try {
			em.getTransaction().begin();
			em.merge(opcao);
			em.getTransaction().commit();

		} catch (Exception e) {
			// TODO: Verificar se o hibernate possui um status de falha ou
			// sucesso
			opcao = null;
		} finally {
			ServiceDAO.fecharConexao();
		}

		return opcao;
	}

	@Override
	public void delete(Opcao opcao) throws EAVException {
		ServiceDAO.iniciarConexao();

		try {
			em.getTransaction().begin();
			em.remove(opcao);
			em.getTransaction().commit();
		} catch (EntityExistsException | RollbackException e) {
			throw new EAVException(EAVException.OPCAO_INVALIDA);
		}
		finally {
			ServiceDAO.fecharConexao();
		}
	}

	@Override
	public List<Opcao> getAll() {
		List<Opcao> opcao = null;
		ServiceDAO.iniciarConexao();

		try {

			TypedQuery<Opcao> query = em.createNamedQuery("Opcao.findAll",
					Opcao.class);
			opcao = query.getResultList();

		} catch (Exception e) {
			// TODO: Verificar se o hibernate possui um status de falha ou
			// sucesso
		} finally {
			ServiceDAO.fecharConexao();
		}

		return opcao;
	}

	@Override
	public Opcao findById(Opcao opcao) {
		ServiceDAO.iniciarConexao();

		try {
			opcao = em.find(Opcao.class, opcao.getIdOpcao());
		} catch (Exception e) {
			// TODO: Verificar se o hibernate possui um status de falha ou
			// sucesso
			opcao = null;
		} finally {
			ServiceDAO.fecharConexao();
		}

		return opcao;
	}
	
	public List<Opcao> findByQuestao(Questao questao) {
		ServiceDAO.iniciarConexao();
		
		List<Opcao> opcoes = new LinkedList<Opcao>();

		try {
			TypedQuery<Opcao> query = em.createNamedQuery("Opcao.findByQuestao",
					Opcao.class).setParameter("questao_id", questao);
			opcoes = query.getResultList();
		} catch (Exception e) {
			// TODO: Verificar se o hibernate possui um status de falha ou
			// sucesso
			opcoes = null;
		} finally {
			ServiceDAO.fecharConexao();
		}

		return opcoes;
	}

}
