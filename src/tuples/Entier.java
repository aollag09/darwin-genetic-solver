package tuples;

import darwin.modele.Caracteristique;

public class Entier extends Caracteristique{

	private int valeur;
	
	public int getValeur() {
		return valeur;
	}

	public static final int MAX_ENTIER = 20;
	
	private static final String NOM_ENTIER = "Entier";
	
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
