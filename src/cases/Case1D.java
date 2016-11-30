package cases;

import java.util.Map;

import library.Graph;
import library.LibraryGraphs;
import library.Vertice;

public class Case1D {
	public static void main(String[] args) {
		LibraryGraphs l = new LibraryGraphs();
		Graph g, r;
		long init, end, ti, te;
		ti = System.currentTimeMillis();

		init = System.currentTimeMillis();
		g = l.readFileAsAdjacencyList("E:\\EclipseWorkspace\\library-graphs\\entries\\grafo_4.txt");
		end = System.currentTimeMillis();
		System.out.println("Time to read the file: " + (end - init) + " miliseconds");

		init = System.currentTimeMillis();
		r = LibraryGraphs.dijkstra(g, 0);
		end = System.currentTimeMillis();
		System.out.println("Time to create the MST: " + (end - init) + " miliseconds");


		init = System.currentTimeMillis();
		Map<Integer, Vertice>m = r.getVerticesMap();
		try{
			System.out.println("Distance between root and vertice 10   : " + m.get(9).getDistanceToRoot());
			System.out.println("Distance between root and vertice 100  : " + m.get(99).getDistanceToRoot());
			System.out.println("Distance between root and vertice 1000 : " + m.get(999).getDistanceToRoot());
			System.out.println("Distance between root and vertice 10000: " + m.get(9999).getDistanceToRoot());
		} catch(NullPointerException e){
		}
		end = System.currentTimeMillis();
		System.out.println("Time to display distances: " + (end - init) + " miliseconds");

		te = System.currentTimeMillis();
		System.out.println("Total time spent: " + (te - ti) + " miliseconds");
	}
}
