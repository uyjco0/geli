package gggpIsokinetics;

/**
 * 
 *  Test the methods of the GggpImpl class.
 *  
 *  Non Public API
 *  
 * 
 * */


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Ignore;


import core.grammar.Element;
import core.grammar.Derivation;
import core.grammar.DerivationImpl;
import core.grammar.GrammarExceptionImpl;
import core.grammar.GrammarMaxDepthExceptionImpl;
import core.grammar.Function;
import core.grammar.FunctionImpl;
import core.grammar.NonTerminal;


import grammarGuidedGeneticProgramming.GggpExceptionImpl;

import java.util.Collection;
import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;


public class Test2_internalImpl {

	private static Isokinetics gg;
	
	/* Initialize the necessary structures */
	@BeforeClass
	public static void InicioTest(){
		
		try{
			
			gg = new Isokinetics("c:/Archivos de programa/GeLi/Test/IsokineticGrammar.gr",
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
	@Ignore
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

	
	/* Test the method fitness over the Isokinetic Grammar. */
	@Test
	public void fitnessTest(){
		
		Map<Derivation,Double> fit;
		Collection<Derivation> ds;
		Map<String, Double> features;
		AuxFeatureFitnessImpl aux;
		Derivation d;
		Derivation d2;
		NonTerminal nt = null;
		Collection<NonTerminal> cnt;
		Function f;
		Double fitness;
		List<Object> parameters;
		
		ds = new LinkedList<Derivation>();
		f = new FunctionImpl();
		
		try{
			
			cnt = gg.getGrammar().getNonTerminals();
			for(NonTerminal nt2: cnt){
				if(nt2.getSymbol().equals("S")){
					nt = nt2;
				}
			}
			
			aux = new AuxFeatureFitnessImpl();
			features = aux.featuresInitialValues("C:/Jorge/Final/Paper2007/PaperIsokineticos/Datos/Feature18/Total/TotalWithLabel18.sec");
			
			
			d = new DerivationImpl(gg.getGrammar());
			d2 = d.getMaxRandomDerivationWithFunction(25, nt, features, f, "real", -2);
			
			System.out.println();
			System.out.println("DERIVATION");
			d2.crossByLevelsWithFunction("real");
			System.out.println();
			
			Assert.assertNotNull(features);
			
			ds.add(d2);
			
			Assert.assertNotNull(ds);
			
			Assert.assertNotNull(gg.getFeatures());
			
			parameters = new LinkedList<Object>();
			parameters.add(new Boolean(true));
			fit = gg.fitness(ds, gg.getFeatures(), parameters);
			
			fitness = fit.get(d2);
			
			System.out.println("El fitness es: " + fitness);
			
			
			
		}catch(GggpExceptionImpl e){
			System.out.println(e.getMessage());
		}
		catch(GrammarExceptionImpl e2){
			System.out.println(e2.getMessage());
		}
		catch(GrammarMaxDepthExceptionImpl e3){
			System.out.println(e3.getMessage());
		}
		
	}
	
	
	/* Test the method initPopulationWithFunction and Fitness thogether over the Isokinetic Grammar. */
	@Test
	@Ignore
	public void initPopulationWithFunctionAndFitnessTest(){
		
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
		Map<Derivation,Double> fit;
		double maxFit;
		Double fitness;
		Derivation dFit;
		Collection<Element> ce;
		Iterator<Element> ite;
		Element elem;
		
		aux = new AuxFeatureFitnessImpl();
		
		features = aux.featuresInitialValues("C:/Jorge/Final/Paper2007/PaperIsokineticos/Datos/Feature18/Total/TotalWithLabel18.sec");
		f = new FunctionImpl();
		symbol = "real";
		symbol2 = -2;
		depth = 15;
		populationSize = 600;
		
		
		parameters = new LinkedList<Object>();
		parameters.add(f);
		parameters.add(populationSize);
		parameters.add(depth);
		parameters.add(symbol);
		parameters.add(symbol2);
		parameters.add(features);
		
		try{
		
			initialPopulation = gg.initPopulation(2, parameters);
			
			parameters = null;
			parameters = new LinkedList<Object>();
			parameters.add(new Boolean(true));
			fit = gg.fitness(initialPopulation, gg.getFeatures(), parameters);
			
			maxFit = Double.MIN_VALUE;
			
			it = initialPopulation.iterator();
			
			
			dFit = null;
			System.out.println();
			while(it.hasNext()){
				
				d = it.next();
				fitness = fit.get(d);
				System.out.println();
				System.out.println("FITNESS: " + fitness);
				System.out.println();
				
				if(fitness > maxFit){
					dFit = d;
					maxFit = fitness;
				}
				
			}
			
			System.out.println();
			System.out.println("The maximum fitness is: " + maxFit);
			
			System.out.println();
			System.out.println("RULE");
			ce= dFit.getLeavesInLeftOrderWithFunction("real");
			ite = ce.iterator();
			while(ite.hasNext()){
				elem = ite.next();
				System.out.print(elem.getSymbol() + " ");
			}
		
		}catch(GggpExceptionImpl e){
			
			System.out.println(e.getMessage());
		}
		catch(GrammarExceptionImpl e){
			
			System.out.println(e.getMessage());
		}
				
	}
	
	/* Test the method parentSelection over the Isokinetic Grammar. */
	@Test
	@Ignore
	public void parentSelectionTest(){
		
		List<Object> parameters;
		Map<String, Double> features;
		AuxFeatureFitnessImpl aux;
		Function f;
		Collection<Derivation> matingPool;
		Iterator<Derivation> it;
		Derivation d;
		
		
		aux = new AuxFeatureFitnessImpl();
		features = aux.featuresInitialValues("C:/Jorge/Final/Paper2007/PaperIsokineticos/Datos/Feature18/Total/TotalWithLabel18.sec");
		f = new FunctionImpl();
		
		parameters = new LinkedList<Object>();
		
		parameters.add(f);
		parameters.add(200);
		parameters.add(25);
		parameters.add("real");
		parameters.add(-2);
		parameters.add(features);
		
		try{
		
			gg.initPopulation(2, parameters);
			
			parameters = new LinkedList<Object>();
		
			/** The parameter K used in the Tournament operator.*/
			parameters.add(new Integer(7));
	
			/** The name of the file with the features of the training items.*/
			parameters.add("C:/Jorge/Final/Paper2007/PaperIsokineticos/Datos/Feature18/Total/TotalWithLabel18.sec");
	
			/** The amount of columns of the file with the features of the training items.*/
			parameters.add(new Integer(19));
		
			/** It creates the mating pool from the current GGGP System's population.*/
			matingPool = gg.parentSelection(gg.getPopulation(), gg.getFitness(), gg.getFitness(), 200, 1, parameters);
			
			it = matingPool.iterator();
			
			while(it.hasNext()){
				d = it.next();
				System.out.println();
				d.crossByLevelsWithFunction("real");
				System.out.println();
			}
			System.out.println();
			System.out.println("Mating pool size: " + matingPool.size());
			
		}catch(GggpExceptionImpl e){
			System.out.println(e.getMessage());
		}
		
	}
	
	/* Test the method nextGeneration over the Isokinetic Grammar. */
	@Test
	@Ignore
	public void nextGenerationTest(){
		
		List<Object> parameters;
		Map<String, Double> features;
		AuxFeatureFitnessImpl aux;
		Function f;
		Collection<Derivation> newIndividuals;
		Iterator<Derivation> it;
		Derivation d;
		
		
		aux = new AuxFeatureFitnessImpl();
		features = aux.featuresInitialValues("C:/Jorge/Final/Paper2007/PaperIsokineticos/Datos/Feature18/Total/TotalWithLabel18.sec");
		f = new FunctionImpl();
		
		parameters = new LinkedList<Object>();
		
		parameters.add(f);
		parameters.add(200);
		parameters.add(25);
		parameters.add("real");
		parameters.add(-2);
		parameters.add(features);
		
		try{
		
			gg.initPopulation(2, parameters);
			
			parameters = new LinkedList<Object>();
		
			/** The parameter K used in the Tournament operator.*/
			parameters.add(new Integer(7));
	
			/** The name of the file with the features of the training items.*/
			parameters.add("C:/Jorge/Final/Paper2007/PaperIsokineticos/Datos/Feature18/Total/TotalWithLabel18.sec");
	
			/** The amount of columns of the file with the features of the training items.*/
			parameters.add(new Integer(19));
		
			/** It creates the mating pool from the current GGGP System's population.*/
			gg.parentSelection(gg.getPopulation(), gg.getFitness(), gg.getFitness(), 200, 1, parameters);
			
		
			parameters = new LinkedList<Object>();
		
			/** The crossover method.*/
			parameters.add(2);
		
			/** Maximum Depth of the Derivations.*/
			parameters.add(25);
		
			/** The Element's symbol to which the Evolutionary Gradient will be applied .*/
			parameters.add("real");
		
			/** It creates the next generation of individuals from the mating pool .*/
			newIndividuals = gg.nextGeneration(gg.getMatingPool(), parameters);
			
			it = newIndividuals.iterator();
			
			while(it.hasNext()){
				d = it.next();
				System.out.println();
				d.crossByLevelsWithFunction("real");
				System.out.println();
			}
			
			System.out.println();
			System.out.println("New population size: " + newIndividuals.size());
			
		}catch(GggpExceptionImpl e){
			System.out.println(e.getMessage());
		}
		
	}
	
}
