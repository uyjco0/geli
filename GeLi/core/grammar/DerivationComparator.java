package core.grammar;

import core.grammar.Derivation;
import java.util.Comparator;
import java.util.Map;

public class DerivationComparator implements Comparator<Derivation> {

	
	private Map<Derivation, Double> fitness;
	
	
	public DerivationComparator(Map<Derivation, Double> fitness) {
		
		 this.fitness = fitness;
	}
	
	
	public int compare(Derivation d1, Derivation d2) {
		
		int result;
		
		if(this.fitness.get(d1) < this.fitness.get(d2)){
			
			result = -1;
			
		}else {
			
				if(this.fitness.get(d1) == this.fitness.get(d2)){
				
					result = 0;
				
				} else {
					
						result = 1;
				}
		}

		return result;
	}
	

}
