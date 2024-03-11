package com.aws.amazon.model;

public class SQSMessege {
    private String messege;

    public String getMessege() {
        return messege;
    }

    public void setMessege(String messege) {
        this.messege = messege;
    }

    @Override
    public String toString() {
        return "SQSMessege{" +
                "messege='" + messege + '\'' +
                '}';
    }
}
