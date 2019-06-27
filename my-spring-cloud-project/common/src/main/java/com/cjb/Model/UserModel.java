package com.cjb.Model;

public class UserModel extends PageModel{

    private Integer userId;

    private String userName;

    private int userAge;

    private String userAddress;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Override
    public int hashCode() {
        return this.userId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        UserModel user = (UserModel)obj;
        return this.userId.equals(user.getUserId());
    }
}
