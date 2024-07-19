package org.vnpay;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final Scanner scanner = new Scanner(System.in);

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

            String clientMessage;
            while (true) {
                clientMessage = dataInputStream.readUTF();
                System.out.println("Client received: " + clientMessage);

                System.out.print("Reply to client: ");
                String reply = scanner.nextLine();

                if (reply.equals("exit")) {
                    clientSocket.close();
                    System.out.println("Client disconnected");
                    break;
                }
                dataOutputStream.writeUTF(reply);
                dataOutputStream.flush();
            }

        } catch (Exception e) {
            System.out.println("Connection error"+ e.getMessage());
        } finally {
            try {
                if (clientSocket != null) {
                    clientSocket.close();
                }
            } catch (Exception e) {
                System.out.println("client close failed" + e.getMessage());
            }
        }
    }
}