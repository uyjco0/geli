package rules;

/**
 * 
 * Class that implement the interface AntElement, encapsulating (hiding) the methods's implementation.
 * 
 * It belongs to the public API.
 * 
 * @author Jorge Couchet
 * 
 */


import grammarGuidedGeneticProgramming.GggpExceptionImpl;



public class AntElementImpl implements AntElement {

	/**
	 * 
	 * The symbol associated that represents the Antecedent's Element.
	 * 
	 */
	private String symbol;
	
	/**
	 * 
	 * The type of the Antecedent's Element. The type could be:
	 * 
	 * 1) Type = 0: a pure Antecedent's Element (the Elements over which the operators are applied).
	 * 2) Type = 1: a logical mathematical operator ( "==", "!=", "<", "<=", ">", ">=" ).
	 * 3) Type = 2: a logical operator ( "NOT", "AND", "OR" ).
	 * 4) Type = 3: the opening agrupation element: "(" .
	 * 5) Type = 4: the closing agrupation element: ")" .
	 * 
	 */
	private int type;
	
	
	/**
	 * 
	 * Empty constructor.
	 *
	 */
	public AntElementImpl () {
	
		super();
		this.symbol = null;
		this.type = -1;
	}
	
	
	/**
	 * 
	 * Constructor that receives the symbol and the type associated to the Antecedent's Element.
	 * 
	 * @param s
	 * 			The symbol associated to the Antecedent's Element.
	 * 
	 * @param t
	 * 			The type associated to the Antecedent's Element.
	 *
	 */
	public AntElementImpl (String s, int t) {
	
		super();
		this.symbol = s;
		this.type = t;
	}
	
	
	/**
	 * 
	 * Constructor that receives the symbol associated to the Antecedent's Element.
	 * 
	 * @param s
	 * 			The symbol associated to the Antecedent's Element.
	 * 
	 *
	 */
	public AntElementImpl (String s) {
	
		super();
		this.symbol = s;
		
		if(s.equals("(")) {
		
			this.type = 3;
			
		}else {
			
				if(s.equals(")")) {
					
					this.type = 4;
					
				}else{
					
						if(s.equals("==") || s.equals("!=") || s.equals("<") || s.equals("<=") || s.equals(">") || s.equals(">=")) {
							
							this.type = 1;
							
						}else{
							
								if(((s.toUpperCase()).equals("NOT")) || ((s.toUpperCase()).equals("AND")) || ((s.toUpperCase()).equals("OR"))){
									
									this.type = 2;
									
								}else{
										this.type = 0;
								}
							
						}
					
					
				}
			
		}
		
		
	}
	
	
	public String getSymbol() {

		return this.symbol;
		
	}

	
	public int getType() {

		return this.type;
		
	}

	
	public void setSymbol(String s) throws GggpExceptionImpl {

		if (s == null){
			throw new GggpExceptionImpl("The String must be not null");
		}
		
		this.symbol = s;
		
	}

	
	public void setType(int ty) throws GggpExceptionImpl {
		
		if (ty < 0){
			throw new GggpExceptionImpl("The type must be greater than 0");
		}
		
		this.type = ty;

	}

}
