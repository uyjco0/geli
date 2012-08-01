package core.grammar;

/**
 * 
 *  Test the methods in the classes: Terminal, NonTerminal and Production
 * 
 * */

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


public class Test1_internalImpl {
	
	private static Grammar g;
	private static CreateGrammar cr;
	
	
	/* Initialize the necessary structures and checks the constructor method of the 
	 * NonTerminal, Terminal and Production classes, the setRelation method of the 
	 * NonTerminal and Terminal classes, and the addRightElement method of the 
	 * Production class. */
	@BeforeClass
	public static void InicioTest(){
		
		try{
			cr = new CreateGrammarImpl("c:/Archivos de programa/GeLi/Test/Grammar1-1.gr");
			cr.loadGrammar();
			g = cr.getGrammar();
			
		}catch (GrammarExceptionImpl e){
			System.out.println(e.getMessage());
		}
	}
	
	
	/* Checks the methods involved in the creation of a Terminal, NonTerminal and Production. */
	@Test
	public void TerminalNonTerminalProductionTest(){
		
		NonTerminal nt1;
		NonTerminal nt2;
		NonTerminal nt3;
		NonTerminal nt4;
		NonTerminal ntNew;
		
		Terminal t1;
		Terminal t2;
		Terminal tNew;
		
		Production p7;
		Production pAux;
		Production pNew1;
		Production pNew2;
		
		Collection<Element> ce;
		int id;
		
		try{
			
			nt1 = g.getNonTerminal("A");
			Assert.assertTrue(nt1.getSymbol().equals("A"));
			id = nt1.getId();
			nt1 = g.getNonTerminal(id);
			Assert.assertTrue(nt1.getSymbol().equals("A"));
			nt1 = g.getNonTerminal(nt1);
			Assert.assertTrue(nt1.getSymbol().equals("A"));
			
			nt2 = g.getNonTerminal("B");
			Assert.assertTrue(nt2.getSymbol().equals("B"));
			nt3 = g.getNonTerminal("C");
			Assert.assertTrue(nt3.getSymbol().equals("C"));
			nt4 = g.getNonTerminal("D");
			Assert.assertTrue(nt4.getSymbol().equals("D"));
	
			t1 = g.getTerminal("+");
			Assert.assertTrue(t1.getSymbol().equals("+"));
			id = t1.getId();
			t1 = g.getTerminal(id);
			Assert.assertTrue(t1.getSymbol().equals("+"));
			t1 = g.getTerminal(t1);
			Assert.assertTrue(t1.getSymbol().equals("+"));
			
			t2 = g.getTerminal("*");
			Assert.assertTrue(t2.getSymbol().equals("*"));
			
		
			p7 = g.getProduction("P7");
			Assert.assertTrue(p7.getSymbol().equals("P7"));
			id = p7.getId();
			p7 = g.getProduction(id);
			Assert.assertTrue(p7.getSymbol().equals("P7"));
			p7 = g.getProduction(p7);
			Assert.assertTrue(p7.getSymbol().equals("P7"));
			
		
			ce = new LinkedList<Element>();
			ce.add(nt1);
			ce.add(t1);
			ce.add(nt2);
			// A -> A + B
			pNew1 = g.createProduction("P25", nt1, ce);
			Assert.assertNotNull(pNew1);
			Assert.assertTrue(pNew1.getSymbol().equals("P25"));
			
			pAux =nt1.setRelation(pNew1, g);
			Assert.assertTrue(pAux.getSymbol().equals("P25"));
		
			ntNew = g.createNonTerminal("Z");
			Assert.assertNotNull(ntNew);
			Assert.assertTrue(ntNew.getSymbol().equals("Z"));
			
			tNew =  g.createTerminal("%");
			Assert.assertNotNull(tNew);
			Assert.assertTrue(tNew.getSymbol().equals("%"));
			
			ce = new LinkedList<Element>();
			ce.add(ntNew);
			ce.add(tNew);
			ce.add(ntNew);
			// Z -> Z % Z
			Assert.assertFalse(g.findProduction("P26"));
			pNew2 = g.createProduction("P26", ntNew, ce);
			Assert.assertNotNull(pNew2);
			Assert.assertTrue(pNew2.getSymbol().equals("P26"));
		
		
		
		}catch (GrammarExceptionImpl e){
			System.out.println(e.getMessage());
		}
	}
	
	/* Checks the Constructor of the Element class */
	@Test (timeout=100, expected=GrammarExceptionImpl.class)
	public void ConstructorTest1() throws GrammarExceptionImpl{
		@SuppressWarnings("unused")
		Production p11 = new ProductionImpl(1, -1, "P11");
	}
	
	/* Checks the Constructor of the Element class */
	@Test (timeout=100, expected=GrammarExceptionImpl.class)
	public void ConstructorTest2() throws GrammarExceptionImpl{
		@SuppressWarnings("unused")
		Production p11 = new ProductionImpl(1, 100, null);
	}
	
	
	/* Checks the method getLeft in the Production class */
	@Test (timeout=100)
	public void getLeftTest(){
		
		NonTerminal nt;
		Production p;
		
		p = g.getProduction("P26");
		nt = p.getLeft(g);
			
		Assert.assertTrue(nt.getSymbol().equals("Z"));
			
	}
	
	
	/* Checks the method getRights in the Production class */
	@Test (timeout=100)
	public void getRightsTest(){
		
		Collection<Element> ce;
		Iterator<Element> it;
		Element e;
		Production p;
		
		try{
			
			p = g.getProduction("P26");
			ce = p.getRights(g);
			
			Assert.assertTrue(ce.size()==3);
			
			it = ce.iterator();
			
			e = it.next();
			Assert.assertTrue(e.getSymbol().equals("Z"));
			
			e = it.next();
			Assert.assertTrue(e.getSymbol().equals("%"));
			
			e = it.next();
			Assert.assertTrue(e.getSymbol().equals("Z"));
			
		}catch(GrammarExceptionImpl ex){
			System.out.println(ex.getMessage());
		}
	}
	
	

	/* Checks the method getNonTerminalsRight in the Production class */
	@Test(timeout=100)
	public void getNonTerminalsRightTest(){
		
		Collection<NonTerminal> ce;
		Iterator<NonTerminal> it;
		NonTerminal nt;
		Production p;
		
		try{
			
			p = g.getProduction("P26");
			ce = p.getNonTerminalsRight(g);
			
			Assert.assertTrue(ce.size()==2);
			
			it = ce.iterator();
			
			nt = it.next();
			Assert.assertTrue(nt.getSymbol().equals("Z"));
			
			nt = it.next();
			Assert.assertTrue(nt.getSymbol().equals("Z"));
			
		}catch(GrammarExceptionImpl e){
			System.out.println(e.getMessage());
		}
		
	}
	
	
	/* Checks the method getTerminalsRight in the Production class */
	@Test(timeout=100)
	public void getTerminalsRightTest(){
		
		Collection<Terminal> ce;
		Iterator<Terminal> it;
		Terminal t;
		Production p;
		
		try{
			
			p = g.getProduction("P26");
			ce = p.getTerminalsRight(g);
			
			Assert.assertTrue(ce.size()==1);
			
			it = ce.iterator();
			
			t = it.next();
			Assert.assertTrue(t.getSymbol().equals("%"));
			
		}catch(GrammarExceptionImpl e){
			System.out.println(e.getMessage());
		}
		
	}
	
	
	/* Checks the methods getRightPosition in the Production class */
	@Test(timeout=100)
	public void getRightPositionTest(){
		
		Collection<Integer> ci;
		Production p;
		NonTerminal nt;
		
		try{
			
			p = g.getProduction("P26");
			nt = g.getNonTerminal("Z");
			
			ci = p.getRightPosition(nt);
			
			Assert.assertTrue(ci.size()==2);
			
			Assert.assertTrue(ci.contains(new Integer(1)));
			Assert.assertTrue(ci.contains(new Integer(3)));
			
			p = g.getProduction("P19");
			Assert.assertNull(p.getRightPosition(nt));
			
			nt = g.getNonTerminal("B");
			ci = p.getRightPosition(nt);
			
			Assert.assertTrue(ci.size()==1);
			Assert.assertTrue(ci.contains(new Integer(3)));
			
			}catch(GrammarExceptionImpl e){
			System.out.println(e.getMessage());
		}
	}
	

	/* Checks the method getAllLeftsProd in the NonTerminal class */
	@Test(timeout=100)
	public void getAllLeftsProdTest(){
		
		NonTerminal nt;
		Collection<Production> cp;
		Iterator<Production> it;
		Production p;
		
		nt = g.getNonTerminal("A");
		
		cp = nt.getAllLeftsProd(g);
		
		Assert.assertTrue(cp.size() == 3);
		
		it = cp.iterator();
		
		p = it.next();
		Assert.assertTrue(p.getSymbol().equals("P19") || p.getSymbol().equals("P20") || p.getSymbol().equals("P25"));
		
		p = it.next();
		Assert.assertTrue(p.getSymbol().equals("P19") || p.getSymbol().equals("P20") || p.getSymbol().equals("P25"));
		
		p = it.next();
		Assert.assertTrue(p.getSymbol().equals("P19") || p.getSymbol().equals("P20") || p.getSymbol().equals("P25"));
		
	}
	
	
	/* Checks the method getAllRightsProd in the NonTerminal and
	 * Terminal classes */
	@Test(timeout=100)
	public void getAllRightsProdTest(){
		
		NonTerminal nt;
		Collection<Production> cp;
		Iterator<Production> it;
		Production p;
		
		nt = g.getNonTerminal("A");
		
		cp = nt.getAllRightsProd(g);
		
		Assert.assertTrue(cp.size() == 3);
		
		it = cp.iterator();
		
		p = it.next();
		Assert.assertTrue(p.getSymbol().equals("P18") || p.getSymbol().equals("P19") || p.getSymbol().equals("P25"));
		
		p = it.next();
		Assert.assertTrue(p.getSymbol().equals("P18") || p.getSymbol().equals("P19") || p.getSymbol().equals("P25"));
		
		p = it.next();
		Assert.assertTrue(p.getSymbol().equals("P18") || p.getSymbol().equals("P19") || p.getSymbol().equals("P25"));
		
	}
	
	
	/* Checks the method getLeftProd in the NonTerminal class */
	@Test(timeout=100)
	public void getLeftProdTest(){
		
		NonTerminal nt;
		Production p;
		
		nt = g.getNonTerminal("A");
		p = g.getProduction("P19");
		
		p = nt.getLeftProd(p,g);
		Assert.assertTrue(p.getSymbol().equals("P19"));
		
		
	}
	
	/* Checks the methods getRightProd in the NonTerminal and Terminal classes */
	@Test(timeout=100)
	public void getRightProdTest(){
		
		NonTerminal nt;
		Terminal t;
		Production p;
		
		nt = g.getNonTerminal("A");
		t = g.getTerminal("+");
		p = g.getProduction("P19");
		
		p = nt.getRightProd(p,g);
		Assert.assertTrue(p.getSymbol().equals("P19"));
		
		p = t.getRightProd(p,g);
		Assert.assertTrue(p.getSymbol().equals("P19"));
		
		
	}
	
	/* Checks the methods cloneTerminal in the Terminal class. */
	@Test(timeout=100)
	public void cloneTerminalTest(){
		
		Terminal t;
		Terminal t2;
		Terminal t3;
		Collection<Production> c1;
		Iterator<Production> it1;
		Production p1;
		Collection<Production> c2;
		Iterator<Production> it2;
		Production p2;
		
		try{
			
			t = g.getTerminal("+");
			t2 = t.cloneTerminal();
			
			Assert.assertTrue(t.getSymbol().equals(t2.getSymbol()) && (t.getId() == t2.getId()));
			
			c1 = t.getAllRightsProd(g);
			c2 = t2.getAllRightsProd(g);
			
			it1 = c1.iterator();
			it2 = c2.iterator();
			
			System.out.println("Terminal all rights");
			while(it1.hasNext()){
				p1 = it1.next();
				p2 = it2.next();
				System.out.println();
				System.out.print(p1.getSymbol() + " " + p2.getSymbol());
				System.out.println();
			}
			System.out.println();
			
			
			t3 = t2.cloneTerminal();
			t2.clearTerminal();
			
			c2 = t3.getAllRightsProd(g);
			
			it2 = c2.iterator();
			
			System.out.println("Terminal all rights second clone");
			System.out.println(t3.getId() + " " + t3.getSymbol());

			while(it2.hasNext()){
				p2 = it2.next();
				System.out.println();
				System.out.print(p2.getSymbol());
				System.out.println();
			}
			
			
		}catch(GrammarExceptionImpl e){
			System.out.println(e.getMessage());
		}
		
	}
	
	
	/* Checks the methods cloneNonTerminal in the NonTerminal class. */
	@Test(timeout=100)
	public void cloneNonTerminalTest(){
		
		NonTerminal nt;
		NonTerminal nt2;
		NonTerminal nt3;
		Collection<Production> c1;
		Iterator<Production> it1;
		Production p1;
		Collection<Production> c2;
		Iterator<Production> it2;
		Production p2;
		
		try{
			
			nt = g.getNonTerminal("A");
			nt2 = nt.cloneNonTerminal();
			
			Assert.assertTrue(nt.getSymbol().equals(nt2.getSymbol()) && (nt.getId() == nt2.getId()));
			
			c1 = nt.getAllRightsProd(g);
			c2 = nt2.getAllRightsProd(g);
			
			it1 = c1.iterator();
			it2 = c2.iterator();
			
			System.out.println("NonTerminal all rights");
			while(it1.hasNext()){
				p1 = it1.next();
				p2 = it2.next();
				System.out.println();
				System.out.print(p1.getSymbol() + " " + p2.getSymbol());
				System.out.println();
			}
			System.out.println();
			
			c1 = nt.getAllLeftsProd(g);
			c2 = nt2.getAllLeftsProd(g);
			
			it1 = c1.iterator();
			it2 = c2.iterator();
			
			System.out.println("NonTerminal all lefts");
			while(it1.hasNext()){
				p1 = it1.next();
				p2 = it2.next();
				System.out.println();
				System.out.print(p1.getSymbol() + " " + p2.getSymbol());
				System.out.println();
			}
			System.out.println();
			
			nt3 = nt2.cloneNonTerminal();
			nt2.clearNonTerminal();
			
			c2 = nt3.getAllLeftsProd(g);
			
			it2 = c2.iterator();
			
			System.out.println("NonTerminal all lefts second clone");
			System.out.println(nt3.getId() + " " + nt3.getSymbol());

			while(it2.hasNext()){
				p2 = it2.next();
				System.out.println();
				System.out.print(p2.getSymbol());
				System.out.println();
			}
			
		}catch(GrammarExceptionImpl e){
			System.out.println(e.getMessage());
		}
		
	}
	
	
	/* Checks the methods cloneProduction in the Production class. */
	@Test(timeout=100)
	public void cloneProductionTest(){
		
		Production p;
		Production p2;
		Production p3;
		NonTerminal nt1;
		NonTerminal nt2;
		Collection<Element> c1;
		Iterator<Element> it1;
		Element e1;
		Collection<Element> c2;
		Iterator<Element> it2;
		Element e2;
		
		try{
			
			p = g.getProduction("P19");
			p2 = p.cloneProduction();
			
			Assert.assertTrue(p.getSymbol().equals(p2.getSymbol()) && (p.getId() == p2.getId()));
			
			nt1 = p.getLeft(g);
			nt2 = p2.getLeft(g);
			
			Assert.assertTrue(nt1.getSymbol().equals(nt2.getSymbol()) && (nt1.getId() == nt2.getId()));
			
			c1 = p.getRights(g);
			c2 = p2.getRights(g);
			
			it1 = c1.iterator();
			it2 = c2.iterator();
			
			while(it1.hasNext()){
				e1 = it1.next();
				e2 = it2.next();
				Assert.assertTrue(e1.getSymbol().equals(e2.getSymbol()) && (e1.getId() == e2.getId()));
			}
			
			p3 = p2.cloneProduction();
			p2.clearProduction();
			
			c2 = p3.getRights(g);
			it2 = c2.iterator();
			
			System.out.println("Production rights second clone");
			System.out.println(p3.getId() + " " + p3.getSymbol());
			
			while(it2.hasNext()){
				e2 = it2.next();
				System.out.println();
				System.out.print(e2.getSymbol());
				System.out.println();
			}
			
		}catch(GrammarExceptionImpl e){
			System.out.println(e.getMessage());
		}
		
	}
	

}
