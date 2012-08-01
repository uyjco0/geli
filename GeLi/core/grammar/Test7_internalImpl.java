package core.grammar;

/**
 * 
 *  Test the method getLeavesInLeftOrder in the Derivation class.
 *  
 *  Non Public API
 *  
 * 
 * */

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;

public class Test7_internalImpl {
	
	private static Grammar g;
	private static CreateGrammar cr;
	private static Derivation d;
	
	
	/* Initialize the necessary structures. */
	@BeforeClass
	public static void InicioTest(){
		
		try{
			cr = new CreateGrammarImpl("c:/Archivos de programa/GeLi/Test/grammar.gr");
			cr.loadGrammar();
			g = cr.getGrammar();
			d = new DerivationImpl(g);
		}catch(GrammarExceptionImpl e){
			System.out.println(e.getMessage());
		}
	}
	

	/* Checks the getLeavesInLeftOrder method in the Derivation class. */
	@Test
	public void getLeavesInLeftOrderTest(){
		
		NonTerminal nt = null;
		Collection<NonTerminal> cnt;
		Derivation d2;
		Collection<Element> ce;
		Iterator<Element> it;
		Element el;
		
		cnt = g.getNonTerminals();
		for(NonTerminal nt2: cnt){
			if(nt2.getSymbol().equals("S")){
				nt = nt2;
			}
		}
		
		try{
				
				d2=d.getMaxRandomDerivation(8,nt);
		
				d2.crossByLevels();
				System.out.println();
			
				ce = d2.getLeavesInLeftOrder();
				
				it = ce.iterator();
				System.out.println();
				System.out.println(" LEAVES");
				System.out.println();
				while(it.hasNext()){
					el = it.next();
					System.out.print(el.getSymbol());
					System.out.print(" ");
				}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
