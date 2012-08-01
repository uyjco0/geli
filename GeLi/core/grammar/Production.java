package core.grammar;



import java.util.Collection;



/**
 *     
 *     The inteface declares the methods that define the responsabilities of a Production object.
 *      
 *     It belongs to the Public API.
 *     
 *     
 *     @author Jorge Couchet
 *     
 **/

public interface Production extends Element {

	
	
	/** 
	 * 
	 * Set the NonTerminal nt as the left side symbol of the Production.
	 * 
	 * Throws a GrammarExceptionImpl if the NonTerminal is NULL.
	 * 
	 * @param nt
	 * 			 The NonTerminal symbol to be associated to the Production.
	 * 
	 **/
	public void setLeft(NonTerminal nt) throws GrammarExceptionImpl;
	
	
	/** 
	 * 
	 * @param g
	 * 			 The Grammar associated to the Production.
	 * 			
	 * 
	 * Returns the NonTerminal symbol that is in the left side of the Production.
	 * 
	 * It does not belong to the Public API.
	 * 
	 * 
	 **/
	public NonTerminal getLeft(Grammar g);
	
	
	/**
	 * 
	 * Insert the symbol that is in the position pos at the right side of the Production.
	 * 
	 * Returns TRUE if is possible insert the Element at the position pos, FALSE otherwise.
	 * 
	 * @param e
	 * 			The symbol that is at the right side of the Production.
	 * 
	 * @param pos
	 * 			  The absolute position (starting in 1) of the symbol at the right side of the 
	 *            Production.
	 * 
	 **/
	 public boolean addRightElement(Element e, int pos);
	
	
	/** 
	 * 
	 * @param g
	 * 			The Grammar associated to the Production.
	 * 
	 * Returns a collection with the NonTerminals that are at the right side of the 
	 * Production.
	 * 
	 * Throws a GrammarException if it isn´t possible to add a NonTerminal to the Collection.
	 * 
	 * Return NULL if there aren´t symbols at the right side.
	 * 
	 **/
	public Collection<NonTerminal> getNonTerminalsRight(Grammar g) throws GrammarExceptionImpl;
	
	
	/** 
	 * 
	 * @param g
	 * 			 The Grammar associated to the Production.
	 * 
	 * Returns a collection with the Terminals that are at the right side of the 
	 * Production.
	 * 
	 * Throws a GrammarException if it isn´t possible to add a Terminal to the Collection.
	 * 
	 * Return NULL if there aren´t symbols at the right side. 
	 * 
	 * 
	 **/
	public Collection<Terminal> getTerminalsRight(Grammar g) throws GrammarExceptionImpl;
	
	
	/** 
	 * 
	 * @param g
	 * 			 The Grammar associated to the Production.
	 * 
	 * Returns a Collection with all the symbols at the right side with the order preserved.
	 * 
	 * Throws a GrammarException if it isn´t possible to add an Element to the Collection, or 
	 * if there are NULL elements mixed with non NULL elements at the right side of 
	 * the Production.
	 * 
	 * Return NULL if there aren´t symbols at the right side of the production.
	 * 
	 * 
	 **/
	public Collection<Element> getRights(Grammar g) throws GrammarExceptionImpl;
	
	
	/** 
	 * 
	 * If the Element e belongs to the right side of the Production, then returns an ordered Collection
	 * with the positions where the Element appears, oterwise returns NULL.
	 * 
	 * The positions are converted to the absolute position starting at 1.
	 * 
	 * Throws a GrammarException if it isn´t possible to add a position to the Collection. 
	 * 
	 * @param e
	 * 			 The Element symbol to be checked with the Production.
	 * 
	 * 
	 **/
	public Collection<Integer> getRightPosition(Element e) throws GrammarExceptionImpl;
	
	
	/**
	 * 
	 * Clone the Production returning a new one.
	 * 
	 * @throws GrammarExceptionImpl
	 */
	Production cloneProduction() throws GrammarExceptionImpl;
	
	
	/**
	 * 
	 * Clear the structures used by the Production Object.
	 * 
	 */
	public void clearProduction();
	
}
