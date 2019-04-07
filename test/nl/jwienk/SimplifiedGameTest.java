package nl.jwienk;

import org.junit.Before;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class SimplifiedGameTest {

  @Before
  public void setUp() throws Exception {
  }

  @org.junit.Test
  public void one() {
    GameGraph gameGraph = new GameGraph();

    Node one = new Node(1, Color.PURPLE);
    Node two = new Node(2, Color.BLACK);

    Node three = new Node(3, Color.GREEN);
    Node four = new Node(4, Color.GREEN);

    Node five = new Node(5, Color.ORANGE);
    Node six = new Node(6, Color.PURPLE);

    Node seven = new Node(7, Color.BLUE);
    Node eight = new Node(8, Color.BLACK);

    gameGraph.addNode(one);
    gameGraph.addNode(two);
    gameGraph.addNode(three);
    gameGraph.addNode(four);
    gameGraph.addNode(five);
    gameGraph.addNode(six);
    gameGraph.addNode(seven);
    gameGraph.addNode(eight);

    gameGraph.addEdge(one, three, Color.BLACK);
    gameGraph.addEdge(one, four, Color.PURPLE);

    gameGraph.addEdge(two, five, Color.GREEN);
    gameGraph.addEdge(two, six, Color.PURPLE);

    gameGraph.addEdge(three, four, Color.BLACK);

    gameGraph.addEdge(four, seven, Color.ORANGE);
    gameGraph.addEdge(four, eight, Color.ORANGE);

    gameGraph.addEdge(five, seven, Color.PURPLE);

    gameGraph.addEdge(six, five, Color.GREEN);

    gameGraph.addEdge(eight, one, Color.ORANGE);

    ArrayList<LinkedList<State>> results;
    ArrayList<String> allPaths = new ArrayList<>();

    results = gameGraph.findSolutions(new State(one, two), seven);
    for (LinkedList<State> result : results) {
      allPaths.add(result.getLast().getPath());
    }
    assertEquals(4, results.size());
    results = gameGraph.findSolutionsOptimized(new State(one, two), seven);
    for (LinkedList<State> result : results) {
      allPaths.add(result.getLast().getPath());
    }
    assertEquals(4, results.size());

    for (int i = 1; i <= allPaths.size() / 2; i++) {
      assertEquals(allPaths.get(i - 1), allPaths.get((allPaths.size() / 2 - 1) + i));
    }
  }

  @org.junit.Test
  public void two() {
    GameGraph gameGraph = new GameGraph();

    Node one = new Node(1, Color.PURPLE);
    Node two = new Node(2, Color.BLACK);

    Node three = new Node(3, Color.GREEN);
    Node four = new Node(4, Color.GREEN);
    Node five = new Node(5, Color.GREEN);

    Node six = new Node(6, Color.BLUE);

    gameGraph.addNode(one);
    gameGraph.addNode(two);
    gameGraph.addNode(three);
    gameGraph.addNode(four);
    gameGraph.addNode(five);
    gameGraph.addNode(six);

    gameGraph.addEdge(one, three, Color.BLACK);
    gameGraph.addEdge(one, four, Color.BLACK);
    gameGraph.addEdge(one, five, Color.BLACK);

    gameGraph.addEdge(three, six, Color.BLACK);
    gameGraph.addEdge(four, six, Color.BLACK);
    gameGraph.addEdge(five, six, Color.BLACK);

    ArrayList<LinkedList<State>> results;

    results = gameGraph.findSolutions(new State(one, two), six);
    assertEquals(3, results.size());
    results = gameGraph.findSolutionsOptimized(new State(one, two), six);
    assertEquals(3, results.size());
  }

  @org.junit.Test
  public void three() {
    GameGraph gameGraph = new GameGraph();

    Node one = new Node(1, Color.PURPLE);
    Node two = new Node(2, Color.BLACK);

    Node three = new Node(3, Color.GREEN);
    Node four = new Node(4, Color.GREEN);
    Node five = new Node(5, Color.GREEN);

    Node six = new Node(6, Color.BLUE);

    Node seven = new Node(7, Color.ORANGE);

    gameGraph.addNode(one);
    gameGraph.addNode(two);
    gameGraph.addNode(three);
    gameGraph.addNode(four);
    gameGraph.addNode(five);
    gameGraph.addNode(six);
    gameGraph.addNode(seven);

    gameGraph.addEdge(one, three, Color.BLACK);
    gameGraph.addEdge(one, four, Color.BLACK);
    gameGraph.addEdge(one, five, Color.BLACK);

    gameGraph.addEdge(three, six, Color.BLACK);
    gameGraph.addEdge(four, six, Color.ORANGE);
    gameGraph.addEdge(five, six, Color.BLACK);

    gameGraph.addEdge(two, seven, Color.GREEN);

    ArrayList<LinkedList<State>> results;

    results = gameGraph.findSolutions(new State(one, two), six);
    assertEquals(3, results.size());
    results = gameGraph.findSolutionsOptimized(new State(one, two), six);
    assertEquals(3, results.size());

  }

}