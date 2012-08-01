package core.grammar;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;



/** 
 * 
 * Class that offers a concrete implementation of the interface Grammar.
 * 
 * It belongs to the Public API.
 *
 * @author Jorge Couchet.
 * 
 * 
 **/

public class GrammarImpl implements Grammar {

	/** Holds the maximum id used as key for the Elements of the Grammar (NonTerminals, Terminals and 
	 *  Productions). Then if a new Element is created, its ID will be idMax + 1. **/
	private int idMax;
	
	/** Holds the Terminals of the Grammar. */
	private Terminals_internal terminals;
	
	/** Holds the NonTerminals of the Grammar. */
	private NonTerminals_internal nonterminals;
	
	/** Holds the Productions of the Grammar. */
	private Productions_internal productions;
	
	/** Holds the start symbol of the Grammar. */
	private NonTerminal start;
	
	/** Holds the lenght of each Production of the Grammar. */
	private Map<Integer,Integer> lengthsP;
	
	/** Holds the lenght of each NonTerminal of the Grammar. */
	private Map<Integer,Integer> lengthsNt;
	
	/** Flag in order to determinate that the lenghts of the Productions associated
	 *  to the Grammar where calculated (TRUE) or not (FALSE). */
	private boolean hasCalculatedLenghtsP;
	
	/** Flag in order to determinate that the lenghts of the NonTerminals associated
	 *  to the Grammar where calculated (TRUE) or not (FALSE). */
	private boolean hasCalculatedLenghtsNt;
	
	/** Stores the Graph that maps the Grammar. */
	private GraphGrammar_internal graph;
	
	/** Stores an object of the class LenghtProductionAlgorithmInternal (composition)
	 *  in order to calculate the lengths of the NonTerminals and Productions. */
	private LengthProductionAlgorithm_internal lengthAl;
	
	
	/**
	 * 
	 * Empty constructor.
	 *
	 */
	public GrammarImpl(){
		this.idMax = 0;
		this.terminals = new Terminals_internalImpl();
		this.nonterminals = new NonTerminals_internalImpl();
		this.productions = new Productions_internalImpl();
		this.start = null;
		this.lengthsP = new ConcurrentHashMap<Integer, Integer>();
		this.lengthsNt = new ConcurrentHashMap<Integer, Integer>();
		this.hasCalculatedLenghtsP = false;
		this.hasCalculatedLenghtsNt = false;
		this.graph = null;
		this.lengthAl = new LengthProductionAlgorithm_internalImpl();
		
	}
	
	
	public int calculateLenghtNonTerminal(NonTerminal nt) throws GrammarExceptionImpl {
		
		return this.lengthAl.lengthNonTerminal(this, nt, null, true);
	}
	
	
	public int calculateMinimumDepth() throws GrammarExceptionImpl {
		
		return this.calculateLenghtNonTerminal(this.getStart());
	}
	
	
	public int calculateLenghtProduction(Production p) throws GrammarExceptionImpl {
		
		return this.lengthAl.lengthProduction(this, p, true);
	}
	
	
	public void calculateLengthNonTerminals() throws GrammarExceptionImpl {
		
		this.lengthAl.storeLengthNonTerminals(this);
		
		/* If all were rigth then set hasCalculatedLenghtsNt to TRUE. */
		this.hasCalculatedLenghtsNt = true;
		
	}
	
	
	public void calculateLengthProductions()throws GrammarExceptionImpl {
		
		this.lengthAl.storeLengthProductions(this);
		
		/* If all were rigth then set hasCalculatedLenghtsP to TRUE. */
		this.hasCalculatedLenghtsP = true;
		
	}

	
	public int getLenghtProduction(Production p) {
		int result=-1;
		/* checks if the ID of p is in the Map. */
		if (this.lengthsP.containsKey(p.getId())){
			result = this.lengthsP.get(p.getId());
		}
		return result;
	}
	
	
	public int getLenghtNonTerminal(NonTerminal nt) {
		int result=-1;
		/* checks if the ID of p is in the Map. */
		if (this.lengthsNt.containsKey(nt.getId())){
			result = this.lengthsNt.get(nt.getId());
		}
		return result;
	}

	
	public NonTerminal getNonTerminal(NonTerminal nt) {
		return this.nonterminals.get(nt);
	}
	
	
	public NonTerminal getNonTerminal(int id) {
		return this.nonterminals.get(id);
	}
	
	
	public NonTerminal getNonTerminal(String symbol) {
		return this.nonterminals.get(symbol);
	}

	
	public Production getProduction(Production p) {
		return this.productions.get(p);
	}
	
	
	public Production getProduction(int id) {
		return this.productions.get(id);
	}
	
	
	public Production getProduction(String symbol) {
		return this.productions.get(symbol);
	}

	
	public Element getElement(int id) {
		Element res;
		
		res = this.nonterminals.get(id);
		
		if(res == null){
			
			res = this.terminals.get(id);
			
			if(res == null){
				res = this.productions.get(id);
			}
			
		}
		
		return res;
	}
	
	
	public Element getElement(String symbol) {
		Element res;
		
		res = this.nonterminals.get(symbol);
		
		if(res == null){
			
			res = this.terminals.get(symbol);
			
			if(res == null){
				res = this.productions.get(symbol);
			}
			
		}
		
		return res;
	}
	
	
	public Derivation getRandomDerivation() {
		//TODO Auto-generated method stub
		
		return null;
	}

	
	public Derivation getRandomDerivation(Production p) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Derivation getRandomDerivation(NonTerminal nt) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Derivation getRandomDerivationMaxLenght(int l) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Derivation getRandomDerivationMaxLenght(Production p, int l) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Derivation getRandomDerivationMaxLenght(NonTerminal nt, int l) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void setIdMax (int idMax){
		this.idMax = idMax;
	}
	
	
	public int getIdMax () {
		return this.idMax;
	}
	
	
	public NonTerminal getStart() {
		return this.start;
	}

	
	public Terminal getTerminal(Terminal t) {
		return this.terminals.get(t);
	}
	
	
	public Terminal getTerminal(int id) {
		return this.terminals.get(id);
	}
	
	
	public Terminal getTerminal(String symbol) {
		return this.terminals.get(symbol);
	}

	
	public int setLenghtProduction(Production p, int l) throws GrammarExceptionImpl {
		Integer i;
		if((p==null)||(p.getId()<0)||(p.getSymbol()==null)){
			throw new GrammarExceptionImpl("The Production is NULL");
		}
		
		/* Maps the ID of the Production agains his lenght. */
		i = this.lengthsP.put(p.getId(), l);	
		if(i==null){
			return -3;
		}else{
			return i.intValue();
		}
	}

	
	public int setLenghtNonTerminal(NonTerminal nt, int l) throws GrammarExceptionImpl {
		Integer i;
		if((nt==null)||(nt.getId()<0)||(nt.getSymbol()==null)){
			throw new GrammarExceptionImpl("The NonTerminal is NULL ");
		}
		
		/* Maps the ID of the NonTerminal agains his lenght. */
		i = this.lengthsNt.put(nt.getId(), l);	
		if(i==null){
			return -3;
		}else{
			return i.intValue();
		}
	}
	
	public NonTerminal setNonTerminal(NonTerminal nt) throws GrammarExceptionImpl {
		return this.nonterminals.add(nt);
		
	}

	
	public void setProduction(Production p) throws GrammarExceptionImpl {
		
		NonTerminal nt, nt_aux, nt_aux2;
		Terminal t, t_aux2;
		Production p_aux;
		Collection<NonTerminal> nts;
		Collection<Terminal> ts;
		
		if((p==null)||(p.getId()<0)||(p.getSymbol()==null)){
			throw new GrammarExceptionImpl("The Production is NULL.");
		}
		
		/* Checks if the Terminals and NonTerminals of the Productions are associated to the Grammar. */
		nt = p.getLeft(this);
		nt_aux = this.getNonTerminal(nt);
		if(nt_aux == null){
			/* Associates the NonTerminal to the Grammar. */
			
			nt_aux2 = this.setNonTerminal(nt);
			
			if(nt_aux2 != null){
				throw new GrammarExceptionImpl("The old NonTerminal that collides is: " + nt_aux2.getSymbol());
			}
		}
		
		nts = p.getNonTerminalsRight(this);
		if(nts!=null){
			for(NonTerminal nt2: nts){
				nt_aux = this.getNonTerminal(nt2);
				if(nt_aux == null){
					/* Associates the NonTerminal to the Grammar. */
					nt_aux2=this.setNonTerminal(nt2);
					if(nt_aux2 != null){
						throw new GrammarExceptionImpl("The old NonTerminal that collides is: " + nt_aux2.getSymbol());
					}
				}
			}
		}
		
		ts = p.getTerminalsRight(this);
		if(ts != null){
			for(Terminal t2: ts){
				t = this.getTerminal(t2);
				if(t == null){
					/* Associates the Terminal to the Grammar. */
					t_aux2= this.setTerminal(t2);
					if(t_aux2 != null){
						throw new GrammarExceptionImpl("The old Terminal that collides is: " + t_aux2.getSymbol());
					}
				}
			}
		}
		
		/* Associates the Production to the Grammar. */
		p_aux=this.productions.add(p);
		if(p_aux!=null){
			throw new GrammarExceptionImpl("The old Production that collides is: " + p_aux.getSymbol());
		}
		
		/* Associates the Production with the NonTerminals and Terminals of the Grammar. */
		this.nonterminals.setRelations(p, this);
		this.terminals.setRelations(p, this);
	}
	
	
	@SuppressWarnings("unchecked")
	public void setProduction(Production p, Collection<Element> l, Element leftSide) throws GrammarExceptionImpl {
		NonTerminal nt, nt_aux, nt_aux2;
		Terminal t, t_aux2;
		Production p_aux;
		Collection<NonTerminal> nts;
		Collection<Terminal> ts;
		Iterator<Element> itEl;
		Element elem;
		Class cNt;
		
		if((p==null)||(p.getId()<0)||(p.getSymbol()==null) && (l!=null) && (!l.isEmpty())){
			throw new GrammarExceptionImpl("The Production is NULL.");
		}
		
		/* Checks if the Terminals and NonTerminals of the Productions are associated to the Grammar. */
		nt_aux = new NonTerminalImpl(0, "");
		cNt = nt_aux.getClass();
		if(!cNt.isInstance(leftSide)){
			throw new GrammarExceptionImpl("At the Production left side must be a NonTerminal.");
		}
		
		nt = (NonTerminal)leftSide;
		nt_aux = this.getNonTerminal(nt);
		if(nt_aux == null){
			/* Associates the NonTerminal to the Grammar. */
			
			nt_aux2 = this.setNonTerminal(nt);
			
			if(nt_aux2 != null){
				throw new GrammarExceptionImpl("The old NonTerminal that collides is: " + nt_aux2.getSymbol());
			}
		}
		
		itEl = l.iterator();
		nts = new LinkedList<NonTerminal>();
		ts = new LinkedList<Terminal>();
		while(itEl.hasNext()){
			elem = itEl.next();
			if(cNt.isInstance(elem)){
				nts.add((NonTerminal)elem);
			}else{
					ts.add((Terminal)elem);
			}
		}
		
		if(nts!=null){
			for(NonTerminal nt2: nts){
				nt_aux = this.getNonTerminal(nt2);
				if(nt_aux == null){
					/* Associates the NonTerminal to the Grammar. */
					nt_aux2=this.setNonTerminal(nt2);
					if(nt_aux2 != null){
						throw new GrammarExceptionImpl("The old NonTerminal that collides is: " + nt_aux2.getSymbol());
					}
				}
			}
		}
		
		if(ts != null){
			for(Terminal t2: ts){
				t = this.getTerminal(t2);
				if(t == null){
					/* Associates the Terminal to the Grammar. */
					t_aux2= this.setTerminal(t2);
					if(t_aux2 != null){
						throw new GrammarExceptionImpl("The old Terminal that collides is: " + t_aux2.getSymbol());
					}
				}
			}
		}
		
		/* Associates the Production to the Grammar. */
		p_aux=this.productions.add(p);
		if(p_aux!=null){
			throw new GrammarExceptionImpl("The old Production that collides is: " + p_aux.getSymbol());
		}
		
		/* Associates the Production with the NonTerminals and Terminals of the Grammar. */
		
		this.nonterminals.setRelations(p, this);
		
		this.terminals.setRelations(p, this);
		
	}

	
	public Production findProduction(Production prod, int pos, NonTerminal nt) throws GrammarExceptionImpl{
		
		Production result = null;
		boolean find = false;
		NonTerminal nt2;
		Collection<Production> prods;
		Collection<Element> rights;
		Collection<Element> rights2;
		Iterator<Production> i;
		Iterator<Element> i1;
		Iterator<Element> i2;
		Production p;
		Element element;
		Element element2;
		int pos2;
		
		if((nt!=null)&&(prod!=null)&&(pos>=0)){
			
			nt2 = prod.getLeft(this);
			prods = nt2.getAllLeftsProd(this);
			
			if((prods!=null)&&(!prods.isEmpty())){
				
				i = prods.iterator();
				rights = prod.getRights(this);
				
				while((i.hasNext())&& (!find)){
					
					pos2 = 0;
					p = i.next();
					
					if((prod.getId()!=p.getId())&&(!prod.getSymbol().equals(p.getSymbol()))){
						
						rights2 = p.getRights(this);
						
						if (rights.size() == rights2.size()){
							
							i1 = rights.iterator();
							i2 = rights2.iterator();
							find = true;
							
							while(i1.hasNext()){
								
								element= i1.next(); 
								element2 = i2.next();
								
								if(pos2 != pos){
									if((element.getId()!=element2.getId())||(!element.getSymbol().equals(element2.getSymbol()))){
										find = false;
										break;
									}
								}else{
										if((element2.getId()!=nt.getId())||(!element2.getSymbol().equals(nt.getSymbol()))){
											find = false;
											break;
										}
								}
								pos2++;
							}
							
							if(find){
								return p;
							}
						}
					}
				}
			}
		}
		
		return result;
		
	}
	
	
	public boolean findProduction(Production p) {
		
		Production result = null;
		Production prod;
		boolean find = false;
		Iterator<Production> i;
		
		if(p!=null){
			
			result = this.productions.get(p);
			if (result != null){
				return true;
			}
			
			/* Search by the symbol.*/
			i = this.productions.iterator();
			
			while((i.hasNext())&& (!find)){
				
				prod = i.next();
				if ((prod.getSymbol()).equals(p.getSymbol())) {
					find = true;
				}
			}
		}
		
		return find;
	}
	
	
	public boolean findProduction(int p) {
		
		Production result = null;
		Production prod;
		boolean find = false;
		Iterator<Production> i;
		
		
		if(p >= 0){
			
			result = this.productions.get(p);
			if (result != null){
				return true;
			}
			
			i = this.productions.iterator();
			
			while((i.hasNext())&& (!find)){
				
				prod = i.next();
				if (prod.getId() == p) {
					find = true;
				}
			}
		}
		
		return find;
	}
	
	
	public boolean findProduction(String p) {
		
		Production result = null;
		Production prod;
		boolean find = false;
		Iterator<Production> i;
		
		
		if(p!=null){
			
			result = this.productions.get(p);
			if (result != null){
				return true;
			}
			
			i = this.productions.iterator();
			
			while((i.hasNext())&& (!find)){
				
				prod = i.next();
				if ((prod.getSymbol()).equals(p)) {
					find = true;
				}
			}
		}
		
		return find;
	}
	
	
	public boolean findNonTerminal(NonTerminal nt) {
		
		NonTerminal result = null;
		NonTerminal nt2;
		boolean find = false;
		Iterator<NonTerminal> i;
		
		
		if(nt!=null){
			
			result = this.nonterminals.get(nt);
			if (result != null){
				return true;
			}
			
			i = this.nonterminals.iterator();
			
			while((i.hasNext())&& (!find)){
				
				nt2 = i.next();
				if ((nt2.getSymbol()).equals(nt.getSymbol())) {
					find = true;
				}
			}
		}
		
		return find;
	}
	
	
	public boolean findNonTerminal(int nt) {
		
		NonTerminal result = null;
		NonTerminal nt2;
		boolean find = false;
		Iterator<NonTerminal> i;
		
		
		if(nt >= 0){
			
			result = this.nonterminals.get(nt);
			if (result != null){
				return true;
			}
			
			i = this.nonterminals.iterator();
			
			while((i.hasNext())&& (!find)){
				
				nt2 = i.next();
				if (nt2.getId() == nt) {
					find = true;
				}
			}
		}
		
		return find;
	}
	
	
	public boolean findNonTerminal(String nt) {
		
		NonTerminal result = null;
		NonTerminal nt2;
		boolean find = false;
		Iterator<NonTerminal> i;
		
		
		if(nt!=null){
			
			result = this.nonterminals.get(nt);
			if (result != null){
				return true;
			}
			
			i = this.nonterminals.iterator();
			
			while((i.hasNext())&& (!find)){
				
				nt2 = i.next();
				if ((nt2.getSymbol()).equals(nt)) {
					find = true;
				}
			}
		}
		
		return find;
	}

	
	public boolean findTerminal(Terminal t) {
		
		Terminal result = null;
		Terminal t2;
		boolean find = false;
		Iterator<Terminal> i;
		
		
		if(t!=null){
			
			result = this.terminals.get(t);
			if (result != null){
				return true;
			}
			
			i = this.terminals.iterator();
			
			while((i.hasNext())&& (!find)){
				
				t2 = i.next();
				if ((t2.getSymbol()).equals(t.getSymbol())) {
					find = true;
				}
			}
		}
		
		return find;
	}
	
	
	public boolean findTerminal(int t) {
		
		Terminal result = null;
		Terminal t2;
		boolean find = false;
		Iterator<Terminal> i;
		
		
		if(t >= 0){
			
			result = this.terminals.get(t);
			if (result != null){
				return true;
			}
			
			i = this.terminals.iterator();
			
			while((i.hasNext())&& (!find)){
				
				t2 = i.next();
				if (t2.getId() == t) {
					find = true;
				}
			}
		}
		
		return find;
	}
	
	
	public boolean findTerminal(String t) {
		
		Terminal result = null;
		Terminal t2;
		boolean find = false;
		Iterator<Terminal> i;
		
		
		if(t!=null){
			
			result = this.terminals.get(t);
			if (result != null){
				return true;
			}
			
			i = this.terminals.iterator();
			
			while((i.hasNext())&& (!find)){
				
				t2 = i.next();
				if ((t2.getSymbol()).equals(t)) {
					find = true;
				}
			}
		}
		
		return find;
	}
	
	
	public Terminal createTerminal(String symbol) throws GrammarExceptionImpl{
		
		Terminal t = null;
		Terminal res = null;
		boolean find;
		
		if (symbol != null) {
		
			t = new TerminalImpl((this.idMax+1), symbol);
			
			this.idMax = idMax + 1;
		
			find = findTerminal(t);
		
			if (!find){
				setTerminal(t);
				res = t;
			}
		}
		
		return res;
	}
	
	
	public NonTerminal createNonTerminal(String symbol) throws GrammarExceptionImpl{
		
		NonTerminal nt = null;
		NonTerminal res = null;
		boolean find;
		
		if (symbol != null) {
			
			nt = new NonTerminalImpl((this.idMax+1), symbol);
			
			this.idMax = idMax + 1;
		
			find = findNonTerminal(nt);
		
			if (!find){
				setNonTerminal(nt);
				res = nt;
			}
		}
		
		return res;
	}
	
	
	public Production createProduction(String symbol, NonTerminal lnt, Collection<Element> els) throws GrammarExceptionImpl{
		
		Production p = null;
		Production res = null;
		boolean find;
		Iterator<Element> i;
		Element e;
		int pos = 1;
		
		if((symbol != null)&&(lnt != null)&& (els!=null) && (!els.isEmpty())){
		
			p = new ProductionImpl(lnt, els.size(), (this.idMax+1), symbol);
			
			this.idMax = idMax + 1;
			
			find = findProduction(p);
			
			if (!find){
				
				i = els.iterator();
				
				while (i.hasNext()){
					e = i.next();
					p.addRightElement(e, pos);
					pos ++;
				}
				
				setProduction(p, els, lnt);
				
				res = p;
				
				
			}
		}
		
		return res;
	}
	
	
	public Production updateProduction(String symbol, Element e, int pos) throws GrammarExceptionImpl{
		
		Production p = null;
		Production p2 = null;
		Production res = null;
		boolean find;
		Iterator<Production> i;
		
		if ((symbol != null) && (e != null) && (pos > 0)){
			
			i = this.productions.iterator();
			
			find = false;
			while((i.hasNext())&& (!find)){
				p = i.next();
				if ((p.getSymbol()).equals(symbol)) {
					p2 = p;
					find = true;
				}
			}
			
			if(find && (p2 != null)){
				p2.addRightElement(e, pos);
				this.setProduction(p2);
				res = p2;
			}
		}
		
		return res;
	}
	
	
	@SuppressWarnings("unchecked")
	public void compareProductions(Collection<Production> R, Collection<Element> X, 
			                       Production generateCrossoverNode, int posRigth) throws GrammarExceptionImpl{
		
		Collection<Element> rigthsGenerateCrossoverNode;
		Collection<Element> rigths2;
		Iterator<Element>  i1;
		Iterator<Element>  i2;
		Element e1;
		Element e2;
		int pos_aux;
		Class cl;
		NonTerminal aux;
		LinkedList<Production> Raux;
		
		if((R!=null)&&(!R.isEmpty())&&(X!=null)&&(generateCrossoverNode!=null)&&(posRigth>0)){
			
			rigthsGenerateCrossoverNode = generateCrossoverNode.getRights(this);
			
			if((rigthsGenerateCrossoverNode==null)||(rigthsGenerateCrossoverNode.isEmpty())){
				throw new GrammarExceptionImpl("The Production " + generateCrossoverNode.getSymbol() + " associated to the crossoverNode doesn´t has rights symbols");
			}
			
			Raux = new LinkedList<Production>();
			
			for(Production prod : R){
				
				rigths2 = prod.getRights(this);
				if((rigths2==null)||(rigths2.isEmpty())){
					throw new GrammarExceptionImpl("The Production " + prod.getSymbol() + " doesn´t has rigths symbols");
				}
				
				if(rigths2.size()== rigthsGenerateCrossoverNode.size()){
					pos_aux=1;
					i1 = rigthsGenerateCrossoverNode.iterator();
					i2 = rigths2.iterator();
				
					/* e3 is the element of prod at the position posRigth. */
					Element e3 = null;
					while(i1.hasNext() && i2.hasNext()){
						e1 = i1.next();
						e2 = i2.next();
						if(pos_aux != posRigth){
							if((e1.getId()!=e2.getId())|| (!e1.getSymbol().equals(e2.getSymbol()))){
								
								/*Marks the Production in order to remove from R. */
								Raux.add(prod);
								
								/* Removes e3 from X, because prod isn´t selected. */
								if(e3!=null){
									X.remove(e3);
								}
								break;
							}
						}else{
								e3 = e2;
								X.add(e3);
						}
						
						pos_aux++;
					}
				}else{
						Raux.add(prod);
				}
			}
			
			if(!Raux.isEmpty()){
				for(Production p : Raux){
					R.remove(p);
				}
			}
			
			if(!X.isEmpty()){
				aux = new NonTerminalImpl(0,"Dummy");
				cl = aux.getClass();
				/* Removes from X the Terminal symbols. */
				for(Element e: X){
					if((!cl.isInstance(e))){
						X.remove(e);
					}
				}
			}
		}
	}
	
	
	public void setStart(NonTerminal nt) throws GrammarExceptionImpl {
		if((nt==null)||(nt.getId()<0)||(nt.getSymbol()==null)){
			throw new GrammarExceptionImpl("The Production is NULL.");
		}
		this.start = nt;
		
	}

	
	public Terminal setTerminal(Terminal t) throws GrammarExceptionImpl {
		return this.terminals.add(t);
		
	}


	public Collection<NonTerminal> getNonTerminals() {
		return this.nonterminals.values();
	}


	public Collection<Production> getProductions() {
		return this.productions.values();
	}


	public Collection<Terminal> getTerminals() {
		return this.terminals.values();
	}


	public boolean areThereNonTerminals() {
		return !(this.nonterminals.isEmpty());
	}


	public boolean areThereProductions() {
		return !(this.productions.isEmpty());
	}


	public boolean areThereTerminals() {
		return !(this.terminals.isEmpty());
	}


	public boolean hasCalculatedLenghtsProductions() {
		return this.hasCalculatedLenghtsP;
	}

	
	public boolean hasCalculatedLenghtsNonTerminals() {
		return this.hasCalculatedLenghtsNt;
	}
	
	
	public void grammarToGraph(boolean resumed) throws GrammarExceptionImpl{
		this.graph = new GraphGrammar_internalImpl(this);
		if(resumed){
			this.graph.GrammarToGraphResumed();
		}else{
			this.graph.GrammarToGraph();
		}
	}
	
	
	public void drawGrammar(){
		if(this.graph != null){
			this.graph.drawGraph();
		}
	}

}
