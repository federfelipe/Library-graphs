package library;

public class Tuple {
	public final Integer v1;
	public final Integer v2;
	
	Tuple(Vertice v1, Vertice v2){
		this.v1 = v1.getNumber();
		this.v2 = v2.getNumber();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Tuple){
			if(((Tuple)(o)).v1.equals(this.v1) && ((Tuple)(o)).v2.equals(this.v2))
				return true;
			
		}

		return false;
	}
	
}
