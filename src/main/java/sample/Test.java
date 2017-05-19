package sample;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/*
 * Created by ravenalb on 11-5-2017.
 */
public class Test {

    private static HandleData data = new HandleData();

   // private static ArrayList<CharacterArray> character = new ArrayList<CharacterArray>();
    private static JsonArray character = new JsonArray();
    private static int[] location = new int[2];

    public static void main(String[] args){
        //new Test().test();
        new Test().retrieveCharacter();
        //new Test().test();
        //new Test().searchPcData("position");
        new Test().returnLocation();
        new Test().saveLocation();
    }


//    void updateLocationValue(int index, int value){
//        //currentLocation.set(index, value);
//        //TODO: method needs JsonElement instead of int
//        JsonObject information = searchPCData("position");
//        JsonArray coords = information.get("data").getAsJsonArray();
//        coords.set(index, value);
//    }




    private void test(){
        System.out.println(character);
    }


    private void retrieveCharacter(){
        JsonArray PC = data.getData("character");
        if(PC != null){
            int len = PC.size();
            for (int i = 0; i < len; i++){
                character.add(PC.get(i));
            }
        }
    }

    private JsonObject searchPcData(String PcInfo){
        try {
            JsonArray pc = character;
            for (JsonElement i : pc) {
                JsonObject info = i.getAsJsonObject();
                String id = info.get("id").getAsString();
                if(id != null) {
                    if (id.equals(PcInfo)) {
                        System.out.println(info);
                        return info;
                    }
                }
            }
            System.out.println("getPcData returned null");
        }catch(NullPointerException e){
            System.out.println("getPcData returned nullpointer" + e.getMessage());
        }
        return null;
    }

    private void returnLocation(){
        JsonObject currentLocation = searchPcData("position");
        JsonArray cLocation = currentLocation.get("location").getAsJsonArray();
        Integer x = cLocation.get(0).getAsInt();
        Integer y = cLocation.get(1).getAsInt();
        location[0] = x;
        location[1] = y;
    }

    private void saveLocation(){
        Gson gson = new Gson();
        String jArray = gson.toJson(location, int[].class);
        System.out.println("jArray: " + jArray);

//        JsonObject test = new JsonObject();
//        test.addProperty("location", jArray);
//        System.out.println(test);

        JsonArray pc = character;
        JsonObject testLocation = pc.get(0).getAsJsonObject();
        System.out.println(testLocation);
        testLocation.addProperty("location", jArray);
        System.out.println(testLocation);
        System.out.println(character);
    }


}
