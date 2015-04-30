package entidadesDAO;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.entidade.Usuario;

public class UsuarioDAO extends ServiceDAO implements GenericDAO<Usuario> {

	@Override
	public Usuario create(Usuario usuario) {

		ServiceDAO.iniciarConexao();

		try {
			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();
		} catch (EntityExistsException e) {
			// TODO: Verificar se o hibernate possui um status de falha ou
			// sucesso

		} finally {
			ServiceDAO.fecharConexao();
		}

		return usuario;
	}

	@Override
	public Usuario update(Usuario usuario) {

		ServiceDAO.iniciarConexao();

		try {
			em.getTransaction().begin();
			em.merge(usuario);
			em.getTransaction().commit();

		} catch (Exception e) {
			// TODO: Verificar se o hibernate possui um status de falha ou
			// sucesso
			usuario = null;
		} finally {
			ServiceDAO.fecharConexao();
		}

		return usuario;
	}

	public Usuario findByLogin(Usuario usuario) {

		ServiceDAO.iniciarConexao();

		try {
			usuario = em.createNamedQuery("Usuario.findByLogin", Usuario.class)
					.setParameter("email", usuario.getEmail())
					.setParameter("senha", usuario.getSenha())
					.getSingleResult();

		} catch (IllegalArgumentException e) {
			usuario = null;

		} catch (NoResultException nre) {
			usuario = null;

		} finally {
			ServiceDAO.fecharConexao();
		}

		return usuario;

	}

	@Override
	public Usuario findById(Usuario usuario) {

		ServiceDAO.iniciarConexao();

		try {
			usuario = em.find(Usuario.class, usuario.getCpf());
		} catch (Exception e) {
			// TODO: Verificar se o hibernate possui um status de falha ou
			// sucesso
			usuario = null;
		} finally {
			ServiceDAO.fecharConexao();
		}

		return usuario;

	}

	@Override
	public List<Usuario> getAll() {

		List<Usuario> usuarios = null;
		ServiceDAO.iniciarConexao();

		try {

			TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findAll",
					Usuario.class);
			usuarios = query.getResultList();

		} catch (Exception e) {
			// TODO: Verificar se o hibernate possui um status de falha ou
			// sucesso
		} finally {
			ServiceDAO.fecharConexao();
		}

		return usuarios;
	}

	@Override
	public int delete(Usuario entity) {
		// TODO Auto-generated method stub
		return 0;
	}

}
