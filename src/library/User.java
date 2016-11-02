package library;

public class User {

	public static void main(String[] args) {
		
		LibraryGraphs teste = new LibraryGraphs();
		Graph g = teste.BufferedReader(args[0]);
		g.printVertices();
	}

}