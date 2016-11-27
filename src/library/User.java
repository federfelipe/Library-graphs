package library;

import java.util.Scanner;

/**
 * The main class of the project, used only to call and test functions from
 * {@link library.LibraryGraphs}.
 */
public class User {
	public static void main(String[] args) {

		LibraryGraphs lib = new LibraryGraphs();
		Graph g = lib.readFileAsAdjacencyList(args[0]);
		
		
		Scanner scan = new Scanner(System.in);
		System.out.println(" Welcome to library Graphs. What do you want to know? Enter the number of desired option");
		do{
		System.out.println("For:");
		System.out.println(" 1- Represent the graph in a matrix");
		System.out.println("2- Width search");
		System.out.println("3- Deepth search");
		System.out.println("4-Analyse Conected Components of the Graph");
		System.out.println("5-Distance Between Vertices");
		System.out.println("6-Minimum Path");
		System.out.println("7-Minimum Spanning Tree");
		System.out.println("8-Average Distance");
		System.out.println("9-Exit the program");
		int resp = scan.nextInt();
		
		switch (resp) {
		case 1:
			double[][] m = g.adjacencyMatrix();
			printMatrix(m);			
			break;
			
		case 2:
			System.out.println("Which vertice do you want to search? Enter the number");
			int nvert = scan.nextInt();
			Vertice s = new Vertice(nvert);
			lib.BFS(g, s);
			break;
			
		case 3:
			lib.DFS(g);
			break;
			
		case 4:
			try{
			g.analyseComponentesConex();
			break;
			}catch(NullPointerException e){
				System.out.println("You must first search with Deepth Search, then analyse the components");
				break;
			}
			
		case 5:
			
			break;
			
		case 6:
			
			break;
			
		case 7:
			
			break;
			
		case 8:
			
			break;
			
		case 9:
			scan.close();
			System.exit(0);
			
		default:
			System.out.println("Option not available. Enter one of the options below.");
			break;
		}
				
		}while(true);
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
