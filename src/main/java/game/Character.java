package game;

/*
 * Created by ravenalb on 24-4-2017.
 */


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

class Character {

    private static HandleData data = new HandleData();
    //private static WriteData wdata = new WriteData();

    private static JsonArray character = new JsonArray();
    private static int[] location = new int[2];
    private static int[] goldPurse = new int[1];

    public static void main(String[] args){
        new Character().retrieveCharacter();
        System.out.println("1: " + character);
        new Character().updateLocationValue(0,1);
        new Character().updateLocationValue(1,1);
        System.out.println("2: " + character);
    }

    void printCharacterArray(){
        System.out.println("character: " + character);
    }

    JsonArray returnCharacterArray(){
        return character;
    }

    void updateLocationValue(int index, int value){
        location[index] = value;
        saveLocationToCharacterArray();
        System.out.println("location updated");
    }

    Integer getPlayerLocationY(){
        return location[1];
    }

    Integer getPlayerLocationX(){
        return location[0];
    }

    String printPlayerLocation(){
        Integer locx = location[0];
        Integer locy = location[1];
        return locx.toString() + locy.toString();
    }

    void addGoldToGoldPurse(Integer goldAmount){
        goldPurse[0] += goldAmount;
        saveGoldAmountToCharacterArray();
    }

    void substractGoldFromGoldPurse(Integer goldAmount){
        goldPurse[0] -= goldAmount;
        saveGoldAmountToCharacterArray();
    }

    Integer returnGoldAmount(){
        return goldPurse[0];
    }

    private JsonArray returnInventoryItems(){
        JsonObject inventoryObject = searchPcData("inventory");
        JsonArray nothingHere = new JsonArray();
        nothingHere.add("Nothing in your inventory");
        try {
            JsonArray test = inventoryObject.get("inventory").getAsJsonArray();
            return test;
        }catch(NullPointerException e){
            e.getMessage();
        }
        return nothingHere;
    }

    String printInventory(){
        String allItems = "";
        for(JsonElement i : returnInventoryItems()){
            allItems += i + "\n";
        }
        return allItems.replaceAll("\"", "");
    }

    void addItemToInventory(String item){
        JsonObject inventoryObject = searchPcData("inventory");
        try {
            JsonArray inventory = inventoryObject.get("inventory").getAsJsonArray();
            inventory.add(item);
        }catch(NullPointerException e){
            e.getMessage();
        }
    }

    void retrieveCharacter(){
        character = data.getData("character");
        refillLocationArray();
        refillGoldArray();
    }

    void loadCharacter(){
        character = data.getData("character");
    }

    private JsonObject searchPcData(String PcInfo){
        try {
            JsonArray pc = character;
            for (JsonElement i : pc) {
                JsonObject jsonOBject = i.getAsJsonObject();
                String id = jsonOBject.get("id").getAsString();
                if(id != null) {
                    if (id.equals(PcInfo)) {
                        //System.out.println(info);
                        return jsonOBject;
                    }
                }
            }
            System.out.println("getPcData returned null");
        }catch(NullPointerException e){
            System.out.println("getPcData returned nullpointer" + e.getMessage());
        }
        return null;
    }

    private void refillLocationArray(){
        System.out.println("refill location array");
        JsonObject currentLocation = searchPcData("position");
        JsonElement xLocation = currentLocation.get("locationX");
        JsonElement yLocation = currentLocation.get("locationY");
        Integer x = xLocation.getAsInt();
        Integer y = yLocation.getAsInt();
        location[0] = x;
        location[1] = y;
    }

    private void refillGoldArray(){
        System.out.println("refill goldPurse array");
        JsonObject goldAmount = searchPcData("coins");
        JsonElement gold = goldAmount.get("goldPurse");
        Integer g = gold.getAsInt();
        goldPurse[0] = g;
    }


    private void saveLocationToCharacterArray(){
        Gson gson = new Gson();
        String xCoord = gson.toJson(location[0], Integer.class);
        String yCoord = gson.toJson(location[1], Integer.class);

        JsonArray pc = character;
        JsonObject testLocation = pc.get(0).getAsJsonObject();
        testLocation.addProperty("locationX", xCoord);
        testLocation.addProperty("locationY", yCoord);
    }

    private void saveGoldAmountToCharacterArray(){
        Gson gson = new Gson();
        String gold = gson.toJson(goldPurse[0], Integer.class);

        JsonObject goldObject = searchPcData("coins");
        goldObject.addProperty("goldPurse", gold);
    }

    void saveCharacter(){
        data.saveData("character", character);
    }


//    public int getElementFromArray(int[] array, int index){
//        return array[index];
//    }

//    private void replaceIntArrayValue(int[] array, int index, int value){
//        array[index] = value;
//    }

//    private static int rollDie(int min, int max){
//        Random rand = new Random();
//        return rand.nextInt(max) + min;
//    }




}
