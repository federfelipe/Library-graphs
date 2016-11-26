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
	public static void BFS(Graph g, Vertice s) {
				
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
	
	/**
	 * @brief DFS method for traversing and searching tree or graph data structures
	 * @param Graph g
	 *            The graph 
	 * @return void.
	 */	
       public static void DFS(Graph g){
			List<Vertice> vertices = g.getV();
			int compconex=1;
			for (Vertice v : vertices) {
				if(v.isVisitado() == false){
					v.setComponente(compconex);
					DFSVisit(v,compconex);
					compconex++;
				}
			 }
		 }
		
       /**
   	 * @brief DFSVisit is a complement method of DFS
   	 * @param Vertice v, int compconex
   	 *            The vertice and the number of compConex 
   	 * @return void.
   	 */	
    	public static void DFSVisit(Vertice v,int compconex){
			v.setVisitado(true);
			System.out.println(v.getNumero());
			for (Vertice w : v.adj) {
				if(w.isVisitado() == false){
					DFSVisit(w,compconex);
					w.setComponente(compconex);
				}
			}
		}
    	
    	// Dijkstra with PriorityQueue implemented using MinHeap
    	
 /*public class DikjstraAlgorithm {
    public static void main(String[] args) {

        Graph graph = new Graph(9);
        for (int i = 0; i < 9; i++) {
            graph.addVertex(i);
        }
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 7, 8);
        graph.addEdge(1, 0, 4);
        graph.addEdge(1, 7, 11);
        graph.addEdge(1, 2, 8);
        graph.addEdge(2, 1, 8);
        graph.addEdge(2, 3, 7);
        graph.addEdge(2, 8, 2);
        graph.addEdge(2, 5, 4);
        graph.addEdge(3, 2, 7);
        graph.addEdge(3, 4, 9);
        graph.addEdge(3, 5, 14);
        graph.addEdge(4, 3, 9);
        graph.addEdge(4, 5, 10);
        graph.addEdge(5, 2, 4);
        graph.addEdge(5, 3, 14);
        graph.addEdge(5, 4, 10);
        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 5, 2);
        graph.addEdge(6, 7, 1);
        graph.addEdge(6, 8, 6);
        graph.addEdge(7, 0, 8);
        graph.addEdge(7, 1, 11);
        graph.addEdge(7, 6, 1);
        graph.addEdge(7, 8, 7);
        graph.addEdge(8, 2, 2);
        graph.addEdge(8, 6, 6);
        graph.addEdge(8, 7, 7);
        graph.findShortestPaths(0);
    }

    public static class Graph {
        Vertex[] vertices;
        int maxSize;
        int size;

        public Graph(int maxSize) {
            this.maxSize = maxSize;
            vertices = new Vertex[maxSize];
        }

        public void addVertex(int name) {
            vertices[size++] = new Vertex(name);
        }

        public void addEdge(int sourceName, int destinationName, int weight) {
            int srcIndex = sourceName;
            int destiIndex = destinationName;
            vertices[srcIndex].adj = new Neighbour(destiIndex, weight, vertices[srcIndex].adj);
            vertices[destiIndex].indegree++;
        }
        
        public void findShortestPaths(int sourceName){
            applyDikjstraAlgorith(vertices[sourceName]);
            for(int i = 0; i < maxSize; i++){
                System.out.println("Distance of "+vertices[i].name+" from Source: "+ vertices[i].cost);
            }
        }
        
        public class Vertex {
            int cost;
            int name;
            Neighbour adj;
            int indegree;
            State state;

            public Vertex(int name) {
                this.name = name;
                cost = Integer.MAX_VALUE;
                state = State.NEW;
            }

            public int compareTo(Vertex v) {
                if (this.cost == v.cost) {
                    return 0;
                }
                if (this.cost < v.cost) {
                    return -1;
                }
                return 1;
            }
        }

        public enum State {
            NEW, IN_Q, VISITED
        }

        public class Neighbour {
            int index;
            Neighbour next;
            int weight;

            Neighbour(int index, int weight, Neighbour next) {
                this.index = index;
                this.next = next;
                this.weight = weight;
            }
        }

        public void applyDikjstraAlgorith(Vertex src) {
            Heap heap = new Heap(maxSize);
            heap.add(src);
            src.state = State.IN_Q;
            src.cost = 0;
            while (!heap.isEmpty()) {
                Vertex u = heap.remove();
                u.state = State.VISITED;
                Neighbour temp = u.adj;
                while (temp != null) {
                    if (vertices[temp.index].state == State.NEW) {
                        heap.add(vertices[temp.index]);
                        vertices[temp.index].state = State.IN_Q;
                    }
                    if (vertices[temp.index].cost > u.cost + temp.weight) {
                        vertices[temp.index].cost = u.cost + temp.weight;
                        heap.heapifyUP(vertices[temp.index]);
                    }
                    temp = temp.next;
                }
            }
        }

        public static class Heap {
            private Vertex[] heap;
            private int maxSize;
            private int size;

            public Heap(int maxSize) {
                this.maxSize = maxSize;
                heap = new Vertex[maxSize];
            }

            public void add(Vertex u) {
                heap[size++] = u;
                heapifyUP(size - 1);
            }

            public void heapifyUP(Vertex u) {
                for (int i = 0; i < maxSize; i++) {
                    if (u == heap[i]) {
                        heapifyUP(i);
                        break;
                    }
                }
            }

            public void heapifyUP(int position) {
                int currentIndex = position;
                Vertex currentItem = heap[currentIndex];
                int parentIndex = (currentIndex - 1) / 2;
                Vertex parentItem = heap[parentIndex];
                while (currentItem.compareTo(parentItem) == -1) {
                    swap(currentIndex, parentIndex);
                    currentIndex = parentIndex;
                    if (currentIndex == 0) {
                        break;
                    }
                    currentItem = heap[currentIndex];
                    parentIndex = (currentIndex - 1) / 2;
                    parentItem = heap[parentIndex];
                }
            }

            public Vertex remove() {
                Vertex v = heap[0];
                swap(0, size - 1);
                heap[size - 1] = null;
                size--;
                heapifyDown(0);
                return v;
            }

            public void heapifyDown(int postion) {
                if (size == 1) {
                    return;
                }

                int currentIndex = postion;
                Vertex currentItem = heap[currentIndex];
                int leftChildIndex = 2 * currentIndex + 1;
                int rightChildIndex = 2 * currentIndex + 2;
                int childIndex;
                if (heap[leftChildIndex] == null) {
                    return;
                }
                if (heap[rightChildIndex] == null) {
                    childIndex = leftChildIndex;
                } else if (heap[rightChildIndex].compareTo(heap[leftChildIndex]) == -1) {
                    childIndex = rightChildIndex;
                } else {
                    childIndex = leftChildIndex;
                }
                Vertex childItem = heap[childIndex];
                while (currentItem.compareTo(childItem) == 1) {
                    swap(currentIndex, childIndex);
                    currentIndex = childIndex;
                    currentItem = heap[currentIndex];
                    leftChildIndex = 2 * currentIndex + 1;
                    rightChildIndex = 2 * currentIndex + 2;
                    if (heap[leftChildIndex] == null) {
                        return;
                    }
                    if (heap[rightChildIndex] == null) {
                        childIndex = leftChildIndex;
                    } else if (heap[rightChildIndex].compareTo(heap[leftChildIndex]) == -1) {
                        childIndex = rightChildIndex;
                    } else {
                        childIndex = leftChildIndex;
                    }
                }
            }

            public void swap(int index1, int index2) {
                Vertex temp = heap[index1];
                heap[index1] = heap[index2];
                heap[index2] = temp;
            }

            public boolean isEmpty() {

                return size == 0;
            }
        }
      }
    }*/

 
}