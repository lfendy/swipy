package questions;

import android.test.AndroidTestCase;

import cheesy.ultra.mundane.trophies.swipy.questions.Transition;

/**
 * Created by lfendy on 26/11/14.
 */
public class TransitionTest extends AndroidTestCase{


    public void testWillParseFromArray() {
       String[] rawData = {"fart","loud","y"};
       Transition t = new Transition(rawData);

       assertEquals("fart", t.getFromState());
       assertEquals("loud", t.getToState());
       assertEquals(Transition.Type.YES, t.getType());
   }

}

