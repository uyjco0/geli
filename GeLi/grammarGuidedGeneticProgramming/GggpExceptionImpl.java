package grammarGuidedGeneticProgramming;

/**
 * 
 *  Class that implements a custom Exception in order to handle the exceptions that can appear processing
 *  the GGGP System.
 *  
 *  It belongs to the Public API.
 *  
 *  @author Jorge Couchet
 * 
 * 
 * */

public class GggpExceptionImpl extends  Exception {

	
	private static final long serialVersionUID = 1L;

	
	public GggpExceptionImpl(String s){
		super(s);
	}
	
}
