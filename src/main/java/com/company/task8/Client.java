package com.company.task8;

public class Client {
    String name;
    Integer port;

    public Client(String firstMessage, Integer port) {
        this.name = firstMessage;
        this.port = port;
    }

    public String sendMessage(String message) {
        return (this.name + " : " + message);
    }
}