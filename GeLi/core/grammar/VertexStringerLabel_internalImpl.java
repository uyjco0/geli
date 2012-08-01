package core.grammar;

/**
 * 
 * Class that offers a concrete implementation of the interface VertexStringer.
 * 
 * it is a helper class in order to add a label to the Vertex of the Graph. The class is used 
 * with the PluggableRenderer class in order to draw a Graph with label in the vertices.
 * 
 * It does not belong to the Public API.
 * 
 * @author Jorge Couchet
 * 
 **/

import java.util.Map;
import edu.uci.ics.jung.graph.ArchetypeVertex;
import edu.uci.ics.jung.graph.Vertex;
import edu.uci.ics.jung.graph.decorators.VertexStringer; 


class VertexStringerLabel_internalImpl implements VertexStringer  {

	
	
	/* Holder of the labels of each Vertex of the Graph. */
	protected Map<Vertex,String> map; 
	
	
	
	/**
	 * 
	 * Constructor that receive the map with the labels of each Vertex.
	 * 
	 * @param labels
	 * 				 The map with the labels of each Vertex.
	 * 
	 * 
	 **/
	VertexStringerLabel_internalImpl(Map<Vertex,String> labels) {
	       this.map = labels; 
	   } 
	
	
	/**
	 * 
	 * Returns the associated label of a given Vertex. If the map is NULL or the Vertex
	 * doesn´t has a label associated returns the empty String ("");
	 * 
	 * @param arg0
	 * 				The Vertex that is necessary to know his label.
	 *  
	 * 
	 **/
	public String getLabel(ArchetypeVertex arg0) {
		if((this.map!=null)&&(this.map.containsKey(arg0))){
			return this.map.get(arg0); 
		}else{
				return "";
		}
	}

}
