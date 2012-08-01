package core.grammar;


import java.util.Collection;
import java.util.Iterator;




/** 
 * 
 *  The interface encapsulates (hides) the implementation of the structure where hold the Productions
 *  of the Grammar.
 *  
 *  It does not belong to the Public API.
 *  
 *  @author Jorge Couchet
 * 
 * 
 **/

 interface Productions_internal {

	
	
	/** 
	 * 
	 * Return an iterator in order to iterate over the Productions that are in the Productions Object.
	 * 
	 * Returns NULL if Productions is empty. 
	 * 
	 **/
	Iterator<Production> iterator();
	
	
	/** 
	 * 
	 * Return the the Productions that are in the Productions object as a Collection.
	 * 
	 * Returns NULL if Productions is empty.
	 * 
	 * 
	 **/
	Collection<Production> values();

	
	/** 
	 * 
	 * Adds a Production to the Productions object.
	 * 
	 * The method returns NULL if the ID of p doesn´t collide with other ID. If there is a collision, 
	 * then the new p replace the old_p, and the method returns old_p.
	 * 
	 * Throws a GrammarExceptionImpl if the Production is NULL.
	 * 
	 * 
	 * @param p
	 * 			The Production to be added.
	 * 
	 **/
	Production add(Production p) throws GrammarExceptionImpl;
	
	
	/** 
	 * 
	 * Return the Production of the Productions object, so that the ID of the Production fulfill
	 * that ID == p.getId(), otherwise return NULL.
	 * 
	 * @param p
	 * 			The Production to be used as a filter, this only need to set the ID field.
	 * 
	 **/
	Production get(Production p);
	
	
	/** 
	 * 
	 * Return the Production of the Productions object, so that the ID must be grater than zero, otherwise 
	 * return NULL.
	 * 
	 * @param id
	 * 			The Production's id to be used as a filter.
	 * 
	 **/
	Production get(int id);
	
	
	/** 
	 * 
	 * Return the Production of the Productions object, so that the symbol must be not NULL, otherwise 
	 * return NULL.
	 * 
	 * @param symbol
	 * 			The Production's symbol to be used as a filter.
	 * 
	 **/
	Production get(String symbol);
	
	
	/**
	 * 
	 * Returns TRUE if Productions is empty, FALSE otherwise.
	 * 
	 * */
	boolean isEmpty();
	
	
	
}
