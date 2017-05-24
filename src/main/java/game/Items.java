package game;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Iterator;

/*
 * Created by ravenalb on 1-5-2017.
 */
class Items {

    private HandleData data = new HandleData();

    private JsonArray allItems = new JsonArray();

    private static String currentItem = "";


    void retrieveItems(){
        allItems = data.getData("items");
    }

    void setCurrentItem(String item){
        currentItem = item;
    }

    String returnItemDescription(){
        String nullPointer = "No item selected";
        try {
            JsonObject itemObject = getItem(currentItem);
            JsonElement itemElement = itemObject.get("desc");
            return itemElement.getAsString();
        }catch(NullPointerException e){
            e.getMessage();
        }
        return nullPointer;
    }

    private JsonObject getItem(String item){
        JsonArray items = allItems;
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



}
