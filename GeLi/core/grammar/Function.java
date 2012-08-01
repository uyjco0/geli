package core.grammar;



/** 
 * 
 * Interface that defines the methods that are required to create a function to be associated to the Derivation's nodes
 * and applied over the Grammar symbols
 * 
 * The interface encapsulates (hides) from the Grammar the several kind of functions to be implemented.
 * 
 * It belongs to the Public API.
 * 
 * @author Jorge Couchet.
 * 
 * 
 **/

public interface Function {
	
	
	
	/**
	 * 
	 * 
	 * Apply with probability p the function over the value and returns the result.
	 * 
	 * 
	 * @param value
	 * 			The value over which the function is applied.
	 * 
	 * @param p
	 * 			The probability used to decide to apply the function or not.
	 * 
	 * 
	 **/
	public Double applyFunction(Double value, double p);
	
	
	/**
	 *
	 * 
	 * Changes (with probability p) the function for a new function and return the new one.
	 * 
	 * 
	 **/
	public Function updateFunction(double p);
	
	
	/**
	 *
	 * 
	 * Creates and returns a new function.
	 * 
	 * 
	 **/
	public Function createFunction();
	
	
	/**
	 *
	 * 
	 * Returns the operator associated to the function.
	 * 
	 * 
	 **/
	public String getOperator();
	
	
	/**
	 *
	 * 
	 * Returns the increment associated to the function.
	 * 
	 * 
	 **/
	public double getIncrement();
	
	
	/**
	 * 
	 * Clone the Functionr, returning a new one.
	 * 
	 */
	Function cloneFunction();
	
	
	/**
	 * 
	 * Clear the structures used by the Function Object.
	 * 
	 */
	public void clearFunction(); 
	

}
