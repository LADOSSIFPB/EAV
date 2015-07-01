package entidadesDAO;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.http.HttpStatus;

import br.edu.entidade.Usuario;
import br.edu.util.EAVException;
import br.edu.util.Erro;
import br.edu.util.StringUtil;

public class UsuarioDAO extends ServiceDAO implements GenericDAO<Usuario> {

	@Override
	public Usuario create(Usuario usuario) throws EAVException {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("EAV");

		EntityManager em = emf.createEntityManager();

		String senhaCriptografada;
		try {
			senhaCriptografada = StringUtil.criptografar(usuario.getSenha());
			usuario.setSenha(senhaCriptografada);

			try {
				em.getTransaction().begin();
				em.persist(usuario);
				em.getTransaction().commit();
			} catch (EntityExistsException | RollbackException e) {
				throw new EAVException(EAVException.USUARIO_EXISTENTE);
			} finally {
				em.close();
				emf.close();
			}
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new EAVException(EAVException.ERRO_CRIPITOGRAFAR);
		}

		return usuario;
	}

	@Override
	public Usuario update(Usuario usuario) throws EAVException {

		ServiceDAO.iniciarConexao();

		String senhaCriptografada;
		try {
			senhaCriptografada = StringUtil.criptografar(usuario.getSenha());
			usuario.setSenha(senhaCriptografada);

			try {
				em.getTransaction().begin();
				em.merge(usuario);
				em.getTransaction().commit();

			} catch (Exception e) {
				// TODO: verificar esse erro
				usuario = null;
			} finally {
				ServiceDAO.fecharConexao();
			}
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new EAVException(EAVException.ERRO_CRIPITOGRAFAR);
		}

		return usuario;
	}

	public Usuario findByLogin(Usuario usuario) throws EAVException {

		ServiceDAO.iniciarConexao();

		String senhaCriptografada;

		try {
			senhaCriptografada = StringUtil.criptografar(usuario.getSenha());
			usuario.setSenha(senhaCriptografada);

			try {

				usuario = em
						.createNamedQuery("Usuario.findByLogin", Usuario.class)
						.setParameter("email", usuario.getEmail())
						.setParameter("senha", usuario.getSenha())
						.getSingleResult();
			} catch (NoResultException nre) {
				throw new EAVException(EAVException.USUARIO_INVALIDO);

			} finally {
				ServiceDAO.fecharConexao();
			}
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new EAVException(EAVException.ERRO_CRIPITOGRAFAR);
		}

		usuario.setSenha(null);

		return usuario;

	}

	@Override
	public Usuario findById(Usuario usuario) {

		ServiceDAO.iniciarConexao();

		try {
			usuario = em.find(Usuario.class, usuario.getCpf());
		} catch (Exception e) {
			// TODO: verificar esse erro
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
			// TODO: verificar esse erro
		} finally {
			ServiceDAO.fecharConexao();
		}

		return usuarios;
	}

	@Override
	public void delete(Usuario usuario) throws EAVException {
		ServiceDAO.iniciarConexao();

		Usuario usuarioRemove = em.find(Usuario.class, usuario.getCpf());

		try {
			em.getTransaction().begin();
			em.remove(usuarioRemove);
			em.getTransaction().commit();
		} catch (EntityExistsException | RollbackException
				| IllegalArgumentException e) {
			throw new EAVException(EAVException.USUARIO_INVALIDO);
		} finally {
			ServiceDAO.fecharConexao();
		}
	}

	public Usuario usuarioIsExists(Usuario usuario) throws EAVException {

		ServiceDAO.iniciarConexao();

		String senhaCriptografada;

		try {
			senhaCriptografada = StringUtil.criptografar(usuario.getSenha());
			usuario.setSenha(senhaCriptografada);

			try {

				usuario = em
						.createNamedQuery("Usuario.findByLogin", Usuario.class)
						.setParameter("email", usuario.getEmail())
						.setParameter("senha", usuario.getSenha())
						.getSingleResult();
			} catch (NoResultException nre) {
				throw new EAVException(EAVException.SENHA_INCORRETA);

			} finally {
				ServiceDAO.fecharConexao();
			}
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new EAVException(EAVException.ERRO_CRIPITOGRAFAR);
		}

		usuario.setSenha(null);

		return usuario;

	}

}
