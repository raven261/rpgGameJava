package game;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.*;

/*
 * Created by ravenalb on 5-5-2017.
 */

public class HandleData {


    final private static String SAVE_CHARACTER = "C:\\Users\\ravenalb\\IdeaProjects\\rpggame\\src\\main\\java\\game\\character.json";
    final private static String SAVE_ROOMS = "C:\\Users\\ravenalb\\IdeaProjects\\rpggame\\src\\main\\java\\game\\rooms.json";


    final private static String CHARACTER_INITIAL = "C:\\Users\\ravenalb\\IdeaProjects\\rpggame\\src\\main\\java\\game\\character_initial.json";
    final private static String ROOMS_INITIAL= "C:\\Users\\ravenalb\\IdeaProjects\\rpggame\\src\\main\\java\\game\\rooms_initial.json";

    final private static String CHARACTER = "C:\\Users\\ravenalb\\IdeaProjects\\rpggame\\src\\main\\java\\game\\character.json";
    final private static String ROOMS = "C:\\Users\\ravenalb\\IdeaProjects\\rpggame\\src\\main\\java\\game\\rooms.json";
    final private static String ITEMS = "C:\\Users\\ravenalb\\IdeaProjects\\rpggame\\src\\main\\java\\game\\items.json";
    final private static String TEST = "C:\\Users\\ravenalb\\IdeaProjects\\rpggame\\src\\test\\java\\game\\rooms_test.json";

    public static void main(String[] args){
    }

    JsonArray getData(String type){

        if(type.equals("character")){
            return readJson(CHARACTER);
        }
        else if(type.equals("rooms")){
            return readJson(ROOMS);
        }
        else if(type.equals("items")){
            return readJson(ITEMS);
        }
        else if(type.equals("test")){
            return readJson(TEST);
        }
        return null;
    }

    void saveData(String type, JsonArray array){

        if(type.equals("character")) {
            saveJson(SAVE_CHARACTER, array);
        }
        else if(type.equals("rooms")) {
            saveJson(SAVE_ROOMS, array);
        }
        System.out.println("data saved");
    }

    private JsonArray readJson(String file){
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            JsonParser parser = new JsonParser();
            return parser.parse(br).getAsJsonArray();
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        System.out.println("readJson returned null");
        return null;
    }



    private void saveJson(String file, JsonArray array){
        try{
            FileWriter writer = new FileWriter(file);
            Gson gson = new Gson();
            String arrayString = gson.toJson(array);
            writer.write(arrayString);
            writer.close();
            System.out.println("file saved");
        }catch(IOException e){
            e.getMessage();
        }

    }

}
