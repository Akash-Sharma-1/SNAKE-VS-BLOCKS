package sample;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * This class contains the combination of text on top of an rectangle
 * */
public class textrect {
    /**
     * text for the block
     */
    Text s;
    /**
     * rectangle shape object
     */
    Rectangle r;
    /**
     * stackpane to stack them
     */
    StackPane st;

    /**
     *
     * @param r rectangle object
     * @param s text object
     * @param p pane to which it should be added
     */
    textrect(Rectangle r, Text s, Pane p)
    {
        this.r=r;
        this.s=s;
        this.st=new StackPane();
        s.setFont(Font.font ("Tahoma", 20));
        st.getChildren().addAll(r,s);
        p.getChildren().add(st);
    }
}
