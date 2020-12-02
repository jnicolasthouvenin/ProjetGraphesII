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
	/**
	 * ArrayList des sommets du graphe.
	 */
	private Sommet[] S;
	/**
	 * ArrayList d'arrayList d'arc correspondant à la matrice des arcs du graphe.
	 */
	private Arc[][] A;
	/**
	 * LinkedList d'entiers correspondant aux voisins de chaque sommet. On considère voisins deux sommets reliés par un arc.
	 */
	private Integer[][] V;
	
	/**
	 * Objet représentant une instance du graphe étudié dans ce projet.
	 */
	public Graphe() {
		this.S = new Sommet[0];
		this.A = new Arc[0][0];
		this.V = new Integer[0][0];
	}
	
	/**
	 * Remplace la valeur de S par la valeur de nouveauS.
	 * @param nouveauS la nouvelle valeur de S.
	 */
	public void setS(Sommet[] nouveauS) {
		S = nouveauS;
	}
	
	/**
	 * Remplace la valeur de A par la valeur de nouveauA.
	 * @param nouveauA la nouvelle valeur de A
	 */
	public void setA(Arc[][] nouveauA) {
		A = nouveauA;
	}
	
	/**
	 * Remplace la valeur de V par la valeur de nouveauV.
	 * @param nouveauV la nouvelle valeur de V
	 */
	public void setV(Integer[][] nouveauV) {
		V = nouveauV;
	}
	
	/**
	 * Retourne un nouveau graphe dans lequel on a effectué un algorithme de préflots avant.
	 * @return nouveau graphe dans lequel on a effectué un algorithme de préflots avant.
	 */
	public Graphe preflotsAvant() {
		
		Integer[][] listeSommets = suiteEntiersCroissants(2,(S.length-1));
		// initialisation
	    for(int voisin : V.get(1)){
	    	pousser(S,1,voisin,A);
	    }
		
		return new Graphe();
	}
	
	/**
	 * Pousse une quantité de flot du sommet u au sommet v
	 */
	public boolean pousser(ArrayList<Sommet> S,int u,int v,ArrayList<ArrayList<Arc>> A) {
		Arc arc = A.get(u).get(v);
		return true;
	}
}
