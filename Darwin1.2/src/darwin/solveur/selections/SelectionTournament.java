package darwin.solveur.selections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import darwin.interfaces.IBeing;
import darwin.interfaces.IPopulation;
import darwin.model.Selection;

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
public class SelectionTournament extends Selection{
		
	/**
	 * La liste des individus � retourner apr�s une selection
	 */
	private ArrayList<IBeing> individusGagnant;
	
	/**
	 * Constructeur de base
	 * @param nbIndividus, le nombre d'individus � selectionner
	 * @throws Exception
	 */
	public SelectionTournament(int nbIndividus) throws Exception {
		super(nbIndividus);
		individusGagnant = new ArrayList<IBeing>();
	}

	@Override
	public List<IBeing> select(IPopulation population) {
		List<IBeing> selectionnes = new ArrayList<IBeing>(population.getBeingsList());
		tournoi(selectionnes, population);
		return individusGagnant;
	}
	
	/**
	 * C'est dans cette m�thode que nous organisons une des �tapes du tournoi
	 * en fonction du nombre d'individus souhait�s � l'arriv�e.
	 */
	private void tournoi(List<IBeing> beings, IPopulation population){
		try{
			/* D�claration de la liste des individus s�lection poue la  manche du tournoi */
			ArrayList<IBeing> individusCourant = new ArrayList<IBeing>();
			if(beings.size() > this.numberBeings * 2 ){
				/* Dans ce cas l� tous les individus se rencontrent deux � deux ! */
				/* On shuffle la liste */
				Collections.shuffle(beings);
				/* On v�rifie que la liste est paire ou on g�re le cas impaire*/
				int size = (beings.size() % 2 == 0) ? (int)(beings.size()) : (int)(beings.size() - 1.0);
				/* On confronte les individus deux � deux de suite dans la liste m�lang�e */
				for(int i = 0; i<size; i+=2){
					IBeing id1 = beings.get(i);
					IBeing id2 = beings.get(i+1);
					IBeing gagnant = population.evaluateBeing(id1)<population.evaluateBeing(id2) ? id1 : id2;
					individusCourant.add(gagnant);
				}
				if(size == beings.size() - 1)/* Cas ou le liste est impaire on ajoute le dernier �l�ment*/
					individusCourant.add(beings.get(beings.size() - 1));
				/* Appel recursif de la m�thode */
				tournoi(individusCourant,population);
			}else{
				/* C'est l'�tape finale du tournoi !*/
				/* Le but ici est de se faire s'affronter suffisament d'individus pour optenir 
				 * le nombre souhait� d'individus finalement
				 */
				int nombreDAffrontements = (beings.size() - this.numberBeings);
				/* On shuffle la liste */
				Collections.shuffle(beings);
				for(int i = 0; i<2*nombreDAffrontements - 1; i+=2){
					/* On se fait affronter le bon nombre d'individus */
					IBeing id1 = beings.get(i);
					IBeing id2 = beings.get(i+1);
					IBeing gagnant = population.evaluateBeing(id1)<population.evaluateBeing(id2) ? id1 : id2;
					individusCourant.add(gagnant);
				}
				for(int i = 2*nombreDAffrontements; i < beings.size(); i++){
					/* Tous les autres individus n'ont pas besoin d'�tre affronter, on les rajoute directement
					 * � la liste des individus s�l�ctionn�s */
					individusCourant.add(beings.get(i));
				}
				/* On met a jour la liste des selection a retourner */
				individusGagnant.clear();
				individusGagnant.addAll(individusCourant);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
