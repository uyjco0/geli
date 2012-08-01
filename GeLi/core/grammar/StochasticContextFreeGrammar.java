package core.grammar;





/**
 *     
 *     The inteface declares the methods that define the responsabilities of a Stochastic Context Free
 *     Grammar (SCFG).
 *     
 *     The interface extend the more general interface Grammar.
 *     
 *     At this level should be declared the methods that are responsability of the Grammar, for example,
 *     the probability of a Production depends of the other Productions that share the same left side
 *     symbol, then there are two options: 1) the Production is aware of this restriction, or 2) the 
 *     Grammar is aware. It is better that the Grammar be aware of this, because first this knowledge 
 *     introduces a high coupling between the Production objects, and second the probability is only 
 *     necessary for the Derivation, and this is an operation at the level of the Grammar, not at the 
 *     level of a Production.
 *     
 *     A deterministic Context Free Grammar can be considered as a SCFG where the Productions that have
 *     the same left side symbol, have equal probability.
 *     
 *     It belongs to the Public API.
 *     
 *     
 *     @author Jorge Couchet
 *     
 **/

public interface StochasticContextFreeGrammar extends Grammar {
	
	
	
	/**
	 * 
	 * Returns TRUE is the Grammar is a true stochastic Grammar, and FALSE if is a
	 * standard Context Free Grammar.
	 * 
	 *
	 * */
	public boolean getType();
	
	
	/**
	 * 
	 * Sets the type of the Grammar.
	 * 
	 * @param type
	 * 				The type of the Grammar: Stochastic (TRUE) or standard (FALSE).
	 * 
	 * 
	 * 
	 * */
	public void setType(boolean type);
	
	
	/**
	 * 
	 * Set the probability associated to the Production p. The probability play a role in the selection
	 * of the Prodution in a Derivation. 
	 * 
	 * The sum of all the probabilities of the Productions that have the same left side symbol must be 1.
	 * 
	 * The method returns a negative if the ID of p doesn´t collide with other ID. If there is a collision, 
	 * then the new probability replace the old_probability, and the method returns the old_probability 
	 * of the Production that collides.
	 * 
	 * Throws a GrammarExceptionImpl if the Production is NULL. 
	 * 
	 * @param p
	 * 			The Production that will have asociated the probability pr.
	 * @param pr
	 * 			The probability to be asociated to the Production p.
	 * 
	 **/
	public double setProbability(Production p, double pr) throws GrammarExceptionImpl;
	
	
	/** 
	 * 
	 * Get the probability associated to the Production p. Returns a negative value if the Production
	 * doesn´t has associated a proabbility value.
	 * 
	 * @param p
	 * 			The Production that is necessary to know its probability associated.
	 * 
	 **/
	public double getProbability(Production p);
	
	
	/** 
	 * 
	 * Checks that the sum of the probabilities of the Productions that have at the left side the
	 * symbol nt, sum 1.
	 * 
	 * Return TRUE if the restriction is fulfilled, FALSE otherwise.
	 * 
	 * @param nt
	 * 			The NonTerminal to be checked.
	 * 
	 **/
	public boolean checkIsFulfilledProbabilityRestriction(NonTerminal nt);
	
	
	/** 
	 * 
	 * Checks that the sum of the probabilities of the Productions that have at the left side the
	 * same NonTerminal symbol, sum 1.
	 * 
	 * Return TRUE if the restriction is fulfilled in the Grammar, FALSE otherwise.
	 * 
	 * In the case that it returns FALSE generates a log with the NonTerminal symbols that doesn´t fulfill
	 * the restriction.
	 * 
	 * 
	 **/
	public boolean checkIsFulfilledProbabilityRestriction();
	
	
	/** 
	 * 
	 * Return a Production object that has the NonTerminal symbol nt at the left side.
	 * 
	 * The Production object is randomly selected with the probabilities associated to the Productions.
	 * 
	 * 
	 * @param nt
	 * 			The NonTerminal that filters the production to be selected.
	 * 
	 **/
	public Production getProduction(NonTerminal nt);
	
}
