import java.util.List;

public class BuscaProfund {

	public static void DFS(Grafo g){
		List<Vertice> lista = g.getLista();
		int compconex=1;
		for (Vertice v : lista) {
			if(v.isVisitado() == false){
				v.setComponente(compconex);
				DFSVisit(g,v);
				compconex++;
			}
		}
	}
	
	public static void DFSVisit(Grafo g, Vertice v){
		v.setVisitado(true);
		System.out.println(v.numero);
		for (Vertice w : v.adj) {
			if(w.isVisitado() == false){
				DFSVisit(g,w);
			}
		}
	}
}
