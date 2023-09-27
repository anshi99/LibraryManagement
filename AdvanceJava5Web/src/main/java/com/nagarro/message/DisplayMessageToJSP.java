package com.nagarro.message;

public class DisplayMessageToJSP {

    private String messageDetails;
    private String messageType;
    private String cssClass;

    public DisplayMessageToJSP(String messageDetails, String messageType, String cssClass) {
        super();
        this.messageDetails = messageDetails;
        this.messageType = messageType;
        this.cssClass = cssClass;
    }


    public String getMessageDetails() {
        return messageDetails;
    }
    public void setMessageDetails(String messageDetails) {
        this.messageDetails = messageDetails;
    }
    public String getMessageType() {
        return messageType;
    }
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }
}


