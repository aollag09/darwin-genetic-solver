package darwin.solveur.crossovers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import darwin.interfaces.ICaracteristique;
import darwin.interfaces.IIndividu;
import darwin.modele.CrossOver;

/**
 * <h2> Cross Over avec un ou plusieurs points de croisement
 * Cette classe permet de créer de nouveaux individus en croisnat deux individus à l'aide de la méthode 
 * point de croisement.
 * 
 * @author Amaury
 *
 */
public class CrossOverPointDeCroisement extends CrossOver{

	protected CrossOverPointDeCroisement(double probabilite) throws Exception {
		super(probabilite);
	}

	@Override
	public List<IIndividu> crossOver(IIndividu individu1, IIndividu individu2) {
		//On initialise la liste à retourner avec les individus inchangés
		ArrayList<IIndividu> retour = 
				new ArrayList<IIndividu>(Arrays.asList(new IIndividu[] {individu1, individu2}));
		//Si le hasard en décide ainsi (et que les individus sont du même type, on effectue le crossOver
		if(this.doCrossOver() && individu1.getType().equals(individu2.getType())){
			int nbTotalCaracteres = individu1.getNombreCaracteristiques();
			IIndividu n1 = individu1.clone();
			IIndividu n2 = individu2.clone();
			
			int pointDeCroisement = (int) (Math.random()*nbTotalCaracteres);
			for(int i = pointDeCroisement - 1; i< nbTotalCaracteres; i++){
				ICaracteristique c1 = n1.getListCaracteristique().get(i).clone();
				ICaracteristique c2 = n2.getListCaracteristique().get(i).clone();
				n1.getListCaracteristique().remove(i);
				n1.getListCaracteristique().add(i, c2);
				n2.getListCaracteristique().remove(i);
				n2.getListCaracteristique().add(i,c1);
			}
			n1.setName(n1.getName()+"-j");
			n2.setName(n2.getName()+"-j");
			retour = new ArrayList<IIndividu>(Arrays.asList(new IIndividu[] {n1, n2}));
		}
		return retour;
	}


}
