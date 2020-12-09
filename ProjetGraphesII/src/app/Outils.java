/*
 * Copyright (c) 2020, Jules Nicolas-Thouvenin and Lucas Baussay. All rights reserved.
 *
 */

package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Objet offrant à ses classes filles des méthodes utilitaires pour simplifier le code.
 */
public class Outils {
	
	////////////////////////// PARSING DES DONNEES //////////////////////////
	
	/**
	* Lit le fichier donné et renvoie une liste d'équipe
	* 
	*  @return		Un ArrayList d'Equipe
	*  @param		fileName le nom du fichier
	*  @throws		NumbetFormatException
	*  @throws		IOException
	*/	
	public static Equipe[] lireFichier(String fileName) throws NumberFormatException, IOException{
		File file = new File("data/"+fileName);
		BufferedReader b = new BufferedReader(new FileReader(file));
		
		int nbEquipe = Integer.parseInt( b.readLine() );
		
		Equipe[] equipes = new Equipe[nbEquipe];
		
		for (int iterEquipe = 0; iterEquipe < nbEquipe; iterEquipe++) {
			String readLine = b.readLine();
			String[] stringEquipe = readLine.split(" ");
			
			int ind = Integer.parseInt(stringEquipe[0]);
			String name = stringEquipe[1];
			int nbWins = Integer.parseInt(stringEquipe[2]);
			int remain = Integer.parseInt(stringEquipe[3]);
			
			Integer[] listMatch = new Integer[nbEquipe];
			for (int iter = 0; iter < nbEquipe; iter++) {
				listMatch[iter] = Integer.parseInt(stringEquipe[iter+4]);
			}
			equipes[ind-1] = new Equipe(name, nbWins, remain, listMatch);
		}
		b.close();
		return equipes;
	}
	
	/**
	 * Retourne le graphe associé au vecteur d'equipes donné
	 * @param indEquipe
	 * @param equipes
	 * @return
	 */
	public static Graphe ConstructionReseau(int indEquipe, Equipe[] equipes) {
		int n = equipes.length;
		int nbSommet = (n-1)*(n-2)/2 + n +1;
		int winPossible = equipes[indEquipe-1].getNbWins() + equipes[indEquipe-1].getRemainMatch();
		
		Sommet[] S = new Sommet[nbSommet];
		Arc[][] A = new Arc[nbSommet][nbSommet];
		Integer[][] V = new Integer[nbSommet][];
		Integer[] tailleV = new Integer[nbSommet];
		int iterSommet = 0;
		
		// Creation des sommets et des longueure de Voisinage, d'abord la source
		
		S[0] = new Sommet(Integer.MAX_VALUE, nbSommet);
		V[0] = new Integer[nbSommet-n-1];
		tailleV[0] = 0;
		// Puis les sommets des matchs
		for (int iter1=1; iter1 < n; iter1++) {
			if (iter1 != indEquipe) {
				for (int iter2=iter1+1; iter2 <= n; iter2++) {
					if (iter2 != indEquipe) {
						iterSommet++;
						S[iterSommet] = new Sommet(0, 0, iter1, iter2);
						V[iterSommet] = new Integer[3];
						tailleV[iterSommet] = 0;
					}
				}
			}
		}
		// Puis les sommets des équipes
		for (int iter=1; iter<=n; iter++) {
			if (iter != indEquipe) {
				iterSommet++;
				S[iterSommet] = new Sommet(0, 0, iter);
				V[iterSommet] = new Integer[n-1];
				tailleV[iterSommet] = 0;
			}
		}
		// Enfin le puits
		iterSommet++;
		S[iterSommet] = new Sommet();
		V[iterSommet] = new Integer[n-1];
		tailleV[iterSommet] = 0;
		
		// Maintenant on créer les les arrêtes et on remplit les voisinages entre les matchs et les Equipes : 
		
		for (int iter = 1; iter<=nbSommet-n-1; iter++) {
			
			int indEq1 = S[iter].getEquipe1() + nbSommet-n-1;
			if (S[iter].getEquipe1() > indEquipe) {
				indEq1 -= 1; 
			}
			int indEq2 = S[iter].getEquipe2() + nbSommet-n-1;
			if (S[iter].getEquipe2() > indEquipe) {
				indEq2--; 
			}
			
			A[0][iter] = new Arc(equipes[S[iter].getEquipe1()-1].getListMatch()[S[iter].getEquipe2()-1]);
			A[iter][0] = new Arc(0);
			
			V[0][tailleV[0]] = iter;
			tailleV[0]++;
			V[iter][tailleV[iter]] = 0;
			tailleV[iter]++;
			
			A[iter][indEq1] = new Arc(Integer.MAX_VALUE);
			A[indEq1][iter] = new Arc(0);
			
			V[iter][tailleV[iter]] = indEq1;
			tailleV[iter]++;
			V[indEq1][tailleV[indEq1]] = iter;
			tailleV[indEq1]++;
			
			A[iter][indEq2] = new Arc(Integer.MAX_VALUE);
			A[indEq2][iter] = new Arc(0);
			
			V[iter][tailleV[iter]] = indEq2;
			tailleV[iter]++;
			V[indEq2][tailleV[indEq2]] = iter;
			tailleV[indEq2]++;
		}
		
		// Les arrêtes entre les équipes et le puits ainsi que les voisinages : 
		
		for (int iter = nbSommet-n; iter< nbSommet-1; iter++) {
			A[iter][nbSommet-1] = new Arc(winPossible - equipes[S[iter].getEquipe1()-1].getNbWins());
			A[nbSommet-1][iter] = new Arc(0);
			
			V[iter][tailleV[iter]] = nbSommet-1;
			tailleV[iter]++;
			V[nbSommet-1][tailleV[nbSommet-1]] = iter;
			tailleV[nbSommet-1]++;
		}
		
		return new Graphe(S, A, V);
	}
	
	//////////////////////////AFFICHAGE //////////////////////////
	
	/**
	* Affiche les sommets de la liste de sommets donnés
	* @param S la liste des sommets
	*/
	public static void afficherSommets(Sommet[] S) {
		for (Sommet sommet : S) {
			System.out.println("Sommet("+sommet.getE()+","+sommet.getH()+")");
		}
	}

	/**
	* Affiche les arcs existants et leur capacité résiduelle.
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
	* @param str la chaine à écrire
	* @throws IOException
	*/
	public static void debug(String str) throws IOException {
		File outputFile = new File("/home/jules/Documents/GraphesII/Projet/debug.txt");
		appendToFile(outputFile,str+"\n");
	}

	/**
	* Vide le fichier debug.txt
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
