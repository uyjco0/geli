package core.grammar;

/**
 * 
 *  Test the methods in the Sort class.
 *  
 *  Non Public API
 *  
 * 
 * */


import org.junit.Test;
import java.util.LinkedList;
import util.random.Sort;

public class Test0_internalImpl {

	
	/* Checks the method sortElement. */
	/* IT IS NECESSARY TO PUT A PRINTLN OF THE RAND VALUE IN THE METHOD sortElement IN ORDER TO
	 * CHECK THAT THE METHOD IS WORKING FINE. */
	@Test(timeout=100)
	public void sortElementTest(){
		
		String res;
		LinkedList<String> c1 = new LinkedList<String>();
		LinkedList<Double> c2 = new LinkedList<Double>();
		Sort<String> s = new Sort<String>();
				
		c1.add("A");
		c2.add(new Double(0.2));
		
		c1.add("B");
		c2.add(new Double(0.3));
		
		c1.add("C");
		c2.add(new Double(0.4));
		
		c1.add("D");
		c2.add(new Double(0.1));
		
		res = s.sortElement(c1, c2);
		System.out.println(res);
		
		res = s.sortElement(c1, c2);
		System.out.println(res);
		
		res = s.sortElement(c1, c2);
		System.out.println(res);
		
		res = s.sortElement(c1, c2);
		System.out.println(res);
		
	}
}
