package tp;

import parallelisation.client.MaitreTSP;

public class TP1 extends TP {

	public TP1() {
		super();
		intialisationExp();
	}

	/**
	 * Ajout de toutes les expériences à effectuer pour ce TP
	 */
	private void intialisationExp(){
		for (int i = 1; i < 9; i++) {
			for (int j = 1; j < 51; j++) {
				MaitreTSP maitre = new MaitreTSP(i, this);
				Experience exp = new Experience(" TP1 Experience n°"+j+" avec "+i+" serveurs", maitre);
				this.ajouterExperience(exp);
			}
		}
	}
	
	public static void main(String[] args) {
		TP1 tp1 = new TP1();
		tp1.lancerTP();
	}
	
	
}
