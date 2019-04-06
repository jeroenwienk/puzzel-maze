package nl.jwienk;

public class State {
  private Node nodeOne;
  private Node nodeTwo;
  private int parentHashCode = 0;

  private String path = "";

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

  public void setParentHashCode(int hashCode) {
    this.parentHashCode = hashCode;
  }

  public int getParentHashCode() {
    return parentHashCode;
  }

  public String getPath() {
    return path;
  }

  public void appendPaths(State s) {
    this.path += s.getPath() + s.getNodeOne().getIdentifier()+ " " + s.getNodeTwo().getIdentifier() + ",";
  }

  @Override
  public boolean equals(Object other) {
    if (other == null) return false;
    else if (!(other instanceof State)) return false;
    return this.getNodeOne().getIdentifier() == ((State) other).getNodeOne().getIdentifier() &&
        this.getNodeTwo().getIdentifier() == ((State) other).getNodeTwo().getIdentifier()
        //&& this.getPath().equals(((State) other).getPath())
        ;
  }

  @Override
  public int hashCode() {
    return Integer.parseInt(this.getNodeOne().getIdentifier() + "" + this.getNodeTwo().getIdentifier());
        //+ path.hashCode();
  }

  @Override
  public String toString() {
    return String.format("one=%-4s two=%s "
        , nodeOne.getIdentifier(), nodeTwo.getIdentifier());
  }
}
