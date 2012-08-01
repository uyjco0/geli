package core.grammar;

/**
 * 
 * Helper class for the class DerivationImpl (in order to hide the Derivation's implementation).
 * The class is private, it must be used through the class DerivationImpl.
 * 
 */

import jdsl.core.api.Tree;
import jdsl.core.api.Position;
import jdsl.core.ref.NodeTree;
import jdsl.core.api.BoundaryViolationException;
import jdsl.core.api.InvalidAccessorException;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import util.random.Sort;


public final class DerivationAux_InternalImpl {

	
	/** 
	 * 
	 * 
	 * The function duplicates the Tree "t" starting at the position "node". The duplicate is load
	 * in the tree "result".  The call to the function must be whith an empty "result" (ie, the
	 * tree must be created with the tree's constructor function without parameters. The goal is
	 * to pass the tree by reference, then it must be not NULL.), and a NULL parameter "node2" 
	 * (it is used in the recursion that the funcion makes).
	 * 
	 *  
	 **/	
	@SuppressWarnings("unchecked")
	static void duplicateTree(Position node, Tree t, Tree result, Position node2) throws GrammarExceptionImpl{
		
		Production prod;
		Element el;
		Position pos;
		int num;
		Collection<Position> childs;
		boolean root = false;
		Element e;
		Terminal te = new TerminalImpl(0, "");;
		Class cl2;
		
		if((t != null) && (node!=null)){
			
			if(result == null){
				result = new NodeTree();
			}
						
			/* Base step. */
			if(t.isExternal(node)){
				if(node2 == null){
					pos = result.root();
					root=true;
				}else{
						if(result.isExternal(node2)){
							pos = result.insertFirstChild(node2, null);
						}else{
								pos = result.insertLastChild(node2, null);
						}
				}
				
				/* The root node has a null Production. */
				if((!root) && (!t.isRoot(node))){
					prod = (Production)node.get("Production");
					if(prod==null){
						throw new GrammarExceptionImpl("The node doesn´t has a valid Production " +
							                           "associated");
					}
				}else{
						prod = null;
						root = false;
				}
				
				if(prod!=null){
					pos.set("Production", prod.cloneProduction());
				}else{
						pos.set("Production", prod);
				}
				
				el = (Element)node.get("Element");
				
				if(el==null){
					throw new GrammarExceptionImpl("The node doesn´t has a valid Element " +
							                       "associated");
				}
				
				cl2 = te.getClass();
				if(cl2.isInstance(el)){
					
					e = ((TerminalImpl)el).cloneTerminal();
					
				}else{
						e = ((NonTerminalImpl)el).cloneNonTerminal();
					
				}
				
				pos.set("Element", e);
				
			}
			/* Recursive step. */
			else{
					if(node2 == null){
						pos = result.root();
						root = true;
					}else{
							if(result.isExternal(node2)){
								pos = result.insertFirstChild(node2, null);
						 }else{
								pos = result.insertLastChild(node2, null);	
						}
					}
					
					/* The root node has a null Production. */
					if((!root) && (!t.isRoot(node))){
						prod = (Production)node.get("Production");
						if(prod==null){
							throw new GrammarExceptionImpl("The node doesn´t has a valid " +
								                       "Production associated");
						}
					}else{
							prod = null;
							root = false;
					}
					
					if(prod!=null){
						pos.set("Production", prod.cloneProduction());
					}else{
							pos.set("Production", prod);
					}	
					
					el = (Element)node.get("Element");
					
					if(el==null){
						throw new GrammarExceptionImpl("The node doesn´t has a valid Element " +
								                       "associated");
					}
					
					cl2 = te.getClass();
					if(cl2.isInstance(el)){
						
						e = ((TerminalImpl)el).cloneTerminal();
						
					}else{
							e = ((NonTerminalImpl)el).cloneNonTerminal();
						
					}
					
					pos.set("Element", e);
					
					num = t.numChildren(node);
					childs = new LinkedList<Position>();
					
					for(int i=0; i< num; i++){
						childs.add(t.childAtRank(node, i));
					}
					
					for(Position p: childs){
						duplicateTree(p, t, result, pos);
					}
			}
		}
	}
	
	
	/** 
	 * 
	 * The function duplicates the Tree "t" starting at the position "node". The duplicate is load
	 * in the tree "result".  The call to the function must be whith an empty "result" (ie, the
	 * tree must be created with the tree's constructor function without parameters. The goal is
	 * to pass the tree by reference, then it must be not NULL.), and a NULL parameter "node2" 
	 * (it is used in the recursion that the funcion makes).
	 * 
	 * The parameter "s" represent the symbol associated to the nodes with a Function.
	 * 
	 **/	
	@SuppressWarnings("unchecked")
	static void duplicateTreeWithFunction(Position node, Tree t, Tree result, Position node2, String s) throws GrammarExceptionImpl{
		
		Production prod;
		Element el;
		Position pos;
		int num;
		Collection<Position> childs;
		boolean root = false;
		Element e;
		Terminal te = new TerminalImpl(0, "");;
		Class cl2;
		Function aux;
		Double value;
		
		if((t != null) && (node!=null)){
			
			if(result == null){
				result = new NodeTree();
			}
						
			/* Base step. */
			if(t.isExternal(node)){
				if(node2 == null){
					pos = result.root();
					root=true;
				}else{
						if(result.isExternal(node2)){
							pos = result.insertFirstChild(node2, null);
						}else{
								pos = result.insertLastChild(node2, null);
						}
				}
				
				/* The root node has a null Production. */
				if((!root) && (!t.isRoot(node))){
					prod = (Production)node.get("Production");
					if(prod==null){
						throw new GrammarExceptionImpl("The node doesn´t has a valid Production " +
							                       "associated");
					}
				}else{
						prod = null;
						root = false;
				}
				
				if(prod!=null){
					pos.set("Production", prod.cloneProduction());
				}else{
						pos.set("Production", prod);
				}
				
				el = (Element)node.get("Element");
				
				if(el==null){
					throw new GrammarExceptionImpl("The node doesn´t has a valid Element " +
							                       "associated");
				}
				
				cl2 = te.getClass();
				if(cl2.isInstance(el)){
					
					e = ((TerminalImpl)el).cloneTerminal();
					
				}else{
						e = ((NonTerminalImpl)el).cloneNonTerminal();
					
				}
				
				pos.set("Element", e);
				
				if(((e.getSymbol()).toUpperCase()).equals(s.toUpperCase())){
					
					value = (Double)node.get("Value");
					if(value != null){
						value = new Double(value.doubleValue());
					}
					
					pos.set("Value", value);
					
					aux = (Function)node.get("Function");
					if(aux != null){
						aux = aux.cloneFunction();
					}
					
					pos.set("Function", aux);
				}
				
			}
			/* Recursive step. */
			else{
					if(node2 == null){
						pos = result.root();
						root = true;
					}else{
							if(result.isExternal(node2)){
								pos = result.insertFirstChild(node2, null);
						 }else{
								pos = result.insertLastChild(node2, null);	
						}
					}
					
					/* The root node has a null Production. */
					if((!root) && (!t.isRoot(node))){
						prod = (Production)node.get("Production");
						if(prod==null){
							throw new GrammarExceptionImpl("The node doesn´t has a valid " +
								                       "Production associated");
						}
					}else{
							prod = null;
							root = false;
					}
					
					if(prod!=null){
						pos.set("Production", prod.cloneProduction());
					}else{
							pos.set("Production", prod);
					}
					
					el = (Element)node.get("Element");
					
					if(el==null){
						throw new GrammarExceptionImpl("The node doesn´t has a valid Element " +
								                       "associated");
					}
					
					cl2 = te.getClass();
					if(cl2.isInstance(el)){
						
						e = ((TerminalImpl)el).cloneTerminal();
						
					}else{
							e = ((NonTerminalImpl)el).cloneNonTerminal();
						
					}
					
					pos.set("Element", e);
					
					if(((e.getSymbol()).toUpperCase()).equals(s.toUpperCase())){
						
						value = (Double)node.get("Value");
						if(value != null){
							value = new Double(value.doubleValue());
						}
						
						pos.set("Value", value);
						
						aux = (Function)node.get("Function");
						if(aux != null){
							aux = aux.cloneFunction();
						}
						
						pos.set("Function", aux);
					}
					
					num = t.numChildren(node);
					childs = new LinkedList<Position>();
					
					for(int i=0; i< num; i++){
						childs.add(t.childAtRank(node, i));
					}
					
					for(Position p: childs){
						duplicateTreeWithFunction(p, t, result, pos, s);
					}
			}
		}
	}
	
	
	/** 
	 * 
	 * The function duplicates the Tree "t" starting at the position "node". The duplicate is load
	 * in the tree "result".  The call to the function must be whith an empty "result" (ie, the
	 * tree must be created with the tree's constructor function without parameters. The goal is
	 * to pass the tree by reference, then it must be not NULL.), and a NULL parameter "node2" 
	 * (it is used in the recursion that the funcion makes).
	 * 
	 * The difference with the other duplicateTree is the parameter m (that must be a non null value
	 * in order to pass it for reference). The function actualizes the structure m.
	 *
	 * 
	 **/	
	@SuppressWarnings("unchecked")
	static void duplicateTree(Position node, Tree t, Tree result, Position node2, Map<String, LinkedList<Position>> m) throws GrammarExceptionImpl{
		
		Production prod;
		Element el;
		Position pos;
		int num;
		Collection<Position> childs;
		LinkedList<Position> l;
		boolean root = false;
		Element e;
		Terminal te = new TerminalImpl(0, "");;
		Class cl2;
		
		if((t != null) && (node!=null)){
			
			if(result == null){
				result = new NodeTree();
			}
						
			/* Base step. */
			if(t.isExternal(node)){
				if(node2 == null){
					pos = result.root();
					root=true;
				}else{
						if(result.isExternal(node2)){
							pos = result.insertFirstChild(node2, null);
						}else{
								pos = result.insertLastChild(node2, null);
						}
				}
				
				/* The root node has a null Production. */
				if((!root) && (!t.isRoot(node))){
					prod = (Production)node.get("Production");
					if(prod==null){
						throw new GrammarExceptionImpl("The node doesn´t has a valid Production " +
							                       "associated");
					}
				}else{
						prod = null;
						root = false;
				}
				
				if(prod!=null){
					pos.set("Production", prod.cloneProduction());
				}else{
						pos.set("Production", prod);
				}
				
				el = (Element)node.get("Element");
				if(el==null){
					throw new GrammarExceptionImpl("The node doesn´t has a valid Element " +
							                       "associated");
				}
				
				cl2 = te.getClass();
				if(cl2.isInstance(el)){
					
					e = ((TerminalImpl)el).cloneTerminal();
					
				}else{
						e = ((NonTerminalImpl)el).cloneNonTerminal();
					
				}
				
				pos.set("Element", e);
				
				/* Updates the map with the Position where the Element e appears in the Derivation. */
				if(m.containsKey(e.getSymbol())){
					l = m.get(e.getSymbol());
					l.add(pos);
					m.put(e.getSymbol(), l);
				}else{
						l = new LinkedList<Position>();
						l.add(pos);
						m.put(e.getSymbol(), l);
				}
				
			}
			/* Recursive step. */
			else{
					if(node2 == null){
						pos = result.root();
						root = true;
					}else{
							if(result.isExternal(node2)){
								pos = result.insertFirstChild(node2, null);
						 }else{
								pos = result.insertLastChild(node2, null);	
						}
					}
					
					/* The root node has a null Production. */
					if((!root) && (!t.isRoot(node))){
						prod = (Production)node.get("Production");
						if(prod==null){
							throw new GrammarExceptionImpl("The node doesn´t has a valid " +
								                       "Production associated");
						}
					}else{
							prod = null;
							root = false;
					}
					
					if(prod!=null){
						pos.set("Production", prod.cloneProduction());
					}else{
							pos.set("Production", prod);
					}
					
					el = (Element)node.get("Element");
					
					if(el==null){
						throw new GrammarExceptionImpl("The node doesn´t has a valid Element " +
								                       "associated");
					}
					
					cl2 = te.getClass();
					if(cl2.isInstance(el)){
						
						e = ((TerminalImpl)el).cloneTerminal();
						
					}else{
							e = ((NonTerminalImpl)el).cloneNonTerminal();
						
					}
					
					pos.set("Element", e);
					
					/* Updates the map with the Position where the Element e appears in the Derivation. */
					if(m.containsKey(e.getSymbol())){
						l = m.get(e.getSymbol());
						l.add(pos);
						m.put(e.getSymbol(), l);
					}else{
							l = new LinkedList<Position>();
							l.add(pos);
							m.put(e.getSymbol(), l);
					}
					
					num = t.numChildren(node);
					childs = new LinkedList<Position>();
					
					for(int i=0; i< num; i++){
						childs.add(t.childAtRank(node, i));
					}
					
					for(Position p: childs){
						duplicateTree(p, t, result, pos, m);
					}
			}
		}
	}
	
	
	/** 
	 * 
	 * The function duplicates the Tree "t" starting at the position "node". The duplicate is load
	 * in the tree "result".  The call to the function must be whith an empty "result" (ie, the
	 * tree must be created with the tree's constructor function without parameters. The goal is
	 * to pass the tree by reference, then it must be not NULL.), and a NULL parameter "node2" 
	 * (it is used in the recursion that the funcion makes).
	 * 
	 * The difference with the other duplicateTree is the parameter m (that must be a non null value
	 * in order to pass it for reference). The function actualizes the structure m.
	 * 
	 * The parameter "s" represent the symbol associated to the nodes with a Function.
	 * 
	 **/	
	@SuppressWarnings("unchecked")
	static void duplicateTreeWithFunction(Position node, Tree t, Tree result, Position node2, Map<String, LinkedList<Position>> m, String s) throws GrammarExceptionImpl{
		
		Production prod;
		Element el;
		Position pos;
		int num;
		Collection<Position> childs;
		LinkedList<Position> l;
		boolean root = false;
		Element e;
		Terminal te = new TerminalImpl(0, "");;
		Class cl2;
		Function aux;
		Double value;
		
		if((t != null) && (node!=null)){
			
			if(result == null){
				result = new NodeTree();
			}
						
			/* Base step. */
			if(t.isExternal(node)){
				if(node2 == null){
					pos = result.root();
					root=true;
				}else{
						if(result.isExternal(node2)){
							pos = result.insertFirstChild(node2, null);
						}else{
								pos = result.insertLastChild(node2, null);
						}
				}
				
				/* The root node has a null Production. */
				if((!root) && (!t.isRoot(node))){
					prod = (Production)node.get("Production");
					if(prod==null){
						throw new GrammarExceptionImpl("The node doesn´t has a valid Production " +
							                       "associated");
					}
				}else{
						prod = null;
						root = false;
				}
				
				if(prod!=null){
					pos.set("Production", prod.cloneProduction());
				}else{
						pos.set("Production", prod);
				}
				
				el = (Element)node.get("Element");
				
				if(el==null){
					throw new GrammarExceptionImpl("The node doesn´t has a valid Element " +
							                       "associated");
				}
				
				cl2 = te.getClass();
				if(cl2.isInstance(el)){
					
					e = ((TerminalImpl)el).cloneTerminal();
					
				}else{
						e = ((NonTerminalImpl)el).cloneNonTerminal();
					
				}
				
				pos.set("Element", e);
				
				if(((e.getSymbol()).toUpperCase()).equals(s.toUpperCase())){
					
					value = (Double)node.get("Value");
					if(value != null){
						value = new Double(value.doubleValue());
					}
						
					pos.set("Value", value);
					
					aux = (Function)node.get("Function");
					if(aux != null){
						aux = aux.cloneFunction();
					}
					
					pos.set("Function", aux);
				}
				
				/* Updates the map with the Position where the Element e appears in the Derivation. */
				if(m.containsKey(e.getSymbol())){
					l = m.get(e.getSymbol());
					l.add(pos);
					m.put(e.getSymbol(), l);
				}else{
						l = new LinkedList<Position>();
						l.add(pos);
						m.put(e.getSymbol(), l);
				}
				
			}
			/* Recursive step. */
			else{
					if(node2 == null){
						pos = result.root();
						root = true;
					}else{
							if(result.isExternal(node2)){
								pos = result.insertFirstChild(node2, null);
						 }else{
								pos = result.insertLastChild(node2, null);	
						}
					}
					
					/* The root node has a null Production. */
					if((!root) && (!t.isRoot(node))){
						prod = (Production)node.get("Production");
						if(prod==null){
							throw new GrammarExceptionImpl("The node doesn´t has a valid " +
								                       "Production associated");
						}
					}else{
							prod = null;
							root = false;
					}
					
					if(prod!=null){
						pos.set("Production", prod.cloneProduction());
					}else{
							pos.set("Production", prod);
					}
					
					el = (Element)node.get("Element");
					
					if(el==null){
						throw new GrammarExceptionImpl("The node doesn´t has a valid Element " +
								                       "associated");
					}
					
					cl2 = te.getClass();
					if(cl2.isInstance(el)){
						
						e = ((TerminalImpl)el).cloneTerminal();
						
					}else{
							e = ((NonTerminalImpl)el).cloneNonTerminal();
						
					}
					
					pos.set("Element", e);
					
					if(((e.getSymbol()).toUpperCase()).equals(s.toUpperCase())){
						
						value = (Double)node.get("Value");
						if(value != null){
							value = new Double(value.doubleValue());
						}
						
						pos.set("Value", value);
						
						aux = (Function)node.get("Function");
						if(aux != null){
							aux = aux.cloneFunction();
						}
						
						pos.set("Function", aux);
					}
					
					/* Updates the map with the Position where the Element e appears in the Derivation. */
					if(m.containsKey(e.getSymbol())){
						l = m.get(e.getSymbol());
						l.add(pos);
						m.put(e.getSymbol(), l);
					}else{
							l = new LinkedList<Position>();
							l.add(pos);
							m.put(e.getSymbol(), l);
					}
					
					num = t.numChildren(node);
					childs = new LinkedList<Position>();
					
					for(int i=0; i< num; i++){
						childs.add(t.childAtRank(node, i));
					}
					
					for(Position p: childs){
						duplicateTreeWithFunction(p, t, result, pos, m,s);
					}
			}
		}
	}

	
	
	static int depth(Position node, Tree t){
		Collection<Position> cProcess;
		Collection<Position> cChilds;
		int num;
		int level =0;
		
		if((node != null) && (t!=null)){
			
			level++;
			
			/* Loads the node's childs in order. */
			num = t.numChildren(node);
			cProcess = new LinkedList<Position>();
			for(int i=0; i< num; i++){
				cProcess.add(t.childAtRank(node, i));
			}
			
			cChilds = new LinkedList<Position>();
			while(!cProcess.isEmpty()){
								
				level ++;
				
				for(Position p: cProcess){
					
					/* Load the childs of the actual node (p) in order. */
					num = t.numChildren(p);
					for(int i=0; i< num; i++){
						cChilds.add(t.childAtRank(p, i));
					}
				}
				
				cProcess = cChilds;
				cChilds = new LinkedList<Position>();
			}
		}
		return level;
	}
	
	
	/**
	 * 
	 * The function returns the level (starting at 1) at which the node "node" is located in the m-Tree t.
	 * 
	 * The function MUST be invoked with the parameter "level" = 0 (in order that the level returned starts at
	 * 1).
	 * 
	 */
	static int depthBackwards(Position node, Tree t, int level){
		
		if((node != null) && (t!=null)){
			level++;
			if(!t.isRoot(node)){
				level = depthBackwards(t.parent(node), t, level);
			}
		}
		
		return level;
	}
	
	
	static void crossByLevels(Tree t){
		Position node;
		Collection<Position> cProcess;
		Collection<Position> cChilds;
		int num;
		int level;
		Production prod;
		
		if(t != null){
			level = 0;
			node = t.root();
			
			/* Process the root. */
			System.out.println("Level: " + level);
			level++;
			System.out.println("Root: " + ((Element)node.get("Element")).getSymbol());
			System.out.println();
			
			/* Loads the childs of the root in order. */
			num = t.numChildren(node);
			cProcess = new LinkedList<Position>();
			for(int i=0; i< num; i++){
				cProcess.add(t.childAtRank(node, i));
			}
			
			cChilds = new LinkedList<Position>();
			while(!cProcess.isEmpty()){
								
				System.out.println("Level: " + level);
				level ++;
				
				for(Position p: cProcess){
					
					/*Process the actual node. */
					System.out.print(((Element)p.get("Element")).getSymbol());
					System.out.print("  ");
					prod = (Production)p.get("Production");
					if(prod!=null){
						System.out.print("Prod: " + prod.getSymbol());
						System.out.print("  ||  ");
					}
					
					/* Load the childs of the actual node in order. */
					num = t.numChildren(p);
					for(int i=0; i< num; i++){
						cChilds.add(t.childAtRank(p, i));
					}
				}
				
				System.out.println();
				System.out.println();
				
				cProcess = cChilds;
				cChilds = new LinkedList<Position>();
			}
		}
	}
	
	
	/**
	 * 
	 * 
	 * 
	 * @param t
	 * 			 The tree to be crossed
	 * @param s
	 * 			The symbol associated to the nodes with a function
	 * 
	 */
	static void crossByLevelsWithFunction(Tree t, String s){
		Position node;
		Collection<Position> cProcess;
		Collection<Position> cChilds;
		int num;
		int level;
		Production prod;
		String symbol;
		Function f ;
		Double value;
		
		if(t != null){
			level = 0;
			node = t.root();
			
			/* Process the root. */
			System.out.println("Level: " + level);
			level++;
			
			symbol = ((Element)node.get("Element")).getSymbol();
			
			if((symbol.toUpperCase()).equals(s.toUpperCase())){
				
				
				f = (Function)node.get("Function");
				
				value = (Double)node.get("Value");
				
				if(f != null ) {
					System.out.print("Root: " + symbol + " " + "Operator: " + f.getOperator() + " Increment: " + f.getIncrement() + " Value: " + value.floatValue());
				}else{
					System.out.print("Root: " + symbol);
				}
				
			}else{
				
				System.out.print("Root: " + symbol);
			}
			
			
			System.out.println();
			
			/* Loads the childs of the root in order. */
			num = t.numChildren(node);
			cProcess = new LinkedList<Position>();
			for(int i=0; i< num; i++){
				cProcess.add(t.childAtRank(node, i));
			}
			
			cChilds = new LinkedList<Position>();
			while(!cProcess.isEmpty()){
								
				System.out.println("Level: " + level);
				level ++;
				
				for(Position p: cProcess){
					
					/*Process the actual node. */
					symbol = ((Element)p.get("Element")).getSymbol();
					
					if((symbol.toUpperCase()).equals(s.toUpperCase())){ 
						
			
						f = (Function)p.get("Function");
						
						value = (Double)p.get("Value");
						
						if(f != null) {
							System.out.print("Element: " + symbol + " " + "Operator: " + f.getOperator() + " Increment: " + f.getIncrement() + " Value: " + value.doubleValue());
						}else{
							System.out.print("Element: " + symbol);
						}
						
					}else{
						
						System.out.print("Element: " + symbol);
					}
					System.out.print("  ");
					
					prod = (Production)p.get("Production");
					if(prod != null){
						System.out.print("Prod: " + prod.getSymbol());
						System.out.print("  ||  ");
					}
					
					/* Load the childs of the actual node in order. */
					num = t.numChildren(p);
					for(int i=0; i< num; i++){
						cChilds.add(t.childAtRank(p, i));
					}
				}
				
				System.out.println();
				System.out.println();
				
				cProcess = cChilds;
				cChilds = new LinkedList<Position>();
			}
		}
	}
	
	
	static Position getPosition(Tree t, int level, int pos){
		
		Position node = null;
		Collection<Position> cProcess;
		Collection<Position> cChilds;
		int num;
		int level2;
		
		if((t != null)){
			
			if((level > DerivationAux_InternalImpl.depth(t.root(), t))|| (level<0) || (pos<0)){
				return null;
			}
			
			level2 = 1;
			node = t.root();
			
			if(level2 == level){
				if(pos==0){
					return node;
				}else{
						return null;
				}
			}
			
			/* Loads the childs of the root in order. */
			num = t.numChildren(node);
			cProcess = new LinkedList<Position>();
			for(int i=0; i< num; i++){
				cProcess.add(t.childAtRank(node, i));
			}
			
			cChilds = new LinkedList<Position>();
			while(!cProcess.isEmpty()){
								
				level2 ++;
				if(level2==level){
					if(pos<cProcess.size()){
						num = 0;
						for(Position p: cProcess){
							if(num==pos){
								return p;
							}
							num++;
						}
					}else{
							return null;
					}
					
				}
				
				for(Position p: cProcess){
					
					/* Load the childs of the actual node in order. */
					num = t.numChildren(p);
					for(int i=0; i< num; i++){
						cChilds.add(t.childAtRank(p, i));
					}
				}
				
				cProcess = cChilds;
				cChilds = new LinkedList<Position>();
			}
		}
		return node;
	}
	
	
	/**
	 * 
	 * Given a node (po) at the level "level" in the m-Tree t, the function returns
	 * the absolute rank of the node (po).
	 * 
	 * The function returns -1 if isn't possible return a result.
	 * 
	 */
	static int getAbsoluteRank(Tree t, Position po, int level){
		
		Position node = null;
		Collection<Position> cProcess;
		Collection<Position> cChilds;
		int num;
		int level2;
		int rank = -1;
		
		if((t != null)&&(po!=null)){
			
			if((level > DerivationAux_InternalImpl.depth(t.root(), t))|| (level<0)){
				return -1;
			}
			
			level2 = 1;
			node = t.root();
			
			if(level2 == level){
				return 0;
			}
			
			/* Loads the childs of the root in order. */
			num = t.numChildren(node);
			cProcess = new LinkedList<Position>();
			for(int i=0; i< num; i++){
				cProcess.add(t.childAtRank(node, i));
			}
			
			cChilds = new LinkedList<Position>();
			while(!cProcess.isEmpty()){
								
				level2 ++;
				if(level2==level){
					num = 0;
					for(Position p: cProcess){
						if(p.equals(po)){
							return num;
						}
						num++;
					}
				}
				
				for(Position p: cProcess){
					
					/* Load the childs of the actual node in order. */
					num = t.numChildren(p);
					for(int i=0; i< num; i++){
						cChilds.add(t.childAtRank(p, i));
					}
				}
				
				cProcess = cChilds;
				cChilds = new LinkedList<Position>();
			}
		}
		return rank;
	}
	
	
	/**
	 * 
	 * @param t
	 *           The tree where is the node "pos".
	 *           
	 * @param pos
	 * 			  The node which is the reference.
	 * 
	 * @param abs_rank
	 * 					The node to be returned must be at the level of the node "pos" and its absolute position (AP)
	 *                  must be: AP = absolute_position_of_pos + abs_rank
	 * 
	 * @return
	 * 			Returns the node at the tree's absolute position AP. Returns NULL if for any reason isn't impossible
	 *          to find this node.
	 * 
	 */
	@SuppressWarnings("unused")
	static Position getNodeAtAbsoluteRank(Tree t, Position pos, int abs_rank){
		Position res = null;
		Position father;
		Position pos_aux;
		Position aux_pos;
		int abs_rank2;
		int level_pos;
		int level;
		int amount;
		int num;
		Collection<Position> cProcess;
		Collection<Position> cChilds;
		boolean end;
		boolean find;
		Iterator<Position> it;
		int aux;
		
		level_pos = DerivationAux_InternalImpl.depthBackwards(pos, t, 0);
		abs_rank2 = DerivationAux_InternalImpl.getAbsoluteRank(t, pos, level_pos);
		abs_rank = abs_rank2 + abs_rank;
		
		if(abs_rank == abs_rank2){
			
			res = pos;
		
		}else {
		
			if(!t.isRoot(pos)){
			
				father = t.parent(pos);
				
				if(father != null){
					
					if(t.isRoot(father)){
					
						amount = t.numChildren(father);
						if((abs_rank >=0) && (abs_rank < amount)){
							res = t.childAtRank(father, abs_rank);
						}
					
					}else{
				
						level =1 ;
						pos_aux = t.root();
						
						
						/* Loads the childs of the root in order. */
						num = t.numChildren(pos_aux);
						cProcess = new LinkedList<Position>();
						for(int i=0; i< num; i++){
							cProcess.add(t.childAtRank(pos_aux, i));
						}
						
						cChilds = new LinkedList<Position>();
						end = false;
						
						while((!cProcess.isEmpty()) && (!end)){
											
							level ++;
							
							if(level == level_pos){
								end = true;
							}
							
							for(Position p: cProcess){
								
								/* Load the childs of the actual node in order. */
								num = t.numChildren(p);
								for(int i=0; i< num; i++){
									cChilds.add(t.childAtRank(p, i));
								}
							}
							
							if(end){
								if(abs_rank < cProcess.size()){
									it = cProcess.iterator();
									find = false;
									aux = 0;
									while(it.hasNext() && (!find)){
										if(aux == abs_rank){
											res = it.next();
											find = true;
										}else{
											   aux ++;
											   aux_pos = it.next();
											   
										}
									}
								}
							}
							
							cProcess = cChilds;
							cChilds = new LinkedList<Position>();
							
						}
					}
				}
			}
		}
		
		
		return res;
	}
	
	
	/** 
	 * 
	 * Removes the Tree's subtree starting at the Position (node) pos
	 * 
	 */
	@SuppressWarnings("unchecked")
	static Tree deleteTree(Tree t, Position pos) throws GrammarExceptionImpl{
		int num;
		Terminal te;
		Element el;
		Class c;
		Production p;
		
		
		if((t!=null) && (pos != null)){
			
			te = new TerminalImpl(0, "");
			c = te.getClass();
			te.clearTerminal();
			te = null;
				
			if(t.isExternal(pos)){
				
				if(!t.isRoot(pos)){
					
					el = (Element)pos.get("Element");
					if(el!=null){
						if(c.isInstance(el)){
							((Terminal)el).clearTerminal();
							el = null;
							pos.set("Element", el);
						}else{
								((NonTerminal)el).clearNonTerminal();
								el = null;
								pos.set("Element", el);
						}
					}
					
					p = (Production)pos.get("Production");
					if(p!=null){
						p.clearProduction();
						p = null;
						pos.set("Production", p);
					}
					
					t.removeExternal(pos);
					
				}else{
					
					el = (Element)pos.get("Element");
					if(el!=null){
						if(c.isInstance(el)){
							((Terminal)el).clearTerminal();
							el = null;
							pos.set("Element", el);
						}else{
								((NonTerminal)el).clearNonTerminal();
								el = null;
								pos.set("Element", el);
						}
					}
					
					p = (Production)pos.get("Production");
					if(p!=null){
						p.clearProduction();
						p = null;
						pos.set("Production", p);
					}
					
					t=null;
				}
			}else{
					num = t.numChildren(pos);
					//It's necessary to start at the end and goes to the first, because the function is deleting
					//the childs associated to "pos", then the rank of the childs are changing dynamically.
					for(int i=(num-1); i >= 0; i--){
						//Apply the deleteTree to each pos's child
						t=deleteTree(t, t.childAtRank(pos, i));
					}
					
					if(!t.isRoot(pos)){
						
						el = (Element)pos.get("Element");
						if(el!=null){
							if(c.isInstance(el)){
								((Terminal)el).clearTerminal();
								el = null;
								pos.set("Element", el);
							}else{
									((NonTerminal)el).clearNonTerminal();
									el = null;
									pos.set("Element", el);
							}
						}
						
						p = (Production)pos.get("Production");
						if(p!=null){
							p.clearProduction();
							p = null;
							pos.set("Production", p);
						}
						
						t.removeExternal(pos);
						
					}else{
							
						el = (Element)pos.get("Element");
						if(el!=null){
							if(c.isInstance(el)){
								((Terminal)el).clearTerminal();
								el = null;
								pos.set("Element", el);
							}else{
									((NonTerminal)el).clearNonTerminal();
									el = null;
									pos.set("Element", el);
							}
						}
						
						p = (Production)pos.get("Production");
						if(p!=null){
							p.clearProduction();
							p = null;
							pos.set("Production", p);
						}
						
						t=null;
					}
			}
			
		}
		return t;
	}
	
	
	/** 
	 * 
	 * Removes the Tree's subtree starting at the Position (node) pos
	 * 
	 */
	@SuppressWarnings("unchecked")
	static Tree deleteTreeWithFunction(Tree t, Position pos, String s) throws GrammarExceptionImpl{
		int num;
		Terminal te;
		Element el;
		Class c;
		Production p;
		Function f;
		String s2;
		
		
		if((t!=null) && (pos != null)){
			
			te = new TerminalImpl(0, "");
			c = te.getClass();
			te.clearTerminal();
			te = null;
				
			if(t.isExternal(pos)){
				
				if(!t.isRoot(pos)){
					
					el = (Element)pos.get("Element");
					s2 = el.getSymbol();
					if(el!=null){
						if(c.isInstance(el)){
							((Terminal)el).clearTerminal();
							el = null;
							pos.set("Element", el);
						}else{
								((NonTerminal)el).clearNonTerminal();
								el = null;
								pos.set("Element", el);
						}
					}
					
					p = (Production)pos.get("Production");
					if(p!=null){
						p.clearProduction();
						p = null;
						pos.set("Production", p);
					}
					
					if(s2.equals(s)){
						f = (Function)pos.get("Function");
						if(f!=null){
							f.clearFunction();
							f = null;
							pos.set("Function", f);
						}
					
						pos.set("Value", null);
					}
					
					s2 = null;
					
					t.removeExternal(pos);
					
				}else{
					
					el = (Element)pos.get("Element");
					s2 = el.getSymbol();
					if(el!=null){
						if(c.isInstance(el)){
							((Terminal)el).clearTerminal();
							el = null;
							pos.set("Element", el);
						}else{
								((NonTerminal)el).clearNonTerminal();
								el = null;
								pos.set("Element", el);
						}
					}
					
					p = (Production)pos.get("Production");
					if(p!=null){
						p.clearProduction();
						p = null;
						pos.set("Production", p);
					}
					
					if(s2.equals(s)){
						f = (Function)pos.get("Function");
						if(f!=null){
							f.clearFunction();
							f = null;
							pos.set("Function", f);
						}
					
						pos.set("Value", null);
					}
					s2 = null;
					
					t=null;
				}
			}else{
					num = t.numChildren(pos);
					//It's necessary to start at the end and goes to the first, because the function is deleting
					//the childs associated to "pos", then the rank of the childs are changing dynamically.
					for(int i=(num-1); i >= 0; i--){
						//Apply the deleteTree to each pos's child
						t=deleteTree(t, t.childAtRank(pos, i));
					}
					
					if(!t.isRoot(pos)){
						
						el = (Element)pos.get("Element");
						s2 = el.getSymbol();
						if(el!=null){
							if(c.isInstance(el)){
								((Terminal)el).clearTerminal();
								el = null;
								pos.set("Element", el);
							}else{
									((NonTerminal)el).clearNonTerminal();
									el = null;
									pos.set("Element", el);
							}
						}
						
						p = (Production)pos.get("Production");
						if(p!=null){
							p.clearProduction();
							p = null;
							pos.set("Production", p);
						}
						
						if(s2.equals(s)){
							f = (Function)pos.get("Function");
							if(f!=null){
								f.clearFunction();
								f = null;
								pos.set("Function", f);
							}
						
							pos.set("Value", null);
						}
						s2 = null;
						
						t.removeExternal(pos);
						
					}else{
							
						el = (Element)pos.get("Element");
						s2 = el.getSymbol();
						if(el!=null){
							if(c.isInstance(el)){
								((Terminal)el).clearTerminal();
								el = null;
								pos.set("Element", el);
							}else{
									((NonTerminal)el).clearNonTerminal();
									el = null;
									pos.set("Element", el);
							}
						}
						
						p = (Production)pos.get("Production");
						if(p!=null){
							p.clearProduction();
							p = null;
							pos.set("Production", p);
						}
						
						if(s2.equals(s)){
							f = (Function)pos.get("Function");
							if(f!=null){
								f.clearFunction();
								f = null;
								pos.set("Function", f);
							}
						
							pos.set("Value", null);
						}
						s2 = null;
						
						t=null;
					}
			}
			
		}
		return t;
	}
	
	
	/**
	 * 
	 * The function returns a Hash (Map), where for each Grammar's Element (the Element is the Hash's key), are
	 * holded (as a LinkedList) the nodes (position) where the Element appears in the Derivation (m-Tree).
	 * 
	 */
	static Map<String, LinkedList<Position>> updateMap(Tree t){
		Position node;
		Collection<Position> cProcess;
		Collection<Position> cChilds;
		int num;
		Map<String, LinkedList<Position>> m = null;
		LinkedList<Position> l;
		Element e;
		
		if(t != null){
			
			m = new ConcurrentHashMap<String, LinkedList<Position>>();
			node = t.root();
			
			/* Process the root. */
			e = (Element)node.get("Element");
			if(m.containsKey(e.getSymbol())){
				l = m.get(e.getSymbol());
				l.add(node);
				m.put(e.getSymbol(), l);
			}else{
					l = new LinkedList<Position>();
					l.add(node);
					m.put(e.getSymbol(), l);
			}
			
			/* Loads the childs of the root in order. */
			num = t.numChildren(node);
			cProcess = new LinkedList<Position>();
			for(int i=0; i< num; i++){
				cProcess.add(t.childAtRank(node, i));
			}
			
			cChilds = new LinkedList<Position>();
			while(!cProcess.isEmpty()){
								
				
				for(Position p: cProcess){
					
					/*Process the actual node. */
					e = (Element)p.get("Element");
					if(m.containsKey(e.getSymbol())){
						l = m.get(e.getSymbol());
						l.add(p);
						m.put(e.getSymbol(), l);
					}else{
							l = new LinkedList<Position>();
							l.add(p);
							m.put(e.getSymbol(), l);
					}
					
					/* Load the childs of the actual node in order. */
					num = t.numChildren(p);
					for(int i=0; i< num; i++){
						cChilds.add(t.childAtRank(p, i));
					}
				}
				
				cProcess = cChilds;
				cChilds = new LinkedList<Position>();
			}
		}
		
		return m;
	}
	
	
	/**
	 * 
	 * The function has the following behavior: it updates the nodes that have an Element with a symbol "symbol" (those 
	 * nodes have associated a function f and a value v). The node's update is the following: the function f associated
	 * to the node is updated ( f2 = f.updateFunction ), and f2 (that sustitutes f in the node), is applied over v
	 * ( v2 = f2.applyFuntion[v] ), and the returning new value v2, sustitutes v in the node.
	 * 
	 * 
	 * @param t
	 * 			The m-Tree to be updated.
	 * 
	 * @param symbol
	 * 			     The symbol to be used to select the nodes to be updated in t.
	 * 
	 * @return
	 * 			The updated m-Tree.          	 
	 * 
	 * @throws GrammarExceptionImpl
	 * 
	 */
	static Tree updateTreeWithFunction(Tree t, String symbol) throws GrammarExceptionImpl{
		Position node;
		Collection<Position> cProcess;
		Collection<Position> cChilds;
		Element e;
		Function f;
		Function newF;
		Double newValue;
		int num;
		
		if(t != null){
			node = t.root();
			
			/* Process the root. */
			e = (Element)node.get("Element");
			if (((e.getSymbol()).toUpperCase()).equals(symbol.toUpperCase())){
				f = (Function)node.get("Function");
				if(f!=null){
					newF = f.updateFunction(0.01);
					newValue = newF.applyFunction((Double)node.get("Value"), 0.5);
					node.set("Value",newValue);
					node.set("Function",newF);
				}
			}
			
			
			/* Loads the childs of the root in order. */
			num = t.numChildren(node);
			cProcess = new LinkedList<Position>();
			for(int i=0; i< num; i++){
				cProcess.add(t.childAtRank(node, i));
			}
			
			cChilds = new LinkedList<Position>();
			while(!cProcess.isEmpty()){
								
				for(Position p: cProcess){
					
					/*Process the actual node. */
					e = (Element)p.get("Element");
					if (((e.getSymbol()).toUpperCase()).equals(symbol.toUpperCase())){
						f = (Function)p.get("Function");
						if(f!=null){
							newF = f.updateFunction(0.01);
							newValue = newF.applyFunction((Double)p.get("Value"), 0.5);
							p.set("Value", newValue);
							p.set("Function",newF);
						}
					}
					
					/* Load the childs of the actual node in order. */
					num = t.numChildren(p);
					for(int i=0; i< num; i++){
						cChilds.add(t.childAtRank(p, i));
					}
				}
				
				cProcess = cChilds;
				cChilds = new LinkedList<Position>();
			}
		}
		
		return t;
	}
	
	
	@SuppressWarnings({ "unchecked", "unchecked" })
	static Tree getMaxRandomDerivation(Tree t, int maxDepthGlobal, int maxDepthActual, Element startElement, Production p, Position father, StochasticContextFreeGrammar  g) throws GrammarExceptionImpl, GrammarMaxDepthExceptionImpl{
		
		NonTerminal nt = new NonTerminalImpl(0, "");
		Terminal te = new TerminalImpl(0, "");
		Collection<Production> cp;
		Production prod;
		boolean isTerminal = false;
		boolean find;
		boolean find2;
		Iterator<Production> ip;
		Iterator<Double> id;
		Production p_aux;
		Double d_aux;
		Class cl1;
		Class cl2;
		Position pos;
		Collection<Production> c1;
		Collection<Double> c2;
		Collection<Double> c2_aux;
		Sort<Production> sort;
		double pr;
		double pr_aux;
		Collection<Element> ce;
		int depth;
		Element e;
		
		cl1 = nt.getClass();
		cl2 = te.getClass();
		if(startElement == null){
			
			throw new GrammarExceptionImpl("The start element is NULL");
			
		}else{
			
				if((!cl1.isInstance(startElement))&&(!cl2.isInstance(startElement))){
					throw new GrammarExceptionImpl("The nodes of the derivation should be a NonTerminal or Terminal");
				}
		}
		
		
		if(t == null){
			t = new NodeTree();
		}
		
		
		if(cl2.isInstance(startElement)){
			
			isTerminal = true;
			e = ((Terminal)startElement).cloneTerminal();
			
		}else{
				e = ((NonTerminalImpl)startElement).cloneNonTerminal();
			
		}
		
		
		/* Base step*/
		if(isTerminal){
			
			/* is adding a depth of one. */
			if((maxDepthActual+1) > maxDepthGlobal){
				throw new GrammarMaxDepthExceptionImpl("There was an error an the max depth was " + "crossed. ");
			}
			if(father == null){
				pos = t.root();
			}else{
					/* The childs are added at the order that they are at the right side of
					 * the Production, then theirs rank (rankOfchild) are theirs positions
					 * in the right side of the Production. 
					 * Take care because ranks starts at 0. */
					if(t.isExternal(father)){
						/* If father doesn´t has childs. */
						pos = t.insertFirstChild(father, null);
					}else{
							/* If father has childs. */
							pos = t.insertLastChild(father, null);
					}
			}
			/* p is the Production that generates the node(Position=node). It's only null for the
			 * root node. */
			if(p!=null){
				pos.set("Production", p.cloneProduction());
			}else{
				   pos.set("Production", p);
			}
			/* e is the Element associated to the node (Position=node). */
			pos.set("Element", e);
			
			return t;
		}
		/* Recursive step */
		else{
				cp = ((NonTerminal)e).getAllLeftsProd(g);
				if(cp==null){
					throw new GrammarExceptionImpl("The NonTerminal " + e.getSymbol() + " doesn´t has Productions associated");
				}
								
				c1 = new LinkedList<Production>();
				c2 = new LinkedList<Double>();
				c2_aux = new LinkedList<Double>();
				pr_aux = 0;
				for(Production p2:cp){
					if(g.hasCalculatedLenghtsProductions()){
						depth = g.getLenghtProduction(p2);
					}else{
						throw new GrammarExceptionImpl("The Grammar doesn´t has the lengths of the " + "Productions calculated");
					}
					if((1+depth)<=maxDepthGlobal){
						c1.add(p2);
						pr = g.getProbability(p2);
						if(pr>=0){
							pr_aux = pr_aux + pr;
							c2_aux.add(pr);
						}else{
								throw new GrammarExceptionImpl("The Production " + p2.getSymbol() + " doesn´t has a probability associated");
						}
					}
				}
				
				if(c1.isEmpty()){
					throw new GrammarExceptionImpl("The NonTerminal " + e.getSymbol() + " doesn´t " + "has a Production associated so that " + "1 + Length(Production)<= " + maxDepthGlobal);
				}
				
				if(pr_aux<=0){
					throw new GrammarExceptionImpl("The probability of the Productions is equal or less than zero.");
				}
				
				/* Because there aren´t all the Productions that have nt at the left side, it is
				 * necessary to reconvert the probabilities of the productions that survive in order
				 * to sum one and conserve the distribution of probability. */
				ip = c1.iterator();
				id = c2_aux.iterator();
				while(ip.hasNext()){
					p_aux = ip.next();
					d_aux = id.next();
					c2.add((d_aux.doubleValue())/pr_aux);
				}
				
				
				/* Gets a random Production of the several that have the NonTerminal e at the left side. */
				sort = new Sort<Production>();
				prod = sort.sortElement(c1, c2);
				
				if(prod == null){
					throw new GrammarExceptionImpl("The sortElement method has failed. ");
				}
				
				ce = prod.getRights(g);
				if(ce == null){
					throw new GrammarExceptionImpl("The Production selected hasn´t rights symbols. ");
				}
				
							
				/* is adding a depth of one. */
				if((maxDepthActual+1) > maxDepthGlobal){
					throw new GrammarMaxDepthExceptionImpl("There was an error an the max detph was " + "crossed. ");
				}
				
				if(father == null){
					pos = t.root();
				}else{
						/* The childs are added at the order that they are at the right side of
						 * the Production, then theirs rank (rankOfchild) are theirs positions
						 * in the right side of the Production. 
						 * Take care because ranks starts at 0. */
						if(t.isExternal(father)){
							/* If father doesn´t has childs. */
							pos = t.insertFirstChild(father, null);
						}else{
								/* If father has childs. */
								pos = t.insertLastChild(father, null);
						}
						
				}
				
				/* p is the Production that generates the node(Position=node). It's only null for the
				 * root node. */
				if(p!=null){
					pos.set("Production", p.cloneProduction());
				}else{
						pos.set("Production", p);
				}
				/* e is the Element associated to the node (Position=node). */
				pos.set("Element", e);
								
				find2 = false;
				while(!find2){
					try{
						/* The childs are added at the order that they are at the right side of
						 * the Production. */
						if(!ce.isEmpty()){
							for(Element ele:ce){
								t=getMaxRandomDerivation(t, maxDepthGlobal, (1+maxDepthActual), ele, prod, pos, g);
							}
						}
						
						find2 = true;
						
					}catch(GrammarMaxDepthExceptionImpl ex){
							
						
							/* Removes "prod" from c1, because the Derivation with prod crosses the Max depth. */
							
						    find = false;
							ip = c1.iterator();
							id = c2.iterator();
							while((!find) && ip.hasNext()){
								p_aux = ip.next();
								d_aux = id.next();
								if(prod == p_aux){
									c1.remove(p_aux);
									c2.remove(d_aux);
									find = true;
								}
							}
							
							/** Delete the nodes added with the production "prod" deleted above. */
							for(int i=(t.numChildren(pos)-1); i >= 0; i--){
								t=deleteTree(t, t.childAtRank(pos, i));
							}
							
							/** If there aren´t more Productions then re-throw the Exception, because the 
							 *  element "e" at "pos" doesn't have productions that lead a derivation tree 
							 *  that fulfills the Max depth restriction. */
							if(c1.isEmpty()){
								
								/** If ROOT doesn't have more productions, then it's necessary to 
								 *  finish the search. */
								if(t.isRoot(pos)){
									find2 = true;
								}
								
								/** Delete "pos" and its childs because "pos" don't have more productions. */
								t = deleteTree(t, pos);
								
								if(!find2){
									throw ex;
								}
								
							}
							
							if(!find2){
								
								pr_aux=0;
								for(Double d2: c2){
									pr_aux = pr_aux + d2.doubleValue();
								}
							
								if(pr_aux<= 0){
									throw new GrammarExceptionImpl("The probability of the Productions is " + "equal or less than zero.");
								}
							
								/** Because there aren´t all the Productions that have nt at the left side, it is							 	* necessary to reconvert the probabilities of the productions that survive in order
								 * to sum one and conserve the distribution of probability. */
								c2_aux = new LinkedList<Double>();
								ip = c1.iterator();
								id = c2.iterator();
								while(ip.hasNext()){
									p_aux = ip.next();
									d_aux = id.next();
									c2_aux.add((d_aux.doubleValue())/pr_aux);
								}
							
								/* Obtain a new prod. */
								prod = sort.sortElement(c1, c2_aux);
								
								if(prod == null){
									throw new GrammarExceptionImpl("The sortElement method has failed. ");
								}
								
								ce = prod.getRights(g);
								if(ce == null){
									throw new GrammarExceptionImpl("The Production selected hasn´t rights symbols. ");								
								}	
							}
					}//catch
				}
				
				return t;
		}	
	}

	
	
	@SuppressWarnings("unchecked")
	static Tree getMaxRandomDerivationWithFunction(Tree t, int maxDepthGlobal, int maxDepthActual, Element startElement, 
			                                       Production p, Position father, StochasticContextFreeGrammar  g, 
			                                       Map<String, Double> features, Function f, String symbol, 
			                                       int symbol2_rank) throws GrammarExceptionImpl, GrammarMaxDepthExceptionImpl{
		
		NonTerminal nt = new NonTerminalImpl(0, "");
		Terminal te = new TerminalImpl(0, "");
		Collection<Production> cp;
		Production prod;
		boolean isTerminal = false;
		boolean find;
		boolean find2;
		Iterator<Production> ip;
		Iterator<Double> id;
		Production p_aux;
		Double d_aux;
		Class cl1;
		Class cl2;
		Position pos;
		Position pos2;
		Collection<Production> c1;
		Collection<Double> c2;
		Collection<Double> c2_aux;
		Sort<Production> sort;
		double pr;
		double pr_aux;
		Collection<Element> ce;
		int depth;
		Double value;
		Function f2;
		Element e;
		
		cl1 = nt.getClass();
		cl2 = te.getClass();
		if(startElement == null){
			
			throw new GrammarExceptionImpl("The start element is NULL");
			
		}else{
			
				if((!cl1.isInstance(startElement))&&(!cl2.isInstance(startElement))){
					throw new GrammarExceptionImpl("The nodes of the derivation should be a NonTerminal or Terminal");
				}
		}
		
		if(t == null){
			t = new NodeTree();
		}
		
		
		if(cl2.isInstance(startElement)){
			
			isTerminal = true;
			e = ((TerminalImpl)startElement).cloneTerminal();
			
		}else{
				e = ((NonTerminalImpl)startElement).cloneNonTerminal();
			
		}
		
		
		/* Base step*/
		if(isTerminal){
			
			/* is adding a depth of one. */
			if((maxDepthActual+1) > maxDepthGlobal){
				throw new GrammarMaxDepthExceptionImpl("There was an error an the max depth was " + "crossed. ");
			}
			if(father == null){
				pos = t.root();
			}else{
					/* The childs are added at the order that they are at the right side of
					 * the Production, then theirs rank (rankOfchild) are theirs positions
					 * in the right side of the Production. 
					 * Take care because ranks starts at 0. */
					if(t.isExternal(father)){
						/* If father doesn´t has childs. */
						pos = t.insertFirstChild(father, null);
					}else{
							/* If father has childs. */
							pos = t.insertLastChild(father, null);
					}
			}
			/* p is the Production that generates the node(Position=node). It's only null for the
			 * root node. */
			if(p!=null){
				pos.set("Production", p.cloneProduction());
			}else{
					pos.set("Production", p);
			}
			
			/* e is the Element associated to the node (Position=node). */
			pos.set("Element", e);
			
			if (!t.isRoot(pos)){
				
				// Associates the Value and the Function to the node with "symbol" 
				if(((e.getSymbol()).toUpperCase()).equals(symbol.toUpperCase())){
					
					try {
					
						pos2 = DerivationAux_InternalImpl.getNodeAtAbsoluteRank(t, pos, symbol2_rank);
						
						if(pos2 != null){
						
							value = features.get(((Element)(pos2.get("Element"))).getSymbol());
							
							if(value == null){
								value = features.get((((Element)(pos2.get("Element"))).getSymbol()).toUpperCase());
							}
							
							if(value != null){
								
								value = new Double(value.doubleValue());
								
								f2 = f.createFunction();
								if(f2 != null){
									pos.set("Value", value);
									pos.set("Function", f2);
								}else {
									pos.set("Value", null);
									pos.set("Function", null);
								}
							}else {
								pos.set("Value", null);
								pos.set("Function", null);
							}
						}else {
							pos.set("Value", null);
							pos.set("Function", null);
						}
					
					}catch (BoundaryViolationException e1) {
							throw new GrammarExceptionImpl(e1.getMessage());
					}
					catch (InvalidAccessorException e2){
						    throw new GrammarExceptionImpl(e2.getMessage());
					}
				
				}
			}else {
				if(((e.getSymbol().toUpperCase())).equals(symbol.toUpperCase())){
					
					pos.set("Value", null);
					pos.set("Function", null);
				}
			}
			
			return t;
		}
		/* Recursive step */
		else{
				cp = ((NonTerminal)e).getAllLeftsProd(g);
				if(cp==null){
					throw new GrammarExceptionImpl("The NonTerminal " + e.getSymbol() + " doesn´t has Productions associated");
				}
								
				c1 = new LinkedList<Production>();
				c2 = new LinkedList<Double>();
				c2_aux = new LinkedList<Double>();
				pr_aux = 0;
				for(Production p2:cp){
					if(g.hasCalculatedLenghtsProductions()){
						depth = g.getLenghtProduction(p2);
					}else{
						throw new GrammarExceptionImpl("The Grammar doesn´t has the lengths of the " + "Productions calculated");
					}
					if((1+depth)<=maxDepthGlobal){
						c1.add(p2);
						pr = g.getProbability(p2);
						if(pr>=0){
							pr_aux = pr_aux + pr;
							c2_aux.add(pr);
						}else{
								throw new GrammarExceptionImpl("The Production " + p2.getSymbol() + " doesn´t has a probability associated");
						}
					}
				}
				
				if(c1.isEmpty()){
					throw new GrammarExceptionImpl("The NonTerminal " + e.getSymbol() + " doesn´t " + "has a Production associated so that " + "1 + Length(Production)<= " + maxDepthGlobal);
				}
				
				if(pr_aux<=0){
					throw new GrammarExceptionImpl("The probability of the Productions is equal or " + "less than zero.");
				}
				
				/* Because there aren´t all the Productions that have nt at the left side, it is
				 * necessary to reconvert the probabilities of the productions that survive in order
				 * to sum one and conserve the distribution of probability. */
				ip = c1.iterator();
				id = c2_aux.iterator();
				while(ip.hasNext()){
					p_aux = ip.next();
					d_aux = id.next();
					c2.add((d_aux.doubleValue())/pr_aux);
				}
				
				
				/* Gets a random Production of the several that have the NonTerminal e at the left side. */
				sort = new Sort<Production>();
				prod = sort.sortElement(c1, c2);
								
				if(prod == null){
					throw new GrammarExceptionImpl("The sortElement method has failed. ");
				}
				
				ce = prod.getRights(g);
				if(ce == null){
					throw new GrammarExceptionImpl("The Production selected hasn´t rights symbols. ");
				}
				
							
				/* is adding a depth of one. */
				if((maxDepthActual+1) > maxDepthGlobal){
					throw new GrammarMaxDepthExceptionImpl("There was an error an the max detph was crossed. ");
				}
				if(father == null){
					pos = t.root();
				}else{
						/* The childs are added at the order that they are at the right side of
						 * the Production, then theirs rank (rankOfchild) are theirs positions
						 * in the right side of the Production. 
						 * Take care because ranks starts at 0. */
						if(t.isExternal(father)){
							/* If father doesn´t has childs. */
							pos = t.insertFirstChild(father, null);
						}else{
								/* If father has childs. */
								pos = t.insertLastChild(father, null);
						}
						
				}
				
				/* p is the Production that generates the node(Position=node). It's only null for the
				 * root node. */
				if(p!=null){
					pos.set("Production", p.cloneProduction());
				}else{
						pos.set("Production", p);
				}
				
				/* e is the Element associated to the node (Position=node). */
				pos.set("Element", e);
				
				if(!t.isRoot(pos)){
					
					// Associates the Value and the Function to the node with "symbol" 
					if(((e.getSymbol().toUpperCase())).equals(symbol.toUpperCase())){
						try {
							
							pos2 = DerivationAux_InternalImpl.getNodeAtAbsoluteRank(t, pos, symbol2_rank);
							
							if(pos2 != null){
							
								value = features.get(((Element)(pos2.get("Element"))).getSymbol());
								
								if(value == null){
									value = features.get((((Element)(pos2.get("Element"))).getSymbol()).toUpperCase());
								}
								
								if(value != null){
									
									value = new Double(value.doubleValue());
									
									f2 = f.createFunction();
									if(f2 != null){
										pos.set("Value", value);
										pos.set("Function", f2);
									}else {
										pos.set("Value", null);
										pos.set("Function", null);
									}
								}else {
									pos.set("Value", null);
									pos.set("Function", null);
								}
							}else {
								pos.set("Value", null);
								pos.set("Function", null);
							}
						
						}catch (BoundaryViolationException e1) {
								throw new GrammarExceptionImpl(e1.getMessage());
						}
						catch (InvalidAccessorException e2){
							    throw new GrammarExceptionImpl(e2.getMessage());
						}
					
					}
					
				}else {
						if(((e.getSymbol().toUpperCase())).equals(symbol.toUpperCase())){
							
							pos.set("Value", null);
							pos.set("Function", null);
						}
				}
								
				find2 = false;
				while(!find2){
					try{
						
						/* The childs are added at the order that they are at the right side of
						 * the Production. */
						if(!ce.isEmpty()){
							for(Element ele:ce){
								t=getMaxRandomDerivationWithFunction(t, maxDepthGlobal, (1+maxDepthActual), ele, prod, pos, g, features, f, symbol, symbol2_rank);
							}
						}
						
						find2 = true;
						
					}catch(GrammarMaxDepthExceptionImpl ex){
													
							/* Removes prod from c1, because the Derivation with prod crosses the Max depth. */
							find = false;
							ip = c1.iterator();
							id = c2.iterator();
							while((!find) && ip.hasNext()){
								p_aux = ip.next();
								d_aux = id.next();
								if(prod == p_aux){
									c1.remove(p_aux);
									c2.remove(d_aux);
									find = true;
								}
							}
							
							/** Delete the nodes added with the production "prod" deleted above. */
							for(int i=(t.numChildren(pos)-1); i >= 0; i--){
								t=deleteTreeWithFunction(t, t.childAtRank(pos, i), symbol);
							}
							
							/** If there aren´t more Productions then re-throw the Exception, because the 
							 *  element "e" at "pos" doesn't have productions that lead a derivation tree 
							 *  that fulfills the Max depth restriction. */
							if(c1.isEmpty()){
								
								/** If ROOT doesn't have more productions, then it's necessary to 
								 *  finish the search. */
								if(t.isRoot(pos)){
									find2 = true;
								}
								
								/** Delete "pos" and its childs because "pos" don't have more productions. */
								t = deleteTreeWithFunction(t, pos, symbol);
								
								if(!find2){
									throw ex;
								}
							}
							
							if(!find2){
								
								pr_aux=0;
								for(Double d2: c2){
									pr_aux = pr_aux + d2.doubleValue();
								}
							
								if(pr_aux<= 0){
									throw new GrammarExceptionImpl("The probability of the Productions is " + "equal or less than zero.");
								}
							
								/* Because there aren´t all the Productions that have nt at the left side, it is
								 * necessary to reconvert the probabilities of the productions that survive in order
								 * to sum one and conserve the distribution of probability. */
								c2_aux = new LinkedList<Double>();
								ip = c1.iterator();
								id = c2.iterator();
								while(ip.hasNext()){
									p_aux = ip.next();
									d_aux = id.next();
									c2_aux.add((d_aux.doubleValue())/pr_aux);
								}
							
								/* Obtain a new prod. */
								prod = sort.sortElement(c1, c2_aux);
								
								if(prod == null){
									throw new GrammarExceptionImpl("The sortElement method has failed. ");
								}
								
								ce = prod.getRights(g);
								if(ce == null){
									throw new GrammarExceptionImpl("The Production selected hasn´t rights symbols. ");
								}		
						}
					} //catch
				}
				
				return t;
		}	
	}
	


	
	@SuppressWarnings("unused")
	static Derivation[] crossoverGBX(Grammar g, Map<String, LinkedList<Position>> m1, Tree t1, Map<String, 
			                      LinkedList<Position>> m2, Tree t2, int maxDepthGlobal) throws GrammarExceptionImpl{
		
		Derivation[] result = null;
		Derivation res1;
		Derivation res2;
		Tree resTree1;
		Tree resTree2;
		Tree firstParent;
		Tree secondParent;
		Tree duplicate1;
		Tree duplicate2;
		Collection<Tree> ct1;
		Collection<Position> NT;
		Collection<Double> cd2;
		Sort<Tree> s1;
		Sort<Position> s2;
		Sort<Element> s3;
		Map<String, LinkedList<Position>> secondMap;
		Map<String, LinkedList<Position>> firstMap;
		boolean find;
		boolean find2;
		double amount;
		double pr;
		Position crossoverNode;
		Position crossoverNode2;
		Position position;
		Position position2;
		Position father;
		Position root;
		Element elementCrossoverNode;
		Element CS;
		Position parentCrossoverNode;
		NonTerminal start;
		NonTerminal A;
		NonTerminal nt1;
		NonTerminal nt2;
		Production prod1;
		Production prod2;
		Production generateCrossoverNode;
		Production gen1;
		Production gen2;
		LinkedList<Position> l;
		Collection<NonTerminal> cnt;
		Collection<Production> R_aux;
		Collection<Production> R;
		Collection<Element> X;
		Collection<Position> PN;
		int amountRigths;
		int posRigth;
		int P1;
		int P2;
		int pos1;
		int pos2;
		int absRank;
		int depth;
	
		if((t1!=null) && (t2!=null)){
		
		
			/* Select randomly one of the Trees as the First Parent. */
			ct1 = new LinkedList<Tree>();
			cd2 = new LinkedList<Double>();
			ct1.add(t1);
			cd2.add(0.5);
			ct1.add(t2);
			cd2.add(0.5);
			s1 = new Sort<Tree>();
			firstParent = s1.sortElement(ct1,cd2);
			if(firstParent == null){
				throw new GrammarExceptionImpl("The sortElement method has failed. ");
			}
			if(!firstParent.equals(t1)){
				secondParent = t1;
				secondMap = m1;
				firstMap = m2;
			
			}else{
					secondParent = t2;
					secondMap = m2;
					firstMap = m1;
			}
		
		
			/* Select randomly one of the nodes of the Tree that has a NonTerminal associated, it
			 * will be the crossoverNode. */
			cnt=g.getNonTerminals();
			start = g.getStart();
			/* Removes the start symbol, because there isn´t cross at the start symbol.*/
			cnt.remove(start);
					
			if((cnt == null)||(cnt.isEmpty())){
				throw new GrammarExceptionImpl("The Grammar doesn´t has NonTerminal symbols. ");
			}
		
			amount = cnt.size();
			if(amount == 0){
				throw new GrammarExceptionImpl("The Grammar doesn´t has NonTerminal symbols. ");
			}
		
			NT = new LinkedList<Position>();
			amount = 0;
			for(NonTerminal nt: cnt){
				if(firstMap.containsKey(nt.getSymbol())){
					l = firstMap.get(nt.getSymbol());
					if(l!=null){
						amount = amount+l.size();
						for(Position pos:l){
							NT.add(pos);
						}
					}
				}
			}
		
			/*If NT is empty then the two offspring  are identical to the parents. */
			if(!NT.isEmpty() && (amount >0)){
				find = false;
				while(!find && !NT.isEmpty()){
				
					pr = 1/amount;
					cd2 = new LinkedList<Double>();
					for(Position pos: NT){
						cd2.add(pr);
					}
				
					s2 = new Sort<Position>();
					crossoverNode = s2.sortElement(NT, cd2);
					if(crossoverNode == null){
						throw new GrammarExceptionImpl("The sortElement method has failed. ");
					}
				
					NT.remove(crossoverNode);
					
					/* Gets the Element associated to the crossoverNode. */
					elementCrossoverNode = (Element)crossoverNode.get("Element");
					if(elementCrossoverNode==null){
						throw new GrammarExceptionImpl("There wasn´t a Element associated");
					}
				
					/* Gets the Production that generates the crossoverNode. */
					generateCrossoverNode = (Production)crossoverNode.get("Production");
					if(generateCrossoverNode==null){
						throw new GrammarExceptionImpl("There wasn´t a Production associated");
					}
			
					amountRigths = (generateCrossoverNode.getRights(g)).size();
					
					/* Gets the position of the crossoverNode in the Production. */
					posRigth = firstParent.rankOfChild(crossoverNode);
					/* The ranks starts at 0, then is necessary to increment in one unit in order to start in 1*/
					posRigth++;
					
					/*Select the parent of the crossoverNode. */
					parentCrossoverNode = firstParent.parent(crossoverNode);
				
					/* Select the NonTerminal associated to the parent of the crossoverNode. */
					A = (NonTerminal)parentCrossoverNode.get("Element");
			
					if(A==null){
						throw new GrammarExceptionImpl("The parent of the crossoverNode doesn´t has a " + "NonTerminal symbol associated. ");
					}
			
					R_aux = A.getAllLeftsProd(g);
			
					if(R_aux==null){
						throw new GrammarExceptionImpl("There was a problem selecting the Productions that " + A.getSymbol() + " is at the left side");
					}
			
					/* Select the Productions that have the same amount of right symbols that the
					 * Production generateCrossoverNode. 
					 * It doesn´t remove from R_aux because is possible that removes from the structure
					 * of the NonTerminal, because several times the collections returned are a shadow
					 * copy of the original structure.*/
					R = new LinkedList<Production>();
					for(Production prod:R_aux){
						if(((prod.getRights(g)).size())==amountRigths){
							R.add(prod);
						}
					}
					
					
					if((R!=null)&&(!R.isEmpty())){
				
						/* Compares each Production in R against the generateCrossoverNode Production.
						 * If there is any symbol at any position (except the position posRigth), 
						 * that isn´t equal to the symbol at the same position in the generateCrossoverNode
						 * Production, the Production is deleted from R.*/
						X = new LinkedList<Element>();
						
						g.compareProductions(R, X, generateCrossoverNode, posRigth);
											
						if(!X.isEmpty()){
							while((!find) && (!X.isEmpty())){
								amount = X.size();
								if(amount == 0){
									throw new GrammarExceptionImpl("There was a problem with the size of " + "the Collection X. ");
								}
								pr = 1/amount;
								cd2 = new LinkedList<Double>();
								for(Element el: X){
									cd2.add(pr);
								}
								s3 = new Sort<Element>();
								CS = s3.sortElement(X, cd2);
								if(CS == null){
									throw new GrammarExceptionImpl("The sortElement method has failed. ");
								}
							
								X.remove(CS);
								
								l=null;
								if(secondMap.containsKey(CS.getSymbol())){
									l = secondMap.get(CS.getSymbol());
								}
								if((l!=null)&& (!l.isEmpty())){
									PN = new LinkedList<Position>();
									for(Position pos:l){
										PN.add(pos);
									}
									while((!find) &&(!PN.isEmpty())){
										amount = PN.size();
										if(amount == 0){
											throw new GrammarExceptionImpl("There was a problem with the size of " + "the Collection of nodes related to CS. ");
										}
										pr = 1/amount;
										cd2 = new LinkedList<Double>();
										for(Position pos: PN){
											cd2.add(pr);
										}
										crossoverNode2 = s2.sortElement(PN, cd2);
										if(crossoverNode2 == null){
											throw new GrammarExceptionImpl("The sortElement method has failed. ");
										}
									
										PN.remove(crossoverNode2);
									
										P1 = DerivationAux_InternalImpl.depthBackwards(crossoverNode, firstParent, 0) +
											 DerivationAux_InternalImpl.depth(crossoverNode2, secondParent);
										P2 = DerivationAux_InternalImpl.depthBackwards(crossoverNode2, secondParent, 0) +
										 	 DerivationAux_InternalImpl.depth(crossoverNode, firstParent);
										
										if((P1<=maxDepthGlobal)&&(P2<=maxDepthGlobal)){
										
											nt1 = (NonTerminal)crossoverNode.get("Element");
											nt2 = (NonTerminal)crossoverNode2.get("Element");
										
											if((nt1 != null) && (nt2 != null)){
											
												if(nt1.getSymbol().equals(nt2.getSymbol())){
												
													resTree1 = new NodeTree();
													DerivationAux_InternalImpl.duplicateTree(firstParent.root(), firstParent, resTree1, null);
								
													resTree2 = new NodeTree();
													DerivationAux_InternalImpl.duplicateTree(secondParent.root(), secondParent, resTree2, null);
													
													if((resTree1!=null)&&(resTree2!=null)){
													
														depth = DerivationAux_InternalImpl.depthBackwards(crossoverNode, firstParent, 0);
														absRank = DerivationAux_InternalImpl.getAbsoluteRank(firstParent, crossoverNode, depth);
														position = DerivationAux_InternalImpl.getPosition(resTree1, depth, absRank);
														
														duplicate1 = new NodeTree();
														DerivationAux_InternalImpl.duplicateTree(position, resTree1, duplicate1, null);
							
														depth = DerivationAux_InternalImpl.depthBackwards(crossoverNode2, secondParent, 0);
														absRank = DerivationAux_InternalImpl.getAbsoluteRank(secondParent, crossoverNode2, depth);
														position2 = DerivationAux_InternalImpl.getPosition(resTree2, depth, absRank);
														
														duplicate2 = new NodeTree();
														DerivationAux_InternalImpl.duplicateTree(position2, resTree2, duplicate2, null);
							
														if((duplicate1 != null) && (duplicate2 != null)){
									
															root = duplicate2.root();
															root.set("Production", ((Production)(crossoverNode.get("Production"))).cloneProduction());
															resTree1.replaceSubtree(position, duplicate2);
								
															root = duplicate1.root();
															root.set("Production", ((Production)(crossoverNode2.get("Production"))).cloneProduction());
															resTree2.replaceSubtree(position2, duplicate1);
														
															result = new Derivation[2];
															
															res1 = new DerivationImpl(g);
															((DerivationImpl)res1).setTree(resTree1);
															
															res2 = new DerivationImpl(g);
															((DerivationImpl)res2).setTree(resTree2);
															
															result[0] = res1;
															result[1] = res2;
								
															find = true;
														}
													}else{
															throw new GrammarExceptionImpl("There was a problem duplicating the trees.");
													}
												}else{
														/** Step 12*/
														prod1 = (Production)crossoverNode.get("Production");
														prod2 = (Production)crossoverNode2.get("Production");
														
														if(!firstParent.isRoot(crossoverNode)){
															/* The rank starts at zero. */
															pos1 = firstParent.rankOfChild(crossoverNode);
														}else{
																throw new GrammarExceptionImpl("There was a problem, because" + "in the root is only the start symbol and" + "this symbols is not allowed to be a crossover" + "node");
														}
														
														if(!secondParent.isRoot(crossoverNode2)){
															/* The rank starts at zero. */
															pos2 = secondParent.rankOfChild(crossoverNode2);
														}else{
																throw new GrammarExceptionImpl("There was a problem, because" + "in the root is only the start symbol and" + "this symbols is not allowed to be " + "a crossover node");
														}
														
														gen1 = g.findProduction(prod1, pos1, nt2);
														if(gen1!=null){
															find2 = true;
														}else{
																find2 = false;
														}
																											
														if(find2){
															
															gen2 = g.findProduction(prod2, pos2, nt1);
															if(gen2!=null){
																find2 = true;
															}else{
																	find2 = false;
															}
																											
															if(find2){
																
																resTree1 = new NodeTree();
																DerivationAux_InternalImpl.duplicateTree(firstParent.root(), firstParent, resTree1, null);
																
																resTree2 = new NodeTree();
																DerivationAux_InternalImpl.duplicateTree(secondParent.root(), secondParent, resTree2, null);
											
																if((resTree1!=null)&&(resTree2!=null)){
																
																	depth = DerivationAux_InternalImpl.depthBackwards(crossoverNode, firstParent, 0);
																	absRank = DerivationAux_InternalImpl.getAbsoluteRank(firstParent, crossoverNode, depth);
																	position = DerivationAux_InternalImpl.getPosition(resTree1, depth, absRank);
																
																	duplicate1 = new NodeTree();
																	DerivationAux_InternalImpl.duplicateTree(position, resTree1, duplicate1, null);
											
																	depth = DerivationAux_InternalImpl.depthBackwards(crossoverNode2, secondParent, 0);
																	absRank = DerivationAux_InternalImpl.getAbsoluteRank(secondParent, crossoverNode2, depth);
																	position2 = DerivationAux_InternalImpl.getPosition(resTree2, depth, absRank);
																	
																	duplicate2 = new NodeTree();
																	DerivationAux_InternalImpl.duplicateTree(position2, resTree2, duplicate2, null);
											
																	if((duplicate1 != null) && (duplicate2 != null)){
																	
													
																		root = duplicate2.root();
																		root.set("Production", gen1.cloneProduction());
																		resTree1.replaceSubtree(position, duplicate2);
													
																		root = duplicate1.root();
																		root.set("Production", gen2.cloneProduction());
																		resTree2.replaceSubtree(position2, duplicate1);
													
																		result = new Derivation[2];
																	
																		res1 = new DerivationImpl(g);
																		((DerivationImpl)res1).setTree(resTree1);
																	
																		res2 = new DerivationImpl(g);
																		((DerivationImpl)res2).setTree(resTree2);
																	
																		result[0] = res1;
																		result[1] = res2;
												
																		find = true;
																	}
																}else{
																		throw new GrammarExceptionImpl("There was a problem duplicating the trees.");
																}
															}
														}
													
													
												}
											}else{
													throw new GrammarExceptionImpl("The node has a null Element associated. ");
											} 
										
										}
									
									}
								
								}
							
							}
						
						}
					
					}
			
				}//while
			}
		}
	
		return result;
	}

	
	@SuppressWarnings("unused")
	static Derivation[] crossoverGBXWithFunction(Grammar g, Map<String, LinkedList<Position>> m1, Tree t1, Map<String, 
            LinkedList<Position>> m2, Tree t2, int maxDepthGlobal, String symbol) throws GrammarExceptionImpl{

		Derivation[] result = null;
		Derivation res1;
		Derivation res2;
		Tree resTree1;
		Tree resTree2;
		Tree firstParent;
		Tree secondParent;
		Tree duplicate1;
		Tree duplicate2;
		Collection<Tree> ct1;
		Collection<Position> NT;
		Collection<Double> cd2;
		Sort<Tree> s1;
		Sort<Position> s2;
		Sort<Element> s3;
		Map<String, LinkedList<Position>> secondMap;
		Map<String, LinkedList<Position>> firstMap;
		boolean find;
		boolean find2;
		double amount;
		double pr;
		Position crossoverNode;
		Position crossoverNode2;
		Position position;
		Position position2;
		Position father;
		Position root;
		Element elementCrossoverNode;
		Element CS;
		Position parentCrossoverNode;
		NonTerminal start;
		NonTerminal A;
		NonTerminal nt1;
		NonTerminal nt2;
		Production prod1;
		Production prod2;
		Production generateCrossoverNode;
		Production gen1;
		Production gen2;
		LinkedList<Position> l;
		Collection<NonTerminal> cnt;
		Collection<Production> R_aux;
		Collection<Production> R;
		Collection<Element> X;
		Collection<Position> PN;
		int amountRigths;
		int posRigth;
		int P1;
		int P2;
		int pos1;
		int pos2;
		int absRank;
		int depth;

		if((t1!=null) && (t2!=null)){

			/* Select randomly one of the Trees as the First Parent. */
			ct1 = new LinkedList<Tree>();
			cd2 = new LinkedList<Double>();
			ct1.add(t1);
			cd2.add(0.5);
			ct1.add(t2);
			cd2.add(0.5);
			s1 = new Sort<Tree>();
			firstParent = s1.sortElement(ct1,cd2);
			if(firstParent == null){
				throw new GrammarExceptionImpl("The sortElement method has failed. ");
			}
			if(!firstParent.equals(t1)){
				secondParent = t1;
				secondMap = m1;
				firstMap = m2;

			}else{
				secondParent = t2;
				secondMap = m2;
				firstMap = m1;
			}


			/* Select randomly one of the nodes of the Tree that has a NonTerminal associated, it
			 * will be the crossoverNode. */
			cnt=g.getNonTerminals();
			start = g.getStart();
			/* Removes the start symbol, because there isn´t cross at the start symbol.*/
			cnt.remove(start);

			if((cnt == null)||(cnt.isEmpty())){
				throw new GrammarExceptionImpl("The Grammar doesn´t has NonTerminal symbols. ");
			}

			amount = cnt.size();
			if(amount == 0){
				throw new GrammarExceptionImpl("The Grammar doesn´t has NonTerminal symbols. ");
			}

			NT = new LinkedList<Position>();
			amount = 0;
			for(NonTerminal nt: cnt){
				if(firstMap.containsKey(nt.getSymbol())){
					l = firstMap.get(nt.getSymbol());
					if(l!=null){
						amount = amount+l.size();
						for(Position pos:l){
							NT.add(pos);
						}
					}
				}
			}
			
			/*If NT is empty then the two offspring  are identical to the parents. */
			if(!NT.isEmpty() && (amount >0)){
				find = false;
				while(!find && !NT.isEmpty()){

					pr = 1/amount;
					cd2 = new LinkedList<Double>();
					for(Position pos: NT){
						cd2.add(pr);
					}

					s2 = new Sort<Position>();
					crossoverNode = s2.sortElement(NT, cd2);
					if(crossoverNode == null){
						throw new GrammarExceptionImpl("The sortElement method has failed. ");
					}

					NT.remove(crossoverNode);

					/* Gets the Element associated to the crossoverNode. */
					elementCrossoverNode = (Element)crossoverNode.get("Element");
					if(elementCrossoverNode==null){
						throw new GrammarExceptionImpl("There wasn´t a Element associated");
					}

					/* Gets the Production that generates the crossoverNode. */
					generateCrossoverNode = (Production)crossoverNode.get("Production");
					if(generateCrossoverNode==null){
						throw new GrammarExceptionImpl("There wasn´t a Production associated");
					}

					amountRigths = (generateCrossoverNode.getRights(g)).size();

					/* Gets the position of the crossoverNode in the Production. */
					posRigth = firstParent.rankOfChild(crossoverNode);
					/* The ranks starts at 0, then is necessary to increment in one unit in order to start in 1*/
					posRigth++;

					/*Select the parent of the crossoverNode. */
					parentCrossoverNode = firstParent.parent(crossoverNode);

					/* Select the NonTerminal associated to the parent of the crossoverNode. */
					A = (NonTerminal)parentCrossoverNode.get("Element");

					
					if(A==null){
						throw new GrammarExceptionImpl("The parent of the crossoverNode doesn´t has a " + "NonTerminal symbol associated. ");
					}

					R_aux = A.getAllLeftsProd(g);

					if(R_aux==null){
						throw new GrammarExceptionImpl("There was a problem selecting the Productions that " + A.getSymbol() + " is at the left side");
					}

					/* Select the Productions that have the same amount of right symbols that the
					 * Production generateCrossoverNode. 
					 * It doesn´t remove from R_aux because is possible that removes from the structure
					 * of the NonTerminal, because several times the collections returned are a shadow
					 * copy of the original structure.*/
					R = new LinkedList<Production>();
					for(Production prod:R_aux){
						if(((prod.getRights(g)).size())==amountRigths){
							R.add(prod);
						}
					}

					
					if((R!=null)&&(!R.isEmpty())){

						/* Compares each Production in R against the generateCrossoverNode Production.
						 * If there is any symbol at any position (except the position posRigth), 
						 * that isn´t equal to the symbol at the same position in the generateCrossoverNode
						 * Production, the Production is deleted from R.*/
						X = new LinkedList<Element>();
	
						g.compareProductions(R, X, generateCrossoverNode, posRigth);
						
						if(!X.isEmpty()){
							
							while((!find) && (!X.isEmpty())){
								amount = X.size();
								if(amount == 0){
									throw new GrammarExceptionImpl("There was a problem with the size of " + "the Collection X. ");
								}
								pr = 1/amount;
								cd2 = new LinkedList<Double>();
								for(Element el: X){
									cd2.add(pr);
								}
								s3 = new Sort<Element>();
								CS = s3.sortElement(X, cd2);
								if(CS == null){
									throw new GrammarExceptionImpl("The sortElement method has failed. ");
								}
		
								X.remove(CS);
			
								l=null;
								if(secondMap.containsKey(CS.getSymbol())){
									l = secondMap.get(CS.getSymbol());
								}
								if((l!=null)&& (!l.isEmpty())){
									PN = new LinkedList<Position>();
									for(Position pos:l){
										PN.add(pos);
									}
									while((!find) &&(!PN.isEmpty())){
										amount = PN.size();
										if(amount == 0){
											throw new GrammarExceptionImpl("There was a problem with the size of " + "the Collection of nodes related to CS. ");
										}
										pr = 1/amount;
										cd2 = new LinkedList<Double>();
										for(Position pos: PN){
											cd2.add(pr);
										}
										crossoverNode2 = s2.sortElement(PN, cd2);
										if(crossoverNode2 == null){
											throw new GrammarExceptionImpl("The sortElement method has failed. ");
										}
				
										PN.remove(crossoverNode2);
				
										P1 = DerivationAux_InternalImpl.depthBackwards(crossoverNode, firstParent, 0) +
										DerivationAux_InternalImpl.depth(crossoverNode2, secondParent);
										P2 = DerivationAux_InternalImpl.depthBackwards(crossoverNode2, secondParent, 0) +
										DerivationAux_InternalImpl.depth(crossoverNode, firstParent);
					
										if((P1<=maxDepthGlobal)&&(P2<=maxDepthGlobal)){
					
											nt1 = (NonTerminal)crossoverNode.get("Element");
											nt2 = (NonTerminal)crossoverNode2.get("Element");
					
											if((nt1 != null) && (nt2 != null)){
						
												if(nt1.getSymbol().equals(nt2.getSymbol())){
							
													resTree1 = new NodeTree();
													DerivationAux_InternalImpl.duplicateTreeWithFunction(firstParent.root(), firstParent, resTree1, null, symbol);
								
													resTree2 = new NodeTree();
													DerivationAux_InternalImpl.duplicateTreeWithFunction(secondParent.root(), secondParent, resTree2, null, symbol);
													
													if((resTree1!=null)&&(resTree2!=null)){
													
														depth = DerivationAux_InternalImpl.depthBackwards(crossoverNode, firstParent, 0);
														absRank = DerivationAux_InternalImpl.getAbsoluteRank(firstParent, crossoverNode, depth);
														position = DerivationAux_InternalImpl.getPosition(resTree1, depth, absRank);
														
														duplicate1 = new NodeTree();
														DerivationAux_InternalImpl.duplicateTreeWithFunction(position, resTree1, duplicate1, null, symbol);
							
														depth = DerivationAux_InternalImpl.depthBackwards(crossoverNode2, secondParent, 0);
														absRank = DerivationAux_InternalImpl.getAbsoluteRank(secondParent, crossoverNode2, depth);
														position2 = DerivationAux_InternalImpl.getPosition(resTree2, depth, absRank);
														
														duplicate2 = new NodeTree();
														DerivationAux_InternalImpl.duplicateTreeWithFunction(position2, resTree2, duplicate2, null, symbol);
							
														if((duplicate1 != null) && (duplicate2 != null)){
									
															root = duplicate2.root();
															root.set("Production", ((Production)(crossoverNode.get("Production"))).cloneProduction());
															resTree1.replaceSubtree(position, duplicate2);
								
															root = duplicate1.root();
															root.set("Production", ((Production)(crossoverNode2.get("Production"))).cloneProduction());
															resTree2.replaceSubtree(position2, duplicate1);
														
															result = new Derivation[2];
															
															res1 = new DerivationImpl(g);
															((DerivationImpl)res1).setTree(resTree1);
															
															res2 = new DerivationImpl(g);
															((DerivationImpl)res2).setTree(resTree2);
															
															result[0] = res1;
															result[1] = res2;
								
															find = true;
														}
													}else{
														throw new GrammarExceptionImpl("There was a problem duplicating the trees.");
													}
												}else{
													/** Step 12*/
													prod1 = (Production)crossoverNode.get("Production");
													prod2 = (Production)crossoverNode2.get("Production");
									
													if(!firstParent.isRoot(crossoverNode)){
														/* The rank starts at zero. */
														pos1 = firstParent.rankOfChild(crossoverNode);
													}else{
														throw new GrammarExceptionImpl("There was a problem, because" + "in the root is only the start symbol and" + "this symbols is not allowed to be a crossover" + "node");
													}
									
													if(!secondParent.isRoot(crossoverNode2)){
														/* The rank starts at zero. */
														pos2 = secondParent.rankOfChild(crossoverNode2);
													}else{
														throw new GrammarExceptionImpl("There was a problem, because" + "in the root is only the start symbol and" + "this symbols is not allowed to be " + "a crossover node");
													}
									
													gen1 = g.findProduction(prod1, pos1, nt2);
													if(gen1!=null){
														find2 = true;
													}else{
														find2 = false;
													}
																						
													if(find2){
										
														gen2 = g.findProduction(prod2, pos2, nt1);
														if(gen2!=null){
															find2 = true;
														}else{
															find2 = false;
														}
																						
														if(find2){
															
															resTree1 = new NodeTree();
															DerivationAux_InternalImpl.duplicateTreeWithFunction(firstParent.root(), firstParent, resTree1, null, symbol);
															
															resTree2 = new NodeTree();
															DerivationAux_InternalImpl.duplicateTreeWithFunction(secondParent.root(), secondParent, resTree2, null, symbol);
										
															if((resTree1!=null)&&(resTree2!=null)){
															
																depth = DerivationAux_InternalImpl.depthBackwards(crossoverNode, firstParent, 0);
																absRank = DerivationAux_InternalImpl.getAbsoluteRank(firstParent, crossoverNode, depth);
																position = DerivationAux_InternalImpl.getPosition(resTree1, depth, absRank);
															
																duplicate1 = new NodeTree();
																DerivationAux_InternalImpl.duplicateTreeWithFunction(position, resTree1, duplicate1, null, symbol);
										
																depth = DerivationAux_InternalImpl.depthBackwards(crossoverNode2, secondParent, 0);
																absRank = DerivationAux_InternalImpl.getAbsoluteRank(secondParent, crossoverNode2, depth);
																position2 = DerivationAux_InternalImpl.getPosition(resTree2, depth, absRank);
																
																duplicate2 = new NodeTree();
																DerivationAux_InternalImpl.duplicateTreeWithFunction(position2, resTree2, duplicate2, null, symbol);
										
																if((duplicate1 != null) && (duplicate2 != null)){
																
												
																	root = duplicate2.root();
																	root.set("Production", gen1.cloneProduction());
																	resTree1.replaceSubtree(position, duplicate2);
												
																	root = duplicate1.root();
																	root.set("Production", gen2.cloneProduction());
																	resTree2.replaceSubtree(position2, duplicate1);
												
																	result = new Derivation[2];
																
																	res1 = new DerivationImpl(g);
																	((DerivationImpl)res1).setTree(resTree1);
																
																	res2 = new DerivationImpl(g);
																	((DerivationImpl)res2).setTree(resTree2);
																
																	result[0] = res1;
																	result[1] = res2;
											
																	find = true;
																}
															}else{
																throw new GrammarExceptionImpl("There was a problem duplicating the trees.");
															}
														}
													}
								
								
												}
											}else{
												throw new GrammarExceptionImpl("The node has a null Element associated. ");
											} 
					
										}
				
									}
			
								}
		
							}
	
						}

					}

				}//while
			}
		}

		return result;
	}
	

	public static Collection<Element> getLeavesInLeftOrder(Tree t) throws GrammarExceptionImpl {
		
		Collection<Element> res = null;
		Position node;
		
		if(t != null){
			node = t.root();
			if (node != null) {
				res = getLeavesInLeftOrderAux(node, t, null);
			}
		}
		
		return res;
	}
	
	
	public static Collection<Element> getLeavesInLeftOrderWithFunction(Tree t, String symbol) throws GrammarExceptionImpl {
		
		Collection<Element> res = null;
		Position node;
		
		if(t != null){
			node = t.root();
			if (node != null) {
				res = getLeavesInLeftOrderAux(node, t, symbol);
			}
		}
		
		return res;
	}
	
	
	/**
	 * 
	 * Recursive function in order to help the function getLeavesInLeftOrder.
	 * 
	 */
	public static Collection<Element> getLeavesInLeftOrderAux(Position node, Tree t, String s) throws GrammarExceptionImpl {
		
		Collection<Element> res = null;
		Collection<Element> aux;
		Iterator<Element> ie;
		Element e;
		Element el;
		Element elAux;
		Double value;
		Position node2;
		Iterator<Position> it ;
		Collection<Position> childs;
		int num;
		
		if (t.isExternal(node)){
			
			res = new LinkedList<Element>();
			el = (Element)node.get("Element");
			if(s!=null){
				if(((el.getSymbol()).toUpperCase()).equals(s.toUpperCase())){
					
					value = (Double)node.get("Value");
					if(value != null){
						
						elAux = new ElementImpl(0, value.toString());
						res.add(elAux);
						
					}else{
							res.add(el);
					}
					
				}else{
						res.add(el);
				}	
			}else{
					res.add(el);
			}
			return res;
			
		}else{
			
			/* Loads the childs of the node in order. */
			num = t.numChildren(node);
			childs = new LinkedList<Position>();
			for(int i=0; i< num; i++){
				childs.add(t.childAtRank(node, i));
			}
			
			it = childs.iterator();
			res = new LinkedList<Element>();
			
			while (it.hasNext()){
				node2 = it.next();
				if (t.isExternal(node2)){
					
					el = (Element)node2.get("Element");
					if(s!=null){
						
						if(((el.getSymbol()).toUpperCase()).equals(s.toUpperCase())){
							
							value = (Double)node2.get("Value");
							if(value != null){
								
								elAux = new ElementImpl(0, value.toString());
								res.add(elAux);
								
							}else{
									res.add(el);
							}
							
						}else{
								res.add(el);
						}
						
					}else{
							res.add(el);
					}
					
				}else {
						aux = getLeavesInLeftOrderAux(node2, t, s);
						
						ie = aux.iterator();
						while (ie.hasNext()){
							e = ie.next();
							res.add(e);
						}
				}
			}
			
			return res;
			
		}
	}
	
	
}
