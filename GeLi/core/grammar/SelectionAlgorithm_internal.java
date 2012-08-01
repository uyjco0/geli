package core.grammar;


import java.util.Collection;

/** 
 * 
 * It implements the STRATEGY Pattern because encapsulates (hides) the algorithm used to select an 
 * element of a collection.
 * 
 * The implementation used in Runtime should be selected from a Configuration File, as a Parameter
 * or using another method.
 * 
 * It does not belong to the Public API.
 * 
 * 
 *@author Jorge Couchet 
 * 
 * 
 **/
interface SelectionAlgorithm_internal <E> {

	
	
	
	/** 
	 * 
	 * It receives a collections of E elements and returns one of the elements with a particular 
	 * criteria.
	 * 
	 * @param c
	 * 			The collection to be used to select an element.
	 * 
	 * 
	 **/
	E selElement(Collection<E> c);
	
}
