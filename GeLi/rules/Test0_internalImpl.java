package rules;

/**
 * 
 *  Test the method evaluatesRule in the class RulesImpl.
 *  
 *  Non Public API
 *  
 * 
 * */


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import core.grammar.CreateGrammar;
import core.grammar.CreateGrammarImpl;
import core.grammar.Derivation;
import core.grammar.DerivationImpl;
import core.grammar.Grammar;
import core.grammar.GrammarExceptionImpl;
import core.grammar.NonTerminal;
import core.grammar.Element;
import core.grammar.Function;
import core.grammar.FunctionImpl;
import core.grammar.ParseDerivation;
import core.grammar.ParseDerivationImpl;

import gggpIsokinetics.ExtractBioSecuenceFeatures;

import util.io.file.GenericFile;



import java.util.Collection;
import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class Test0_internalImpl {

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
	
	
	
	/* Test the rules generation and evaluation over the Isokinetic Grammar. */
	@Test
	public void RulesTest(){
		
		Derivation d2;
		NonTerminal nt = null;
		Collection<NonTerminal> cnt;
		Collection<String> namesFeat;
		Collection<Element> elems;
		Iterator<Element> it;
		Element el;
		Element elBef;
		Collection<Collection<Element>> elemRules;
		Iterator<Collection<Element>> it2;
		Collection<Collection<Collection<Element>>> rule1;
		Iterator<Collection<Collection<Element>>> it3;
		Collection<Collection<Element>> rule2;
		Iterator<Collection<Element>> it4;
		ParseDerivation dp;
		List<AntElement> antecedent;
		AntElement ael;
		Rules r;
		Collection<Rules> cr;
		Iterator<Rules> it5;
		Iterator<AntElement> it6;
		GenericFile genF;
		boolean resEval;
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
		
			d2 = d.getMaxRandomDerivationWithFunction(15, nt, aux, F, "real", -2);
			
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
			
			
			dp = new ParseDerivationImpl();
			
			/** Split the rules. */
			elemRules = dp.splitElementsBeforeToken(elems, ";");
			
			Assert.assertNotNull(elemRules);
			
			cr = new LinkedList<Rules>();
			it2 = elemRules.iterator();
			while(it2.hasNext()){
				
				/** Each element is a rule. */
				elems = it2.next();
				
				/** Split the ANTECEDENT and CONSECUENT from a rule. */
				rule1 = dp.splitElementsBetweenTokens(elems, "if", "then");
				Assert.assertTrue(rule1.size() == 1);
				
				it3 = rule1.iterator();
				rule2 = it3.next();
				Assert.assertTrue(rule2.size() == 2);
				
				it4 = rule2.iterator();
				
				/** Antecedent. */
				elems = it4.next();
				antecedent = new LinkedList<AntElement>();
				it = elems.iterator();
				while(it.hasNext()){
					
					el = it.next();
					ael = new AntElementImpl(el.getSymbol());
					antecedent.add(ael);
				}
				
				/** Consecuent. */
				elems = it4.next();
				it = elems.iterator();
				el = null;
				elBef = null;
				while(it.hasNext()){
					elBef = el;
					el = it.next();
				}
				
				r = new RulesImpl(antecedent, elBef.getSymbol());
				cr.add(r);
				
			} 
			
			/** Print the rules generated. */
			it5 = cr.iterator();
			while(it5.hasNext()){
				System.out.println();
				System.out.println("RULE");
				r = it5.next();
				antecedent = r.getAntecedent();
				System.out.print("Antecedent: ");
				it6 = antecedent.iterator();
				while(it6.hasNext()){
					ael = it6.next();
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
			it5 = cr.iterator();
			r = it5.next();
			
			System.out.println();
			System.out.println("RULE TO EVALUATE");
			
			antecedent = r.getAntecedent();
			System.out.print("Antecedent: ");
			it6 = antecedent.iterator();
			while(it6.hasNext()){
				ael = it6.next();
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
