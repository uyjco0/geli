package gggpIsokinetics;

/**
 * 
 *  Test the method the main methods from core.grammar package over the Isokinetic Grammar, and the 
 *  gggpIsokinetics class's method parseRules.
 *  
 *  Non Public API
 *  
 * 
 * */


import org.junit.Assert;
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
import core.grammar.Production;
import core.grammar.Element;
import core.grammar.Function;
import core.grammar.FunctionImpl;

import rules.AntElement;
import rules.Rules;

import util.io.file.GenericFile;



import java.util.Collection;
import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class Test1_internalImpl {

	private static CreateGrammar cr;
	private static Grammar g;
	private static Derivation d;
	
	/* Initialize the necessary structures */
	@BeforeClass
	public static void InicioTest(){
		
		try{
			
			cr = new CreateGrammarImpl("c:/Archivos de programa/GeLi/Test/IsokineticGrammar.gr");
			cr.loadGrammar();
			g = cr.getGrammar();
			d = new DerivationImpl(g);
			
		}catch(GrammarExceptionImpl ex){
			  System.out.println(ex.getMessage());
		 }
	}
	
	
	
	/* Test the loading of the Isokinetic Grammar. */
	@Test
	public void loadAndDerivateIsokineticGrammarTest(){
		
		Collection<Production> cp;
		Iterator<Production> it;
		Collection<Element> ce;
		Iterator<Element> it2;
		Element e2;
		Production p;
		
			
		try{
		
			cp = g.getProductions();
			
			if( cp != null){
				
				it = cp.iterator();
				while(it.hasNext()){
					p = it.next();
					System.out.println("Prod: " + p.getSymbol());
					System.out.print("Left: " + (p.getLeft(g)).getSymbol());
					ce = p.getRights(g);
					if(ce != null){
						it2 = ce.iterator();
						while(it2.hasNext()){
							e2 = it2.next();
							System.out.print(" Elem: " + e2.getSymbol());
						}
						
					}else{
							System.out.println("The production doesn't have right symbols");
					}
					System.out.println();
					System.out.println("End Prod: " + p.getSymbol());
					System.out.println();
				}
				
				
			}else{
					System.out.println("There aren't productions");
			}
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
				
	}
	
	
	/* Test the loading of the Isokinetic Grammar. */
	@Test
	@Ignore
	public void loadAndDerivateIsokineticGrammarTest2(){
		
		NonTerminal nt = null;
		Collection<NonTerminal> cnt;
		Collection<Production> cp;
		Iterator<Production> it;
		Collection<Element> ce;
		Iterator<Element> it2;
		Element e2;
		Production p;
		
			
		try{
		
			cnt = g.getNonTerminals();
			for(NonTerminal nt2: cnt){
				if(nt2.getSymbol().equals("EXPR")){
					nt = nt2;
				}
			}
			
			cp = nt.getAllLeftsProd(g);
			
			if( cp != null){
				
				it = cp.iterator();
				while(it.hasNext()){
					p = it.next();
					System.out.println("Prod: " + p.getSymbol());
					System.out.print("Left: " + (p.getLeft(g)).getSymbol());
					ce = p.getRights(g);
					if(ce != null){
						it2 = ce.iterator();
						while(it2.hasNext()){
							e2 = it2.next();
							System.out.print(" Elem: " + e2.getSymbol());
						}
						
					}else{
							System.out.println("The production doesn't have right symbols");
					}
					System.out.println();
					System.out.println("End Prod: " + p.getSymbol());
					System.out.println();
				}
				
				
			}else{
					System.out.println("There aren't productions");
			}
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
				
	}
	
	
	/* Test the method getMaxRandomDerivation over the Isokinetic Grammar. */
	@Test
	@Ignore
	public void getMaxRandomDerivationTest(){
		
		Derivation d2;
		NonTerminal nt = null;
		Collection<NonTerminal> cnt;
		Collection<Element> ce;
		Iterator<Element> it;
		Element el;
		
		
		cnt = g.getNonTerminals();
		for(NonTerminal nt2: cnt){
			if(nt2.getSymbol().equals("S")){
				nt = nt2;
			}
		}
				
		try{
		
			 d2 = d.getMaxRandomDerivation(10, nt);
			 
			 if(d2 != null){
				 d2.crossByLevels();
			 
			 	System.out.println();
			 	System.out.println();
				
			 	ce = d2.getLeavesInLeftOrder();
			 	if(ce != null ){
			 		it = ce.iterator();
			 		while(it.hasNext()){
			 			el = it.next();
			 			System.out.print(el.getSymbol() + " ");
			 		}
			 	}
				
			 	System.out.println();
		    }
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
				
	}

	
	/* Test the method getMaxRandomDerivationWithFunction over the Isokinetic Grammar. */
	@Test
	@Ignore
	public void getMaxRandomDerivationWithFunctionTest(){
		
		Derivation d2;
		NonTerminal nt = null;
		Collection<NonTerminal> cnt;
		Collection<String> namesFeat;
		Collection<Element> cel;
		Iterator<Element> it;
		Element el;
		Function F;
		Map<String, Double> aux;
		
		cnt = g.getNonTerminals();
		for(NonTerminal nt2: cnt){
			if(nt2.getSymbol().equals("S")){
				nt = nt2;
			}
		}
		
		F = new FunctionImpl();
		
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
		
		aux = ExtractBioSecuenceFeatures.calculateAverageFeatures("C:/Jorge/Final/Paper2007/PaperIsokineticos/Datos/Feature18/Total/TotalWithLabel18.sec",19,namesFeat);
		
		try{
		
			d2 = d.getMaxRandomDerivationWithFunction(10, nt, aux, F, "real", -2);
			
			if(d2!=null){
			
				d2.crossByLevelsWithFunction("real");
				
				cel = d2.getLeavesInLeftOrderWithFunction("real");
			
				System.out.println();
				System.out.println("RULES");
				it = cel.iterator();
				while(it.hasNext()){
					el = it.next();
					System.out.print(el.getSymbol());
					System.out.print(" ");
				}
				System.out.println();
				System.out.println();
			}
			
			
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
				
	}
	
	
	/* Test the method parseRules over the Isokinetic Grammar. */
	@Test
	public void parseRulesTest(){
		
		Derivation d2;
		NonTerminal nt = null;
		Collection<NonTerminal> cnt;
		Collection<String> namesFeat;
		Collection<Element> elems;
		Iterator<Element> it;
		Element el;
		List<AntElement> antecedent;
		AntElement ael;
		Rules r;
		Collection<Rules> cr;
		Iterator<Rules> it2;
		Iterator<AntElement> it3;
		GenericFile genF;
		boolean resEval;
		Function F;
		Map<String, Double> aux;
		Isokinetics iso;
		
		/** It's calling the GggpImpl empty constructor. */
		iso = new Isokinetics();
		
		cnt = g.getNonTerminals();
		for(NonTerminal nt2: cnt){
			if(nt2.getSymbol().equals("S")){
				nt = nt2;
			}
		}
		
		F = new FunctionImpl();
		
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
		
		aux = ExtractBioSecuenceFeatures.calculateAverageFeatures("C:/Jorge/Final/Paper2007/PaperIsokineticos/Datos/Feature18/Total/TotalWithLabel18.sec",19,namesFeat);
		
		try{
		
			d2 = d.getMaxRandomDerivationWithFunction(18, nt, aux, F, "real", -2);
			
			/** Get the rules from a Derivation. */
			elems = d2.getLeavesInLeftOrderWithFunction("real");
			
			Assert.assertNotNull(elems);
			
			System.out.println();
			System.out.println("RULES");
			it = elems.iterator();
			while(it.hasNext()){
				el = it.next();
				System.out.print(el.getSymbol());
				System.out.print(" ");
			}
			System.out.println();
			System.out.println();
			
			
			cr = iso.parseRules(d2, "real");
			
			/** Print the rules generated. */
			it2 = cr.iterator();
			while(it2.hasNext()){
				System.out.println();
				System.out.println("RULE");
				r = it2.next();
				antecedent = r.getAntecedent();
				System.out.print("Antecedent: ");
				it3 = antecedent.iterator();
				while(it3.hasNext()){
					ael = it3.next();
					System.out.print(ael.getSymbol() + " ");
				}
				System.out.print("  Consecuent: " + r.getConsecuent());
				System.out.println();
			}
			
			
			genF = new GenericFile();
			genF.setLocale(Locale.US);
			genF.load("C:/Jorge/Final/Paper2007/PaperIsokineticos/Datos/Feature18/Total/TotalWithLabel18.sec");
			
			aux = new ConcurrentHashMap<String, Double>();
			
			/** Get the first features row. */
			aux.put((new String("secDifTorMax").toUpperCase()), genF.getData().get(0, 0));
			aux.put((new String("secDifAngTorMax").toUpperCase()), genF.getData().get(0, 1));
			aux.put((new String("secDifTorMin").toUpperCase()), genF.getData().get(0, 2));
			aux.put((new String("secDifAngTorMin").toUpperCase()), genF.getData().get(0, 3));
			aux.put((new String("torMax").toUpperCase()), genF.getData().get(0, 4));
			aux.put((new String("angTorMax").toUpperCase()), genF.getData().get(0, 5));
			aux.put((new String("timTorMax").toUpperCase()), genF.getData().get(0, 6));
			aux.put((new String("torMin").toUpperCase()), genF.getData().get(0, 7));
			aux.put((new String("angTorMin").toUpperCase()), genF.getData().get(0, 8));
			aux.put((new String("timTorMin").toUpperCase()), genF.getData().get(0, 9));
			aux.put((new String("timAvgTorMaxExt").toUpperCase()), genF.getData().get(0, 10));
			aux.put((new String("desAvgTimMaxExt").toUpperCase()), genF.getData().get(0, 11));
			aux.put((new String("timAvgTorMinFlx").toUpperCase()), genF.getData().get(0, 12));
			aux.put((new String("desAvgTimMinFlx").toUpperCase()), genF.getData().get(0, 13));
			aux.put((new String("torAvgExt").toUpperCase()), genF.getData().get(0, 14));
			aux.put((new String("desAvgTorExt").toUpperCase()), genF.getData().get(0, 15));
			aux.put((new String("torAvgFlx").toUpperCase()), genF.getData().get(0, 16));
			aux.put((new String("desAvgTorFlx").toUpperCase()), genF.getData().get(0, 17));
			
			System.out.println();
			System.out.println("FEATURE TO EVALUATE");
			
			System.out.println("secDifTorMax: " + aux.get((new String("secDifTorMax").toUpperCase())));
			System.out.println("secDifAngTorMax: " + aux.get((new String("secDifAngTorMax").toUpperCase())));
			System.out.println("secDifTorMin: " + aux.get((new String("secDifTorMin").toUpperCase())));
			System.out.println("secDifAngTorMin: " + aux.get((new String("secDifAngTorMin").toUpperCase())));
			System.out.println("torMax: " + aux.get((new String("torMax").toUpperCase())));
			System.out.println("angTorMax: " + aux.get((new String("angTorMax").toUpperCase())));
			System.out.println("timTorMax: " + aux.get((new String("timTorMax").toUpperCase())));
			System.out.println("torMin: " + aux.get((new String("torMin").toUpperCase())));
			System.out.println("angTorMin: " + aux.get((new String("angTorMin").toUpperCase())));
			System.out.println("timTorMin: " + aux.get((new String("timTorMin").toUpperCase())));
			System.out.println("timAvgTorMaxExt: " + aux.get((new String("timAvgTorMaxExt").toUpperCase())));
			System.out.println("desAvgTimMaxExt: " + aux.get((new String("desAvgTimMaxExt").toUpperCase())));
			System.out.println("timAvgTorMinFlx: " + aux.get((new String("timAvgTorMinFlx").toUpperCase())));
			System.out.println("desAvgTimMinFlx: " + aux.get((new String("desAvgTimMinFlx").toUpperCase())));
			System.out.println("torAvgExt: " + aux.get((new String("torAvgExt").toUpperCase())));
			System.out.println("desAvgTorExt: " + aux.get((new String("desAvgTorExt").toUpperCase())));
			System.out.println("torAvgFlx: " + aux.get((new String("torAvgFlx").toUpperCase())));
			System.out.println("desAvgTorFlx: " + aux.get((new String("desAvgTorFlx").toUpperCase())));
			
			/** Get the first rule. */
			it2 = cr.iterator();
			r = it2.next();
			
			System.out.println();
			System.out.println("RULE TO EVALUATE");
			
			antecedent = r.getAntecedent();
			System.out.print("Antecedent: ");
			it3 = antecedent.iterator();
			while(it3.hasNext()){
				ael = it3.next();
				System.out.print(ael.getSymbol() + " ");
			}
			System.out.print("  Consecuent: " + r.getConsecuent());
			System.out.println();
			
			resEval = r.evaluatesRule(aux, r.getAntecedent());
			
			System.out.println();
			System.out.println(resEval);
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
				
	}
	
	

	
}
