package entidadesDAO;

import java.util.List;

import br.edu.util.EAVException;

public interface GenericDAO<T> {
	
	public T create(T entity) throws EAVException;
	
	public T update(T entity) throws EAVException;
	
	public void delete(T entity) throws EAVException;

	public List<T> getAll() throws EAVException;
	
	public T findById(T entity) throws EAVException;
	
}
