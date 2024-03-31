package com.grpc.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import messaging.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MessageServer {
    private final int port;
    private final Server server;
    private final Map<String, StringBuilder> receivedMessages;

    public MessageServer(int port) {
        this.port = port;
        this.server = ServerBuilder.forPort(port)
                .addService(new MessageServiceImpl())
                .build();
        this.receivedMessages = new HashMap<>();
    }

    public void start() throws IOException {
        server.start();
        System.out.println("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            MessageServer.this.stop();
            System.err.println("*** server shut down");
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        MessageServer server = new MessageServer(50051);
        server.start();
        server.blockUntilShutdown();
    }

    private class MessageServiceImpl extends MessageServiceGrpc.MessageServiceImplBase {
        @Override
        public void sendMessage(MessageRequest request, StreamObserver<Empty> responseObserver) {
            String recipient = request.getRecipient();
            receivedMessages.computeIfAbsent(recipient, k -> new StringBuilder()).append(request.getSender()).append(": ").append(request.getMessage()).append("\n");
            responseObserver.onNext(Empty.newBuilder().build());
            responseObserver.onCompleted();
        }

        @Override
        public void getReceivedMessages(RecipientRequest request, StreamObserver<MessageList> responseObserver) {
            String recipient = request.getRecipient();
            MessageList.Builder messageListBuilder = MessageList.newBuilder();
            if (receivedMessages.containsKey(recipient)) {
                receivedMessages.get(recipient).lines().forEach(messageListBuilder::addMessages);
            }
            responseObserver.onNext(messageListBuilder.build());
            responseObserver.onCompleted();
        }
    }
}
