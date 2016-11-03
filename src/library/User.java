package library;

/**
 * The main class of the project, used only to call and test functions from 
 * {@link library.LibraryGraphs}.
 */
public class User {
	public static void main(String[] args) {
		
		LibraryGraphs teste = new LibraryGraphs();
		Graph g = teste.readFileAsAdjacencyList(args[0]);
		g.printVertices();
	}

}