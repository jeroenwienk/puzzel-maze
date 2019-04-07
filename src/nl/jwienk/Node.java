package nl.jwienk;


import java.util.*;

/**
 * Class representing a Node.
 */
public class Node implements Comparable<Node> {

  private int identifier;
  private Color color;
  private Set<Edge> edges = new HashSet<>();

  /**
   * Constructor for a node.
   *
   * @param identifier the identifier for this node
   * @param color      the color of this node
   */
  public Node(int identifier, Color color) {
    this.identifier = identifier;
    this.color = color;
  }

  /**
   * Get the identifier.
   *
   * @return this nodes identifier
   */
  public int getIdentifier() {
    return this.identifier;
  }

  /**
   * Get the color.
   *
   * @return this nodes color
   */
  public Color getColor() {
    return this.color;
  }

  /**
   * Add and edge to this node.
   *
   * @param edge the edge to add
   */
  public void addEdge(Edge edge) {
    this.edges.add(edge);
  }

  /**
   * Gets all edges that originate from this node. Sorts them in ascending order.
   *
   * @return a list containing all the edges
   */
  public List<Edge> getEdges() {
    List<Edge> sortedEdges = new ArrayList<>(edges);
    Collections.sort(sortedEdges);
    return sortedEdges;
  }

  /**
   * Gets all edges that originate from this node for a specified color. Sorts them in ascending order.
   *
   * @param color the color of the edges
   * @return a list containing all the edges with the requested color
   */
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

  /**
   * Comparing 2 nodes is based on if one identifier comes before the other.
   *
   * @param otherNode the node to compare to
   * @return -1 if this identifier is smaller otherwise 0
   */
  @Override
  public int compareTo(Node otherNode) {
    return this.getIdentifier() < otherNode.getIdentifier() ? -1 : 0;
  }
}
