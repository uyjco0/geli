package core.grammar;

import java.util.Collection;





/**
 *     
 *     The inteface declares the methods that define the responsabilities of a general Grammar.
 *     
 *     
 *     At this level should be declared the methods that are responsability of the Grammar, por example,
 *     the derivation of a Production is responsability of the Grammar that knows all the productions and
 *     Symbols that define the Grammar, it isn´t a responsability of a Production for example.
 *     
 *     It belongs to the Public API.
 *     
 *     @author Jorge Couchet
 *     
 **/

public interface Grammar {
	
	
	/**
	 * 
	 * Sets the maximum ID associated with the Elements of the Grammar (NonTerminals, Terminals and Productions)
	 * 
	 * @param idMax
	 * 			The maximum ID used in the Grammar, then if a new Element is created, its ID will be idMax + 1.
	 * 
	 **/
	public void setIdMax (int idMax);
		
	
	/**
	 * 
	 *  Returns the maximum ID associated to the Grammar.
	 * 
	 **/
	public int getIdMax (); 
	
	
	/**
	 * 
	 * If the NonTerminal nt is associated to the Grammar returns the NonTerminal object that has the ID = nt.getID,
	 * otherwise returns NULL.
	 * 
	 * @param nt
	 * 			The NonTerminal that is necessary to know if belongs to the Grammar. It only need to have
	 *          a value in the ID field.
	 * 
	 **/
	public NonTerminal getNonTerminal(NonTerminal nt);
	
	
	/**
	 * 
	 * Returns the NonTerminal object that has the id = "id", otherwise returns NULL.
	 * 
	 * @param id
	 * 			The NonTerminal's id that is necessary to know if belongs to the Grammar.
	 * 
	 * 
	 **/
	public NonTerminal getNonTerminal(int id);
	
	
	/**
	 * 
	 * Returns the NonTerminal object that has the symbol = "symbol", otherwise returns NULL.
	 * 
	 * @param symbol
	 * 			The NonTerminal's symbol that is necessary to know if belongs to the Grammar.
	 * 
	 * 
	 **/
	public NonTerminal getNonTerminal(String symbol);
	
		
	/**
	 * 
	 * Returns the NonTerminals associated to the Grammar as a Collection.
	 * 
	 *
	 **/
	public Collection<NonTerminal> getNonTerminals();
	
	
	/**
	 * 
	 * Associates the NonTerminal nt to the Grammar.
	 * 
	 * The method returns NULL if the ID of nt doesn´t collide with other ID. If there is a collision, 
	 * then the new nt replace the old_nt, and the method returns the old_nt.
	 * 
	 * Throws a GrammarExceptionImpl if the NonTerminal is NULL.
	 * 
	 * 
	 * @param nt
	 * 			The new NonTerminal symbol to be associated to the Grammar.
	 * 
	 **/
	public NonTerminal setNonTerminal(NonTerminal nt) throws GrammarExceptionImpl;
		
	
	/**
	 * 
	 * If the Terminal t is associated to the Grammar returns the Terminal object that has the ID = t.getID,
	 * otherwise returns NULL.
	 * 
	 * @param t
	 * 			The Terminal that is necessary to know if belongs to the Grammar. It only need to have
	 *          a value in the ID field.
	 * 
	 **/
	public Terminal getTerminal(Terminal t);
	
	
	/**
	 * 
	 * Returns the Terminal object that has its id = "id", otherwise returns NULL.
	 * 
	 * @param id
	 * 			The Terminal's id that is necessary to know if belongs to the Grammar.
	 * 
	 * 
	 **/
	public Terminal getTerminal(int id);
	
	
	/**
	 * 
	 * Returns the Terminal object that has its symbol = "symbol", otherwise returns NULL.
	 * 
	 * @param symbol
	 * 			The Terminal's symbol that is necessary to know if belongs to the Grammar.
	 * 
	 * 
	 **/
	public Terminal getTerminal(String symbol);
	
	
	/**
	 * 
	 * Returns the Terminals associated to the Grammar as a Collection.
	 * 
	 *
	 **/
	public Collection<Terminal> getTerminals();
	
	
	/**
	 * 
	 * Associates the Terminal t to the Grammar.
	 * 
	 * The method returns NULL if the ID of t doesn´t collide with other ID. If there is a collision, 
	 * then the new t replace the old_t, and the method returns the old_t.
	 * 
	 * Throws a GrammarExceptionImpl if the Terminal is NULL.
	 * 
	 * 
	 * @param t
	 * 			The new Terminal symbol to be associated to the Grammar.
	 * 
	 **/
	public Terminal setTerminal(Terminal t) throws GrammarExceptionImpl;
		
	
	/**
	 * 
	 * If the Production p is associated to the Grammar returns the Production object that has the ID = p.getID,
	 * otherwise returns NULL.
	 * 
	 * @param p
	 * 			The production that is necessary to know if belongs to the Grammar. It only need to have
	 *          a value in the ID field.
	 * 
	 **/
	public Production getProduction(Production p);
	
	
	/**
	 * 
	 * Returns the Production object that has the id = "id", otherwise returns NULL.
	 * 
	 * @param id
	 * 			The Production's id that is necessary to know if belongs to the Grammar.
	 * 
	 * 
	 **/
	public Production getProduction(int id);
	
	
	/**
	 * 
	 * Returns the Production object that has the symbol = "symbol", otherwise returns NULL.
	 * 
	 * @param symbol
	 * 			The Production's symbol that is necessary to know if belongs to the Grammar.
	 * 
	 * 
	 **/
	public Production getProduction(String symbol);
	
	
	/**
	 * 
	 * Returns the Productions associated to the Grammar as a Collection.
	 * 
	 *
	 **/
	public Collection<Production> getProductions();
	
	
	/**
	 * 
	 * Associates the Production p with the Grammar.
	 * 
	 * The method must check first that the NonTerminal and Terminal symbols that are in the Production
	 * are associated to the Grammar, if it isn´t the case then the method must associate first these 
	 * symbols to the Grammar.
	 * 
	 * The method must call the methods setRelations of the NonTerminals and Terminals objects.
	 * 
	 * The method throws a GrammarException if the Production is NULL or there were any collision. 
	 * 
	 * 
	 * @param p
	 * 			The new production to be associated to the Grammar.
	 * 
	 **/
	public void setProduction(Production p) throws GrammarExceptionImpl;
	
	
	public void setProduction(Production p, Collection<Element> l, Element leftSide) throws GrammarExceptionImpl;
	
	
	/**
	 * 
	 * Return the Start symbol of the Grammar.
	 * 
	 *   
	 **/
	public NonTerminal getStart();
	
	
	/**
	 * 
	 * Sets Start symbol of the Grammar.
	 * 
	 * Throws a GrammarExceptionImpl if the NonTerminal is NULL.
	 * 
	 * @param nt
	 * 			The NonTerminal symbol that is the Start symbol of the Grammar.
	 *  
	 **/
	public void setStart(NonTerminal nt) throws GrammarExceptionImpl;
	
	
	/**
	 * 
	 * Calculates and stores the length of each NonTerminal of the Grammar.
	 * 
	 * It's necessary to take care of to have associated all the Productions to the Grammar before
	 * the calculation of the length of the NonTerminals (because is possible that theirs lengths 
	 * depends of the others Productions).
	 * 
	 * Only works if the method hasCalculatedLengthsNonTerminals == FALSE.
	 * 
	 * Throws a GrammarException if there the Grammar is NULL, of the Grammar doesn't has 
	 * NonTerminals symbols, or doesn't has a start symbol, or there is any problem in the
	 * calculation of the lengths of the NonTerminals.
	 * 
	 *  
	 *  
	 **/
	public void calculateLengthNonTerminals() throws GrammarExceptionImpl;
	
	
	/**
	 * 
	 * Calculates and stores the length of each Production of the Grammar.
	 * 
	 * It's necessary to take care of to have associated all the Productions to the Grammar before
	 * the calculation of the length of the Productions (because is possible that theirs lengths 
	 * depends of the others Productions).
	 * 
	 * Only works if the method hasCalculatedLengthsProductions == FALSE. 
	 * 
	 * Throws a GrammarException if there the Grammar is NULL, of the Grammar doesn't has 
	 * Productions symbols, or doesn't has a start symbol, or there is any problem with the 
	 * calculation of the lengths of  the NonTerminals. 
	 * 
	 *  
	 *  
	 **/
	public void calculateLengthProductions() throws GrammarExceptionImpl;
		
	
	/**
	 * 
	 * Calculates the length of the NonTerminal nt of the Grammar.
	 * 
	 * It's necessary to take care of to have associated all the Productions to the Grammar before
	 * call this method (because is possible that its length depends of the others Productions).
	 * 
	 * Returns -2 if the Grammar is NULL or the NonTerminal nt doesn´t belong to the Grammar.
	 * Returns -1 if the NonTerminal nt doesn´t have associated Productions (an associated 
	 * Production is a Production that have nt at the left side).
	 * Returns the lenght of the NonTerminal nt if the above conditions are´nt fullfiled. 
	 * 
	 * Throws a GrammarException if there is a problem in the calculation of the lenght, or
	 * if there is an infinite loop with the Productions that are associated to the 
	 * NonTerminal nt.
	 *
	 * 
	 * @param nt
	 * 			The NonTerminal that will be used for the length calculation.
	 *  
	 *   
	 *  
	 **/
	public int calculateLenghtNonTerminal(NonTerminal nt) throws GrammarExceptionImpl;
	
	
	/**
	 * 
	 * 
	 * Returns the Grammar's minimum depth. The length of the Grammar's axiom (S) establishes the minimum size
	 * of a derivation tree.
	 * 
	 * 
	 */
	public int calculateMinimumDepth() throws GrammarExceptionImpl;
	
	
	/**
	 * 
	 * Calculates (doesn´t store) the length of the Production p of the Grammar.
	 * 
	 * It's necessary to take care of to have associated all the Productions to the Grammar before
	 * call this method (because is possible that its length depends of the others Productions).
	 * 
	 * Returns -2 if the Grammar is NULL or the Production p doesn´t belong to the Grammar.
	 * Returns -1 if the Production p doesn´t have associated NonTerminals (an associated 
	 * NonTerminals is a NonTerminal that is at the right side of the Production).
	 * Returns the lenght of the Production p if the above conditions are´nt fullfiled.
	 * 
	 * Throws a GrammarException if the Production doesn´t has right symbols, or there is a 
	 * problem in the calculation of the lenght, or if there is an infinite loop.
	 * 
	 *
	 * 
	 * @param p
	 * 			The Production that will be used for the length calculation.
	 *  
	 *   
	 *  
	 **/
	public int calculateLenghtProduction(Production p) throws GrammarExceptionImpl;
	
	
	/**
	 * 
	 * Returns the length associated to the Production p.
	 * If the Production isn´t in the Grammar returns a negative value.
	 * 
	 * @param p
	 * 			The Production that is necessary to know his length.
	 *  
	 **/
	public int getLenghtProduction(Production p);
	
	
	/**
	 * 
	 * Returns the length associated to the NonTerminal nt.
	 * If the NonTerminal isn´t in the Grammar returns a negative value.
	 * 
	 * @param nt
	 * 			The NonTerminal that is necessary to know his length.
	 *  
	 **/
	public int getLenghtNonTerminal(NonTerminal nt);
	
	
	/**
	 * 
	 * Stores (it doesn´t make any calculation) the length of the Production p of the Grammar.
	 * 
	 * The method returns a negative if the ID of p doesn´t collide with other ID. If there is a collision, 
	 * then the new lenght replace the old_lenght, and the method returns the old_lenght of 
	 * the Production that collides.
	 * 
	 * Throws a GrammarExceptionImpl if the Production is NULL.
	 * 
	 * @param p
	 * 			The Production that will be associated with the length l.
	 * 
	 * @param l
	 * 			The actual length of the Production p.
	 * 
	 *   
	 *  
	 **/
	public int setLenghtProduction(Production p, int l) throws GrammarExceptionImpl;
	
	
	/**
	 * 
	 * Stores (it doesn´t make any calculation) the length of the NonTerminal p of the Grammar.
	 * 
	 * The method returns a negative if the ID of nt doesn´t collide with other ID. If there is a collision, 
	 * then the new lenght replace the old_lenght, and the method returns the old_lenght of 
	 * the NonTerminal that collides.
	 * 
	 * Throws a GrammarExceptionImpl if the NonTerminal is NULL.
	 * 
	 * @param nt
	 * 			The NonTerminal that will be associated with the length l.
	 * 
	 * @param l
	 * 			The actual length of the NonTerminal nt.
	 * 
	 *   
	 *  
	 **/
	public int setLenghtNonTerminal(NonTerminal nt, int l) throws GrammarExceptionImpl;
	
	
	/**
	 * 
	 * Returns a random Derivation of the Grammar starting from the Start symbol.
	 *   
	 *  
	 **/
	public Derivation getRandomDerivation();
	
	
	/**
	 * 
	 * Returns a random Derivation of the Grammar starting from the Production p.
	 * 
	 * Take care because it isn´t a complete Derivation (that is: the Derivation 
	 * doesn´t start with the Start symbol, unless the NonTerminal symbol at the left side of the 
	 * Production == Start symbol).
	 *  
	 * @param p
	 *   		The Production p that is derivated until a Derivation is obtained.
	 *  
	 **/
	public Derivation getRandomDerivation(Production p);
	
	
	/**
	 * 
	 * Returns a random Derivation of the Grammar starting from the NonTerminal nt.
	 * 
	 * Take care because it isn´t a complete Derivation (that is: the Derivation doesn´t start with 
	 * the Start symbol, unless nt == Start symbol).
	 *  
	 * @param nt
	 *   		The symbol from whom the derivation stars.
	 *  
	 **/
	public Derivation getRandomDerivation(NonTerminal nt);
	
	
	/**
	 * 
	 * Returns a random Derivation of the Grammar starting from the Start symbol and that satisfies
	 * the restriction that its length is at the most l.
	 * 
	 * l= the maximum length from the root symbol (the Start in this case) of the Derivation until one 
	 * of its Terminal symbol.
	 * 
	 * If it isn´t possible to fulfill the restriction then returns NULL.
	 * 
	 *
	 * @param l
	 *   		The number that imposes the length restriction.
	 * 
	 *     
	 **/
	public Derivation getRandomDerivationMaxLenght(int l);
	
	
	/**
	 * 
	 * Returns a random Derivation of the Grammar starting from the Production p and that satisfies
	 * the restriction that its length is at the most l.
	 * 
	 * Take care because it isn´t a complete Derivation (that is: the Derivation 
	 * doesn´t start with the Start symbol, unless the NonTerminal symbol at the left side of the 
	 * Production == Start symbol).
	 * 
	 * l= the maximum length from the root symbol (the Start in this case) of the Derivation until one 
	 * of its Terminal symbols.
	 * 
	 * If it isn´t possible to fulfill the restriction then returns NULL.
	 * 
	 *  
	 * @param p
	 *   		The Production p that is derivated until a Derivation is obtained.
	 *   
	 *
	 * @param l
	 *   		The number that imposes the length restriction.
	 *   
	 *  
	 **/
	public Derivation getRandomDerivationMaxLenght(Production p, int l);
	
	
	/**
	 * 
	 * Returns a random Derivation of the Grammar starting from the NonTerminal nt and that satisfies
	 * the restriction that its length is at the most l.
	 * 
	 * Take care because it isn´t a complete Derivation (that is: the Derivation doesn´t start with 
	 * the Start symbol, unless nt == Start symbol).
	 * 
	 * If it isn´t possible to fulfill the restriction then returns NULL.
	 *  
	 * @param nt
	 *   		The symbol from whom the Derivation starts.
	 *
	 * @param l
	 *   		The number that imposes the length restriction.
	 *  
	 *  
	 **/
	public Derivation getRandomDerivationMaxLenght(NonTerminal nt, int l);
	
	
	/**
	 * 
	 * Returns TRUE if the Grammar has Terminals associated, FALSE otherwise.
	 * 
	 * 
	 * */
	public boolean areThereTerminals();
	
	
	/**
	 * 
	 * Returns TRUE if the Grammar has NonTerminals associated, FALSE otherwise.
	 * 
	 * 
	 * */
	public boolean areThereNonTerminals();
	
	
	/**
	 * 
	 * Returns TRUE if the Grammar has Productions associated, FALSE otherwise.
	 * 
	 * 
	 * */
	public boolean areThereProductions();
	
	
	/**
	 * 
	 * Returns TRUE if the lenghts of the Productions associated to the Grammar
	 * where calculated, FALSE otherwise.
	 * 
	 * 
	 * */
	public boolean hasCalculatedLenghtsProductions();
	
	
	/**
	 * 
	 * Returns TRUE if the lenghts of the NonTerminals associated to the Grammar
	 * where calculated, FALSE otherwise.
	 * 
	 * 
	 * */
	public boolean hasCalculatedLenghtsNonTerminals();
	
	
	/**
	 * 
	 * Maps the Grammar to a Graph.
	 * 
	 * @param resumed
	 * 				   If is TRUE is mapped to a resumed Graph, if is FALSE is mapped to a 
	 * 				   full Graph.
	 * 
	 * */
	public void grammarToGraph(boolean resumed) throws GrammarExceptionImpl;
	
	
	/**
	 * 
	 * Draws the Grammar using the Graph that maps it.  It is necessary to call the method
	 * grammarToGraph before.
	 * 
	 * 
	 * */
	public void drawGrammar();
	
	
	/**
	 * 
	 * The method looks for a Production that fulfills the following properties:
	 * 
	 * a) The same NonTerminal symbol at the left side,
	 * AND
	 * b) A right side with the same lenght that the right side of prod,
	 * AND
	 * c) The same Elements at the right side, with the exception of the Element at the position pos,
	 * AND
	 * d) The Element at the position pos must be equal to the NonTerminal nt.
	 * 
	 * If the method finds a Production with the properties  of above, then it returns the Production, 
	 * otherwise returns NULL.
	 * 
	 * @param prod
	 * 				The Production that is used for the search.
	 * 
	 * @param pos
	 * 				The position of the Production prod where must be the NonTerminal nt.
	 * 
	 * @param nt
	 * 				The NonTerminal that must be in the position pos of the Production to look for.
	 * 			
	 * 
	 * 
	 * 
	 * */
	public Production findProduction(Production prod, int pos, NonTerminal nt) throws GrammarExceptionImpl;
	
	
	/**
	 * 
	 * The method looks for a Production in the Grammar that fulfills the following conditions:
	 * a) There is a Production in the Grammar that has the same ID that p,
	 * OR
	 * b) There is a Production in the Grammar that has the same Symbol that p.
	 * 
	 * @param p
	 * 			The Production that is used for the search
	 * @return
	 * 			TRUE if one of the conditions are fulfilled or FALSE if none of them are fulfilled.
	 * 
	 * @throws GrammarExceptionImpl
	 * 
	 */
	public boolean findProduction(Production p);
	
	
	/**
	 * 
	 * The method looks for a Production in the Grammar that fulfills the condition that there is a 
	 * Production in the Grammar that has its id equal to "id".
	 * 
	 * @param id
	 * 			The Production's id that is used for the search
	 * @return
	 * 			TRUE if the condition is fulfilled or FALSE if isn't fulfilled.
	 * 
	 * @throws GrammarExceptionImpl
	 * 
	 */
	public boolean findProduction(int id);
	
	
	/**
	 * 
	 * The method looks for a Production in the Grammar that fulfills the condition that there is a 
	 * Production in the Grammar that has its symbol equal to "symbol".
	 * 
	 * @param symbol
	 * 			The Production's symbol that is used for the search
	 * @return
	 * 			TRUE if the condition is fulfilled or FALSE if isn't fulfilled.
	 * 
	 * @throws GrammarExceptionImpl
	 * 
	 */
	public boolean findProduction(String symbol);
	
	
	/**
	 * 
	 * The method looks for a NonTerminal in the Grammar that fulfills the following conditions:
	 * a) There is a NonTerminal in the Grammar that has the same ID that nt,
	 * OR
	 * b) There is a NonTerminal in the Grammar that has the same Symbol that nt.
	 * 
	 * @param nt
	 * 			The NonTerminal that is used for the search
	 * @return
	 * 			TRUE if one of the conditions are fulfilled or FALSE if none of them are fulfilled.
	 * 
	 * @throws GrammarExceptionImpl
	 * 
	 */
	public boolean findNonTerminal(NonTerminal nt);
	
	
	/**
	 * 
	 * The method looks for a NonTerminal in the Grammar that fulfills the condition that there is a 
	 * NonTerminal in the Grammar that has its id equal to "id".
	 * 
	 * @param id
	 * 			The NonTerminal's id that is used for the search
	 * @return
	 * 			TRUE if the condition is fulfilled or FALSE if isn't fulfilled.
	 * 
	 * @throws GrammarExceptionImpl
	 * 
	 */
	public boolean findNonTerminal(int id);
	
	
	/**
	 * 
	 * The method looks for a NonTerminal in the Grammar that fulfills the condition that there is a 
	 * NonTerminal in the Grammar that has its symbol equal to "symbol".
	 * 
	 * @param symbol
	 * 			The NonTerminal's symbol that is used for the search
	 * @return
	 * 			TRUE if the condition is fulfilled or FALSE if isn't fulfilled.
	 * 
	 * @throws GrammarExceptionImpl
	 * 
	 */
	public boolean findNonTerminal(String symbol);
	
	
	/**
	 * 
	 * The method looks for a Terminal in the Grammar that fulfills the following conditions:
	 * a) There is a Terminal in the Grammar that has the same ID that t,
	 * OR
	 * b) There is a Terminal in the Grammar that has the same Symbol that t.
	 * 
	 * @param t
	 * 			The Terminal that is used for the search
	 * @return
	 * 			TRUE if one of the conditions are fulfilled or FALSE if none of them are fulfilled.
	 * 
	 * @throws GrammarExceptionImpl
	 * 
	 */
	public boolean findTerminal(Terminal t);
	
	
	/**
	 * 
	 * The method looks for a Terminal in the Grammar that fulfills the condition that there is a 
	 * Terminal in the Grammar that has its id equal to "id".
	 * 
	 * @param id
	 * 			The Terminal's id that is used for the search.
	 * 
	 * @return
	 * 			TRUE if the condition is fulfilled or FALSE if isn't fulfilled.
	 * 
	 * @throws GrammarExceptionImpl
	 * 
	 */
	public boolean findTerminal(int id);
	
	
	/**
	 * 
	 * The method looks for a Terminal in the Grammar that fulfills the condition that there is a 
	 * Terminal in the Grammar that has its symbol equal to "symbol".
	 * 
	 * @param symbol
	 * 			The Terminal's symbol that is used for the search.
	 * 
	 * @return
	 * 			TRUE if the condition is fulfilled or FALSE if isn't fulfilled.
	 * 
	 * @throws GrammarExceptionImpl
	 * 
	 */
	public boolean findTerminal(String symbol);
	
	
	/**
	 * 
	 * The method creates a new NonTerminal (with symbol associated) and adds it to the Grammar.
	 * 
	 * @param symbol
	 * 			The symbol associated to the NonTerminal.
	 * @return
	 * 			The new NonTerminal object created or NULL if it wasn't possible.
	 * 
	 * @throws GrammarExceptionImpl
	 * 
	 */
	public NonTerminal createNonTerminal(String symbol) throws GrammarExceptionImpl;
	
	
	/**
	 * 
	 * The method creates a new Terminal (with symbol associated) and adds it to the Grammar.
	 * 
	 * @param symbol
	 * 			The symbol associated to the Terminal.
	 * @return
	 * 			The new Terminal object created or NULL if it wasn't possible.
	 * 
	 * @throws GrammarExceptionImpl
	 * 
	 */
	public Terminal createTerminal(String symbol) throws GrammarExceptionImpl;
	
	
	/**
	 * 
	 * The method creates a new Production and adds it to the Grammar.
	 * 
	 * @param symbol
	 * 			The name of the Production.
	 * 
	 * @param lnt
	 * 			The NonTerminal symbol at the left side of the Production.
	 * 
	 * @param els
	 * 			The Terminal and NonTerminal symbols at the right side of the Producti,
	 * 
	 * @return
	 * 			The new Production object created or NULL if it wasn't possible.
	 * 
	 * @throws GrammarExceptionImpl
	 * 
	 */
	public Production createProduction(String symbol, NonTerminal lnt, Collection<Element> els) throws GrammarExceptionImpl;
	
	
	/**
	 * 
	 * The method updates a Production associated to the Grammar. It changes the symbol at the position
	 * pos of the right side of the Production by the Element e.
	 * 
	 * @param symbol
	 * 			The name of the Production in the Grammar.
	 * 
	 * @param e
	 * 			The new Element to be associated to the Production.
	 * 
	 * @param pos
	 * 			The position at the right side of the Production (the symbol that is here will be
	 *          changed by e).
	 * 
	 * @return
	 * 			The Production updated or NULL if it wasn't possible.
	 * 
	 * @throws GrammarExceptionImpl
	 * 
	 */
	public Production updateProduction(String symbol, Element e, int pos) throws GrammarExceptionImpl;
	
	
	/** 
	 * 
	 * Compares each Production in R against the generateCrossoverNode Production.
	 * For each Production in R, first it looks that its right side have the same length that the right side of 
	 * generateCrossoverNode, if fulfill this condition then checks that the symbols at any position (with the 
	 * exception of the position posRigth), are equals to the symbols at the same position in the 
	 * generateCrossoverNode Production. If these conditions aren´t fulfilled then this Production is deleted from R.
	 * 
	 * For the Productions that aren´t deleted from R, their symbols at the position posRigth are added to X.
	 * 
	 * Take care because only looks at the right side of the Productions of R, that is, doesn´t look that their lefts
	 * side agree with the left side of generateCrossoverNode. If this condition is needed, then must be checked 
	 * before.
	 * 
	 * 
	 * The method throws an GrammarExceptionImpl if generateCrossoverNode doesn't have right symbols.
	 * 
	 * @param R
	 * 			The Productions to be compared. R cannot be null or empty (in this case the method doesn´t do nothing).
	 * 
	 * @param X
	 * 			The collection that will hold the symbols at the position posRight of the Productions of R that
	 * 			fulfill the above condition. X cannot be null (in this case the method doesn´t do nothing).
	 * 
	 * @param generateCrossoverNode
	 * 			The Production against which the Productions of R are compared. It cannot be null (in this case the method doesn´t do nothing).
	 * 
	 * @param posRigth
	 * 			The position of generateCrossoverNode against which the symbols at the same position on the 
	 *          Productions of R aren´t compared. It must be greater than zero (the position starts at one).
	 * 
	 * 
	 * 
	 * */
	public void compareProductions(Collection<Production> R, Collection<Element> X, 
                                   Production generateCrossoverNode, int posRigth) throws GrammarExceptionImpl;
	
	
	/**
	 * 
	 * Returns the Element object that has the id = "id", otherwise returns NULL.
	 * 
	 * @param id
	 * 			The Element's id that is necessary to know if belongs to the Grammar.
	 * 
	 * 
	 **/
	public Element getElement(int id);
	
	
	/**
	 * 
	 * Returns the Element object that has the symbol = "symbol", otherwise returns NULL.
	 * 
	 * @param symbol
	 * 			The Element's symbol that is necessary to know if belongs to the Grammar.
	 * 
	 * 
	 **/
	public Element getElement(String symbol);
	
	
}
