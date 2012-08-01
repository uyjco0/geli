package rules;

import java.util.List;
import java.util.Map;
import grammarGuidedGeneticProgramming.GggpExceptionImpl;


/**
 * 
 * Interface that declare the methods that define the responsabilities of a Rule object.
 * 
 * A Rule has one of the following shapes: 
 * 
 * 1)  if (ANTECEDENT) then CONSECUENT .
 * 2)  if NOT (ANTECEDENT) then CONSECUENT .
 * 
 * A Rule Object will belongs to a Knowledge Database (KDB).
 * 
 * A Knowledge Database is a set of rules .
 * 
 * 
 * It belongs to the public API.
 * 
 * @author Jorge Couchet
 * 
 * 
 */

public interface Rules {
	
	
	
	/**
	 * 
	 * The function evaluates the Rule's ANTECEDENT with one of the following shapes: 
	 * 
	 * 1) NOT ( (X1 Lmo Y1) Lo (X2 Lmo Y2) Lo ... Lo (X3 Lmo Y3) )
	 * 2) ( (X1 Lmo Y1) Lo (X2 Lmo Y2) Lo ... Lo (X3 Lmo Y3) )
	 * 
	 * With:
	 * 
	 * 1) Lmo = Logical Mathematical Operator, with Lmo = "==", "!=", "<", "<=", ">", ">="
	 * 2) Lo = Logical Operator, with Lo = "AND", "OR"
	 * 3) Xi and Yi are real values (doubles)
	 * 
	 * @param rd
	 * 			 A mapping between the ANTECEDENT's string label and his corresponding real value.
	 * 
	 * @param antecedent
	 * 					 The ANTECEDENT to be evaluated.
	 * 
	 * @return
	 * 			The boolean value corresponding to the Rule's ANTECEDENT evaluation.
	 * 
	 * 
	 */
	public boolean evaluatesRule (Map<String, Double> rd, List<AntElement> antecedent) throws GggpExceptionImpl;
	
	
	/**
	 * 
	 * @return
	 * 		   The Rule's ANTECEDENT.
	 */
	public List<AntElement> getAntecedent();
	
	
	/**
	 * 
	 * Associates the ANTECEDENT to the Rule.
	 * 
	 * @param a
	 * 			 The ANTECEDENT to be associated to the Rule.
	 */
	public void setAntecedent(List<AntElement> a);
	
	
	/**
	 * 
	 * @return
	 * 			The Rule's CONSECUENT.
	 */
	public String getConsecuent();
	
	
	/**
	 * 
	 * Associates the CONSECUENT to the Rule.
	 * 
	 * @param c
	 * 			The CONSECUENT to be associated to the Rule. 
	 */
	public void setConsecuent(String c);

}
