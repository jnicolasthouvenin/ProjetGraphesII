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
	
	/**
	 * Construit un nouveau Sommet non débordant de hauteur nulle.
	 */
	public Sommet() {
		this.e = 0;
		this.h = 0;
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
}
