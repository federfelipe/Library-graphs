import java.util.List;

public class BuscaProfund {

	public static void DFS(Grafo g){
		List<Vertice> lista = g.getLista();
		for (Vertice v : lista) {
			if(v.visitado == false){
				DFSVisit(g,v);
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
