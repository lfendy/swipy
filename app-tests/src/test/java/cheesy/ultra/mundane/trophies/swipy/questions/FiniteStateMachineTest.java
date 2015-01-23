package cheesy.ultra.mundane.trophies.swipy.questions;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import cheesy.ultra.mundane.trophies.swipy.RobolectricGradleTestRunner;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricGradleTestRunner.class)
public class FiniteStateMachineTest{

   /*
   given
   [[nodeid, nodeText, nodeType]]
   [[nodeid, nodeid, responsetype]]

   o
   [[node1, Did u just fart?, q]]
   [[node1, node2, y]
    [node1, node3, n]
   ]

   testingNodeReturnFromResponse
   when response y on node1 -- will give node2 back
   when response n on node1 -- will give node3 back

   testing node Text on node
   when node1, then should return "Did u just fart?"

   * */

    @Test
    public void testWillReturnNextStateForYesAndNo(){
        String[][] states = {
                {"office", "r u @ office?",     "q"},
                {"fart",   "did u just FART?!", "q"},
                {"awol",   "did u skip work?",  "q"}
        };

        String[][] transitions = {
                {"office", "fart", "y"},
                {"office", "awol", "n"}
        };

        FiniteStateMachine fsm = new FiniteStateMachine(states, transitions);
        State nextYesState = fsm.getNextState(new State.Id("office"), Transition.Type.YES);
        assertEquals(new State.Id("fart"), nextYesState.getId());
        State nextNoState = fsm.getNextState(new State.Id("office"), Transition.Type.NO);
        assertEquals(new State.Id("awol"), nextNoState.getId());
    }

    @Test
    public void testWillReturnFirstState(){
        Random random = new Random();
        String randomString = String.format("%d", random.nextInt());
        String[][] states = {
                {randomString, "r u @ office?",     "q"},
                {"fart",   "did u just FART?!", "q"},
                {"awol",   "did u skip work?",  "q"}
        };

        String[][] transitions = {
                {"office", "fart", "y"},
                {"office", "awol", "n"}
        };

        FiniteStateMachine fsm = new FiniteStateMachine(states, transitions);
        State firstState = fsm.getFirstQuestionState();
        assertEquals(new State.Id(randomString), firstState.getId());
    }

    public void testWillReturnFirstNonTrophy(){
        Random random = new Random();
        String randomString = String.format("%d", random.nextInt());
        String randomString2 = String.format("%d", random.nextInt());
        String[][] states = {
                {randomString, "r u @ office?",     "t"},
                {randomString2,   "did u just FART?!", "q"},
                {"awol",   "did u skip work?",  "q"}
        };

        String[][] transitions = {
                {"office", "fart", "y"},
                {"office", "awol", "n"}
        };

        FiniteStateMachine fsm = new FiniteStateMachine(states, transitions);
        State firstState = fsm.getFirstQuestionState();
        assertEquals(new State.Id(randomString2), firstState.getId());
    }



}
