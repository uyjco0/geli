package core.grammar;


/** 
 * 
 * Interface that defines the methods that are required to create the Grammar (load from a file or
 * a interactive creation).
 * 
 * The interface encapsulates (hides) from the Grammar the methods used for the creation of the Grammar.
 * 
 * The object that implements this interface is called from the constructor of the Grammar, the 
 * specific implementation is selected from a Configuration File, from a Parameter or using another 
 * method.
 * 
 * It belongs to the Public API.
 * 
 * @author Jorge Couchet.
 * 
 * 
 **/

public interface CreateGrammar {
	
	
	
	/**
	 * 
	 * Loads the Grammar from a file.
	 * 
	 * Throws a GrammarExceptionImpl if there was a problem loading the Grammar.
	 * 
	 * 
	 **/
	public void loadGrammar() throws GrammarExceptionImpl;
	
	
	/**
	 * 
	 * Loads the Grammar from a file.
	 * 
	 * Throws a GrammarExceptionImpl if there was a problem loading the Grammar.
	 * 
	 * @param s
	 * 			The path and the name of the file.
	 * 
	 * 
	 * 
	 **/
	public void loadGrammar(String s) throws GrammarExceptionImpl;
	
	
	/**
	 * 
	 * Presents a GUI that allows to the user create the Grammar in an interactive manner.
	 * 
	 * 
	 * 
	 **/
	public void createInteractiveGrammar();
	
	
	/**
	 * 
	 * Returns the loaded Grammar.
	 * 
	 * 
	 **/
	public Grammar getGrammar();
	
	

}
