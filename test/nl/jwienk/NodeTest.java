package nl.jwienk;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NodeTest {

  Node node;
  Node toNode;
  Node toNodeTwo;

  @Before
  public void setUp() throws Exception {
    node = new Node(1, Color.GREEN);
    toNode = new Node(2, Color.PURPLE);
    toNodeTwo = new Node(3, Color.ORANGE);
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void getIdentifier() {
    assertEquals(1, node.getIdentifier());
    assertEquals(2, toNode.getIdentifier());
    assertEquals(3, toNodeTwo.getIdentifier());
  }

  @Test
  public void getColor() {
    assertEquals(Color.GREEN, node.getColor());
    assertEquals(Color.PURPLE, toNode.getColor());
    assertEquals(Color.ORANGE, toNodeTwo.getColor());
  }

  @Test
  public void addEdge() {
    node.addEdge(new Edge(toNode, Color.GREEN));
    assertEquals(1, node.getEdges().size());
    assertEquals(toNode, node.getEdges().get(0).getToNode());
  }

  @Test
  public void getEdges() {
    node.addEdge(new Edge(toNode, Color.GREEN));
    assertEquals(1, node.getEdges().size());
    node.addEdge(new Edge(toNodeTwo, Color.PURPLE));
    assertEquals(2, node.getEdges().size());
    assertEquals(toNode, node.getEdges().get(0).getToNode());
    assertEquals(toNodeTwo, node.getEdges().get(1).getToNode());
  }

  @Test
  public void getEdgesForColor() {
    node.addEdge(new Edge(toNode, Color.GREEN));
    node.addEdge(new Edge(toNodeTwo, Color.PURPLE));
    assertEquals(1, node.getEdgesForColor(Color.GREEN).size());
    assertEquals(toNode, node.getEdgesForColor(Color.GREEN).get(0).getToNode());
    assertEquals(1, node.getEdgesForColor(Color.PURPLE).size());
    assertEquals(toNodeTwo, node.getEdgesForColor(Color.PURPLE).get(0).getToNode());
    assertEquals(0, node.getEdgesForColor(Color.ORANGE).size());
    assertEquals(0, node.getEdgesForColor(Color.BLACK).size());
  }

  @Test
  public void compareTo() {
    assertEquals(-1, node.compareTo(toNode));
    assertEquals(0, toNode.compareTo(node));
    assertEquals(-1, node.compareTo(toNodeTwo));
  }
}