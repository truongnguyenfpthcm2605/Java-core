package org.vnpay;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            //server
            int port = 8080;
            String message;
            Socket socket = new Socket("localhost", port);
            System.out.println("Connected to: " + socket.getRemoteSocketAddress() + "successfully");

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.print("send message to server:");
                message = sc.nextLine();

                if(message.equals("exist")) {
                    socket.close();
                    System.out.println("Server already exists");
                    break;
                }
                dataOutputStream.writeUTF(message);


                System.out.println("received from server: " + dataInputStream.readUTF());



            }
        } catch (Exception ignored) {
            System.out.println("Connection failed");
        }
    }
}
