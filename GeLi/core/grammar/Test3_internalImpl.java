package core.grammar;

/**
 * 
 *  Test the methods CalculateLenghtProduction in the LengthProductionAlgorithm_internal 
 *  and Grammar classes.
 *  
 *  Non Public API
 *  
 * 
 * */

import java.util.Collection;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

public class Test3_internalImpl {
	
	private static Grammar g;
	private static CreateGrammar cr;
	private static LengthProductionAlgorithm_internal l;
	/* Initialize the necessary structures and check the constructor method of 
	 * the CreateGrammar class. */
	@BeforeClass
	public static void InicioTest(){
		
		try{
			cr = new CreateGrammarImpl("c:/Archivos de programa/GeLi/Test/grammar.gr");
			cr.loadGrammar();
			g = cr.getGrammar();
			l = new LengthProductionAlgorithm_internalImpl();
			Assert.assertNotNull(l);
		}catch(GrammarExceptionImpl ex){
			  System.out.println(ex.getMessage());
		 }
	}
	
	
	/* Checks that the method lengthTerminalLenght works fine. */
	@Test(timeout=100)
	public void lengthTerminalTest(){
		
		int len = -2;
		Collection<Terminal> ct;
		Terminal t=null;
		Terminal t2=null;
		Terminal t3=null;
		
		ct = g.getTerminals();
		Assert.assertNotNull(ct);
		for(Terminal t4:ct){
			if((t4.getSymbol()).equals("1")){
				t = t4;
			}
			if((t4.getSymbol()).equals("5")){
				t2 = t4;
			}
		}
		Assert.assertNotNull(t);
		Assert.assertNotNull(t2);
		
		len = l.lengthTerminal(null, t);
		Assert.assertTrue(len==-2);
		
		len = l.lengthTerminal(g, null);
		Assert.assertTrue(len==-2);
		
		try{
			t3 = new TerminalImpl(0,"");
		}catch(GrammarExceptionImpl e){
			System.out.println(e.getMessage());
		}
		
		len = l.lengthTerminal(g, t3);
		Assert.assertTrue(len==-2);
		
		len = l.lengthTerminal(g, t);
		Assert.assertTrue(len==0);
		
		len = l.lengthTerminal(g, t2);
		Assert.assertTrue(len==0);
		
			
	}
	

	/* Checks that the method storeLengthNonTerminal works fine. */
	@Test(timeout=100)
	public void storeLengthNonTerminalTest(){
		
		Collection<NonTerminal> cnt;
		int res;
		
		try{
			l.storeLengthNonTerminals(g);
			cnt = g.getNonTerminals();
			
			System.out.println();
			System.out.println("*******************");
			System.out.println();
			System.out.println("TEST StoreLengthNonTerminal");
			System.out.println();
			System.out.println("*******************");
			
			for(NonTerminal nt: cnt){
				res = g.getLenghtNonTerminal(nt);
				
				System.out.println();
				System.out.println("The length of the NonTerminal " + nt.getSymbol() + " is: " + res);
				System.out.println();
				
			}
			
		}catch(GrammarExceptionImpl e){
			System.out.println(e.getMessage());
		}
	}
	
	
	/* Checks that the method storeLengthProduction works fine. */
	@Test(timeout=100)
	public void storeLengthProductionTest(){
		
		Collection<Production> cp;
		int res;
		
		try{
			l.storeLengthProductions(g);
			cp = g.getProductions();
			System.out.println();
			System.out.println("*******************");
			System.out.println();
			System.out.println("TEST StoreLengthProduction");
			System.out.println();
			System.out.println("*******************");
			for(Production p: cp){
				res = g.getLenghtProduction(p);
				
				System.out.println();
				System.out.println("The length of the Production " + p.getSymbol() + " is: " + res);
				System.out.println();
				
			}
			
		}catch(GrammarExceptionImpl e){
			System.out.println(e.getMessage());
		}
	}
	
	/* Checks that the methods calculateLengths in the Grammar class works fine. */
	@Test(timeout=100)
	public void calculateLengthsTest(){
		int len = -2;
		Collection<Production> cp;
		Collection<NonTerminal> cnt;

		/*Reloads again because need to clean the structures.*/
		try{
			cr = new CreateGrammarImpl("c:/Archivos de programa/GeLi/Test/grammar.gr");
			cr.loadGrammar();
			g = cr.getGrammar();
		}catch(GrammarExceptionImpl ex){
			  System.out.println(ex.getMessage());
		}
		
		System.out.println();
		System.out.println("*******************");
		System.out.println();
		System.out.println("TEST CalculateLengths");
		System.out.println();
		System.out.println("*******************");
		
		try{
			cp = g.getProductions();
			Assert.assertNotNull(cp);
			for(Production p4:cp){
				len = g.calculateLenghtProduction(p4);
				System.out.println();
				System.out.println("The length of the Production " + p4.getSymbol() + " is: " + len);
				System.out.println();
			}
			
			
			cnt = g.getNonTerminals();
			Assert.assertNotNull(cnt);
			for(NonTerminal nt4:cnt){
				len = g.calculateLenghtNonTerminal(nt4);
				System.out.println();
				System.out.println("The length of the NonTerminal " + nt4.getSymbol() + " is: " + len);
				System.out.println();
			}
			
						
			g.calculateLengthProductions();
			cp = g.getProductions();
			System.out.println();
			System.out.println("*******************");
			System.out.println();
			System.out.println("STORE PRODUCTIONS");
			System.out.println();
			System.out.println("*******************");
			for(Production p4: cp){
				len = g.getLenghtProduction(p4);
				
				System.out.println();
				System.out.println("The length of the Production " + p4.getSymbol() + " is: " + len);
				System.out.println();
				
			}
			
			g.calculateLengthNonTerminals();
			cnt = g.getNonTerminals();
			System.out.println();
			System.out.println("*******************");
			System.out.println();
			System.out.println("STORE NonTERMINALS");
			System.out.println();
			System.out.println("*******************");
			for(NonTerminal nt4: cnt){
				len = g.getLenghtNonTerminal(nt4);
				
				System.out.println();
				System.out.println("The length of the NonTerminal " + nt4.getSymbol() + " is: " + len);
				System.out.println();
				
			}
			
			
		}catch(GrammarExceptionImpl e){
			System.out.println(e.getMessage());
		}
	}
	
}
