/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ricartexclusion;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class RicartExclusion extends Thread {
    DataOutputStream dos;
    MessageSender msgSender;
    int port;
    int id;
    static int identity; // Identifying the node numbers as I get the request to connect

    public RicartExclusion(Map<Integer, Integer> ports, Integer id) {
        this.id = id;
        this.port = 8833;
    }

    @Override
    public void run() {
        ServerSocket ss;
        try {
            InetAddress ipAddress = InetAddress.getByName("192.168.100.31"); // Replace with the desired IP address
            System.out.println("Starting server on IP: " + ipAddress + ", port no: " + port);
            ss = new ServerSocket(port, 50, ipAddress);

            while (true) {
                System.out.println("Waiting for connections...");
                Socket s = ss.accept();
                SocketConnections.sockets.put(identity++, s);
                MessageReceiver m = new MessageReceiver(s, id); // Start thread to listen for incoming data
                m.start();
                dos = new DataOutputStream(s.getOutputStream());
                // dos.writeUTF("Thanks for the connect...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Inicialización del mapa de puertos con ID de nodo como clave y número de puerto como valor
        Map<Integer, Integer> ports = new HashMap<>();
        ports.put(0, 8888);
        ports.put(1, 8889);
        ports.put(2, 8890);
        // ... Agrega más entradas según tus necesidades ...

        // ID del nodo deseado
        int id = 0; // Cambia este valor según el nodo deseado

        RicartExclusion ricartExclusion = new RicartExclusion(ports, id);
        ricartExclusion.start(); // Inicia el hilo
    }
}
