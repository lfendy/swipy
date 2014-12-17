package questions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import cheesy.ultra.mundane.trophies.swipy.questions.State;
import cheesy.ultra.mundane.trophies.swipy.questions.Transition;

import static org.junit.Assert.assertEquals;

@Config(manifest = "./src/main/AndroidManifest.xml")
@RunWith(RobolectricTestRunner.class)
public class TransitionTest{

    @Test
    public void testWillParseFromArray() {
       String[] rawData = {"fart","loud","y"};
       Transition t = new Transition(rawData);


       assertEquals(new State.Id("fart"), t.getFromState());
       assertEquals(new State.Id("loud"), t.getToState());
       assertEquals(Transition.Type.YES, t.getType());
   }

}

