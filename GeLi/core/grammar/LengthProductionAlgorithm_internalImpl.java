package core.grammar;

/** 
 * 
 * Class that offers a concrete implementation of the interface LengthProductionAlgorithm_internal.
 * 
 * 
 * It doesn´t belong to the Public API.
 *
 * @author Jorge Couchet.
 * 
 **/

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.LinkedList;

class LengthProductionAlgorithm_internalImpl implements LengthProductionAlgorithm_internal{

	
	/**
	 * 
	 *  Empty constructor.
	 *
	 **/
	LengthProductionAlgorithm_internalImpl(){
		super();
	}

	
	public int lengthTerminal(Grammar g, Terminal t){
		Terminal te;
		int result = -2;
		if(g==null){
			return result;
		}
		te=g.getTerminal(t);
		if(te!=null){
			result = 0;
		}
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	public int lengthNonTerminal(Grammar g, NonTerminal nt, Map<Integer,Boolean> map, boolean store) throws GrammarExceptionImpl{
		NonTerminal nte;
		Terminal te;
		Class c;
		Class c2;
		Collection<Production> cp;
		Collection<Element> ce;
		Collection<Integer> lengths;
		Map<String,Element> elems;
		int res;
		int res_prod;
		int res_global;
		boolean find;
		boolean find2;
		int result = -1;
		String s;
		String s2;
		
		if(g!=null){
			nte=g.getNonTerminal(nt);
		}else{
				return -2;
		}
		
		if(nte!=null){
			if(map == null){
				map = new ConcurrentHashMap<Integer,Boolean>();
			}
			if(!map.containsKey(nt.getId())){
				s= nt.getSymbol();
				if((!s.startsWith("Tv"))&&(!s.startsWith("TV"))&&(!s.startsWith("Tf"))&&(!s.startsWith("TF"))){
					map.put(nt.getId(), true);
				}else{
						if(store){
							g.setLenghtNonTerminal(nt, 2);
						}
						return 2;
				}
				
				if(store){
					result = g.getLenghtNonTerminal(nt);
					if(result>=0){
						return result;
					}
				}
				
				cp = nt.getAllLeftsProd(g);
				
				if((cp!=null)&&(!cp.isEmpty())){
					/* Base step of the recursion, checks if one of the Productions has only one
					 * symbol and that symbol is a Terminal or a NonTerminal TV or TF */
					te = new TerminalImpl(0,"");
					c = te.getClass();
					c2 = nte.getClass();
					for(Production p:cp){
						ce=p.getRights(g);
						if(ce.size()==1){
							for(Element e:ce){
								if(c.isInstance(e)){
									if(store){
										g.setLenghtNonTerminal(nt, 1);
									}
									return 1;
								}else{
										if(c2.isInstance(e)){
											s2= e.getSymbol();
											if((s2.startsWith("Tv"))||(s2.startsWith("TV"))||(s2.startsWith("Tf"))||(s2.startsWith("TF"))){
												if(store){
													g.setLenghtNonTerminal(nt, 2);
												}
												return 2;
											}
										}
								}
							}
						}
					}
				
					/* Recursive step */
					c = nt.getClass();
					res_global = java.lang.Integer.MAX_VALUE;
					find2 = false;
					for(Production p:cp){
						res_prod = java.lang.Integer.MAX_VALUE;
						find = true;
						lengths = new LinkedList<Integer>();
						ce=p.getRights(g);
						
						// Selects only the NonTerminals and if a NonTerminal is repeated it selects only one
						elems= new ConcurrentHashMap<String,Element>();
						for (Element e:ce){
							if((c.isInstance(e))&&(!elems.containsKey(e.getSymbol()))){
								elems.put(e.getSymbol(), e);
							}
						}
						
						ce=elems.values();
						
						for(Element e:ce){
							if(store){
								res = g.getLenghtNonTerminal((NonTerminal)e);
								if(res<0){
									res = lengthNonTerminal(g,(NonTerminal)e, map, store);
								}
							}else{
									res = lengthNonTerminal(g,(NonTerminal)e, map, store);
							}
								
							if(res>=0){
								lengths.add(1+res);
							}
							else{
									lengths.add(res);
									
							}
						}
												
						if(!lengths.isEmpty()){
							for(Integer in:lengths){
								if(in.intValue()>=0){
									if(res_prod>=in.intValue()){
										res_prod = in.intValue();
									}
								}else{
										find = false;
								}	
							}
							if(find){
								if(res_global>res_prod){
									res_global = res_prod;
									find2 = true;
								}
							}
						}else{
								throw new GrammarExceptionImpl("Calculating NonTerminal length: there was a problem calculating the lenght of the NonTerminal: " + nt.getSymbol());
						}
					}
					
					if(find2){
						result = res_global;
					}else{
						throw new GrammarExceptionImpl("Calculating NonTerminal length: there is an infinite loop with the Productions that are associated to the NonTerminal: " + nt.getSymbol());
					}
				}
			}else{
					// If marked return -3 and it doesn´t set the Grammar lengths for the NonTerminals.
					return -3;
			}
		}else{
				return -2;
		}
		if(store && (result>=0)){
			g.setLenghtNonTerminal(nt, result);
		}
		return result;	
	}
	
	
	public void storeLengthNonTerminals(Grammar g) throws GrammarExceptionImpl{
		
		@SuppressWarnings("unused")
		int res;
		NonTerminal nt;
		Collection<NonTerminal> cnt;
		
		if(g==null){
			throw new GrammarExceptionImpl("The Grammar is NULL");
		}
		
		cnt = g.getNonTerminals();
		if(cnt==null){
			throw new GrammarExceptionImpl("The Grammar doesn´t has NonTerminals");
		}
		
		nt = g.getStart();
		if(nt==null){
			throw new GrammarExceptionImpl("The Grammar doesn´t has a start symbol");
		}
		
		if(!g.hasCalculatedLenghtsNonTerminals()){
			res = lengthNonTerminal(g, nt, null, true);
		
			for(NonTerminal nt2: cnt){
				res = lengthNonTerminal(g, nt2, null, true);
			}
		}
			
	}
	
	
	public int lengthProduction(Grammar g, Production p, boolean store) throws GrammarExceptionImpl {
		
		int res;
		int result = -1;
		Production p2;
		Collection<NonTerminal> cnt=null;
		Collection<Integer> lengths;
		Map<String,NonTerminal> elems;
		NonTerminal left;
		String s;
		
		
		if(g==null){
			return -2;
		}
		
		p2 = g.getProduction(p);
		if(p2==null){
			return -2;
		}
		
		left = p.getLeft(g);
		s= left.getSymbol();
		if((!s.startsWith("Tv"))&&(!s.startsWith("TV"))&&(!s.startsWith("Tf"))&&(!s.startsWith("TF"))){
			cnt = p.getNonTerminalsRight(g);
			if(cnt==null){
				throw new GrammarExceptionImpl("The Production doesn´t has symbols at the right side");
			}
		}else{
				if(store){
					g.setLenghtProduction(p, 1);
				}
				return 1;
		}
		
		lengths = new LinkedList<Integer>();
		
		// Eliminates the duplicated NonTerminals.
		elems= new ConcurrentHashMap<String,NonTerminal>();
		for (NonTerminal nt: cnt){
			if(!elems.containsKey(nt.getSymbol())){
				elems.put(nt.getSymbol(), nt);
			}
		}
		
		cnt = elems.values();
		
		for(NonTerminal nt: cnt){
			if(g.hasCalculatedLenghtsNonTerminals()){
				res = g.getLenghtNonTerminal(nt);
				if(res>=0){
					lengths.add(res);
				}else{
						/*In this case updates the structure of the Grammar that holds the 
						 *lengths of the NonTerminals, because this length should be in this
						 *structure. */
						res = lengthNonTerminal(g, nt, null, true);
						lengths.add(res);
				}
				
			}else{
					res = lengthNonTerminal(g, nt, null, true);
					lengths.add(res);
			}
		}
		if(!lengths.isEmpty()){
			res = java.lang.Integer.MIN_VALUE;
			for(Integer in:lengths){
				if(res<in.intValue()){
					res = in.intValue();
				}
			}
			result = 1+res;
		}else{
				throw new GrammarExceptionImpl("Calculating Production length: there was a problem obtaining the lengths of the NonTerminls");
		}
		
		if(store){
			g.setLenghtProduction(p, result);
		}
		return result;
		
	}

	public void storeLengthProductions(Grammar g) throws GrammarExceptionImpl {
		@SuppressWarnings("unused")
		int res;
		Collection<Production> cp;
		
		if(g==null){
			throw new GrammarExceptionImpl("The Grammar is NULL");
		}
		
		cp = g.getProductions();
		if(cp==null){
			throw new GrammarExceptionImpl("The Grammar doesn´t has Productions");
		}
		
		if(!g.hasCalculatedLenghtsProductions()){
			for(Production p: cp){
				res = lengthProduction(g, p, true);
			}
		}
			
		
	}

}
