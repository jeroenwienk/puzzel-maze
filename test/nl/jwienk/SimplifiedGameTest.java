package nl.jwienk;

import nl.jwienk.utils.Helpers;
import org.junit.Before;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class SimplifiedGameTest {

  private GameGraph gameGraph;
  private Set<State> visited = new HashSet<>();
  private Set<State> forbiddenStates = new HashSet<>();

  @Before
  public void setUp() throws Exception {
    gameGraph = new GameGraph();

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

    gameGraph.setGoalNode(seven);

    Helpers.printGraphVizString(gameGraph);


  }

  @org.junit.Test
  public void dfs() {
    Node one = gameGraph.getNodesList().get(0);
    Node two = gameGraph.getNodesList().get(1);

    ArrayList<LinkedList<State>> results;

    results = gameGraph.findSolutions(new State(one, two));

    for (LinkedList<State> result : results) {
      Helpers.printResult(result);
    }

  }

}