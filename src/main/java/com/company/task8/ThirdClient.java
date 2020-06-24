package com.company.task8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ThirdClient {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clSocket = new DatagramSocket();
        clSocket.setBroadcast(true);
        // автоматическая отправка первого сообщения-имени
        String name = "Maria";
        clSocket.send(new DatagramPacket(name.getBytes(), name.getBytes().length, InetAddress.getByName("127.0.0.1") , 6000));
        //буфер для получения входящих данных
        byte[] buffer = new byte[65536];
        DatagramPacket firstReply = new DatagramPacket(buffer, buffer.length);
        clSocket.receive(firstReply);
        String nameMessage = new String(firstReply.getData(), 0, firstReply.getLength());
        System.out.println("Сервер: " + firstReply.getAddress().getHostAddress() + ", получил имя: " + nameMessage);
        while(!clSocket.isClosed())
        {
            //Ожидаем ввод сообщения серверу
            System.out.println("Введите сообщение серверу: ");
            String myMessage = (String)reader.readLine();
            byte[] bytes = myMessage.getBytes();
            //Отправляем сообщение
            DatagramPacket dp = new DatagramPacket(bytes , bytes.length , InetAddress.getByName("127.0.0.1"), 6000);
            clSocket.send(dp);
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            //Получаем данные
            clSocket.receive(reply);
            byte[] data = reply.getData();
            myMessage = new String(data, 0, reply.getLength());
            if (myMessage.equals("quit")) {
                System.out.println("Сервер получил сообщение quit, закрываем сокет");
                clSocket.close();
            } else {
                System.out.println("Сервер - " + reply.getAddress().getHostAddress() + ", порт - " + reply.getPort() + ", получил сообщение - " + myMessage);
            }
        }
    }
}