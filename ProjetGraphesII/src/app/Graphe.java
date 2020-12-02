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
	 * ArrayList d'arrayList d'entiers correspondant à la matrice d'adjacence du graphe.
	 */
	private ArrayList<ArrayList<Integer>> M;
	/**
	 * ArrayList des sommets du graphe.
	 */
	private ArrayList<Sommet> S;
	/**
	 * ArrayList d'arrayList d'arc correspondant à la matrice des arcs du graphe.
	 */
	private ArrayList<ArrayList<Arc>> A;
	/**
	 * LinkedList d'entiers correspondant aux voisins de chaque sommet. On considère voisins deux sommets reliés par un arc.
	 */
	private LinkedList<Integer> V;
	
	/**
	 * Objet représentant une instance du graphe étudié dans ce projet.
	 */
	public Graphe() {
		this.M = new ArrayList<ArrayList<Integer>>();
		this.S = new ArrayList<Sommet>();
		this.A = new ArrayList<ArrayList<Arc>>();
		this.V = new LinkedList<Integer>();
	}
	
	/**
	 * Remplace la valeur de M par la valeur de nouveauM.
	 * @param nouveauM la nouvelle valeur de M.
	 */
	public void setM(ArrayList<ArrayList<Integer>> nouveauM) {
		M = nouveauM;
	}
	
	/**
	 * Remplace la valeur de S par la valeur de nouveauS.
	 * @param nouveauS la nouvelle valeur de S.
	 */
	public void setS(ArrayList<Sommet> nouveauS) {
		S = nouveauS;
	}
	
	/**
	 * Remplace la valeur de A par la valeur de nouveauA.
	 * @param nouveauA la nouvelle valeur de A
	 */
	public void setA(ArrayList<ArrayList<Arc>> nouveauA) {
		A = nouveauA;
	}
	
	/**
	 * Remplace la valeur de V par la valeur de nouveauV.
	 * @param nouveauV la nouvelle valeur de V
	 */
	public void setV(LinkedList<Integer> nouveauV) {
		V = nouveauV;
	}
	
	/**
	 * Retourne un nouveau graphe dans lequel on a effectué un algorithme de préflots avant.
	 * @return nouveau graphe dans lequel on a effectué un algorithme de préflots avant.
	 */
	public Graphe preflotsAvant() {
		
		ArrayList<Integer> listeSommets = new ArrayList<Integer>(S.size());
		
		
		return new Graphe();
	}
}
