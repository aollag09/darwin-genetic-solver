package darwin.solveur.selections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import darwin.interfaces.IIndividu;
import darwin.interfaces.IPopulation;
import darwin.modele.Selection;

/**
 * <h2>La classe Selection par tournoi</h2>
 * 
 * Cette selection organise des rencontres entre pairs d'individus, et la gagnant est celui
 * qui a une meilleur �valuation que son adversaire.
 * Cette m�thode de selection permet de garder des individus parfois avec des �valuations 
 * m�diocre tout en assurant une bonne convergence
 * 
 * On r�sultat du tournoi, on obtient donc un nombre nbIndivdu de vainceur apr�s avoir organis� 
 * une s�rie de rencontre sur plusieurs �tapes (ex: 1/16�me, 1/8�me, quart de final...)
 * 
 * @author Amaury
 *
 */
public class SelectionTournoi extends Selection{

	public SelectionTournoi(int nbIndividus) throws Exception {
		super(nbIndividus);
	}

	@Override
	public List<IIndividu> selectionner(IPopulation population) {
		List<IIndividu> selectionnes = new ArrayList<IIndividu>(population.getListIndividus());
		tournoi(selectionnes, population);
		return selectionnes;
	}
	
	/**
	 * C'est dans cette m�thode que nous organisons une des �tapes du tournoi
	 * en fonction du nombre d'individus souhait�s � l'arriv�e.
	 */
	private void tournoi(List<IIndividu> individus, IPopulation population){
		/* D�claration de la liste des individus s�lection poue la  manche du tournoi */
		ArrayList<IIndividu> individusCourant = new ArrayList<IIndividu>();
		if(individus.size() > this.nbIndivus * 2 ){
			/* Dans ce cas l� tous les individus se rencontrent deux � deux ! */
			/* On shuffle la liste */
			Collections.shuffle(individus);
			/* On v�rifie que la liste est paire ou on g�re le cas impaire*/
			int size = (individus.size() % 2 == 0) ? (int)(individus.size()) : (int)(individus.size() - 1.0);
			/* On confronte les individus deux � deux de suite dans la liste m�lang�e */
			for(int i = 0; i<size; i+=2){
				IIndividu id1 = individus.get(i);
				IIndividu id2 = individus.get(i+1);
				IIndividu gagnant = population.evaluerIndividu(id1)>population.evaluerIndividu(id2) ? id1 : id2;
				individusCourant.add(gagnant);
			}
			if(size == individus.size() - 1)/* Cas ou le liste est impaire on ajoute le dernier �l�ment*/
				individusCourant.add(individus.get(individus.size() - 1));
			/* Appel recursif de la m�thode */
			tournoi(individusCourant,population);
		}else{
			/* C'est l'�tape finale du tournoi !*/
			/* Le but ici est de se faire s'affronter suffisament d'individus pour optenir 
			 * le nombre souhait� d'individus finalement
			 */
			int nombreDAffrontements = (individus.size() - this.nbIndivus);
			/* On shuffle la liste */
			Collections.shuffle(individus);
			for(int i = 0; i<2*nombreDAffrontements - 1; i+=2){
				/* On se fait affronter le bon nombre d'individus */
				IIndividu id1 = individus.get(i);
				IIndividu id2 = individus.get(i+1);
				IIndividu gagnant = population.evaluerIndividu(id1)>population.evaluerIndividu(id2) ? id1 : id2;
				individusCourant.add(gagnant);
			}
			for(int i = 2*nombreDAffrontements; i < individus.size(); i++){
				/* Tous les autres individus n'ont pas besoin d'�tre affronter, on les rajoute directement
				 * � la liste des individus s�l�ctionn�s */
				individusCourant.add(individus.get(i));
			}
		}
	}
	
}
