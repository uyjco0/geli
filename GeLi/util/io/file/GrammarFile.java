package util.io.file;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

import java.util.LinkedList;

import java.io.IOException;

import core.grammar.Grammar;
import core.grammar.GrammarExceptionImpl;
import core.grammar.StochasticContextFreeGrammar;
import core.grammar.StochasticContextFreeGrammarImpl;
import core.grammar.Element;
import core.grammar.NonTerminal;
import core.grammar.NonTerminalImpl;
import core.grammar.Terminal;
import core.grammar.TerminalImpl;
import core.grammar.Production;
import core.grammar.ProductionImpl;



/** 
 * 
 * Class that implements the methods necessaries to load a file with the definition of a Grammar.
 * 
 * 
 	 * 
     *         #########  START of the Explanation of the format of the Grammar File ###########
     * 
     * All the symbols should be separated by a white space or a tab (for example between "#" and "CN"
     * in "# CN", should be a white space or a tab, between "CN" and "=" in "CN =", should be a 
     * white space or a tab, and so on.
     * 
     * # CN ->  This field could be: CN = Standard (normal) Context Free Grammar, or
     *          CS = Stochastic Context Free Grammar. 
     *          If this line doesn´t appear the Grammar by default is Stochastic.
     *            
     * # S = S -> This line defines the Start Symbol, the first S should appear, the second S is the
     *            name of the Start Symbol (it could be another symbol, as for example Z: # S = Z).
     *            This line should be in the second place (if above appear CN/CS) or in the first
     *            place if CN/CS doesn´t appear.
     *            
	 * # N = A B C D -> This line defines the NonTerminal symbols. The first N should appear, the
	 *                  name of the NonTerminals could be anything string.
	 *                  Could be several lines of N:
	 *                  							 # N = A B
	 *                 								 # N = C D
	 *                  
	 * # TV = 1 2 3 4 5 6  -> A line starting with TV defines a class of Terminals that aren't 
	 * 						  operators.
	 *                        Could be several lines of TV's, each TV would create a NonTerminal
	 *                        symbol in the Grammar, the first TV would create the TV1 NonTerminal,
	 *                        the second would create the TV2 NonTerminal, and so on.
	 *                        Each TV defines several Productions in the Grammar, in this case 
	 *                        defines:
	 *                        		       P1: TV1->1, P2: TV1->2, ..., P6: TV1->6
	 *                        
	 * # TV = a b c d e  ->  P7: TV2->a, P8: TV2->b, ..., P11: TV2->e
	 * 
     * # TF = + * /  ->  A line starting with TF defines a class of Terminals that are operators. 
	 * 					 Could be several lines of TF's, each TF would create a NonTerminal
	 *                   symbol in the Grammar, the first TF would create the TF1 NonTerminal,
	 *                   the second would create the TF2 NonTerminal, and so on.
	 *                   Each TF defines several Productions in the Grammar, in this case 
	 *                   defines:
	 *                        		P12: TF1->+ , P13: TF1->*  ,  P14: TF1->/
	 *                        
     * # TF = con add sub  ->  P15: TF2->con , P16: TF2->add  ,  P17: TF2->sub
     * 
     * # P = p_r 1 S A  ->  The line starting with P defines a Production, p_r marks the start
     *                      of the probability value associated to the Production ( the value 0,1
     *                      in this case), next the first symbol should be a NonTerminal (should
     *                      be defined in any N) and is the left side symbol of the Grammar. The
     *                      others symbols are the rigth side symbols (should be defined in any
     *                      N, TV or TF).
     *                      If the Grammar will be a Standard (normal) Context Free Grammar, then
     *                      the probabilities p_r aren´t condidered.
     *                      The Production defined in this line is: P18: S->A with probability 1.
     *                        
     * # P = p_r 0,8 A A + B + A  ->  P19: A->A+B+A with probability 0.8 .
     * 
     * # P = p_r 0,2 A TV1  -> P20: A->TV1 with probability 0.2 . It is equivalent to P:A->1, P:A->2,
     *                         ..., P:A->6 .
     *                         
     * # P = p_r 1 B TV1  ->  P21:B->TV1 with probability 1 .
     * 
     * 
     *   #########  END of the Explanation of the format of the Grammar File ###########
     *
 * 
 * 
 * It belongs to the Public API.
 * 
 * @author Jorge Couchet.
 * 
 * */

public class GrammarFile extends BaseFile_internal {

	
	
	/** Hold the Grammar that the class load. */
	private StochasticContextFreeGrammar g;
	
	/** Hold the NonTerminal and Terminal object created */
	private HashMap<String,Element> map;
	
	
	
	/**
	 * 
	 * Empty constructor.
	 *
	 */
	public GrammarFile(){
		super();
		this.g = new StochasticContextFreeGrammarImpl();
		this.map = new HashMap<String, Element>();
	}
    
	
    /**
     * 
     * Defines not parse data, because is a Grammar file, then the Grammar is in the comment 
     * (a comment start with "#") of the file. 
     * 
     * 
     */
    protected boolean parseData()
    {
    	boolean error = true;
    	if((this.ft).getExt().equals(".gr")){
    		(this.ft).setName("GrammarFile");
            (this.ft).setDescription("File with the definition of the grammar");
        	 this.parseD= false;
        	 error = false;
        }
    	
    	return error;
    }
    
    
    /**
     * 
     * Parses the Grammar that is encoded in the comment (a comment start with "#") of the file, and
     * load this in a Grammar object.
     * 
     *          ##########  Explanation of the format of the Grammar File ##############
     * 
     * All the symbols should be separated by a white space or a tab (for example between "#" and "CN"
     * in "# CN", should be a white space or a tab, between "CN" and "=" in "CN =", should be a 
     * white space or a tab, and so on.
     * 
     * # CN ->  This field could be: CN = Standard (normal) Context Free Grammar, or
     *          CS = Stochastic Context Free Grammar. 
     *          If this line doesn´t appear the Grammar by default is Stochastic.
     *            
     * # S = S -> This line defines the Start Symbol, the first S should appear, the second S is the
     *            name of the Start Symbol (it could be another symbol, as for example Z: # S = Z).
     *            This line should be in the second place (if above appear CN/CS) or in the first
     *            place if CN/CS doesn´t appear.
     *            
	 * # N = A B C D -> This line defines the NonTerminal symbols. The first N should appear, the
	 *                  name of the NonTerminals could be anything string.
	 *                  Could be several lines of N:
	 *                  							 # N = A B
	 *                 								 # N = C D
	 *                  
	 * # TV = 1 2 3 4 5 6  -> A line starting with TV defines a class of Terminals that aren't 
	 * 						  operators.
	 *                        Could be several lines of TV's, each TV would create a NonTerminal
	 *                        symbol in the Grammar, the first TV would create the TV1 NonTerminal,
	 *                        the second would create the TV2 NonTerminal, and so on.
	 *                        Each TV defines several Productions in the Grammar, in this case 
	 *                        defines:
	 *                        		       P1: TV1->1, P2: TV1->2, ..., P6: TV1->6
	 *                        
	 * # TV = a b c d e  ->  P7: TV2->a, P8: TV2->b, ..., P11: TV2->e
	 * 
     * # TF = + * /  ->  A line starting with TF defines a class of Terminals that are operators. 
	 * 					 Could be several lines of TF's, each TF would create a NonTerminal
	 *                   symbol in the Grammar, the first TF would create the TF1 NonTerminal,
	 *                   the second would create the TF2 NonTerminal, and so on.
	 *                   Each TF defines several Productions in the Grammar, in this case 
	 *                   defines:
	 *                        		P12: TF1->+ , P13: TF1->*  ,  P14: TF1->/
	 *                        
     * # TF = con add sub  ->  P15: TF2->con , P16: TF2->add  ,  P17: TF2->sub
     * 
     * # P = p_r 1 S A  ->  The line starting with P defines a Production, p_r marks the start
     *                      of the probability value associated to the Production ( the value 0,1
     *                      in this case), next the first symbol should be a NonTerminal (should
     *                      be defined in any N) and is the left side symbol of the Grammar. The
     *                      others symbols are the rigth side symbols (should be defined in any
     *                      N, TV or TF).
     *                      If the Grammar will be a Standard (normal) Context Free Grammar, then
     *                      the probabilities p_r aren´t condidered.
     *                      The Production defined in this line is: P18: S->A with probability 1.
     *                        
     * # P = p_r 0,8 A A + B + A  ->  P19: A->A+B+A with probability 0.8 .
     * 
     * # P = p_r 0,2 A TV1  -> P20: A->TV1 with probability 0.2 . It is equivalent to P:A->1, P:A->2,
     *                         ..., P:A->6 .
     *                         
     * # P = p_r 1 B TV1  ->  P21:B->TV1 with probability 1 .
     * 
     * 
     * 
     */
    @SuppressWarnings("unchecked")
	protected void parseComment() throws IOException{
    	NonTerminal start = null;
    	NonTerminal nt = null;
    	NonTerminal ntTv = null;
    	NonTerminal ntTf = null;
    	Element left = null;
    	Production p;
    	Terminal te;
    	LinkedList<Element> l = null;
    	String t;
    	String s;
    	double pr=0;
    	int pos;
    	int type = 0;
    	int amountTv = 1;
    	int amountTf = 1;
    	int amountP = 1;
    	int amountSymbols = 0;
    	int amountProd = 1;
    	int id = 1;
    	boolean hasStart;
    	boolean hasToken;
    	boolean hasPr=false;
    	boolean first;
    	boolean first2;
    	boolean control;
    	
    	StringTokenizer st;
    	Class c;
        
    	(this.nf).setMaximumFractionDigits(6);
        hasStart = false;
    	Iterator<String> i = this.comment.iterator();
    	/* Each line of the iterator is a line of the Grammar file. */
		while (i.hasNext())
        {
			s = i.next();
			st = new StringTokenizer(s, this.delimiters);
			first = true;
			first2 = false;
			while (st.hasMoreTokens())
	        {
	            t = st.nextToken();
	            /* Checks if is the first token of the line. */
	            if(first){
	            	type = 0;
	            	first = false;
	            	t = t.toUpperCase();
	            	if(!hasStart){
	            		if(t.equals("CS")){
	            			this.g.setType(true);
	            		}else{
	            				if(t.equals("CN")){
	            					this.g.setType(false);
	            				}else{
	            						if(t.equals("S")){
	            							type = 1;
	            							hasStart = true;
	            						}else{
	            							throw new IOException("There isn´t a Start Symbol, or there isn´t at the first place");
	            						}
	            				}
	            		}
	            		
	            	}
	            	if(t.equals("N")){
	            		type = 2;
	            	}
	            	
	            	if(t.equals("TV")){
	            		type = 3;
	            		try{
	            			
	            			ntTv = new NonTerminalImpl(id, t+amountTv);
	            			id++;
            			    if(this.g.setNonTerminal(ntTv)!=null){
            					throw new IOException("A duplicated ID");
            				}
            			    
            			}catch(GrammarExceptionImpl ex){
            				throw new IOException(ex.getMessage());
            			}
            			if(this.map.put(ntTv.getSymbol(), ntTv)!= null){
            				throw new IOException("A duplicated ID");
            			}
	            		amountSymbols = 0;
	            		l = new LinkedList<Element>();
	            	}
	            	
	            	if(t.equals("TF")){
	            		type = 4;
	            		try{
	            			
	            			ntTf = new NonTerminalImpl(id, t+amountTf);
	            			id++;
	            			if(this.g.setNonTerminal(ntTf)!=null){
            					throw new IOException("A duplicated ID");
            				}
	            			
            			}catch(GrammarExceptionImpl ex){
            				throw new IOException(ex.getMessage());
            			}
            			if(this.map.put(ntTf.getSymbol(), ntTf)!=null){
            				throw new IOException("A duplicated ID");
            			}
	            		amountSymbols = 0;
	            		l = new LinkedList<Element>();
	            		
	            	}
	            	
	            	if(t.equals("P")){
	            		first2 = true;
	            		hasPr=true;
	            		type = 5;
	            		amountSymbols = 0;
	            		l = new LinkedList<Element>();
	            	}
	            }
	            else{
	            	 /* Consumes the token if is equal to "=" */
	            	hasToken = true;
	            	if(t.equals("=")){
	            		if(st.hasMoreTokens()){
	            			t = st.nextToken();
	            		}else{
	            			hasToken = false;
	            		}
	            	}
	            	if(hasToken){
	            		switch (type) {
	                		case 1:  
	                			
	                				try{
	                					nt = new NonTerminalImpl(id, t);
	                					id++;
	                					this.g.setStart(nt);
	                					
	                				}catch(GrammarExceptionImpl ex){
	                					throw new IOException(ex.getMessage());
	                				}
	                				start = nt;
	                				if(this.map.put(nt.getSymbol(), nt)!=null){
	                					throw new IOException("A duplicated ID");
	                				}
	                				
	                				break;
	                			
	                		case 2:  
	                				try{
	                					nt = new NonTerminalImpl(id, t);
	                					id++;
	                					if(this.g.setNonTerminal(nt)!=null){
	                						throw new IOException("A duplicated ID");
	                					}
	                				}catch(GrammarExceptionImpl ex){
	                    				throw new IOException(ex.getMessage());
	                    			}
	                				if(this.map.put(nt.getSymbol(), nt)!=null){
	                					throw new IOException("A duplicated ID");
	                				}
	                				
	                				break;
	                	
	                		case 3:  
	                				if (t.equals(start.getSymbol())){
	                					throw new IOException("The start symbol as a Terminal");
	                				}else{
	                					amountSymbols++;
	                					
	                					try{
	                						te = new TerminalImpl(id, t);
	                						id++;
	                						l.add(te);
	                						if(this.g.setTerminal(te)!=null){
	                							throw new IOException("A duplicated ID");
	                						}
	                					}catch(GrammarExceptionImpl ex){
	                        				throw new IOException(ex.getMessage());
	                        			}
	                					if(this.map.put(te.getSymbol(), te)!=null){
	                						throw new IOException("A duplicated ID");
	                					}
	                						                					
	                					break;
	                				}
                				
	                		case 4:  
	                			
	                				if (t.equals(start.getSymbol())){
	                					throw new IOException("The start symbol as a Terminal");
	                				}else{
	                						amountSymbols++;
	                						
	                						try{
	                							te = new TerminalImpl(id, t);
	                							id++;
	                							l.add(te);
	                							if(this.g.setTerminal(te)!=null){
	                								throw new IOException("A duplicated ID");
	                							}
	                						}catch(GrammarExceptionImpl ex){
	                            				throw new IOException(ex.getMessage());
	                            			}
	                						if(this.map.put(te.getSymbol(), te)!=null){
	                							throw new IOException("A duplicated ID");
	                						}
	                							                						
	                						break;
	                				}
                		
	                		case 5:  
	                			
	                				if(hasPr){
	                					
	                					hasPr = false;
	                					if(t.equals("p_r")){
	                						if(st.hasMoreTokens()){
	                							t = st.nextToken();
	                							try
	                							{
	                								pr=(this.nf).parse(t).doubleValue();
	                								
	                								break;
	                							}
	                							catch (ParseException e)
	                							{
	                								throw new IOException("A non valid value as probability");
	                							}
	                						}
	                						
	                						break;
	                					}
	                					else{
	                						throw new IOException("There isn`t a valid probability symbol");
	                					}
	                				}else{
	                					
	                					if(first2){
	                						first2 = false;
	                						if (this.map.containsKey(t)){
	                							
	                							left = this.map.get(t);
	                							
	                							break;
	                						}
	                						else{
	                							throw new IOException("Unknown symbol in the Production");
	                						}
	                					}else{
	                							amountSymbols++;
	                							if (t.equals(start.getSymbol())){
	                								throw new IOException("The start symbol is at the right side of a Production");
	                							}else{
	        	                						if (this.map.containsKey(t)){
	        	                							
	        	                							l.add(this.map.get(t));
	        	                							
	        	                							break;
	        	                						}
	        	                						else{
	        	                							throw new IOException("Unknown symbol in the Production");
	        	                						}
	                							}
	                						
	                					}
	                				}
	                		                				
	                		default: 
	                				throw new IOException("Unknown symbol at the start of the line");
	            		}
	            	}
	            }
	            
	                
	          }
			  switch (type) {
			  case 1:
				  
				  	if(start == null){
				  		throw new IOException("There isn´t a start symbol");
				  	}
				  	
				  	break;
				  	
			  case 3:
				  
				  if(l.isEmpty()){
					  throw new IOException(ntTv.getSymbol() + " is empty");
				  }else{
					  
					  	double amount = l.size();
					  	double prob = 0;
					  	if(amount!=0){
					  		prob = 1/amount;
					  	}
					  	
					  	for(Element e:l){
					    	
					      try{
					    	  p = new ProductionImpl(ntTv, 1, id, "P" + amountProd);
					      }catch(GrammarExceptionImpl ex){
					    	  throw new IOException(ex.getMessage());
					      }
					      
					      id++;
						  amountProd++;
						  pos=1;
						  
						  control = p.addRightElement(e, pos);
						  if(!control){
							  throw new IOException("There was a problem loading the production " + ntTv.getSymbol());
						  }
						  
						  try{
							    this.g.setProduction(p, l, ntTv);
							    
							    this.g.setProbability(p, prob);
							  	
						  }catch(GrammarExceptionImpl ex){
							  throw new IOException(ex.getMessage());
						  }
						  
						  
					  }
					  
					  amountTv++;
					  
					  break;
				  }
				
			  case 4:
				  
				  if(l.isEmpty()){
					  throw new IOException(ntTf.getSymbol() + " is empty");
				  }else{
					  
					  	double amount = l.size();
					  	double prob = 0;
					  	if(amount!=0){
					  		prob = 1/amount;
					  	}
					  	for(Element e:l){
					  	  try{
					  		  p = new ProductionImpl(ntTf, 1, id, "P" + amountProd);
					  	  }catch(GrammarExceptionImpl ex){
					    	  throw new IOException(ex.getMessage());
					      }
						  id++;
						  amountProd++;
						  pos =1;
						  control = p.addRightElement(e, pos);
						  if(!control){
							  throw new IOException("There was a problem loading the production " + ntTf.getSymbol());
						  }
						  try{
							  this.g.setProduction(p,l,ntTf);
							  this.g.setProbability(p, prob);
						  }catch(GrammarExceptionImpl ex){
							  throw new IOException(ex.getMessage());
						  }
					  }
					  
					  amountTf++;
					  
					  break;
				  }
		
			  case 5:
				  
				 if(l.isEmpty()){
					  throw new IOException("P" + amountProd + " is empty");
				  }else{
					  c = nt.getClass();
					  if(c.isInstance(left)){
						  
						 try{
							  p = new ProductionImpl((NonTerminal)left, amountSymbols, id, "P" + amountProd);
						  }catch(GrammarExceptionImpl ex){
					    	  throw new IOException(ex.getMessage());
					      }
						  
						  id++;
						  amountProd++;
						  pos =1;
						  
						  for(Element e:l){
							  control = p.addRightElement(e, pos);
							  if(!control){
								  throw new IOException("There was a problem loading the production " + "P" + amountProd);
							  }
							  pos++;
						  }
						  
						  try{
							  this.g.setProduction(p,l,left);
						  }catch(GrammarExceptionImpl ex){
							  throw new IOException(ex.getMessage());
						  }
						  
						  if(this.g.getType()){
							  try{
								 double pro = this.g.setProbability(p, pr);
								 if(pro>=0){
									 throw new IOException("There was a collision with the probabilities");
								 }
							  }catch(GrammarExceptionImpl ex){
								  throw new IOException(ex.getMessage());
							  }
						  }
						  amountP++;
						  
						  break;
						  
					  }else{
						  throw new IOException("A production that doesn´t have a NonTerminal at the left side");
					  }
				  }
			  }
		}
		this.g.setIdMax(id);
		this.map.clear();
		this.map = null;
      }
        
    
    /**
     * 
     *  Returns the Grammar loaded.
     *  
     * 
     * */
     public Grammar getGrammar(){
    	 return this.g;
     }
}
