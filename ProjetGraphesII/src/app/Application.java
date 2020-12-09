/*
 * Copyright (c) 2020, Jules Nicolas-Thouvenin and Lucas Baussay. All rights reserved.
 *
 */

package app;

import java.io.IOException;

import vendors.Tools;

/**
 * Objet représentant une instance de l'application implémentée dans le cadre de ce proket.
 */
public class Application extends Tools {

	/**
	 * Fonction principale.
	 * 
	 * @param		args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("Hello World !");
		
		Arc[][] A = new Arc[8][8];
		System.out.println(A[1][1]);
		A[0][1] = new Arc(6);
	    A[1][0] = new Arc(0);

	    A[0][2] = new Arc(1);
	    A[2][0] = new Arc(0);

	    A[0][3] = new Arc(1);
	    A[3][0] = new Arc(0);

	    A[1][4] = new Arc(Integer.MAX_VALUE);
	    A[4][1] = new Arc(0);

	    A[1][5] = new Arc(Integer.MAX_VALUE);
	    A[5][1] = new Arc(0);

	    A[2][4] = new Arc(Integer.MAX_VALUE);
	    A[4][2] = new Arc(0);

	    A[2][6] = new Arc(Integer.MAX_VALUE);
	    A[6][2] = new Arc(0);

	    A[3][5] = new Arc(Integer.MAX_VALUE);
	    A[5][3] = new Arc(0);

	    A[3][6] = new Arc(Integer.MAX_VALUE);
	    A[6][3] = new Arc(0);

	    A[4][7] = new Arc(0);
	    A[7][4] = new Arc(0);

	    A[5][7] = new Arc(5);
	    A[7][5] = new Arc(0);

	    A[6][7] = new Arc(7);
	    A[7][6] = new Arc(0);
		Sommet[] S = {new Sommet(1000,8),new Sommet(),new Sommet(),new Sommet(),new Sommet(),new Sommet(),new Sommet(),new Sommet()};
		Integer[][] V = {{1,2,3},{0,4,5},{0,4,6},{0,5,6},{1,2,7},{1,3,7},{2,3,7},{4,5,6}};
		Graphe G = new Graphe(S,A,V);
		
		G.preflotsAvant();
		
		afficherArcs(A);
		afficherSommets(S);
		
		System.out.println("Fin");
		
		Equipe[] equipes = lireFichierBis( "didact.dat" );
		Graphe balti = ConstructionReseau(2, equipes);
		
		balti.preflotsAvant();
		
		afficherArcs(balti.getA());
		afficherSommets(balti.getS());
	}
	
	/**
	 * 
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
		
}

//comit
