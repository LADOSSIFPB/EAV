package entidadesDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ServiceDAO {
	
	static EntityManagerFactory emf;

	static EntityManager em;
	
	public static void iniciarConexao (){
		
		emf = Persistence
				.createEntityManagerFactory("EAV");

		em = emf.createEntityManager();
	}
	
	public static void fecharConexao(){
		em.close();
		emf.close();
	}	
}
