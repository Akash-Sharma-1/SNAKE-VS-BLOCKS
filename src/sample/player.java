package sample;

import java.util.Calendar;
import java.util.Date;

/**
 * player class for each player for a gameplay
 */
public class player {
    /**
     * name of the player
     */
    String name;
    /**
     * integer score of the player
     */
    int score;
    /**
     * date of the gameplay
     */
    Date d;

    /**
     *
     * @param n name of player
     * @param i final score of the player
     */
    player(String n,int i)
    {
        name=n;
        score=i;
        d= Calendar.getInstance().getTime();

    }
}
