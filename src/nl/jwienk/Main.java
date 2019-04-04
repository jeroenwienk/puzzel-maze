package nl.jwienk;


import nl.jwienk.utils.Helpers;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Main {

  public static void main(String[] args) {
    GameGraph graph = new GameGraph();

    Node one = new Node(1, Color.PURPLE);
    Node two = new Node(2, Color.BLACK);
    Node three = new Node(3, Color.GREEN);
    Node four = new Node(4, Color.GREEN);
    Node five = new Node(5, Color.GREEN);
    Node six = new Node(6, Color.ORANGE);
    Node seven = new Node(7, Color.ORANGE);
    Node eight = new Node(8, Color.PURPLE);
    Node nine = new Node(9, Color.PURPLE);
    Node ten = new Node(10, Color.BLACK);
    Node eleven = new Node(11, Color.ORANGE);
    Node twelve = new Node(12, Color.PURPLE);
    Node thirtheen = new Node(13, Color.ORANGE);
    Node fourteen = new Node(14, Color.GREEN);
    Node fifteen = new Node(15, Color.ORANGE);
    Node sixteen = new Node(16, Color.GREEN);
    Node seventeen = new Node(17, Color.GREEN);
    Node eighteen = new Node(18, Color.BLACK);
    Node nineteen = new Node(19, Color.ORANGE);
    Node twenty = new Node(20, Color.GREEN);
    Node twentyone = new Node(21, Color.BLACK);
    Node twentytwo = new Node(22, Color.BLACK);
    Node twentythree = new Node(23, Color.BLUE);

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

    graph.addEdge(one, new Edge(four, Color.PURPLE));
    graph.addEdge(one, new Edge(five, Color.BLACK));

    graph.addEdge(two, new Edge(six, Color.GREEN));
    graph.addEdge(two, new Edge(twelve, Color.PURPLE));

    graph.addEdge(three, new Edge(one, Color.ORANGE));
    graph.addEdge(three, new Edge(four, Color.ORANGE));

    graph.addEdge(four, new Edge(thirtheen, Color.BLACK));

    graph.addEdge(five, new Edge(nine, Color.ORANGE));

    graph.addEdge(six, new Edge(nine, Color.GREEN));
    graph.addEdge(six, new Edge(ten, Color.PURPLE));

    graph.addEdge(seven, new Edge(two, Color.GREEN));

    graph.addEdge(eight, new Edge(three, Color.PURPLE));

    graph.addEdge(nine, new Edge(four, Color.GREEN));
    graph.addEdge(nine, new Edge(fourteen, Color.BLACK));

    graph.addEdge(ten, new Edge(fifteen, Color.GREEN));

    graph.addEdge(eleven, new Edge(twelve, Color.GREEN));
    graph.addEdge(eleven, new Edge(ten, Color.PURPLE));

    graph.addEdge(twelve, new Edge(seven, Color.GREEN));

    graph.addEdge(thirtheen, new Edge(eighteen, Color.GREEN));
    graph.addEdge(thirtheen, new Edge(eight, Color.GREEN));

    graph.addEdge(fourteen, new Edge(twenty, Color.ORANGE));
    graph.addEdge(fourteen, new Edge(twentythree, Color.GREEN));

    graph.addEdge(fifteen, new Edge(twentytwo, Color.GREEN));
    graph.addEdge(fifteen, new Edge(twentythree, Color.PURPLE));

    graph.addEdge(sixteen, new Edge(fifteen, Color.GREEN));

    graph.addEdge(seventeen, new Edge(eleven, Color.BLACK));
    graph.addEdge(seventeen, new Edge(twelve, Color.PURPLE));
    graph.addEdge(seventeen, new Edge(sixteen, Color.BLACK));

    graph.addEdge(eighteen, new Edge(nine, Color.ORANGE));
    graph.addEdge(eighteen, new Edge(twenty, Color.ORANGE));

    graph.addEdge(nineteen, new Edge(eighteen, Color.GREEN));

    graph.addEdge(twenty, new Edge(twentyone, Color.ORANGE));

    graph.addEdge(twentyone, new Edge(twentytwo, Color.ORANGE));
    graph.addEdge(twentyone, new Edge(twentythree, Color.BLACK));

    graph.addEdge(twentytwo, new Edge(seventeen, Color.ORANGE));

    //Helpers.printGraphVizString(graph);

    graph.setGoalNode(twentythree);
    Set<State> forbiddenStates = new HashSet<>();
    Set<State> visited = new HashSet<>();

    LinkedList<State> result;

    do {
      visited.addAll(forbiddenStates);
      result = graph.dfs(new State(one, two), visited);
      Helpers.printResult(result);
      if (result.size() > 0) {
        forbiddenStates.add(result.getLast());
      }
      visited = new HashSet<>();
    } while (result.size() > 0);

  }
}
