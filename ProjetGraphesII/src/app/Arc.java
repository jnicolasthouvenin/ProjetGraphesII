/*
 * Copyright (c) 2020, Jules Nicolas-Thouvenin and Lucas Baussay. All rights reserved.
 *
 */

package app;

/**
 * Objet représentant une instance d'un arc du graphe.
 */
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
