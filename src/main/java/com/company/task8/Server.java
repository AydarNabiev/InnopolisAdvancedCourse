package com.company.task8;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

public class Server extends Subject {
    public static DatagramSocket udpSocket;
    private static HashMap<Integer, Client> clientMap= new HashMap<>();
    // first word private? second word client check map if notExists "no such client!"

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        udpSocket = new DatagramSocket(6000);
        udpSocket.setBroadcast(true);
        byte[] buffer = new byte[65536];
        DatagramPacket incomingMessages = new DatagramPacket(buffer, buffer.length);
        System.out.println("Ожидаем данные...");
        while(true)
        {
            udpSocket.receive(incomingMessages);
            byte[] data = incomingMessages.getData();
            String message = new String(data, 0, incomingMessages.getLength());
            System.out.println("Сервер получил от " + incomingMessages.getPort() + ": " + message);
            if (!message.equals("quit")) {
                if (!clientMap.containsKey(incomingMessages.getPort())) {
                    Client client = new Client(message, incomingMessages.getPort());
                    clientMap.put(client.port, client);
                    DatagramPacket dp = new DatagramPacket(message.getBytes() , message.getBytes().length, InetAddress.getByName("127.0.0.1"), incomingMessages.getPort());
                    udpSocket.send(dp);
                    server.notifyObserver(dp);

                } else {
                    Client existingClient = clientMap.get(incomingMessages.getPort());
                    message = existingClient.formMessage(message);
                    if (message.substring(message.lastIndexOf(" ; ") + 11).startsWith("private")) {
                        String receiverPort = message.substring(message.lastIndexOf(" ; ") + 19).substring(0, 4);
                        Client receiver = clientMap.getOrDefault(receiverPort, null);
                        if (receiver == null) {
                            System.out.println("Клиент не найден!");
                        } else {
                            DatagramPacket dp = new DatagramPacket(message.getBytes() , message.getBytes().length, InetAddress.getByName("127.0.0.1"), receiver.port);
                            udpSocket.send(dp);
                            server.notifyObserver(dp);
                        }
                    } else {
                        for (Integer key : clientMap.keySet()) {
                            DatagramPacket dp = new DatagramPacket(message.getBytes() , message.getBytes().length, InetAddress.getByName("127.0.0.1"), key);
                            udpSocket.send(dp);
                            server.notifyObserver(dp);
                        }
                    }
                }

            } else {
                clientMap.remove(incomingMessages.getPort());
            }
        }
    }

    @Override
    protected void notifyObserver(DatagramPacket packet) {
        observers.forEach((observer -> {
            try {
                observer.update(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
}