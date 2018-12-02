package sample;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;


/**
 *@author AKASH SHARMA
 *@version 39.1
 *
 */

/**
 * This class holds the actual gameplay including blocks, walls and other gui components.
 */
public class GamePlay{
    /**
     * Player who is playing this object
     */
     player currentplayer;

    /**
     *
     * @param p Player for this gameplay
     */
     GamePlay(player p)
     {
         currentplayer=p;
     }

    /**
     *GENERATES BLOCKS FOR THE SCREEN PERIODICALLY AND TRANSLATES THEM
     * @param p Pane on which blocks will be generated
     * @param blocks Arraylist of all the blocks on the current screen
     * @param snake Arraylist of all the balls of the snake
     * @param blockss Arraylist of all the texted-blocks on the current screen (for decreasign their weights)
     * @param trans ArrayList holding all the transitions of all the blocks
     * @param checkblock HashMap holding all the block's status if collided or not
     */
     public void blockgenerator(Pane p,ArrayList<textrect> blocks,ArrayList<Circle> snake,ArrayList<textrect> blockss,ArrayList<TranslateTransition> trans,HashMap<Integer,Boolean> checkblock)
     {
         Random r=new Random();
         int noblock=(r.nextInt(3)+1);
//          noblock=1;
         ArrayList<Integer> list = new ArrayList<Integer>();
         for (int i=0; i<5; i++) {
             list.add(i);
         }

         Rectangle b=new Rectangle(78,78);

         if(noblock==1)
         {

             int pos=(r.nextInt(4));
             b.setX(pos*80);
             b.setY(-120);
             b.setArcWidth(20);
             b.setArcHeight(20);
             double rr = r.nextDouble();
             double gg = r.nextDouble();
             double bb = r.nextDouble();
             double oo = r.nextDouble();
             Color rectangleColor = new Color(rr, gg, bb,1);
             b.setFill(rectangleColor);
             Text t=new Text("21");

             t.setX(pos*80);
             t.setY(-120);
             Random bn=new Random();

             int blockno=0;
             int sb=bn.nextInt(2);
             if(sb==0) blockno=bn.nextInt(snake.size())+2;
             else blockno=bn.nextInt(snake.size()+5)+2;
             textrect q=new textrect(b,new Text(Integer.toString(blockno)),p);
             q.st.setLayoutX(pos*80);
             q.st.setLayoutY(-120);
             blocks.add(q);
             blockss.add(q);
             checkblock.put(blockss.size()-1,false);
//             p.getChildren().addAll(blocks);


         }
         else
         {
             int cnt=0;
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
//                 Text t=new Text("21");
//                 t.setX(pos*80);
//                 t.setY(-120);
                 Random bn=new Random();
                 int blockno=0;
                 int sb=bn.nextInt(2);
                 if(sb==0) blockno=bn.nextInt(snake.size()+8)+2;
                 else blockno=bn.nextInt(snake.size())+2;
                 if(blockno>snake.size())
                 {
                    cnt++;
                 }
                 if(cnt>2)
                 {
                     blockno=bn.nextInt(snake.size())+1;

                 }
                 textrect q=new textrect(x,new Text(Integer.toString(blockno)),p);
                 q.st.setLayoutX(pos*80);
                 q.st.setLayoutY(-120);
                 blocks.add(q);
                 blockss.add(q);
                 checkblock.put(blockss.size()-1,false);

//                 System.out.println(blocks);

             }
//             p.getChildren().addAll(blocks);

         }
         for(int i=0;i<noblock;i++)
         {
             TranslateTransition transition=new TranslateTransition();
             transition.setDuration(Duration.seconds(6));
//             transition.setInterpolator(Interpolator.LINEAR);
             transition.setToY(630);
             transition.setNode(blocks.get(i).st);
             transition.play();
             trans.add(transition);
         }



     }

    /**
     * GENERATES WALLS FOR THE SCREEN PERIODICALLY AND TRANSLATES THEM
     * @param p Pane on which all the walls will be generated on
     * @param ii Indicates the timeline it originates from 3sec or 5 sec
     * @param blocks Arraylist of all the blocks on the current screen for avoiding collision
     * @param walls Arraylist of all the walls on the current screen
     * @param walltrans Arraylist of all the wall's transitions on the current screen
     */
    public void wallgenerator(Pane p,int ii,ArrayList<Rectangle> blocks,ArrayList<Rectangle> walls,ArrayList<TranslateTransition> walltrans) {
        Random r = new Random();
        int noblock = (r.nextInt(2) + 1);

        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
//        ArrayList<Rectangle> blocks = new ArrayList<Rectangle>();


        if (noblock == 1) {

            int size=0;
            int length=0;
            if(ii==0)
            {
                length=(r.nextInt(40)+80);

            }
            else
            {
                length=(r.nextInt(20)+40);


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
            walls.add(b);

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

                }
                else
                {
                    length=((r.nextInt(2)*10)+50);

                }
                Rectangle x = new Rectangle(3, length);
                if(pos!=0)  x.setX((pos * 80)-2);
                else x.setX(0);
                x.setY(-1*(length));


                x.setFill(Color.WHITE);
                blocks.add(x);
                walls.add(x);

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
            walltrans.add(transition);
        }

    }

    /**
     * GENERATES TOKENS FOR THE SCREEN PERIODICALLY AND TRANSLATES THEM
     * @param p Pane on which tokens will be generated on
     * @param ii Indicates the timeline it originates from 3sec or 5 sec
     * @param tokens Arraylist of all the tokens on the current screen
     * @param tokensout Arraylist of all the token's types on the current screen
     * @param tokentrans Arraylist of all the token's transition on the current screen
     */
    public void tokengenerator(Pane p,int ii,ArrayList<Circle> tokens, ArrayList<Integer> tokensout,ArrayList<TranslateTransition> tokentrans)
    {
//        ArrayList<Rectangle> tokens=new ArrayList<Rectangle>();
        Random r = new Random();
            int x=(int)(Math.random()*4);
            if(ii==0) x=0;
            int xx=(r.nextInt()+80);
            int pos=r.nextInt(5)+1;
            Circle c=new Circle();
            if(x==0)
            {
                c=new Circle(((pos*80)-40),-18,9, Color.YELLOW);
//                else c=new Circle(((pos*80)-40),-18,9, Color.RED);
                p.getChildren().add(c);

            }
            if(x==1)
            {
                Image image=null;
                try
                {
                    image = new Image(new FileInputStream("src/sample/magnet.jpg"));
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
                    image = new Image(new FileInputStream("src/sample/shield.jpg"));
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
                    image = new Image(new FileInputStream("src/sample/bomb.jpg"));
                    if(ii==0)c=new Circle(((pos*80)-40),-18,9);
                    else c=new Circle(((pos*80)-40),0,9);
                    c.setFill(new ImagePattern(image));
                    p.getChildren().add(c);

                }
                catch(Exception e)
                {

                }
            }
            tokens.add(c);
            tokensout.add(x);
            TranslateTransition transition=new TranslateTransition();
            transition.setDuration(Duration.seconds(6));
            if(ii==0)transition.setToY(632);
            else transition.setToY(620);
            transition.setNode(c);
            transition.play();
            tokentrans.add(transition);


    }

    /**
     * MOVES THE SNAKE TOWARDS LEFT
     * @param snake Arraylist of snake balls
     * @param l Label indicating the length of the snake
     */
     public void moveleft(ArrayList<Circle> snake,Label l)
     {
         l.setTranslateX(l.getTranslateX() - 20);
         for(int i=0;i<snake.size();i++) {
             snake.get(i).setTranslateX(snake.get(i).getTranslateX() - 20);
         }
     }
    /**
     * MOVES THE SNAKE TOWARDS RIGHT
     * @param snake Arraylist of snake balls
     * @param l Label indicating the length of the snake
     */
     public void moveright(ArrayList<Circle> snake,Label l)
     {
         l.setTranslateX(l.getTranslateX() + 20);
         for(int i=0;i<snake.size();i++) {
             snake.get(i).setTranslateX(snake.get(i).getTranslateX()+20);
         }
     }

    /**
     * INCREMENTING SNAKES LENGTH
     * @param snake Arraylist of snake balls
     * @param l Label indicating the length of the snake
     * @param p Pane on which snake is attached to
     */
     public void addlength(ArrayList<Circle> snake,Label l,Pane p)
     {
        double y=snake.get(snake.size()-1).getCenterY()+14;
        double x;
//        if(snake.size()!=5)  x=snake.get(0).getTranslateX();
         x=l.getTranslateX()+200;
         Circle c=new Circle();
         c.setTranslateX(x);
         c.setCenterY(y);
         c.setRadius(7);
         c.setFill(Color.YELLOW);
        snake.add(c);
        String text=Integer.toString(snake.size());
        l.setText(text);
        p.getChildren().removeAll(snake);
        p.getChildren().addAll(snake);

     }

    /**
     * DECREMENTING SNAKES LENGTH
     * @param snake Arraylist of snake balls
     * @param l Label indicating the length of the snake
     * @param p Pane on which snake is attached to
     * @param value the weight by which it will be decreased
     * @param s StackPane of the block it collides with
     */
    public void sublength(ArrayList<Circle> snake,Label l,Pane p, int value, StackPane s)
    {
        for(int i=0;i<value;i++)
        {
            if(snake.size()==0) return;
            p.getChildren().removeAll(snake);
            snake.remove(snake.size()-1);
            String text=Integer.toString(snake.size());
            l.setText(text);

        }


    }

    /**
     * Function indicating the existence of shield power
     * @param s shield token object
     */
    public void gotshield(shield s)
    {
        s.shieldexist=true;
    }

    /**
     * Restarts the game
     * @param block Timeline generating blocks
     * @param wall Timeline generating walls
     * @param token Timeline generating tokens
     * @param blockss Arraylist of all the blocks on the current screen
     * @param tokens11 Arraylist of all the tokens on the current screen from 1st timeline
     * @param tokens22 Arraylist of all the tokens on the current screen from 2nd timeline
     * @param walls11 Arraylist of all the walls on the current screen from 1st timeline
     * @param walls22 Arraylist of all the walls on the current screen from 2nd timeline
     * @param p Pane on which all this will re-added on
     */
    public void restarter(Timeline block,Timeline wall,Timeline token,ArrayList<textrect> blockss,ArrayList<Circle> tokens11,ArrayList<Circle> tokens22,ArrayList<Rectangle> walls11,ArrayList<Rectangle> walls22,Pane p)
    {
        block.pause();
        wall.pause();
        token.pause();
        for(int i=0;i<blockss.size();i++)
        {
            p.getChildren().remove(blockss.get(i).st);

        }
        for(int i=0;i<tokens11.size();i++)
        {
            p.getChildren().remove(tokens11.get(i));
        }
        for(int i=0;i<tokens22.size();i++)
        {
            p.getChildren().remove(tokens22.get(i));

        }
        for(int i=0;i<walls11.size();i++)
        {
            p.getChildren().remove(walls11.get(i));

        }
        for(int i=0;i<walls22.size();i++)
        {
            p.getChildren().remove(walls22.get(i));

        }
        block.play();
        wall.play();
        token.play();
    }

    /**
     * Pauses the gameplay
     * @param block Timeline generating blocks
     * @param wall  Timeline generating walls
     * @param token Timeline generating tokens
     * @param blockss Arraylist of all the blocks' translations on the current screen
     * @param tokens11 Arraylist of all the tokens' translations on the current screen
     * @param walls11 Arraylist of all the walls' translations on the current screen
     * @param p Pane on which all this will re-added on
     */
    public void pauser(Timeline block,Timeline wall,Timeline token,ArrayList<TranslateTransition> blockss,ArrayList<TranslateTransition> tokens11,ArrayList<TranslateTransition> walls11,paused p)
    {
        if(p.pauseorplay)
        {
            block.pause();
            wall.pause();
            token.pause();
            for(int i=0;i<blockss.size();i++)
            {
                blockss.get(i).pause();

            }
            for(int i=0;i<tokens11.size();i++)
            {
                tokens11.get(i).pause();
            }

            for(int i=0;i<walls11.size();i++)
            {
                walls11.get(i).pause();

            }
            p.pauseorplay=false;
        }
        else
        {
            block.play();
            wall.play();
            token.play();
            for(int i=0;i<blockss.size();i++)
            {
                blockss.get(i).play();

            }
            for(int i=0;i<tokens11.size();i++)
            {
                tokens11.get(i).play();
            }

            for(int i=0;i<walls11.size();i++)
            {
                walls11.get(i).play();

            }
            p.pauseorplay=true;


        }


    }

    /**
     * The main function of this class which returns the scene with all the things added
     * and acts as a mediator between home page and the gameplay page
     * @param home home page scene
     * @param primaryStage current stage on which the home scene will
     * @param root homepage group on which components were added
     * @return scene with the main pane added
     */
     public Scene game(Scene home, Stage primaryStage, Group root)
     {
         paused pausecheck=new paused();
         Pane p=new Pane();
         Label score = new Label("Score :");
         int scoreval=0;
         shield to=new shield();
         endgame isgameover=new endgame();
         isgameover.end=false;
         snake_dir directiondetector=new snake_dir();
         MenuItem menuItem1 = new MenuItem("EXIT");
         MenuItem menuItem2 = new MenuItem("RESTART");
         MenuItem menuItem3= new MenuItem("PAUSE/PLAY");
//         if(pausecheck.pauseorplay)  menuItem3
//         else menuItem3= new MenuItem("PLAY");

         MenuButton menuButton = new MenuButton("Options", null, menuItem1, menuItem2,menuItem3);
         menuButton.setLayoutX(320);
         menuButton.setLayoutY(460);
         score.setLayoutY(460);
         score.setTextFill(Color.WHITE);
         Label val=new Label("0");
         val.setLayoutX(score.getLayoutX()+30);
         val.setLayoutY(score.getLayoutY());
         val.setTextFill(Color.WHITE);

         p.getChildren().addAll(menuButton,score,val);
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
         Rectangle rr=new Rectangle(80,80);


         p.getChildren().addAll(snake);
         p.getChildren().add(len);
         ArrayList<textrect> blockss= new ArrayList<textrect>();
         ArrayList<Circle> tokens11= new ArrayList<Circle>();
         ArrayList<Integer> token1type= new ArrayList<Integer>();
         ArrayList<Rectangle> walls11= new ArrayList<Rectangle>();
         ArrayList<Rectangle> walls22= new ArrayList<Rectangle>();
         ArrayList<Circle> tokens22= new ArrayList<Circle>();
         ArrayList<Integer> token2type= new ArrayList<Integer>();

         ArrayList<TranslateTransition> blockstransition=new ArrayList<TranslateTransition>();
         ArrayList<TranslateTransition> wallstransition=new ArrayList<TranslateTransition>();
         ArrayList<TranslateTransition> tokenstransition=new ArrayList<TranslateTransition>();
         HashMap<Integer,Boolean> checkblock=new HashMap<Integer,Boolean>();

         Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(3.4), new EventHandler<ActionEvent>() {

             @Override
             public void handle(ActionEvent event) {
                 ArrayList<textrect> blocks= new ArrayList<textrect>();
                 ArrayList<Rectangle> wall1= new ArrayList<Rectangle>();

                 blockgenerator(p,blocks,snake,blockss,blockstransition,checkblock);
                 //Walls with Blocks
                 wallgenerator(p,0,wall1,walls11,wallstransition);
                 tokengenerator(p,0,tokens11,token1type,tokenstransition);
                 System.out.println(checkblock);


             }


         }));

//         for(int i=0;i<blockss.size();i++) {
//             while(blockss.get(i).getTranslateY()<=260)
//             {
//                 System.out.println(blockss.get(i).getY());
////
//             }

//
//
//         }


         Timeline tSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {

             @Override
             public void handle(ActionEvent event) {
                 //middle wall
                 ArrayList<Rectangle> wall2= new ArrayList<Rectangle>();

                 wallgenerator(p,1,wall2,walls22,wallstransition);
             }

         }));

         Timeline sevenSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(5.1), new EventHandler<ActionEvent>() {

             @Override
             public void handle(ActionEvent event) {
//                 blockgenerator(p);
                 //Walls with Blocks
//                 wallgenerator(p,0);
                 tokengenerator(p,1,tokens22,token2type,tokenstransition);
//                 System.out.println(snake.get(0).getTranslateX());


             }

         }));

//         Timeline wallchecker = new Timeline(new KeyFrame(Duration.seconds(200), new EventHandler<ActionEvent>() {
//
//             @Override
//             public void handle(ActionEvent event) {
////                 System.out.println("yo");
//
//
//
////                 p.getChildren().addAll(snake);
//
//
//
//             }
//
//         }));

         Timeline checker = new Timeline(new KeyFrame(Duration.millis(400), new EventHandler<ActionEvent>() {
                int a=0;
             @Override
             public void handle(ActionEvent event) {
//                 System.out.println(snake.get(0).getTranslateX()+200);

                 for(int j=0;j<walls11.size();j++)
                 {
//                     System.out.println(walls11.get(j).getX());

                     for(int i=0;i<snake.size();i++)
                     {
                         if(snake.get(i).getBoundsInParent().intersects(walls11.get(j).getBoundsInParent()))
                         {
                             System.out.println(walls11.get(j).getX());
                             System.out.println(snake.get(i).getTranslateX());
                             if(directiondetector.direction==0)
                             {
                                 moveleft(snake,len);
                                 directiondetector.direction=1;
                             }
                             else
                             {
                                 moveright(snake,len);
                                 directiondetector.direction=0;


                             }
                             System.out.println("collided");
                         }

                     }
                 }
                 for(int j=0;j<walls22.size();j++)
                 {
//                     System.out.println(walls22.get(j).getX());

                     for(int i=0;i<snake.size();i++)
                     {
                         if(snake.get(i).getBoundsInParent().intersects(walls22.get(j).getBoundsInParent()))
                         {
                             System.out.println(walls22.get(j).getX());
                             System.out.println(snake.get(i).getTranslateX()+200);
                             if(directiondetector.direction==0)
                             {
                                 moveleft(snake,len);
                                 directiondetector.direction=1;
                             }
                             else
                             {
                                 moveright(snake,len);
                                 directiondetector.direction=0;


                             }
                             System.out.println("collided");
                         }

                     }
                 }
                 for(int i=0;i<blockss.size();i++)
                 {
                     if(snake.get(0).getBoundsInParent().intersects(blockss.get(i).st.getBoundsInParent()))
                     {
                         p.getChildren().remove(blockss.get(i).st);


                         if(Integer.parseInt(blockss.get(i).s.getText())>=snake.size())
                         {
                             if(to.shieldexist==true)
                             {
                                 for(int l=0;l<5;l++) {
                                     Random rr = new Random();
                                     Circle c = new Circle(5);
                                     c.setFill(Color.WHITE);
                                     int lr = rr.nextInt(100);
                                     if (lr % 2 == 0) c.setCenterX(blockss.get(i).r.getX() + rr.nextInt(40));
                                     else c.setCenterX(blockss.get(i).r.getX() - rr.nextInt(40)+20);
                                     c.setCenterY(blockss.get(i).st.getTranslateY() - 80+rr.nextInt(40)-20);
                                     double rrr = rr.nextDouble();
                                     double gg = rr.nextDouble();
                                     double bb = rr.nextDouble();
                                     double oo = rr.nextDouble();
                                     Color rectangleColor = new Color(rrr, gg, bb, 1);
                                     c.setFill(rectangleColor);
                                     c.setFill(Color.DARKRED);
                                     p.getChildren().add(c);
                                     TranslateTransition transition = new TranslateTransition();
                                     transition.setDuration(Duration.seconds(3));
                                     lr = rr.nextInt(100);
                                     if (lr % 2 == 0) transition.setToY(c.getCenterY() + rr.nextInt(5));
                                     else transition.setToY(c.getCenterY() - rr.nextInt(5));
                                     lr = rr.nextInt(100);
                                     if (lr % 2 == 0) transition.setToX(c.getCenterX() + rr.nextInt(5));
                                     else transition.setToX(c.getCenterX() - rr.nextInt(5));
                                     transition.setNode(c);
                                     transition.play();
                                     FadeTransition ft = new FadeTransition(Duration.seconds(2), c);
                                     ft.setFromValue(1.0);
                                     ft.setToValue(0.0);
                                     ft.play();
                                     ScaleTransition st = new ScaleTransition(Duration.seconds(2), c);
                                     st.setFromX(1f);
                                     st.setFromY(1f);
                                     st.setToX(4f);
                                     st.setToY(4f);
                                     st.play();

                                 }
                                 currentplayer.score+=Integer.parseInt(blockss.get(i).s.getText());
                                 val.setText(Integer.toString(currentplayer.score));
                             }

                             else
                             {
                                 if(checkblock.get(i)==false) {
                                     System.out.println("saas");

//                                 p.getChildren().remove(blockss.get(i).st);
//
//                                 //game over - since the snake collided with a bigger block
//                                 //game over page
////                                 return ;
                                 Stage s=new Stage();
                                 Label gameover=new Label("OOPS! GAME OVER");
                                 gameover.setLayoutX(50);
                                 gameover.setLayoutY(50);
                                     gameover.setTextFill(Color.DARKOLIVEGREEN);
                                     gameover.setFont(Font.font("Cambria", 20));
                                     gameover.setStyle("-fx-font-weight: bold");

                                     Label well_played=new Label("WELL PLAYED , "+currentplayer.name+" !");
                                 well_played.setLayoutX(50);
                                 well_played.setLayoutY(100);
                                     well_played.setTextFill(Color.MEDIUMVIOLETRED);
                                     well_played.setFont(Font.font("Cambria", 20));
                                     well_played.setStyle("-fx-font-weight: bold");

                                     Label fscore=new Label("YOUR FINAL SCORE IS "+Integer.toString(currentplayer.score));
                                 fscore.setLayoutX(50);
                                 fscore.setLayoutY(150);
                                     fscore.setStyle("-fx-font-weight: bold");

                                     fscore.setTextFill(Color.GREEN);
                                     fscore.setFont(Font.font("Cambria", 20));
                                 Pane paner=new Pane();
                                 paner.setStyle("-fx-background-color: #F6DBC7;");

                                 Scene ga=new Scene(paner,300, 300);
                                 s.setScene(ga);
                                 s.show();
                                 Button b=new Button("RETURN TO HOME");
                                 b.setLayoutX(100);
                                 b.setLayoutY(200);
                                 b.setOnAction(ee->{
                                     s.close();
                                     primaryStage.setTitle("Snake vs Block - Welcome Back!");
                                     primaryStage.setScene(home);

                                 });
                                 paner.getChildren().addAll(gameover,well_played,fscore,b);

                                 isgameover.end=true;
                                 return;
                                     //current player object has the current score
                                 }

                             }

                         }
                         else {
//                             p.getChildren().remove(blockss.get(i).st);
                             if (checkblock.get(i)==false) {
                                 for (int l = 0; l < 5; l++) {
                                     Random rr = new Random();
                                     Circle c = new Circle(5);
                                     c.setFill(Color.WHITE);
                                     int lr = rr.nextInt(100);
                                     if (lr % 2 == 0) c.setCenterX(blockss.get(i).r.getX() + rr.nextInt(40));
                                     else c.setCenterX(blockss.get(i).r.getX() - rr.nextInt(40) + 20);
                                     c.setCenterY(blockss.get(i).st.getTranslateY() - 80 + rr.nextInt(40) - 20);
                                     double rrr = rr.nextDouble();
                                     double gg = rr.nextDouble();
                                     double bb = rr.nextDouble();
                                     double oo = rr.nextDouble();
                                     Color rectangleColor = new Color(rrr, gg, bb, 1);
                                     c.setFill(rectangleColor);
                                     p.getChildren().add(c);
                                     TranslateTransition transition = new TranslateTransition();
                                     transition.setDuration(Duration.seconds(3));
                                     lr = rr.nextInt(100);
                                     if (lr % 2 == 0) transition.setToY(c.getCenterY() + rr.nextInt(5));
                                     else transition.setToY(c.getCenterY() - rr.nextInt(5));
                                     lr = rr.nextInt(100);
                                     if (lr % 2 == 0) transition.setToX(c.getCenterX() + rr.nextInt(5));
                                     else transition.setToX(c.getCenterX() - rr.nextInt(5));
                                     transition.setNode(c);
                                     transition.play();
                                     FadeTransition ft = new FadeTransition(Duration.seconds(2), c);
                                     ft.setFromValue(1.0);
                                     ft.setToValue(0.0);
                                     ft.play();
                                     ScaleTransition st = new ScaleTransition(Duration.seconds(2), c);
                                     st.setFromX(1f);
                                     st.setFromY(1f);
                                     st.setToX(4f);
                                     st.setToY(4f);
                                     st.play();
                                 }
                                 if (to.shieldexist == true) {

                                 }
                                 else {
                                     sublength(snake, len, p, Integer.parseInt(blockss.get(i).s.getText()), blockss.get(i).st);
                                     p.getChildren().addAll(snake);

                                 }
//                             System.out.println();
                                 currentplayer.score += Integer.parseInt(blockss.get(i).s.getText());
                                 val.setText(Integer.toString(currentplayer.score));
                                 checkblock.put(i,true);
                                 return;

                             }
                         }
//                         else
//                         {
////                             sublength(snake,len,Integer.parseInt(blockss.get(i).s.getText()));
//                         }


//                         if(a.get(i)==0) new Thread(loadTask).start();
//                         a.set(i,1);
//                         p.getChildren().remove(blockss.get(i).st);
//                         for(int j=0;j<blockstransition.size();j++)
//                         {
//                             blockstransition.get(j).play();
//
//                         }
//                         for(int j=0;j<wallstransition.size();j++)
//                         {
//                             wallstransition.get(j).play();
//                         }
//                         for(int j=0;j<tokenstransition.size();j++)
//                         {
//                             tokenstransition.get(j).play();
//                         }

                     }

//                     System.out.println(to.shieldexist);
                 }


             }

         }));



         Timeline tokencheck = new Timeline(new KeyFrame(Duration.millis(220), new EventHandler<ActionEvent>() {

             @Override
             public void handle(ActionEvent event) {
                 for(int i=0;i<tokens11.size();i++)
                 {
                     if(snake.get(0).getBoundsInParent().intersects(tokens11.get(i).getBoundsInParent()))
                     {
                         p.getChildren().remove(tokens11.get(i));

                         if(token1type.get(i)==0)
                         {
                             addlength(snake,len,p);
                         }


                     }
                 }
                 for(int i=0;i<tokens22.size();i++)
                 {
                     int cnt=0;
                     if(snake.get(0).getBoundsInParent().intersects(tokens22.get(i).getBoundsInParent())) {
                         if (token2type.get(i) == 0) {
                             addlength(snake, len, p);
                         }
                         if (token2type.get(i) == 1) {
                             for (int k = 0; k < tokens11.size(); k++) {
                                 System.out.println(tokens11.get(k).getTranslateY());
//                                 Circle c = new Circle(5);
//                                 c.setFill(Color.WHITE);
//                                 c.setCenterY(tokens11.get(k).getCenterX());
//                                 c.setCenterX(tokens11.get(k).getTranslateY());
//                                 p.getChildren().add(c);
                                 p.getChildren().remove(tokens11.get(k));

                                 for (int l = 0; l < 3; l++) {
                                     Random rr = new Random();
                                     Circle c = new Circle(2);
                                     c.setFill(Color.WHITE);
                                     int lr = rr.nextInt(100);
                                     if (lr % 2 == 0) c.setCenterX(tokens11.get(k).getCenterX() + rr.nextInt(40));
                                     else c.setCenterX(tokens11.get(k).getCenterX() - rr.nextInt(40) + 20);
                                     c.setCenterY(tokens11.get(k).getTranslateY() + rr.nextInt(40) - 20);
                                     double rrr = rr.nextDouble();
                                     double gg = rr.nextDouble();
                                     double bb = rr.nextDouble();
                                     double oo = rr.nextDouble();
                                     Color rectangleColor = new Color(rrr, gg, bb, 1);
                                     c.setFill(rectangleColor);
                                     c.setFill(Color.YELLOW);
                                     p.getChildren().add(c);
                                     TranslateTransition transition = new TranslateTransition();
                                     transition.setDuration(Duration.seconds(3));
                                     lr = rr.nextInt(100);
                                     if (lr % 2 == 0) transition.setToY(c.getCenterY() + rr.nextInt(2));
                                     else transition.setToY(c.getCenterY() - rr.nextInt(2));
                                     lr = rr.nextInt(100);
                                     if (lr % 2 == 0) transition.setToX(c.getCenterX() + rr.nextInt(2));
                                     else transition.setToX(c.getCenterX() - rr.nextInt(2));
                                     transition.setNode(c);
                                     transition.play();
                                     FadeTransition ft = new FadeTransition(Duration.seconds(2), c);
                                     ft.setFromValue(1.0);
                                     ft.setToValue(0.0);
                                     ft.play();
                                     ScaleTransition st = new ScaleTransition(Duration.seconds(2), c);
                                     st.setFromX(1f);
                                     st.setFromY(1f);
                                     st.setToX(4f);
                                     st.setToY(4f);
                                     st.play();
                                     cnt++;
                                 }

                             }
                             for (int k = 0; k < tokens22.size(); k++) {
                                 if (token2type.get(k) == 0) {
                                     p.getChildren().remove(tokens22.get(k));
//                                 System.out.println(tokens11.get(k).getCenterX());
                                     for (int l = 0; l < 3; l++) {

                                         Random rr = new Random();
                                         Circle c = new Circle(3);
                                         int lr = rr.nextInt(100);
                                         if (lr % 2 == 0) c.setCenterX(tokens22.get(k).getCenterX() + rr.nextInt(40));
                                         else c.setCenterX(tokens22.get(k).getCenterX() - rr.nextInt(40) + 20);
                                         c.setCenterY(tokens22.get(k).getTranslateY() + rr.nextInt(40) - 20);
                                         double rrr = rr.nextDouble();
                                         double gg = rr.nextDouble();
                                         double bb = rr.nextDouble();
                                         double oo = rr.nextDouble();
                                         Color rectangleColor = new Color(rrr, gg, bb, 1);
                                         c.setFill(Color.YELLOW);
                                         p.getChildren().add(c);
                                         TranslateTransition transition = new TranslateTransition();
                                         transition.setDuration(Duration.seconds(3));
                                         lr = rr.nextInt(100);
                                         if (lr % 2 == 0) transition.setToY(c.getCenterY() + rr.nextInt(2));
                                         else transition.setToY(c.getCenterY() - rr.nextInt(2));
                                         lr = rr.nextInt(100);
                                         if (lr % 2 == 0) transition.setToX(c.getCenterX() + rr.nextInt(2));
                                         else transition.setToX(c.getCenterX() - rr.nextInt(2));
                                         transition.setNode(c);
                                         transition.play();
                                         FadeTransition ft = new FadeTransition(Duration.seconds(2), c);
                                         ft.setFromValue(1.0);
                                         ft.setToValue(0.0);
                                         ft.play();
                                         ScaleTransition st = new ScaleTransition(Duration.seconds(2), c);
                                         st.setFromX(1f);
                                         st.setFromY(1f);
                                         st.setToX(4f);
                                         st.setToY(4f);
                                         st.play();
                                     }
                                     cnt++;

                                 }
                             }
                             addlength(snake, len, p);
                             addlength(snake, len, p);
//                         addlength(snake,len,p);
                         }




//                         sublength(snake,len,p,cnt/5,new StackPane());

                         p.getChildren().remove(tokens22.get(i));
                         if (token2type.get(i) == 2) {
//                             System.out.println("yo");
                             for (int ij = 0; ij < snake.size(); ij++) {
                                 FillTransition ft = new FillTransition(Duration.millis(5000), snake.get(ij), Color.YELLOW, Color.RED);
                                 ft.setCycleCount(2);
                                 ft.setAutoReverse(true);
                                 ft.play();
                             }
                             gotshield(to);
                             PauseTransition delay = new PauseTransition(Duration.seconds(5));
                             delay.setOnFinished( evente ->to.shieldexist=false);
                             delay.play();

                         }
                         if(token2type.get(i)==3)
                         {
                             for(int x=0;x<blockss.size();x++)
                             {
                                 p.getChildren().remove(blockss.get(x).st);
                                 for(int l=0;l<5;l++) {
                                     Random rr = new Random();
                                     Circle c = new Circle(5);
                                     c.setFill(Color.WHITE);
                                     int lr = rr.nextInt(100);
                                     if (lr % 2 == 0) c.setCenterX(blockss.get(x).r.getX() + rr.nextInt(40));
                                     else c.setCenterX(blockss.get(x).r.getX() - rr.nextInt(40)+20);
                                     c.setCenterY(blockss.get(x).st.getTranslateY() - 80+rr.nextInt(40)-20);
                                     double rrr = rr.nextDouble();
                                     double gg = rr.nextDouble();
                                     double bb = rr.nextDouble();
                                     double oo = rr.nextDouble();
                                     Color rectangleColor = new Color(rrr, gg, bb, 1);
                                     c.setFill(rectangleColor);
                                     if(l%2==0)c.setFill(Color.WHITE);
                                     else c.setFill(Color.ORANGE);
                                     p.getChildren().add(c);
                                     TranslateTransition transition = new TranslateTransition();
                                     transition.setDuration(Duration.seconds(3));
                                     lr = rr.nextInt(100);
                                     if (lr % 2 == 0) transition.setToY(c.getCenterY() + rr.nextInt(5));
                                     else transition.setToY(c.getCenterY() - rr.nextInt(5));
                                     lr = rr.nextInt(100);
                                     if (lr % 2 == 0) transition.setToX(c.getCenterX() + rr.nextInt(5));
                                     else transition.setToX(c.getCenterX() - rr.nextInt(5));
                                     transition.setNode(c);
                                     transition.play();
                                     FadeTransition ft = new FadeTransition(Duration.seconds(2), c);
                                     ft.setFromValue(1.0);
                                     ft.setToValue(0.0);
                                     ft.play();
                                     ScaleTransition st = new ScaleTransition(Duration.seconds(2), c);
                                     st.setFromX(1f);
                                     st.setFromY(1f);
                                     st.setToX(4f);
                                     st.setToY(4f);
                                     st.play();
                                 }
                             }
                             for(int x=0;x<walls11.size();x++)
                             {
                                 p.getChildren().remove(walls11.get(x));
                                 for(int l=0;l<3;l++) {
                                     Random rr = new Random();
                                     Circle c = new Circle(5);
                                     c.setFill(Color.WHITE);
                                     int lr = rr.nextInt(100);
                                     if (lr % 2 == 0) c.setCenterX(walls11.get(x).getX() + rr.nextInt(40));
                                     else c.setCenterX(walls11.get(x).getX() - rr.nextInt(40)+20);
                                     c.setCenterY(walls11.get(x).getTranslateY() - 80+rr.nextInt(40)-20);
                                     double rrr = rr.nextDouble();
                                     double gg = rr.nextDouble();
                                     double bb = rr.nextDouble();
                                     double oo = rr.nextDouble();
                                     Color rectangleColor = new Color(rrr, gg, bb, 1);
                                     c.setFill(rectangleColor);
                                     if(l%2==0)c.setFill(Color.WHITE);
                                     else c.setFill(Color.WHITE);
                                     p.getChildren().add(c);
                                     TranslateTransition transition = new TranslateTransition();
                                     transition.setDuration(Duration.seconds(3));
                                     lr = rr.nextInt(100);
                                     if (lr % 2 == 0) transition.setToY(c.getCenterY() + rr.nextInt(5));
                                     else transition.setToY(c.getCenterY() - rr.nextInt(5));
                                     lr = rr.nextInt(100);
                                     if (lr % 2 == 0) transition.setToX(c.getCenterX() + rr.nextInt(5));
                                     else transition.setToX(c.getCenterX() - rr.nextInt(5));
                                     transition.setNode(c);
                                     transition.play();
                                     FadeTransition ft = new FadeTransition(Duration.seconds(2), c);
                                     ft.setFromValue(1.0);
                                     ft.setToValue(0.0);
                                     ft.play();
                                     ScaleTransition st = new ScaleTransition(Duration.seconds(2), c);
                                     st.setFromX(1f);
                                     st.setFromY(1f);
                                     st.setToX(4f);
                                     st.setToY(4f);
                                     st.play();
                                 }
                             }
                             for(int x=0;x<walls22.size();x++)
                             {
                                 p.getChildren().remove(walls22.get(x));
                                 for(int l=0;l<3;l++) {
                                     Random rr = new Random();
                                     Circle c = new Circle(5);
                                     c.setFill(Color.WHITE);
                                     int lr = rr.nextInt(100);
                                     if (lr % 2 == 0) c.setCenterX(walls22.get(x).getX() + rr.nextInt(40));
                                     else c.setCenterX(walls22.get(x).getX() - rr.nextInt(40)+20);
                                     c.setCenterY(walls22.get(x).getTranslateY() - 80+rr.nextInt(40)-20);
                                     double rrr = rr.nextDouble();
                                     double gg = rr.nextDouble();
                                     double bb = rr.nextDouble();
                                     double oo = rr.nextDouble();
                                     Color rectangleColor = new Color(rrr, gg, bb, 1);
                                     c.setFill(rectangleColor);
                                     if(l%2==0)c.setFill(Color.WHITE);
                                     else c.setFill(Color.WHITE);
                                     p.getChildren().add(c);
                                     TranslateTransition transition = new TranslateTransition();
                                     transition.setDuration(Duration.seconds(3));
                                     lr = rr.nextInt(100);
                                     if (lr % 2 == 0) transition.setToY(c.getCenterY() + rr.nextInt(5));
                                     else transition.setToY(c.getCenterY() - rr.nextInt(5));
                                     lr = rr.nextInt(100);
                                     if (lr % 2 == 0) transition.setToX(c.getCenterX() + rr.nextInt(5));
                                     else transition.setToX(c.getCenterX() - rr.nextInt(5));
                                     transition.setNode(c);
                                     transition.play();
                                     FadeTransition ft = new FadeTransition(Duration.seconds(2), c);
                                     ft.setFromValue(1.0);
                                     ft.setToValue(0.0);
                                     ft.play();
                                     ScaleTransition st = new ScaleTransition(Duration.seconds(2), c);
                                     st.setFromX(1f);
                                     st.setFromY(1f);
                                     st.setToX(4f);
                                     st.setToY(4f);
                                     st.play();
                                 }
                             }
                         }
                     }
                  }
                 }
         }));

         Timeline end = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {

             @Override
             public void handle(ActionEvent event) {
//                 System.out.println(currentplayer.name);
                 if(isgameover.end==true)
                 {
                     fiveSecondsWonder.pause();
                     tSecondsWonder.pause();
                     sevenSecondsWonder.pause();
                     checker.pause();
                     tokencheck.pause();
                     Label l=new Label(Integer.toString(currentplayer.score));
                     l.setLayoutX(390);
                     l.setLayoutY(0);
                     l.setTextFill(Color.WHITE);
                     root.getChildren().add(l);

                 }

             }
         }));
         menuItem2.setOnAction(event -> {
             restarter(fiveSecondsWonder,tSecondsWonder,sevenSecondsWonder, blockss, tokens11, tokens22, walls11, walls22, p);
         });
         menuItem3.setOnAction(event -> {
             pauser(fiveSecondsWonder,tSecondsWonder,sevenSecondsWonder, blockstransition, tokenstransition, wallstransition,pausecheck);
         });
         len.setTextFill(Color.WHITE);
         p.setStyle("-fx-background-color: #000000;");
         end.setCycleCount(Timeline.INDEFINITE);
         end.play();
         fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
         fiveSecondsWonder.play();
         tSecondsWonder.setCycleCount(Timeline.INDEFINITE);
         tSecondsWonder.play();
         sevenSecondsWonder.setCycleCount(Timeline.INDEFINITE);
         sevenSecondsWonder.play();
         checker.setCycleCount(Timeline.INDEFINITE);
         checker.play();
         tokencheck.setCycleCount(Timeline.INDEFINITE);
         tokencheck.play();
//         wallchecker.setCycleCount(Timeline.INDEFINITE);
//         wallchecker.play();
         primaryStage.setResizable(false);
         Scene ga=new Scene(p,400, 500,Color.BLACK);
         ga.setOnKeyPressed(e ->{
             switch(e.getCode())
             {
                 case A:
                     moveleft(snake,len);
                     directiondetector.direction=1;
                     break;

                 case D:
                     moveright(snake,len);
                     directiondetector.direction=0;

                     break;
             }
         });
         return ga;

     }
}
