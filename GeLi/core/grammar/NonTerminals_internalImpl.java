package core.grammar;


import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Iterator;




/** 
 * 
 * Class that offers a concrete implementation of the interface NonTerminals.
 * 
 * It does not belong to the Public API.
 *
 *
 * @author Jorge Couchet.
 * 
 * 
 **/

class NonTerminals_internalImpl implements NonTerminals_internal {

	
	
	/** The holders of the class. */
	private Map<Integer, NonTerminal> map;
	
	
	
	/**
	 * 
	 * Empty constructor.
	 *  
	 */
	NonTerminals_internalImpl(){
		this.map = new ConcurrentHashMap<Integer,NonTerminal>();
	}
	
		
	public Iterator<NonTerminal> iterator(){
		if(!this.map.isEmpty()){
			return this.map.values().iterator();
		}else{
			return null;
		}
	}
	
	
	public Collection<NonTerminal> values(){
		if(!this.map.isEmpty()){
			return this.map.values();
		}else{
				return null;
		}
	}

	
	public NonTerminal add(NonTerminal nt) throws GrammarExceptionImpl{
		
		if((nt==null)||(nt.getId()<0)||(nt.getSymbol()==null)){
			throw new GrammarExceptionImpl("The NonTerminal is NULL");
		}
		
		return this.map.put(nt.getId(), nt);
	}
	
	
	public NonTerminal get(NonTerminal nt){
		
		NonTerminal result = null;
		
		if ((nt!=null) && (nt.getId()>=0) && this.map.containsKey(nt.getId())){
			result= this.map.get(nt.getId());
		}
		
		return result;
	}
	
	
	public NonTerminal get(int nt){
		
		NonTerminal result = null;
		
		if ( (nt>=0) && this.map.containsKey(nt)){
			result= this.map.get(nt);
		}
		
		return result;
	}
	
	
	public NonTerminal get(String nt){
		
		NonTerminal result = null;
		Collection<NonTerminal> cnt;
		Iterator<NonTerminal> it;
		boolean find = false;
		NonTerminal ntAux;
		
		if ( nt != null){
			cnt = this.values();
			it = cnt.iterator();
			while((it.hasNext()) && (!find)){
				ntAux = it.next();
				if(ntAux.getSymbol().equals(nt)){
					result=ntAux ;
					find = true;
				}
			}
		}
		
		return result;
	}


	public void setRelations(Production p, Grammar g) throws GrammarExceptionImpl {
		
		Production p_aux;
		
		if((p!=null) && (p.getId()>=0)){
			
			for(NonTerminal nt: this.map.values()){
				
				p_aux=nt.setRelation(p, g);
				
				if(p_aux!=null){
					throw new GrammarExceptionImpl("The old Production that collides is " + p.getSymbol());
				}
				
			}
		}
		
	}
	
	
	public boolean isEmpty(){
		return(this.map).isEmpty();
	}
}
