package cases;

import library.Edge;
import library.Graph;
import library.LibraryGraphs;

public class Case3MST {
	public static void main(String[] args) {
		LibraryGraphs l = new LibraryGraphs();
		Graph g, r;
		long init, end, ti, te;
		ti = System.currentTimeMillis();
		
		init = System.currentTimeMillis();
		g = l.readFileAsAdjacencyList("E:\\EclipseWorkspace\\library-graphs\\entries\\grafo_4.txt");
		end = System.currentTimeMillis();
		System.out.println("Time to read the file: " + (end-init) + " miliseconds");
		

		init = System.currentTimeMillis();
		r = LibraryGraphs.dijkstra(g, 0);
		end = System.currentTimeMillis();
		System.out.println("Time to create the MST: " + (end-init) + " miliseconds");

		init = System.currentTimeMillis();
		double tw=0;
		for(Edge edge : r.getE()){
			tw+=edge.getWeight();
		}
		System.out.println("MST total weight: " + tw);
		end = System.currentTimeMillis();
		
		init = System.currentTimeMillis();
		r.createGraphFile("E:\\EclipseWorkspace\\library-graphs\\entries\\grafo1_case3.txt", "UTF-8");
		end = System.currentTimeMillis();
		System.out.println("Time to write the MST file: " + (end-init) + " miliseconds");
		
		te = System.currentTimeMillis();
		System.out.println("Total time spent: " + (te-ti) + " miliseconds");
	}

}
