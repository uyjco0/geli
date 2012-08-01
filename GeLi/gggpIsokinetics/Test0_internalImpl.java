package gggpIsokinetics;

/**
 * 
 *  Test the methods of the GggpImpl class.
 *  
 *  Non Public API
 *  
 * 
 * */


import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Ignore;


import core.grammar.Derivation;
import core.grammar.DerivationImpl;
import core.grammar.GrammarExceptionImpl;
import core.grammar.GrammarMaxDepthExceptionImpl;
import core.grammar.Function;
import core.grammar.FunctionImpl;
import core.grammar.NonTerminal;


import grammarGuidedGeneticProgramming.GggpImpl;
import grammarGuidedGeneticProgramming.GggpExceptionImpl;

import java.util.Collection;
import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;


public class Test0_internalImpl {

	private static GggpImpl gg;
	
	/* Initialize the necessary structures */
	@BeforeClass
	public static void InicioTest(){
		
		try{
			
			gg = new GggpImpl("c:/Archivos de programa/GeLi/Test/IsokineticGrammar.gr",
					          "C:/Jorge/Final/Paper2007/PaperIsokineticos/Datos/Feature18/Total/TotalWithLabel-Train18.sec",
					          null);
			
			
		}catch(GggpExceptionImpl ex){
			  System.out.println(ex.getMessage());
		}
	}
	
	
	/* Test the method initPopulation over the Isokinetic Grammar. */
	@Test
	@Ignore
	public void initPopulationTest(){
		
		List<Object> parameters;
		int populationSize;
		int depth;
		Collection<Derivation> initialPopulation;
		Iterator<Derivation> it;
		Derivation d;
		int amount = 0;
		
		depth = 25;
		populationSize = 200;
		
		
		parameters = new LinkedList<Object>();
		parameters.add(populationSize);
		parameters.add(depth);
		
		try{
		
			initialPopulation = gg.initPopulation(1, parameters);
			
			it = initialPopulation.iterator();
			
			
			
			System.out.println();
			while(it.hasNext()){
				
				amount++;
				System.out.println();
				System.out.println("INDIVIDUAL: " + amount);
				System.out.println();
				d = it.next();
				d.crossByLevels();
				
			}
			
			System.out.println();
			System.out.println("Population size: " + initialPopulation.size());
		
		}catch(GggpExceptionImpl e){
			
			System.out.println(e.getMessage());
		}
			
	}
	
	
	/* Test the method initPopulationWithFunction over the Isokinetic Grammar. */
	@Test
	@Ignore
	public void initPopulationWithFunctionTest(){
		
		List<Object> parameters;
		Function f;
		int populationSize;
		int depth;
		String symbol;
		int symbol2;
		Map<String, Double> features;
		AuxFeatureFitnessImpl aux;
		Collection<Derivation> initialPopulation;
		Iterator<Derivation> it;
		Derivation d;
		int amount = 0;
		
		aux = new AuxFeatureFitnessImpl();
		
		features = aux.featuresInitialValues("C:/Jorge/Final/Paper2007/PaperIsokineticos/Datos/Feature18/Total/TotalWithLabel18.sec");
		f = new FunctionImpl();
		symbol = "real";
		symbol2 = -2;
		depth = 25;
		populationSize = 200;
		
		
		parameters = new LinkedList<Object>();
		parameters.add(f);
		parameters.add(populationSize);
		parameters.add(depth);
		parameters.add(symbol);
		parameters.add(symbol2);
		parameters.add(features);
		
		try{
		
			initialPopulation = gg.initPopulation(2, parameters);
			
			it = initialPopulation.iterator();
			
			
			
			System.out.println();
			while(it.hasNext()){
				
				amount++;
				System.out.println();
				System.out.println("INDIVIDUAL: " + amount);
				System.out.println();
				d = it.next();
				d.crossByLevelsWithFunction("real");
				
			}
			
			System.out.println();
			System.out.println("Population size: " + initialPopulation.size());
		
		}catch(GggpExceptionImpl e){
			
			System.out.println(e.getMessage());
		}
				
	}
	
	
	/* Test the method crossover over the Isokinetic Grammar. */
	@Test
	@Ignore
	public void crossoverTest(){
		
		List<Object> parameters;
		int depth;
		Collection<Derivation> parents;
		Collection<Derivation> childs;
		Derivation p1;
		Derivation p2;
		Iterator<Derivation> it;
		Derivation d;
		NonTerminal nt = null;
		Collection<NonTerminal> cnt;
		int amount = 0;
		
		depth = 25;
		
		
		parameters = new LinkedList<Object>();
		parameters.add(depth);
		
		try{
		
			cnt = gg.getGrammar().getNonTerminals();
			for(NonTerminal nt2: cnt){
				if(nt2.getSymbol().equals("S")){
					nt = nt2;
				}
			}
			
			d = new DerivationImpl(gg.getGrammar());
			p1 = d.getMaxRandomDerivation(depth, nt);
			p2 = d.getMaxRandomDerivation(depth, nt);
			
			parents = new LinkedList<Derivation>();
			parents.add(p1);
			parents.add(p2);
			
			childs = gg.crossover(parents, 1, parameters);
			
			it = parents.iterator();
			System.out.println("PARENTS: ");
			System.out.println();
			while(it.hasNext()){
				
				amount++;
				System.out.println();
				System.out.println("PARENT: " + amount);
				System.out.println();
				d = it.next();
				d.crossByLevels();
				
			}
			System.out.println();
			
			it = childs.iterator();
			amount = 0;
			System.out.println("CHILDS: ");
			System.out.println();
			while(it.hasNext()){
				
				amount++;
				System.out.println();
				System.out.println("CHILD: " + amount);
				System.out.println();
				d = it.next();
				d.crossByLevels();
				
			}
			
		
		}catch(GrammarExceptionImpl e){
			
			System.out.println(e.getMessage());
		}
		catch(GrammarMaxDepthExceptionImpl e2){
			
			System.out.println(e2.getMessage());
		}
		catch(GggpExceptionImpl e3){
			
			System.out.println(e3.getMessage());
		}
		
			
	}
	
	
	/* Test the method crossoverWithFunction over the Isokinetic Grammar. */
	@Test
	public void crossoverWithFunctionTest(){
		
		List<Object> parameters;
		int depth;
		String symbol;
		Collection<Derivation> parents;
		Collection<Derivation> childs;
		Map<String, Double> features;
		AuxFeatureFitnessImpl aux;
		Function f;
		Derivation p1;
		Derivation p2;
		Iterator<Derivation> it;
		Derivation d;
		NonTerminal nt = null;
		Collection<NonTerminal> cnt;
		int amount = 0;
		
		depth = 25;
		symbol = "real";
		
		aux = new AuxFeatureFitnessImpl();
		features = aux.featuresInitialValues("C:/Jorge/Final/Paper2007/PaperIsokineticos/Datos/Feature18/Total/TotalWithLabel18.sec");
		f = new FunctionImpl();
		
		parameters = new LinkedList<Object>();
		parameters.add(depth);
		parameters.add(symbol);
		
		try{
		
			cnt = gg.getGrammar().getNonTerminals();
			for(NonTerminal nt2: cnt){
				if(nt2.getSymbol().equals("S")){
					nt = nt2;
				}
			}
			
			d = new DerivationImpl(gg.getGrammar());
			p1 = d.getMaxRandomDerivationWithFunction(depth, nt, features, f, symbol, -2);
			p2 = d.getMaxRandomDerivationWithFunction(depth, nt, features, f, symbol, -2);
			
			parents = new LinkedList<Derivation>();
			parents.add(p1);
			parents.add(p2);
			
			childs = gg.crossover(parents, 2, parameters);
			
			it = parents.iterator();
			System.out.println("PARENTS: ");
			System.out.println();
			while(it.hasNext()){
				
				amount++;
				System.out.println();
				System.out.println("PARENT: " + amount);
				System.out.println();
				d = it.next();
				d.crossByLevelsWithFunction(symbol);
				
			}
			System.out.println();
			
			it = childs.iterator();
			amount = 0;
			System.out.println("CHILDS: ");
			System.out.println();
			while(it.hasNext()){
				
				amount++;
				System.out.println();
				System.out.println("CHILD: " + amount);
				System.out.println();
				d = it.next();
				d.crossByLevelsWithFunction(symbol);
				
			}
			
		
		}catch(GrammarExceptionImpl e){
			
			System.out.println(e.getMessage());
		}
		catch(GrammarMaxDepthExceptionImpl e2){
			
			System.out.println(e2.getMessage());
		}
		catch(GggpExceptionImpl e3){
			
			System.out.println(e3.getMessage());
		}
		
			
	}

	
}
