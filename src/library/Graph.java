package library;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a graph as an adjacency list.
 */
public class Graph {

	private List<Vertice> v = new ArrayList<Vertice>();
	private List<Edge> e = new ArrayList<Edge>();

	// Getters and setters
	public List<Vertice> getV() {
		return v;
	}

	public void setV(List<Vertice> v) {
		this.v = v;
	}

	public List<Edge> getE() {
		return e;
	}

	public void setE(List<Edge> e) {
		this.e = e;
	}

	/**
	 * Prints the vertices contained in a graph and it's adjacent vertices.
	 */
	public void printVertices() {
		Iterator<Vertice> it = v.iterator();
		while (it.hasNext()) {
			Vertice cv = it.next();
			System.out.println("Vertice: " + cv.getNumero());
			System.out.print("Adjacent vertices: ");
			for (Vertice v1 : cv.getAdj()) {
				System.out.print(v1.getNumero() + " ");
			}
			System.out.println("");

		}
	}

	public double[][] adjacencyMatrix() {
		double[][] m = null;
		if (v != null && e != null) {
			m = new double[v.size()][v.size()];
			Iterator<Edge> it = e.iterator();
			while (it.hasNext()) {
				Edge edge = it.next();
				m[edge.getV1().getNumero()][edge.getV2().getNumero()] = edge.getWeight();
				m[edge.getV2().getNumero()][edge.getV1().getNumero()] = edge.getWeight();
			}

		} else {
			System.out.println("This graph does not have vertices.");
			return null;
		}

		return m;
	}

}
