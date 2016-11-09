package library;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a vertice from a {@link library.Graph}.
 */
public class Vertice {
	
	private int numero;
	private boolean visitado=false;
	private Vertice pai;
	//Usado para representar. Ver com o professor se é isso mesmo
	private int nivel=0;

	private int componente;
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
	
	public Vertice getPai() {
		return pai;
	}

	public void setPai(Vertice pai) {
		this.pai = pai;
		this.nivel = (pai.getNivel()+1);
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}
	
	public void setComponente(int componente){
		this.componente=componente;
	}
	public int getComponente(){
		return componente;
	}
	//Não sei se deverá ser utilizado
	public void setNivel(int nivel){
		this.nivel = nivel;
	}
	
	public int getNivel(){
		return nivel;
	}

	public List<Vertice> getAdj() {
		return adj;
	}

	
}
