package darwin.solveur.crossovers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import darwin.interfaces.ICaracteristique;
import darwin.interfaces.IIndividu;
import darwin.modele.CrossOver;

/**
 * Permet de réaliser un crossOver simple : avec une probabilité p, on interchange n
 * caractères pris au hasard entre les individus concernés.
 * @author Dim
 *
 */
public class CrossOverSimple extends CrossOver{
	
	
	private static final long serialVersionUID = 1649476761942148889L;
	
	
	// VARIABLES D'INSTANCE
	/**
	 * Le nombre de caractères à interchanger
	 */
	protected int nbCaracteres;
	
	/**
	 * 
	 * @param n nombre de caractères à interchanger
	 * @param p probabilité de crossOver
	 * @throws Exception
	 */
	public CrossOverSimple(int n, double p) throws Exception{
		super(p);
		this.nbCaracteres = n;
	}
	
	@Override
	public List<IIndividu> crossOver(IIndividu individu1, IIndividu individu2) {
		
		//On initialise la liste à retourner avec les individus inchangés
		ArrayList<IIndividu> retour = 
				new ArrayList<IIndividu>(Arrays.asList(new IIndividu[] {individu1, individu2}));
		
		//Si le hasard en décide ainsi (et que les individus sont du même type, on effectue le crossOver
		if(this.doCrossOver() && individu1.getType().equals(individu2.getType())){
					
			int nbTotalCaracteres = individu1.getNombreCaracteristiques();
			/* On s'assure que les individus contiennent au moins n+1 caractères */
			int nbCaracteresEffectif = (this.nbCaracteres < nbTotalCaracteres)?
												this.nbCaracteres:nbTotalCaracteres;
			IIndividu n1 = individu1.clone();
			IIndividu n2 = individu2.clone();
			
			/* On choisi au hasard les caractères à interchanger et on effectue le crossOver*/
			for(int i=0; i< nbCaracteresEffectif; i++){
				int j = (int)(Math.random()*nbTotalCaracteres);
				ICaracteristique c1 = n1.getListCaracteristique().get(j).clone();
				ICaracteristique c2 = n2.getListCaracteristique().get(j).clone();
				n1.getListCaracteristique().remove(j);
				n1.getListCaracteristique().add(j,c2);
				n2.getListCaracteristique().remove(j);
				n2.getListCaracteristique().add(j, c1);
			}
			n1.setName(n1.getName()+"-j");
			n2.setName(n2.getName()+"-j");
			retour = new ArrayList<IIndividu>(Arrays.asList(new IIndividu[] {n1, n2}));
		}
		return retour;
	}
}
