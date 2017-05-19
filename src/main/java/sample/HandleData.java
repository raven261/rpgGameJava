package sample;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.*;

/*
 * Created by ravenalb on 5-5-2017.
 */

public class HandleData {

    private Character pc = new Character();
    private Rooms rooms = new Rooms();

    final private static String SAVE_CHARACTER = "C:\\Users\\ravenalb\\IdeaProjects\\rpggame\\src\\main\\java\\sample\\character.json";
    final private static String SAVE_ROOMS = "C:\\Users\\ravenalb\\IdeaProjects\\rpggame\\src\\main\\java\\sample\\rooms.json";


    final private static String CHARACTER_INITIAL = "C:\\Users\\ravenalb\\IdeaProjects\\rpggame\\src\\main\\java\\sample\\character_initial.json";
    final private static String ROOMS_INITIAL= "C:\\Users\\ravenalb\\IdeaProjects\\rpggame\\src\\main\\java\\sample\\rooms_initial.json";

    final private static String CHARACTER = "C:\\Users\\ravenalb\\IdeaProjects\\rpggame\\src\\main\\java\\sample\\character.json";
    final private static String ROOMS = "C:\\Users\\ravenalb\\IdeaProjects\\rpggame\\src\\main\\java\\sample\\rooms.json";
    final private static String ITEMS = "C:\\Users\\ravenalb\\IdeaProjects\\rpggame\\src\\main\\java\\sample\\items.json";

    public static void main(String[] args){
    }

//    void initialStart(){
//        readJson(CHARACTER_INITIAL);
//        readJson(ROOMS_INITIAL);
//        System.out.println("Initial start");
//    }

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
        return null;
    }

//    JsonArray loadData(String type){
//
//        if(type.equals("character")){
//            return readJson(CHARACTER);
//        }
//        else if(type.equals("rooms")){
//            return readJson(ROOMS);
//        }
//        else if(type.equals("items")){
//            return readJson(ITEMS);
//        }
//        return null;
//    }

    JsonArray readJson(String file){
        //JsonParser error = new JsonParser().parse("{\"error\": \"nullpointer\"}").getAsJsonObject();
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

    void saveData(){
        saveJson(SAVE_CHARACTER, pc.returnCharacterArray());
        saveJson(SAVE_ROOMS, rooms.returnAllRoomsArray());
        System.out.println("data saved");
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
