package nl.jwienk;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class GameGraphTest {

  private GameGraph graph;

  private Node one = new Node(1, Color.PURPLE);
  private Node two = new Node(2, Color.BLACK);
  private Node three = new Node(3, Color.GREEN);
  private Node four = new Node(4, Color.GREEN);
  private Node five = new Node(5, Color.GREEN);
  private Node six = new Node(6, Color.ORANGE);
  private Node seven = new Node(7, Color.ORANGE);
  private Node eight = new Node(8, Color.PURPLE);
  private Node nine = new Node(9, Color.PURPLE);
  private Node ten = new Node(10, Color.BLACK);
  private Node eleven = new Node(11, Color.ORANGE);
  private Node twelve = new Node(12, Color.PURPLE);
  private Node thirtheen = new Node(13, Color.ORANGE);
  private Node fourteen = new Node(14, Color.GREEN);
  private Node fifteen = new Node(15, Color.ORANGE);
  private Node sixteen = new Node(16, Color.GREEN);
  private Node seventeen = new Node(17, Color.GREEN);
  private Node eighteen = new Node(18, Color.BLACK);
  private Node nineteen = new Node(19, Color.ORANGE);
  private Node twenty = new Node(20, Color.GREEN);
  private Node twentyone = new Node(21, Color.BLACK);
  private Node twentytwo = new Node(22, Color.BLACK);
  private Node twentythree = new Node(23, Color.BLUE);

  @Before
  public void setUp() throws Exception {
    graph = new GameGraph();

    one = new Node(1, Color.PURPLE);
    two = new Node(2, Color.BLACK);
    three = new Node(3, Color.GREEN);
    four = new Node(4, Color.GREEN);
    five = new Node(5, Color.GREEN);
    six = new Node(6, Color.ORANGE);
    seven = new Node(7, Color.ORANGE);
    eight = new Node(8, Color.PURPLE);
    nine = new Node(9, Color.PURPLE);
    ten = new Node(10, Color.BLACK);
    eleven = new Node(11, Color.ORANGE);
    twelve = new Node(12, Color.PURPLE);
    thirtheen = new Node(13, Color.ORANGE);
    fourteen = new Node(14, Color.GREEN);
    fifteen = new Node(15, Color.ORANGE);
    sixteen = new Node(16, Color.GREEN);
    seventeen = new Node(17, Color.GREEN);
    eighteen = new Node(18, Color.BLACK);
    nineteen = new Node(19, Color.ORANGE);
    twenty = new Node(20, Color.GREEN);
    twentyone = new Node(21, Color.BLACK);
    twentytwo = new Node(22, Color.BLACK);
    twentythree = new Node(23, Color.BLUE);

    graph.addNode(one);
    graph.addNode(two);
    graph.addNode(three);
    graph.addNode(four);
    graph.addNode(five);
    graph.addNode(six);
    graph.addNode(seven);
    graph.addNode(eight);
    graph.addNode(nine);
    graph.addNode(ten);
    graph.addNode(eleven);
    graph.addNode(twelve);
    graph.addNode(thirtheen);
    graph.addNode(fourteen);
    graph.addNode(fifteen);
    graph.addNode(sixteen);
    graph.addNode(seventeen);
    graph.addNode(eighteen);
    graph.addNode(nineteen);
    graph.addNode(twenty);
    graph.addNode(twentyone);
    graph.addNode(twentytwo);
    graph.addNode(twentythree);

    graph.addEdge(one, four, Color.PURPLE);
    graph.addEdge(one, five, Color.BLACK);

    graph.addEdge(two, six, Color.GREEN);
    graph.addEdge(two, twelve, Color.PURPLE);

    graph.addEdge(three, one, Color.ORANGE);
    graph.addEdge(three, four, Color.ORANGE);

    graph.addEdge(four, thirtheen, Color.BLACK);

    graph.addEdge(five, nine, Color.ORANGE);

    graph.addEdge(six, nine, Color.GREEN);
    graph.addEdge(six, ten, Color.PURPLE);

    graph.addEdge(seven, two, Color.GREEN);

    graph.addEdge(eight, three, Color.PURPLE);

    graph.addEdge(nine, four, Color.GREEN);
    graph.addEdge(nine, fourteen, Color.BLACK);

    graph.addEdge(ten, fifteen, Color.GREEN);

    graph.addEdge(eleven, twelve, Color.GREEN);
    graph.addEdge(eleven, ten, Color.PURPLE);

    graph.addEdge(twelve, seven, Color.GREEN);

    graph.addEdge(thirtheen, eighteen, Color.GREEN);
    graph.addEdge(thirtheen, eight, Color.GREEN);

    graph.addEdge(fourteen, twenty, Color.ORANGE);
    graph.addEdge(fourteen, twentythree, Color.GREEN);

    graph.addEdge(fifteen, twentytwo, Color.GREEN);
    graph.addEdge(fifteen, twentythree, Color.PURPLE);

    graph.addEdge(sixteen, fifteen, Color.GREEN);

    graph.addEdge(seventeen, eleven, Color.BLACK);
    graph.addEdge(seventeen, twelve, Color.PURPLE);
    graph.addEdge(seventeen, sixteen, Color.BLACK);

    graph.addEdge(eighteen, nine, Color.ORANGE);
    graph.addEdge(eighteen, twenty, Color.ORANGE);

    graph.addEdge(nineteen, eighteen, Color.GREEN);

    graph.addEdge(twenty, twentyone, Color.ORANGE);

    graph.addEdge(twentyone, twentytwo, Color.ORANGE);
    graph.addEdge(twentyone, twentythree, Color.BLACK);

    graph.addEdge(twentytwo, seventeen, Color.ORANGE);
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void findSolutions() {
    ArrayList<LinkedList<State>> results = graph.findSolutions(new State(one, two), twentythree);
    assertEquals(3, results.size());
    assertEquals(37, results.get(0).size());
    assertEquals(54, results.get(1).size());
    assertEquals(54, results.get(2).size());
    assertEquals(new State(one, two), results.get(0).getFirst());
    assertEquals(new State(one, two), results.get(1).getFirst());
    assertEquals(new State(one, two), results.get(2).getFirst());
    assertEquals(new State(nine, twentythree), results.get(0).getLast());
    assertEquals(new State(twentythree, nine), results.get(1).getLast());
    assertEquals(new State(twentythree, nine), results.get(2).getLast());
    assertEquals(524, graph.getNrOfStatesChecked());
    assertEquals(23, graph.getNodesList().size());
  }

  @Test
  public void findSolutionsOptimized() {
    ArrayList<LinkedList<State>> results = graph.findSolutionsOptimized(new State(one, two), twentythree);
    assertEquals(3, results.size());
    assertEquals(37, results.get(0).size());
    assertEquals(54, results.get(1).size());
    assertEquals(54, results.get(2).size());
    assertEquals(new State(one, two), results.get(0).getFirst());
    assertEquals(new State(one, two), results.get(1).getFirst());
    assertEquals(new State(one, two), results.get(2).getFirst());
    assertEquals(new State(nine, twentythree), results.get(0).getLast());
    assertEquals(new State(twentythree, nine), results.get(1).getLast());
    assertEquals(new State(twentythree, nine), results.get(2).getLast());
    assertEquals(229, graph.getNrOfStatesChecked());
    assertEquals(23, graph.getNodesList().size());
  }

  @Test
  public void getNeighbourStates() {
    LinkedList<State> neighbourStates = graph.getNeighbourStates(new State(one, two), true);
    assertEquals(1, neighbourStates.size());
    assertEquals(new State(five, two), neighbourStates.get(0));
  }

  @Test
  public void getNodesList() {
    assertEquals(23, graph.getNodesList().size());
    assertEquals(one, graph.getNodesList().get(0));
    assertEquals(twentythree, graph.getNodesList().get(22));
  }

  @Test
  public void addNode() {
    graph.addNode(new Node(24, Color.GREEN));
    assertEquals(24, graph.getNodesList().size());
    assertEquals(Color.GREEN, graph.getNodesList().get(23).getColor());
    assertEquals(24, graph.getNodesList().get(23).getIdentifier());
  }

  @Test
  public void addEdge() {
    graph.addEdge(one, twentythree, Color.BLACK);
    ArrayList<LinkedList<State>> results = graph.findSolutionsOptimized(new State(one, two), twentythree);
    assertEquals(7, results.size());

    assertEquals(3, graph.getNodesList().get(0).getEdges().size());
    assertEquals(2, graph.getNodesList().get(0).getEdgesForColor(Color.BLACK).size());
  }

  @Test
  public void getNrOfStatesChecked() {
    assertEquals(0, graph.getNrOfStatesChecked());
  }
}