package cheesy.ultra.mundane.trophies.swipy.questions;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;

@Config(manifest = "./src/main/AndroidManifest.xml")
@RunWith(RobolectricTestRunner.class)
public class StateTest{

   @Test
   public void testWillParseFromArray(){
       String[] rawData = {"fart","Did u just FART?! MOFO?!?!?","q"};
       State q = new State(rawData);

       assertEquals(new State.Id("fart"), q.getId());
       assertEquals("Did u just FART?! MOFO?!?!?", q.getText());
       assertEquals(State.Type.Question, q.getType());

   }

}
