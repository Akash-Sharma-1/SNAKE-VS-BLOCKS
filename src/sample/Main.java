package sample;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.util.Date;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Pane r =new Pane();
//
//SNAKE IMAGE::
        Image image = new Image(new FileInputStream("src/sample/unnamed.png"));
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
        HBox hbox = new HBox(button1);
        hbox.setLayoutX(120);
        hbox.setLayoutY(270);
        HBox hboxx = new HBox(button2);
        hboxx.setLayoutX(120);
        hboxx.setLayoutY(320);
        r.getChildren().add(hbox);
        r.getChildren().add(hboxx);

        //LEADERBOARD BUTTON
        Button button3= new Button("LeaderBoard");
        button3.setMinWidth(150);
        button3.setMinHeight(30);
        HBox hbox2 = new HBox(button3);
        hbox2.setLayoutX(120);
        hbox2.setLayoutY(370);
        Label lastscore= new Label("Recent Score:");
        lastscore.setLayoutX(300);
        lastscore.setLayoutY(0);
        lastscore.setTextFill(Color.WHITE);
        Rectangle rekt=new Rectangle(380,480);
        rekt.setLayoutX(10);
        rekt.setLayoutY(10);
        Circle c1 =new Circle(10);
        c1.setFill(Color.YELLOW);
        c1.setLayoutX(0);
        Circle c2 =new Circle(10);
        c2.setFill(Color.RED);
        c2.setLayoutX(20);
        Circle c3 =new Circle(10);
        c3.setFill(Color.WHITE);
        c3.setLayoutX(40);
        Circle c4 =new Circle(10);
        c4.setFill(Color.GREEN);
        c4.setLayoutX(60);
        Circle c5 =new Circle(10);
        c5.setFill(Color.BLUE);
        c5.setLayoutX(80);
        PathTransition pl= new PathTransition();
        pl.setPath(rekt);
        pl.setNode(c1);
        pl.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pl.setCycleCount(Animation.INDEFINITE);
        pl.setAutoReverse(true);
        pl.setDuration(Duration.seconds(4));
        pl.play();
        rekt.setLayoutX(10);
        rekt.setLayoutY(10);
        rekt.setHeight(460);
        rekt.setWidth(360);

        //
        PathTransition pl1= new PathTransition();
        pl1.setPath(rekt);
        pl1.setNode(c2);
        pl1.setCycleCount(Animation.INDEFINITE);
        pl1.setAutoReverse(true);
        pl1.setDuration(Duration.seconds(4));
        pl1.play();
        rekt.setLayoutX(10);
        rekt.setLayoutY(10);
        rekt.setHeight(440);
        rekt.setWidth(340);

        //
        PathTransition pl2= new PathTransition();
        pl2.setPath(rekt);
        pl2.setNode(c3);
        pl2.setCycleCount(Animation.INDEFINITE);
        pl2.setAutoReverse(true);
        pl2.setDuration(Duration.seconds(4));
        pl2.play();

        //
        rekt.setLayoutX(10);
        rekt.setLayoutY(10);
        rekt.setHeight(420);
        rekt.setWidth(320);
        PathTransition pl3= new PathTransition();
        pl3.setPath(rekt);
        pl3.setNode(c4);
        pl3.setCycleCount(Animation.INDEFINITE);
        pl3.setAutoReverse(true);
        pl3.setDuration(Duration.seconds(4));
        pl3.play();
        //
        rekt.setLayoutX(10);
        rekt.setLayoutY(10);
        rekt.setHeight(400);
        rekt.setWidth(300);

        PathTransition pl4= new PathTransition();
        pl4.setPath(rekt);
        pl4.setNode(c5);
        pl4.setCycleCount(Animation.INDEFINITE);
        pl4.setAutoReverse(true);
        pl4.setDuration(Duration.seconds(4));
        pl4.play();
        r.getChildren().add(hbox2);
        Group root = new Group(r, hbox,lastscore,c1,c2,c3,c4,c5);


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
            Scene game=g.game(scene,primaryStage);
            game.setFill(Color.BLACK);
            Stage s=new Stage();
            Pane p=new Pane();
            TextField namer=new TextField();
            Button b=new Button("Click");
            b.setOnAction(e-> );
            Scene ga=new Scene(p,300, 400);
            s.show();
            primaryStage.setTitle("Game-Play");
            primaryStage.setScene(game);

        });



    }


    public static void main(String[] args) {
        launch(args);
    }
}
