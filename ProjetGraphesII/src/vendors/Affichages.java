/*
 * Copyright (c) 2020, Jules Nicolas-Thouvenin and Lucas Baussay. All rights reserved.
 *
 */

package vendors;

import java.util.ArrayList;

import app.Arc;

/**
 * L'interface Affichages offre plusieurs méthodes default permettant d'afficher facilement des structures complexes.
 *
 * @see ArrayList
 */
public interface Affichages {
	
	/**
	 * Affiche les arcs existants et leur capacité résiduelle.
	 * 
	 * @param A la matrice des arcs.
	 */
	static void afficherArcs(Arc[][] A) {
		int l = A.length;
		for (int ligne = 0; ligne < l; ligne ++) {
			for (int colonne = 0; colonne < l; colonne ++) {
				Arc arc = A[ligne][colonne];
				if (arc != null) {
					System.out.println("("+ligne+","+colonne+") = "+arc.getR());
				}
			}
		}
	}
	
}
