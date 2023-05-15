package com.example.docentapplication;

public class Message {
    public static String SENT_BY_ME = "me";
    public static String SENT_BY_BOT = "bot";

    String message;
    String sentBy;
    Boolean isChecked;

    public Boolean getChecked() { return isChecked; }

    public void setChecked(Boolean checked) { this.isChecked = checked; }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSentBy() {
        return sentBy;
    }

    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
    }

    public Message(String message, String sentBy, Boolean isChecked) {
        this.message = message;
        this.sentBy = sentBy;
        this.isChecked = isChecked;
    }
}
