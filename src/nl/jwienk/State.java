package nl.jwienk;

public class State {
  private Node nodeOne;
  private Node nodeTwo;

  public State(Node nodeOne, Node nodeTwo) {
    this.nodeOne = nodeOne;
    this.nodeTwo = nodeTwo;
  }

  public Node getNodeOne() {
    return this.nodeOne;
  }

  public Node getNodeTwo() {
    return this.nodeTwo;
  }

  @Override
  public boolean equals(Object other) {
    if (other == null) return false;
    else if (!(other instanceof State)) return false;
    return this.getNodeOne().getIdentifier() == ((State) other).getNodeOne().getIdentifier() &&
        this.getNodeTwo().getIdentifier() == ((State) other).getNodeTwo().getIdentifier();
  }

  @Override
  public int hashCode() {
    return Integer.parseInt(this.getNodeOne().getIdentifier() + "" + this.getNodeTwo().getIdentifier());
  }

  @Override
  public String toString() {
    return "State{" +
        "nodeOne=" + nodeOne.getIdentifier() +
        ", nodeTwo=" + nodeTwo.getIdentifier() +
        '}';
  }
}
