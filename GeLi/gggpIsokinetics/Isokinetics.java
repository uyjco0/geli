package gggpIsokinetics;


/**
 * 
 * The class is a practical class, uses the GGGP, RULES an GRAMMAR API, in order to evolve an individual that has a 
 * rule's set capable of performing the prognosis of a medical isokinetics time series.
 * 
 * @author Jorge Couchet
 * 
 */


import java.util.Collection;
import java.util.Map;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.DoubleMatrix1D;

import core.grammar.Derivation;
import core.grammar.ParseDerivation;
import core.grammar.ParseDerivationImpl;
import core.grammar.Element;
import core.grammar.FunctionImpl;
import core.grammar.GrammarExceptionImpl;


import grammarGuidedGeneticProgramming.GggpImpl;
import grammarGuidedGeneticProgramming.GggpExceptionImpl;
import rules.AntElement;
import rules.AntElementImpl;
import rules.Rules;
import rules.RulesImpl;

public class Isokinetics extends GggpImpl {
	
	
	/** Empty constructor. */
	public Isokinetics() {
		
		super();
		
	}
	
	
	/** Constructor that receives the name and path of the Grammar's file and Features's vector. */
	public Isokinetics(String fileNameG, String fileNameF, List<Object> parameters) throws GggpExceptionImpl {
		
		super(fileNameG, fileNameF, parameters);
	}
	
	
	/**
	 * 
	 * The function calculates for each derivation (d) that belongs to "individuals", the entropy-based 
	 * fitness function of the rule set associated to d, over the isokinetic curves sample set 
	 * "features".
	 * 
	 * @param individuals
	 * 						The derivations with the rule set associated to each one.
	 * 
	 * @param features
	 * 				   The sample set of isokinetic curves.
	 * 
	 */
	public Map<Derivation, Double> fitness(Collection<Derivation> individuals, DoubleMatrix2D features, 
			                                List<Object> parameters) throws GggpExceptionImpl{
		
		Map<Derivation,Double> res = null;
		Iterator<Object> it;
		Boolean par;
		
		if((individuals != null) && (features!=null) && (parameters !=null) && (parameters.size() == 1)){
			
			it = parameters.iterator();
			par = (Boolean)(it.next());
			
			res = this.fitness(individuals, features, par.booleanValue() );
		}
		
		return res;
		
	}
	
	
	/**
	 * 
	 * The function calculates for each derivation (d) that belongs to "individuals", the entropy-based 
	 * fitness function of the rule set associated to d, over the isokinetic curves sample set 
	 * "features".
	 * 
	 * @param individuals
	 * 						The derivations with the rule set associated to each one.
	 * 
	 * @param features
	 * 				   The sample set of isokinetic curves.
	 * 
	 */
	private Map<Derivation, Double> fitness(Collection<Derivation> individuals, DoubleMatrix2D features, boolean applyEnt)
	                                       throws GggpExceptionImpl{
		
		Map<Derivation, Double> res = null;
		Collection<AuxFeatureFitnessImpl> auxFit;
		Iterator<AuxFeatureFitnessImpl> itAux;
		AuxFeatureFitnessImpl auxFitE;
		Iterator<Derivation> it;
		Derivation d;
		Collection<Rules> cr;
		Iterator<Rules> itr;
		Rules r;
		int normalClass;
		int injuryClass;
		double notSuccess;
		boolean evaluation;
		double success;
		double ebff;
		double efc;
		double H;
		double auxFitness;
		
		
		if((individuals != null) && (features != null)){
			
			auxFit = new LinkedList<AuxFeatureFitnessImpl>();
			
			auxFitE = new AuxFeatureFitnessImpl();
			
			auxFit = auxFitE.mapFeaturesWithLabel(this.getFeatures());
			
			res = new ConcurrentHashMap<Derivation, Double>();
			
			it = individuals.iterator();
			
			while (it.hasNext()){
				
				d = it.next();
				
				cr = parseRules(d, "real");
				
				itAux = auxFit.iterator();
				
				success = 0;
				H = 0;
				
				/** To each isokinetic curve auxFitE, all the DB's rules are applied. Only the rules that 
				 *  returns TRUE are considered, and depending of the class (normal or injury) associated 
				 *  to the rule and to auxFitE, one of two variables are incremented ("normalClass" = normal 
				 *  class, and "injuryClass" = normal class). At the end, the class with more hits is returned. */
				while (itAux.hasNext()){
					
					auxFitE = itAux.next();
					
					itr = cr.iterator();
					
					normalClass = 0;
					injuryClass = 0;
					notSuccess = 0;
					
					/** Applies all the rules over the isokinetic curve auxFiteE */
					while(itr.hasNext()){
					
						r = itr.next();
					
						evaluation = r.evaluatesRule(auxFitE.getFeatures(), r.getAntecedent());
						
						if(evaluation){
							
							if(((r.getConsecuent()).toUpperCase()).equals("NORMAL")){
								
								if(auxFitE.getLabel().equals(new Double(1))){
									
									/** The rule misclassify the isokinetic curve.*/
									notSuccess ++;
								}
								
								normalClass ++;
								
							}else {
								
								if(auxFitE.getLabel().equals(new Double(0))){
									
									/** The rule misclassify the isokinetic curve.*/
									notSuccess ++;
									
								}
								
									injuryClass ++;
							}
						}
					}
					
					if(applyEnt){
					
						if (cr.size() != 0) {
						
							/** The entropy function component of the rule set over the isokinetic curve auxFitE.*/
							efc = notSuccess/(2*cr.size());
						
						}else{
							efc = 0;
						}
					
						/** The entropy funtion of the distribution of the rule set's non success results, over
						 *  the sample of isokinect curves.*/
						if(efc != 0){
						
							H = H - efc*Math.log(efc);
					
						}
					}
					
					/** Classification of the result, that is: if the rules made a right prognosis, increment
					 * success, otherwise it does nothing. */
					if(auxFitE.getLabel().equals(new Double(0))){
						
						if(normalClass > injuryClass) {
							
							/** The rule set classify properly the isokinetic curve.*/
							success ++;	
						}
						
					} else {
						
							if(injuryClass > normalClass) {
							
								/** The rule set classify properly the isokinetic curve.*/
								success ++;
						}
						
					}
				}
				
				if(applyEnt){
				
					auxFitness = (success - H);
				
				}else{
						auxFitness = success;
				}
				
				
				if(auxFitness != 0){
					
					/** The entropy-based fitness function of the rule set over the isokinetic curves sample set.*/
					ebff = auxFitness/features.rows();
					
				}else {
						ebff = 0;
				}
				
				res.put(d, ebff);
			}
		}
		
		return res;
	}
	
	
	/**
	 * 
	 * @param fileGrammar
	 * 					  The path and name of the file with the Grammar's definition.
	 * 
	 * @param fileFeaturesTotal
	 * 					        The path and name of the file with the features in order to calculate the 
	 *                          initial values for the initial population.
	 * 
	 * @param fileFeaturesTrain
	 * 						     The path and name of the file with the features in order to train the 
	 * 							 GGGP system.
	 * 
	 * @param  cols
	 * 			    The column's amount of fileFeatures (18).
	 * 
	 * @param sizeP
	 * 				The GGGP System's size population (200).
	 * 	
	 * @param maxDepth
	 * 					Maximum Depth of the Derivations (10).
	 * 
	 * @param symbol
	 * 				 The Element's symbol to which the Evolutionary Gradient will be applied ("real").
	 * 
	 * @param symbol2
	 * 				  The Element's symbol which has the name of the feature, for which the Evolutionary Gradient 
	 *                is looking for a real value that will be used in a rule ("TV1").
	 *                
	 * @param K
	 * 				  The parameter used in the Tournament operator.
	 * 
	 * @param perBest
	 * 				  If elitism'll be applied, the parameters defines which percentage of the current
	 * 				  generation'll survive in the next (the survivals are selected by theirs fitness,ie,
	 *                only the individuals with the best fitness are selected).
	 *                
	 * @param elitism
	 * 				  It defines if elitism is applied or not.
	 * 
	 * @param  amountIter
	 * 					  It defines the maximum amount of training's iterations.
	 * 
	 * @param  percentage
	 * 					  It defines the maximum fitness to be reached, ie, if the training process reach
	 * 					  this fitness the process is stopped.
	 * 
	 * @param  applyEnt
	 * 					  It defines if apply Entropy or not in the fitness calculation.
	 * 					  
	 * 
	 * @return
	 * 			The Derivation (the best individual) that results from the training process.
	 * 
	 * 
	 * @throws GggpExceptionImpl
	 * 
	 */
	public Collection<Derivation> train (String fileGrammar, String fileFeaturesTotal, String fileFeaturesTrain, int cols, 
			                 int sizeP, int maxDepth, String symbol, int symbol2, int K, int perBest, boolean elitism,
			                 int amountIter, double percentage, boolean applyEnt) throws GggpExceptionImpl{
		
        
		Runtime rt = Runtime.getRuntime();
		List<Object> parameters;
		Map<String,Double> feat;
		Collection<Derivation> newIndividuals;
		@SuppressWarnings("unused")
		Collection<Derivation> newPopulation = null;
		@SuppressWarnings("unused")
		Collection<Derivation> matingPool;
		int auxAmountIter;
		AuxFeatureFitnessImpl auxFitE;
		double currentPer;
		Map<Derivation,Double> currentFitness;
	
		
		/** Load the features of the training items with theirs labels .*/
		this.loadFeatures(fileFeaturesTrain, null);
		
		/** Load the Grammar associated to the GGGP System .*/
		this.loadGrammar(fileGrammar);
		
		
		
		/** ################### START INIT POPULATION ################## **/
		
		parameters = new LinkedList<Object>();
		
		/** The function DELTA to be used in the Evolutionary Gradient. */
		parameters.add(new FunctionImpl());
		
		/** Size of the GGGP System's Population.*/
		parameters.add(new Integer(sizeP));
		
		/** Maximum Depth of the Derivations.*/
		parameters.add(new Integer(maxDepth));
		
		/** The Element's symbol to which the Evolutionary Gradient will be applied .*/
		parameters.add(symbol);
		
		/** The Element's symbol which has the name of the feature, for which the Evolutionary Gradient is looking for 
		 *  a real value that will be used in a rule.*/
		parameters.add(symbol2);
		
		/** Feat is the mapping from the name of each feature to his average real value in the training and test items. */
		auxFitE = new AuxFeatureFitnessImpl();
		feat = auxFitE.featuresInitialValues(fileFeaturesTotal);
		parameters.add(feat);
		
		/** It creates the initial GGGP System's population. */
		newIndividuals = null;
		newIndividuals = this.initPopulation(2, parameters);
		
		parameters = null;
		parameters = new LinkedList<Object>();
		parameters.add(new Boolean(applyEnt));
		currentFitness = this.fitness(newIndividuals, this.getFeatures(), parameters);
		currentPer =  java.util.Collections.max(currentFitness.values());
		
		this.setFitness(currentFitness);
		
		
		/** ################### END INIT POPULATION ################## **/
		
		
		
		/** ################### START THE TRAINING PROCESS ################### */
		
		auxAmountIter = 1;
		
		while ((auxAmountIter <= amountIter) && (currentPer < percentage )) {
			
			System.out.println("Iteration number: " + auxAmountIter);
			
			auxAmountIter++;
				
			
			
			/** ################### START CREATE MATING POOL ################## **/
			parameters = null;
			parameters = new LinkedList<Object>();
		
			/** The parameter K used in the Tournament operator.*/
			parameters.add(new Integer(K));
		
			/** The name of the file with the features of the training items.*/
			parameters.add(fileFeaturesTrain);
		
			/** The amount of columns of the file with the features of the training items.*/
			parameters.add(new Integer(cols));
			
			/** If apply entropy or not in fitness calculation. */
			parameters.add(new Boolean(applyEnt));
		
			/** It creates the mating pool from the current GGGP System's population.*/
			matingPool = null;
			matingPool = this.parentSelection(this.getPopulation(), null, this.getFitness(), sizeP, 1, parameters);
		
			/** ################### END CREATE MATING POOL ################## **/
			
			
			
			/** ################### START CREATE POPULATION'S NEXT GENERATION ################## **/
			
			parameters = null;
			parameters = new LinkedList<Object>();
			
			/** The crossover method.*/
			parameters.add(new Integer(2));
			
			/** Maximum Depth of the Derivations.*/
			parameters.add(new Integer(maxDepth));
			
			/** The Element's symbol to which the Evolutionary Gradient will be applied .*/
			parameters.add(symbol);
			
			/** It creates the next generation of individuals from the mating pool .*/
			newIndividuals = null;
			newIndividuals = this.nextGeneration(this.getMatingPool(), parameters);
			
			/** ################### END CREATE POPULATION'S NEXT GENERATION ################## **/
			
			
			
			/** ################### START CHANGE CURRENT POPULATION ################## **/
			
			newPopulation = null;
			newPopulation = this.survivalSelection(this.getPopulation(), newIndividuals, 1, null, elitism, perBest);
			
			try{
				this.clearStructures(rt);
			}catch(GrammarExceptionImpl ex){
				
			}
			
			this.setPopulation(newPopulation);
			
			parameters = null;
			parameters = new LinkedList<Object>();
			parameters.add(new Boolean(applyEnt));
			currentFitness = this.fitness(newPopulation, this.getFeatures(), parameters);
			currentPer =  java.util.Collections.max(currentFitness.values());
			
			this.setFitness(currentFitness);
			
			/** ################### END CHANGE CURRENT POPULATION ################## **/
			
			
			
		}
		
		/** ################### END THE TRAINING PROCESS ################### */
		
		
		
		System.out.println();
		System.out.println("The TRAIN Maximum Fitness is: " + currentPer);
		return newPopulation;
	}
	
	
	/**
	 * 
	 * @param individual
	 * 					  The derivation to be tested.
	 * 
	 * @param d
	 * 			 The features used to test the derivation.
	 * 
	 * @return
	 * 			A list with the test results.
	 * 
	 * @throws GggpExceptionImpl
	 * 
	 */
	public List<Integer> test(Derivation individual, DoubleMatrix2D d) throws GggpExceptionImpl {
		
		List<Integer> res = null;
		Collection<AuxFeatureFitnessImpl> auxFit;
		Iterator<AuxFeatureFitnessImpl> itAux;
		AuxFeatureFitnessImpl auxFitE;
		Collection<Rules> cr;
		Iterator<Rules> itr;
		Rules r;
		int normalClass;
		int injuryClass;
		int fit;
		int falsePositive;
		int falseNegative;
		int indefined;
		boolean evaluation;
		
		
		if((individual != null) && (d != null)){
			
			auxFitE = new AuxFeatureFitnessImpl();
			
			auxFit = auxFitE.mapFeaturesWithLabel(d);
			
			cr = parseRules(individual, "real");
				
			itAux = auxFit.iterator();
			
			fit = 0;
			falsePositive = 0;
			falseNegative = 0;
			indefined = 0;
			
			/** To each feature, all the rules are applied. Only the rules that returns TRUE are considered, and
			 *  in function of the class (normal or injury) associated to the rule, one of two variables are 
			 *  incremented ("normalClass" = normal class, and "injuryClass" = normal class). At the end, the result 
			 *  is classified in FIT (a right prognosis), FALSE_NEGATIVE, FALSE_POSITIVE or INDEFINED. */
			while (itAux.hasNext()){
					
				auxFitE = itAux.next();
					
				itr = cr.iterator();
				
				normalClass = 0;
				injuryClass = 0;
				
				while(itr.hasNext()){
					
					r = itr.next();
					
					evaluation = r.evaluatesRule(auxFitE.getFeatures(), r.getAntecedent());
						
					if(evaluation){
							
						if(((r.getConsecuent()).toUpperCase()).equals("NORMAL")){
								
							normalClass ++;
								
						}else {
								
								injuryClass ++;
						}
					}
				}
					
				/** Classification of the results. */
				if(auxFitE.getLabel().equals(new Double(0))){
						
					if(normalClass > injuryClass) {
							
						fit ++;	
					
					}else {
						
							if(normalClass < injuryClass) {
							
								falsePositive ++;	
						
							}else {
								
									indefined ++;
							}
						
					}
						
				} else {
						
						if(injuryClass > normalClass) {
							
							fit ++;
						
						}else {
						
							if(injuryClass < normalClass) {
							
								falseNegative ++;	
						
							}else {
								
									indefined ++;
							}
						
						}
				}
			}
			
			res = new LinkedList<Integer>();
			
			/** Add the amount of classified items (series). */
			res.add(new Integer(d.rows()));
			
			/** Add the amount of right prognosis (FIT).*/
			res.add(new Integer(fit));
			
			/** Add the amount of false negatives.*/
			res.add(new Integer(falseNegative));
			
			/** Add the amount of false positives.*/
			res.add(new Integer(falsePositive));
			
			/** Add the amount of indefined.*/
			res.add(new Integer(indefined));
			
		}
		
		return res;
	}
	
	
	
	public String classify(Collection<Rules> cr, DoubleMatrix1D feature) throws GggpExceptionImpl{
		
		boolean evaluation;
		Iterator<Rules> itr;
		String res = null;
		Map<String,Double> aux;
		int normalClass;
		int injuryClass;
		Rules r;
		
		if((cr!=null) && (!cr.isEmpty()) && (feature != null)){
			
			aux = new ConcurrentHashMap<String, Double>();
			
			/** The features's real values are from 0 to 17. */
			aux.put((new String("secDifTorMax").toUpperCase()), feature.get(0));
			aux.put((new String("secDifAngTorMax").toUpperCase()), feature.get(1));
			aux.put((new String("secDifTorMin").toUpperCase()), feature.get(2));
			aux.put((new String("secDifAngTorMin").toUpperCase()), feature.get(3));
			aux.put((new String("torMax").toUpperCase()), feature.get(4));
			aux.put((new String("angTorMax").toUpperCase()), feature.get(5));
			aux.put((new String("timTorMax").toUpperCase()), feature.get(6));
			aux.put((new String("torMin").toUpperCase()), feature.get(7));
			aux.put((new String("angTorMin").toUpperCase()), feature.get(8));
			aux.put((new String("timTorMin").toUpperCase()), feature.get(9));
			aux.put((new String("timAvgTorMaxExt").toUpperCase()), feature.get(10));
			aux.put((new String("desAvgTimMaxExt").toUpperCase()), feature.get(11));
			aux.put((new String("timAvgTorMinFlx").toUpperCase()), feature.get(12));
			aux.put((new String("desAvgTimMinFlx").toUpperCase()), feature.get(13));
			aux.put((new String("torAvgExt").toUpperCase()), feature.get(14));
			aux.put((new String("desAvgTorExt").toUpperCase()), feature.get(15));
			aux.put((new String("torAvgFlx").toUpperCase()), feature.get(16));
			aux.put((new String("desAvgTorFlx").toUpperCase()), feature.get(17));
			
			itr = cr.iterator();
			
			normalClass = 0;
			injuryClass = 0;
			
			while(itr.hasNext()){
			
				r = itr.next();
				
				evaluation = r.evaluatesRule(aux, r.getAntecedent());
			
				if(evaluation){
					
					if(((r.getConsecuent()).toUpperCase()).equals("NORMAL")){
						
							normalClass ++;
						
					}else{
						
							injuryClass ++;
					}
				
				}
				
			}
			
			if (normalClass > injuryClass){
				res = new String("NORMAL");
			}else{
					if (normalClass < injuryClass){
						res = new String("INJURY");
					}else{
							res = new String("INDEFINITE");
					}
			}
			
			
		}
		
		
		return res;
		
		
	}
	
	
	/**
	 * 
	 * @param d
	 * 			The Derivation from which the Rules's set will be extracted.
	 * 
	 * @param symbol
	 * 				  The symbol of the element that has associated a real value.
	 * 
	 * @return
	 * 			Given a Derivation, the function returns the Rules's set that the Derivation represents.
	 * 
	 * @throws GggpExceptionImpl
	 * 
	 */
	public Collection<Rules> parseRules(Derivation d, String symbol) throws GggpExceptionImpl {
		
		Collection<Rules> res = null;
		Rules rule;
		Collection<Element> leaves;
		Collection<Element> aux;
		ParseDerivation p = new ParseDerivationImpl();
		Collection<Collection<Element>> r;
		Collection<Collection<Element>> r2;
		List<AntElement> antecedent;
		Collection<Collection<Collection<Element>>> ac;
		Iterator<Element> it;
		Iterator<Collection<Element>> it2;
		Iterator<Collection<Collection<Element>>> it3;
		Iterator<Collection<Element>> it4;
		Element el;
		Element elBef;
		AntElement ael;
		
		if (d != null){
		
			res = new LinkedList<Rules>();
			
			try{
				
				if(symbol == null){
					leaves = d.getLeavesInLeftOrder();
				}else{
						leaves = d.getLeavesInLeftOrderWithFunction(symbol);
				}
				
			}catch(Exception e){
				throw new GggpExceptionImpl(e.getMessage());
			}
		
			/** Split the rules. */
			r = p.splitElementsBeforeToken(leaves, ";");
			
			
			res = new LinkedList<Rules>();
			it2 = r.iterator();
			while(it2.hasNext()){
				
				/** Each element is a rule. */
				aux = it2.next();
				
				/** Split the ANTECEDENT and CONSECUENT from a rule. */
				ac = p.splitElementsBetweenTokens(aux, "if", "then");
				
				if(ac.size() !=1){
					throw new GggpExceptionImpl("There is a problem parsing the rules");
				}
				
				
				it3 = ac.iterator();
				r2 = it3.next();
				
				if(r2.size() !=2){
					throw new GggpExceptionImpl("There is a problem parsing the rules");
				}
				
				it4 = r2.iterator();
				
				/** Antecedent. */
				aux = it4.next();
				antecedent = new LinkedList<AntElement>();
				it = aux.iterator();
				while(it.hasNext()){
					
					el = it.next();
					ael = new AntElementImpl(el.getSymbol());
					antecedent.add(ael);
				}
				
				/** Consecuent. */
				aux = it4.next();
				it = aux.iterator();
				el = null;
				elBef = null;
				while(it.hasNext()){
					elBef = el;
					el = it.next();
				}
				
				rule = new RulesImpl(antecedent, elBef.getSymbol());
				res.add(rule);
				
			} 
		}
		
		return res;
	}
}
