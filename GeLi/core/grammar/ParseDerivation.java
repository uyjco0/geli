package core.grammar;

/**
 * 
 * 
 * 
 * It belongs to the Public API.
 * 
 * @author Jorge Couchet
 * 
 */

import java.util.Collection;

public interface ParseDerivation {

	
	/**
	 * 
	 * Given a Collection of Elements and a token tk, the function splits the Collection in severals 
	 * subcollections in the following way: 
	 * 
	 * 1) If for example the Collection is cl = abcTKdeTKa, and the token is TK, the function splits cl in: "abc", 
	 *    "de" and "a".
	 * 2) If for example the Collection is cl = TKabc, and the token is TK, the function splits cl in: NULL.
	 * 3) If for example the Collection is cl = TKabcTK, and the token is TK, the function splits cl in: "abc".
	 * 4) If for example the Collection is cl = abc, and there isn't a token, the function splits cl in: NULL.
	 *    
	 * The function takes the elements before and between each token as the new subcollection. Note that the 
	 * token (TK in the example) isn't included in the subcollections.
	 *
	 * 
	 * @param cl
	 * 				The Collection to be splited.
	 * 
	 * @param tk
	 * 				The token to be used in order to split the Collection.
	 * 
	 * @return
	 * 				A Collection that has the subcollections splited from cl as its elements.
	 * 
	 */
	public Collection<Collection<Element>> splitElementsBeforeToken(Collection<Element> cl, String tk);
	
	
	/**
	 * 
	 * Given a Collection of Elements and the tokens tk1 and tk2, the function splits the Collection in severals 
	 * pairs of subcollections in the following way:
	 * 
	 * 1) If for example the Collection is cl = TK1abcTK2deTK1geTK2hf, and the tokens are TK1 and TK2, the function
	 * splits cl in: {"abc" , "de"} and {"ge", "hf"}.
	 * 2) If for example the Collection is cl = TK1abcTK2, and the tokens are TK1 and TK2, the function splits cl
	 * in: {"abc" , NULL}.
	 * 3) If for example the Collection is cl = abcTK1ggTK2rr, and the tokens are TK1 and TK2, the function splits
	 * cl in: {"gg" , "rr"}.
	 * 
	 * The function takes the elements after the first token (TK1 in the example), and before the second token
	 * (TK2) in order to create the pairs of subcollections. Note that the tokens TK1 and TK2 aren't included 
	 * in the subcollections. 
	 * 
	 * @param cl
	 * 			 The Collection to be splited
	 * 
	 * @param tk1
	 * 			  The first token to be used in order to split the Collection.
	 * 
	 * @param tk2
	 * 			  The first token to be used in order to split the Collection.
	 * 
	 * 
	 */
	public Collection<Collection<Collection <Element>>> splitElementsBetweenTokens(Collection<Element> cl, String tk1, String tk2);
	
	
	/**
	 * 
	 * Delete the first and last Elements of the Collection and returns a new Collection without those 
	 * Elements.
	 * 
	 * @param cl
	 * 			 The Collection from whom the first and last Elements are deleted.
	 * 
	 */
	public Collection <Element> deleteFirstAndLast(Collection<Element> cl);
}
