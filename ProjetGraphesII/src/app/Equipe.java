/**
 * 
 */
package app;

import java.io.IOException;

import app.Graphe;
import vendors.Tools;
/**
 * 
 *
 */
public class Equipe extends Tools{

	/**
	 * Le nom de l'équipe
	 */
	private String name;
	
	/**
	 * Le nombre de match déjà gagné
	 */
	private int nbWins;
	
	/**
	 * Le nombre de match qui lui reste à jouer
	 */
	private int remainMatch;
	
	/**
	 * Le nombre de match contre chacune des autres equipes 
	 */
	private Integer[] listMatch;
	
	/**
	 * Constructeur
	 */
	public Equipe(String name, int nbWins, int remainMatch, Integer[] listMatch) {
		this.name = name;
		this.nbWins = nbWins;
		this.remainMatch = remainMatch;
		this.listMatch = listMatch;
	}
	
	/**
	 * 
	 */
	public Graphe ConstructionReseau(int indEquipe, Equipe[] equipes) {
		int n = equipes.length;
		int nbSommet = (n-1)*(n-2)/2 + n +1;
		int winPossible = equipes[indEquipe].nbWins + equipes[indEquipe].remainMatch;
		
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
			
			A[0][iter] = new Arc(equipes[S[iter].getEquipe1()].listMatch[S[iter].getEquipe2()]);
			A[iter][0] = new Arc(0);
			
			V[0][tailleV[0]] = iter;
			tailleV[0]++;
			V[iter][tailleV[iter]] = 0;
			tailleV[iter]++;
			
			A[iter][S[iter].getEquipe1()] = new Arc(Integer.MAX_VALUE);
			A[S[iter].getEquipe1()][iter] = new Arc(0);
			
			V[iter][tailleV[iter]] = S[iter].getEquipe1();
			tailleV[iter]++;
			V[S[iter].getEquipe1()][tailleV[S[iter].getEquipe1()]] = iter;
			tailleV[S[iter].getEquipe1()]++;
			
			A[iter][S[iter].getEquipe2()] = new Arc(Integer.MAX_VALUE);
			A[S[iter].getEquipe2()][iter] = new Arc(0);
			
			V[iter][tailleV[iter]] = S[iter].getEquipe2();
			tailleV[iter]++;
			V[S[iter].getEquipe2()][tailleV[S[iter].getEquipe2()]] = iter;
			tailleV[S[iter].getEquipe2()]++;
		}
		
		// Les arrêtes entre les équipes et le puits ainsi que les voisinages : 
		
		for (int iter = nbSommet-n; iter< nbSommet-1; iter++) {
			A[iter][nbSommet-1] = new Arc(winPossible - equipes[S[iter].getEquipe1()].nbWins);
			A[nbSommet-1][iter] = new Arc(0);
			
			V[iter][tailleV[iter]] = nbSommet-1;
			tailleV[iter]++;
			V[nbSommet-1][tailleV[nbSommet-1]] = iter;
			tailleV[nbSommet-1]++;
		}
		
		return new Graphe(S, A, V);
		
	}
}
