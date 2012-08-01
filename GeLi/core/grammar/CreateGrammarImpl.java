package core.grammar;

import util.io.file.GrammarFile;
import java.io.IOException;
import java.util.Collection;

/** 
 *
 * Class that offers a concrete implementation of the interface CreateGrammar.
 * 
 * It belongs to the Public API.
 *
 * @author Jorge Couchet.
 * 
 * 
 **/

public class CreateGrammarImpl implements CreateGrammar {
	
	
	
	/** If the Grammar's definitions is in a file, holds the name of this file. */
	private String nameFile;
	
	/** Hold the Grammar that the class load. */
	private Grammar grammar;

	
	/**
	 * 
	 * Empty constructor
	 * 
	 **/
	public CreateGrammarImpl(){
		this.nameFile = null;
	}
	
	
	/**
	 * 
	 * Constructor that receives the name of the file that holds the Grammar's definition.
	 * 
	 * @param s
	 * 			The path and name of the file that holds the Grammar's definition.
	 * 
	 **/
	public CreateGrammarImpl(String s){
		this.nameFile = s;
	}
	
	
	public void createInteractiveGrammar() {
		// TODO Auto-generated method stub
		
	}

	
	public void loadGrammar() throws GrammarExceptionImpl {
		boolean isOK;
		double amount;
		double pr;
		Collection<NonTerminal> cnt;
		Collection<Production> cp;
		GrammarFile g = new GrammarFile();
		
		try{
			
			g.load(this.nameFile);
			
			this.grammar = g.getGrammar();
			
			
			/* If a NonTerminal (nt) appears at the right side of a Production, then it must at least have a 
			 * Production where nt is at the left side. */
			cnt = this.grammar.getNonTerminals();
			for(NonTerminal nt: cnt){
				if((!nt.getSymbol().startsWith("Tv"))&&(!nt.getSymbol().startsWith("TV"))&&(!nt.getSymbol().startsWith("Tf"))&&(!nt.getSymbol().startsWith("TF"))){
					cp = nt.getAllRightsProd(this.grammar);
					if(cp!=null){
						cp = nt.getAllLeftsProd(this.grammar);
						if(cp == null){
							throw new GrammarExceptionImpl("The NonTerminal: " + nt.getSymbol() + " doesn´t allow to finish a derivation");
						}
					}
				}
			}
			
			if(((StochasticContextFreeGrammar)this.grammar).getType()){
				isOK = ((StochasticContextFreeGrammar)this.grammar).checkIsFulfilledProbabilityRestriction();
				if(!isOK){
					throw new GrammarExceptionImpl("The restriction of probabilities of a Stochastic Context Free Grammar isn´t fullfiled");
				}
			}else{
					cnt= this.grammar.getNonTerminals();
					if(cnt!=null){
						for(NonTerminal nt:cnt){
							cp = nt.getAllLeftsProd(this.grammar);
							if(cp!=null){
								amount = cp.size();
								if(amount !=0){
									pr = 1/amount;
									for(Production p:cp){
										((StochasticContextFreeGrammar)this.grammar).setProbability(p, pr);
									}
								}
							}
						}
					}
			}
		}catch(IOException e){
			throw new GrammarExceptionImpl(e.getMessage());
		}
			
	}
		
	
	
	
	public void loadGrammar(String s) throws GrammarExceptionImpl{
		this.nameFile = s;
		this.loadGrammar();
		
	}
	
	
	
	
	public Grammar getGrammar(){
		return this.grammar;
	}

}
