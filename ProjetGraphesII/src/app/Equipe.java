/*
 * Copyright (c) 2020, Jules Nicolas-Thouvenin and Lucas Baussay. All rights reserved.
 *
 */

package app;

/**
 * Objet représentant une équipe.
 */
public class Equipe extends Outils implements Comparable<Equipe>{

	/**
	 *  Indice initial de l'équipe
	 */
	private int indice;
	
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
	 * Liste du nombre de match contre chacune des autres equipes
	 */
	private Integer[] listMatch;
	
	/**
	 * Si l'équipe est éliminée ou non
	 */
	private boolean estElim;
	/**
	 * Construit une instance de la classe Equipe avec les attributs donnés.
	 */
	public Equipe(int indice, String name, int nbWins, int remainMatch, Integer[] listMatch) {
		this.indice = indice;
		this.name = name;
		this.nbWins = nbWins;
		this.remainMatch = remainMatch;
		this.listMatch = listMatch;
		this.estElim = false;
	}

	/**
	 * Retourne le nom de l'équipe
	 * 
	 * @return name le nom de l'équipe
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retourne le nombre de matchs gagnés
	 * 
	 * @return nbWins le nombre de matchs gagnés
	 */
	public int getNbWins() {
		return nbWins;
	}

	/**
	 * Retourne le nombre de matchs restants
	 * 
	 * @return remainMatch le nombre de matchs restants
	 */
	public int getRemainMatch() {
		return remainMatch;
	}

	/**
	 * Retourne les matchs restants contre chacune des équipes
	 * 
	 * @return listMatch la liste des matchs restants contre chacune des équipes
	 */
	public Integer[] getListMatch() {
		return listMatch;
	}
	
	public int compareTo(Equipe compareEquipe) {
		int compareNbWins = ((Equipe) compareEquipe).getNbWins();
		int compareRemainMatch = ((Equipe) compareEquipe).getRemainMatch();
		
		return (compareNbWins + compareRemainMatch) - (this.nbWins + this.remainMatch);
	}

	public int getIndice() {
		return indice;
	}

	public void setEstElim(boolean estElim) {
		this.estElim = estElim;
	}

	public boolean getEstElim() {
		return estElim;
	}
}
