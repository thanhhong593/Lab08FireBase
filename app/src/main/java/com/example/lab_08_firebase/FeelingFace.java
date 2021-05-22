package com.example.lab_08_firebase;

public class FeelingFace {
    private String yourName;
    private String email;
    private String passWord;
    private int happy;
    private int unHappy;
    private int normal;
    public FeelingFace(){

    }

    public FeelingFace(String email, int happy, int unHappy, int normal) {
        this.email = email;
        this.happy = happy;
        this.unHappy = unHappy;
        this.normal = normal;
    }

    public FeelingFace(String yourName, String email, String passWord, int happy, int unHappy, int normal) {
        this.yourName = yourName;
        this.email = email;
        this.passWord = passWord;
        this.happy = happy;
        this.unHappy = unHappy;
        this.normal = normal;
    }

    public String getYourName() {
        return yourName;
    }

    public void setYourName(String yourName) {
        this.yourName = yourName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getHappy() {
        return happy;
    }

    public void setHappy(int happy) {
        this.happy = happy;
    }

    public int getUnHappy() {
        return unHappy;
    }

    public void setUnHappy(int unHappy) {
        this.unHappy = unHappy;
    }

    public int getNormal() {
        return normal;
    }

    public void setNormal(int normal) {
        this.normal = normal;
    }
}
