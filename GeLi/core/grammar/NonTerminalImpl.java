package core.grammar;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



/** 
 * 
 * Class that offers a concrete implementation of the interface NonTerminal.
 * 
 * It belongs to the Public API.
 * 
 *
 * @author Jorge Couchet.
 * 
 * 
 **/

public class NonTerminalImpl extends ElementImpl implements NonTerminal {

	
	
	/** The holder for the Productions that the NonTerminal appears at the left side */
	private Map<Integer,Integer> leftMap;
	
	/** The holder for the Productions that the NonTerminal appears at the right side */
	private Map<Integer,Integer> rightMap;
	
	
	/**
	 * 
	 *  Constructor that receives de ID and the Symbol.
	 *  
	 *  @param id
	 *  			The ID of the NonTerminal.
	 *  
	 *  @param symbol
	 *  				The symbol associated to the NonTerminal.
	 * 
	 **/
	public NonTerminalImpl(int id, String symbol) throws GrammarExceptionImpl{
		super(id, symbol);
		this.leftMap = new ConcurrentHashMap<Integer,Integer>();
		this.rightMap = new ConcurrentHashMap<Integer,Integer>();
	}
	
	
	public void clearNonTerminal(){
		this.leftMap.clear();
		this.leftMap = null;
		this.rightMap.clear();
		this.rightMap = null;
		this.setSymbol(null);
	}
	
	
	public Collection<Production> getAllLeftsProd(Grammar g) {
		
		Iterator<Integer> it;
		Collection<Production> cp;
		Production p;
		
		if (((this.leftMap).values()).isEmpty()){
			return null;
		}else{
				cp = new LinkedList<Production>();
				it = this.leftMap.values().iterator();
				while(it.hasNext()){
					p = g.getProduction(it.next());
					cp.add(p);
				}
				
				return cp;
		}
	}

	
	public Collection<Production> getAllRightsProd(Grammar g) {
		
		Iterator<Integer> it;
		Collection<Production> cp;
		Production p;
		
		if(this.rightMap.values().isEmpty()){
			return null;
		}else{
				
				cp = new LinkedList<Production>();
				it = this.rightMap.values().iterator();
				while(it.hasNext()){
					p = g.getProduction(it.next());
					cp.add(p);
				}
			
				return cp;
		}
	}

	
	public Production getLeftProd(Production p, Grammar g) {
		
		Production result = null;
		
		if(this.leftMap.isEmpty()){
			return null;
		}
		
		if ((p!=null) && (this.leftMap.containsKey(p.getId()))){
			result = g.getProduction(this.leftMap.get(p.getId()));
		}
		return result;
	}

	
	public Production getRightProd(Production p, Grammar g) {
		
		Production result = null;
		
		if(this.rightMap.isEmpty()){
			return null;
		}
		
		if ((p!=null) && (this.rightMap.containsKey(p.getId()))){
			result = g.getProduction(this.rightMap.get(p.getId()));
		}
		
		return result;
	}

	
	public Production setRelation(Production p, Grammar g) throws GrammarExceptionImpl {
		
		@SuppressWarnings("unused")
		Integer result;
		Production res = null;
		Collection<Integer> pos;
		NonTerminal nt = null;
		
		if((p==null) || (p.getId()<0) || (p.getSymbol()==null)){
			throw new GrammarExceptionImpl("The production is NULL");
		}
		
		/* Obtain the position of the NonTerminal symbol at the right side of the production, if
		 * the NonTerminal isn´t at this side, then pos is NULL*/
		
		pos = p.getRightPosition(this);
		
		if (pos!= null){
			
			result = (this.rightMap).put(new Integer(p.getId()), new Integer(p.getId()));
			
			if(result!=null){
				res = p;
			}
			
		}
		
		/*Obtain the left side symbol of the production, and compares it against THIS symbol*/
		
		nt = p.getLeft(g);
		
		if((nt != null)&& (nt.getId()==this.getId())){
			
			result = this.leftMap.put(new Integer(p.getId()), new Integer(p.getId()));
			
			if(result!=null){
				res = p;
			}
		}
		
		return res;
		
	}
	
	
	public NonTerminal cloneNonTerminal() throws GrammarExceptionImpl
	{
		NonTerminal aux;
		Set<Integer> keys;
		Iterator<Integer> it;
		Integer i;
		
		aux = new NonTerminalImpl(this.getId(), new String(this.getSymbol()));
		
		keys = (this.leftMap).keySet();
		it = keys.iterator();
		((NonTerminalImpl)aux).leftMap = new ConcurrentHashMap<Integer,Integer>();
		
		while(it.hasNext()){
			i = it.next();
			(((NonTerminalImpl)aux).leftMap).put(new Integer(i), new Integer(new Integer(i)));
		}
		
		
		keys = (this.rightMap).keySet();
		it = keys.iterator();
		((NonTerminalImpl)aux).rightMap = new ConcurrentHashMap<Integer,Integer>();
		
		while(it.hasNext()){
			i = it.next();
			(((NonTerminalImpl)aux).rightMap).put(new Integer(i), new Integer(new Integer(i)));
		}		
		
		return aux;
	}
	

}
