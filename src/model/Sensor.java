package model;

import java.io.*;
import java.net.*;

/*
 * Abstract osztály, Sesnor model, minden továbbiakban létrehozott sensor rendelkezik
 * az itt definiált tulaldonságokkal
 * */
public abstract class Sensor {

    public Socket socket = null;  //sensor socket-ja
    public String address;  //server ip cím
    public int port;  //server port
    public BufferedReader input   = null;  //bemenet a szerver felől
    public PrintWriter out     = null;  //kiment a szerver felé

    public Sensor() {
    }

    public Sensor(String address, int port) {
        this.address = address;
        this.port = port;
    }

    /*
     * Kapcsolódűs a szerverhez, ezt a metódust meg kell hívni a work-ben amikor implementáljuk
     * */
    public void connect(){
        try {
            socket = new Socket(address,port);
            input  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out    = new PrintWriter(socket.getOutputStream(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Abstract metódus, az adott specifikus sensor példányban implementáljuk
     * */
    public abstract void work();
}
