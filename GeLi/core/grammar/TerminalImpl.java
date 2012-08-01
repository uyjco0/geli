package core.grammar;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/** 
 * 
 * Class that offers a concrete implementation of the interface Terminal.
 * 
 * It does not belong to the Public API.
 * 
 *
 * @author Jorge Couchet.
 * 
 * 
 **/

public class TerminalImpl extends ElementImpl implements Terminal {

	
	
	/** The holder for the Productions that the NonTerminal appears at the right side */
	private Map<Integer,Integer> rightMap;
	
	
	
	/**
	 * 
	 *  Constructor that receives the ID and the Symbol.
	 *  
	 *  @param id
	 *  			The ID of the Terminal.
	 *  
	 *  @param symbol
	 *  				The symbol associated to the Terminal.
	 * 
	 **/
	public TerminalImpl(int id, String symbol) throws GrammarExceptionImpl{
		super(id, symbol);
		this.rightMap = new ConcurrentHashMap<Integer,Integer>();
	}
	
	
	public void clearTerminal(){
		
		this.rightMap.clear();
		this.rightMap = null;
		this.setSymbol(null);
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

	
	public Production getRightProd(Production p, Grammar g) {
		
		Production result = null;
		
		if(this.rightMap.isEmpty()){
			return null;
		}
		
		if((p!=null) && (this.rightMap.containsKey(p.getId()))){
			result = g.getProduction(this.rightMap.get(p.getId()));
		}
		return result;
	}

	
	public Production setRelation(Production p, Grammar g) throws GrammarExceptionImpl {
		
		@SuppressWarnings("unused")
		Integer result;
		Production res = null;
		Collection<Integer> pos;
		
		if((p==null) || (p.getId()<0) || (p.getSymbol()==null)){
			throw new GrammarExceptionImpl("The Production is NULL");
		}
		
		/* Obtain the position of the NonTerminal symbol at the right side of the production, if
		 * the NonTerminal isn´t at this side, then pos is negative*/
		pos = p.getRightPosition(this);
		if (pos!=null){
			result = this.rightMap.put(new Integer(p.getId()), new Integer(p.getId()));
			if(result!=null){
				res = p;
			}
		}
		return res;
	}

	
	public Terminal cloneTerminal() throws GrammarExceptionImpl
	{
		Terminal aux;
		Set<Integer> keys;
		Iterator<Integer> it;
		Integer i;
		
		aux = new TerminalImpl(this.getId(), new String(this.getSymbol()));
		
		keys = (this.rightMap).keySet();
		it = keys.iterator();
		((TerminalImpl)aux).rightMap = new ConcurrentHashMap<Integer,Integer>();
		
		while(it.hasNext()){
			i = it.next();
			(((TerminalImpl)aux).rightMap).put(new Integer(i), new Integer(new Integer(i)));
		}
		
		return aux;
	}
	
	

}
