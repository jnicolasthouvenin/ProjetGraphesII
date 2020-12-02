package vendors;

import java.util.ArrayList;

public interface Listes {
	// retourne une liste contenant les entiers croissants de l'intervale [inf,sup]
	default ArrayList<Integer> suiteEntiersCroissants(int inf,int sup) {
		ArrayList<Integer> liste = new ArrayList<Integer>(sup-inf+1);
		for(int indice = 0; indice <= (inf-sup+1); indice ++) {
			liste.set(indice, indice + inf);
		}
		return liste;
	}
}
