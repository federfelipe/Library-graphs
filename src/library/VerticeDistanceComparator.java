package library;

import java.util.Comparator;

public class VerticeDistanceComparator implements Comparator<Vertice> {

	@Override
	public int compare(Vertice v1, Vertice v2) {
		if(v1 == null){
			System.out.println("The first vertice is null");
			return 0;
		}
		if(v2 == null){
			System.out.println("The second vertice is null");
			return 0;
		}
		if(v1.getDistanceToRoot() > v2.getDistanceToRoot())
			return 1;
		else if(v2.getDistanceToRoot() > v1.getDistanceToRoot())
			return -1;
		return 0;
	}

}
