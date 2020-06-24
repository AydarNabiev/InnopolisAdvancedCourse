package com.company.task8;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static DatagramSocket udpSocket;
    private static List<Integer> ports = new ArrayList<>();
    private static List<Client> clients = new ArrayList<>();
    // map clients logged
    // unique names?
    // first word private? second word client check map if notExists "no such client!"

    public static void main(String[] args) throws IOException {
        // создаём сокет
        udpSocket = new DatagramSocket(6000);
        udpSocket.setBroadcast(true);
        byte[] buffer = new byte[65536];
        DatagramPacket incomingMessages = new DatagramPacket(buffer, buffer.length);
        System.out.println("Ожидаем данные...");
        while(true)
        {
            // Получаем данные
            udpSocket.receive(incomingMessages);
            byte[] data = incomingMessages.getData();
            String message = new String(data, 0, incomingMessages.getLength());
            System.out.println("Сервер получил: " + message);
            //Отправляем данные клиенту
            DatagramPacket dp = new DatagramPacket(message.getBytes() , message.getBytes().length , incomingMessages.getAddress() , incomingMessages.getPort());

            // новый порт? добавляем его в список задействованных, создаём клиента (имя)
            // старый порт? посылаем сообщение задействованием клиента
            if (!ports.contains(incomingMessages.getPort())) {
                // клиента нет, создаём клиента
                Client client = new Client(message, incomingMessages.getPort());
                ports.add(client.port);
                clients.add(client);
            }
            udpSocket.send(dp);
        }

        // принимаем сообщение извне, первое сообщение от каждого клиента станет его именем и будет ставиться в начало
        // класс клиента? (имя, сообщение)
        //
    }

}