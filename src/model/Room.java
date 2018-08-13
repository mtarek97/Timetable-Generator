package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Room {
    private static int count = 0;
    private StringProperty  name;
    private IntegerProperty ID;

    public Room(String name){
        this.name = new SimpleStringProperty(name);
        ID = new SimpleIntegerProperty(count++);
    }

    public int getID() {
        return ID.get();
    }

    public String getName() {
        return name.get();
    }

    public StringProperty roomName() {
        return name;
    }

    public IntegerProperty roomID() {
        return ID;
    }

    @Override
    public String toString() {
        return getName();
    }
}
