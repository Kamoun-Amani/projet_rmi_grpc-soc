package com;
import java.io.*;
import java.net.*;

public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 1234;

    public static void main(String[] args) {
        try (
                Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Connecté au serveur de chat. Commencez à saisir vos messages.");

            // Créer un thread pour lire les messages du serveur
            Thread readerThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = input.readLine()) != null) {
                        System.out.println("Serveur: " + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            readerThread.start();

            // Lire les messages de l'utilisateur et les envoyer au serveur
            String userInputLine;
            while ((userInputLine = userInput.readLine()) != null) {
                output.println(userInputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
