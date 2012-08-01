package core.grammar;

/**
 * 
 *  Class that implements a custom Exception in order to handle the exceptions that can appear 
 *  derivating a Production in the Grammar with a restriction of Max depth..
 *  
 *  It belongs to the Public API.
 *  
 *  @author Jorge Couchet
 * 
 * 
 * */

public class GrammarMaxDepthExceptionImpl extends  Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GrammarMaxDepthExceptionImpl(String s){
		super(s);
	}
	
}
