package core.grammar;


import java.util.Collection;
import java.util.Iterator;



/** 
 *  The interface encapsulates (hides) the implementation of the structure where hold the Terminal
 *  symbols of the Grammar.
 *  
 *  It does not belong to the Public API.
 *  
 *  @author Jorge Couchet
 * 
 * 
 **/

  interface Terminals_internal {

	
	
	/** 
	 * 
	 * Return an iterator in order to iterate over the Terminals that are in the Terminals Object.
	 * 
	 * If Terminals is empty returns NULL.
	 * 
	 * 
	 **/
	Iterator<Terminal> iterator();
	
	
	/** 
	 * 
	 * Return the the Terminals that are in the Terminals object as a Collection.
	 * 
	 * If Terminals is empty returns NULL. 
	 * 
	 **/
	Collection<Terminal> values();

	
	/** 
	 * 
	 * Adds a Terminal to the Terminal object.
	 * 
	 * The method returns NULL if the ID of t doesn´t collide with other ID. If there is a collision, 
	 * then the new t replace the old_t, and the method returns old_t.
	 * 
	 * Throws a GrammarExceptionImpl if the Terminal is NULL.
	 * 
	 * @param t
	 * 			The Terminal to be added.
	 * 
	 **/
	Terminal add(Terminal t) throws GrammarExceptionImpl;
	
	
	/** 
	 * 
	 * Return the Terminal of the Terminals object, so that the ID of the Terminal fulfill
	 * that ID == t.getId(), returns NULL otherwise.
	 * 
	 * @param t
	 * 			The Terminal to be used as a filter, this only need to set the ID field.
	 * 
	 **/
	Terminal get(Terminal t);
	
	
	/** 
	 * 
	 * Return the Terminal of the Terminals object, so "id" must be greater than zero, returns NULL otherwise.
	 * 
	 * @param id
	 * 			The Terminal's id to be used as a filter.
	 * 
	 **/
	Terminal get(int id);
	
	
	/** 
	 * 
	 * Return the Terminal of the Terminals object, so "symbol" must be not NULL, returns NULL otherwise.
	 * 
	 * @param id
	 * 			The Terminal's symbol to be used as a filter.
	 * 
	 **/
	Terminal get(String symbol);
	
	
	/** 
	 * It updates for each Terminal that belongs to Terminals its relation with the Production p.
	 * This is, for each Terminal its SetRelation method is called with p as argument.  
	 *  
	 * The method should be called when a new Production is added to the Grammar.
	 * 
	 * The method throws a GrammarException if there were any collision. 
	 * 
	 * @param g
	 * 			The Grammar associated to the Terminals. 
	 * 
	 *  @param p
	 *  		 The Production that is necessary associate with the Terminals symbols of the Grammar.
	 * 
	 *  
	 **/
	void setRelations(Production p, Grammar g) throws GrammarExceptionImpl;
	
	
	/**
	 * 
	 * Returns TRUE if Terminals is empty, FALSE otherwise.
	 * 
	 * */
	boolean isEmpty();
}
