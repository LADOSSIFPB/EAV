package entidadesDAO;

import java.util.List;

public interface GenericDAO<T> {
	
	public T create(T entity);
	
	public T update(T entity);
	
	public int delete(T entity);

	public List<T> getAll();
	
	public T findById(T entity);
	
}
