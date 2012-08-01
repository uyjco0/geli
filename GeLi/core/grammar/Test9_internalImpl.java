package core.grammar;

/**
 * 
 *  Test the methods in the class ParseDerivationImpl.
 *  
 *  Non Public API
 *  
 * 
 * */


import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Ignore;

import core.grammar.CreateGrammar;
import core.grammar.CreateGrammarImpl;
import core.grammar.Derivation;
import core.grammar.DerivationImpl;
import core.grammar.Grammar;
import core.grammar.GrammarExceptionImpl;
import core.grammar.NonTerminal;
import core.grammar.Function;
import core.grammar.ParseDerivation;


import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class Test9_internalImpl {

	private static CreateGrammar cr;
	private static Grammar g;
	private static Derivation d;
	
	/* Initialize the necessary structures */
	@BeforeClass
	public static void InicioTest(){
		
		try{
			
			cr = new CreateGrammarImpl("c:/Archivos de programa/GeLi/Test/grammar.gr");
			cr.loadGrammar();
			g = cr.getGrammar();
			d = new DerivationImpl(g);
			
		}catch(GrammarExceptionImpl ex){
			  System.out.println(ex.getMessage());
		 }
	}
	
	
	/* Checks the method splitElementsBeforeToken. */
	@Test
	@Ignore
	public void splitElementsBeforeTokenTest(){
		
		Derivation d2;
		NonTerminal nt = null;
		Collection<NonTerminal> cnt;
		Function F;
		Map<String, Double> features;
		Collection<Element> ce;
		Iterator<Element> it;
		Collection<Collection<Element>> cce;
		Iterator<Collection<Element>> it2;
		ParseDerivation p;
		Element el;
		
		cnt = g.getNonTerminals();
		for(NonTerminal nt2: cnt){
			if(nt2.getSymbol().equals("S")){
				nt = nt2;
			}
		}
		
		F = new FunctionImpl();
		
		features = new ConcurrentHashMap<String, Double>();
		features.put("1", new Double(0.1));
		features.put("2", new Double (0.2));
		features.put("3", new Double (0.3));
		features.put("4", new Double (0.4));
		features.put("5", new Double (0.5));
		features.put("6", new Double (0.6));
		
		
				
		try{
		
			d2 = d.getMaxRandomDerivationWithFunction(8, nt, features, F, "2", -1);
			
			ce = d2.getLeavesInLeftOrder();
			
			System.out.println("Collection of elements:");
			
			it = ce.iterator();
			if(it != null){
				while(it.hasNext()){
					el = it.next();
					System.out.print(el.getSymbol() + " ");
				}
			}
			
			System.out.println();
			System.out.println("End Collection of elements:");
			
			p = new ParseDerivationImpl();
			
			cce = p.splitElementsBeforeToken(ce, "+");
			
			System.out.println();
			
			if(cce != null){
				it2 = cce.iterator();
				while(it2.hasNext()){
					System.out.println();
					System.out.println("Collection:");
					ce = it2.next();
					if(ce != null){
						it = ce.iterator();
						while(it.hasNext()){
							el = it.next();
							System.out.print(el.getSymbol() + " ");
						}
						System.out.println();
					}
				}
			}
			
			
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
				
	}
	
	
	/* Checks the method splitElementsBetweenToken. */
	@Test
	@Ignore
	public void splitElementsBetweenTokenTest(){
		
		Derivation d2;
		NonTerminal nt = null;
		Collection<NonTerminal> cnt;
		Function F;
		Map<String, Double> features;
		Collection<Element> coe;
		Collection<Collection<Element>> ce;
		Iterator<Collection<Element>> itc;
		Iterator<Element> it;
		Collection<Collection<Collection<Element>>> cce;
		Iterator<Collection<Collection<Element>>> it2;
		ParseDerivation p;
		Element el;
		
		cnt = g.getNonTerminals();
		for(NonTerminal nt2: cnt){
			if(nt2.getSymbol().equals("S")){
				nt = nt2;
			}
		}
		
		F = new FunctionImpl();
		
		features = new ConcurrentHashMap<String, Double>();
		features.put("1", new Double(0.1));
		features.put("2", new Double (0.2));
		features.put("3", new Double (0.3));
		features.put("4", new Double (0.4));
		features.put("5", new Double (0.5));
		features.put("6", new Double (0.6));
		
		
				
		try{
		
			d2 = d.getMaxRandomDerivationWithFunction(8, nt, features, F, "2", -1);
			
			coe = d2.getLeavesInLeftOrder();
			
			System.out.println("Collection of elements:");
			
			it = coe.iterator();
			if(it != null){
				while(it.hasNext()){
					el = it.next();
					System.out.print(el.getSymbol() + " ");
				}
			}
			
			System.out.println();
			System.out.println("End Collection of elements:");
			
			p = new ParseDerivationImpl();
			
			cce = p.splitElementsBetweenTokens(coe, "+", "*");
			
			System.out.println();
			
			if(cce != null){
				it2 = cce.iterator();
				while(it2.hasNext()){
					System.out.println();
					System.out.println("Collections:");
					ce = it2.next();
					if(ce != null){
						itc = ce.iterator();
						coe = itc.next();
						if(coe!=null){
							System.out.println("Collection1:");
							it = coe.iterator();
							while(it.hasNext()){
								el = it.next();
								System.out.print(el.getSymbol() + " ");
							}
						}
						System.out.println();
						coe = itc.next();
						if(coe!=null){
							System.out.println("Collection2:");
							it = coe.iterator();
							while(it.hasNext()){
								el = it.next();
								System.out.print(el.getSymbol() + " ");
							}
						}
						System.out.println();
					}
				}
			}
			
			
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
				
	}
	
	/* Checks the method deleteFirstAndLast. */
	@Test
	public void deleteFirstAndLastTest(){
		
		Derivation d2;
		NonTerminal nt = null;
		Collection<NonTerminal> cnt;
		Function F;
		Map<String, Double> features;
		Collection<Element> ce;
		Iterator<Element> it;
		ParseDerivation p;
		Element el;
		
		cnt = g.getNonTerminals();
		for(NonTerminal nt2: cnt){
			if(nt2.getSymbol().equals("S")){
				nt = nt2;
			}
		}
		
		F = new FunctionImpl();
		
		features = new ConcurrentHashMap<String, Double>();
		features.put("1", new Double(0.1));
		features.put("2", new Double (0.2));
		features.put("3", new Double (0.3));
		features.put("4", new Double (0.4));
		features.put("5", new Double (0.5));
		features.put("6", new Double (0.6));
		
		
				
		try{
		
			d2 = d.getMaxRandomDerivationWithFunction(8, nt, features, F, "2", -1);
			
			ce = d2.getLeavesInLeftOrder();
			
			it = ce.iterator();
			if(it != null){
				while(it.hasNext()){
					el = it.next();
					System.out.print(el.getSymbol() + " ");
				}
			}
			
			p = new ParseDerivationImpl();
			
			ce = p.deleteFirstAndLast(ce);
			
			System.out.println();
			System.out.println("Collection deleted:");
			
			if(ce != null){
				it = ce.iterator();
				if(it != null){
					while(it.hasNext()){
						el = it.next();
						System.out.print(el.getSymbol() + " ");
					}
				}
			}
			
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
			
	}
	
}
