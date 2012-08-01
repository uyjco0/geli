package core.grammar;

/**
 * 
 * 
 * @author Jorge Couchet
 * 
 */

import java.util.LinkedList;

import core.grammar.Function;
import util.random.Sort;
import java.util.Random;

/** 
 *
 * Class that offers a concrete implementation of the interface Function.
 * The function's behavior is: FunctionImpl(f) = f operator increment. That is, the function applies over it 
 * the operator with increment. For example if operator is "+", the function returns: f + increment .
 * 
 * It belongs to the Public API.
 *
 * @author Jorge Couchet.
 * 
 * 
 **/

public class FunctionImpl implements Function {
	
	
	
	/** The operator associated to the function. */
	private String operator;
	
	/** The increment to be applied by the operator. */
	private double increment;
	
	
	/**
	 * 
	 * The empty constructor.
	 * 
	 * 
	 **/
	public FunctionImpl(){
		
		this.operator = null;
		this.increment = 0;
		
	}
	
	
	/**
	 * 
	 * The constructor.
	 * 
	 * @param opr
	 * 			The operator associated to the function
	 * 
	 * @param incr
	 * 			The increment associated to the function
	 * 			
	 * 
	 **/
	public FunctionImpl(String opr, double incr){
		
		this.operator = opr;
		this.increment = incr;
		
	}	
	
	
	public void clearFunction(){
		this.operator = null;
	}
	
	
	/**
	 * 
	 * @return
	 * 	       The operator associated to the function
	 * 
	 */
	public String getOperator(){
		return this.operator;
	}
	
	
	public Function cloneFunction(){
		Function res;
		
		res = new FunctionImpl(new String(this.operator), this.increment);
		
		return res;
	}
	
	
	/**
	 * 
	 * @return
	 * 	       The increment associated to the function
	 * 
	 */
	public double getIncrement(){
		return this.increment;
	}

	
	public Function updateFunction( double p){
		LinkedList<String> ops;
		LinkedList<Double> dist;
		String op;
		String opr;
		Sort<String> s;
		Random generator;
		double incr;
		Function res = null;
		
		ops = new LinkedList<String>();
		ops.add("Y");
		ops.add("N");
		
		dist = new LinkedList<Double>();
		dist.add(new Double(p));
		dist.add(new Double(1.0 - p));
		
		s = new Sort<String>();
		op = s.sortElement(ops, dist);
		
		if(op.equals("Y")){
			
			ops = new LinkedList<String>();
			ops.add("+");
			ops.add("-");
			
			dist = new LinkedList<Double>();
			dist.add(new Double(0.5));
			dist.add(new Double(0.5));
			
			s = new Sort<String>();
			opr = s.sortElement(ops, dist);
			
			generator = new Random();
			/** Generates a number uniformly distributed between (0,1] */
			incr = generator.nextDouble();
			if (Double.compare(incr, 0.0) == 0){
				incr = 0.001;
			}
				
			res = new FunctionImpl(opr, incr);
			
		} else{
				res = new FunctionImpl(operator, increment);
		}
				
		return res;
		
	}
	
	
	public Function createFunction(){
		LinkedList<String> ops;
		LinkedList<Double> dist;
		String op;
		Sort<String> s;
		Function res = null;
		Random generator;
		double incr;
		
		ops = new LinkedList<String>();
		ops.add("+");
		ops.add("-");
		
		dist = new LinkedList<Double>();
		dist.add(new Double(0.5));
		dist.add(new Double(0.5));
		
		s = new Sort<String>();
		op = s.sortElement(ops, dist);
		
		generator = new Random();
		/** Generates a number uniformly distributed between (0,1] */
		incr = generator.nextDouble();
		if (Double.compare(incr,0.0) == 0){
			incr = 0.001;
		}
		
		res = new FunctionImpl(op, incr);
		
		return res;
		
	}
	
	
	public Double applyFunction(Double f, double p){
		LinkedList<String> ops;
		LinkedList<Double> dist;
		Sort<String> s;
		String op;
		Double res = null;
		
		if (f != null) {
			
			ops = new LinkedList<String>();
			ops.add("Y");
			ops.add("N");
			
			dist = new LinkedList<Double>();
			dist.add(new Double(p));
			dist.add(new Double(1.0 - p));
			
			s = new Sort<String>();
			op = s.sortElement(ops, dist);
			
			if (op.equals("Y")){
				
				if (operator.equals("+")){
					res = new Double(f.doubleValue() + increment);
				} else {
						res = new Double (f.doubleValue() - increment);
				}
				
			} else {
					res = f;
			}
		} 
		
		return res;
		
	}
	



}
