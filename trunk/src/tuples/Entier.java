package tuples;

import darwin.modele.Caracteristique;

public class Entier extends Caracteristique{

	private static final long serialVersionUID = 8586572209708716484L;

	/**
	 * La valeur de l'entier
	 */
	private int valeur;
	
	/**
	 * @return La valeur de l'entier
	 */
	public int getValeur() {
		return valeur;
	}

	/**
	 * La valeur maximum possible pour un entier
	 */
	public static final int MAX_ENTIER = 20;
	
	/**
	 * Le nom de la caractéristique
	 */
	private static final String NOM_ENTIER = "Entier";
	
	/**
	 * Constructeur à partir d'une valeur
	 * @param valeur
	 * @throws Exception si la valeur est négative ou supérieure au maximum
	 */
	public Entier(int valeur) throws Exception{
		this.nom = NOM_ENTIER;
		if(valeur < 0 || valeur > MAX_ENTIER){
			System.out.println("Valeur d'entier incorrecte");
			throw new Exception();
		}
		this.valeur = valeur;
		this.bitSet = Utiles.convertInBitSet(valeur);
		this.tailleBitSet = Utiles.nombreBitNecessairesPour(valeur);
	}
	
	/**
	 * Constructeur par copie profonde (pour le clone)
	 * @param e
	 */
	protected Entier(Entier e){
		super(e);
		this.valeur = e.getValeur();
	}

	@Override
	public Caracteristique clone() {
		return new Entier(this);
	}

	@Override
	public void update() {
		int value = Utiles.intFromBitSet(this.getBitSet());
		this.valeur = (value<0 || value>MAX_ENTIER)?((value<0)?0:MAX_ENTIER):value;	
	}
}	
