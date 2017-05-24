package game;

/*
 * Created by ravenalb on 24-4-2017.
 */


public class World {

    private int[][] world = {
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
            {11, 12, 13, 14, 15, 16, 17, 18, 19, 20}
    };

    private Character pc = new Character();

    public static void main(String[] args) {
       // new World().printRoom();
        //new World().test();
    }

    Integer getRoomCoords(int x, int y) {
        return world[x][y];
    }

    Integer returnWorldLength(){
        return world.length;
    }

    void moveNorth(){

        try {
            Integer oldX = pc.getPlayerLocationX();
            Integer newX = oldX - 1;
            if(newX >= 0){
                pc.updateLocationValue(0, newX);
            }else{
                System.out.println("You cannot go that way.");
            }

        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("MoveNorth: " + e.getMessage());
        }
    }

    void moveEast(){
        Integer oldY = pc.getPlayerLocationY();
        Integer newY = oldY + 1;
        if(newY >= 0 && newY < world[pc.getPlayerLocationX()].length){
            pc.updateLocationValue(1, newY);
        }else{
            System.out.println("East, You cannot go that way");
        }

    }

    void moveSouth(){
        Integer oldX= pc.getPlayerLocationX();
        Integer newX = oldX + 1;
        if(newX < world.length) {
            pc.updateLocationValue(0, newX);
        }else{
            System.out.println("south: newX < world.length error");
        }
    }

    void moveWest(){
        try {
            Integer oldY = pc.getPlayerLocationY();
            Integer newY = oldY - 1;
            if (newY >= 0 && newY < world[newY].length) {
                pc.updateLocationValue(1, newY);
            } else {
                System.out.println("You cannot go that way");
            }
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("MoveNorth: " + e.getMessage());
        }
    }


}
