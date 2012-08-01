package core.grammar;

/**
 * 
 *  Test the methods getMaxRandomDerivationWithFunction, getRandomDerivationWithFunction and 
 *  crossoverGBXWithFunction in the class DerivationImpl, plus the implementation of the class FunctionImpl.
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

import jdsl.core.api.Position;
import jdsl.core.api.Tree;
import jdsl.core.ref.NodeTree;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class Test8_internalImpl {

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
	
	
	/* Checks the method createFunction and updateFunction. */
	@Test
	@Ignore
	public void createFunction_updateFunctionTest(){
		Function F;
		Function F2;
		
		F = new FunctionImpl();
		
		F2 = F.createFunction();
		
		System.out.println(F2.getOperator());
		System.out.println(F2.getIncrement());
		
		System.out.println();
		System.out.println("Updated Function");
		System.out.println();
		
		F2 = F2.updateFunction(0.5);
		
		System.out.println(F2.getOperator());
		System.out.println(F2.getIncrement());
		
	}
	
	
	/* Checks the method getNodeAtAbsolutePosition of the class DerivationAux_InternalImpl. */
	@Test
	@Ignore
	public void getNodeAbsoluteAtPositionTest(){
		Derivation d2;
		NonTerminal nt = null;
		Collection<NonTerminal> cnt;
		Tree t;
		Position node;
		Position aux;
		Position aux2;
		Iterator<Position> it;
		Collection<Position> cProcess;
		Collection<Position> cChilds;
		int num;
		int level;
		int rank;
		
		cnt = g.getNonTerminals();
		for(NonTerminal nt2: cnt){
			if(nt2.getSymbol().equals("S")){
				nt = nt2;
			}
		}
		
		try{
			
			d2 = d.getMaxRandomDerivation(6, nt);
			
			t = ((DerivationImpl)d2).getTree();
			
			level = 0;
			node = t.root();
			
			/* Process the root. */
			System.out.println("Level: " + level);
			level++;
			
			aux = DerivationAux_InternalImpl.getNodeAtAbsoluteRank(t, node, 0);
			
			System.out.print("Root: " + ((Element)node.get("Element")).getSymbol());
			System.out.println(" RootF: " + ((Element)aux.get("Element")).getSymbol());
			System.out.println();
			
			/* Loads the childs of the root in order. */
			num = t.numChildren(node);
			cProcess = new LinkedList<Position>();
			for(int i=0; i< num; i++){
				cProcess.add(t.childAtRank(node, i));
			}
			
			cChilds = new LinkedList<Position>();
			while(!cProcess.isEmpty()){
								
				System.out.println("Level: " + level);
				level ++;
				
				for(Position p: cProcess){
							
					/* Load the childs of the actual node in order. */
					num = t.numChildren(p);
					for(int i=0; i< num; i++){
						cChilds.add(t.childAtRank(p, i));
					}
				}
				
				rank = 0;
				it = cProcess.iterator();
				
				node = it.next();
				System.out.print("Element: " + ((Element)node.get("Element")).getSymbol());
				aux2 = DerivationAux_InternalImpl.getNodeAtAbsoluteRank(t, node, rank);
				System.out.print(" ElementF: " + ((Element)aux2.get("Element")).getSymbol());
				System.out.print(" || ");
				
				rank ++;
				
				while(it.hasNext()){
					
					aux = it.next();
					System.out.print("Element: " + ((Element)aux.get("Element")).getSymbol());
					aux2 = DerivationAux_InternalImpl.getNodeAtAbsoluteRank(t, node, rank);
					System.out.print(" ElementF: " + ((Element)aux2.get("Element")).getSymbol());
					System.out.print(" || ");
					rank++;
					
				}
				
				
				System.out.println();
				System.out.println();
				
				cProcess = cChilds;
				cChilds = new LinkedList<Position>();
			}
			
			
		
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
	
	
	/* Checks the method getMaxRandomDerivationWithFunction. */
	@Test
	@Ignore
	public void getMaxRandomDerivationWithFunctionTest(){
		
		Derivation d2;
		NonTerminal nt = null;
		Collection<NonTerminal> cnt;
		Function F;
		Map<String, Double> features;
		
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
		
			d2 = d.getMaxRandomDerivationWithFunction(10, nt, features, F, "2", -1);
			d2.crossByLevelsWithFunction("2");
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
				
	}
	
	
	/* Checks the method duplicateTreeWithFunction. */
	@Test
	@Ignore
	public void duplicateTreeWithFunctionTest(){
		
		Derivation d2;
		Tree t;
		Tree t2;
		NonTerminal nt = null;
		Collection<NonTerminal> cnt;
		Function F;
		Map<String, Double> features;
		
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
		
			System.out.println();
			d2 = d.getMaxRandomDerivationWithFunction(8, nt, features, F, "2", -1);
			t = new NodeTree();;
			t2 = ((DerivationImpl)d2).getTree();
			DerivationAux_InternalImpl.duplicateTreeWithFunction(t2.root(),t2,t,null, "2" );
			
			System.out.println("ORIGINAL");
			if(d2!=null){
				d2.crossByLevelsWithFunction("2");
			}
			
			d2.clearDerivationWithFunction("2");
			
			System.out.println();
			System.out.println("DUPLICATE");
			if(t!=null){
				DerivationAux_InternalImpl.crossByLevelsWithFunction(t,"2");
			}
			
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
				
	}
	
	
	/* Checks the method crossoverGBXWithFunction and that there isn't a link between father and
	 * child (for the Memory Leak problem). */
	@Test
	public void crossoverGBXWithFunctionTest(){
		
		Derivation d2;
		Derivation d3;
		Derivation[] result;
		NonTerminal nt = null;
		Collection<NonTerminal> cnt;
		Function F;
		Map<String, Double> features;
		
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
		
			System.out.println();
			d2 = d.getMaxRandomDerivationWithFunction(8, nt, features, F, "2", -1);
			d3 = d.getMaxRandomDerivationWithFunction(8, nt, features, F, "2", -1);
			
			result = d.crossoverGBXWithFunction(d2, d3, 8, "2");
			
			//Eliminates the parents
			d2.clearDerivationWithFunction("2");
			d3.clearDerivationWithFunction("2");
			
			/**System.out.println("C");
			result = d.crossoverGBXWithFunction(result[0], result[1], 8, "2");
			System.out.println("D");
			
			System.out.println(); */
			
			/**
			 * It's necessary to put in the function crossoverGBXWithFunction in the class
			 * DerivationImpl the following code next to the assignation of the "result"
			 * variable:
			 * 
			 * ###############
			 * System.out.println("RESULT_0");
			 *	if(result[0]!=null){
			 *	DerivationAux_InternalImpl.crossByLevelsWithFunction(((DerivationImpl)result[0]).getTree(), "2");
			 *	System.out.println();
			 *	System.out.println();
			 * ################
			 * 
			 * At the end of the test, remember to quit this code.
			 *
			 *
			 *
			} **/
			
			System.out.println();
			if(result[0]!=null){
				result[0].crossByLevelsWithFunction("2");
			}
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
				
	}
	
}
