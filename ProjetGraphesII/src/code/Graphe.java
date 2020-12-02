package code;

import java.util.ArrayList;
import java.util.LinkedList;

public class Graphe {
	private ArrayList<Integer> M;
	private ArrayList<Sommet> S;
	private ArrayList<ArrayList<Integer>> A;
	private LinkedList<Integer> V;
	
	public Graphe() {
		this.M = new ArrayList<Integer>();
		this.S = new ArrayList<Sommet>();
		this.A = new ArrayList<ArrayList<Integer>>();
		this.V = new LinkedList<Integer>();
	}
	
	public void setM(ArrayList<Integer> nouveauM) {
		M = nouveauM;
	}
	
	public void setS(ArrayList<Sommet> nouveauS) {
		S = nouveauS;
	}
	
	public void setA(ArrayList<ArrayList<Integer>> nouveauA) {
		A = nouveauA;
	}
	
	public void setV(LinkedList<Integer> nouveauV) {
		V = nouveauV;
	}
}
