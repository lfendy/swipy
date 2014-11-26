package questions;

import android.test.AndroidTestCase;

import cheesy.ultra.mundane.trophies.swipy.questions.Node;

/**
 * Created by lfendy on 26/11/14.
 */
public class NodeTest extends AndroidTestCase {
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


   public void testWillParseNodeFromArray(){
       String[] rawData = {"fart","Did u just FART?! MOFO?!?!?","q"};
       Node q = new Node(rawData);

       assertEquals("fart", q.getId());
       assertEquals("Did u just FART?! MOFO?!?!?", q.getText());
       assertEquals(Node.NodeType.Question, q.getType());

   }

}
