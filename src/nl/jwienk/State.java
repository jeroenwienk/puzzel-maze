package nl.jwienk;

/**
 * Class representing a current state in the game.
 */
public class State {
  private Node nodeOne;
  private Node nodeTwo;
  private String path = "";

  private State previous = null;

  /**
   * Constructor for a state.
   *
   * @param nodeOne first node belonging to this state
   * @param nodeTwo second node belonging to this state
   */
  public State(Node nodeOne, Node nodeTwo) {
    this.nodeOne = nodeOne;
    this.nodeTwo = nodeTwo;
  }

  /**
   * Gets the previous state that led to this state.
   *
   * @return the previous state
   */
  public State getPrevious() {
    return previous;
  }

  /**
   * Get the first node
   *
   * @return the first node belonging to this state.
   */
  public Node getNodeOne() {
    return this.nodeOne;
  }

  /**
   * Get the second node
   *
   * @return the second node belonging to this state.
   */
  public Node getNodeTwo() {
    return this.nodeTwo;
  }

  /**
   * Get the path which led to this state.
   *
   * @return the path
   */
  public String getPath() {
    return path;
  }

  /**
   * Append the path of a previous state to this one.
   *
   * @param previous
   */
  public void appendPath(State previous) {
    this.previous = previous;

    this.path += previous.getPath() + "[" +
        previous.getNodeOne().getIdentifier() + "," +
        previous.getNodeTwo().getIdentifier() + "]";
  }

  /**
   * Check the equality of two states. States are equal if they share the same
   * identifier at the first node and the same identifier at the second node.
   * <p>
   * i.e. one=1,two=2 == one=1,two=2 = true
   * one=1,two=2 == one=2,two=1 = false
   *
   * @param other the other state
   * @return true if equal false otherwise
   */
  @Override
  public boolean equals(Object other) {
    if (other == null) return false;
    else if (!(other instanceof State)) return false;
    return this.getNodeOne().getIdentifier() == ((State) other).getNodeOne().getIdentifier() &&
        this.getNodeTwo().getIdentifier() == ((State) other).getNodeTwo().getIdentifier();
  }

  /**
   * Get the hashCode for this state. Hashcode is based of the identifiers of the two nodes.
   *
   * @return the hashCode as int
   */
  @Override
  public int hashCode() {
    return Integer.parseInt(this.getNodeOne().getIdentifier() + "" + this.getNodeTwo().getIdentifier());
  }

  @Override
  public String toString() {
    return String.format("[%s,%s]"
        , nodeOne.getIdentifier(), nodeTwo.getIdentifier());
  }
}
