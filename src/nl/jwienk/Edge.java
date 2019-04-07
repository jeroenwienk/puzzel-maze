package nl.jwienk;

/**
 * Class representing an edge between two nodes. The from Node keeps a list with edges
 * which originate from itself.
 */
public class Edge implements Comparable<Edge> {

  private Node toNode;
  private Color color;

  /**
   * Constructor for creating an edge.
   *
   * @param toNode the Node to where this edge goes
   * @param color  the color of the edge
   */
  public Edge(Node toNode, Color color) {
    this.toNode = toNode;
    this.color = color;
  }

  /**
   * Get the to node.
   *
   * @return the node to where this edge goes
   */
  public Node getToNode() {
    return this.toNode;
  }

  /**
   * Get the color of this edge
   *
   * @return the color of the edge
   */
  public Color getColor() {
    return this.color;
  }

  /**
   * Comparing 2 edges is based on if its toNode identifier comes before the
   * other edge toNode identifier.
   *
   * @param otherEdge the edge compare to
   * @return if this toNode identifier is smaller -1 otherwise 0
   */
  @Override
  public int compareTo(Edge otherEdge) {
    return this.toNode.getIdentifier() < otherEdge.getToNode().getIdentifier() ? -1 : 0;
  }
}
