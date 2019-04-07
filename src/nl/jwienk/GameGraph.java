package nl.jwienk;

import java.util.*;

/**
 * Class representing a graph
 */
public class GameGraph {

  private List<Node> nodesList = new ArrayList<>();
  private Node goalNode;
  private int nrOfStatesChecked;

  private Set<String> oneGoPaths;
  private LinkedList<State> oneGoStates;

  /**
   * Find all possible solutions for the current graph based on a specified start state and the node
   * which is the goal to achieve.
   *
   * @param startState the state to start from
   * @param goalNode   the goal node to reach
   * @return a list containing results, results is a sequence of states that led to the goal
   */
  public ArrayList<LinkedList<State>> findSolutions(State startState, Node goalNode) {
    nrOfStatesChecked = 0;
    Set<State> visitedStates;
    Set<String> forbiddenPaths = new HashSet<>();
    ArrayList<LinkedList<State>> results = new ArrayList<>();
    LinkedList<State> result;

    this.setGoalNode(goalNode);
    // do while we can still get a result
    do {
      visitedStates = new HashSet<>();
      result = this.dfs(startState, visitedStates, forbiddenPaths);

      // a result has been found
      // add the last state path to the forbidden paths
      // this path represents how we got to this result and will be used
      // so that we will not get this result again
      if (result.size() > 0) {
        forbiddenPaths.add(result.getLast().getPath());
        results.add(result);
      }

    } while (result.size() != 0);

    return results;
  }

  /**
   * Find all solutions in one go. Generate the path from the previous state saved in a state.
   *
   * @param startState the state to start from
   * @param goalNode   the goal node to reach
   */
  public ArrayList<LinkedList<State>> findSolutionsOptimized(State startState, Node goalNode) {
    nrOfStatesChecked = 0;
    oneGoPaths = new HashSet<>();
    oneGoStates = new LinkedList<>();
    Set<State> visitedStates = new HashSet<>();
    ArrayList<LinkedList<State>> results = new ArrayList<>();

    this.setGoalNode(goalNode);
    this.dfsOptimized(startState, visitedStates);

    for (State state : oneGoStates) {
      State endState = state;

      LinkedList<State> solutionStates = new LinkedList<>();
      // the first state does not have a previous
      // so this is the end of the solution
      while (endState != null) {
        solutionStates.addFirst(endState);
        endState = endState.getPrevious();
      }

      results.add(solutionStates);
    }

    return results;
  }

  /**
   * Depth first search the graph and backtrack if a result has been found.
   *
   * @param startState     the start state to start from
   * @param visitedStates  the states that have already been visited
   * @param forbiddenPaths paths of solutions that have already been found
   * @return a list of states that led to a result or an empty list if no result has been found
   */
  public LinkedList<State> dfs(State startState, Set<State> visitedStates, Set<String> forbiddenPaths) {
    LinkedList<State> solutionStates;
    // the visitedStates prevents that we do not get cycles
    visitedStates.add(startState);
    nrOfStatesChecked++;

    // if the current startState we check is the desired state and if the path that led to this startState
    // has not already been added as a solution
    if (isGoalState(startState) && !forbiddenPaths.contains(startState.getPath())) {
      solutionStates = new LinkedList<>();
      // add this startState to the solutionStates
      // now we can start the backtracking process
      // we return here and path that led to this point is built using recursion
      solutionStates.add(startState);
      return solutionStates;

    } else {

      // get all the possible states this first state node can go to
      // based on the current startState
      List<State> neighbourStatesNodeOne = this.getNeighbourStates(startState, true);

      for (State neighbourState : neighbourStatesNodeOne) {
        // append the startState path to this neighbourState
        // this way we know how we got to this neighbourState
        neighbourState.appendPath(startState);

        // if we have not already visited this neighbourState
        if (!visitedStates.contains(neighbourState)) {
          // depth first search this neighbourState
          solutionStates = this.dfs(neighbourState, visitedStates, forbiddenPaths);

          // if we have reached the goal in these solutionStates
          // meaning we came here from the if statement on line 62
          // we add this state to the solutionStates and return
          // this way the path gets built using recursion
          if (goalIsReached(solutionStates)) {
            solutionStates.addFirst(startState);
            return solutionStates;
          }
        }
      }

      // here we do the same but then for the second node
      List<State> neighbourStatesNodeTwo = this.getNeighbourStates(startState, false);

      for (State neighbourState : neighbourStatesNodeTwo) {
        neighbourState.appendPath(startState);

        if (!visitedStates.contains(neighbourState)) {

          solutionStates = this.dfs(neighbourState, visitedStates, forbiddenPaths);
          if (goalIsReached(solutionStates)) {
            solutionStates.addFirst(startState);
            return solutionStates;
          }
        }
      }

    }

    // we need to add this because a branch of a path might lead to a valid solution
    // else we would never go back to try this branch
    visitedStates.remove(startState);
    return new LinkedList<>(); // no solution has been found return an empty list
  }

  /**
   * Here we backtrack the solution based of the previous state saved in every state.
   *
   * @param startState    the start state to start from
   * @param visitedStates the states that have already been visited
   */
  public void dfsOptimized(State startState, Set<State> visitedStates) {
    visitedStates.add(startState);
    nrOfStatesChecked++;

    // we found a solution state
    // add this path to the forbidden paths
    // add this state to oneGoStates
    // from these one go states we can get the previous states that led to the end state
    if (isGoalState(startState) && !oneGoPaths.contains(startState.getPath())) {
      oneGoPaths.add(startState.getPath());
      oneGoStates.add(startState);
    } else {
      List<State> neighbourStatesNodeOne = this.getNeighbourStates(startState, true);

      for (State neighbourState : neighbourStatesNodeOne) {
        neighbourState.appendPath(startState);
        if (!visitedStates.contains(neighbourState)) {
          this.dfsOptimized(neighbourState, visitedStates);
        }
      }

      List<State> neighbourStatesNodeTwo = this.getNeighbourStates(startState, false);

      for (State neighbourState : neighbourStatesNodeTwo) {
        neighbourState.appendPath(startState);
        if (!visitedStates.contains(neighbourState)) {
          this.dfsOptimized(neighbourState, visitedStates);
        }
      }
    }

    // we need to add this because a branch of a path might lead to a valid solution
    // else we would never go back to try this branch
    visitedStates.remove(startState);
  }

  /**
   * Get all the possible neighbourState based on a provided state
   *
   * @param state               the state to get the neighbours for
   * @param isForFirstStateNode get them for the first node or second node
   * @return a list of possible states this state can lead to
   */
  public LinkedList<State> getNeighbourStates(State state, boolean isForFirstStateNode) {
    LinkedList<State> neighbourStates = new LinkedList<>();

    if (isForFirstStateNode) {
      // get the outgoing edges based on the current color of the second node
      List<Edge> outgoingEdges = state.getNodeOne().getEdgesForColor(state.getNodeTwo().getColor());

      // for every edge create a new state that can be achieved
      for (Edge edge : outgoingEdges) {
        // we dont allow the first and second node to have the same position
        if (edge.getToNode().getIdentifier() == state.getNodeTwo().getIdentifier()) continue;
        neighbourStates.add(new State(edge.getToNode(), state.getNodeTwo()));
      }
    } else {
      // here we do the same but for the second node
      List<Edge> outgoingEdges = state.getNodeTwo().getEdgesForColor(state.getNodeOne().getColor());

      for (Edge edge : outgoingEdges) {
        if (edge.getToNode().getIdentifier() == state.getNodeOne().getIdentifier()) continue;
        neighbourStates.add(new State(state.getNodeOne(), edge.getToNode()));
      }
    }

    return neighbourStates;
  }

  /**
   * Checks if a provided state equal the desired state.
   *
   * @param state the state to check
   * @return true if the state is the desired state false otherwise
   */
  private boolean isGoalState(State state) {
    return state.getNodeOne().getIdentifier() == this.goalNode.getIdentifier() ||
        state.getNodeTwo().getIdentifier() == this.goalNode.getIdentifier();
  }

  /**
   * Checks if a list of states contains the solution.
   *
   * @param solutionStates states to check
   * @return true if contained false otherwise
   */
  private boolean goalIsReached(List<State> solutionStates) {
    for (State solutionState : solutionStates) {
      if (isGoalState(solutionState)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Get a list of nodes currently in the graph.
   *
   * @return a list of nodes
   */
  public List<Node> getNodesList() {
    return this.nodesList;
  }

  /**
   * Add a node to this graph.
   *
   * @param node the node to add
   */
  public void addNode(Node node) {
    this.nodesList.add(node);
  }

  /**
   * Create and edge between two nodes.
   *
   * @param fromNode the node where this edge originates from
   * @param toNode   the node where this edge goes to
   * @param color    the color of the edge
   */
  public void addEdge(Node fromNode, Node toNode, Color color) {
    fromNode.addEdge(new Edge(toNode, color));
  }

  /**
   * Set the goal node of the graph.
   *
   * @param goalNode the goal node
   */
  public void setGoalNode(Node goalNode) {
    this.goalNode = goalNode;
  }

  /**
   * Get the number of states checked.
   *
   * @return the number of states checked
   */
  public int getNrOfStatesChecked() {
    return nrOfStatesChecked;
  }
}
