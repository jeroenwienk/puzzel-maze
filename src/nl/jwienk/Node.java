package nl.jwienk;


import java.util.*;

public class Node implements Comparable<Node> {

  private int identifier;
  private Color color;
  private Set<Edge> edges = new HashSet<>();

  public Node(int identifier, Color color) {
    this.identifier = identifier;
    this.color = color;
  }

  public int getIdentifier() {
    return this.identifier;
  }

  public Color getColor() {
    return this.color;
  }

  public void addEdge(Edge edge) {
    this.edges.add(edge);
  }

  public List<Edge> getEdges() {
    List<Edge> sortedEdges = new ArrayList<>(edges);
    Collections.sort(sortedEdges);
    return sortedEdges;
  }

  public List<Edge> getEdgesForColor(Color color) {
    List<Edge> sortedEdges = new ArrayList<>();

    for (Edge edge : edges) {
      if (edge.getColor() == color) {
        sortedEdges.add(edge);
      }
    }

    Collections.sort(sortedEdges);
    return sortedEdges;
  }

  @Override
  public int compareTo(Node other) {
    return this.getIdentifier() < other.getIdentifier() ? -1 : 0;
  }
}
