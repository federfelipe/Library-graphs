package library;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibraryGraphs {

	public LibraryGraphs(){
	}
	
	public List<Vertice> BufferedReader(String filePath) {
	    BufferedReader br = null;
		String sCurrentLine;
		String[] line;
		List<Vertice> graph = new ArrayList<Vertice>();
		int size;
		
		try {
				br = new BufferedReader(new FileReader(filePath));
				try{
					size = Integer.parseInt(br.readLine());
				} catch(NumberFormatException e){
					System.out.println("The file " + filePath + "doesn't match with the library standard");
					return null;
				}
				
				for(int i = 0; i < size; i++){
					graph.add(new Vertice(i));
				}
				
				
				while ((sCurrentLine = br.readLine()) != null) {
					line = sCurrentLine.split(" ");
					graph.get(Integer.parseInt(line[0])).addAdj(graph.get(Integer.parseInt(line[1])));
					graph.get(Integer.parseInt(line[1])).addAdj(graph.get(Integer.parseInt(line[0])));
					if(line.length == 3){
						//Create a new edge and add it's weight.
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null)br.close();
				} catch (IOException ex) {
					ex.printStackTrace();
					return null;
				}
			}
			return graph;
		}
} 