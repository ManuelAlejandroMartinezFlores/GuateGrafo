/**
 * @author Manuel Alejandro Martínez Flores
 * 
 * GuateGraphTest.
 * 
 * Evalua los métodos del grafo.
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GuateGraphTest {

	@Test
	void testAdd() {
		GuateGraph g = new GuateGraph();
		g.addNode("Guatemala");
		g.addNode("Escuintla");
		g.addEdge("Guatemala", "Escuintla", 4f);
		g.addEdge("Escuintla", "Guatemala", 2f);
		g.addEdge("Guatemala", "SantaLucia", 5f);
		g.addEdge("SantaLucia", "Escuintla", -3f);
		assertEquals(g.getEdge("Guatemala", "Escuintla"), 4f);
		assertEquals(g.getEdge("Guatemala", "SantaLucia"), 5f);
		
		
	}
	
	
	@Test
	void testFloydPath() {
		GuateGraph g = new GuateGraph();
		g.addNode("Guatemala");
		g.addNode("Escuintla");
		g.addEdge("Guatemala", "Escuintla", 4f);
		g.addEdge("Escuintla", "Guatemala", 2f);
		g.addEdge("Guatemala", "SantaLucia", 5f);
		g.addEdge("SantaLucia", "Escuintla", -3f);
		
		SqMatrix P = new SqMatrix(3, true);
		P.set(3, 1, 2);
		P.set(1, 2, 3);
		P.set(2, 3, 1);
		g.Floyd();
		
		assertEquals(g.getPaths().toString(), P.toString());
	}
	
	
	@Test
	void testFloydCost() {
		GuateGraph g = new GuateGraph();
		g.addNode("a");
		g.addNode("b");
		g.addNode("c");
		g.addNode("d");
		g.addNode("e");
		g.addEdge("a", "b", 1);
		g.addEdge("b", "c", 2);
		g.addEdge("c", "e", 4);
		g.addEdge("e", "d", 5);
		g.addEdge("d", "b", 1);
		g.addEdge("c", "d", 2);
		g.addEdge("d", "c", 3);
		
		assertEquals(g.centre(), "Centro: d");
		
		
		SqMatrix c = new SqMatrix(5);
		for (int i=1; i<=5; i++) {
			c.set(i, i, 0f);
		}
		c.set(1, 2, 1);
		c.set(1, 3, 3);
		c.set(1, 4, 5);
		c.set(1, 5, 7);
		c.set(2, 3, 2);
		c.set(2, 4, 4);
		c.set(2, 5, 6);
		c.set(3, 2, 3);
		c.set(3, 4, 2);
		c.set(3, 5, 4);
		c.set(4, 2, 1);
		c.set(4, 3, 3);
		c.set(4, 5, 7);
		c.set(5, 2, 6);
		c.set(5, 3, 8);
		c.set(5, 4, 5);
		
		g.Floyd();
		
		assertEquals(g.getCost().toString(), c.toString());		
		
		
	}
	
	
	@Test
	void testDeleteEdge() {
		GuateGraph g = new GuateGraph();
		g.addNode("a");
		g.addNode("b");
		g.addNode("c");
		g.addNode("d");
		g.addNode("e");
		g.addEdge("a", "b", 1);
		g.addEdge("b", "c", 2);
		g.addEdge("c", "e", 4);
		g.addEdge("e", "d", 5);
		g.addEdge("d", "b", 1);
		g.addEdge("c", "d", 2);
		g.addEdge("d", "c", 3);
		
		g.deleteEdge("a", "b");
		assertEquals(g.shortestPath("a", "b"), "No existe camino entre a y b");
	}

}
