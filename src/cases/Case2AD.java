package cases;

import library.Graph;
import library.LibraryGraphs;

public class Case2AD {
	public static void main(String[] args) {
		LibraryGraphs l = new LibraryGraphs();
		Graph g;
		long init, end, ti, te;
		ti = System.currentTimeMillis();
		
		init = System.currentTimeMillis();
		g = l.readFileAsAdjacencyList("E:\\EclipseWorkspace\\library-graphs\\entries\\grafo_4.txt");
		end = System.currentTimeMillis();
		System.out.println("Time to read the file: " + (end-init) + " miliseconds");
		

		init = System.currentTimeMillis();
		System.out.println("Average distance: " + g.averageDistance());
		end = System.currentTimeMillis();
		System.out.println("Time to calculate average distance: " + (end-init) + " miliseconds");

		
		te = System.currentTimeMillis();
		System.out.println("Total time spent: " + (te-ti) + " miliseconds");
	}
}
