package core.grammar;

/**
 * 
 *  Test the methods in the GraphGrammar_internal class.
 *  
 *  Non Public API
 *  
 * 
 * */

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import edu.uci.ics.jung.graph.Vertex;
import edu.uci.ics.jung.graph.impl.DirectedSparseVertex;
import edu.uci.ics.jung.graph.impl.DirectedSparseEdge;
import edu.uci.ics.jung.graph.Edge;
import edu.uci.ics.jung.graph.Graph;

import java.util.Set;
import java.util.Iterator;

public class Test4_internalImpl {
	
	private static Grammar g;
	private static CreateGrammar cr;
	private static GraphGrammar_internal gg;
	private static GraphGrammar_internal gg2;
	
	
	/* Initialize the necessary structures and check the constructor method of 
	 * the GraphGrammar_internal class. */
	@BeforeClass
	public static void InicioTest(){
		
		try{
			cr = new CreateGrammarImpl("c:/Archivos de programa/GeLi/Test/grammar.gr");
			cr.loadGrammar();
			g = cr.getGrammar();
			gg = new GraphGrammar_internalImpl(g);
			gg2 = new GraphGrammar_internalImpl(g);
		}catch(GrammarExceptionImpl e){
			System.out.println(e.getMessage());
		}
	}
	
	/* Checks that the Grammar is correctly mapped against a Graph. */
	@SuppressWarnings("unchecked")
	@Test(timeout=100)
	public void grammarToGraphTest(){
		Vertex v1;
		Vertex v2;
		NonTerminal nt;
		Grammar g1;
		Graph g2;
		String s;
		Element e;
		Set edges;
		Set vertices;
		Edge ed;
		Iterator it;
		double d;
		int in;
		try{
				gg.GrammarToGraph();
				
				v1 = gg.getVertexWithStartSymbol();
				s = gg.getLabelVertex(v1);
				Assert.assertTrue(s.equals("S"));
				
				v2 = new DirectedSparseVertex();
				e = gg.getElement(v2);
				Assert.assertNull(e);
				
				e = gg.getElement(null);
				Assert.assertNull(e);
				
				
				g1=gg.getGrammar();
				Assert.assertNotNull(g1);
				nt = g1.getStart();
				v1= gg.getVertex(nt);
				Assert.assertNotNull(v1);
				s = gg.getLabelVertex(v1);
				Assert.assertTrue(s.equals("S"));
				
				g2 = gg.getGraph();
				vertices = g2.getVertices();
				Assert.assertTrue(vertices.contains(v1));
				
				edges = v1.getOutEdges();
				it = edges.iterator();
				while(it.hasNext()){
					ed = (Edge)it.next();
					s = gg.getLabelEdge(ed);
					Assert.assertTrue(s.startsWith("Pr="));
					d = gg.getProbability(ed);
					Assert.assertTrue(d>=0);
					in = gg.getOrder(ed);
					Assert.assertTrue(in<0);
				}
				
				s = gg.getLabelEdge(null);
				Assert.assertTrue(s.equals(""));
				d = gg.getProbability(null);
				Assert.assertTrue(d<0);
				in = gg.getOrder(null);
				Assert.assertTrue(in<0);
				
				v1 = new DirectedSparseVertex();
				g2.addVertex(v1);
				v2 = new DirectedSparseVertex();
				g2.addVertex(v2);
				ed = new DirectedSparseEdge(v1,v2);
				s = gg.getLabelEdge(ed);
				Assert.assertTrue(s.equals(""));
				d = gg.getProbability(ed);
				Assert.assertTrue(d<0);
				in = gg.getOrder(ed);
				Assert.assertTrue(in<0);
				g2.removeVertex(v1);
				g2.removeVertex(v2);
				
		 }catch(GrammarExceptionImpl ex){
			  System.out.println(ex.getMessage());
		 }
		 
	}
	
	/* Checks that the Grammar is correctly mapped against a Graph. */
	@SuppressWarnings("unchecked")
	@Test(timeout=100)
	public void grammarToGraphResumedTest(){
		Vertex v1;
		Vertex v2;
		NonTerminal nt;
		Grammar g1;
		Graph g2;
		String s;
		Element e;
		Set edges;
		Set vertices;
		Edge ed;
		Iterator it;
		double d;
		int in;
		try{
				gg2.GrammarToGraphResumed();
				
				v1 = gg2.getVertexWithStartSymbol();
				s = gg2.getLabelVertex(v1);
				Assert.assertTrue(s.equals("S"));
				
				v2 = new DirectedSparseVertex();
				e = gg2.getElement(v2);
				Assert.assertNull(e);
				
				e = gg2.getElement(null);
				Assert.assertNull(e);
				
				
				g1=gg2.getGrammar();
				Assert.assertNotNull(g1);
				nt = g1.getStart();
				v1= gg2.getVertex(nt);
				Assert.assertNotNull(v1);
				s = gg2.getLabelVertex(v1);
				Assert.assertTrue(s.equals("S"));
				
				g2 = gg2.getGraph();
				vertices = g2.getVertices();
				Assert.assertTrue(vertices.contains(v1));
				
				edges = v1.getOutEdges();
				it = edges.iterator();
				while(it.hasNext()){
					ed = (Edge)it.next();
					s = gg2.getLabelEdge(ed);
					Assert.assertTrue(s.startsWith("Pr="));
					d = gg2.getProbability(ed);
					Assert.assertTrue(d>=0);
					in = gg2.getOrder(ed);
					Assert.assertTrue(in<0);
				}
				
				s = gg2.getLabelEdge(null);
				Assert.assertTrue(s.equals(""));
				d = gg2.getProbability(null);
				Assert.assertTrue(d<0);
				in = gg2.getOrder(null);
				Assert.assertTrue(in<0);
				
				v1 = new DirectedSparseVertex();
				g2.addVertex(v1);
				v2 = new DirectedSparseVertex();
				g2.addVertex(v2);
				ed = new DirectedSparseEdge(v1,v2);
				s = gg2.getLabelEdge(ed);
				Assert.assertTrue(s.equals(""));
				d = gg2.getProbability(ed);
				Assert.assertTrue(d<0);
				in = gg.getOrder(ed);
				Assert.assertTrue(in<0);
				g2.removeVertex(v1);
				g2.removeVertex(v2);
				
		}catch(GrammarExceptionImpl ex){
			  System.out.println(ex.getMessage());
		 }
		 
	}
	
	/* Checks that the Graph drawing. */
	@Test
	public void drawGraphTest(){
			
		gg.drawGraph();
		gg2.drawGraph();
		
		/* An infinite loop in order to see the JFrame: SHOULD BE STOPPED BY HAND!!! */
		while(true){
		}
		
	}

}
