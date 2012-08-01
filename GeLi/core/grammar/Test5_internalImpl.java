package core.grammar;

/**
 * 
 *  Test methods in the in the JDSL library.
 *  
 *  Non Public API
 *  
 * 
 * */


import jdsl.core.api.Tree;
import jdsl.core.api.Position;
import jdsl.core.ref.NodeTree;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Ignore;

import java.util.Collection;
import java.util.LinkedList;

public class Test5_internalImpl {
	
	private static Tree t1;
	private static Tree t2;
	private static Tree t3;
	private static Tree t4;
	private static Position father;
	private static Position position;
	private static Position position2;
	private static Position pos1;
	private static Position pos2;
	private static Element e1, e2, e3, e4, e5, e6, e7, e8, e9, e10;
	private static Production p;
	
	
	/* Initialize the necessary structures and check the constructor method of 
	 * the Derivation class. */
	@BeforeClass
	public static void InicioTest(){
		
		try{
			
			t1 = new NodeTree();
			t2 = new NodeTree();
			
			t3 = new NodeTree();
		
			e1 = new NonTerminalImpl(0, "S");
			e2 = new NonTerminalImpl(0, "A");
			e3 = new NonTerminalImpl(0, "B");
			e4 = new NonTerminalImpl(0, "C");
			e5 = new NonTerminalImpl(0, "D");
			e6 = new NonTerminalImpl(0, "E");
			e7 = new NonTerminalImpl(0, "F");
			e10 = new NonTerminalImpl(0, "G");
			
			
			e8 = new NonTerminalImpl(0, "TV1");
			e9 = new TerminalImpl(0, "1");
		
			p = new ProductionImpl(1, 0, "P");
		
			father = t1.root();
			father.set("Production", null);
			father.set("Element", e1);
		
			pos1 = t1.insertFirstChild(father, null);
			pos1.set("Production", p);
			pos1.set("Element", e2);
			
			position = pos1;
		
			pos2 = t1.insertLastChild(father, null);
			pos2.set("Production", p);
			pos2.set("Element", e3);
			
			father = pos1;
			pos1 = t1.insertFirstChild(father, null);
			pos1.set("Production", p);
			pos1.set("Element", e4);
			
			father = pos2;
			position2 = t1.insertFirstChild(father, null);
			position2.set("Production", p);
			position2.set("Element", e10);
			
			
			father = t2.root();
			father.set("Production", p);
			father.set("Element", e5);
		
			pos1 = t2.insertFirstChild(father, null);
			pos1.set("Production", p);
			pos1.set("Element", e6);
		
			pos2 = t2.insertLastChild(father, null);
			pos2.set("Production", p);
			pos2.set("Element", e7);
			
			father = t3.root();
			father.set("Production", null);
			father.set("Element", e8);
		
			pos1 = t3.insertFirstChild(father, null);
			pos1.set("Production", p);
			pos1.set("Element", e9);
			
			
		}catch(GrammarExceptionImpl e){
			System.out.println(e.getMessage());
		}
		
		
	}
	
	public static void crossByLevels(Tree t){
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

	
	/* Checks the getPosition and getAbsoluteRank methods in the Tree class. */
	@Test
	@Ignore
	public void getPosition_AbsoluteRankTest(){
		
		Position pos;
		Position pos2;
		int absRank;
		
		pos=DerivationAux_InternalImpl.getPosition(t1, 3, 1);
		
		Assert.assertTrue(pos.equals(position2));
		Assert.assertTrue(t1.rankOfChild(pos)==0);
		Assert.assertTrue(t1.rankOfChild(position2)==0);
		
		pos=DerivationAux_InternalImpl.getPosition(t1, 3, 0);
		Assert.assertFalse(pos.equals(position2));
		
		pos2 = t1.root();
		pos=DerivationAux_InternalImpl.getPosition(t1, 1, 0);
		Assert.assertTrue(pos.equals(pos2));
		
		absRank = DerivationAux_InternalImpl.getAbsoluteRank(t1, pos2, 1);
		Assert.assertTrue(absRank==0);
		
		absRank = DerivationAux_InternalImpl.getAbsoluteRank(t1, position2, 3);
		Assert.assertTrue(absRank==1);
		
	}
	
	/* Checks the replaceSubtree method in the Tree class. */
	@Test
	@Ignore
	public void replaceSubtreeTest(){
		
		Position pos;
		Element el;
		int absRank;
		
		System.out.println("T1:");
		System.out.println();
		crossByLevels(t1);
		
		System.out.println();
		System.out.println();
		
		System.out.println("T2:");
		crossByLevels(t2);
		
		t1.replaceSubtree(position, t2);
		
		System.out.println();
		System.out.println();
		
		System.out.println("Nuevo T1:");
		crossByLevels(t1);
		
		pos=DerivationAux_InternalImpl.getPosition(t1, 3, 2);
		el = (Element)pos.get("Element");
		Assert.assertTrue(el.getSymbol().equals("G"));
		absRank = DerivationAux_InternalImpl.getAbsoluteRank(t1, pos, 3);
		Assert.assertTrue(absRank==2);
		Assert.assertTrue(t1.rankOfChild(pos)==0);
	}

	/* Checks the replaceSubtree method in the Tree class. */
	@Test
	public void duplicateTreeTest(){
		
		Position pos;
		
		System.out.println();
		
		crossByLevels(t3);
		
		System.out.println();
		System.out.println();
		
		try{
			
			t4 = new NodeTree();
			DerivationAux_InternalImpl.duplicateTree(t3.root(), t3, t4, null);
			
			pos = t3.root();
			
			t3 = DerivationAux_InternalImpl.deleteTree(t3, pos);
			
			System.out.println("Clear T3");
			if(t3!=null){
				crossByLevels(t3);
			}
		
			System.out.println();
			System.out.println("T4");
			crossByLevels(t4);
		}catch(GrammarExceptionImpl e){
			System.out.println(e.getMessage());
		}
		
	}

}
