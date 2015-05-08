package managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class DadosProva {

	private int acertos;
	
	public DadosProva() {
		// TODO Auto-generated constructor stub
	}
	
	public DadosProva(int acertos) {
		this.setAcertos(acertos);
	}

	public int getAcertos() {
		return acertos;
	}

	public void setAcertos(int acertos) {
		this.acertos = acertos;
	}
	
}
