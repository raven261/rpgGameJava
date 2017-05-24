package game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    private static Rooms rooms = new Rooms();
    private static Character pc = new Character();
    private Actions actions = new Actions();
    private static Items items = new Items();
    private static HandleData data = new HandleData();

    @Override
    public void start(final Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("game.fxml"));
        primaryStage.setTitle("RPG");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        GridPane game = new GridPane();
        GridPane inventory = new GridPane();
        GridPane help = new GridPane();
        GridPane gameStart = new GridPane();

        game.setAlignment(Pos.TOP_LEFT);
        game.setHgap(10);
        game.setVgap(10);
        game.setPadding(new Insets(25, 25, 25, 25));

        //TODO: check whether it works:
        inventory.setAlignment(Pos.TOP_LEFT);
        inventory.setHgap(10);
        inventory.setVgap(10);
        inventory.setPadding(new Insets(25,25,25,25));

        help.setAlignment(Pos.TOP_LEFT);
        help.setHgap(10);
        help.setVgap(10);
        help.setPadding(new Insets(25,25,25,25));

        gameStart.setAlignment(Pos.TOP_LEFT);
        gameStart.setHgap(10);
        gameStart.setVgap(10);
        gameStart.setPadding(new Insets(25,25,25,25));

        final Scene scene1 = new Scene(game, 700, 500);
        //TODO: check whether it works:
        final Scene scene2 = new Scene(inventory, 300, 300);
        final Scene scene3 = new Scene(help, 300, 300);
        final Scene scene4 = new Scene(gameStart, 300, 300);

        // Room name
        //game.add(new Label("Location: "), 0, 1);
        final Text roomName = new Text();
        roomName.setText(rooms.printRoomName());
        game.add(roomName, 0,2);

        final Text tempPcLoc = new Text();
        tempPcLoc.setText(pc.printPlayerLocation());
        game.add(tempPcLoc, 1, 2);

        // Room description
        final Text roomDesc = new Text();
        roomDesc.setWrappingWidth(300);
        roomDesc.setText(rooms.printRoomDescription());
        game.add(roomDesc, 0, 4);

        //Room items
        game.add(new Label("Ground:"), 0, 5);
        final Text roomItems = new Text();
        roomItems.setText(rooms.printRoomItems());
        game.add(roomItems, 0,6);

        //temp performed action
        game.add(new Label("action:"), 0, 7);
        final Text tempAction = new Text();
        tempAction.setText(actions.returnPlayerAction());
        game.add(tempAction, 1,7);

        // Items information
        game.add(new Label("Items info:"), 3, 0);
        final Text descText = new Text();
        //descText.setText(items.returnItemDescription());
        descText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        game.add(descText, 3,1);

        // Action text box
        //game.add(new Label("Action: "), 0, 5);
        final TextField ActionTextField = new TextField();
        game.add(ActionTextField, 0,10);
        ActionTextField.requestFocus();
        ActionTextField.setAlignment(Pos.CENTER_LEFT);
        //ActionTextField.setBackground();
        ActionTextField.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke){
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    actions.getPlayerAction(ActionTextField.getText());
                    tempAction.setText(actions.returnPlayerAction());
                    actions.performAction();
                    roomName.setText(rooms.printRoomName());
                    roomDesc.setText(rooms.printRoomDescription());
                    roomItems.setText(rooms.printRoomItems());
                    tempPcLoc.setText(pc.printPlayerLocation());
                    descText.setText(items.returnItemDescription());
                    ActionTextField.clear();
                }
            }
        });

        // Inventory screen
        Text inventoryTitle = new Text("Your posessions:");
        inventoryTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        inventory.add(inventoryTitle, 0,0);


        inventory.add(new Label("Gold: "), 0, 1);
        final Text showGold = new Text();
        //showGold.setText(pc.returnGoldAmount().toString());
        inventory.add(showGold, 1, 1);

        inventory.add(new Label("Items: "), 0, 2);
        final Text itemList = new Text();
        itemList.setText(pc.printInventory());
        inventory.add(itemList, 0, 3);


        Button btnInv = new Button("Inventory");
        HBox showInv = new HBox(5);
        showInv.setAlignment(Pos.CENTER_RIGHT);
        showInv.getChildren().add(btnInv);
        game.add(showInv, 2, 1);
        btnInv.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                final Stage invStage = new Stage();
                invStage.setTitle("Inventory");
                //invStage.initModality(Modality.APPLICATION_MODAL);
                //invStage.initOwner(primaryStage);
                invStage.setScene(scene2);
                invStage.show();
                itemList.setText(pc.printInventory());
                showGold.setText(pc.returnGoldAmount().toString());
            }
        });

        // Help screen
        Text helpTitle = new Text("Help");
        helpTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        help.add(helpTitle, 0,0);

        final Text helpText = new Text();
        helpText.setText("HELP!!!");
        help.add(helpText, 0, 1);


        Button btnHelp = new Button("Help");
        HBox showHelp = new HBox(5);
        showHelp.setAlignment(Pos.CENTER_LEFT);
        showHelp.getChildren().add(btnHelp);
        game.add(showHelp, 2, 2);
        btnHelp.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                final Stage helpStage = new Stage();
                helpStage.setTitle("Help");
                //invStage.initModality(Modality.APPLICATION_MODAL);
                //invStage.initOwner(primaryStage);
                helpStage.setScene(scene3);
                helpStage.show();
                //itemList.setText(pc.printInventory());
            }
        });

        Button btnStart = new Button("Start");
        HBox start = new HBox(5);
        start.setAlignment(Pos.CENTER_RIGHT);
        start.getChildren().add(btnStart);
        gameStart.add(start, 2, 11);
        btnStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                //rdata.initialStart();
                rooms.retrieveRooms();
                rooms.printRoomsArray();
                pc.retrieveCharacter();
                pc.printCharacterArray();
                final Stage gameStage = new Stage();
                gameStage.setScene(scene1);
                gameStage.show();
            }
        });

        Button btnSave = new Button("Save");
        HBox save = new HBox(5);
        save.setAlignment(Pos.CENTER_RIGHT);
        save.getChildren().add(btnSave);
        game.add(save, 2, 12);
        btnSave.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                //data.saveData();
                rooms.saveRooms();
                pc.saveCharacter();
            }
        });

        Button btnLoad = new Button("Load");
        HBox load = new HBox(5);
        load.setAlignment(Pos.CENTER_RIGHT);
        load.getChildren().add(btnLoad);
        game.add(load, 2, 13);
        btnLoad.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                pc.retrieveCharacter();
                rooms.retrieveRooms();
            }
        });


        primaryStage.setScene(scene1);
    }


    public static void main(String[] args) {
        //TODO: refacture so the initial gamestate will be set differently
        rooms.retrieveRooms();
        rooms.printRoomsArray();
        items.retrieveItems();
        pc.retrieveCharacter();
        pc.printCharacterArray();

        launch(args);
    }
}
