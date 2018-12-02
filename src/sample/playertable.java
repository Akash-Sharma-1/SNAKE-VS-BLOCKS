package sample;

import javafx.beans.property.SimpleStringProperty;

public class playertable { SimpleStringProperty name;
    SimpleStringProperty score;
    SimpleStringProperty date;

    playertable(String nae, int scoe)
    {
        this.name=new SimpleStringProperty(nae);
        this.score=new SimpleStringProperty(Integer.toString(scoe));
        this.date=new SimpleStringProperty("today");
    }
    public String getName() {
        return name.get();
    }
    public String getDate() {
        return date.get();
    }
    public String getScore() {
        return score.get();
    }

}
