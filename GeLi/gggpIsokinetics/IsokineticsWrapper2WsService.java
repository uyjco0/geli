package gggpIsokinetics;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.DoubleMatrix1D;
import cern.colt.matrix.impl.DenseDoubleMatrix1D;

import core.grammar.GrammarExceptionImpl;
import core.grammar.Derivation;
import core.grammar.Element;

import grammarGuidedGeneticProgramming.GggpExceptionImpl;

import gggpIsokinetics.Isokinetics;

import rules.Rules;
import rules.RulesImpl;
import rules.AntElement;
import rules.AntElementImpl;

import util.io.file.GenericFile;

import java.io.FileNotFoundException;
import java.io.IOException;


public class IsokineticsWrapper2WsService {
	
	
	
	public int test(int param){
		
		return param*2;
		
	}
	
	
	public String[] trainSystem(String namefG, String namefFT, String namefTr, String namefTs, int cols, int sizeP, 
								int maxDepth, String symbol, int symbol2, int K, int perBest, boolean elitism, 
								int amountIter, double percentage, boolean applyEnt) throws Exception{
		
		
		String[] res = null;
		Isokinetics iso;
		Collection<Derivation> cd;
		GenericFile f;
		Iterator<Derivation> it;
		Collection<Element> ce;
		Iterator<Element> itEl;
		Derivation d;
		DoubleMatrix2D df;
		List<Integer> res1;
		Iterator<Integer> it2;
		List<Integer> resFinal = null;
		Derivation dFit;
		double maxFit;
		Double fitness;
		double d1;
		double d2;
		int pos;
		
		try{
		
			iso = new Isokinetics();
		
			cd = iso.train(namefG, namefFT, namefTr, cols, sizeP, maxDepth, symbol, symbol2, K, perBest, 
				           elitism, amountIter, percentage, applyEnt);
		
			f = new GenericFile();
		
			f.load(namefTs);
			
			df = f.getData();
		
			maxFit = Double.MIN_VALUE;
		
			it = cd.iterator();
		
			dFit = null;
			System.out.println();
		
			while(it.hasNext()){
			
				d = it.next();
				res1 = iso.test(d, df);
				it2 = res1.iterator();
				d1 = it2.next();
				d2 = it2.next();
				fitness = d2/d1;
			
				if(fitness > maxFit){
					dFit = d;
					resFinal = res1;
				}
			
			}
		
		
			ce= dFit.getLeavesInLeftOrderWithFunction("real");
			
			if( ce != null ){
				
				res = new String[ce.size() + 4];
				
				it2 = resFinal.iterator();
				d1 = it2.next();
				d2 = it2.next();
				fitness = d2/d1;
				
				// The first element is the Fitness.
				res[0]=fitness.toString();
				
				d1 = it2.next();
				//The amount of false negatives.
				res[1] = (new Double(d1)).toString();
				
				
				d1 = it2.next();
				//The amount of false positives.
				res[2] = (new Double(d1)).toString();
				
				d1 = it2.next();
				//The amount of indefined.
				res[3] = (new Double(d1)).toString();
				
				pos = 4;
				itEl = ce.iterator();
				while(itEl.hasNext()){
					res[pos] = (itEl.next()).getSymbol();
					pos++;
				}
			}
			
		} catch(FileNotFoundException ex){
			
			throw new Exception(ex.getMessage());
			
		} catch (IOException ex2){
			
			throw new Exception(ex2.getMessage());
			
		}  catch (GrammarExceptionImpl ex3){
			
			throw new Exception(ex3.getMessage());
			
		} catch (GggpExceptionImpl ex5){
			
			throw new Exception(ex5.getMessage());
		}
		
		
		
		return res;
		
	}
	
		
	public String classify(String[] cs, double [] feature) throws Exception{
		
		Isokinetics iso;
		Collection<Rules> cr;
		DoubleMatrix1D feat;
		String res = null;
		
		cr = this.stringToRules(cs);
		
		feat = this.arrayToMatrix(feature);
		
		iso = new Isokinetics();
		
		try{
			res = iso.classify(cr, feat);
			
		}catch(GggpExceptionImpl ex){
			
			throw new Exception(ex.getMessage());
		}
		
		return  res;
		
	}
	
	
	private Collection<Rules> stringToRules(String[] cs){
		
		Collection<Rules> res = null;
		List<AntElement> antecedent;
		AntElement ae;
		int length;
		int pos;
		Rules r;
		
		if(cs != null){
			
			length = cs.length;
			pos = 0;
			res = new LinkedList<Rules>();
			
			while(pos < length){
			
				antecedent = new LinkedList<AntElement>();
				
				while(!((cs[pos].toUpperCase()).equals("THEN"))){
					
					if(!((cs[pos].toUpperCase()).equals("IF"))){
						ae = new AntElementImpl(cs[pos]);
						antecedent.add(ae);
					}
					
					pos ++;
					
				}
				
				pos = pos + 4;
				
				r = new RulesImpl(antecedent, cs[pos]);
				
				res.add(r);
				
				pos = pos + 3;
				
			}
			
			
		}
		
		return res;
		
	}
	
	
	private DoubleMatrix1D arrayToMatrix(double[] feature){
		
		DoubleMatrix1D res = null;
		int length = 18;
		int pos;
		
		if(feature != null){
			
			res = new DenseDoubleMatrix1D(length);
			
			pos = 0;
			while( pos < length){
				
				res.set(pos, feature[pos]);
				pos++;
				
			}
			
		}
		
		return res;
		
	}
	
	
	

}
