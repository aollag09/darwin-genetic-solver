package tuples;

import java.util.ArrayList;
import darwin.modele.Individu;
import darwin.interfaces.ICaracteristique;

public class Tuple extends Individu{
	
	
	private static final long serialVersionUID = -4445531036515782238L;

	/**
	 * Un entier correspondant au type du tuple (d�finit arbitrairement)
	 */
	public static final int TYPE_TUPLE = 1;
		
	/**
	 * Le nombre d'entiers du tuple
	 */
	private int nbEntiers;
	
	/**
	 * @return Le nombre d'entiers qui composent le tuple
	 */
	public int getNbEntiers() {
		return nbEntiers;
	}

	/**
	 * Constructeur � partir du nombre d'entiers, le tableau correspondant aux valeurs de ces entiers,
	 *  et un id servant � nommer le tuple
	 * @param nombreEntiers Le nombre d'entiers qui composent le tuple
	 * @param valeurs Les valeurs � attribuer � ce tuple
	 * @param id Un identifiant qui permet de nommer le tuple
	 * @throws Exception Si la taille du tableau de valeur ne correspond pas au nombre d'entier souhait�s
	 */
	public Tuple(int nombreEntiers, int[] valeurs, int id) throws Exception{
		this.type = TYPE_TUPLE;
		this.name = "Tuple " + id;
		this.nbEntiers = nombreEntiers;
		this.caracteristiques = new ArrayList<ICaracteristique>();
		if(valeurs.length != nombreEntiers){
			System.out.println("Taille du tuple diff�rente du nombre de valeurs � lui attribuer");
			throw new Exception();
		}
		else{
			try{
				for(int i=0; i<nombreEntiers; i++){
					this.caracteristiques.add(new Entier(valeurs[i]));
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Constructeur par recopie (pour le tuple)
	 * @param t
	 */
	protected Tuple(Tuple t){
		super(t);
		this.nbEntiers = t.nbEntiers;
	}
	
	@Override
	public Individu clone() {
		return new Tuple(this);
	}
	
	/**
	 * Surcharge de la m�thode toString, afin d'avoir un affichage des tuples adapt�
	 */
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
