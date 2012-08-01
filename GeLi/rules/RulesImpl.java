package rules;


/**
 * 
 * Class that implement the interface Rules, encapsulating (hidding) the implementation of his methods.
 * 
 * 
 * It belongs to the public API.
 * 
 * @author Jorge Couchet
 *
 */


import java.util.Collection;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import grammarGuidedGeneticProgramming.GggpExceptionImpl;


public class RulesImpl implements Rules {
	
	
	/**
	 * Holds the rule's ANTECEDENT 
	 */
	private List<AntElement> antecedent;
	
	/**
	 * Holds the rule's CONSECUENT.
	 */
	private String consecuent;
	
	
	/**
	 * 
	 * Empty constructor.
	 * 
	 */
	public RulesImpl () {
		
		super();
		this.antecedent = null;
		this.consecuent = null;
	}
	
	
	/**
	 * 
	 * Constructor that receives the ANTECEDENT and CONSECUENT.
	 * 
	 */
	public RulesImpl (List<AntElement> a, String c) {
		
		super();
		this.antecedent = a;
		this.consecuent = c;
	}
	
	
	public boolean evaluatesRule (Map<String, Double> rd, List<AntElement> antecedent) throws GggpExceptionImpl{
		
		boolean res = false;
		boolean res1;
		boolean thereIsNot = false;;
		AntElement [] e = new AntElement [3];
		AntElement ae;
		List<AntElement> ant1;
		List<AntElement> ant2;
		Iterator<AntElement> it;
		Map<String, Integer> mp;
		int i;
		int op;
		int countOpen;
		int countClose;
		
		/** The simplest cases. */
		if((antecedent.size() == 5) || (antecedent.size() == 10) ){
			
			/** Delete the first level "(" and ")". */
			antecedent = this.deleteFirstAndLast(antecedent);
			
			if(antecedent.size() == 8) {
				
				it = antecedent.iterator();
				ae = it.next();
				if(!(((ae.getSymbol()).toUpperCase()).equals("NOT"))){
					throw new GggpExceptionImpl("The syntaxis of the rule's ANTECEDENT is wrong");
				}
				
				/** Delete the "NOT" symbol. */
				antecedent = this.deleteFirst(antecedent);
				
				thereIsNot = true;
				
				/** Delete the second level "(" and ")". */
				antecedent = this.deleteFirstAndLast(antecedent);
				/** Delete the third level "(" and ")". */
				antecedent = this.deleteFirstAndLast(antecedent);
			}
			
			it = antecedent.iterator();
			i = 0;
			while(it.hasNext()){
				e[i] = it.next();
				i++;
			}
			
			if((e[0].getType()==0) && (e[1].getType()==1) && (e[2].getType()==0)){
				
				mp = mapLogicalMathOperators();
				op = mp.get(e[1].getSymbol());
				
				switch(op) {
					
					case 1:
							res = Math.round(rd.get((e[0].getSymbol()).toUpperCase())) == Math.round(Double.valueOf(e[2].getSymbol()));
							break;
							
					case 2:
							res = Math.round(rd.get((e[0].getSymbol()).toUpperCase())) != Math.round(Double.valueOf(e[2].getSymbol()));
							break;
							
					case 3:
							res = rd.get((e[0].getSymbol()).toUpperCase()) < Double.valueOf(e[2].getSymbol());
							break;
							
					case 4:
							res = rd.get((e[0].getSymbol()).toUpperCase()) <= Double.valueOf(e[2].getSymbol());
							break;
							
					case 5:
							res = rd.get((e[0].getSymbol()).toUpperCase()) > Double.valueOf(e[2].getSymbol());
							break;
							
					case 6:
							res = rd.get((e[0].getSymbol()).toUpperCase()) >= Double.valueOf(e[2].getSymbol());
							break;
					
				}
				
				if(thereIsNot){
					res = !(res);
				}
				
			} else {
					
					throw new GggpExceptionImpl("The syntaxis of the rule's ANTECEDENT is wrong");
			}
		/** The recursive cases. */
		} else {
					
					it = antecedent.iterator();
					ae = it.next();
					if(!((ae.getSymbol()).equals("("))){
						
						throw new GggpExceptionImpl("The syntaxis of the rule's ANTECEDENT is wrong");
					}
					
					/** Delete the first level "(" and ")". */
					antecedent = this.deleteFirstAndLast(antecedent);
					
			    	it = antecedent.iterator();
			    	ae = it.next();
						
					if(ae.getType()==2){
						
						if(((ae.getSymbol()).toUpperCase()).equals("NOT")){
							
							/** Delete the "NOT" symbol. */
							antecedent = this.deleteFirst(antecedent);
							
							it = antecedent.iterator();
							ae = it.next();
							if(!((ae.getSymbol()).equals("("))){
								
								throw new GggpExceptionImpl("The syntaxis of the rule's ANTECEDENT is wrong");
							}
							
							/** Delete the second level "(" and ")". */
							antecedent = this.deleteFirstAndLast(antecedent);
							
							res = ! evaluatesRule(rd,antecedent);
							
							
						}else{
								
							throw new GggpExceptionImpl("The syntaxis of the rule's ANTECEDENT is wrong");
						}
					}else{		
						
						mp = mapLogicalOperators();
						
						op = -1;
						
						ant1 = new LinkedList<AntElement>();
						ant2 = new LinkedList<AntElement>();
						
						countOpen = 0;
						countClose = 0;
							
						if(ae.getType()!=3) {
							
							throw new GggpExceptionImpl("The syntaxis of the rule's ANTECEDENT is wrong");
						}
						
						countOpen ++;
						ant1.add(ae);
					
						while((it.hasNext()) && (countOpen != countClose)){
									
							ae = it.next();
									
							if(ae.getType()==3) {
								countOpen ++;
							}
									
							if(ae.getType()==4) {
								countClose ++;
							}
									
							ant1.add(ae);
						}
						
						if(countOpen != countClose){
							
							throw new GggpExceptionImpl("The syntaxis of the rule's ANTECEDENT is wrong");
						}
						
						if(!(it.hasNext())){
							
							throw new GggpExceptionImpl("The syntaxis of the rule's ANTECEDENT is wrong");
						}
								
						ae = it.next();
							
						if(ae.getType() != 2){
							
							throw new GggpExceptionImpl("The syntaxis of the rule's ANTECEDENT is wrong");
						}
									
						op = mp.get((ae.getSymbol()).toUpperCase());
											
						if(!(it.hasNext())){
							
							throw new GggpExceptionImpl("The syntaxis of the rule's ANTECEDENT is wrong");
						}
							
						countOpen = 0;
						countClose = 0;
							
						ae = it.next();
											
						if(ae.getType()!=3) {
							
							throw new GggpExceptionImpl("The syntaxis of the rule's ANTECEDENT is wrong");
						}
												
						countOpen ++;
						ant2.add(ae);
												
						while(it.hasNext()){
											
							ae = it.next();
													
							if(ae.getType()==3) {
								countOpen ++;
							}
													
							if(ae.getType()==4) {
								countClose ++;
							}
										
							ant2.add(ae);
											
						}
										
						if(countOpen != countClose){
							
							throw new GggpExceptionImpl("The syntaxis of the rule's ANTECEDENT is wrong");
						}
						
						res = evaluatesRule(rd,ant1);
						
					
						res1 = evaluatesRule(rd,ant2);
						
						switch(op) {
						
							case 1:
									res = res && res1;
									break;
							
							case 2:
									res = res || res1;
									break;
									
							default:
								
								throw new GggpExceptionImpl("The syntaxis of the rule's ANTECEDENT is wrong");
						}
						
				}
		}
		
		return res;
	}
	
	
	/**
	 * 
	 * Maps the Logical Mathematical operators against Integers, in order to use these map in a SWITCH sentence.
	 * 
	 */
	private Map<String, Integer> mapLogicalMathOperators(){
		
		Map<String, Integer> res;
		
		
		res = new ConcurrentHashMap<String, Integer>();
		
		res.put("==", 1);
		res.put("!=", 2);
		res.put("<",  3);
		res.put("<=", 4);
		res.put(">",  5);
		res.put(">=", 6);
		
		return res;
		
	}
	
	
	/**
	 * 
	 * Maps the Logical Operators against Integers, in order to use these map in a SWITCH sentence.
	 * 
	 */
	private Map<String, Integer> mapLogicalOperators(){
		
		Map<String, Integer> res;
		
		
		res = new ConcurrentHashMap<String, Integer>();
		
		res.put("AND", 1);
		res.put("OR",  2);
		res.put("NOT", 3);
		
		return res;
		
	}
	
	
	private List<AntElement> deleteFirstAndLast(Collection<AntElement> cl) {
		
		List<AntElement> res = null;
		Iterator<AntElement> it;
		AntElement e;
		
		if ( cl != null){
			
			res = new LinkedList<AntElement>();
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
	
	
	private List<AntElement> deleteFirst(Collection<AntElement> cl) {
		
		List<AntElement> res = null;
		Iterator<AntElement> it;
		AntElement e;
		
		if ( cl != null){
			
			res = new LinkedList<AntElement>();
			it = cl.iterator();
			
			/* The first Element isn't inserted in the result. */
			e = it.next();
			
			while(it.hasNext()){
				e = it.next();
				res.add(e);
			}
			
			if (res.isEmpty()){
				res = null;
			}
		}
		
		return res;
	}
	
	
	
	public List<AntElement> getAntecedent(){
		
		return this.antecedent;
		
	}
	
	
	public void setAntecedent(List<AntElement> a){
		
		this.antecedent = a;
		
	}
	
	
	public String getConsecuent(){
		
		return this.consecuent;
		
	}
	
	
	public void setConsecuent(String c){
		
		this.consecuent = c;
		
	}

}
