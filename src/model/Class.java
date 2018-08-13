package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Class {
    private static int count = 0;
    private Course course;
    private Instructor instructor;
    private StudentsGroup studentGroup;
    private Room allowedRoom;
    private StringProperty type;
    private IntegerProperty ID;
    private StringProperty day = new SimpleStringProperty("day");
    private IntegerProperty period = new SimpleIntegerProperty(0);

    public Class(int id){
        ID = new SimpleIntegerProperty(id);
    }

    public Class(Course course,String type, Instructor instructor, StudentsGroup studentGroup, Room allowedRoom){
        this.course = course;
        this.type = new SimpleStringProperty(type);
        this.instructor = instructor;
        this.studentGroup = studentGroup;
        this.allowedRoom = allowedRoom;
        ID = new SimpleIntegerProperty(count++);
    }


    public void setDay(String day) {
        this.day.set(day);
    }

    public StringProperty classDay() {
        return day;
    }

    public void setPeriod(int period) {
        this.period.set(period);
    }

    public IntegerProperty classPeriod() {
        return period;
    }

    public void setAllowedRoom(Room room){
        this.allowedRoom = room;
    }

    public StringProperty className(){
        return new SimpleStringProperty(this.toString());
    }

    @Override
    public String toString(){
        String str = "";
        try {

            str =  getID() + "_" + studentGroup.toString() + "_" + course.getCode() + "_ " + course.getName();
        }catch (NullPointerException e){
            str= "Empty";
        }
        return str;

    }

    public Course getCourse() {
        return course;
    }

    public boolean isAllowedRoom(int roomID){
        return allowedRoom.getID() == roomID;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public StudentsGroup getStudentGroup() {
        return studentGroup;
    }

    public int getID() {
        return ID.get();
    }

    public IntegerProperty classID(){
        return ID;
    }

    public StringProperty classGroup(){
        return studentGroup.groupName();
    }

    public StringProperty classType(){
        return type;
    }

    public StringProperty classCourse(){
        return new SimpleStringProperty(course.getName() + " " + course.getCode());
    }

    public StringProperty classInstructor(){
        return instructor.instructorName();
    }

    public StringProperty classRoom(){
        return allowedRoom.roomName();
    }
}
