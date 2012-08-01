package core.grammar;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.Vertex;
import edu.uci.ics.jung.graph.Edge;


/** 
 * 
 * Interface that defines the methods that are required to map the Grammar against a 
 * Graph, and draw the corresponding Graph.
 * 
 * The interface encapsulates (hides) from the Grammar the methods used for the mapping.
 * 
 * It does not belong to the Public API.
 * 
 * 
 * @author Jorge Couchet.
 * 
 * 
 **/

interface GraphGrammar_internal {

	
	
	/**
	 * 
	 * Returns the Graph that maps the Grammar.  The Graph could be an empty Graph.
	 * 
	 **/
	Graph getGraph();
	
	
	/**
	 * 
	 * Returns the Grammar that is mapped to the Graph.
	 * 
	 **/
	Grammar getGrammar();
	
	
	/**
	 * 
	 * Returns the Vertex that have associated the start Symbol. Returns NULL if there
	 * isn´t a Vertex that has the start symbol associated.
	 * 
	 **/
	Vertex getVertexWithStartSymbol();
	
	
	/**
	 * 
	 * Returns the label associated to the Vertex v. If the Vertex doesn´t has a label
	 * associated, or the Vertex is NULL returns the empty String ("").
	 * 
	 * @param v
	 * 			The Vertex that is necessary to know his label.
	 * 
	 **/
	String getLabelVertex(Vertex v);
	
	
	/**
	 * 
	 * Returns the Element of the Grammar associated to the Vertex v. If the Vertex 
	 * doesn´t has an Element associated, or the Vertex is NULL returns NULL.
	 * 
	 * @param v
	 * 			The Vertex that is necessary to know the Element of the Grammar
	 *          associated with him.
	 * 
	 * 
	 **/
	Element getElement(Vertex v);
	
	
	/**
	 * 
	 * Returns the Vertex of the Graph associated to the Element e of the Grammar. 
	 * If the Element doesn´t has a Vertex associated, or the Element is NULL 
	 * returns NULL.
	 * 
	 * @param e
	 * 			The Element of the Grammar that is necessary to know the Vertex of the
	 * 		    Graph associated with him.
	 *
	 * 
	 **/
	Vertex getVertex(Element e);
	
	
	/**
	 * 
	 * Returns the label associated to the Edge e. If the Edge doesn´t has a label
	 * associated, or the Edge is NULL returns the empty String ("").
	 * 
	 * @param e
	 * 			The Edge that is necessary to know his label.
	 * 
	 **/
	String getLabelEdge(Edge e);
	
	
	/**
	 * 
	 * Returns the probability associated to the Edge e. If the Edge doesn´t has a 
	 * probability associated, or the Edge is NULL returns a negative value.
	 * 
	 * @param e
	 * 			The Edge that is necessary to know his probability.
	 * 
	 **/
	double getProbability(Edge e);
	
	
	/**
	 * 
	 * Returns the order associated to the Edge e. If the Edge doesn´t has a 
	 * order associated, or the Edge is NULL returns a negative value.
	 * 
	 * @param e
	 * 			The Edge that is necessary to know his order.
	 * 
	 **/
	int getOrder(Edge e);
	
	
	
	
	/**
	 * 
	 * Maps a Grammar against a Graph.
	 * 
	 * Throws a GrammarExceptionImpl if there is any problem.
	 * 
	 * 
	 **/
	void GrammarToGraph()throws GrammarExceptionImpl;
	
	
	/**
	 * 
	 * Maps a Grammar against a Graph, but uses less nodes that the method GrammarToGraph.
	 * 
	 * Throws a GrammarExceptionImpl if there is any problem.
	 * 
	 * 
	 **/
	void GrammarToGraphResumed() throws GrammarExceptionImpl;
	
	
	/**
	 * 
	 * Draws the Graph.
	 *  
	 * 
	 **/
	void drawGraph();
}
