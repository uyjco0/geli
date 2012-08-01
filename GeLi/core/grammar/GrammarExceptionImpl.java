package core.grammar;

/**
 * 
 *  Class that implements a custom Exception in order to handle the exceptions that can appear processing
 *  the Grammar.
 *  
 *  It belongs to the Public API.
 *  
 *  @author Jorge Couchet
 * 
 * 
 * */

public class GrammarExceptionImpl extends  Exception{

	
	private static final long serialVersionUID = 1L;

	
	public GrammarExceptionImpl(String s){
		super(s);
	}
	
}
