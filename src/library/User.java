package library;

/**
 * The main class of the project, used only to call and test functions from
 * {@link library.LibraryGraphs}.
 */
public class User {
	public static void main(String[] args) {

		LibraryGraphs teste = new LibraryGraphs();
		Graph g = teste.readFileAsAdjacencyList(args[0]);
		double[][] m = g.adjacencyMatrix();
		printMatrix(m);
	}

	public static void printMatrix(double[][] m) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				if (j == m.length - 1) {
					System.out.println(m[i][j]);
				} else {
					System.out.print(m[i][j] + " ");
				}
			}
		}

	}
}