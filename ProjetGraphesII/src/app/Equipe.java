/*
 * Copyright (c) 2020, Jules Nicolas-Thouvenin and Lucas Baussay. All rights reserved.
 *
 */

package app;

/**
 * Objet représentant une équipe.
 */
public class Equipe extends Outils {

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
	 * Construit une instance de la classe Equipe avec les attributs donnés.
	 */
	public Equipe(String name, int nbWins, int remainMatch, Integer[] listMatch) {
		this.name = name;
		this.nbWins = nbWins;
		this.remainMatch = remainMatch;
		this.listMatch = listMatch;
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
}
