package com.example.chatdemoproject.bean;

/**
 * Created by sarabjjeet on 9/28/17.
 */

public class SendMessageModel {
    String textMessage;
    String image;

    public SendMessageModel(String textMessage, String image) {
        this.textMessage = textMessage;
        this.image = image;
    }

    public SendMessageModel() {
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
