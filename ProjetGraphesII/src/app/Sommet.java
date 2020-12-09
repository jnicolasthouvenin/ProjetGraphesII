/*
 * Copyright (c) 2020, Jules Nicolas-Thouvenin and Lucas Baussay. All rights reserved.
 *
 */

package app;

/**
 * Objet représentant une instance d'un sommet du graphe.
 */
public class Sommet {
	/**
	 * Entier correspondant au nombre d'unités de flot débordant du sommet.
	 */
	private int e;
	/**
	 * Entier correspondant à la hauteur du sommet.
	 */
	private int h;
	
	private int equipe1;
	private int equipe2;
	
	/**
	 * Construit un nouveau Sommet non débordant de hauteur nulle.
	 */
	public Sommet() {
		this.e = 0;
		this.h = 0;
		this.equipe1 = -1;
		this.equipe2 = -1;
	}
	
	/**
	 * Construit un nouveau Sommet avec les attributs donnés.
	 */
	public Sommet(int e,int h) {
		this.e = e;
		this.h = h;
		this.equipe1 = -1;
		this.equipe2 = -1;
	}
	
	public Sommet(int e, int h, int equipe) {
		this.e = e;
		this.h = h;
		this.equipe1 = equipe;
		this.equipe2 = -1;
	}
	
	public Sommet(int e, int h, int equipe1, int equipe2) {
		this.e = e;
		this.h = h;
		this.equipe1 = equipe1;
		this.equipe2 = equipe2;
	}
	
	/**
	 * Retourne la quantité entière de flot débordante.
	 * @return e la quantité entière de flot débordante.
	 */
	public int getE() {
		return e;
	}
	
	/**
	 * Retourne l'entier correspondant à la hauteur du sommet.
	 * @return h l'entier correspondant à la hauteur du sommet.
	 */
	public int getH() {
		return h;
	}
	
	public int getEquipe1() {
		return equipe1;
	}
	
	public int getEquipe2() {
		return equipe2;
	}
	
	/**
	 * Remplace la valeur de h par une nouvelle valeur donnée. 
	 * @param nouveauH l'entier correspondant à la nouvelle hauteur.
	 */
	public void setH(int nouveauH) {
		h = nouveauH;
	}
	
	/**
	 * Ajoute à h un delta.
	 * @param deltaH l'entier à ajouter à h.
	 */
	public void ajouterH(int deltaH) {
		setH(h+deltaH);
	}
	
	/**
	 * Remplace la valeur de e par une nouvelle valeur donnée. 
	 * @param nouveauE l'entier correspondant à la nouvelle quantité débordante.
	 */
	public void setE(int nouveauE) {
		e = nouveauE;
	}
	
	/**
	 * Ajoute à e un delta.
	 * @param deltaE l'entier à ajouter à e.
	 */
	public void ajouterE(int deltaE) {
		setE(e+deltaE);
	}
}

//comit
