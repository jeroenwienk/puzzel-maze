package nl.jwienk.utils;


import nl.jwienk.Edge;
import nl.jwienk.GameGraph;
import nl.jwienk.Node;
import nl.jwienk.State;

import java.util.LinkedList;

public final class Helpers {
  private Helpers() {
  }

  public static void printGraphVizString(GameGraph gameGraph) {
    System.out.println("digraph {");

    System.out.println();
    System.out.println("\tgraph [size = \"24, 24\", overlap=prism, overlap_scaling=2, ratio=0.6]");
    System.out.println("\tnode [shape=box, color=blue, style=filled, fontcolor=white]");

    System.out.println();
    for (Node node : gameGraph.getNodesList()) {
      System.out.println("\t" + node.getIdentifier() + " [style=filled, fillcolor=" + node.getColor() + "]");
    }
    System.out.println();

    for (Node node : gameGraph.getNodesList()) {

      for (Edge edge : node.getEdges()) {
        System.out.println("\t" + node.getIdentifier() + " -> " + edge.getToNode().getIdentifier() + " [color=" + edge.getColor() + "]");
      }

    }

    System.out.println();
    System.out.println("}");

  }

  public static void printResult(LinkedList<State> result) {
    if (result.size() != 0) {
      System.out.println(String.format("%-20s %s", "POS1", "POS2"));
      for (State state : result) {
        System.out.println(String.format("%-20s %s", state.getNodeOne().getIdentifier(), state.getNodeTwo().getIdentifier()));
      }
      System.out.println(String.format("%-20s %s\n", "SIZE", result.size()));

    } else {
      System.out.println("No more results");
    }

  }

}
