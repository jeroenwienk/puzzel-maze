package nl.jwienk;

import java.util.*;

public class GameGraph {

  private List<Node> nodesList = new ArrayList<>();
  private Node goalNode;

  private int count;

  public ArrayList<LinkedList<State>> findSolutions(State startState) {
    Set<State> visited;
    Set<String> forbiddenPaths = new HashSet<>();
    ArrayList<LinkedList<State>> results = new ArrayList<>();

    LinkedList<State> result;
    do {
      visited = new HashSet<>();
      result = this.dfs(startState, visited, forbiddenPaths);

      if (result.size() > 0) {
        forbiddenPaths.add(result.getLast().getPath());
        results.add(result);
      }

    } while (result.size() != 0);

    return results;
  }

  public LinkedList<State> dfs(State startState, Set<State> visitedStates, Set<String> forbiddenPaths) {
    LinkedList<State> solutionStates;
    visitedStates.add(startState);

    count++;
    System.out.println("Count=" + count + ": " + startState);

    if (isGoalState(startState) && !forbiddenPaths.contains(startState.getPath())) {
      System.out.println(startState.getPath());
      solutionStates = new LinkedList<>();
      solutionStates.add(startState);
      return solutionStates;

    } else {

      List<State> neighbourStatesNodeOne = this.getNeighbourStates(startState, true);

      for (State neighbour : neighbourStatesNodeOne) {
        neighbour.appendPaths(startState);

        if (!visitedStates.contains(neighbour)) {

          solutionStates = this.dfs(neighbour, visitedStates, forbiddenPaths);
          if (goalIsReached(solutionStates)) {
            solutionStates.addFirst(startState);
            return solutionStates;
          }
        }
      }

      List<State> neighbourStatesNodeTwo = this.getNeighbourStates(startState, false);

      for (State neighbour : neighbourStatesNodeTwo) {
        neighbour.appendPaths(startState);

        if (!visitedStates.contains(neighbour)) {

          solutionStates = this.dfs(neighbour, visitedStates, forbiddenPaths);
          if (goalIsReached(solutionStates)) {
            solutionStates.addFirst(startState);
            return solutionStates;
          }
        }
      }


    }

    visitedStates.remove(startState);
    return new LinkedList<>();
  }

  public LinkedList<State> getNeighbourStates(State state, boolean forFirstStateNode) {

    LinkedList<State> neighbourStates = new LinkedList<>();

    if (forFirstStateNode) {
      List<Edge> outgoingEdges = state.getNodeOne().getEdgesForColor(state.getNodeTwo().getColor());

      for (Edge edge : outgoingEdges) {
        if (edge.getToNode().getIdentifier() == state.getNodeTwo().getIdentifier()) continue;
        neighbourStates.add(new State(edge.getToNode(), state.getNodeTwo()));
      }
    } else {
      List<Edge> outgoingEdges = state.getNodeTwo().getEdgesForColor(state.getNodeOne().getColor());

      for (Edge edge : outgoingEdges) {
        if (edge.getToNode().getIdentifier() == state.getNodeOne().getIdentifier()) continue;
        neighbourStates.add(new State(state.getNodeOne(), edge.getToNode()));
      }
    }

    return neighbourStates;
  }

  private boolean isGoalState(State state) {
    return state.getNodeOne().getIdentifier() == this.goalNode.getIdentifier() || state.getNodeTwo().getIdentifier() == this.goalNode.getIdentifier();
  }

  private boolean goalIsReached(List<State> solutionStates) {
    for (State state : solutionStates) {
      if (isGoalState(state)) {
        return true;
      }
    }
    return false;
  }

  public void addNode(Node node) {
    this.nodesList.add(node);
  }

  public void addEdge(Node from, Node to, Color color) {
    from.addEdge(new Edge(to, color));
  }

  public List<Node> getNodesList() {
    return this.nodesList;
  }

  public void setGoalNode(Node goalNode) {
    this.goalNode = goalNode;
  }

}
