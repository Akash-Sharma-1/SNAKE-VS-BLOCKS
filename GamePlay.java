package sample;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.Callable;

public class GamePlay{

     public void blockgenerator(Pane p)
     {
         Random r=new Random();
         int noblock=(r.nextInt(3)+1);
//          noblock=1;
         ArrayList<Integer> list = new ArrayList<Integer>();
         for (int i=0; i<5; i++) {
             list.add(i);
         }
         Rectangle b=new Rectangle(78,78);
         ArrayList<Rectangle> blocks= new ArrayList<Rectangle>();



         if(noblock==1)
         {

             int pos=(r.nextInt(4));
             b.setLayoutX(pos*80);
             b.setLayoutY(-120);
             b.setArcWidth(20);
             b.setArcHeight(20);
             double rr = r.nextDouble();
             double gg = r.nextDouble();
             double bb = r.nextDouble();
             double oo = r.nextDouble();
             Color rectangleColor = new Color(rr, gg, bb,1);
             b.setFill(rectangleColor);
             p.getChildren().add(b);
             blocks.add(b);


         }
         else
         {
             Collections.shuffle(list);
             for(int i=0;i<noblock;i++)
             {
                 int pos=list.get(i);
//                 System.out.println(i);
                 Rectangle x=new Rectangle(78,78);
                 x.setX(pos*80);
                 x.setY(-120);
                 x.setArcWidth(20);
                 x.setArcHeight(20);
                 double rr = r.nextDouble();
                 double gg = r.nextDouble();
                 double bb = r.nextDouble();
                 double oo = r.nextDouble();
                 Color rectangleColor = new Color(rr, gg, bb, 1);
                 x.setFill(rectangleColor);
                 blocks.add(x);
//                 System.out.println(blocks);

             }
             p.getChildren().addAll(blocks);

         }
         for(int i=0;i<noblock;i++)
         {
             TranslateTransition transition=new TranslateTransition();
             transition.setDuration(Duration.seconds(6));
//             transition.setInterpolator(Interpolator.LINEAR);
             transition.setToY(630);
             transition.setNode(blocks.get(i));
             transition.play();


         }



//         yourTransition.setOnFinished(new EventHandler<ActionEvent>(){
//             public void handle(ActionEvent AE){
//                 transitionPlaying = false;
//             }


     }


    public void wallgenerator(Pane p,int ii) {
        Random r = new Random();
        int noblock = (r.nextInt(2) + 1);

        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        ArrayList<Rectangle> blocks = new ArrayList<Rectangle>();


        if (noblock == 1) {

            int size=0;
            int length=0;
            if(ii==0)
            {
                length=(r.nextInt(40)+80);
                size=-120;
            }
            else
            {
                length=(r.nextInt(20)+40);
                size=(r.nextInt(30)+30);

            }
            int pos = (r.nextInt(4));
            Rectangle b = new Rectangle(3, length);
            if(pos!=0)  b.setX((pos * 80)-2);
            else b.setX(0);
            b.setLayoutY(-1*(length));

//            System.out.println(size);
//            b.setLayoutY(size);
            b.setFill(Color.WHITE);
            blocks.add(b);
            p.getChildren().add(b);


        } else {
            Collections.shuffle(list);
            for (int i = 0; i < noblock; i++) {
                int pos = list.get(i);
//                 System.out.println(i);
                int size=0;
                int length=0;
                if(ii==0)
                {
                    length=(r.nextInt(40)+80);
                    size=-120;
                }
                else
                {
                    length=((r.nextInt(2)*10)+50);
                    size=(r.nextInt(30)+30);

                }
                Rectangle x = new Rectangle(3, length);
                if(pos!=0)  x.setX((pos * 80)-2);
                else x.setX(0);
                x.setY(-1*(length));


                x.setFill(Color.WHITE);
                blocks.add(x);
//                 System.out.println(blocks);

            }
            p.getChildren().addAll(blocks);

        }
        for(int i=0;i<noblock;i++)
        {
            TranslateTransition transition=new TranslateTransition();
            transition.setDuration(Duration.seconds(6));
//            transition.setInterpolator(Interpolator.LINEAR);
            transition.setToY(630);
            transition.setNode(blocks.get(i));
            transition.play();
        }

    }
    public void tokengenerator(Pane p,int ii)
    {
        ArrayList<Rectangle> tokens=new ArrayList<Rectangle>();
        Random r = new Random();
            int x=(int)(Math.random()*4);
            if(ii==0) x=0;
            int xx=(r.nextInt()+80);
            int pos=r.nextInt(5)+1;
            Circle c=new Circle();
            if(x==0)
            {
                c=new Circle(((pos*80)-40),-18,9, Color.YELLOW);
                p.getChildren().add(c);

            }
            if(x==1)
            {
                Image image=null;
                try
                {
                    image = new Image(new FileInputStream("C:/Users/AKASH SHARMA/Desktop/magnet.jpg"));
                    if(ii==0)c=new Circle(((pos*80)-40),-18,9);
                    else c=new Circle(((pos*80)-40),0,9);
                    c.setFill(new ImagePattern(image));
                    p.getChildren().add(c);

                }
                catch(Exception e)
                {

                }
            }
            if(x==2)
            {
                Image image=null;
                try
                {
                    image = new Image(new FileInputStream("C:/Users/AKASH SHARMA/Desktop/shield.jpg"));
                    if(ii==0)c=new Circle(((pos*80)-40),-18,9);
                    else c=new Circle(((pos*80)-40),0,9);
                    c.setFill(new ImagePattern(image));
                    p.getChildren().add(c);

                }
                catch(Exception e)
                {

                }
            }
            if(x==3)
            {
                Image image=null;
                try
                {
                    image = new Image(new FileInputStream("C:/Users/AKASH SHARMA/Desktop/bomb.jpg"));
                    if(ii==0)c=new Circle(((pos*80)-40),-18,9);
                    else c=new Circle(((pos*80)-40),0,9);
                    c.setFill(new ImagePattern(image));
                    p.getChildren().add(c);

                }
                catch(Exception e)
                {

                }
            }

            TranslateTransition transition=new TranslateTransition();
            transition.setDuration(Duration.seconds(6));
            if(ii==0)transition.setToY(632);
            else transition.setToY(620);
            transition.setNode(c);
            transition.play();


    }
     public void moveleft(ArrayList<Circle> snake,Label l)
     {
         l.setTranslateX(l.getTranslateX() - 20);
         for(int i=0;i<5;i++) {
             snake.get(i).setTranslateX(snake.get(i).getTranslateX() - 20);
         }
     }
     public void moveright(ArrayList<Circle> snake,Label l)
     {
         l.setTranslateX(l.getTranslateX() + 20);
         for(int i=0;i<5;i++) {
             snake.get(i).setTranslateX(snake.get(i).getTranslateX()+20);
         }
     }
     public Scene game(Scene home, Stage primaryStage)
     {
         Pane p=new Pane();
         Label score = new Label("Score :");
         MenuItem menuItem1 = new MenuItem("EXIT");
         MenuItem menuItem2 = new MenuItem("RESTART");
         MenuButton menuButton = new MenuButton("Options", null, menuItem1, menuItem2);
         menuButton.setLayoutX(320);
         menuButton.setLayoutY(460);
         score.setLayoutY(460);
         score.setTextFill(Color.WHITE);
         p.getChildren().addAll(menuButton,score);
         menuItem1.setOnAction(event -> {
             primaryStage.setTitle("Snake vs Block - Welcome Back!");
             primaryStage.setScene(home);
         });
         ArrayList<Circle> snake=new ArrayList<Circle>();
         int point=250;
         Label len=new Label("5");
         len.setLayoutX(197);
         len.setLayoutY(225);
         for(int i=0;i<5;i++)
         {
             snake.add(new Circle(200,point,7, Color.YELLOW));
             point+=14;
         }


         p.getChildren().addAll(snake);
         p.getChildren().add(len);

         Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(3.4), new EventHandler<ActionEvent>() {

             @Override
             public void handle(ActionEvent event) {
                 blockgenerator(p);
                 //Walls with Blocks
                 wallgenerator(p,0);
                 tokengenerator(p,0);
             }

         }));
         Timeline tSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {

             @Override
             public void handle(ActionEvent event) {
                 //middle wall
                 wallgenerator(p,1);

             }

         }));

         Timeline sevenSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(5.1), new EventHandler<ActionEvent>() {

             @Override
             public void handle(ActionEvent event) {
//                 blockgenerator(p);
                 //Walls with Blocks
//                 wallgenerator(p,0);
                 tokengenerator(p,1);
             }

         }));


         len.setTextFill(Color.WHITE);
         p.setStyle("-fx-background-color: #000000;");
         fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
         fiveSecondsWonder.play();
         tSecondsWonder.setCycleCount(Timeline.INDEFINITE);
         tSecondsWonder.play();
         sevenSecondsWonder.setCycleCount(Timeline.INDEFINITE);
         sevenSecondsWonder.play();
         primaryStage.setResizable(false);
         Scene ga=new Scene(p,400, 500,Color.BLACK);
         ga.setOnKeyPressed(e ->{
             switch(e.getCode())
             {
                 case A:
                     moveleft(snake,len);
                     break;

                 case D:
                     moveright(snake,len);
                     break;
             }
         });
         return ga;

     }
}
