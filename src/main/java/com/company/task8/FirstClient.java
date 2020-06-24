package com.company.task8;

import java.io.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class FirstClient {

    /*public static void main(String[] args) throws IOException {
        //Socket socket = new Socket("127.0.0.1", 6000);
        InetAddress address = InetAddress.getByName("127.0.0.1");
        DatagramSocket socket = new DatagramSocket(6000);

        // надо введенную в консоль строку засунуть в datagrampacket и отправить по сокету

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        Scanner scanner = new Scanner(System.in);
        String message;
        while (!(message = scanner.nextLine()).isEmpty()) {
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            System.out.println("server returns " + bufferedReader.readLine());
        }
        socket.close();
    }*/

}