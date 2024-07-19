package org.vnpay;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try {
            //server start
            int port = 8080;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);
            Scanner scanner = new Scanner(System.in);

            // Accept multiple connections from clients
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected");

                // Create a new thread for each client connection
                new Thread(new ClientHandler(clientSocket)).start();


            }

        }catch (Exception ignored){

        }
    }
}
