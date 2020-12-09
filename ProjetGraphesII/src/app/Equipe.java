/**
 * 
 */
package app;

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

	public int getNbWins() {
		return nbWins;
	}

	public int getRemainMatch() {
		return remainMatch;
	}

	public Integer[] getListMatch() {
		return listMatch;
	}
}
