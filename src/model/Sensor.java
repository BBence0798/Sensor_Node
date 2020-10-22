package model;

import java.io.*;
import java.net.*;
import java.text.DecimalFormat;
import java.util.Random;

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

    //Lekerekítjük 2 tizedesjegyre a koordinátát
    private static DecimalFormat df = new DecimalFormat("0.00");

    /*Generál koordinátákat és vissza adja azokat*/
    public  double[] getCoordinates(){
        double[] coordinates = new double[2];
        Random r = new Random();
        double min = -50;
        double max = 50;

        coordinates[0] = Double.parseDouble(df.format(min + (max-min) * r.nextDouble()).replaceAll(",","."));
        coordinates[1] = Double.parseDouble(df.format(min + (max-min) * r.nextDouble()).replaceAll(",","."));

        return coordinates;
    }

    /*
     * Abstract metódus, az adott specifikus sensor példányban implementáljuk
     * */
    public abstract void work();
}
