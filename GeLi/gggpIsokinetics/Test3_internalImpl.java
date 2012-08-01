package gggpIsokinetics;

/**
 * 
 *  Run the methods train, test and classify of the Isokinetic class.
 *  
 *  Non Public API
 *  
 * 
 * */



import rules.Rules;

import util.io.file.GenericFile;

import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.DoubleMatrix1D;
import cern.colt.matrix.impl.DenseDoubleMatrix1D;


import core.grammar.Element;
import core.grammar.Derivation;
import core.grammar.GrammarExceptionImpl;


import grammarGuidedGeneticProgramming.GggpExceptionImpl;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Iterator;

import org.junit.Test;


public class Test3_internalImpl {

	
	@Test
	public void trainTest(){
	//public static void main(String[] args) {
		
		Isokinetics gg;
		double maxFit;
		Double fitness;
		Derivation dFit;
		Collection<Element> ce;
		Iterator<Element> ite;
		Collection<Derivation> res;
		Iterator<Derivation> it;
		Derivation d;
		Element elem;
		GenericFile f;
		DoubleMatrix2D df;
		List<Integer> res1;
		Iterator<Integer> it2;
		List<Integer> resFinal = null;
		double d1;
		double d2;
		Collection<Rules> cr;
		DoubleMatrix1D features;
		String resClass;
		
		gg = new Isokinetics();
		
		try{
			
			res = gg.train("c:/Archivos de programa/GeLi/Test/IsokineticGrammar.gr", 
					"C:/Jorge/Final/Paper2007/PaperIsokineticos/Datos/Feature18/Total/TotalWithLabel18.sec",
				     "C:/Jorge/Final/Paper2007/PaperIsokineticos/Datos/Feature18/Total/TotalWithLabel-Train18.sec",
				     19, 60, 25, "real", -2, 7, 5, true, 30, 0.8, true);
			
			
			
			f = new GenericFile();
			
			try {
			
				f.load("C:/Jorge/Final/Paper2007/PaperIsokineticos/Datos/Feature18/Total/TotalWithLabel-Test18.sec");
				
			}catch (IOException e){
				throw new GggpExceptionImpl(e.getMessage());
			}
			
			df = f.getData();
			
			maxFit = Double.MIN_VALUE;
			
			it = res.iterator();
			
			
			dFit = null;
			System.out.println();
			
			while(it.hasNext()){
				
				d = it.next();
				res1 = gg.test(d, df);
				it2 = res1.iterator();
				d1 = it2.next();
				d2 = it2.next();
				fitness = d2/d1;
				
				if(fitness > maxFit){
					dFit = d;
					resFinal = res1;
					maxFit = fitness;
				}
				
			}
			
			
			if( (dFit != null) && (resFinal != null)){
				
				it2 = resFinal.iterator();
				d1 = it2.next();
				d2 = it2.next();
				fitness = d2/d1;
				System.out.println();
				System.out.println("The TEST Maximum Fitness is: " + fitness);
			
				d1 = it2.next();
				System.out.println("The amount of false negatives is: " + d1);
			
				d1 = it2.next();
				System.out.println("The amount of false positives is: " + d1);
			
				d1 = it2.next();
				System.out.println("The amount of indefined is: " + d1);
			
				System.out.println();
				System.out.println("RULE's DB");
				ce= dFit.getLeavesInLeftOrderWithFunction("real");
				ite = ce.iterator();
				while(ite.hasNext()){
					elem = ite.next();
					System.out.print(elem.getSymbol() + " ");
				}
			
			
				/** Test the classify method. */
				cr = gg.parseRules(dFit, "real");
			
				features = new DenseDoubleMatrix1D(18);
				features.set(0,3);
				features.set(1,3);
				features.set(2,3);
				features.set(3,3);
				features.set(4,3);
				features.set(5,3);
				features.set(6,3);
				features.set(7,3);
				features.set(8,3);
				features.set(9,3);
				features.set(10,3);
				features.set(11,3);
				features.set(12,3);
				features.set(13,3);
				features.set(14,3);
				features.set(15,3);
				features.set(16,3);
				features.set(17,3);
			
				resClass = gg.classify(cr, features);
				System.out.println();
				System.out.println();
				System.out.println("The classification result is: " + resClass);
				
			}else{
					System.out.println();
					System.out.println("The TEST fitness is 0");
			}
			
		
		}catch(GggpExceptionImpl e){
			System.out.println(e.getMessage());
		}catch(GrammarExceptionImpl e2){
			System.out.println(e2.getMessage());
		}
			
	}
	
	
}
