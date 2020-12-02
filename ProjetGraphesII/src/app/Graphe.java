/*
 * Copyright (c) 2020, Jules Nicolas-Thouvenin and Lucas Baussay. All rights reserved.
 *
 */

package app;

import java.util.ArrayList;
import java.util.LinkedList;

import vendors.Listes;

/**
 * Objet représentant une instance du graphe étudié dans le projet.
 */
public class Graphe implements Listes {
	private ArrayList<Integer> M;
	private ArrayList<Sommet> S;
	private int nbSommets;
	private ArrayList<ArrayList<Integer>> A;
	private LinkedList<Integer> V;
	
	public Graphe() {
		this.M = new ArrayList<Integer>();
		this.S = new ArrayList<Sommet>();
		this.nbSommets = 0;
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
	
	public int preflotsAvant() {
		
		ArrayList<Integer> listeSommets = new ArrayList<Integer>(nbSommets);
		
		
		return 0;
	}
}
