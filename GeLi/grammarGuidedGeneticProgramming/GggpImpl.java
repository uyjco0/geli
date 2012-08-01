package grammarGuidedGeneticProgramming;


/** 
 * 
 * 
 * Class that implements the interface Gggp. 
 * 
 * 
 * It belongs to the Public API.
 * 
 * @author Jorge Couchet.
 * 
 * 
 **/


import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;


import cern.colt.matrix.DoubleMatrix2D;
import core.grammar.*;
import util.io.file.GenericFile;
import java.io.IOException;
import util.random.*;

                                       
public class GggpImpl implements Gggp<Derivation, Double, DoubleMatrix2D, Double, Grammar> {

	
	
	/** The Grammar associated to the GGGP System */
	private Grammar g;
	
	
	/** The features over which the GGGP system is trained. It's used to calculate the individual's fitness */
	private DoubleMatrix2D features;
	
	
	/** It stores the current generation of the GGGP System's population */
	private Collection<Derivation> population;
	
	
	/** It stores the Mating Pool generated from the current population's generation. */
	private Collection<Derivation> matingPool;
	
	
	/** It stores the fitness of the current population's generation. */
	private Map<Derivation, Double> fitness;
	
	
	/** It stores the probabilities of being selected to be parent of the current population's generation. */
	@SuppressWarnings("unused")
	private Map<Derivation, Double> probabilities;
	
	
	/** Empty constructor. */
	public GggpImpl() {
		
		this.g = null;
		this.features = null;
		this.fitness = new ConcurrentHashMap<Derivation, Double>();
		this.probabilities = new ConcurrentHashMap<Derivation, Double>();
		this.population = new LinkedList<Derivation>();
		this.matingPool = new LinkedList<Derivation>();
		
	}
	
	
	/** Constructor that receives the name and path of the Grammar's file and Features's vector. */
	public GggpImpl(String fileNameG, String fileNameF, List<Object> parameters) throws GggpExceptionImpl {
		
		try{
			
			this.g = this.loadGrammar(fileNameG);
			this.features = this.loadFeatures(fileNameF, parameters);
			this.fitness = new ConcurrentHashMap<Derivation, Double>();
			this.probabilities = new ConcurrentHashMap<Derivation, Double>();
			this.population = new LinkedList<Derivation>();
			this.matingPool = new LinkedList<Derivation>();
			
		}catch(Exception e){
			throw new GggpExceptionImpl(e.getMessage());
		}
		
	}
	

	public void clearStructures(Runtime rt) throws GrammarExceptionImpl{
		
		Iterator<Derivation> it;
		Derivation d;
		
		this.fitness.clear();
		this.probabilities.clear();
		
		if(this.population!=null){
			it = this.population.iterator();
			while(it.hasNext()){
				d = it.next();
				this.population.remove(d);
				d.clearDerivationWithFunction("real");
                d = null;
                it = this.population.iterator();
			}
		}
		
		if(this.matingPool!=null){
			it = this.matingPool.iterator();
			while(it.hasNext()){
				d = it.next();
				this.matingPool.remove(d);
				d.clearDerivationWithFunction("real");
				it = this.matingPool.iterator();
			}
		}
                
        rt.gc();
	}
	
	
	public Collection<Derivation> getPopulation(){
		
		return this.population;
	}
	
	
	public  void setPopulation(Collection<Derivation> p){
		
		this.population = p;
	}
	
	
	public Map<Derivation, Double> getFitness(){
		
		return this.fitness;
	}
	
	
	public  void setFitness(Map<Derivation, Double> f){
		
		this.fitness = f;
	}
	
	
	public Collection<Derivation> getMatingPool(){
		
		return this.matingPool;
	}
	
	
	public  void setMatingPool(Collection<Derivation> mp){
		
		this.matingPool = mp;
	}
	
	
	public Grammar getGrammar(){
		
		return this.g;
	}
	
	
	public DoubleMatrix2D getFeatures(){
		
		return this.features;
		
	}
	
	
	/**
	 * 
	 * Method = 1: Defines the standard GBX operator.
	 * Method = 2: Defines the GBX operator with function.
	 * 
	 */
	public Collection<Derivation> crossover(Collection<Derivation> parents, int method, 
			                                List<Object> parameters) throws GggpExceptionImpl {
		
		Collection<Derivation> childs = null;
		
		switch(method){
		
			/* Calls the standard GBX method*/
			case 1:
					childs = crossover_gbx(parents, parameters);
					break;
			
			/* Calls the GBX method with function */
			case 2:
					childs = crossover_gbxWithFunction(parents, parameters);
					break;
		
		}
		
		return childs;
	}

	
	private Collection<Derivation> crossover_gbx(Collection<Derivation> parents, List<Object> parameters)
	                                             throws GggpExceptionImpl{
		Iterator<Derivation> it;
		Iterator<Object> it2;
		Derivation i1;
		Derivation i2;
		int depth;
		Derivation [] childs;
		Derivation d;
		List<Derivation> result = null;
		
		if((parents != null) && (parents.size() == 2) && (parameters != null) && (parameters.size() == 1)){
			
			it = parents.iterator();
			i1 = it.next();
			i2 = it.next();
			
			it2 = parameters.iterator();
			depth = (Integer)it2.next();
			
			try {
				
				d = new DerivationImpl(this.g);
				childs = d.crossoverGBX(i1, i2, depth);
				
			} catch (GrammarExceptionImpl e){
				
				throw new GggpExceptionImpl(e.getMessage());
			}
			
			if(childs != null){
				result = new LinkedList<Derivation>();
				result.add(childs[0]);
				result.add(childs[1]);
			}
			
		}
		return result;
	}
	
	
	private Collection<Derivation> crossover_gbxWithFunction(Collection<Derivation> parents, 
			                                                 List<Object> parameters) throws GggpExceptionImpl{
		Iterator<Derivation> it;
		Iterator<Object> it2;
		Derivation i1;
		Derivation i2;
		int depth;
		String symbol;
		Derivation [] childs;
		Derivation d;
		List<Derivation> result = null;

		if((parents != null) && (parents.size() == 2) && (parameters != null) && (parameters.size() == 2)){

			it = parents.iterator();
			i1 = it.next();
			i2 = it.next();

			it2 = parameters.iterator();
			depth = (Integer)it2.next();
			symbol = (String)it2.next();
			
			try {
				
				d = new DerivationImpl(this.g);
				childs = d.crossoverGBXWithFunction(i1, i2, depth, symbol);
			
			} catch (GrammarExceptionImpl e) {
				
				throw new GggpExceptionImpl(e.getMessage());
			}

			if(childs != null){
				result = new LinkedList<Derivation>();
				result.add(childs[0]);
				result.add(childs[1]);
			}

		}
		return result;
	}
	
	
	public Map<Derivation, Double> fitness(Collection<Derivation> individuals, DoubleMatrix2D features, 
			                               List<Object> parameters) throws GggpExceptionImpl{
		
		return null;
	}

	
	/**
	 * 
	 * Method = 1: Defines the standard GBIM initialization operator.
	 * Method = 2: Defines the GBIM initialization operator with function.
	 * 
	 */
	public Collection<Derivation> initPopulation(int method, List<Object> parameters) throws GggpExceptionImpl {
		
		Collection<Derivation> population = null;
		
		switch(method){
		
			/* Calls the standard GBIM initialization operator*/
			case 1:
					population = initPopulation_gbim(parameters);
					break;
					
					/* Calls the GBIM with function initialization operator*/
			case 2:
					population = initPopulation_gbimWithFunction(parameters);
					break;
		
		
	
		}
		
		return population;
	}
	
	
	private Collection<Derivation> initPopulation_gbim(List<Object> parameters) throws GggpExceptionImpl {
		
		Iterator<Object> it;
		int pSize;
		int depth;
		int amount;
		Derivation d;
		NonTerminal nt;
		List<Derivation> population = null;
		
		if((parameters != null) && (parameters.size() == 2)){
			
			amount = 1;
			population = new LinkedList<Derivation>();
			it = parameters.iterator();
			pSize = (Integer)it.next();
			depth = (Integer)it.next();
			
			try {
				
				d = new DerivationImpl(this.g);
				
			} catch (GrammarExceptionImpl e) {
				
				throw new GggpExceptionImpl(e.getMessage());
				
			}
			nt = this.g.getStart();
			
			while(amount <= pSize){
				
				try {
					
					population.add(d.getMaxRandomDerivation(depth, nt));
					
				} catch (Exception e) {
					
					throw new GggpExceptionImpl(e.getMessage());
				}
				amount ++;
			}
		}
		
		this.population = null;
		
		this.population = population;
		
		return population;
	}
	
	
	@SuppressWarnings("unchecked")
	private Collection<Derivation> initPopulation_gbimWithFunction(List<Object> parameters) throws GggpExceptionImpl {
		
		Iterator<Object> it;
		Function f;
		int pSize;
		int depth;
		int amount;
		String symbol;
		Integer symbol2;
		Map<String, Double> featuresMap;
		Derivation d;
		NonTerminal nt;
		List<Derivation> population = null;
		
		if((parameters != null) && (parameters.size() == 6)){
			
			amount = 1;
			population = new LinkedList<Derivation>();
			it = parameters.iterator();
			f = (Function)it.next();
			pSize = (Integer)it.next();
			depth = (Integer)it.next();
			symbol = (String)it.next();
			symbol2 = (Integer)it.next();
			featuresMap = (Map<String, Double>)it.next();
			
			try {
				
				d = new DerivationImpl(this.g);
				
			} catch (GrammarExceptionImpl e){
				
				throw new GggpExceptionImpl(e.getMessage());
			}
			nt = this.g.getStart();
			
			while(amount <= pSize){
				try {
				
					population.add(d.getMaxRandomDerivationWithFunction(depth, nt, featuresMap, f, symbol, symbol2));
					
				} catch (Exception e) {
					
					throw new GggpExceptionImpl(e.getMessage());
				}
				amount ++;
			}
		}
		
		this.population = null;
		
		this.population = population;
		
		
		return population;
	}
	
	
	public Grammar loadGrammar(String fileName) throws GggpExceptionImpl {
		
		Grammar g = null;
		CreateGrammar cg;
		
		cg = new CreateGrammarImpl();
		try{
		
			cg.loadGrammar(fileName);
			
		} catch (GrammarExceptionImpl e){
			
			throw new GggpExceptionImpl(e.getMessage());
		}
		
		if (cg != null){
			g = cg.getGrammar();
		}
		
		this.g = g;
		
		return g;
	}

	
	public DoubleMatrix2D loadFeatures(String fileName, List<Object> parameters) throws GggpExceptionImpl {
		
		GenericFile f;
		DoubleMatrix2D d = null;
		
		if( fileName != null ) {
			
			f = new GenericFile();
			
			try {
			
				f.load(fileName);
				
			} catch (IOException e){
				
				throw new GggpExceptionImpl(e.getMessage());
			}
			
			d = f.getData();
			
		}
		
		this.features = d;
		
		return d;
	}
	
	
	public Collection<Derivation> mutation(Collection<Derivation> individuals, int method, 
			                               List<Object> parameters) throws GggpExceptionImpl {
		
		
		
		return null;
	}

	
	/**
	 * 
	 * Method = 1: Defines the Tournament operator with replacement and selecting the fitest in a 
	 *             deterministic way
	 * 
	 **/
	public Collection<Derivation> parentSelection( Collection<Derivation> individuals, Map<Derivation, Double> probabilities,
												   Map<Derivation, Double> fitness, int sizeMatingPool, int method, 
												   List<Object> parameters) throws GggpExceptionImpl {
		Collection<Derivation> matingPool = null;
		
		switch(method){
		
			/* Calls the Tournament operator with replacement and selecting the fitest in a deterministic way*/
			case 1:
					matingPool = parentSelection_Tournament(individuals, fitness, sizeMatingPool, parameters);
					break;
					
		}
		
		return matingPool;
		
	}
	
		
	private Collection<Derivation> parentSelection_Tournament (Collection<Derivation> individuals, Map<Derivation, Double> fitness,
															   int sizeMatingPool, List<Object> parameters) 
															   throws GggpExceptionImpl {
		
		Collection<Derivation> matingPool = null;
		int K;
		int col;
		String fileName;
		int amount1;
		int amount2;
		List<Derivation> setK;
		List<Double> sort;
		Iterator<Object> it;
		double prob;
		double prob2;
		Sort<Derivation> s;
		Derivation d;
		DerivationComparator dc;
		List<Object> param;
		List<Derivation> ind;
		Map<Derivation, Double> fi;
		Double fi2;
		DoubleMatrix2D fe;
		Boolean applyEnt;
		
		if( (individuals != null) && (!(individuals.isEmpty())) && (sizeMatingPool > 0) && (sizeMatingPool <= individuals.size()) && (parameters != null) && (parameters.size() == 4)) {
			
			it = parameters.iterator();
			K = (Integer)it.next();
			fileName = (String)it.next();
			col = (Integer)it.next();
			applyEnt = (Boolean)it.next();
			
			amount1 = 1;
			matingPool = new LinkedList<Derivation>();
			
			sort = new LinkedList<Double>();
			prob2 = individuals.size();
			prob = 1/prob2;
			
			for(int i =0; i<individuals.size(); i++){
				sort.add(prob);
			}
			
			if(fitness == null){
				fitness = new ConcurrentHashMap<Derivation, Double>();
			}
			
			parameters = null;
			parameters = new LinkedList<Object>();
			parameters.add(applyEnt);
			
			while ( amount1 <= sizeMatingPool){
				
				amount2 = 1;
				setK = new LinkedList<Derivation>();
				
				while (amount2 <= K){
					
					s = new Sort<Derivation>();
					
					d = s.sortElement(individuals, sort);
					
					if ((fitness.isEmpty()) || (!fitness.containsKey(d))){
						
						if(this.features == null){
							
							param = new LinkedList<Object>();
							param.add(col);
							fe = loadFeatures(fileName, param);
							
						} else {
								 fe = this.features;
						}
						
						ind = new LinkedList<Derivation>();
						ind.add(d);
						fi = fitness(ind, fe, parameters);
						fi2 = fi.get(d);
						this.fitness.put(d, fi2);
						fitness.put(d, fi2);
						
						this.fitness = fitness;
					
						if(fi!=null){
							fi.clear();
						}
					}
					
					setK.add(d);
					amount2++;
				}
				
				dc = new DerivationComparator(fitness);
				
				matingPool.add(java.util.Collections.max(setK, dc));
				amount1++;
			}
		}
		
		parameters.clear();
		parameters = null;
		
		this.matingPool = null;
		
		this.matingPool = matingPool;
		
		return matingPool;
		
		
	}
	
	
	public Collection<Derivation> nextGeneration(Collection<Derivation> matingPool, List<Object> parameters)
	                                             throws GggpExceptionImpl{
		
		Iterator<Object> it;
		Iterator<Derivation> it2;
		Derivation d;
		Collection<Derivation> p;
		int method;
		Collection<Derivation> newIndividuals = null;
		Collection<Derivation> childs;
		List<Double> sort;
		double prob;
		double prob2;
		Sort<Derivation> s;
		
		if( (matingPool != null) && (!matingPool.isEmpty()) && (parameters != null) ){
			
			prob2 = matingPool.size();
			prob2 = prob2%2;
			
			if (prob2 != 0){
				
				sort = new LinkedList<Double>();
				prob2 = matingPool.size();
				prob = 1/prob2;
				
				for(int i =0; i<matingPool.size(); i++){
					sort.add(prob);
				}
				
				s = new Sort<Derivation>();
				d = s.sortElement(matingPool, sort);
				matingPool.remove(d);
			}
			
			it = parameters.iterator();
			method = (Integer)it.next();
			parameters.remove((Object)method);
			
			newIndividuals = new LinkedList<Derivation>();
			
			while (!matingPool.isEmpty()) {
			
				p = new LinkedList<Derivation>();
				
				for (int j=0; j<2; j++){
				
					sort = new LinkedList<Double>();
					prob2 = matingPool.size();
					prob = 1/prob2;
			
					for(int i =0; i<matingPool.size(); i++){
						sort.add(prob);
					}
			
					s = new Sort<Derivation>();
					d = s.sortElement(matingPool, sort);
					matingPool.remove(d);
					p.add(d);
				}
				
				childs = this.crossover(p, method, parameters);
				
				p.clear();
				p=null;
				
				it2 = childs.iterator();
				while (it2.hasNext()){
					
					newIndividuals.add(it2.next());
				}
				
				childs.clear();
				childs=null;
				
			}
			
		}
		
		return newIndividuals;
		
	}
	
	
	public Map<Derivation, Double> parentSelectionProbabilities( Collection<Derivation> population, 
			                                                     Map<Derivation, Double> fitness,
			                                                     int method, List<Object> parameters) 
			                                                     throws GggpExceptionImpl {

		
		return null;
	}

	
	public double populationDiversity(Collection<Derivation> individuals, int method, List<Object> parameters) 
	                                  throws GggpExceptionImpl{
		
		
		
		return 0;
	}

	
	/**
	 * 
	 * Method = 1: Defines the Generation Model.
	 * 
	 */
	public Collection<Derivation> survivalSelection( Collection<Derivation> oldIndividuals, 
													 Collection<Derivation> newIndividuals, 
												     int method, List<Object> parameters, 
												     boolean elitism, int perBest) throws GggpExceptionImpl {
		
		Collection<Derivation> newPopulation = null;
		Collection<Derivation> bestInd;
		Iterator<Derivation> it;
		Derivation auxD;
		Derivation currentD;
		int total;
		double maxFit;
		List<Double> sort;
		double prob;
		double prob2;
		Sort<Derivation> s;
		
		switch(method){
		
			/* The Generation Model replaces the entire current population by the new one. */
			case 1:
					
					newPopulation = newIndividuals;
					
					if(elitism && (perBest > 0)){
						
						total = (oldIndividuals.size()*perBest)/100;
						
						if((total > 0) && (total < oldIndividuals.size() )){
						
							bestInd = new LinkedList<Derivation>();
							
							for(int i = 1; i<= total; i++){
								
								it = oldIndividuals.iterator();
								maxFit = Double.MIN_VALUE;
								currentD = null;
								
								while(it.hasNext()){
									
									auxD = it.next();
									
									if(this.fitness.get(auxD).doubleValue() > maxFit){
										maxFit = this.fitness.get(auxD).doubleValue();
										currentD = auxD;
									}
									
								}
								
								bestInd.add(currentD);
								oldIndividuals.remove(currentD);
								
							}
							
							if(!bestInd.isEmpty()){
								
								for(int l = 1; l<=total; l++) {
								
									sort = new LinkedList<Double>();
									prob2 = newPopulation.size();
									prob = 1/prob2;
						
									for(int j =0; j<newPopulation.size(); j++){
										sort.add(prob);
									}
						
									s = new Sort<Derivation>();
									auxD = s.sortElement(newPopulation, sort);
									
									newPopulation.remove(auxD);
									
									try{
										auxD.clearDerivation();
									}catch(GrammarExceptionImpl ex){
										throw new GggpExceptionImpl(ex.getMessage());
									}
								}
								
								it = bestInd.iterator();
								while(it.hasNext()){
									auxD = it.next();
									bestInd.remove(auxD);
									newPopulation.add(auxD);
									it = bestInd.iterator();
								}
								
							}
							
						}
						
					}
										
					break;
					
		}
		
		return newPopulation;
	}

}
