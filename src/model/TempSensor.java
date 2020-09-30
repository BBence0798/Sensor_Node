package model;

/*
 * Sensor példány , Temperature Sensor, örökli az Abstract sensor osztály tulajdonságait
 * */
public class TempSensor extends Sensor {

    public TempSensor(String address, int port) {
        super(address,port);
    }

    @Override
    public void work() {
        System.out.println(address+ " : " + port);
        connect();
        out.println("Hello. I am a temperature sensor!");
        out.println("VEGE");
    }
}
