package core.grammar;

/**
 * 
 * Class that offers a concrete implementation of the interface Derivation.
 * 
 * The class encapsulates (hides) the implementation of a derivation of the Grammar (it could be a 
 * Tree or another structure, that structure is encapsulated).
 * 
 * It belongs to the Public API.
 * 
 * @author Jorge Couchet
 * 
 **/



/**    
 *  IMPORTANTE !!!!
 *  También debo arreglar en cada clase que devuelve una derivation para que carguen el
 *   MAP, ahora no lo estoy haciendo y eso está  mal */


import jdsl.core.api.Tree;
import jdsl.core.api.Position;
import jdsl.core.ref.NodeTree;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DerivationImpl implements Derivation {
	
	
	
	/** The holder of the derivation of the Grammar. */
	private Tree derivation;
	
	/** For each element, map to a list with the Positions where appears in the Derivation. */
	private Map<String, LinkedList<Position>> map;
	
	/** Holds the Grammar related to the Derivation. */
	Grammar grammar;
	
	/** Holds the signature of the Derivation.*/
	Double signature;
	
	
	
	public DerivationImpl(Grammar g) throws GrammarExceptionImpl{
		if(g==null){
			throw new GrammarExceptionImpl("The Grammar is NULL.");
		}
		this.grammar = g;
		this.derivation = null;
		this.map = null;
		this.signature = null;
	}
	
	
	public void clearDerivation() throws GrammarExceptionImpl {
		
		if(this.map!=null){
			this.map.clear();
		}
		
		if(this.derivation!=null){
			this.derivation = DerivationAux_InternalImpl.deleteTree(this.derivation, this.derivation.root());
			this.derivation = null;
		}
		
		this.map = null;
		this.grammar = null;
		this.signature = null;
		
	}
	
	
	public void clearDerivationWithFunction(String symbol) throws GrammarExceptionImpl {
		
		if(this.map!=null){
			this.map.clear();
		}
		
		if(this.derivation!=null){
			this.derivation = DerivationAux_InternalImpl.deleteTreeWithFunction(this.derivation, this.derivation.root(), symbol);
			this.derivation = null;
		}
		
		this.map = null;
		this.grammar = null;
		this.signature = null;
		
	}
	
	
	Tree getTree(){
		return this.derivation;
	}
	
	
	void setTree(Tree t){
		this.derivation = t;
	}
	
	
	Map<String, LinkedList<Position>> getMap(){
		return this.map;
	}
	
	
	void setMap(Map<String, LinkedList<Position>> m){
		this.map = m;
	}
	
	
	private Grammar getGrammar(){
		return this.grammar;
	}
	
	
		
	public Derivation getMaxRandomDerivation(int maxDepthGlobal, NonTerminal nt) throws GrammarExceptionImpl, GrammarMaxDepthExceptionImpl{
		
		Collection<Production> cp;
		Derivation result;
		Tree resTree = null;
		Map<String, LinkedList<Position>> resMap;
		int minimumDepth;
		
		if(this.grammar == null){
			throw new GrammarExceptionImpl("The Grammar is NULL. ");
		}
		
		if(nt == null){
			throw new GrammarExceptionImpl("The NonTerminal is NULL. ");
		}
		
		cp = nt.getAllLeftsProd(this.grammar);
		if(cp==null){
			throw new GrammarExceptionImpl("The NonTerminal " + nt.getSymbol() + " doesn´t has Productions associated");
		}
		
		if(!this.grammar.hasCalculatedLenghtsProductions()){
			this.grammar.calculateLengthProductions();
		}
		
		minimumDepth = this.grammar.calculateMinimumDepth();
		if( minimumDepth > maxDepthGlobal){
			throw new GrammarExceptionImpl("The maximum global depth given " + maxDepthGlobal + " is less than the minimum depth " + minimumDepth );
		}
		
		
		result = new DerivationImpl(this.grammar);
		
		resTree = DerivationAux_InternalImpl.getMaxRandomDerivation(resTree, maxDepthGlobal, 0, nt, null, null, (StochasticContextFreeGrammar)this.grammar);
		resMap = DerivationAux_InternalImpl.updateMap(resTree);
		
		((DerivationImpl)result).setTree(resTree);
		((DerivationImpl)result).setMap(resMap);
				
		return result;
	}
	
	
	public Derivation getMaxRandomDerivationWithFunction(int maxDepthGlobal, NonTerminal nt, Map<String, Double> features,
			                                             Function f, String symbol, int symbol2_pos) throws GrammarExceptionImpl, GrammarMaxDepthExceptionImpl{
		
		Collection<Production> cp;
		Derivation result;
		Tree resTree = null;
		Map<String, LinkedList<Position>> resMap;
		int minimumDepth;
		
		if(this.grammar == null){
			throw new GrammarExceptionImpl("The Grammar is NULL. ");
		}
		
		if(nt == null){
			throw new GrammarExceptionImpl("The NonTerminal is NULL. ");
		}
		
		cp = nt.getAllLeftsProd(this.grammar);
		if(cp==null){
			throw new GrammarExceptionImpl("The NonTerminal " + nt.getSymbol() + " doesn´t has Productions associated");
		}
		
		if(!this.grammar.hasCalculatedLenghtsProductions()){
			this.grammar.calculateLengthProductions();
		}
		
		minimumDepth = this.grammar.calculateMinimumDepth();
		if( minimumDepth > maxDepthGlobal){
			throw new GrammarExceptionImpl("The maximum global depth given " + maxDepthGlobal + " is less than the minimum depth " + minimumDepth );
		}
		
		result = new DerivationImpl(this.grammar);
		
		resTree = DerivationAux_InternalImpl.getMaxRandomDerivationWithFunction(resTree, maxDepthGlobal, 0, nt, null, null, (StochasticContextFreeGrammar)this.grammar, features, f, symbol, symbol2_pos);
		resMap = DerivationAux_InternalImpl.updateMap(resTree);
		
		((DerivationImpl)result).setTree(resTree);
		((DerivationImpl)result).setMap(resMap);
				
		return result;
	}
	
	
	public Derivation [] crossoverGBX(Derivation d1, Derivation d2, int maxDepthGlobal) throws GrammarExceptionImpl {
		
		Derivation[] result = null;
		Derivation d;
		Map<String, LinkedList<Position>> m;
		
		if((d1!=null)&&(d2!=null)&&(maxDepthGlobal>0)){
		
			result = DerivationAux_InternalImpl.crossoverGBX(((DerivationImpl)d1).getGrammar(), ((DerivationImpl)d1).getMap(), ((DerivationImpl)d1).getTree(), 
				               ((DerivationImpl)d2).getMap(), ((DerivationImpl)d2).getTree(), maxDepthGlobal);
			
			if(result != null){
				/* Load the map of each derivation of result. */
				for(int i =0; i<result.length; i++){
					d = result[i];
					if(d!=null){
						m = DerivationAux_InternalImpl.updateMap(((DerivationImpl)d).getTree());
						((DerivationImpl)d).setMap(m);
						result[i]=d;
					}
				}
				
			}
		}
		
		return result;
	}
	
	
	public Derivation [] crossoverGBXWithFunction(Derivation d1, Derivation d2, int maxDepthGlobal, String symbol) throws GrammarExceptionImpl {
		
		Derivation[] result = null;
		Derivation d;
		Map<String, LinkedList<Position>> m;
		Tree t;
		
		if((d1!=null)&&(d2!=null)&&(maxDepthGlobal>0)){
		
			result = DerivationAux_InternalImpl.crossoverGBXWithFunction(((DerivationImpl)d1).getGrammar(), ((DerivationImpl)d1).getMap(), ((DerivationImpl)d1).getTree(), 
				               ((DerivationImpl)d2).getMap(), ((DerivationImpl)d2).getTree(), maxDepthGlobal, symbol);
			
			if(result != null){
				
				/* Load the map of each derivation of result. */
				for(int i =0; i<result.length; i++){
					
					d = result[i];
					
					if(d!=null){
						t = ((DerivationImpl)d).getTree();
						t = DerivationAux_InternalImpl.updateTreeWithFunction(t, symbol);
						((DerivationImpl)d).setTree(t);
						m = DerivationAux_InternalImpl.updateMap(((DerivationImpl)d).getTree());
						((DerivationImpl)d).setMap(m);
						result[i]=d;
					}
				}
				
			}
		}
		
		return result;
	}
	
		
	public void crossByLevels(){
		if(this.derivation!=null){
			DerivationAux_InternalImpl.crossByLevels(this.derivation);
		}
	}
	
	
	public void crossByLevelsWithFunction(String s){
		if(this.derivation!=null){
			DerivationAux_InternalImpl.crossByLevelsWithFunction(this.derivation, s);
		}
	}
	
	
	public Collection<Element> getLeavesInLeftOrder() throws GrammarExceptionImpl {
		
		Collection<Element> res = null;
		
		if(this.derivation !=null){
			res = DerivationAux_InternalImpl.getLeavesInLeftOrder(this.derivation);
		}
		
		return res;
	}
	
	
	public Collection<Element> getLeavesInLeftOrderWithFunction(String symbol) throws GrammarExceptionImpl {
		
		Collection<Element> res = null;
		
		if(this.derivation !=null){
			res = DerivationAux_InternalImpl.getLeavesInLeftOrderWithFunction(this.derivation, symbol);
		}
		
		return res;
	}
	
	
	public int depth(){
		
		if(this.derivation!=null){
			return DerivationAux_InternalImpl.depth(this.derivation.root(), this.derivation);
		}else{
				return 0;
		}
	}
	
	
	public Derivation duplicateDerivation(int level, int absRank) throws GrammarExceptionImpl{
		
		Tree resultTree = null;
		DerivationImpl result = new DerivationImpl(this.grammar);
		int depth;
		Position position;
		ConcurrentHashMap<String, LinkedList<Position>> map;
		
		depth = DerivationAux_InternalImpl.depth(this.derivation.root(), this.derivation);
		
		if((level<=0) || (level> depth) || (absRank<0)){
			throw new GrammarExceptionImpl("There was a problem with the parameters.");
		}
				
		position = DerivationAux_InternalImpl.getPosition(this.derivation, level, absRank);
		
		map = new ConcurrentHashMap<String, LinkedList<Position>>();
		resultTree = new NodeTree();
		DerivationAux_InternalImpl.duplicateTree(position, this.derivation, resultTree, null, map);
		
		result.setMap(map);
		result.setTree(resultTree);
		
		return result;
	}
	
	
	public void getLevelsRanks(Element e){
		int level;
		LinkedList<Position> l;
		
		if((e!=null)&&(this.map.containsKey(e))){
			l = this.map.get(e);
			for(Position pos:l){
				level = DerivationAux_InternalImpl.depthBackwards(pos, this.derivation, 0);
				System.out.println("Level: " + level  + " " + "Absolute rank: " + DerivationAux_InternalImpl.getAbsoluteRank(this.derivation, pos, level));
			}
		}
	}
	
	
	public Double getSignature() {
		return this.signature;
	}


}
