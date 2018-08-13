package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentsGroup {
    private static int count = 0;
    private StringProperty name;
    private IntegerProperty ID;

    public StudentsGroup(String name){
        this.name = new SimpleStringProperty(name);
        ID = new SimpleIntegerProperty(count++);
    }

    public int getID() {
        return ID.get();
    }

    public String getName() {
        return name.get();
    }

    public StringProperty groupName() {
        return name;
    }

    public IntegerProperty groupID() {
        return ID;
    }

    @Override
    public String toString() {
        return  getName()/* + "_" + graduationYear*/;
    }
}
