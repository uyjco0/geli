package core.grammar;


/** 
 * 
 * Class that offers a concrete implementation of the interface StochasticContextFreeGrammar.
 * 
 * It belongs to the Public API.
 *
 * @author Jorge Couchet.
 * 
 * 
 **/

 import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Collection;

public class StochasticContextFreeGrammarImpl extends GrammarImpl implements StochasticContextFreeGrammar {

	
	
	/** Flag in order to check if the Grammar is stochastic or a standard one. */
	private boolean isStochastic;
	
	/** Structure in order to hold the probabilities associated to the Productions. */
	private Map<Integer, Double> probabilities;
	
	/**
	 * 
	 * Empty constructor.
	 * 
	 * */
	public StochasticContextFreeGrammarImpl(){
		super();
		
		/*This is the default value. */
		this.isStochastic = true;
		
		this.probabilities = new ConcurrentHashMap<Integer,Double>(); 
	}
	
	
	public boolean checkIsFulfilledProbabilityRestriction(NonTerminal nt) {
		Collection<Production> cp;
		double pr;
		if((this.isStochastic) && (nt!=null) && (nt.getId()>=0) && (nt.getSymbol()!=null)){
			cp = nt.getAllLeftsProd(this);
			if(cp!=null){
				pr = 0;
				for(Production p: cp){
					pr = pr + this.getProbability(p);
				}
				/* Allows a tolerance range of 0.000001. */
				if((0.999999 <= pr) && (pr <= 1.000001)){
					return true;
				}else{
					return false;
				}
			}else{
					return true;
			}
			
		}else{
			return true;
		}
		
	}

	
	public boolean checkIsFulfilledProbabilityRestriction() {
		Collection<NonTerminal> cnt;
		if(this.isStochastic){
			cnt = this.getNonTerminals();
			if(cnt!=null){
				for(NonTerminal nt:cnt){
					if(!this.checkIsFulfilledProbabilityRestriction(nt)){
						return false;
					}
				}
				return true;
			}else{
					return true;
			}
		}else{
			return false;
		}
	}

	
	public double getProbability(Production p) {
		double result=-1;
		/* checks if the ID of p is in the Map. */
		if (this.probabilities.containsKey(p.getId())){
			result = this.probabilities.get(p.getId());
		}
		return result;
	}

	
	public Production getProduction(NonTerminal nt) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public double setProbability(Production p, double pr) throws GrammarExceptionImpl {
		Double i;
		if((p==null)||(p.getId()<0)||(p.getSymbol()==null)||(pr<0)){
			throw new GrammarExceptionImpl("The Production is NULL or the probability is negative");
		}
		
		/* Maps the ID of the Production agains his probability. */
		i = this.probabilities.put(p.getId(), pr);	
		if(i==null){
			return -3;
		}else{
			return i.doubleValue();
		}
		
	}


	public boolean getType() {
		
		return this.isStochastic;
	}


	public void setType(boolean type) {
		
		this.isStochastic = type;
		
	}

}
