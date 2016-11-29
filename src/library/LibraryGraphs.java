package library;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

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
				boolean first = true;
				while ((sCurrentLine = br.readLine()) != null) {
					line = sCurrentLine.split(" ");
					v.get(Integer.parseInt(line[0]) - 1).addAdj(v.get(Integer.parseInt(line[1]) - 1));
					v.get(Integer.parseInt(line[1]) - 1).addAdj(v.get(Integer.parseInt(line[0]) - 1));
					if (line.length == 3) {
						if (first) {
							first = false;
							g.setWeighted(true);
						}
						e.add(new Edge(v.get(Integer.parseInt(line[0]) - 1), v.get(Integer.parseInt(line[1]) - 1),
								Double.parseDouble(line[2])));
					} else {
						if (first) {
							first = false;
							g.setWeighted(false);
						}
						e.add(new Edge(v.get(Integer.parseInt(line[0]) - 1), v.get(Integer.parseInt(line[1]) - 1), 0));
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
	 * @brief BFS method for traversing and searching tree or graph data
	 *        structures
	 * @param Graph
	 *            g, Vertice s The graph and a initial vertex.
	 * @return void.
	 */
	public void BFS(Graph g, Vertice s) {

		List<Vertice> vet = g.getV();
		// For clean up the important detail that will be used on the search, to
		// avoid conflict with DFS's result
		for (Vertice v : vet) {
			v.setVisited(false);
		}

		Vertice ve = vet.get(s.getNumber());
		ve.setLevel(0);

		try {
			q.offer(ve);
			ve.setVisited(true);
			while (!(q.isEmpty())) {
				Vertice u = q.poll();
				System.out.println("");
				System.out.print(+u.getNumber() + " level on tree: " + u.getLevel() + " father of vertices : ");
				for (Vertice v : u.adj) {
					if (v.isVisited() == false) {
						v.setFather(u);
						v.setVisited(true);
						q.add(v);
						System.out.print(v.getNumber() + " ");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @brief DFS method for traversing and searching tree or graph data
	 *        structures
	 * @param Graph
	 *            g The graph
	 * @return void.
	 */
	public void DFS(Graph g) {
		List<Vertice> vertices = g.getV();

		// For clean up the important detail that will be used on the search, to
		// avoid conflict with BFS's result
		for (Vertice ve : vertices) {
			ve.setVisited(false);
		}
		int compconex = 1;
		for (Vertice v : vertices) {
			if (v.isVisited() == false) {
				v.setComponente(compconex);
				DFSVisit(v, compconex);
				compconex++;
			}
		}
	}

	/**
	 * @brief DFSVisit is a complement method of DFS
	 * @param Vertice
	 *            v, int compconex The vertice and the number of compConex
	 * @return void.
	 */
	public static void DFSVisit(Vertice v, int compconex) {
		v.setVisited(true);
		for (Vertice w : v.adj) {
			if (w.isVisited() == false) {
				DFSVisit(w, compconex);
				w.setComponente(compconex);
			}
		}
	}

	/**
	 * Prints a matrix.
	 * 
	 * @param g
	 *            The matrix to be printed.
	 */
	public void printMatrix(double[][] g) {
		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g.length; j++) {
				System.out.print(g[i][j] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * Generates a graph containing a MST.
	 * 
	 * @param graph
	 *            The {@link library.Graph} to get the MST.
	 * @param root
	 *            The vertice to be taken as root.
	 * @return A {@link library.Graph} containing the MST and the minimum
	 *         distance of each {@link library.Vertice} between the root and
	 *         itself.
	 */
	public Graph dijkstra(Graph graph, int root) {
		Graph g = new Graph(graph), gr = new Graph();
		Vertice aux;
		List<Vertice> adj;
		double[][] w = g.adjacencyMatrix();
		Comparator<Vertice> comp = new VerticeDistanceComparator();
		PriorityQueue<Vertice> q = new PriorityQueue<Vertice>(g.getV().size(), comp);

		// Checks if the given root exists in the given graph.
		if (g.getE().get(root) == null) {
			System.out.println("Root vertex not found. Not executing Dijkstra Algorithm.");
			return null;
		}

		// Checks if is possible to use Dijkstra algorithm on the given graph
		for (Edge e : graph.getE()) {
			if (e.getWeight() < 0.0) {
				System.out.println(
						"Dijkstra can't be used on this graph: There is at least one edge with a negative weight.");
				return null;
			}
		}
		if (graph.getNumComp() != 0) {
			System.out.println(
					"Dijkstra can't be used on this graph: the given graph has more than 1 connected component");
			return null;
		}

		// Initialize single-source
		for (Vertice v : g.getV()) {
			if (v.getNumber() == root) {
				v.setDistanceToRoot(0);
			} else {
				v.setFather(null);
				v.setDistanceToRoot(Double.MAX_VALUE);
			}
			q.add(v);
		}

		// Relax
		while (gr.getV().size() < g.getV().size()) {
			aux = q.peek();
			gr.getV().add(new Vertice(aux));
			adj = aux.getAdj();

			// Gets the shortest edge and add it (and the vertice) to the
			// result graph.
			for (Vertice v : adj) {
				double weight = w[aux.getNumber()][v.getNumber()];
				// Updates current vertice's distance.
				if (v.getDistanceToRoot() > aux.getDistanceToRoot() + weight) {
					v.setDistanceToRoot(aux.getDistanceToRoot() + weight);
					v.setFather(aux);
				}
			}
			// For some reason, if remove() is used instead of peek() on first
			// line, the Queue screws up.
			q.remove();
		}

		for (Vertice v : gr.getV()) {
			if (v.getFather() != null) {
				gr.getE().add(new Edge(v, v.getFather(), v.getDistanceToRoot() - v.getFather().getDistanceToRoot()));
				// gr.getE().add(new Edge(v.getFather(), v,
				// v.getDistanceToRoot() - v.getFather().getDistanceToRoot()));
			}
		}

		return gr;

	}

}
