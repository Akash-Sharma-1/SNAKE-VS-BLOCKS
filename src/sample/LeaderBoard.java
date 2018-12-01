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

public class LeaderBoard {

    public VBox leaderboardscene(Stage primaryStage, Scene scene) {
        TableView table = new TableView();
        Label label=new Label("Welcome to the LeaderBoard");
        label.setFont(new Font("Arial", 20));
        table.setEditable(true);
//        tableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
//
//        ObservableList<String> items = FXCollections.observableArrayList("Itachi");
//        table.setItems(items);

        TableColumn firstNameCol = new TableColumn("Name");
//        firstNameCol.setCellValueFactory(new PropertyValueFactory<Person,String>("firstName"));

        TableColumn scoreCol = new TableColumn("Score");
        TableColumn dateCol = new TableColumn("Date");
//        scoreCol.setCellValueFactory(
//                new PropertyValueFactory<Person,String>("lastName")
//        );
//        dateCol.setCellValueFactory(
//                new PropertyValueFactory<Person,String>("email")
//        );
        table.getColumns().addAll(firstNameCol, scoreCol, dateCol);

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
}
