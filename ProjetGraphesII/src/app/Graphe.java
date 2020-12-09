/*
 * Copyright (c) 2020, Jules Nicolas-Thouvenin and Lucas Baussay. All rights reserved.
 *
 */

package app;

import java.io.IOException;

/**
 * Objet représentant une instance du graphe étudié dans le projet.
 */
public class Graphe extends Outils {
	/**
	 * ArrayList des sommets du graphe.
	 */
	private Sommet[] S;
	/**
	 * ArrayList d'arrayList d'arc correspondant à la matrice des arcs du graphe.
	 */
	private Arc[][] A;
	/**
	 * LinkedList d'entiers correspondant aux voisins de chaque sommet. On considère voisins deux sommets reliés par un arc.
	 */
	private Integer[][] V;
	
	/**
	 * Objet représentant une instance du graphe étudié dans ce projet.
	 */
	public Graphe() {
		this.S = new Sommet[0];
		this.A = new Arc[0][0];
		this.V = new Integer[0][0];
	}
	
	/**
	 * Objet représentant une instance du graphe étudié dans ce projet et possédant les attributs donnés.
	 */
	public Graphe(Sommet[] S, Arc[][] A, Integer[][] V) {
		this.S = S;
		this.A = A;
		this.V = V;
	}
	
	/**
	 * Remplace la valeur de S par la valeur de nouveauS.
	 * @param nouveauS la nouvelle valeur de S.
	 */
	public void setS(Sommet[] nouveauS) {
		S = nouveauS;
	}
	
	/**
	 * Remplace la valeur de A par la valeur de nouveauA.
	 * @param nouveauA la nouvelle valeur de A
	 */
	public void setA(Arc[][] nouveauA) {
		A = nouveauA;
	}
	
	/**
	 * Remplace la valeur de V par la valeur de nouveauV.
	 * @param nouveauV la nouvelle valeur de V
	 */
	public void setV(Integer[][] nouveauV) {
		V = nouveauV;
	}

	/**
	 * Retourne la matrice des arcs
	 * @return A la matrice des arcs
	 */
	public Arc[][] getA() {
		return A;
	}

	/**
	 * Retourne la liste des sommets
	 * @return S la liste des sommets
	 */
	public Sommet[] getS() {
		return S;
	}
	
	/**
	 * Retourne un nouveau graphe dans lequel on a effectué un algorithme de préflots avant.
	 * @return nouveau graphe dans lequel on a effectué un algorithme de préflots avant.
	 * @throws IOException 
	 */
	public Graphe preflotsAvant() throws IOException {
		int nbSommets = getS().length;
		//clearDebug();
		//debug("nbSommets = "+nbSommets);
		 
		Integer[] listeSommets = suiteEntiersCroissants(1,(nbSommets-1));
		// initialisation
	    for(int voisin : V[0]){
	    	pousser(getS(),0,voisin,getA());
	    }
	    
	    int indiceListe = 0;
	    int debug = 1;
	    while(indiceListe < nbSommets-2 && debug <= 100) {
	    	//debug("[indiceList = "+indiceListe);
	    	debug ++;
	    	int sommet = listeSommets[indiceListe];
	        boolean hauteurModifiee = decharger(getS(),sommet,V,getA());
	        if(hauteurModifiee) {
	        	listeSommets[indiceListe] = listeSommets[0];
	            listeSommets[0] = sommet;
	            indiceListe = 0;
	        }
	        else {
	        	indiceListe ++;
	        }
	    }
		
		return new Graphe();
	}

	/**
	 * Retourne si oui ou non la décharge du sommet u a eu un quelquonque effet sur le réseau.
	 * @param S la liste des sommets
	 * @param sommet l'indice du sommet à décharger
	 * @param V la matrice des voisins
	 * @param A la matrice des arcs
	 * @return
	 * @throws IOException 
	 */
	public boolean decharger(Sommet[] S,int sommet,Integer[][] V,Arc[][] A) throws IOException {
		//debug("[décharger] "+sommet);
	    int h = S[sommet].getH();
	    int debug = 1;
	    boolean pousseeReussie = false;
	    while (S[sommet].getE() > 0 && debug <= 100) {
	        debug ++;
	        for (int voisin : V[sommet]) {
	            pousseeReussie = pousser(S,sommet,voisin,A);
	        }
	        elever(S,sommet,V,A);
	    }
	    return h < S[sommet].getH() || pousseeReussie;
	}
	
	/**
	 * Retourne si oui ou non la poussée a été possible et donc effectuée
	 * @param S la liste des sommets
	 * @param u l'indice du sommet à pousser
	 * @param v l'indice du sommet vers qui pousser
	 * @param A la matrice des arcs
	 * @return si oui ou non la poussée a été possible et donc effectuée
	 * @throws IOException 
	 */
	public boolean pousser(Sommet[] S,int u,int v,Arc[][] A) throws IOException {
		Arc arc = A[u][v];
		
		if(S[u].getE() > 0 && arc.getR() > 0 && S[u].getH() > S[v].getH()) {
			//debug("[pousser "+u+" vers "+v+" ]");
			int flot = Math.min(S[u].getE(), arc.getR());
	        A[u][v].ajouterR(-flot);
	        A[v][u].ajouterR(flot);
	        S[u].ajouterE(-flot);
	        S[v].ajouterE(flot);
	        return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Retourne si oui ou non l'élévation a été possible et donc effectuée
	 * @param S la liste des sommets
	 * @param indiceSommet l'indice du sommet à élever
	 * @param V la matrice des voisins
	 * @param A la matrice des arcs
	 * @return si oui ou non l'élévation a été possible et donc effectuée
	 * @throws IOException 
	 */
	public boolean elever(Sommet[] S,int indiceSommet,Integer[][] V,Arc[][] A) throws IOException {
	    // verification que le sommet déborde
	    if (S[indiceSommet].getE() <= 0) {
	        return false;
	    }
	
	    // verification que les voisins sont tous trop eleves (on en profite pour calculer la hauteur minimum)
	    int minH = Integer.MAX_VALUE;
	    for (int indiceVoisin : V[indiceSommet]) { // parcours des sommets voisins à indiceSommet) {
	        int hauteurVoisin = S[indiceVoisin].getH();
	        if (hauteurVoisin < S[indiceSommet].getH() && A[indiceSommet][indiceVoisin].getR() > 0) {
	            return false;
	        }
	        if (hauteurVoisin < minH) { // on a trouvé une nouvelle hauteur minimum
	            minH = hauteurVoisin; // mise à jour de hauteur minimum
	        }
		}
	
	    // modification de la hauteur
	    if (S[indiceSommet].getH() < 1 + minH) {
	        S[indiceSommet].setH(1+minH);
	    } else {
	        S[indiceSommet].ajouterH(1);
	    }
	
	    return true;
	}
	
	/**
	 * Affiche en console le contenu du graphe (utile pour vérifier la bonne exécution du préflots)
	 */
	public void afficherGraphe() {
		System.out.println("Flot dans le graphe :");
		for(int ligne = 0; ligne < A.length; ligne ++) {
			for (int colonne = ligne+1; colonne < A.length; colonne ++) {
				Arc arcEndroit = A[ligne][colonne];
				Arc arcEnvers = A[colonne][ligne];
				if (arcEndroit != null) {
					String capa = Integer.toString(arcEndroit.getR() + arcEnvers.getR());
					if (arcEndroit.getR() + arcEnvers.getR() > 10000000) {
						capa = "Inf";
					}
					System.out.println("("+ligne+","+colonne+") - "+arcEnvers.getR()+"/"+capa);
				}
			}
		}
	}
	
	/**
	 * Affiche en console le contenu du graphe (utile pour vérifier la bonne exécution du préflots)
	 */
	public boolean sourceSaturee() {
		int indiceDernierSommet = 1;
		Sommet sommet = S[indiceDernierSommet];
		while (sommet.getEquipe2() != -1) {
			indiceDernierSommet ++;
			sommet = S[indiceDernierSommet];
		}
		indiceDernierSommet --;
		int indiceCourant = 1;
		boolean sourceSaturee = true;
		while (sourceSaturee && indiceCourant <= indiceDernierSommet) {
			if (A[0][indiceCourant].getR() != 0) {
				sourceSaturee = false;
			}
			indiceCourant ++;
		}
		return sourceSaturee;
	}
}

//comit
