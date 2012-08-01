package core.grammar;

/**
 * 
 * Class that offers a concrete implementation of the interface EdgeStringer.
 * 
 * it is a helper class in order to add a label to the Edge of the Graph. The class is used 
 * with the PluggableRenderer class in order to draw a Graph with label in the edges.
 * 
 * It does not belong to the Public API.
 * 
 * @author Jorge Couchet
 * 
 **/

import java.util.Map;
import edu.uci.ics.jung.graph.ArchetypeEdge;
import edu.uci.ics.jung.graph.DirectedEdge;
import edu.uci.ics.jung.graph.decorators.EdgeStringer; 


class EdgeStringerLabel_internalImpl implements EdgeStringer  {

	
	
	/* Holder of the labels of each Edge of the Graph. */
	protected Map<DirectedEdge,String> map; 
	
	
	/**
	 * 
	 * Constructor that receive the map with the labels of each Edge.
	 * 
	 * @param labels
	 * 				 The map with the labels of each Edge.
	 * 
	 * 
	 **/
	EdgeStringerLabel_internalImpl(Map<DirectedEdge,String> labels) {
	       this.map = labels; 
	} 
	
	
	/**
	 * 
	 * Returns the associated label of a given Edge. If the map is NULL or the Edge
	 * doesn´t has a label associated returns the empty String ("");
	 * 
	 * @param arg0
	 * 				The Edge that is necessary to know his label.
	 * 		  
	 * 
	 **/
	public String getLabel(ArchetypeEdge arg0) {
		if((this.map!=null)&&(this.map.containsKey(arg0))){
			return this.map.get(arg0); 
		}else{
				return "";
		}
	}

}
