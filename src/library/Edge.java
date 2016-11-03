package library;

/**
 * Represents an edge from a {@link library.Graph}.
 */
public class Edge {
	private Vertice v1, v2;
	private double weight;
	
	//public constructor
	public Edge(Vertice v1, Vertice v2){
		this.v1 = v1;
		this.v2 = v2;
	}
	public Edge(Vertice v1, Vertice v2, double weight){
		this.v1 = v1;
		this.v2 = v2;
		this.weight = weight;
	}	
	
	//Getters and setters
	public Vertice getV1() {
		return v1;
	}
	public void setV1(Vertice v1) {
		this.v1 = v1;
	}
	public Vertice getV2() {
		return v2;
	}
	public void setV2(Vertice v2) {
		this.v2 = v2;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	
}
