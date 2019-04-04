package nl.jwienk;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class GameGraph {

  private List<Node> nodesList = new ArrayList<>();
  private Node goalNode;

  public LinkedList<State> dfs(State startState, Set<State> visitedStates) {
    LinkedList<State> solutionStates;
    visitedStates.add(startState);

    if (isGoalState(startState)) {

      solutionStates = new LinkedList<>();
      solutionStates.add(startState);
      return solutionStates;

    } else {

      List<State> neighbourStatesNodeOne = this.getNeighbourStates(startState, true);

      for (State neighbour : neighbourStatesNodeOne) {

        if (!visitedStates.contains(neighbour)) {
          solutionStates = this.dfs(neighbour, visitedStates);
          if (goalIsReached(solutionStates)) {
            solutionStates.addFirst(startState);
            return solutionStates;
          }
        }


      }

      List<State> neighbourStatesNodeTwo = this.getNeighbourStates(startState, false);

      for (State neighbour : neighbourStatesNodeTwo) {

        if (!visitedStates.contains(neighbour)) {
          solutionStates = this.dfs(neighbour, visitedStates);
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

  private boolean isGoalState(State state) {
    return state.getNodeOne().getIdentifier() == this.goalNode.getIdentifier() || state.getNodeTwo().getIdentifier() == this.goalNode.getIdentifier();
  }

  private boolean goalIsReached(List<State> solution) {
    for (State state : solution) {
      if (isGoalState(state)) {
        return true;
      }
    }
    return false;
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

  public void addNode(Node node) {
    this.nodesList.add(node);
  }

  public void addEdge(Node from, Edge edge) {
    from.addEdge(edge);
  }

  public List<Node> getNodesList() {
    return this.nodesList;
  }

  public void setGoalNode(Node goalNode) {
    this.goalNode = goalNode;
  }

}
