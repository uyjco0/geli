package core.grammar;

/**
 * 
 * 
 * @author Jorge Couchet
 * 
 */

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class ParseDerivationImpl implements ParseDerivation {

	
	
	/**
	 * 
	 * Empty constructor.
	 *
	 */
	public ParseDerivationImpl(){
		
		super();
		
	}
	
	
	public Collection<Element> deleteFirstAndLast(Collection<Element> cl) {
		
		Collection<Element> res = null;
		Iterator<Element> it;
		Element e;
		
		if ( cl != null){
			
			res = new LinkedList<Element>();
			it = cl.iterator();
			
			/* The first Element isn't inserted in the result. */
			e = it.next();
			
			while(it.hasNext()){
				
				e = it.next();
				/* If it hasn't a next Element, then is the last Element and isn't inserted. */
				if(it.hasNext()){
					res.add(e);
				}
			}
			
			if (res.isEmpty()){
				res = null;
			}
		}
		
		return res;
	}

		
	public Collection<Collection<Element>> splitElementsBeforeToken(Collection<Element> cl, String token){
		
		Collection<Collection<Element>> res = null;
		Iterator<Element> it;
		Collection<Element> aux;
		Element e;
		boolean first;
		boolean isToken;
		
		if((cl != null)&& (token != null)){
			
			token = token.toUpperCase();
			
			res = new LinkedList<Collection<Element>>();
			it = cl.iterator();
			
			first = true;
			while(it.hasNext()){
				
				
				aux = new LinkedList<Element>();
				isToken = false;
				while ((!isToken) && (it.hasNext())){
					e = it.next();
					if(((e.getSymbol()).toUpperCase()).equals(token)){
						isToken = true;
					}else{
						
							aux.add(e);
					}
				}
				
				/* e is the last Element used, and it must be the token in order to insert the subcollection */
				if((isToken) || (!first) ){
					res.add(aux);
				}
				
				if(first){
					first = false;
				}
				
			}
			
			if(res.isEmpty()){
				res = null;
			}
			
		}
		
		return res;
	}

	
	public Collection<Collection<Collection<Element>>> splitElementsBetweenTokens(Collection<Element> cl, 
			                String tk1, String tk2){
		
		LinkedList<Collection<Collection<Element>>> res = null;
		Iterator<Element> it;
		LinkedList<Collection<Element>> aux;
		LinkedList<Element> aux1;
		LinkedList<Element> aux2;
		Element e;
		boolean isToken;
		boolean isToken2;
		
		if((cl != null)&& (tk1 != null) && (tk2 != null)){
			
			tk1 = tk1.toUpperCase();
			tk2 = tk2.toUpperCase();
			
			res = new LinkedList<Collection<Collection <Element>>>();
			
			it = cl.iterator();
			
			isToken = false;
			while ((!isToken) && (it.hasNext())){
				e = it.next();
				if(((e.getSymbol()).toUpperCase()).equals(tk1)){
					isToken = true;
				}
			}
			
			while ( it.hasNext()){
				
				aux = new LinkedList<Collection<Element>>();
				aux1 = new LinkedList<Element>();
				aux2 = new LinkedList<Element>();
				
				if(it.hasNext()){
					
					isToken2 = false;
					while ((!isToken2) && (it.hasNext())){
						e = it.next();
						if(((e.getSymbol()).toUpperCase()).equals(tk2)){
							isToken2 = true;
						}else{
							aux1.add(e);
						}
					
					}
					
					if(it.hasNext()){
				
						isToken = false;
						while ((!isToken) && (it.hasNext())){
							e = it.next();
							if(((e.getSymbol()).toUpperCase()).equals(tk1)){
								isToken = true;
							}else{
								aux2.add(e);
							}
						}
					}else {
							aux2 = new LinkedList<Element>();
					}
					
					aux = new LinkedList<Collection<Element>>();
					aux.add(aux1);
					aux.add(aux2);
					res.add(aux);
					
				}
			}
			
			if(res.isEmpty()){
				res = null;
			}
			
		}
		
		return res;
	}

}
