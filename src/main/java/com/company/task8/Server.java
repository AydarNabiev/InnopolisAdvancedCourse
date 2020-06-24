package com.company.task8;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

public class Server {
    public static DatagramSocket udpSocket;
    private static HashMap<Integer, Client> clientMap= new HashMap<>();
    // first word private? second word client check map if notExists "no such client!"

    public static void main(String[] args) throws IOException {
        // создаём сокет
        udpSocket = new DatagramSocket(6000);
        //udpSocket.connect(InetAddress.getByName("127.0.0.1"), 6000);
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
            if (!message.equals("quit")) {
                if (!clientMap.containsKey(incomingMessages.getPort())) {
                    // клиента нет, создаём клиента
                    Client client = new Client(message, incomingMessages.getPort());
                    clientMap.put(client.port, client);
                } else {
                    Client existingClient = clientMap.get(incomingMessages.getPort());
                    message = existingClient.formMessage(message);
                }
            } else {
                clientMap.remove(incomingMessages.getPort());
            }
            //Отправляем данные клиенту/ам

            for (Integer key : clientMap.keySet()) {
                //DatagramPacket dp = new DatagramPacket(message.getBytes() , message.getBytes().length, incomingMessages.getAddress(), key);
                DatagramPacket dp = new DatagramPacket(message.getBytes() , message.getBytes().length, InetAddress.getByName("127.0.0.1"), key);
                udpSocket.send(dp);
            }

            /*DatagramPacket dp = new DatagramPacket(message.getBytes() , message.getBytes().length, incomingMessages.getAddress(), incomingMessages.getPort());
            udpSocket.send(dp);*/
        }
    }
}