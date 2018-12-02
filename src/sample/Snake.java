package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

/**
 * class holding the snake for the gameplay
 */
public class Snake {
    /**
     * arraylist of snake balls
     */
    ArrayList<Circle> snakeballs;
    /**
     * changing x coordiate
     */
    double posx;
    /**
     * constant y coordinate
     */
    static  double posy;
    /**
     * speed of the snake
     */
    int speed;
    /**
     * length of the snake
     */
    int length;
}
