package nl.jwienk;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StateTest {

  Node nodeOne;
  Node nodeTwo;
  Node nodeThree;
  Node nodeFour;
  State stateOne;
  State stateTwo;
  State stateThree;

  @Before
  public void setUp() throws Exception {
    nodeOne = new Node(1, Color.BLACK);
    nodeTwo = new Node(2, Color.ORANGE);
    nodeThree = new Node(3, Color.PURPLE);
    nodeFour = new Node(4, Color.GREEN);
    stateOne = new State(nodeOne, nodeTwo);
    stateTwo = new State(nodeOne, nodeThree);
    stateThree = new State(nodeTwo, nodeThree);
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void getNodeOne() {
    assertEquals(nodeOne, stateOne.getNodeOne());
  }

  @Test
  public void getNodeTwo() {
    assertEquals(nodeTwo, stateOne.getNodeTwo());
  }

  @Test
  public void getPath() {
    assertEquals("", stateOne.getPath());
    stateTwo.appendPath(stateOne);
    assertEquals("", stateOne.getPath());
    assertEquals("[1,2]", stateTwo.getPath());
    stateThree.appendPath(stateTwo);
    assertEquals("[1,2][1,3]", stateThree.getPath());
  }

  @Test
  public void appendPath() {
    stateTwo.appendPath(stateOne);
    assertEquals("[1,2]", stateTwo.getPath());
    assertEquals(stateOne, stateTwo.getPrevious());
  }

  @Test
  public void equals() {
    State localState = new State(nodeOne, nodeTwo);
    assertTrue(stateOne.equals(localState));

    assertFalse(stateOne.equals(stateTwo));
    assertFalse(stateTwo.equals(stateOne));
    assertFalse(stateOne.equals(stateThree));
    assertFalse(stateThree.equals(stateOne));
  }

}