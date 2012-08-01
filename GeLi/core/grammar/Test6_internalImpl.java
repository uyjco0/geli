package core.grammar;

/**
 * 
 *  Test the methods in the Derivation class and the methods related to the Derivation class in the
 *  Grammar class.
 *  
 *  Non Public API
 *  
 * 
 * */

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Ignore;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import jdsl.core.api.Position;
import jdsl.core.api.Tree;

public class Test6_internalImpl {
	
	private static Grammar g;
	private static CreateGrammar cr;
	private static Derivation d;
	
	
	/* Initialize the necessary structures and check the constructor method of 
	 * the Derivation class. */
	@BeforeClass
	public static void InicioTest(){
		
		try{
			cr = new CreateGrammarImpl("c:/Archivos de programa/GeLi/Test/RNA.gr");
			cr.loadGrammar();
			g = cr.getGrammar();
			d = new DerivationImpl(g);
		}catch(GrammarExceptionImpl e){
			System.out.println(e.getMessage());
		}
	}
	

	/* Checks the getMaxRandomDerivation method in the Derivation class. */
	@Test
	@Ignore
	public void getMaxRandomDerivationTest(){
		
		NonTerminal nt = null;
		Element el = null;
		Collection<NonTerminal> cnt;
		Derivation d2;
		Derivation d3;
		
		cnt = g.getNonTerminals();
		for(NonTerminal nt2: cnt){
			if(nt2.getSymbol().equals("S")){
				nt = nt2;
			}
			if(nt2.getSymbol().equals("A")){
				el = nt2;
			}
		}
		
		try{
			
			d2=d.getMaxRandomDerivation(5,nt);
			System.out.println();
			System.out.println("La profundidad es: " + d2.depth());
			System.out.println();
			
			d2.crossByLevels();
			
			System.out.println();
			System.out.println();
			
			d3 = d2.duplicateDerivation(1, 0);
			d3.crossByLevels();
			
			System.out.println();
			System.out.println();
			
			d3.getLevelsRanks(el);
			
			System.out.println();
			System.out.println();
			
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	
	/* Checks that getMaxRandomDerivation method loads correctly the map of the Derivation. */
	@Test
	public void mapDerivationTest(){
		
		NonTerminal nt = null;
		Collection<NonTerminal> cnt;
		Derivation d2;
		Map<String, LinkedList<Position>> m;
		Set<String> s;
		LinkedList<Position> l;
		Tree t;
		@SuppressWarnings("unused")
		Position pos;
		
		cnt = g.getNonTerminals();
		for(NonTerminal nt2: cnt){
			if(nt2.getSymbol().equals("S")){
				nt = nt2;
			}
		}
		
		try{
			
			d2=d.getMaxRandomDerivation(7,nt);
			d2.crossByLevels();
			
			/* If is possible obtain the parent of a position, then the position is right.*/
			m = ((DerivationImpl)d2).getMap();
			t = ((DerivationImpl)d2).getTree();
			s = m.keySet();
			for(String e: s){
				l = m.get(e);
				for(Position p: l){
					if(!t.isRoot(p)){
						pos=t.parent(p);
					}
				}
			}
			System.out.println("CORRECTO");
			
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	
	/* Checks the crossover method in the Derivation class. */
	@Test
	@Ignore
	public void crossoverTest(){
		
		NonTerminal nt = null;
		Collection<NonTerminal> cnt;
		Derivation d2;
		Derivation d3;
		Derivation [] result = null;
		
		cnt = g.getNonTerminals();
		for(NonTerminal nt2: cnt){
			if(nt2.getSymbol().equals("S")){
				nt = nt2;
			}
		}
		
		try{
			
			while(true){
				
				System.out.println("PARENT: ");
				System.out.println();
				d2=d.getMaxRandomDerivation(6,nt);
				System.out.println("FIN PARENT: ");
				/*d2.crossByLevels();
				System.out.println();*/
				System.out.println("PARENT: ");
				System.out.println();
				d3 = d.getMaxRandomDerivation(6,nt);
				System.out.println("FIN PARENT: ");
				/*d3.crossByLevels();*/
			
				result = d.crossoverGBX(d2, d3, 6);
			
				if(result != null){
					System.out.println();
					System.out.println("RESULT 1: ");
					System.out.println();
					//result[0].crossByLevels();
					System.out.println();
					System.out.println("RESULT 2: ");
					/*System.out.println();
					result[1].crossByLevels();*/
				}else{
						System.out.println("No fue posible cruzar");
				}
			}
			
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
