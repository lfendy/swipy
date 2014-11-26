package questions;

import android.test.AndroidTestCase;

import cheesy.ultra.mundane.trophies.swipy.questions.State;

/**
 * Created by lfendy on 26/11/14.
 */
public class StateTest extends AndroidTestCase {


   public void testWillParseFromArray(){
       String[] rawData = {"fart","Did u just FART?! MOFO?!?!?","q"};
       State q = new State(rawData);

       assertEquals("fart", q.getId());
       assertEquals("Did u just FART?! MOFO?!?!?", q.getText());
       assertEquals(State.Type.Question, q.getType());

   }

}
