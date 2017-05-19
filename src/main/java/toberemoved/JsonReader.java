package toberemoved;


import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;


/*
 * Created by ravenalb on 24-4-2017.
 */


public class JsonReader {

    final private String ROOMSJSON = "C:\\Users\\ravenalb\\IdeaProjects\\rpggame\\src\\main\\java\\sample\\room_text.json";

    public static void main(String[] args){
        new JsonReader().returnGroundItems();
    }

    public String getName(Integer index){
        Map room = new JsonReader().convertToMap(index);
        return room.get("name").toString();
    }

    public String getDescription(Integer index){
        Map room = new JsonReader().convertToMap(index);
        return room.get("desc").toString();
    }

    public void returnGroundItems(){
        JSONArray items = getGround(1);
        for(Object o : items){
            System.out.println(o);
        }
    }

    private JSONArray getGround(Integer index){
        Map room = new JsonReader().convertToMap(index);
        return (JSONArray)room.get("ground");
    }

    private Map convertToMap(Integer index){
        Object obj = new JsonReader().getRoom(index);
        return (Map)obj;
    }

    private Object getRoom(Integer index){
        Object file = new JsonReader().parseFile(ROOMSJSON);
        JSONArray array = (JSONArray) file;
        return array.get(index);
    }

    private Object parseFile(String fileLocation){
        JSONParser parser = new JSONParser();
        Object file = "";
        try{
            file = parser.parse(new FileReader(fileLocation));
        }catch(FileNotFoundException fnf) {
            System.out.println(fnf);
        }catch(IOException e){
            System.out.println(e);
        }catch(ParseException p){
            System.out.println(p);
        }
        return file;
    }



}
