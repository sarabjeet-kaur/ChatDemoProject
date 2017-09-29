package com.example.chatdemoproject.bean;

import java.io.Serializable;

/**
 * Created by sarabjjeet on 9/28/17.
 */

public class SendMessageModel implements Serializable {
    String textMessage;
    String image;
    String messageType;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

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
