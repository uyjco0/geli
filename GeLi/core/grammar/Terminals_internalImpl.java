package core.grammar;


import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Iterator;


/** 
 * 
 * Class that offers a concrete implementation of the interface Terminals.
 * 
 * It does not belong to the Public API.
 * 
 *
 * @author Jorge Couchet.
 * 
 * 
 **/

class Terminals_internalImpl implements Terminals_internal {

	
	/** The holders of the class. */
	private Map<Integer,Terminal> map;
	
	
	
	/**
	 * 
	 * Empty constructor.
	 *  
	 */
	Terminals_internalImpl(){
		this.map = new ConcurrentHashMap<Integer,Terminal>();
	}
	
	
	public Iterator<Terminal> iterator(){
		if(!this.map.isEmpty()){
			return this.map.values().iterator();
		}else{
			   return null;
		}
	}
	
	
	public Collection<Terminal> values(){
		if(!this.map.isEmpty()){
			return this.map.values();
		}else{
			   return null;
		}
	}

	
	public Terminal add(Terminal t) throws GrammarExceptionImpl{
		if((t==null)||(t.getId()<0)||(t.getSymbol()==null)){
			throw new GrammarExceptionImpl("The Terminal is NULL");
		}
		
		return this.map.put(t.getId(), t);
	}
	
	
	public Terminal get(Terminal t){
		Terminal result = null;
		if ((t!=null) && (t.getId()>=0) && (this.map.containsKey(t.getId()))){
			result = this.map.get(t.getId());
		}
		return result;
	}
	
	
	public Terminal get(int t){
		Terminal result = null;
		if ((t>=0) && (this.map.containsKey(t))){
			result = (this.map).get(t);
		}
		return result;
	}
	
	
	public Terminal get(String t){
		Terminal result = null;
		Collection<Terminal> ct;
		Iterator<Terminal> it;
		boolean find = false;
		Terminal tAux;
		
		if ( t != null){
			ct = this.values();
			it = ct.iterator();
			while((it.hasNext()) && (!find)){
				tAux = it.next();
				if(tAux.getSymbol().equals(t)){
					result=tAux ;
					find = true;
				}
			}
		}
		
		return result;
	}


	public void setRelations(Production p, Grammar g)throws GrammarExceptionImpl {
		
		Production p_aux;
		
		if((p!=null) && (p.getId()>=0)){
			for(Terminal t: this.map.values()){
				p_aux=t.setRelation(p, g);
				if(p_aux!=null){
					throw new GrammarExceptionImpl("The old Production that collides is " + p.getSymbol());
				}
			}
		}
	}
	
	
	public boolean isEmpty(){
		return (this.map).isEmpty();
	}
}
