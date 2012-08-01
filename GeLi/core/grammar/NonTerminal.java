package core.grammar;



import java.util.Collection;





/**
 *     
 *     The inteface declares the methods that define the responsabilities of a NonTerminal object.
 *      
 *     It belongs to the Public API.
 *     
 *     
 *     @author Jorge Couchet
 *     
 **/


public interface NonTerminal extends Element {

	
	
	/** 
	 * 
	 * Returns all the Productions where the NonTerminal is at the left side.
	 * 
	 * If there aren´t Productions return NULL.
	 * 
	 * @param g
	 * 			 The Grammar associated to the NonTerminal.
	 * 
	 * 
	 **/
	public Collection<Production> getAllLeftsProd(Grammar g);
	
	
	/** 
	 * 
	 * Checks if the NonTerminal is at the left side of the Production with ID==p.getId().
	 * 
	 * If it is TRUE returns the Production, if not returns NULL.
	 * 
	 * @param g
	 * 			 The Grammar associated to the NonTerminal. 
	 * 
	 * @param p
	 * 		  The Production object used as a filter. It is only necessary that has a value in the ID
	 *        field.
	 * 
	 **/
	public Production getLeftProd(Production p, Grammar g);
	
	
	/** 
	 * 
	 * Returns all the Productions where the NonTerminal is at the right side.
	 * 
	 * If there aren´t Productions return NULL. 
	 * 
	 * @param g
	 * 			 The Grammar associated to the NonTerminal. 
	 * 
	 * 
	 **/
	public Collection<Production> getAllRightsProd(Grammar g);
	
	
	/** 
	 * 
	 * Checks if the NonTerminal is at the right side of the Production with id==p.getId().
	 * 
	 *  If it is TRUE returns the Production, if not returns NULL.
	 *  
	 * @param g
	 * 			 The Grammar associated to the NonTerminal.  
	 *  
	 * @param p
	 * 		  The Production object used as a filter. It is only necessary that has a value in the ID
	 *        field. 
	 *  
	 **/
	public Production getRightProd(Production p, Grammar g);
	
	/** 
	 * 
	 * If any NonTerminal symbol that belongs to the Production p match with the NonTerminal that 
	 * is represented by THIS, then the method sets the right and left structures of the NonTerminal 
	 * with the Production p.  Otherwise the method don´t do anything.
	 * 
	 * In the right and left structures is where the NonTerminal holds the reference to the 
	 * Productions where participates.
	 * 
	 * The method returns NULL if the ID of p doesn´t collide with other ID. If there is a collision, 
	 * then the new p replace the old_p, and the method returns old_p.
	 * 
	 * Throws a GrammarException if the Production is NULL or there was a problem.
	 * 
	 * Non Public API
	 * 
	 * @param p
	 * 		  The Production object used as a filter. It is only necessary that has a value in the ID
	 *        field.
	 *  
	 *  @param g
	 *  		 The Grammar associated to the NonTerminal.
	 * 
	 **/
	public Production setRelation(Production p, Grammar g) throws GrammarExceptionImpl;
	
	/** 
	 * 
	 * Clone the NonTerminal.
	 * 
	 * Returns a new NonTerminal. 
	 * 
	 **/
	public NonTerminal cloneNonTerminal() throws GrammarExceptionImpl;
	
	
	/**
	 * 
	 * Clear the structures used by the NonTerminal Object.
	 * 
	 */
	public void clearNonTerminal();
	

}
