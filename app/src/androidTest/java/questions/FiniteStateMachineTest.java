package questions;

import android.test.AndroidTestCase;

import cheesy.ultra.mundane.trophies.swipy.questions.FiniteStateMachine;
import cheesy.ultra.mundane.trophies.swipy.questions.State;
import cheesy.ultra.mundane.trophies.swipy.questions.Transition;

/**
 * Created by lfendy on 26/11/14.
 */
public class FiniteStateMachineTest extends AndroidTestCase{

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

    public void testWillReturnStateForYesAndNo(){
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
        State nextYesState = fsm.getNextState("office", Transition.Type.YES);
        assertEquals("fart", nextYesState.getId());
        State nextNoState = fsm.getNextState("office", Transition.Type.NO);
        assertEquals("awol", nextNoState.getId());
    }

}
