package com.company.task8;

import java.io.IOException;
import java.net.DatagramPacket;

public interface Observer {

    void update(DatagramPacket packet) throws IOException;

}