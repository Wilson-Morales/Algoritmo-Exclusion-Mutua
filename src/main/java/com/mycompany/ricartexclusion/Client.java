/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.ricartexclusion;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Client {

    public static void main(String[] args) throws IOException {
        List<Node> nodeinfo = new ArrayList<>();
        nodeinfo.add(new Node(0, "192.168.100.31", 8833));
        // Agrega más nodos según sea necesario...

        Client client = new Client();
        try {
            client.connectToNodes(nodeinfo);
            client.sendRequests(nodeinfo, 0);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Espera 15 segundos antes de enviar el mensaje
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Envía el mensaje a la zona crítica
        client.sendMessageToCriticalZone("Este es un mensaje importante");

        // Ya has creado el objeto MessageSender, así que solo necesitas usarlo para enviar el mensaje a todos los nodos
        MessageSender msgSender = new MessageSender();
        msgSender.sendToAll("Hello from Client");

    }

    public void connectToNodes(List<Node> nodeinfo) throws UnknownHostException, IOException, InterruptedException {
        for (Node node : nodeinfo) {
            Socket clientSocket = new Socket(node.getIpaddr(), node.getPortno());
            System.out.println("~~~~~Adding " + node.getId() + " to the socket connections");
            SocketConnections.sockets.put(node.getId(), clientSocket);
            MessageReceiver msgReceive = new MessageReceiver(clientSocket, node.getId());
            msgReceive.start();
        }
    }

    public void sendRequests(List<Node> nodeinfo, int id) throws UnknownHostException, IOException, InterruptedException {
        System.out.println("Bring them up in 15 secs...");
        Thread.sleep(15000);

        for (int i = 1; i < nodeinfo.size(); i++) {
            Socket clientSocket = SocketConnections.sockets.get(nodeinfo.get(i).getId());
            MessageSender msgSender = new MessageSender();
            msgSender.createOutputStream(clientSocket, id, Message.REQUEST);
        }
    }

    public void sendMessageToCriticalZone(String message) throws IOException {
        // Envía el mensaje a todos los nodos conectados
        MessageSender msgSender = new MessageSender(message);
        msgSender.start();
    }
}
