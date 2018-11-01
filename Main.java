package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Group;

import java.io.FileInputStream;
import java.util.Date;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Pane r =new Pane();
//
//SNAKE IMAGE::
        Image image = new Image(new FileInputStream("C:/Users/AKASH SHARMA/Desktop/unnamed.png"));
        ImageView imageView = new ImageView(image);
        imageView.setX(100);
        imageView.setY(50);
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);
        r.getChildren().add(imageView);

//Buttons:
        //GAMEPLAY PAGE BUTTONS
        Button button1 = new Button("Start New Game");
        Button button2 = new Button("Resume");
        button1.setMinWidth(150);
        button1.setMinHeight(30);
        button2.setMinWidth(150);
        button2.setMinHeight(30);
        HBox hbox = new HBox(button1, button2);
        hbox.setLayoutX(30);
        hbox.setLayoutY(300);
        hbox.setSpacing(40);
        r.getChildren().add(hbox);

        //LEADERBOARD BUTTON
        Button button3= new Button("LeaderBoard");
        button3.setMinWidth(150);
        button3.setMinHeight(30);
        HBox hbox2 = new HBox(button3);
        hbox2.setLayoutX(120);
        hbox2.setLayoutY(400);
        Label lastscore= new Label("Recent Score:");
        lastscore.setLayoutX(300);
        lastscore.setLayoutY(0);
        lastscore.setTextFill(Color.WHITE);

        r.getChildren().add(hbox2);
        Group root = new Group(r, hbox,lastscore);


        //SCENE 1 -> HOME PAGE
        Scene scene= new Scene(root,400, 500);
        scene.setFill(Color.BLACK);
        primaryStage.setTitle("SNAKE VS BLOCK");
        primaryStage.setScene(scene);
        primaryStage.show();


        //GO TO LEADERBOARD
        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LeaderBoard l=new LeaderBoard();
                VBox v= l.leaderboardscene(primaryStage,scene);
                Group leaderboardtable=new Group(v);
                Scene s= new Scene(leaderboardtable,400, 500);
                primaryStage.setTitle("LeaderBoard");
                primaryStage.setScene(s);

            }
        });

        button1.setOnAction(e -> {
            GamePlay g=new GamePlay();
            Scene game=g.game();
            primaryStage.setTitle("Game-Play");
            primaryStage.setScene(game);

        });



    }


    public static void main(String[] args) {
        launch(args);
    }
}
