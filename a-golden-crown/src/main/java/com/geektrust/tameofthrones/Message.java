package com.geektrust.tameofthrones;

public class Message {
    private final Kingdom sender;
    private final String message;
    private final Kingdom receiver;

    public Message(Kingdom sender, String message, Kingdom receiver) {
        this.sender = sender;
        this.message = message;
        this.receiver = receiver;
    }

    public Kingdom getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public Kingdom getReceiver() {
        return receiver;
    }
}
