package util.random;

/** 
 * 
 * Class that define several randoms methods. 
 *
 * 
 * It belongs to the Public API.
 * 
 * @author Jorge Couchet.
 * 
 * 
 **/


import java.util.Collection;
import java.util.Iterator;

public class Sort <T> {

	public Sort(){
		super();
	}
	
	
	/**
	 * 
	 * Receives a Collection c1 of elements T and a Collection c2 with distribution of probability of 
	 * these elements.  Returns a random element of c1 following the distribution in c2.
	 * 
	 * 
	 * Returns NULL if c1 or c2 are NULL, or the length of c1 and c2 isn´t equal.
	 * 
	 *  @param c1
	 *  			The Collection that has the elements to choice one in a random way.
	 *  
	 *  @param c2
	 *  			The Collection that has the distribution of probability associated to the elements
	 *  			of c1. The probabilities that c2 holds must sum one (but it isn´t controlled).
	 * 
	 * 
	 * */
	public T sortElement(Collection<T> c1, Collection<Double> c2){
		T res = null;
		T el;
		int size1;
		int size2;
		double rand;
		double pos;
		double pos_before;
		Double d;
		Iterator<T> i1;
		Iterator<Double> i2;
		
		if((c1==null) || (c2==null)){
			return res;
		}else{
			   size1 = c1.size();
			   if(size1 == 1){
				   i1 = c1.iterator();
				   res = i1.next();
				   return res;
			   }
			   size2 = c2.size();
			   if(size1 != size2){
				   return res;
			   }
		}
		
		rand = Math.random();
		
		pos = 0;
		i1 = c1.iterator();
		i2 = c2.iterator();
		while (i1.hasNext()){
			el = i1.next();
			d = i2.next();
			pos_before = pos;
			pos = pos + d.doubleValue();
			if((pos_before<=rand)&&(rand<=pos)){
				res = el;
				break;
			}	
		}
		
		return res;
	}
}
