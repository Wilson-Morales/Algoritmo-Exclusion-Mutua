/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ricartexclusion;


public class Node {

	int id;
	String ipaddr;
	int portno;
        
    public Node(int id, String ipaddr, int portno) {
        this.id = id;
        this.ipaddr = ipaddr;
        this.portno = portno;
    }
    
    // Getters
    public int getId() {
        return id;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public int getPortno() {
        return portno;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    public void setPortno(int portno) {
        this.portno = portno;
    }

    Node() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}