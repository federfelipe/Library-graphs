package library;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Represents a graph as an adjacency list.
 */
public class Graph {

	private List<Vertice> v = new ArrayList<Vertice>();
	private List<Edge> e = new ArrayList<Edge>();
	private int numCompConex = 0;
	private Map<Integer, List<Vertice>> mapComp = new HashMap<Integer, List<Vertice>>();
	private boolean weighted;

	// Public constructors
	public Graph() {
		super();
	}

	/**
	 * This constructor makes a copy of the given graph.
	 * 
	 * @param g
	 *            the graph to be copied.
	 */
	public Graph(Graph g) throws NullPointerException {
		super();
		this.v = new ArrayList<Vertice>((ArrayList<Vertice>) (g.getV()));
		this.e = new ArrayList<Edge>((ArrayList<Edge>) (g.getE()));
		this.numCompConex = g.getNumComp();
		this.mapComp = new HashMap<Integer, List<Vertice>>(g.getMapComp());
		this.weighted = g.isWeighted();

	}

	// Getters and setters
	public List<Vertice> getV() {
		return v;
	}

	public void setV(List<Vertice> v) {
		this.v = v;
	}

	public List<Edge> getE() {
		return e;
	}

	public void setE(List<Edge> e) {
		this.e = e;
	}

	public int getNumComp() {
		return numCompConex;
	}

	public Map<Integer, List<Vertice>> getMapComp() {
		return mapComp;
	}

	public void setMapComp(Map<Integer, List<Vertice>> mapComp) {
		this.mapComp = mapComp;
	}

	public void setWeighted(boolean weighted) {
		this.weighted = weighted;
	}

	public boolean isWeighted() {
		return this.weighted;
	}

	/**
	 * Analyzes in which each vertex connected component is and reports the
	 * total number of connected components of the graph. Besides, put in a map
	 * all of vertices which belong the same component
	 */
	public void analyseComponentesConex() throws NullPointerException {
		int compMax = 0;
		List<Vertice> lComp = null;

		for (Vertice vet : v) {

			int comp = vet.getComponente();

			if (comp > compMax) {
				compMax = comp;
			}

			if (!(mapComp.containsKey(comp))) { // if it didn't have that number
												// of component, then put of the
												// first time
				lComp = new ArrayList<Vertice>();
				lComp.add(vet);
				mapComp.put(comp, lComp);
			} else { // For another component, cant use the same list, then is
						// created another for that component
				List<Vertice> l2Comp = new ArrayList<Vertice>();
				l2Comp.add(vet);
				mapComp.put(comp, l2Comp);
			}

		}
		numCompConex = compMax;

		System.out.println("This Graph has " + numCompConex + " conected componentes");

		List<Map.Entry<Integer, List<Vertice>>> mapSorted = sortByValue(mapComp);

		for (Map.Entry<Integer, List<Vertice>> entry : mapSorted) {
			System.out.print("The componente " + entry.getKey() + " have ");

			System.out.println(entry.getValue().size() + " vertices: ");
			List<Vertice> vet = entry.getValue();
			for (Vertice ver : vet) {
				System.out.println(ver.getNumber());
			}
		}

	}

	/**
	 * Complement method to sort the conected component by the quantity of
	 * vertices
	 */
	public static <K, V extends Comparable<? super V>> List<Map.Entry<Integer, List<Vertice>>> sortByValue(
			Map<Integer, List<Vertice>> mapComp2) {
		List<Map.Entry<Integer, List<Vertice>>> list = new LinkedList<Map.Entry<Integer, List<Vertice>>>(
				mapComp2.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Integer, List<Vertice>>>() {
			public int compare(Map.Entry<Integer, List<Vertice>> o1, Map.Entry<Integer, List<Vertice>> o2) {
				return -((Integer) o1.getValue().size()).compareTo((Integer) o2.getValue().size());
			}
		});

		return list;
	}

	/**
	 * Prints the vertices contained in a graph and it's adjacent vertices.
	 */
	public void printVertices() {
		Iterator<Vertice> it = v.iterator();
		while (it.hasNext()) {
			Vertice cv = it.next();
			System.out.println("Vertice: " + cv.getNumber());
			System.out.print("Adjacent vertices: ");
			for (Vertice v1 : cv.getAdj()) {
				System.out.print(v1.getNumber() + " ");
			}
			System.out.println("");

		}
	}

	/**
	 * Creates a matrix from the adjacency list.
	 * 
	 * @return a matrix containing the weights of each tree.
	 */
	public double[][] adjacencyMatrix() {
		double[][] m = null;
		if (!v.isEmpty() && !e.isEmpty()) {
			m = new double[v.size()][v.size()];
			Iterator<Edge> it = e.iterator();
			while (it.hasNext()) {
				Edge edge = it.next();
				m[edge.getV1().getNumber()][edge.getV2().getNumber()] = edge.getWeight();
				m[edge.getV2().getNumber()][edge.getV1().getNumber()] = edge.getWeight();
			}

		} else if (e.isEmpty()) { /* HERE */
			m = new double[v.size()][v.size()];
			Iterator<Vertice> it = v.iterator();
			while (it.hasNext()) {
				Vertice vertice = it.next();
				Iterator<Vertice> itAdj = vertice.getAdj().iterator();
				while (itAdj.hasNext()) {
					Vertice vAdj = it.next();
					m[vertice.getNumber()][vAdj.getNumber()] = 1;
				}
			}
		} else {
			System.out.println("This graph does not have vertices.");
			return null;
		}
		return m;
	}

	/**
	 * @brief Creates a text file containing info about the graph.
	 * @param path
	 *            the output file path.
	 * @param encoding
	 *            the encoding used to write the file.
	 */
	public void createInfoFile(String path, String encoding) {
		PrintWriter file = null;
		try {
			file = new PrintWriter(path, encoding);

			file.println("# n = " + getV().size());
			file.println("# m = " + getE().size());
			file.println("# d_medio = " + (((float) (getE().size())) / getV().size()));

		} catch (FileNotFoundException e) {
			System.err.println("The file " + path + " could not be opened for writting.");
		} catch (UnsupportedEncodingException e) {
			System.err.println("The encoding " + encoding + " is not supported.");
		} finally {
			if (file != null) {
				file.close();
			}
		}
	}

	/**
	 * Writes a file containing info about the graph. The file has the same
	 * format as the input one used by
	 * {@link library.LibraryGraphs#readFileAsAdjacencyList}
	 * 
	 * @param path
	 *            The output file path.
	 * @param encoding
	 *            The encoding to be used (e.g. UTF-8)
	 */
	public void createGraphFile(String path, String encoding) {
		PrintWriter file = null;
		Map<Tuple, Edge> edges = getEdgesMap();
		try {
			file = new PrintWriter(path, encoding);
			file.println(v.size());
			for (Edge edge : this.e) {
				edges.remove(new Tuple(edge.getV2(), edge.getV1()));
				file.println(
						(edge.getV1().getNumber() + 1) + " " + (edge.getV2().getNumber() + 1) + " " + edge.getWeight());
				edges.remove(new Tuple(edge.getV1(), edge.getV2()));
			}

		} catch (FileNotFoundException e) {
			System.err.println("The file " + path + " could not be opened for writting.");
		} catch (UnsupportedEncodingException e) {
			System.err.println("The encoding " + encoding + " is not supported.");
		} finally {
			if (file != null) {
				file.close();
			}
		}

	}

	/**
	 * @brief Creates a map containing all the Vertices of the Graph.
	 * @return a map containing {@link library.Integer} as keys and
	 *         {@link library.Vertice} as values.
	 */
	public Map<Integer, Vertice> getVeticesMap() {
		Map<Integer, Vertice> m = new HashMap<Integer, Vertice>();
		if (v.size() == 0) {
			System.out.println("The graph has no vertices.");
			return null;
		}
		for (Vertice vertice : v) {
			m.put(vertice.getNumber(), vertice);
		}
		return m;
	}

	/**
	 * @brief Creates a map containing all the Edges of the Graph.
	 * @return a map containing {@link library.Tuple} as keys and
	 *         {@link library.Edge} as values.
	 */
	public Map<Tuple, Edge> getEdgesMap() {
		Map<Tuple, Edge> m = new HashMap<Tuple, Edge>();
		if (e.size() == 0) {
			System.out.println("The graph has no edges");
			return null;
		}
		for (Edge edge : e) {
			m.put(new Tuple(edge.getV1(), edge.getV2()), edge);
		}

		return m;
	}

}
