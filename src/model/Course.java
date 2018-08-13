package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Course {
    private StringProperty name;
    private StringProperty code;

    public Course(String name, String code){
        this.name = new SimpleStringProperty(name);
        this.code = new SimpleStringProperty(code);
    }

    public StringProperty courseName() {
        return name;
    }

    public StringProperty courseCode() {
        return code;
    }

    public String getName(){
        return name.get();
    }

    public String getCode(){
        return code.get();
    }

    @Override
    public String toString() {
        return getName() + " " + getCode();
    }
}