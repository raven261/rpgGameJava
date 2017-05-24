package game;

/*
 * Created by ravenalb on 26-4-2017.
 */

class Actions {

    private World world = new World();
    private Rooms rooms = new Rooms();
    private Character pc = new Character();
    private Items items = new Items();

    private String rawAction = "start";
    private String[] actionArray = {};

    void getPlayerAction(String takeAction){
        rawAction = takeAction.toLowerCase();
        stringToArray();
    }

    String returnPlayerAction(){
        return rawAction;
    }

    private void stringToArray(){
        actionArray = rawAction.split(" ");
    }

    String returnActionText(String text){
        return text.replaceAll("\"", "");
    }


    void performAction(){

        if(actionArray[0].equals("go")){
            if(actionArray[1].equals("north") || actionArray[1].equals("n")){
                world.moveNorth();
            }
            else if(actionArray[1].equals("east") || actionArray[1].equals("e")){
                world.moveEast();
            }
            else if(actionArray[1].equals("west") || actionArray[1].equals("w")){
                world.moveWest();
            }
            else if(actionArray[1].equals("south") || actionArray[1].equals("s")){
                world.moveSouth();
            }
        }
        else if(actionArray[0].equals("take")){
            String item = actionArray[1];
            rooms.takeItem(item);
        }
        else if(actionArray[0].equals("inspect")){
            String item = actionArray[1];
            items.setCurrentItem(item);
        }
        else {
            System.out.println("Unknown command");
        }
    }
}
