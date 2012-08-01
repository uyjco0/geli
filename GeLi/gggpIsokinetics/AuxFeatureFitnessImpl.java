package gggpIsokinetics;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cern.colt.matrix.DoubleMatrix2D;


/**
 * 
 * Helper class for the function Fitness.
 * 
 * 
 * The class doesn't belong to the Public API.
 * 
 * 
 * @author Jorge Couchet
 *
 */

final class AuxFeatureFitnessImpl {
	
	/** It holds the mapping between the feature's name and its real value. */
	private Map<String, Double> features;
	
	/** It holds the feature's label. 0 = Normal, 1 = Injury. */
	private double label;
	
	
	/**
	 * 
	 * Empty constructor.
	 * 
	 */
	public AuxFeatureFitnessImpl(){
		
		this.features = null;
		this.label = -1;
		
	}
	
	
	/**
	 * 
	 * Constructor that receives the fields of the class.
	 * 
	 * @param f
	 * 			The mapping between the feature's name and its real value.
	 * 
	 * @param l
	 * 			The feature's label.
	 * 
	 */
	public AuxFeatureFitnessImpl(Map<String, Double> f, Double l){
		
		this.features = f;
		this.label = l;
		
	}
	
	
	public Map<String, Double> getFeatures(){
		
		return this.features;
		
	}
	
	
	public void setFeatures(Map<String, Double> f){
		
		this.features = f;
		
	}
	
	
	public Double getLabel(){
		
		return this.label;
		
	}
	
	
	public void setLabel(Double l){
		
		this.label = l;
		
	}
	
	
	/** The function maps the name of the features against its corresponding real value and label. */
	public Collection<AuxFeatureFitnessImpl> mapFeaturesWithLabel(DoubleMatrix2D features){
		
		Collection<AuxFeatureFitnessImpl> auxFit = null;
		Map<String,Double> aux;
		AuxFeatureFitnessImpl auxFitE;
		
		auxFit = new LinkedList<AuxFeatureFitnessImpl>();
		
		for(int i =0; i < features.rows(); i++){
			
			aux = new ConcurrentHashMap<String, Double>();
			
			/** The features's real values are from 0 to 17. */
			aux.put((new String("secDifTorMax").toUpperCase()), features.get(i, 0));
			aux.put((new String("secDifAngTorMax").toUpperCase()), features.get(i, 1));
			aux.put((new String("secDifTorMin").toUpperCase()), features.get(i, 2));
			aux.put((new String("secDifAngTorMin").toUpperCase()), features.get(i, 3));
			aux.put((new String("torMax").toUpperCase()), features.get(i, 4));
			aux.put((new String("angTorMax").toUpperCase()), features.get(i, 5));
			aux.put((new String("timTorMax").toUpperCase()), features.get(i, 6));
			aux.put((new String("torMin").toUpperCase()), features.get(i, 7));
			aux.put((new String("angTorMin").toUpperCase()), features.get(i, 8));
			aux.put((new String("timTorMin").toUpperCase()), features.get(i, 9));
			aux.put((new String("timAvgTorMaxExt").toUpperCase()), features.get(i, 10));
			aux.put((new String("desAvgTimMaxExt").toUpperCase()), features.get(i, 11));
			aux.put((new String("timAvgTorMinFlx").toUpperCase()), features.get(i, 12));
			aux.put((new String("desAvgTimMinFlx").toUpperCase()), features.get(i, 13));
			aux.put((new String("torAvgExt").toUpperCase()), features.get(i, 14));
			aux.put((new String("desAvgTorExt").toUpperCase()), features.get(i, 15));
			aux.put((new String("torAvgFlx").toUpperCase()), features.get(i, 16));
			aux.put((new String("desAvgTorFlx").toUpperCase()), features.get(i, 17));
			
			/** The last feature (at the position 18) is the label: 0 = NORMAL, 1 = INJURY. */
			auxFitE = new AuxFeatureFitnessImpl(aux, features.get(i, 18));
			
			auxFit.add(auxFitE);
		}
		
		return auxFit;
		
	}
	
	
	/** The function maps the name of the features against its corresponding real value. */
	public Collection<Map<String,Double>> mapFeaturesWithoutLabel(DoubleMatrix2D features){
		
		Collection<Map<String,Double>> auxMap = null;
		Map<String,Double> aux;
		
		auxMap = new LinkedList<Map<String,Double>>();
		
		for(int i =0; i < features.rows(); i++){
			
			aux = new ConcurrentHashMap<String, Double>();
			
			/** The features's real values are from 0 to 17. */
			aux.put((new String("secDifTorMax").toUpperCase()), features.get(i, 0));
			aux.put((new String("secDifAngTorMax").toUpperCase()), features.get(i, 1));
			aux.put((new String("secDifTorMin").toUpperCase()), features.get(i, 2));
			aux.put((new String("secDifAngTorMin").toUpperCase()), features.get(i, 3));
			aux.put((new String("torMax").toUpperCase()), features.get(i, 4));
			aux.put((new String("angTorMax").toUpperCase()), features.get(i, 5));
			aux.put((new String("timTorMax").toUpperCase()), features.get(i, 6));
			aux.put((new String("torMin").toUpperCase()), features.get(i, 7));
			aux.put((new String("angTorMin").toUpperCase()), features.get(i, 8));
			aux.put((new String("timTorMin").toUpperCase()), features.get(i, 9));
			aux.put((new String("timAvgTorMaxExt").toUpperCase()), features.get(i, 10));
			aux.put((new String("desAvgTimMaxExt").toUpperCase()), features.get(i, 11));
			aux.put((new String("timAvgTorMinFlx").toUpperCase()), features.get(i, 12));
			aux.put((new String("desAvgTimMinFlx").toUpperCase()), features.get(i, 13));
			aux.put((new String("torAvgExt").toUpperCase()), features.get(i, 14));
			aux.put((new String("desAvgTorExt").toUpperCase()), features.get(i, 15));
			aux.put((new String("torAvgFlx").toUpperCase()), features.get(i, 16));
			aux.put((new String("desAvgTorFlx").toUpperCase()), features.get(i, 17));
			
			
			auxMap.add(aux);
		}
		
		return auxMap;
		
	}
	
	
	/** Get the initial values of the features, in order to set the initial population. */
	public Map<String, Double> featuresInitialValues(String fileName){
		
		Collection<String> namesFeat;
		Map<String, Double> aux;
		
		namesFeat = new LinkedList<String>();
		namesFeat.add( new String("secDifTorMax").toUpperCase());
		namesFeat.add( new String("secDifAngTorMax").toUpperCase());
		namesFeat.add( new String("secDifTorMin").toUpperCase());
		namesFeat.add( new String("secDifAngTorMin").toUpperCase());
		namesFeat.add( new String("torMax").toUpperCase());
		namesFeat.add( new String("angTorMax").toUpperCase());
		namesFeat.add( new String("timTorMax").toUpperCase());
		namesFeat.add( new String("torMin").toUpperCase());
		namesFeat.add( new String("angTorMin").toUpperCase());
		namesFeat.add( new String("timTorMin").toUpperCase());
		namesFeat.add( new String("timAvgTorMaxExt").toUpperCase());
		namesFeat.add( new String("desAvgTimMaxExt").toUpperCase());
		namesFeat.add( new String("timAvgTorMinFlx").toUpperCase());
		namesFeat.add( new String("desAvgTimMinFlx").toUpperCase());
		namesFeat.add( new String("torAvgExt").toUpperCase());
		namesFeat.add( new String("desAvgTorExt").toUpperCase());
		namesFeat.add( new String("torAvgFlx").toUpperCase());
		namesFeat.add( new String("desAvgTorFlx").toUpperCase());
		
		aux = ExtractBioSecuenceFeatures.calculateAverageFeatures(fileName,19,namesFeat);
		
		return aux;
	}
	

}
