package nl.jwienk;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EdgeTest {

  Edge edge;
  Edge otherEdge;
  Node node;
  Node otherNode;


  @Before
  public void setUp() throws Exception {
    node = new Node(1, Color.GREEN);
    otherNode = new Node(2, Color.ORANGE);
    edge = new Edge(node, Color.ORANGE);
    otherEdge = new Edge(otherNode, Color.BLACK);
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void getToNode() {
    assertEquals(node, edge.getToNode());
    assertEquals(otherNode, otherEdge.getToNode());
  }

  @Test
  public void getColor() {
    assertEquals(Color.ORANGE, edge.getColor());
    assertEquals(Color.BLACK, otherEdge.getColor());
  }

  @Test
  public void compareTo() {
    assertEquals(0, edge.compareTo(edge));
    assertEquals(-1, edge.compareTo(otherEdge));
  }
}