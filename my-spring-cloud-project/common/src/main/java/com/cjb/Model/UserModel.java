package com.cjb.Model;

public class UserModel {

    private String id;


    private String userName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        UserModel user = (UserModel)obj;
        return this.id.equals(user.getId());
    }
}
