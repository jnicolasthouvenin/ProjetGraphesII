package app;

public class Arc {
	private int r;
	
	public Arc(int r) {
		this.r = r;
	}
	
	public int getR() {
		return r;
	}
	
	public void setR(int nouveauR) {
		r = nouveauR;
	}
	
	public void ajouterR(int ajoutR) {
		setR(r+ajoutR);
	}
}
