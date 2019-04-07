package nl.jwienk.utils;


import nl.jwienk.Edge;
import nl.jwienk.GameGraph;
import nl.jwienk.Node;
import nl.jwienk.State;

import java.util.LinkedList;

public final class Helpers {
  private Helpers() {
  }

  /**
   * Print a graphviz string representing the current graph. This can then be used on
   * http://www.webgraphviz.com/ to visualise the graph.
   *
   * @param gameGraph the graph to print
   */
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
        System.out.println("\t" + node.getIdentifier() + " -> " +
            edge.getToNode().getIdentifier() + " [color=" + edge.getColor() + "]");
      }

    }

    System.out.println();
    System.out.println("}");
  }

  /**
   * Print the result of a list of solution states.
   *
   * @param result list containing the order which led to a solution.
   */
  public static void printResult(LinkedList<State> result, boolean largeOutput) {
    if (result.size() != 0) {
      if (largeOutput) {
        System.out.println(String.format("%-10s %s", "POS1", "POS2"));
        for (State state : result) {
          System.out.println(String.format("%-10s %s", state.getNodeOne().getIdentifier(),
              state.getNodeTwo().getIdentifier()));
        }
      }
      System.out.println(String.format("\n%-10s %s", "SIZE", result.size()));

      String pathString = result.getLast().getPath() + result.getLast().toString();
      String[] paths = pathString.split("]");

      StringBuilder sb = new StringBuilder();

      for (int i = 0; i < paths.length; i++) {
        if (i == 0) {
          sb.append(String.format("%-11s", "PATH"));
        }

        if (i % 10 == 0 && i != 0) {
          sb.append("\n").append(String.format("%-11s", ""));
        }

        sb.append(paths[i]).append("]");

      }

      sb.append("\n");

      System.out.println(sb.toString());

      //System.out.println(String.format("%-10s %s\n", "PATH", result.getLast().getPath() + result.getLast().toString()));
    }
  }

}
