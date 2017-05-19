package toberemoved;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/*
 * Created by ravenalb on 1-5-2017.
 */
public class ItemReader {

    final private static String ITEMJSON = "C:\\Users\\ravenalb\\IdeaProjects\\rpggame\\src\\main\\java\\sample\\items.json";

    public static void main(String[] args){
       new ItemReader().printItemName("item2");
    }

    private void printItemName(String item){
        String name = returnItemName(item);
        System.out.println(name);
    }

    public String returnItemName(String item){
        JsonObject fullItem = getItemObject(item);
        return fullItem.get("name").toString();
    }

    private JsonObject getItemObject(String item){
        try {
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(new FileReader(ITEMJSON));
            JsonParser parser = new JsonParser();
            JsonArray items = parser.parse(br).getAsJsonArray();


            for(JsonElement i : items){
                JsonObject itemObject = i.getAsJsonObject();
                String id = itemObject.get("id").getAsString();
                if(id.equals(item)){
                    return itemObject;
                }

            }

        }catch(FileNotFoundException e){ System.out.println(e);}
        return null;
    }


}
