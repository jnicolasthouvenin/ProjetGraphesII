/*
 * Copyright (c) 2020, Jules Nicolas-Thouvenin and Lucas Baussay. All rights reserved.
 *
 */

package app;

/**
 * Objet représentant une instance d'un arc du graphe.
 */
public class Arc {
	/**
	 * Quantité entière de flot résiduel sur l'arc.
	 */
	private int r;
	
	/**
	 * Construit un arc de capacité résiduelle r.
	 * @param r la capacité entière résiduelle.
	 */
	public Arc(int r) {
		this.r = r;
	}
	
	/**
	 * Construit un arc de capacité résiduelle 0.
	 */
	public Arc() {
		this.r = 0;
	}
	
	/**
	 * Retourne la valeur de la capacité résiduelle de l'arc.
	 * @return r l'entier correspondant à la capacité résiduelle.
	 */
	public int getR() {
		return r;
	}
	
	/**
	 * Remplace la valeur de r par une nouvelle valeur donnée. 
	 * @param nouveauR l'entier correspondant à la nouvelle capacité résiduelle.
	 */
	public void setR(int nouveauR) {
		r = nouveauR;
	}
	
	/**
	 * Ajoute à r un delta résiduel.
	 * @param deltaR l'entier à ajouter à r.
	 */
	public void ajouterR(int deltaR) {
		setR(r+deltaR);
	}
}

//comit
