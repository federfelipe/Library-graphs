package library;

import java.util.LinkedList;
import java.util.Queue;

public class BuscaLargura {
	
	static Queue<Vertice> q = new LinkedList<Vertice>();
	
	
	public static void BFS(Graph g, Vertice s){
	
		try{
		q.offer(s);
		s.setVisitado(true);
		while(!(q.isEmpty())){
			Vertice u = q.poll();
			System.out.println("");
			System.out.print( + u.getNumero() + " adjs : ");
			for (Vertice v : u.adj) {
				if(v.isVisitado() == false){
					v.setPai(u);
					v.setVisitado(true);
					q.add(v);
					System.out.print(v.getNumero() + " ");
				}
			}
			
		}
	
		
	} catch(Exception e){
		e.printStackTrace();
	}
	}

}
