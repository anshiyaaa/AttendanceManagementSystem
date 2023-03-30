package org.exmaple.models;

public class ModelAttendance {

    public int id;

    public int class_id;

    public int user_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public ModelAttendance(int id, int class_id, int user_id) {
        this.id = id;
        this.class_id = class_id;
        this.user_id = user_id;
    }
}
