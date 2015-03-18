package cheesy.ultra.mundane.trophies.swipy.questions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import cheesy.ultra.mundane.trophies.swipy.RobolectricGradleTestRunner;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricGradleTestRunner.class)
@Config(emulateSdk = 18, reportSdk = 18)
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

