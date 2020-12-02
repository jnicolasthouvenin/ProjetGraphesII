/*
 * Copyright (c) 2020, Jules Nicolas-Thouvenin and Lucas Baussay. All rights reserved.
 *
 */

package app;

/**
 * Objet représentant une instance d'un sommet du graphe.
 */
public class Sommet {
	private int e;
	private int h;
	
	public Sommet() {
		this.e = 0;
		this.h = 0;
	}
	
	public int getE() {
		return e;
	}
	
	public int getH() {
		return h;
	}
}
