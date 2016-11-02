package library;

import java.util.List;

public class BuscaProfund {

	public static void DFS(Graph g){
		List<Vertice> vertices = g.getV();
		int compconex=1;
		for (Vertice v : vertices) {
			if(v.isVisitado() == false){
				v.setComponente(compconex);
				DFSVisit(g,v,compconex);
				compconex++;
			}
		}
	}
	
	public static void DFSVisit(Graph g, Vertice v,int compconex){
		v.setVisitado(true);
		System.out.println(v.getNumero());
		for (Vertice w : v.adj) {
			if(w.isVisitado() == false){
				//DFSVisit(g,w);
				w.setComponente(compconex);
				
			}
		}
	}
}
