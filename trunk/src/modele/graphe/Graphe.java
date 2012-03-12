package modele.graphe;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.util.ArrayList;
import javax.swing.JPanel;


import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse.Mode;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

/**
 * Un objet graphe qui nous permettra de modéliser tous les types de graphes !
 * 
 * @author Amaury
 *
 */
public class Graphe extends SparseMultigraph<Ville, Road>  {
	
	private static final long serialVersionUID = 4211750183598614203L;
	
	// CONSTRUCTEUR
	
	public Graphe() {
		super();
	}
	
	// ACCESSEUR PARTICULIERS
	
	public ArrayList<Road> getEdges() {
		return new ArrayList<Road>(super.getEdges());
	}

	public ArrayList<Ville> getVertices(){
		return new ArrayList<Ville>(super.getVertices());
	}
	
	/**
	 * Méthode permettant de gérer un affichage dynamique et intéractif du graphe 
	 * @param d, la dimension à l'écran souhaitée pour le graphe !
	 * @return
	 */
	public JPanel afficher(Dimension d){
		JPanel pane = new JPanel();
		pane.setBackground(Color.WHITE);
		
		Layout<Ville, Road> layout = new StaticLayout<Ville, Road>(this);
		layout.setSize(new Dimension(700,500));
		
		/* On cherche le maximum en X et en Y*/
		double maxX = Integer.MIN_VALUE,maxY = Integer.MIN_VALUE;
		for(Ville t : this.getVertices()){
			if(t.getPosition().getX() > maxX)
				maxX = t.getPosition().getX();
			if(t.getPosition().getY() > maxY)
				maxY = t.getPosition().getY();
		}

		for(Ville t : this.getVertices()){
			layout.setLocation(t, new Point( (int)( (t.getPosition().getX() / maxX) * 700),
					(int)( (t.getPosition().getY() / maxY) * 500)));
		}

		// The BasicVisualizationServer<V,E> is parameterized by the edge types
		// Setup up a new vertex to paint transformer...
		VisualizationViewer<Ville,Road> vv =
				new VisualizationViewer<Ville,Road>(layout);

		Transformer<Ville,String> vertexStr = new Transformer<Ville,String>() {
			@Override
			public String transform(Ville arg0) {
				return arg0.toString();
			}
		};

		Transformer<Road, String> roadStr = new Transformer<Road, String>() {

			@Override
			public String transform(Road arg0) {
				return arg0.toString();
			}

		};
		
		Transformer<Ville, Paint> vertexPaint = new Transformer<Ville, Paint>() {

			@Override
			public Paint transform(Ville arg0) {
				return Color.WHITE;
			}
		};

		vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
		vv.getRenderContext().setVertexLabelTransformer(vertexStr);
		vv.getRenderContext().setEdgeLabelTransformer(roadStr);
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);

		vv.setPreferredSize(new Dimension(750,550)); 
		DefaultModalGraphMouse<?, ?> gm = new DefaultModalGraphMouse<Object, Object>();
		gm.setMode(Mode.TRANSFORMING);
		vv.setGraphMouse(gm);
		
		pane.add(vv);
		return pane;
		
	}


}
