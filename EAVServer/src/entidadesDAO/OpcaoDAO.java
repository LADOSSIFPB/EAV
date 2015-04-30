package entidadesDAO;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.TypedQuery;

import br.edu.entidade.Opcao;

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
	public int delete(Opcao entity) {
		// TODO Auto-generated method stub
		return 0;
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

}
