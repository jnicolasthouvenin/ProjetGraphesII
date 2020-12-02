/*
 * Copyright (c) 2020, Jules Nicolas-Thouvenin and Lucas Baussay. All rights reserved.
 *
 */

package vendors;

import java.util.ArrayList;

/**
 * L'interface Listes offre plusieurs méthodes default permettant de travailler facilement avec des listes.
 *
 * @see ArrayList
 */
public interface Listes {
	/** 
	 * Retourne une liste contenant les entiers croissants de l'intervalle [inf,sup]
	 *  
	 * @return      Un ArrayList d'entiers contenant les entiers triés entre inf et sup (inclus).
	 * @param       inf le plus petit entier.
	 * @param		sup le plus grand entier.
	 */
	default ArrayList<Integer> suiteEntiersCroissants(int inf,int sup) {
		ArrayList<Integer> liste = new ArrayList<Integer>(sup-inf+1);
		for(int indice = 0; indice <= (inf-sup+1); indice ++) {
			liste.set(indice, indice + inf);
		}
		return liste;
	}
}
