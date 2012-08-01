package rules;

/**
 * 
 * Inteface that declares the methods that define the responsabilities of an Antecedent's Element object.
 *      
 * It belongs to the Public API.
 * 
 * @author Jorge Couchet
 * 
 */

import grammarGuidedGeneticProgramming.GggpExceptionImpl;


public interface AntElement {
	
	/** 
	 * 
	 * Return the type of an Antecedent's Element.
	 * 
	 * The type could be:
	 * 
	 * 1) Type = 0: a pure Antecedent's Element (the Elements over which the operators are applied).
	 * 2) Type = 1: a logical mathematical operator ( "==", "!=", "<", "<=", ">", ">=" ).
	 * 3) Type = 2: a logical operator ( "NOT", "AND", "OR" ).
	 * 4) Type = 3: the opening agrupation element: "(" .
	 * 5) Type = 4: the closing agrupation element: ")" .
	 * 
	 **/
	public int getType();
	
	
	/** 
	 * 
	 * Set the the type of an Antecedent's Element.
	 * 
	 * Throws a GggpExceptionImpl if the ty<0.
	 * 
	 * @param ty
	 * 			 The type to be associated with the Antecedent's Element.
	 * 
	 **/
	public void setType(int ty) throws GggpExceptionImpl;
	
	
	/** 
	 * 
	 * Return the symbol associated to the Antecedent's Element.
	 * 
	 * The symbol represents the Antecedent's Element.
	 * 
	 **/
	public String getSymbol();
	
	
	/** 
	 * 
	 * Set the symbol associated to the Antecedent's Element.
	 * 
	 * Throws a GggpExceptionImpl if s == NULL.
	 * 
	 * @param s
	 * 			 The symbol to be associated with the Antecedent's Element.
	 * 
	 **/
	public void setSymbol(String s) throws GggpExceptionImpl;

}
