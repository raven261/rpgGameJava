package game;

/*
 * Created by ravenalb on 19-5-2017.
 */



import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public class RoomsTest {

    private Rooms rooms = new Rooms();
    //private World world = new World();
    //private HandleData data = new HandleData();

    @Test
    public void testRoomTexts(){
        rooms.retrieveTestRooms();
        rooms.testSetRoomNumberLocal("1");
        assertEquals("Test room name String", "Test Room", rooms.printRoomName());
        assertEquals("Test room description String", "Test room description", rooms.printRoomDescription());
        assertEquals("test room items", "test1 test2 ", rooms.printRoomItems());
    }

    @Test
    public void testGetRoomIndex(){
        assertEquals("assert that an index is returned as an Integer", (Object)12, rooms.getRoomIndex(1,1));
    }

//    @Test(expected=IndexOutOfBoundsException.class)
    //TODO: this test does not work, figureout how to get it to work
//    public void testgetRoomCoordsException(){
//        Integer worldEnd = world.returnWorldLength();
//        Integer worldOverEnd = worldEnd + 1;
//        System.out.println(worldOverEnd);
//        rooms.getRoomIndex(0, worldOverEnd);
//    }

}
