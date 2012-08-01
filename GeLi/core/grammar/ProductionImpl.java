package core.grammar;

import java.util.Collection;
import java.util.LinkedList;


/** 
 * 
 * Class that offers a concrete implementation of the interface Production.
 * 
 * It belongs to the Public API.
 *
 * @author Jorge Couchet.
 * 
 * 
 **/

public class ProductionImpl extends ElementImpl implements Production{
	
	
	/** Holder for the NonTerminal symbol at the left side of the Production. */
	private int left;
	
	
	/** Holder for the NonTerminal and Terminal symbols at the right side of the Production. 
	 *  The position of a symbol in the array is the position of the symbol in the right side
	 *  of the Production. */
	private int [] right;
	
	/** Is TRUE if there are symbols in the right structure, FALSE otherwise*/
	private boolean hasRight;
	
	
	
	/**
	 * 
	 * Constructor that receives the amount of symbols of the right side of the Production.
	 * 
	 * Throws a GrammarExceptionImpl if  l<=0. 
	 *  
	 * @param l
	 * 		    The amount of symbols of the right side of the Production.
	 *   
	 *  @param id
	 *  			The ID of the Production.
	 *  
	 *  @param symbol
	 *  				The symbol associated to the Production.
	 * 
	 * * 
	 * 
	 **/
	public ProductionImpl(int l, int id, String symbol) throws GrammarExceptionImpl{
		
		super(id, symbol);
		
		int pos = 0;
		this.left = -1;
		
		
		
		if(l>0){
			this.right = new int[l];
		}else{
			   throw new GrammarExceptionImpl("The Left symbol is NULL");
		}
		
		while (pos<this.right.length){
			this.right[pos]=-1;
			pos++;
		}
		
		this.hasRight=false;
	}
	
	
	/**
	 * 
	 * Constructor that receives the NonTerminal symbol of the left side of the Production,
	 * and the amount of symbols of the right side of the Production.
	 * 
	 * Throws a GrammarExceptionImpl if the left symbol is NULL, or l<=0.
	 * 
	 * @param left
	 * 			   The NonTerminal symbol of the left side of the Production.
	 * 
	 * @param l
	 * 		    The amount of symbols of the right side of the Production.
	 * 
	 *  
	 *  @param id
	 *  			The ID of the Production.
	 *  
	 *  @param symbol
	 *  				The symbol associated to the Production.
	 * 
	 * * 
	 * 
	 **/
	public ProductionImpl(NonTerminal left, int l, int id, String symbol) throws GrammarExceptionImpl{
		
		super(id, symbol);
		
		int pos = 0;
		
		if((left!=null) && (left.getId()>=0)){
			this.left= left.getId();
		}else{
			   throw new GrammarExceptionImpl("The Left symbol is NULL");
		}
		
		if(l>0){
			this.right = new int[l];
		}else{
			   throw new GrammarExceptionImpl("The Production must have one element at least");
		}
		
		while (pos<this.right.length){
			this.right[pos]=-1;
			pos++;
		}
		
		this.hasRight = false;
	}
	
	
	public void clearProduction(){
		
		this.setSymbol(null);
		
	}
	
	@SuppressWarnings("unchecked")
	public boolean addRightElement(Element e, int pos){
		boolean result = true;
		Class c = this.getClass();
		
		/* Checks that it doesn´t insert a Production as a symbol at the right side. */
		if(c.isInstance(e)){
			return false;
		}
		
		if((pos>=0) && (pos<=this.right.length) && (e != null) && (e.getId()>=0) && (e.getSymbol()!=null)){
			this.right[pos-1]= e.getId();
			this.hasRight = true;
		}else{
			result = false;
		}
		
		return result;
	}
	
	
	public NonTerminal getLeft(Grammar g){
		
		return  g.getNonTerminal(this.left);
	}
	
	
	public void setLeft(NonTerminal nt) throws GrammarExceptionImpl{
		
		if((nt!=null) && (nt.getId()>=0)){
			this.left= nt.getId();
		}else{
				throw new GrammarExceptionImpl("The NonTerminal is NULL");
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public Collection<NonTerminal> getNonTerminalsRight(Grammar g) throws GrammarExceptionImpl{
		
		NonTerminal aux = new NonTerminalImpl(0, "");
		Element e;
		Collection<NonTerminal> co = null;
		boolean first = true;
		co = null;
		int pos = 0;
		
		if(!this.hasRight){
			return null;
		}
		
		/* Obtain the NonTerminal Class*/
		Class c = aux.getClass();
		
		
		/* Iterates until cover all the array */
		while ( pos < (this.right).length ){
			/* Check that the class of the object is NonTerminal */
			if(this.right[pos]>0){
				if(first){
					co = new LinkedList<NonTerminal>();
					first = false;
				}
				
				e = g.getElement(this.right[pos]);
				
				if(c.isInstance(e)){
					if(!co.add((NonTerminal)e)){
						throw new GrammarExceptionImpl("It isn´t possible to add the NonTerminal to the Collection");
					}
				}
			}
			pos++;
		}
		
		return co;
	}


	@SuppressWarnings("unchecked")
	public Collection<Terminal> getTerminalsRight(Grammar g) throws GrammarExceptionImpl {
		
		Collection<Terminal> co = null;
		Element e;
		boolean first = true;
		co = null;
		Terminal t;
		int pos = 0;
		
		if(!this.hasRight){
			return null;
		}
		
		t = new TerminalImpl(0, "Dummy");
		
		/* Obtain the NonTerminal Class*/
		Class c = t.getClass();
		
		
		/* Iterates until cover all the array */
		while (pos<this.right.length){
			
			/* Check that the class of the object is Terminal */
			if(this.right[pos]> 0){
				if (first){
					co = new LinkedList<Terminal>();
					first = false;
				}
				
				e = g.getElement(this.right[pos]);
				
				if(c.isInstance(e)){
				
					if(!co.add((Terminal)e)){
						throw new GrammarExceptionImpl("It isn´t possible to add the Terminal to the Collection");
					}
				}
			}
			pos++;
		}
		
		return co;
	}

	
	@SuppressWarnings("unchecked")
	public Collection<Element> getRights(Grammar g) throws GrammarExceptionImpl{
		
		LinkedList<Element> l = null;
		Element e;
		int pos =0;
		boolean first = true;
		Terminal t = new TerminalImpl(0,"");
		NonTerminal nt = new NonTerminalImpl(0,"");
		Class c1;
		Class c2;
		
		c1 = t.getClass();
		c2 = nt.getClass();
		
		if(!this.hasRight){
			return null;
		}
		
		while (pos<this.right.length){
			if(this.right[pos] > 0){
				if(first){
					l = new LinkedList<Element>();
					first = false;
				}
				
				e = g.getElement(this.right[pos]);
				
				if(c1.isInstance(e) || c2.isInstance(e)){
					if(!l.add(e)){
						throw new GrammarExceptionImpl("It isn´t possible to add the Element to the Collection");
					}
				}else{
						throw new GrammarExceptionImpl("There is a Production at the Production right side.");
				}
				
			}else{
				throw new GrammarExceptionImpl("There are NULL elements at the right side of the Production");
			}
			pos++;
		}
		return l;
	}

	
	@SuppressWarnings("unchecked")
	public Collection<Integer> getRightPosition(Element e) throws GrammarExceptionImpl {
		
		Collection<Integer> result = null;
		boolean isFirst = true;
		Class c = this.getClass();
		int pos = 0;
		
		/* Checks that e is not null and isn´t a Production object. */
		if(e==null || c.isInstance(e)){
			return null;
		}
		
		/* Iterates until cover all the array */
		while (pos<this.right.length){
			
			/* Check that the ID's agree*/
			if((this.right[pos]> 0) && (this.right[pos] == e.getId())){
				if(isFirst){
					result = new LinkedList<Integer>();
					isFirst = false;
				}
				/* Adds one to convert to the absolute position starting at 1 */
				if(!result.add(pos+1)){
					throw new GrammarExceptionImpl("It isn´t possible to add the position to the Collection");
				}
				
			}
			pos++;
		}
		return result;
	}

	
	public Production cloneProduction() throws GrammarExceptionImpl{
		
		Production res;
		
		res = new ProductionImpl((this.right).length, this.getId(), new String(this.getSymbol()));
		
		((ProductionImpl)res).hasRight = this.hasRight;
		
		((ProductionImpl)res).left = this.left;
		
		for(int i =0; i < (this.right).length; i++){
			
			((ProductionImpl)res).right[i] = (this.right)[i];
			
		}
		
		return res;
	}
	
}
