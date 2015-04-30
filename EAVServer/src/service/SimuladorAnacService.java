package service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath(value = "SimuladorAnacService")
public class SimuladorAnacService extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();

	public SimuladorAnacService() {
		
		// ADD YOUR RESTFUL RESOURCES HERE
		this.singletons.add(new ServiceCadastro());
		this.singletons.add(new ServiceConsulta());
	}

	public Set<Class<?>> getClasses() {
		return this.empty;
	}

	public Set<Object> getSingletons() {
		return this.singletons;
	}
}