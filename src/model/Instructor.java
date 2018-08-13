package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Instructor {
    private static int count = 0;
    private IntegerProperty ID;
    private StringProperty fullname;
    //private ArrayList<Course> allowedCourses;

    public Instructor(String fullname/*, ArrayList<Course> allowedCourses*/){
        ID = new SimpleIntegerProperty(count++);
        this.fullname = new SimpleStringProperty(fullname);
        //this.allowedCourses = allowedCourses;
    }

   /* public ArrayList<Course> getAllowedCourses() {
        return allowedCourses;
    }*/

    public int getID() {
        return ID.get();
    }

    public String getFullname() {
        return fullname.get();
    }

    public StringProperty instructorName() {
        return fullname;
    }

    public IntegerProperty instructorID() {
        return ID;
    }

    @Override
    public String toString() {
        return getFullname();
    }
}
