package game;

/*
 * Created by ravenalb on 24-4-2017.
 */


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Iterator;

public class Rooms {

    private World world = new World();
    private Character pc = new Character();
    private HandleData data = new HandleData();

    private static JsonArray allRooms = new JsonArray();

    private static String ROOMNUMBER = "";

    public static void main(String[] args){
       //new Rooms().printRoomName(0, 0);
    }

    JsonArray returnAllRoomsArray(){
        return allRooms;
    }

    Integer getRoomIndex(int x, int y){
        return world.getRoomCoords(x, y);
    }

    private String returnRoomNumber(){
        return ROOMNUMBER;
    }

    void testSetRoomNumberLocal(String roomNumber){
        ROOMNUMBER = roomNumber;
    }

    private void setRoomNumberLocal(){
        Integer roomNumber = getRoomIndex(pc.getPlayerLocationX(),pc.getPlayerLocationY());
        ROOMNUMBER = roomNumber.toString();
    }

    private String returnItemName(String item){
        JsonObject fullItem = getItem(item);
        return fullItem.get("name").toString();
    }

    private String returnRoomString(String item){
        setRoomNumberLocal();
        JsonObject currentRoom = getRoom(ROOMNUMBER);
        return currentRoom.get(item).toString().replaceAll("\"", "");
    }

    private JsonArray returnRoomJsonArray(String roomItem){
        JsonObject currentRoom = getRoom(ROOMNUMBER);
        return currentRoom.get(roomItem).getAsJsonArray();
    }

    String printRoomName(){
        return returnRoomString("name");
    }

    String printRoomDescription(){
        return returnRoomString("desc");
    }

    String printRoomItems(){
        String allItems = "";
        for(JsonElement i : returnRoomJsonArray("ground")){
            allItems += i + " ";
        }
        return allItems.replaceAll("\"", "");
    }

    void takeItem(String item){
        JsonArray items = returnRoomJsonArray("ground");
        Iterator<JsonElement> iter = items.iterator();
        while (iter.hasNext()){
            String itemString = iter.next().getAsString();
            if(item.equals(itemString)){
                iter.remove();
                if(item.equals("gold")) {
                    pc.addGoldToGoldPurse(1);
                }else{
                    pc.addItemToInventory(item);
                }
            }
        }
    }

    private JsonObject getItem(String item){
        JsonArray items = data.getData("items");
        Iterator<JsonElement> iter = items.iterator();
        while (iter.hasNext()){
            JsonElement i = iter.next();
            JsonObject itemObject = i.getAsJsonObject();
            String id = itemObject.get("id").getAsString();
            if(id.equals(item)){
                return itemObject;
            }
        }
        return null;
    }

    void saveRooms(){
        data.saveData("rooms", allRooms);
    }

    private JsonObject getRoom(String room){
        try {
            JsonArray rooms = allRooms;
            for (JsonElement i : rooms) {
                JsonObject itemObject = i.getAsJsonObject();
                String id = itemObject.get("id").getAsString();
                if (id.equals(room)) {
                    return itemObject;
                }
            }
            System.out.println("getRoom returned null");
        }catch(NullPointerException e){
            System.out.println("getRoom returned nullpointer" + e.getMessage());
        }
        return null;
    }

    void retrieveRooms(){
        allRooms = data.getData("rooms");
    }

    void retrieveTestRooms(){allRooms = data.getData("test");}

    void printRoomsArray(){
        System.out.println("allRooms: " + allRooms);
    }

}
