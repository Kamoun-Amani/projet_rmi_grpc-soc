package com.grpc.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import messaging.*;

import java.util.Scanner;

public class MessageClient {
    private final ManagedChannel channel;
    private final MessageServiceGrpc.MessageServiceBlockingStub blockingStub;

    public MessageClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        blockingStub = MessageServiceGrpc.newBlockingStub(channel);
    }

    public void sendMessage(String sender, String recipient, String message) {
        MessageRequest request = MessageRequest.newBuilder()
                .setSender(sender)
                .setRecipient(recipient)
                .setMessage(message)
                .build();
        blockingStub.sendMessage(request);
    }

    public void getReceivedMessages(String recipient) {
        RecipientRequest request = RecipientRequest.newBuilder()
                .setRecipient(recipient)
                .build();
        MessageList messageList = blockingStub.getReceivedMessages(request);
        messageList.getMessagesList().forEach(System.out::println);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, java.util.concurrent.TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws InterruptedException {
        MessageClient client = new MessageClient("localhost", 50051);
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String sender = scanner.nextLine();

        while (true) {
            System.out.print("1. Send a message\n2. Get received messages\nChoose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                System.out.print("Enter recipient: ");
                String recipient = scanner.nextLine();
                System.out.print("Enter message: ");
                String message = scanner.nextLine();
                client.sendMessage(sender, recipient, message);
                System.out.println("Message sent.");
            } else if (option == 2) {
                client.getReceivedMessages(sender);
            } else {
                System.out.println("Invalid option.");
            }
        }
    }
}

