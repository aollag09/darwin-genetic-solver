package darwin.solveur.crossovers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import darwin.interfaces.ICaracteristique;
import darwin.interfaces.IIndividu;
import darwin.modele.CrossOver;

public class CrossOverChemin extends CrossOver{

	public CrossOverChemin(double probablite) throws Exception{
		super(probablite);
	}
	
	@Override
	public List<IIndividu> crossOver(IIndividu individu1, IIndividu individu2) {
		//On initialise la liste à retourner avec les individus inchangés
		ArrayList<IIndividu> retour = 
				new ArrayList<IIndividu>(Arrays.asList(new IIndividu[] {individu1, individu2}));
		//Si le hasard en décide ainsi (et que les individus sont du même type, on effectue le crossOver
		if(this.doCrossOver() && individu1.getType().equals(individu2.getType())){
			int nbVilles = individu1.getNombreCaracteristiques();
			if(nbVilles >= 2){
				int ptCoupage = (int) ((nbVilles-1)*Math.random());
				IIndividu n1 = individu1.clone();
				IIndividu n2 = individu2.clone();
				List<ICaracteristique> newChemin1 = new ArrayList<ICaracteristique>();
				List<ICaracteristique> newChemin2 = new ArrayList<ICaracteristique>();
				for(int i = 0; i<ptCoupage; i++){
					newChemin1.add(individu1.getCaracteristique(i));
					newChemin2.add(individu2.getCaracteristique(i));
				}
				for(int j = 0; j<nbVilles; j++){
					if(!newChemin1.contains(individu2.getCaracteristique(j))){
						newChemin1.add(individu2.getCaracteristique(j));
					}
					if(!newChemin2.contains(individu1.getCaracteristique(j))){
						newChemin2.add(individu1.getCaracteristique(j));
					}
				}
				n1.setListCaracteristiques(newChemin1);
				n2.setListCaracteristiques(newChemin2);
				n1.setName(n1.getName()+"-j");
				n2.setName(n2.getName()+"-j");
				retour = new ArrayList<IIndividu>(Arrays.asList(new IIndividu[] {n1, n2}));
			}
		}
		return retour;
	}

}
