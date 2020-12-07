/*
 * Copyright (c) 2020, Jules Nicolas-Thouvenin and Lucas Baussay. All rights reserved.
 *
 */

package vendors;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import app.Arc;
import app.Sommet;

/**
 * La classe Tools contient toutes les fonctions utilitaires du programme. Chaque classe fille de Tools bénéficie de ces outils.
 */
public class Tools {
	
	////////////////////////// AFFICHAGE //////////////////////////

	/**
	 * Affiche les sommets de la liste de sommets donnés
	 * 
	 * @param S la liste des sommets
	 */
	public static void afficherSommets(Sommet[] S) {
		for (Sommet sommet : S) {
			System.out.println("Sommet("+sommet.getE()+","+sommet.getH()+")");
		}
	}
	
	/**
	 * Affiche les arcs existants et leur capacité résiduelle.
	 * 
	 * @param A la matrice des arcs.
	 */
	public static void afficherArcs(Arc[][] A) {
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
	
	////////////////////////// MANIPULATION DE FICHIERS //////////////////////////
	
	/**
	 * Ecrit la chaine str dans le fichier debug.txt
	 * 
	 * @param str la chaine à écrire
	 * @throws IOException
	 */
	public static void debug(String str) throws IOException {
		File outputFile = new File("/home/jules/Documents/GraphesII/Projet/debug.txt");
		appendToFile(outputFile,str+"\n");
	}
	
	/**
	 * Vide le fichier debug.txt
	 * 
	 * @throws IOException
	 */
	public static void clearDebug() throws IOException {
		File outputFile = new File("/home/jules/Documents/GraphesII/Projet/debug.txt");
		FileWriter fileWriter = new FileWriter(outputFile);
		fileWriter.write("");
		fileWriter.close();
	}
	
	/** 
	 * Ajoute la chaine donnée au fichier donné et va à la ligne.
	 *  
	 * @param       file le fichier
	 * @param		str la chaine
	 * @throws IOException
	 */
	public static void toDebug(File file, String str) throws IOException {
		File outputFile = new File(file.getAbsolutePath()+"\\debug.txt");
		appendToFile(outputFile,str+"\n");
	}
	
	/** 
	 * Ajoute la chaine donnée au fichier donné
	 *  
	 * @param       file le fichier
	 * @param		str la chaine
	 * @throws IOException
	 */
	public static void appendToFile(File file, String str) throws IOException {
		FileWriter fileWriter = new FileWriter(file,true);
		fileWriter.append(str);
		fileWriter.close();
	}
	
	////////////////////////// MANIPULATION DE LISTES //////////////////////////
	
	/** 
	 * Retourne une liste contenant les entiers croissants de l'intervalle [inf,sup]
	 *  
	 * @return      Un ArrayList d'entiers contenant les entiers triés entre inf et sup (inclus).
	 * @param       inf le plus petit entier.
	 * @param		sup le plus grand entier.
	 */
	public static Integer[] suiteEntiersCroissants(int inf,int sup) {
		System.out.println("inf"+inf+","+"sup "+sup);
		Integer[] liste = new Integer[sup-inf+1];
		for(int indice = 0; indice < (sup-inf+1); indice ++) {
			liste[indice] = indice + inf;
		}
		return liste;
	}
}
