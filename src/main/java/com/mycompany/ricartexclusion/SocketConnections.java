/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ricartexclusion;

import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class SocketConnections {

//	static List<Socket> sockets = new ArrayList<>();
	
	static Map<Integer,Socket> sockets = new HashMap<Integer, Socket>();

	public static Map<Integer,Socket> getSocketConnections() {
//		System.out.println("~~~~~~~~Hashmap socket size : "+sockets.size());
		return sockets;
	}

	public static void setSocketConnections(Map<Integer,Socket> socketConnections) {
		SocketConnections.sockets = socketConnections;
		System.out.println("Socket connections size is : "+socketConnections.size());
	}
	
	public static void displaySockets(){
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		Iterator it = sockets.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry pairs = (Map.Entry) it.next();
			System.out.println(pairs.getKey() + " = " + pairs.getValue());
		}
	}
}