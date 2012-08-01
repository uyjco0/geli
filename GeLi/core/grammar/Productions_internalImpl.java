package core.grammar;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


import java.util.Iterator;
import java.util.Collection;


/** 
 * 
 * Class that offers a concrete implementation of the interface Productions.
 * 
 * It does not belong to the Public API.
 * 
 * 
 * @author Jorge Couchet.
 * 
 * 
 **/

class Productions_internalImpl implements Productions_internal {

	
	
	/** The holders of the class. */
	private Map<Integer,Production> map;
	
	
	
	/**
	 * 
	 * Empty constructor.
	 *  
	 */
	public Productions_internalImpl(){
		this.map = new ConcurrentHashMap<Integer,Production>();
	}
	
	
	public Iterator<Production> iterator(){
		if(!this.map.isEmpty()){
			return this.map.values().iterator();
		}else{
				return null;
		}
	}
	
	
	public Collection<Production> values(){
		if(!this.map.isEmpty()){
			return this.map.values();
		}else{
				return null;
		}
	}

	
	public Production add(Production p) throws GrammarExceptionImpl{
		if((p==null)||(p.getId()<0)||(p.getSymbol()==null)){
			throw new GrammarExceptionImpl("The Production is NULL");
		}
		
		return (this.map).put(p.getId(), p);
	}
	
	
	public Production get(Production p){
		Production result = null;
		if ((p!=null) && (p.getId()>=0) && this.map.containsKey(p.getId())){
			result= this.map.get(p.getId());
		}
		return result;
	}
	
	
	public Production get(int p){
		Production result = null;
		if ((p>=0) && (this.map).containsKey(p)){
			result= (this.map).get(p);
		}
		return result;
	}
	
	
	public Production get(String p){
		Production result = null;
		Collection<Production> cp;
		Iterator<Production> it;
		boolean find = false;
		Production pAux;
		
		if ( p != null){
			cp = this.values();
			it = cp.iterator();
			while((it.hasNext()) && (!find)){
				pAux = it.next();
				if(pAux.getSymbol().equals(p)){
					result=pAux ;
					find = true;
				}
			}
		}
		
		return result;
	}	
	
	public boolean isEmpty(){
		return(this.map).isEmpty();
	}
}
