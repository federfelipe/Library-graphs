package library;

import java.util.ArrayList;
import java.util.List;

public class Vertice {
	
	private int numero;
	private boolean visitado=false;
	private Vertice pai;

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

	public List<Vertice> getAdj() {
		return adj;
	}

	
}
