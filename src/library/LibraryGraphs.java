package library;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LibraryGraphs {

	public LibraryGraphs(){
	}
	
	public void BufferedReader(String filePath) {
	    BufferedReader br = null;
		try {
				String sCurrentLine;
				br = new BufferedReader(new FileReader(filePath));
				while ((sCurrentLine = br.readLine()) != null) {
					System.out.println(sCurrentLine);
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null)br.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
} 