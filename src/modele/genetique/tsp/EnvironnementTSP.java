package modele.genetique.tsp;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import darwin.modele.Environnement;
import modele.graphe.Graphe;
import modele.graphe.Point;
import modele.graphe.Road;
import modele.graphe.Ville;

/** L'environement dans lequel évolue le graphe est bien évidement la carte avec les distances entre toutes les
 * villes : le graphe Complet
 * 
 * 
 * @author Amaury
 *
 */
public class EnvironnementTSP extends Environnement{

	// CONSTANTES :
	
	/** Le nombre maximum de villes possibles */
	public static int NOMBRE_MAX_VILLES = Integer.MAX_VALUE;
	/** Le nombre minimum de villes possibles à tratier */
	public static int NOMBRE_MIN_VILLES = 1;
	/** Le nombre de ville */
	public static int NOMBRE_VILLES;
	/** Le nombre de bits necessaires pour coder les villes */
	public static int NOMBRE_BIT;
	
	// VARIABLES D'INSTANCES
	
	
	/** Le graphe complet de toutes les villes !*/
	private Graphe graphe;
	
	/** Le nombre de ville courante dans l'environement */
	private int nombreVilles;
	
	
	// CONSTRUCTEURS

	public EnvironnementTSP(Graphe graph) {
		this.setGraphe(graph);
	}

	public EnvironnementTSP() {
		super();
		graphe = new Graphe();
		load();
		genererRoad();
		graphe.afficher(new Dimension(700,500));
	}
	

	// AUTRES METHODES

	/**
	 * Méthode permettant de charger toutes les coordonnées des villes à partir du fichier texte
	 */
	private void load(){
		String fichier ="res/berlin52.tsp";

		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				try {
					Integer.parseInt((ligne.charAt(0)+""));
					// Création des villes
					String[] vals = ligne.split(" ");
					int id = Integer.parseInt(vals[0].trim());
					double x = Double.parseDouble(vals[1].trim());
					double y = Double.parseDouble(vals[2].trim());
					graphe.addVertex(new Ville(id, new Point(x,y)));
				} catch (Exception e) {
					if(ligne.startsWith("NAME"))
						this.name = "TSP Problem on : "+ ligne.split(":")[1].trim();
					if(ligne.startsWith("DIMENSION")){
						setNombreVilles(Integer.parseInt(ligne.split(":")[1].trim()));
						NOMBRE_VILLES = Integer.parseInt(ligne.split(":")[1].trim());
						NOMBRE_BIT = Utilitaire.nombreBitNecessairesPour(NOMBRE_VILLES);
					}
				}
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
	
	/**
	 * Méthode permettant de créer toutes les road pour avoir un graphe complet !
	 */
	private void genererRoad(){
		for(int i = 0; i < graphe.getVertexCount(); i++){
			for(int j = 0; j <graphe.getVertexCount(); j++){
				if( i != j){
					Ville v1 = graphe.getVertices().get(i);
					Ville v2 = graphe.getVertices().get(j);
					graphe.addEdge(new Road(Road.getDistance(v1, v2)), v1, v2);
				}
			}
		}
	}
	


	/**
	 * @return the graphe
	 */
	public Graphe getGraphe() {
		return graphe;
	}

	/**
	 * @param graphe the graphe to set
	 */
	public void setGraphe(Graphe graphe) {
		this.graphe = graphe;
	}

	
	/**
	 * @return the nombreVilles
	 */
	public int getNombreVilles() {
		return nombreVilles;
	}

	/**
	 * @param nombreVilles the nombreVilles to set
	 */
	public void setNombreVilles(int nombreVilles) {
		this.nombreVilles = nombreVilles;
	}

	@Override
	public double evaluerIndividu(darwin.interfaces.IIndividu individu)
			throws Exception {
		Chemin chemin = (Chemin) individu;
		double distanceTotale = 0;
		for(int i = 0; i< chemin.getNombreCaracteristiques() - 1; i++){
			distanceTotale += Road.getDistance(	(Ville) chemin.getCaracteristique(i),
												(Ville) chemin.getCaracteristique(i+1));
		}
		return distanceTotale;
	}

	@Override
	public boolean isValid(darwin.interfaces.IIndividu individu) {
		Chemin chemin = (Chemin) individu;
		boolean isValid = true;
		for(int i = 0; i< chemin.getNombreCaracteristiques(); i++){
			for(int j = i+1; j < chemin.getNombreCaracteristiques(); j++){
				if( (chemin.getCaracteristique(i)).equals((Ville) chemin.getCaracteristique(j)))
					isValid = false;
			}
		}
		return isValid;
	}
	
}
