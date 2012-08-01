package grammarGuidedGeneticProgramming;


/** 
 * 
 * 
 * Interface that defines the methods that are required to create and manipulate a Grammar-guided Genetic 
 * Programming (GGGP) System.
 * 
 * The interface encapsulates (hides) the implementation of the methods used for the creation 
 * and manipulation of a GGGP System.
 * 
 * 
 * It belongs to the Public API.
 * 
 * @author Jorge Couchet.
 * 
 * 
 **/


import java.util.List;
import java.util.Collection;
import java.util.Map;



public interface Gggp <I, T, S, U, G> {

	
	
	/**
	 * 
	 * 
	 * Load the Grammar associated to the GGGP System.
	 * 
	 * @param fileName
	 * 					The name and path of the file with the Grammar's definition.
	 * 
	 * Throws a GrammarExceptionImpl if there was a problem with the Grammar.
	 * 
	 * 
	 **/
	public G loadGrammar(String fileName)throws GggpExceptionImpl;
	
	
	/**
	 * 
	 * 
	 * Load the features's vector used to calculate the fitness in the GGGP System.
	 * 
	 * @param fileName
	 * 					The name and path of the file with the features's vector.
	 * 
	 * @param parameters
	 * 				    A List with the parameters associated to the file to load.
	 *					I define it as a List<Object> and not as a List<?>, because I want that the
	 * 				 	List had several types inside (then, I really want that it had Objects inside). If
	 * 				 	I use ? instead of Object, then I'm telling that I have a List of any type, but all
	 * 				 	the elements of the List must be of the same type.
	 * 				 	I'm using a List instead of a Collection because the order of the parameters matters (then
	 * 				 	If I use an Iterator over a List, the iterator preserves the order).  
	 * 
	 * 
	 **/
	public S loadFeatures(String fileName, List<Object> parameters) throws GggpExceptionImpl;
	
	
	/**
	 * 
	 * @return
	 * 			The current GGGP System's population.
	 * 
	 */
	public Collection<I> getPopulation();
	
	
	/**
	 * 
	 * @return
	 * 			The Grammar of the GGGP system.
	 * 
	 */
	public G getGrammar();
	
	
	/**
	 * 
	 * @return
	 * 			The features over which of the GGGP system is trained.
	 * 
	 */
	public S getFeatures();
	
	
	/**
	 * 
	 * 
	 * Sets the current GGGP System's population.
	 * 
	 * @param p
	 * 			The new current GGGP System's population.
	 */
	public  void setPopulation(Collection<I> p);
	
	
	/**
	 * 
	 * @return
	 * 		   The mapping between the current population's individuals and theirs fitness.
	 * 
	 */
	public Map<I, T> getFitness();
	
	
	/**
	 * 
	 * Sets the structure that holds the mapping between the current population's individuals and theirs fitness.
	 * 
	 * @param f
	 * 			The mapping between the current population's individuals and theirs fitness.
	 * 
	 */
	public  void setFitness(Map<I, T> f);
	
	
	/**
	 * 
	 * @return
	 * 		   The mating pool created from the current GGGP System's Population.
	 * 
	 */
	public Collection<I> getMatingPool();
	
	
	/**
	 * 
	 * Sets the structure that holds the mating pool created from the current GGGP System's Population.
	 * 
	 * @param mp
	 * 			 The mating pool created from the current GGGP System's Population.
	 * 
	 */
	public  void setMatingPool(Collection<I> mp);
	
	
	/**
	 * 
	 * 
	 * Creates a new population of individuals.
	 * 
	 * @param method
	 * 				 Defines the method to be used in order to create the new population (for example:
	 *               Half and Half, GBIM, etc.).
	 *               
	 * @param parameters
	 * 				 A List with the parameters associated to the method defined.
	 * 				  
	 * 
	 * @return
	 * 			A new population.
	 * 
	 * Throws a GrammarExceptionImpl if there was a problem with the Grammar.
	 * 
	 * 
	 **/
	public Collection<I> initPopulation(int method, List<Object> parameters) throws GggpExceptionImpl;
	
	
	/**
	 * 
	 * 
	 * Calculates the fitness of a collection of individuals using a features's vector.
	 * 
	 * @param individuals
	 * 						The individuals who are used to calculate the fitness.
	 * 
	 * @param features
	 * 					The features's vector used by the fitness's function
	 * 
	 * @param parameters
	 * 					 The extra-parameters associated to the function.
	 * 
	 * @return
	 * 			A map with the fitness of each individual (the individuals are the key of the map).
	 * 
	 * 
	 * 
	 **/
	public Map<I,T> fitness(Collection<I> individuals, S features, List<Object> parameters) throws GggpExceptionImpl;
	
	
	/**
	 * 
	 * 
	 * Calculates the probability of each population's individual to be selected as a parent.
	 * 
	 * @param population
	 * 					The GGGP System's population
	 * 
	 * @param fitness
	 * 				  A map with the fitness of each individual (the individuals are the key of the map).
	 * 
	 * @param method
	 * 				Defines the method to be used in order to calculate the probabilities (for example:
	 *              Fitness Proportional, Ranking Selection, etc.).
	 *              
	 * @param parameters
	 * 				 A List with the parameters associated to the method defined.
	               
	 * 
	 * @return
	 * 			A map with the probability of each individual (the individuals are the key of the map).
	 * 
	 * 
	 **/
	public Map<I, U> parentSelectionProbabilities(Collection<I> population, Map<I,T> fitness, int method,
			                                      List<Object> parameters) throws GggpExceptionImpl;
	
	
	/**
	 * 
	 * 
	 * Creates a the mating pool (the individuals's set to be used to create the next individual's generation).
	 * 
	 * @param individuals
	 * 					  The individuals who are used in order to create the mating pool.
	 * 
	 * @param probabilities
	 * 						The individuals's probabilities to be selected for the mating pool (it's calculated
	 *                      using the method parentSelectionProbabilities).
	 *                      
	 * @param fitness
	 * 					The individuals's fitness (it's calculated using the method fitness).
	 * 
	 * @param sizeMatingPool
	 * 						 The parameter that defines the mating pool's size.
	 * 
	 * @param method
	 * 				Defines the method to be used to create the mating pool (for example: Tournament Selection,
	 *              Roulette Wheel, Stochastic Universal Sampling, etc.).
	 *              
	 * @param parameters
	 * 				 A List with the parameters associated to the method defined.
	 *               
	 * 
	 * 
	 **/
	public Collection<I> parentSelection(Collection<I> individuals, Map<I,U> probabilities, 
										 Map<I,T> fitness, int sizeMatingPool, int method,
										 List<Object> parameters) throws GggpExceptionImpl;
	
	
	/**
	 * 
	 * 
	 * Generates the next generation of the GGGP System's population.
	 * 
	 * @param matingPool
	 * 					 The individuals used to create the next generation by crossover.
	 * 
	 * @param parameters
	 * 					 The parameters used in order to select the crossover method and the crossover 
	 *                   operator's parameters.
	 *                   
	 * @return
	 * 			The new individuals generated from the mating pool.
	 * 
	 *
	 */
	public Collection<I> nextGeneration(Collection<I> matingPool, List<Object> parameters) throws GggpExceptionImpl;
	
	
	/**
	 * 
	 * 
	 * Creates the new generation of the GGGP System's population.
	 * 
	 * @param oldIndividuals
	 * 						The GGGP System's population generation to be replaced.
	 * 
	 * @param newIndividuals
	 * 						 The new individuals generated from the mating pool by crossover and mutation that
	 *                       will replace the old individuals, and create the new generation of the GGGP System's 
	 *                       population.
	 *                       
	 * @param method
	 * 				Defines the method to be used to create the new population's generation (for example:
	 * 				Generational Model, Steady-state Model, etc.).
	 * 
	 * @param parameters
	 * 				 A List with the parameters associated to the method defined. 
	 * 
	 * @param elitism
	 * 				  Defines if the elitism procedure will be applied or not.
	 * 
	 * @param perBest
	 * 			  Defines the percentage of the fitest individuals from the old generation which'll
	 *            replace (aleatory) the same individuals percentage in the new generation.
	 * 
	 * @return
	 * 		   The new generation of the GGGP System's population.
	 * 		  
	 * 
	 * 
	 **/
	public Collection<I> survivalSelection(Collection<I> oldIndividuals, Collection<I> newIndividuals, 
			                               int method, List<Object> parameters, boolean elitism, 
			                               int perBest) throws GggpExceptionImpl;
	
	/**
	 * 
	 * 
	 * Creates new individuals from a parent'set (usually consisting of two individuals), using a crossover
	 * operation.
	 * 
	 * @param parents
	 * 				  The parent's set to be used to create the new individuals.
	 * 
	 * @param method
	 * 				 Defines the crossover's method to be used (for example: GBX, etc).
	 * 
	 * @param parameters
	 * 				 A List with the parameters associated to the method defined. 
	 * 
	 * @return
	 * 		   The new individuals generated by crossover.
	 * 
	 * 
	 **/
	public Collection<I> crossover(Collection<I> parents, int method, List<Object> parameters) throws GggpExceptionImpl;
	
	
	/**
	 * 
	 * 
	 * Creates new individuals from a individuals'set (usually consisting of two individuals), using a 
	 * mutation operation.
	 * 
	 * @param individuals
	 * 					  The individual's set to be mutated.
	 * 
	 * @param method
	 * 				 Defines the mutation's method to be used.
	 * 
	 * @param parameters
	 * 				 A List with the parameters associated to the method defined. 
	 * 
	 * @return
	 * 		   The new mutated individuals.
	 * 
	 * 
	 **/
	public Collection<I> mutation(Collection<I> individuals, int method, List<Object> parameters) throws GggpExceptionImpl;
	
	
	/**
	 * 
	 * 
	 * Calculates a diversity's measure of the GGGP System's population.
	 * 
	 * @param individuals
	 * 					  The individuals who are used to calculate the diversity.
	 * 
	 * @param method
	 * 				 Defines the method used to calculate the diversity (for example: Entropy, Fitness Variation,
	 *               Genotype Variation, etc.).
	 *               
	 * @param parameters
	 * 				 A List with the parameters associated to the method defined.               
	 * 
	 * 
	 **/
	public double populationDiversity(Collection<I> individuals, int method, List<Object> parameters) 
	                                  throws GggpExceptionImpl;
	
}
