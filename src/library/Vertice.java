package library;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a vertice from a {@link library.Graph}.
 */
public class Vertice {
	
	private int number;
	private boolean visited=false;
	private Vertice father;
	//Used for Graph's representation
	private int level=0;

	private int componente;
	List<Vertice> adj = new ArrayList<Vertice>();
	
	public Vertice(int num){
		number=num;
	}
	
	public void addAdj(Vertice v){
		adj.add(v);
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean isVisited() {
		return visited;
	}
	
	public Vertice getFather() {
		return father;
	}

	public void setFather(Vertice father) {
		this.father = father;
		this.level = (father.getLevel()+1);
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	public void setComponente(int componente){
		this.componente=componente;
	}
	public int getComponente(){
		return componente;
	}

	public void setLevel(int level){
		this.level = level;
	}
	
	public int getLevel(){
		return level;
	}

	public List<Vertice> getAdj() {
		return adj;
	}

	
}
