import java.util.List;

public class BuscaProfund {

	public static void DFS(Grafo g){
		List<Vertice> lista = g.getLista();
		int compconex=1;
		for (Vertice v : lista) {
			if(v.visitado == false){
				DFSVisit(g,v);
				compconex++;
			}
		}
	}
	
	public static void DFSVisit(Grafo g, Vertice v){
		v.visitado = true;
		System.out.println(v.numero);
		for (Vertice w : v.adj) {
			if(w.visitado == false){
				DFSVisit(g,w);
			}
		}
	}
}
