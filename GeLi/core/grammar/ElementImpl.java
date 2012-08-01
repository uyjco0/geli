package core.grammar;


/** 
 * 
 * Class that offers a concrete implementation of the interface Element.
 * 
 * It belongs to the Public API.
 *
 * @author Jorge Couchet.
 * 
 * 
 **/

public class ElementImpl implements Element {

	
	
	/** The holder of the ID associated with the Element. */
	private int id;
	
	/** The holder of the symbols associated with the Element. */
	private String symbol;
	
	
	/**
	 * 
	 *  Constructor that receives de ID and the Symbol.
	 *  
	 *  @param id
	 *  			The ID of the Element.
	 *  
	 *  @param symbol
	 *  				The symbol associated to the Element.
	 * 
	 * */
	public ElementImpl(int id, String symbol) throws GrammarExceptionImpl{
		if(id<0 || symbol==null){
			throw new GrammarExceptionImpl("The Id is smaller than zero, or the symbol is NULL.");
		}
		this.id = id;
		this.symbol = symbol;
	}
	
	public int getId(){
		
		return this.id;
	}
	
	
	public void setId(int id) throws GrammarExceptionImpl{
		if(id>=0){
			this.id = id;
		}else{
			   throw new GrammarExceptionImpl("The ID should be greater or equal than 0");
		}
	}

	
	public String getSymbol() {
		return this.symbol;
	}

	
	public void setSymbol(String s) {
		
		this.symbol = s;
		
	}
}
