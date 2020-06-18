package com.sict.studentmanager;

public class Info {
    public String id;
    public String user;
    public int pass;

    public Info(String id, String user, int pass) {
        this.id = id;
        this.user = user;
        this.pass = pass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    public String getUser() {
        return user;
    }

    public int getPass() {
        return pass;
    }
}
