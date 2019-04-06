package nl.jwienk;

public class Edge implements Comparable<Edge> {

  private Node toNode;
  private Color color;

  public Edge(Node toNode, Color color) {
    this.toNode = toNode;
    this.color = color;
  }

  public Node getToNode() {
    return this.toNode;
  }

  public Color getColor() {
    return this.color;
  }

  @Override
  public int compareTo(Edge edge) {
    return this.toNode.getIdentifier() < edge.getToNode().getIdentifier() ? -1 : 0;
  }
}
