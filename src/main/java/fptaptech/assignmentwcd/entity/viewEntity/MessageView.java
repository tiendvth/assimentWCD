package fptaptech.assignmentwcd.entity.viewEntity;


import fptaptech.assignmentwcd.entity.entityEnum.MessageType;

import java.awt.*;

public class MessageView {
    private MessageType messageType;
    private String content;

    public MessageView() {
    }

    public MessageView(MessageType messageType, String content) {
        this.messageType = messageType;
        this.content = content;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
