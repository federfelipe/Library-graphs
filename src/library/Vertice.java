import java.util.ArrayList;
import java.util.List;

public class Vertice {
	
	int numero;
	boolean visitado=false;
	Vertice pai;
	List<Vertice> adj = new ArrayList<Vertice>();
	
	public Vertice(int num){
		numero=num;
	}
	
	public void addAdj(Vertice v){
		adj.add(v);
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean isVisitado() {
		return visitado;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

	public List<Vertice> getAdj() {
		return adj;
	}

	
}
