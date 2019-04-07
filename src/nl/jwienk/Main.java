package nl.jwienk;


import nl.jwienk.utils.Helpers;

import java.util.ArrayList;
import java.util.LinkedList;

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

    ArrayList<LinkedList<State>> results = graph.findSolutions(new State(one, two), twentythree);

    System.out.println(String.format("%-10s %s", "METHOD", "findSolutions"));
    System.out.println(String.format("%-10s %s", "CHECKED", graph.getNrOfStatesChecked()));
    for (LinkedList<State> result : results) {
      Helpers.printResult(result, false);
    }

    results = graph.findSolutionsOptimized(new State(one, two), twentythree);

    System.out.println(String.format("%-10s %s", "METHOD", "findSolutionsOptimized"));
    System.out.println(String.format("%-10s %s", "CHECKED", graph.getNrOfStatesChecked()));
    for (LinkedList<State> result : results) {
      Helpers.printResult(result, false);
    }

  }
}
