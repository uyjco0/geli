package core.grammar;

import java.util.Map;




/**
 * 
 *     The inteface uses an STRATEGY pattern to encapsulates (hides) the algorithm implementation 
 *     used for the length calculation of a Production.
 *     
 *     The implementation used in Runtime should be selected from a Configuration File, as a Parameter
 *     or using another method.
 *       
 *     It does not belong to the Public API.
 *     
 *     
 *     @author Jorge Couchet 
 * 
 * 
 * 
 **/

 interface LengthProductionAlgorithm_internal {
	
	 
	 
	/**
	* 
	* Calculates the length of the Terminal t of the Grammar. 
	* 
	* Returns -2 if the Grammar is NULL or the Terminal t doesn´t belong to the Grammar.
	* 
	* @param g
	* 			The Grammar that has the Terminal nt.
	* 
	* @param t
	* 			The Terminal that will be used for the length calculation.
	*  
	* 
	**/
	int lengthTerminal(Grammar g, Terminal t);
	 
	 
	/**
	* 
	* Calculates the length of the NonTerminal nt of the Grammar.
	* 
	* It's necessary to take care of to have associated all the Productions to the Grammar before
	* call this method (because is possible that its length depends of the others Productions).
	* 
	* Returns -2 if the Grammar is NULL or the NonTerminal nt doesn´t belong to the Grammar.
	* Returns -1 if the NonTerminal nt doesn´t have associated Productions (an associated 
	* Production is a Production that have nt at the left side).
	* Returns -3 if the NonTerminal was marked (but it only happens inside of the recursion).
	* Returns the lenght of the NonTerminal nt if the above conditions are´nt fullfiled. 
	* 
	* Throws a GrammarException if there is a problem in the calculation of the lenght, or
	* if there is an infinite loop with the Productions that are associated to the 
	* NonTerminal nt.
	* 
	* 
	* @param g
	* 			The Grammar that has the NonTerminal nt.
	* 
	* @param nt
	* 			The NonTerminal that will be used for the length calculation.
	* 
	* @param map
	* 			It is used for the recursion in order to mark the NonTerminal visited, 
	* 			then in the first time should be a NULL.
	* 
	* @param store
	* 				If is TRUE then uses the structure of the Grammar that holds the lengths
	* 				of the NonTerminals. If in this structure isn´t the lenght, the the method
	* 				calculates the lenght of the NonTerminal nt and stores it in the structure.  
	* 				If is FALSE the method always calculates the lenghts.
	*  
	**/
	int lengthNonTerminal(Grammar g, NonTerminal nt, Map<Integer,Boolean> map, boolean store) throws GrammarExceptionImpl;
	
	 
	/**
	* 
	* Calculates and stores the length of each NonTerminal of the Grammar.
	* 
	* It's necessary to take care of to have associated all the Productions to the Grammar before
	* call this method (because is possible that its length depends of the others Productions).
	* 
	*
	* Throws a GrammarException if there the Grammar is NULL, of the Grammar doesn't has 
	* NonTerminals symbols, or doesn't has a start symbol, or there is any problem in the
	* calculation of the lengths of the NonTerminals.
	* 
	* 
	* @param g
	* 			The Grammar that is necessary to calculate the lenght of his NonTerminals.
	* 
	* 
	**/
	public void storeLengthNonTerminals(Grammar g) throws GrammarExceptionImpl;
	
	
	/**
	* 
	* Calculates the length of the Production p of the Grammar.
	* 
	* It's necessary to take care of to have associated all the Productions to the Grammar before
	* call this method (because is possible that its length depends of the others Productions).
	* 
	* Returns -2 if the Grammar is NULL or the Production p doesn´t belong to the Grammar.
	* Returns -1 if the Production p doesn´t have associated NonTerminals (an associated 
	* NonTerminals is a NonTerminal that is at the right side of the Production).
	* Returns the lenght of the Production p if the above conditions are´nt fullfiled.
	* 
	* Throws a GrammarException if the Production doesn´t has right symbols, or there is a 
	* problem in the calculation of the lenght, or if there is an infinite loop.
	* 
	* 
	* @param g
	* 			The Grammar that has the Production p.
	* 
	* @param p
	* 			The Production that will be used for the length calculation.
	* 
	* 
	* @param store
	* 				If is TRUE then uses the structure of the Grammar that holds the lengths
	* 				of the Productions. If in this structure isn´t the lenght of the 
	*               Production p, then the the method calculates the lenght and stores it in 
	*               the structure.  
	* 				If is FALSE the method always calculates the lenghts.
	*  
	**/
	int lengthProduction(Grammar g, Production p, boolean store) throws GrammarExceptionImpl;
	
	
	/**
	 * 
	 * Calculates and stores (in the Grammar) the length of each Production of the Grammar.
	 * 
	 * It's necessary to take care of to have associated all the Productions to the Grammar 
	 * before the calculation of a Production (because is possible that its length depends 
	 * of the others Productions).
	 * 
	 * Throws a GrammarException if there the Grammar is NULL, of the Grammar doesn't has 
	 * Productions symbols, or doesn't has a start symbol, or there is any problem with the 
	 * calculation of the lengths of  the NonTerminals. 
	 * 
	 * @param g
	 * 			The Grammar that has the Productions that is necessary calculate theirs lengths.
	 *    
	 *  
	 **/
	void storeLengthProductions(Grammar g) throws GrammarExceptionImpl;
	

}
