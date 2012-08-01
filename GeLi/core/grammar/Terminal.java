package core.grammar;


import java.util.Collection;




/**
 *     
 *     The inteface declares the methods that define the responsabilities of a Terminal object.
 *      
 *     It belongs to the Public API.
 *     
 *     @author Jorge Couchet
 *     
 **/

public interface Terminal extends Element {

	
		
	/**
	 * 
	 *  Returns all the Productions where the Terminal is at the right side.
	 *  
	 * If there aren´t Productions return NULL.
	 * 
	 * @param g
	 * 			The Grammar associated to the Terminal.
	 * 
	 *  
	 **/
	public Collection<Production> getAllRightsProd(Grammar g);

	
	/** 
	 * 
	 * Checks if the Terminal is at the right side of the Production with ID==p.getId().
	 * 
	 *  If it is TRUE returns the Production, if not returns NULL.
	 *  
	 * @param g
	 * 			The Grammar associated to the Terminal.  
	 *  
	 * @param p
	 * 		  The Production object used as a filter. It is only necessary that has a value in the ID
	 *        field. 
	 *  
	 *  
	 **/
	public Production getRightProd(Production p, Grammar g);
	
	
	/** 
	 * 
	 * If any Terminal symbol that belongs to the Production p match with the Terminal that 
	 * is represented by THIS, then the method sets the right structure of the Terminal 
	 * with the Production p.  Otherwise the method don´t do anything.
	 *
	 * In the right structure is where the Terminal holds the reference to the Productions where  
	 * participates.
	 * 
	 * The method returns NULL if the ID of p doesn´t collide with other ID. If there is a collision, 
	 * then the new p replace the old_p, and the method returns old_p.
	 * 
	 * Throws a GrammarException if the Production is NULL or there was a problem.
	 * 
	 * @param g
	 * 			The Grammar associated to the Terminal. 
	 * 
	 * @param p
	 * 		  The Production object used as a filter. It is only necessary that has a value in the ID
	 *        field.  
	 *  
	 * 
	 **/
	public Production setRelation(Production p, Grammar g) throws GrammarExceptionImpl;
	
	
	/**
	 * 
	 * Clone the Terminal, returning a new one.
	 * 
	 */
	Terminal cloneTerminal() throws GrammarExceptionImpl;
	
	
	/**
	 * 
	 * Clear the structures used by the Terminal Object.
	 * 
	 */
	public void clearTerminal();
}
