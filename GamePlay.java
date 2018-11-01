package sample;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class GamePlay{

//     class Sprite extends Circle
//     {
//
//     }
     public void moveleft(Circle c)
     {
         c.setTranslateX(c.getTranslateX()-5);
     }
     public void moveright(Circle c)
     {
         c.setTranslateX(c.getTranslateX()+5);
     }
     public Scene game()
     {
         Pane p=new Pane();
         ArrayList<Circle> snake=new ArrayList<Circle>();
         int point=200;
         for(int i=0;i<5;i++)
         {
             snake.add(new Circle(point,point,10, Color.BLUE));
             point+=10;
         }


         p.getChildren().add(snake);
         Scene ga=new Scene(p,400, 500);
         ga.setOnKeyPressed(e ->{
             switch(e.getCode())
             {
                 case A:
                     moveleft(c);
                     break;

                 case D:
                     moveright(c);
                     break;
             }
         });
         return ga;

     }
}
