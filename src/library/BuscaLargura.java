import java.util.LinkedList;
import java.util.Queue;

public class BuscaLargura {
	
	static Queue<Vertice> q = new LinkedList<Vertice>();
	
	
	
	
	public static void BFS(Grafo g, Vertice s){
	
		try{
		q.offer(s);
		s.visitado = true;
		while(!(q.isEmpty())){
			Vertice u = q.poll();
			System.out.println("");
			System.out.print( + u.numero + " adjs : ");
			for (Vertice v : u.adj) {
				if(v.visitado == false){
					v.pai = u;
					v.visitado = true;
					q.add(v);
					System.out.print(v.numero + " ");
				}
			}
			
		}
	
		
	} catch(Exception e){
		e.printStackTrace();
	}
	}

}
