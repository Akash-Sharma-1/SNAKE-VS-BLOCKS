package sample;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;



/**
 * class with leaderboard- list of all the players name and stats
 */
public class LeaderBoard implements Serializable {
    ObservableList<playertable> items;
    ArrayList<String> stringserialize=new ArrayList<String>();
    ArrayList<Integer> intserialize=new ArrayList<Integer>();

    /**
     * Leaderboard setup function
     * @param primaryStage stage on which it will be used
     * @param scene  scene on which it will be used
     * @return vertical box to be added
     */


    public VBox leaderboardscene(Stage primaryStage, Scene scene) {
        TableView table = new TableView();
        Label label=new Label("Welcome to the LeaderBoard");
        label.setFont(new Font("Arial", 20));
        table.setEditable(true);
        TableColumn firstNameCol = new TableColumn("Name");
        TableColumn scoreCol = new TableColumn("Score");
        TableColumn dateCol = new TableColumn("Date");
        table.getColumns().addAll(firstNameCol, scoreCol, dateCol);





        firstNameCol.setCellValueFactory(new PropertyValueFactory<playertable,String>("name"));


        scoreCol.setCellValueFactory(
                new PropertyValueFactory<playertable,String>("score")
        );
        dateCol.setCellValueFactory(
                new PropertyValueFactory<playertable,String>("date")
        );
        try
        {
            table.getItems().addAll(items);

        }
        catch(Exception e)
        {

        }


        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        Button gobacktohome= new Button("Go Back to Home");
        gobacktohome.setOnAction(e -> {
            primaryStage.setScene(scene);
            primaryStage.setTitle("SNAKE VS BLOCK");
        });
        vbox.getChildren().addAll(label, table,gobacktohome);
        return vbox;
    }
    public void addplayer(player p)
    {
        this.stringserialize.add(p.name);
        this.intserialize.add(p.score);

        try
        {
            this.items.add(new playertable(p.name,p.score));

        }
        catch (Exception e)
        {
            this.items=FXCollections.observableArrayList(new playertable(p.name,p.score));
        }
    }

    public String serialiser(ArrayList<String> pp,String filename)
    {
        try
        {

        FileOutputStream file=new FileOutputStream(filename);
        ObjectOutputStream output=new ObjectOutputStream(file);
        output.writeObject(pp);
        output.close();
        }
        catch(IOException ex)
        {
//            System.out.println("IOException is caught");
        }
        return filename;
    }
    public String scoreserialiser(ArrayList<Integer> pp,String filename)
    {
        try
        {

            FileOutputStream file=new FileOutputStream(filename);
            ObjectOutputStream output=new ObjectOutputStream(file);
            output.writeObject(pp);
            output.close();
        }
        catch(IOException ex)
        {
//            System.out.println("IOException is caught");
        }
        return filename;
    }
    public ArrayList<String> deserialiser(String filename)
    {
        //deserialising the database
        ArrayList<String> x=null;
        try
        {
            System.out.println("Object has been deserialized ");
            FileInputStream file=new FileInputStream(filename);
            ObjectInputStream input=new ObjectInputStream(file);
                x=(ArrayList<String> ) input.readObject();
            input.close();
            file.close();
//            System.out.println(obj.getrev());

        }
        catch(IOException ex)
        {
//            System.out.println("IOException is caught");
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }

        return x;
    }
    public ArrayList<Integer> scoredeserialiser(String filename)
    {
        //deserialising the database
        ArrayList<Integer> x=null;
        try
        {
            System.out.println("Object has been deserialized ");
            FileInputStream file=new FileInputStream(filename);
            ObjectInputStream input=new ObjectInputStream(file);
            x=(ArrayList<Integer> ) input.readObject();
            input.close();
            file.close();
//            System.out.println(obj.getrev());

        }
        catch(IOException ex)
        {
//            System.out.println("IOException is caught");
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }

        return x;
    }
}
