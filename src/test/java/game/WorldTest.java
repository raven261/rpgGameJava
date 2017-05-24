package game;

/*
 * Created by ravenalb on 22-5-2017.
 */


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class WorldTest {

    private World world = new World();

    @Test
    public void testgetRoomCoords(){
        assertEquals("getRoomCoords returns an interger", (Object)12, world.getRoomCoords(1,1));
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testgetRoomCoordsException(){
        Integer worldEnd = world.returnWorldLength();
        Integer worldOverEnd = worldEnd + 1;
        world.getRoomCoords(-1,0);
        world.getRoomCoords(0,-1);
        //world.getRoomCoords(0 ,0);
    }


//    @Test
//    public void test(){
//        System.out.println(world.returnWorldLength());
//    }
}


