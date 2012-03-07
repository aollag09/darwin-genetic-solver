package tuples;

import java.util.ArrayList;
import darwin.modele.Individu;
import darwin.interfaces.ICaracteristique;

public class Tuple extends Individu{
	
	public static final int TYPE_TUPLE = 1;
	
	private static final int VALEUR_DEFAUT_TUPLE = 0;
	
	private int nbEntiers;
	
	public int getNbEntiers() {
		return nbEntiers;
	}

	public Tuple(int nombreEntiers, int[] valeurs, int id){
		this.type = TYPE_TUPLE;
		this.name = "Tuple " + id;
		this.nbEntiers = nombreEntiers;
		this.caracteristiques = new ArrayList<ICaracteristique>();
		try{
			if(valeurs == null || valeurs.length==0){
				for(int i=0; i<nombreEntiers; i++){
					this.caracteristiques.add(new Entier(VALEUR_DEFAUT_TUPLE));
				}
			}
			else{
				for(int i=0; i<nombreEntiers; i++){
					if(valeurs.length-1<i){
						this.caracteristiques.add(new Entier(VALEUR_DEFAUT_TUPLE));
					}
					else{
						this.caracteristiques.add(new Entier(valeurs[i]));
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	protected Tuple(Tuple t){
		super(t);
		this.nbEntiers = t.nbEntiers;
	}
	
	@Override
	public Individu clone() {
		return new Tuple(this);
	}
	
	public String toString(){
		String s = "<";
		for(ICaracteristique c : this.getListCaracteristique()){
			Entier e = (Entier)c;
			s += e.getValeur() +", ";
		}
		s = s.substring(0, s.length()-2) + ">";
		return (this.getName() + " : " + s);
	}

}
