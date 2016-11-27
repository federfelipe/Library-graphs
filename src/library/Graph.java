package library;

import java.util.*;

/**
 * Represents a graph as an adjacency list.
 */
public class Graph{

	private List<Vertice> v = new ArrayList<Vertice>();
	private List<Edge> e = new ArrayList<Edge>();
	private int numCompConex=0;
	private Map<Integer,List<Vertice>> mapComp = new HashMap<Integer,List<Vertice>>();
	
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
	
	public int getNumComp(){
		return numCompConex;
	}
		
	/**
	 * Analyzes in which each vertex connected component is and reports the total number of connected components of the graph.
	 *  Besides, put in a map all of vertices which belong the same component
	 */
	public void analyseComponentesConex() throws NullPointerException{
		int compMax=0;
		List<Vertice> lComp = null;
		
		for (Vertice vet : v) {
		
				
				int comp = vet.getComponente();
				
				if(comp == 0){
					throw new NullPointerException();
				}
				
				if(comp > compMax){
					compMax = comp;
				}
				
			
			if(!(mapComp.containsKey(comp))){ //if it didn't have that number of component, then put of the first time
				lComp =new ArrayList<Vertice>();
				lComp.add(vet);
				mapComp.put(comp, lComp);
				}else if(mapComp.containsKey(comp)){ //As the key already exists, we get the list used and just update the list on that key. 
					List<Vertice> lCompp = mapComp.get(comp);
					lCompp.add(vet);	
				}else{ //For another component, cant use the same list, then is created another for that component
					List<Vertice> l2Comp =new ArrayList<Vertice>();
					l2Comp.add(vet);
					mapComp.put(comp, l2Comp);
				}
						
			
		}
		numCompConex = compMax;


		System.out.println("This Graph has " + numCompConex + " conected componentes");
		
		List<Map.Entry<Integer, List<Vertice>>> mapSorted = sortByValue(mapComp);
	

	
			for (Map.Entry<Integer, List<Vertice>> entry : mapSorted)
	        {
				System.out.print("The componente "+ entry.getKey() + " have ");
						
			System.out.println(entry.getValue().size() + " vertices: ");
			List<Vertice> vet = entry.getValue();
			for (Vertice ver : vet) {
				System.out.println(ver.getNumber());
			}	
	      }
				
	}
	
	/**
	 * Complement method to sort the conected component by the quantity of vertices
	 */
	public static <K, V extends Comparable<? super V>> List<Map.Entry<Integer, List<Vertice>>> sortByValue(Map<Integer, List<Vertice>> mapComp2) {
		List<Map.Entry<Integer, List<Vertice>>> list = new LinkedList<Map.Entry<Integer, List<Vertice>>>( mapComp2.entrySet() );
	        Collections.sort( list, new Comparator<Map.Entry<Integer, List<Vertice>>>()
	        {
	            public int compare( Map.Entry<Integer, List<Vertice>> o1, Map.Entry<Integer, List<Vertice>> o2 )
	            {
	                return -((Integer)o1.getValue().size()).compareTo((Integer)o2.getValue().size() );
	            }
	        } );
	  
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

		} else if (e.isEmpty()){          /* HERE */
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
	

}
