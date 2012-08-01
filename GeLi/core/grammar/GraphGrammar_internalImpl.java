package core.grammar;

/** 
 * 
 * Class that offers a concrete implementation of the interface GraphGrammar_internal.
 * 
 * It doesn´t belong to the Public API.
 *
 * @author Jorge Couchet.
 * 
 **/


import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import edu.uci.ics.jung.graph.Edge;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.Vertex;
import edu.uci.ics.jung.graph.DirectedEdge;
import edu.uci.ics.jung.graph.impl.DirectedSparseGraph;
import edu.uci.ics.jung.graph.impl.DirectedSparseVertex;
import edu.uci.ics.jung.graph.impl.DirectedSparseEdge;
import edu.uci.ics.jung.visualization.PluggableRenderer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.Renderer;
import edu.uci.ics.jung.visualization.Layout;
import edu.uci.ics.jung.visualization.FRLayout;

import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.DualHashBidiMap;

import javax.swing.JFrame; 


class GraphGrammar_internalImpl implements GraphGrammar_internal {

	/** Hold's the Grammar that is mapped to the Graph. */
	private Grammar grammar;
	
	/** Hold's the Graph that is a mapping of the Grammar. */
	private Graph graph;
	
	/** Hold's a reference to the Vertex (node) of the Graph that contains the Start 
	 *  Symbol of the Grammar. */
	private Vertex startSymbol;
	
	/** Maps the ID of an Element with the vertex (node) of the Graph that represent 
	 *  the symbol. */
	private BidiMap mapElementVertex;
	
	/** Maps an Edge with his value: a probability. */
	private Map<DirectedEdge,Double> mapEdgeProb;
	
	/** Maps an Edge with his value: the order. */
	private Map<DirectedEdge,Integer> mapEdgeOrder;
	
	/** Maps a Vertex with his label. */
	private Map<Vertex,String> mapVertexLabel;
	
	/** Maps an Edge with his label. */
	private Map<DirectedEdge,String> mapEdgeLabel;
	
	
	
	/**
	 * 
	 * Construtor that receives the Grammar to be mapped.
	 * 
	 * @param gr
	 * 			 The Grammar to be mapped.
	 * 
	 **/
	@SuppressWarnings({ "static-access", "unchecked" })
	GraphGrammar_internalImpl(Grammar gr) throws GrammarExceptionImpl{
		
		if(gr==null){
			throw new GrammarExceptionImpl("The Grammar is NULL");
		}
		this.grammar = gr;
		this.graph = new DirectedSparseGraph();
		this.startSymbol = null;
		this.mapElementVertex = new DualHashBidiMap();
		this.mapEdgeProb = new ConcurrentHashMap<DirectedEdge,Double>();
		this.mapEdgeOrder = new ConcurrentHashMap<DirectedEdge,Integer>();
		this.mapVertexLabel = new ConcurrentHashMap<Vertex,String>();
		this.mapEdgeLabel = new ConcurrentHashMap<DirectedEdge,String>();
		
		/* Removes the constrain that doesn´t allow parallel edges, because for example
		 * with the rule P1->A+A , I'll have a paralell edge e1 = (P1,A) and e2=(P1,A). */
		Collection constraints = this.graph.getEdgeConstraints();
		constraints.remove(this.graph.NOT_PARALLEL_EDGE);
	}
	
	
	public Graph getGraph(){
		return this.graph;
	}
	
	
	public Grammar getGrammar(){
		return this.grammar;
	}
	
	
	public Vertex getVertexWithStartSymbol(){
		return this.startSymbol;
	}
	
	
	public String getLabelVertex(Vertex v){
		if((v!=null) &&(this.mapVertexLabel.containsKey(v))){
			return this.mapVertexLabel.get(v);
		}else{
				return "";
		}
	}
	
	
	public Element getElement(Vertex v){
		if((v!=null)&&(this.mapElementVertex.containsValue(v))){
			return (Element)this.mapElementVertex.getKey(v);
		}else{
				return null;
		}
	}
	
	
	public Vertex getVertex(Element e){
		if((e!=null)&&(this.mapElementVertex.containsKey(e))){
			return (Vertex)this.mapElementVertex.get(e);
		}else{
				return null;
		}
	}
	
	
	public String getLabelEdge(Edge e){
		if((e!=null) &&(this.mapEdgeLabel.containsKey(e))){
			return this.mapEdgeLabel.get(e);
		}else{
				return "";
		}
	}
	
	
	public double getProbability(Edge e){
		if((e!=null) &&(this.mapEdgeProb.containsKey(e))){
			return this.mapEdgeProb.get(e);
		}else{
				return -1;
		}
	}
	
	
	public int getOrder(Edge e){
		if((e!=null) &&(this.mapEdgeOrder.containsKey(e))){
			return this.mapEdgeOrder.get(e);
		}else{
				return -1;
		}
	}
	
	
	public void GrammarToGraph() throws GrammarExceptionImpl{
		
		Vertex v1;
		Vertex v2;
		String s;
		Double d1;
		double d2;
		Integer i;
		DirectedEdge e1;
		DirectedEdge e2;
		Collection<Production> cp;
		Collection<Terminal> ct;
		Collection<NonTerminal> cnt;
		Collection<Element> cl;
		Object o;
		Element el1;
		
		cp = this.grammar.getProductions();
		ct = this.grammar.getTerminals();
		cnt = this.grammar.getNonTerminals();
		
		try{
			if(cp!=null){
				for(Production p: cp){
					v1 = new DirectedSparseVertex();
					v2=this.graph.addVertex(v1);
					if(v2==null){
						throw new GrammarExceptionImpl("There was a problem adding the vertex to the Graph");
					}
					o=this.mapElementVertex.put(p, v2);
					if(o!=null){
						throw new GrammarExceptionImpl("There is a collision with the Productions");
					}
					s=this.mapVertexLabel.put(v2, p.getSymbol());
					if(s!=null){
						throw new GrammarExceptionImpl("There is a collision with the Vertexs");
					}
				}
			}
		
			if(ct!=null){
				for(Terminal t: ct){
					v1 = new DirectedSparseVertex();
					v2=this.graph.addVertex(v1);
					if(v2==null){
						throw new GrammarExceptionImpl("There was a problem adding the vertex to the Graph");
					}
					o=this.mapElementVertex.put(t, v2);
					if(o!=null){
						throw new GrammarExceptionImpl("There is a collision with the Terminals");
					}
					s=this.mapVertexLabel.put(v2, t.getSymbol());
					if(s!=null){
						throw new GrammarExceptionImpl("There is a collision with the Vertexs");
					}
				}
			}
		
			if(cnt!=null){
				for(NonTerminal nt: cnt){
					v1 = new DirectedSparseVertex();
					v2=this.graph.addVertex(v1);
					if(v2==null){
						throw new GrammarExceptionImpl("There was a problem adding the vertex to the Graph");
					}
					o=this.mapElementVertex.put(nt, v2);
					if(o!=null){
						throw new GrammarExceptionImpl("There is a collision with the NonTerminals");
					}
					s=this.mapVertexLabel.put(v2, nt.getSymbol());
					if(s!=null){
						throw new GrammarExceptionImpl("There is a collision with the Vertexs");
					}
				}
			}
		
			this.startSymbol = (Vertex)this.mapElementVertex.get(this.grammar.getStart());
		
			if(cp!=null){
				for(Production p: cp){
					el1 = p.getLeft(this.grammar);
					v1 = (Vertex)this.mapElementVertex.get(el1);
					v2 = (Vertex)this.mapElementVertex.get(p);
					e1 = new DirectedSparseEdge(v1, v2);
					e2=(DirectedEdge)this.graph.addEdge(e1);
					if(e2==null){
						throw new GrammarExceptionImpl("There was a problem adding the edge to the Graph");
					}
					d2= ((StochasticContextFreeGrammar)this.grammar).getProbability(p);
					Double d_aux = new Double(d2);
					String sr = d_aux.toString();
					if(sr.length()>=4){
						sr = sr.substring(0, 4);
					}else{
							sr = sr.substring(0, 3);
					}
					s=this.mapEdgeLabel.put(e2, "Pr=" + sr);
					if(s!=null){
						throw new GrammarExceptionImpl("There is a collision with the Edges");
					}
					
					d1 = this.mapEdgeProb.put(e2, d2);
					if(d1!=null){
						{
							throw new GrammarExceptionImpl("There is a collision with the Edges");
						}
					}
					
					cl = p.getRights(this.grammar);
					if(cl!=null){
						int pos=1;
						for(Element ele:cl){
							v1 = (Vertex)this.mapElementVertex.get(p);
							v2 = (Vertex)this.mapElementVertex.get(ele);
							e1 = new DirectedSparseEdge(v1, v2);
							e2=(DirectedEdge)this.graph.addEdge(e1);
							if(e2==null){
								throw new GrammarExceptionImpl("There was a problem adding the edge to the Graph");
							}
							s=this.mapEdgeLabel.put(e2, pos+"");
							if(s!=null){
								throw new GrammarExceptionImpl("There is a collision with the Edges");
							}
							i = this.mapEdgeOrder.put(e2, pos);
							if(i!=null){
								{
									throw new GrammarExceptionImpl("There is a collision with the Edges");
								}
							}
							pos++;
						}
					}else{
						throw new GrammarExceptionImpl("There is a Production without right symbols");
					}
				}
			
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
				
	}
	
	
	public void GrammarToGraphResumed() throws GrammarExceptionImpl{
		
		Vertex v1;
		Vertex v2;
		String s;
		Double d1;
		double d2;
		Integer i;
		DirectedEdge e1;
		DirectedEdge e2;
		Collection<Production> cp;
		Collection<Terminal> ct;
		Collection<NonTerminal> cnt;
		Collection<Element> cl;
		Object o;
		Element el1;
		NonTerminal nt_aux;
		String s_aux;
		
		cp = this.grammar.getProductions();
		ct = this.grammar.getTerminals();
		cnt = this.grammar.getNonTerminals();
		
		try{
			if(cp!=null){
				for(Production p: cp){
					nt_aux = p.getLeft(this.grammar);
					s_aux = nt_aux.getSymbol();
					if((!s_aux.startsWith("Tv"))&&(!s_aux.startsWith("TV"))&&(!s_aux.startsWith("Tf"))&&(!s_aux.startsWith("TF"))){
						v1 = new DirectedSparseVertex();
						v2=this.graph.addVertex(v1);
						if(v2==null){
							throw new GrammarExceptionImpl("There was a problem adding the vertex to the Graph");
						}
						o=this.mapElementVertex.put(p, v2);
						if(o!=null){
							throw new GrammarExceptionImpl("There is a collision with the Productions");
						}
						s=this.mapVertexLabel.put(v2, p.getSymbol());
						if(s!=null){
							throw new GrammarExceptionImpl("There is a collision with the Vertexs");
						}
					}
				}
			}
		
			if(ct!=null){
				for(Terminal t: ct){
					v1 = new DirectedSparseVertex();
					v2=this.graph.addVertex(v1);
					if(v2==null){
						throw new GrammarExceptionImpl("There was a problem adding the vertex to the Graph");
					}
					o=this.mapElementVertex.put(t, v2);
					if(o!=null){
						throw new GrammarExceptionImpl("There is a collision with the Terminals");
					}
					s=this.mapVertexLabel.put(v2, t.getSymbol());
					if(s!=null){
						throw new GrammarExceptionImpl("There is a collision with the Vertexs");
					}
				}
			}
		
			if(cnt!=null){
				for(NonTerminal nt: cnt){
					v1 = new DirectedSparseVertex();
					v2=this.graph.addVertex(v1);
					if(v2==null){
						throw new GrammarExceptionImpl("There was a problem adding the vertex to the Graph");
					}
					o=this.mapElementVertex.put(nt, v2);
					if(o!=null){
						throw new GrammarExceptionImpl("There is a collision with the NonTerminals");
					}
					s=this.mapVertexLabel.put(v2, nt.getSymbol());
					if(s!=null){
						throw new GrammarExceptionImpl("There is a collision with the Vertexs");
					}
				}
			}
		
			this.startSymbol = (Vertex)this.mapElementVertex.get(this.grammar.getStart());
		
			if(cp!=null){
				for(Production p: cp){
					el1 = p.getLeft(this.grammar);
					s_aux = el1.getSymbol();
					if((!s_aux.startsWith("Tv"))&&(!s_aux.startsWith("TV"))&&(!s_aux.startsWith("Tf"))&&(!s_aux.startsWith("TF"))){
						v1 = (Vertex)this.mapElementVertex.get(el1);
						v2 = (Vertex)this.mapElementVertex.get(p);
						e1 = new DirectedSparseEdge(v1, v2);
						e2=(DirectedEdge)this.graph.addEdge(e1);
						if(e2==null){
							throw new GrammarExceptionImpl("There was a problem adding the edge to the Graph");
						}
						d2= ((StochasticContextFreeGrammar)this.grammar).getProbability(p);
						Double d_aux = new Double(d2);
						String sr = d_aux.toString();
						if(sr.length()>=4){
							sr = sr.substring(0, 4);
						}else{
								sr = sr.substring(0, 3);
						}
						s=this.mapEdgeLabel.put(e2, "Pr=" + sr);
						if(s!=null){
							throw new GrammarExceptionImpl("There is a collision with the Edges");
						}
						d1 = this.mapEdgeProb.put(e2, d2);
						if(d1!=null){
							{
								throw new GrammarExceptionImpl("There is a collision with the Edges");
							}
						}
					
						cl = p.getRights(this.grammar);
						if(cl!=null){
							int pos=1;
							for(Element ele:cl){
								v1 = (Vertex)this.mapElementVertex.get(p);
								v2 = (Vertex)this.mapElementVertex.get(ele);
								e1 = new DirectedSparseEdge(v1, v2);
								e2=(DirectedEdge)this.graph.addEdge(e1);
								if(e2==null){
									throw new GrammarExceptionImpl("There was a problem adding the edge to the Graph");
								}
								s=this.mapEdgeLabel.put(e2, pos+"");
								if(s!=null){
									throw new GrammarExceptionImpl("There is a collision with the Edges");
								}
								i = this.mapEdgeOrder.put(e2, pos);
								if(i!=null){
									{
										throw new GrammarExceptionImpl("There is a collision with the Edges");
									}
								}
								pos++;
							}
						}else{
							throw new GrammarExceptionImpl("There is a Production without right symbols");
						}
					}else{
							v1 = (Vertex)this.mapElementVertex.get(el1);
							cl = p.getRights(this.grammar);
							v2 = null;
							if(cl.size()!=1){
								throw new GrammarExceptionImpl("There is a Production Tv or TF that doesn´t has exactly one symbol at the right");
							}
							for(Element ele:cl){
								v2 = (Vertex)this.mapElementVertex.get(ele);
							}
							e1 = new DirectedSparseEdge(v1, v2);
							e2=(DirectedEdge)this.graph.addEdge(e1);
							if(e2==null){
								throw new GrammarExceptionImpl("There was a problem adding the edge to the Graph");
							}
							s=this.mapEdgeLabel.put(e2, 1+"");
							if(s!=null){
								throw new GrammarExceptionImpl("There is a collision with the Edges");
							}
							i = this.mapEdgeOrder.put(e2, 1);
							if(i!=null){
								{
									throw new GrammarExceptionImpl("There is a collision with the Edges");
								}
							}
							
					}
				}
			
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
				
	}
	
	public void drawGraph(){
		Layout l;
		Renderer pr ;
		VertexStringerLabel_internalImpl vs;
		EdgeStringerLabel_internalImpl es;
		VisualizationViewer vv ;
		JFrame jf ;
		
		
		l = new FRLayout(this.graph);
		pr = new PluggableRenderer();
		vs = new VertexStringerLabel_internalImpl(this.mapVertexLabel);
		es = new EdgeStringerLabel_internalImpl(this.mapEdgeLabel);
		((PluggableRenderer)pr).setVertexStringer(vs);
		((PluggableRenderer)pr).setEdgeStringer(es);
		
		vv = new VisualizationViewer(l, pr);
		
		jf = new JFrame("Graph draw");
		jf.getContentPane().add ( vv );
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
	}
	
}
