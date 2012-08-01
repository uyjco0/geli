package core.grammar;


import java.util.Collection;
import java.util.Iterator;




/** 
 * 
 *  The interface encapsulates (hides) the implementation of the structure where hold the NonTerminal
 *  symbols of the grammar.
 *  
 *  It does not belong to the Public API.
 *  
 *  @author Jorge Couchet
 * 
 * 
 **/
 interface NonTerminals_internal {

	
	
	/** 
	 * 
	 * Return an iterator in order to iterate over the NonTerminals that are in the NonTerminals Object.
	 * 
	 * If Terminals is empty returns NULL. 
	 * 
	 **/
	Iterator<NonTerminal> iterator();
	
	
	/** 
	 * 
	 * Return the the NonTerminals that are in the NonTerminals object as a Collection.
	 * 
	 * If Terminals is empty returns NULL. 
	 * 
	 **/
	Collection<NonTerminal> values();

	
	/** 
	 * 
	 * Adds a NonTerminal to the NonTerminals object.
	 * 
	 * The method returns NULL if the ID of nt doesn´t collide with other ID. If there is a collision, 
	 * then the new nt replace the old_nt, and the method returns old_nt.
	 * 
	 * Throws a GrammarExceptionImpl if the NonTerminal is NULL.
	 * 
	 * 
	 * @param nt
	 * 			The NonTerminal to be added.
	 * 
	 **/
	NonTerminal add(NonTerminal nt) throws GrammarExceptionImpl;
	
	
	/** 
	 * 
	 * Return the NonTerminal of the NonTerminals object, so that the ID of the NonTerminal fulfill
	 * that ID == nt.getId(), returns NULL otherwise.
	 * 
	 * @param nt
	 * 			The NonTerminal to be used as a filter, this only need to set the ID field.
	 * 
	 **/
	NonTerminal get(NonTerminal nt);
	
	
	/** 
	 * 
	 * Return the NonTerminal of the NonTerminals object, so that the "id" must be greater than zero, returns 
	 * NULL otherwise.
	 * 
	 * @param id
	 * 			The NonTerminal's id to be used as a filter.
	 * 
	 **/
	NonTerminal get(int id);
	
	
	/** 
	 * 
	 * Return the NonTerminal of the NonTerminals object, so that the "symbol" must be not NULL, returns 
	 * NULL otherwise.
	 * 
	 * @param symbol
	 * 			The NonTerminal's symbol to be used as a filter.
	 * 
	 **/
	NonTerminal get(String symbol);
	
	
	/** 
	 *  It updates for each NonTerminal that belongs to NonTerminals its relation with the Production p.
	 *  This is, for each NonTerminal its SetRelation method is called, with p as argument.
	 *  
	 *  
	 *  The method should be called when a new Production is added to the Grammar.
	 *  
	 *  The method throws a GrammarException if there were any collision. 
	 *  
	 *  @param p
	 *  		 The Production that is necessary associate with the NonTerminals symbols of the Grammar.
	 *  
	 *  @param g
	 *  		 The Grammar associated to the NonTerminals.
	 *  
	 **/
	void setRelations(Production p, Grammar g) throws GrammarExceptionImpl;
	
	
	/**
	 * 
	 * Returns TRUE if NonTerminals is empty, FALSE otherwise.
	 * 
	 * */
	boolean isEmpty();
}
