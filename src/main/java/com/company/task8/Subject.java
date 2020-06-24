package com.company.task8;

import java.net.DatagramPacket;
import java.util.ArrayList;

public abstract class Subject {
    protected ArrayList<Observer> observers;

    public Subject() {
        observers = new ArrayList<>();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    protected abstract void notifyObserver(DatagramPacket packet);
}