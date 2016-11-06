package library;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * A class containing functions to manipulate graphs.
 */
public class LibraryGraphs {

	// Queue of BFS method
	static Queue<Vertice> q = new LinkedList<Vertice>();
	
	/**
	 * @brief Reads a file containing information about a graph as an adjacency
	 *        list.
	 * @param filePath
	 *            The path to the file containing information about a graph.
	 * @return A {@link library.Graph} containing the information read from
	 *         file.
	 */
	public Graph readFileAsAdjacencyList(String filePath) {
		BufferedReader br = null;
		String sCurrentLine;
		String[] line;
		Graph g = new Graph();
		List<Vertice> v = new ArrayList<Vertice>();
		List<Edge> e = new ArrayList<Edge>();
		int size;

		try {
			br = new BufferedReader(new FileReader(filePath));
			try {
				size = Integer.parseInt(br.readLine());

				for (int i = 0; i < size; i++) {
					v.add(new Vertice(i));
				}

				while ((sCurrentLine = br.readLine()) != null) {
					line = sCurrentLine.split(" ");
					v.get(Integer.parseInt(line[0]) - 1).addAdj(v.get(Integer.parseInt(line[1]) - 1));
					v.get(Integer.parseInt(line[1]) - 1).addAdj(v.get(Integer.parseInt(line[0]) - 1));
					if (line.length == 3) {
						e.add(new Edge(v.get(Integer.parseInt(line[0]) - 1), v.get(Integer.parseInt(line[1]) - 1),
								Double.parseDouble(line[2])));
					} 
				}
			} catch (NumberFormatException nfe) {
				System.out.println("The file " + filePath + " doesn't match with the library standard");
				return null;
			}
			g.setE(e);
			g.setV(v);

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				return null;
			}
		}
		return g;
	}
	
	/**
	 * @brief BFS method for traversing and searching tree or graph data structures
	 * @param Graph g, Vertice s
	 *            The graph and a initial vertex.
	 * @return void.
	 */	
	public void BFS(Graph g, Vertice s) {
				
		    List<Vertice> vet = g.getV();
			Vertice ve = vet.get(s.getNumero());
    
			try{
			q.offer(ve);
			ve.setVisitado(true);
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