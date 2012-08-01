package core.grammar;


/**
 *     
 *     The inteface that defines a common ancestor to NonTerminal, Terminal and Production objects.
 *      
 *     It belongs to the Public API.
 *     
 *     
 *     @author Jorge Couchet
 *     
 **/

public interface Element {

	
	
	/** 
	 * 
	 * Return the ID of the NonTerminal.
	 * 
	 **/
	public int getId();
	
	
	/** 
	 * 
	 * Set the ID of the NonTerminal.
	 * 
	 * Throws a GrammarExceptionImpl if the ID<0.
	 * 
	 * @param id
	 * 			 The ID to be associated with the NonTerminal.
	 * 
	 **/
	public void setId(int id) throws GrammarExceptionImpl;
	
	/** 
	 * 
	 * Return the symbol of the NonTerminal.
	 * 
	 **/
	public String getSymbol();
	
	
	/** 
	 * 
	 * Set the symbol of the NonTerminal.
	 * 
	 * @param s
	 * 			 The symbol to be associated with the NonTerminal.
	 * 
	 **/
	public void setSymbol(String s);
}
