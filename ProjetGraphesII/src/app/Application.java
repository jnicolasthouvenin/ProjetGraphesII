/*
 * Copyright (c) 2020, Jules Nicolas-Thouvenin and Lucas Baussay. All rights reserved.
 *
 */

package app;

import java.io.IOException;
import java.util.Arrays;

/**
 * Objet représentant une instance de l'application implémentée dans le cadre de ce proket.
 */
public class Application extends Outils {

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
		
		Equipe[] equipes = lireFichier( "didact.dat" );
		Graphe balti = ConstructionReseau(2, equipes);
		
		balti.preflotsAvant();
		
		afficherArcs(balti.getA());
		afficherSommets(balti.getS());
		
		balti.afficherGraphe();
		
		int numEquipe = 2;
		System.out.println("estEliminee("+numEquipe+",equipes) = "+TestEliminationEquipe(numEquipe,equipes));
	
		System.out.println(TestEliminationToutes(equipes));
	}
	
	public static boolean TestEliminationEquipe(int indiceEquipe, Equipe[] equipes) throws IOException {
		Graphe grapheEquipe = ConstructionReseau(indiceEquipe, equipes);
		grapheEquipe.preflotsAvant();
		equipes[indiceEquipe-1].setEstElim(!grapheEquipe.sourceSaturee());
		return !grapheEquipe.sourceSaturee();
	}
	
	public static boolean TestEliminationToutes(Equipe[] equipes) throws IOException {
		Equipe[] copiedEquipes = Arrays.copyOf(equipes, equipes.length);
		Arrays.sort(copiedEquipes);
		int iterCopiedEquipes = 0;
		boolean estElim = false;
		
		while (iterCopiedEquipes<equipes.length && !estElim) {
			Equipe equipe = copiedEquipes[iterCopiedEquipes];
		
			estElim = TestEliminationEquipe(equipe.getIndice(), equipes);
			iterCopiedEquipes++;
			
		}
		
		for (int iterLoose = iterCopiedEquipes+1; iterLoose < equipes.length; iterLoose++) {
			equipes[copiedEquipes[iterLoose].getIndice()-1].setEstElim(true);
		}
		
		return estElim;
	}
}

//comit
