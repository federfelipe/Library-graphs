package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Represents a graph as an adjacency list.
 */
public class Graph {

	private List<Vertice> v = new ArrayList<Vertice>();
	private List<Edge> e = new ArrayList<Edge>();
	private static int numCompConex=0;
	private static Map<Integer,List<Vertice>> mapaComp = new HashMap<Integer,List<Vertice>>();
	
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
	
	public int getNumComp(){
		return numCompConex;
	}
		
	/**
	 * Analyzes in which each vertex connected component is and reports the total number of connected components of the graph.
	 *  Besides, put in a map all of vertices which belong the same component
	 */
	public void analyseComponentesConex(){
		int compMax=0;
		List<Vertice> lComp = new ArrayList<Vertice>();
		Iterator<Vertice> it = v.iterator();
		while (it.hasNext()) {
			Vertice cv = it.next();
			int comp = cv.getComponente();
			lComp.add(cv);
			mapaComp.put(comp, lComp);
						
			if(comp > compMax){
				compMax = comp;
			}
		}
		numCompConex = compMax;
		
	}
	

	/**
	 * Prints the vertices contained in a graph and it's adjacent vertices.
	 */
	public void printVertices() {
		Iterator<Vertice> it = v.iterator();
		while (it.hasNext()) {
			Vertice cv = it.next();
			System.out.println("Vertice: " + cv.getNumber());
			System.out.print("Adjacent vertices: ");
			for (Vertice v1 : cv.getAdj()) {
				System.out.print(v1.getNumber() + " ");
			}
			System.out.println("");

		}
	}

	public double[][] adjacencyMatrix() {
		double[][] m = null;
		if (!v.isEmpty() && !e.isEmpty()) {
			m = new double[v.size()][v.size()];
			Iterator<Edge> it = e.iterator();
			while (it.hasNext()) {
				Edge edge = it.next();
				m[edge.getV1().getNumber()][edge.getV2().getNumber()] = edge.getWeight();
				m[edge.getV2().getNumber()][edge.getV1().getNumber()] = edge.getWeight();
			}

		} else if (e.isEmpty()){          /* HERE */
			m = new double[v.size()][v.size()];
			Iterator<Vertice> it = v.iterator();
			while (it.hasNext()) {
				Vertice vertice = it.next();
				Iterator<Vertice> itAdj = vertice.getAdj().iterator();
					while (itAdj.hasNext()) { 
						Vertice vAdj = it.next();
						m[vertice.getNumber()][vAdj.getNumber()] = 1;
					}
		      }
		} else {
			System.out.println("This graph does not have vertices.");
			return null;
		}
		return m;
	}

}
