package sample;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GamePlay{

     public void blockgenerator(Pane p)
     {
         Random r=new Random();
         int noblock=(r.nextInt(3)+1);
//         int noblock=2;
         ArrayList<Integer> list = new ArrayList<Integer>();
         for (int i=0; i<5; i++) {
             list.add(i);
         }
         Rectangle b=new Rectangle(78,78);
         if(noblock==1)
         {
             int pos=(r.nextInt(4));
             b.setLayoutX(pos*80);
             double rr = r.nextDouble();
             double gg = r.nextDouble();
             double bb = r.nextDouble();
             double oo = r.nextDouble();
             Color rectangleColor = new Color(rr, gg, bb,1);
             b.setFill(rectangleColor);
             p.getChildren().add(b);



         }
         else
         {
             Collections.shuffle(list);
             ArrayList<Rectangle> blocks= new ArrayList<Rectangle>();
             for(int i=0;i<noblock;i++)
             {
                 int pos=list.get(i);
//                 System.out.println(i);
                 Rectangle x=new Rectangle(78,78);
                 x.setX(pos*80);
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




     }

    public void wallgenerator(Pane p) {
        Random r = new Random();
        int noblock = (r.nextInt(3) + 1);
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }

        Rectangle b = new Rectangle(3, 150);
        if (noblock == 1) {
            int pos = (r.nextInt(4));
            b.setLayoutX((pos *80)-20);
            b.setFill(Color.WHITE);
            p.getChildren().add(b);


        } else {
            Collections.shuffle(list);
            ArrayList<Rectangle> blocks = new ArrayList<Rectangle>();
            for (int i = 0; i < noblock; i++) {
                int pos = list.get(i);
//                 System.out.println(i);
                Rectangle x = new Rectangle(3, 150);
                x.setX((pos * 80)-2);
                x.setFill(Color.WHITE);
                blocks.add(x);
//                 System.out.println(blocks);

            }
            p.getChildren().addAll(blocks);

        }

    }
     public void moveleft(ArrayList<Circle> snake,Label l)
     {
         l.setTranslateX(l.getTranslateX() - 10);
         for(int i=0;i<5;i++) {
             snake.get(i).setTranslateX(snake.get(i).getTranslateX() - 10);
         }
     }
     public void moveright(ArrayList<Circle> snake,Label l)
     {
         l.setTranslateX(l.getTranslateX() + 10);
         for(int i=0;i<5;i++) {
             snake.get(i).setTranslateX(snake.get(i).getTranslateX()+10);
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
             snake.add(new Circle(200,point,10, Color.YELLOW));
             point+=20;
         }


         p.getChildren().addAll(snake);
         p.getChildren().add(len);
         blockgenerator(p);
         wallgenerator(p);
         p.setStyle("-fx-background-color: #000000;");
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
