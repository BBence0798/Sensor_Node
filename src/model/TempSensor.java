package model;

import java.util.Random;

/*
 * Sensor példány , Temperature Sensor, örökli az Abstract sensor osztály tulajdonságait
 * */
public class TempSensor extends Sensor {

    private double minValue = 2;
    private double maxValue = 40;
    private double[] coordinates;
    Random random;

    public TempSensor() {
        random = new Random();
    }

    public TempSensor(String address, int port) {
        super(address,port);
        random = new Random();
    }

    @Override
    public void work() {
        System.out.println(address+ " : " + port);
        connect();  //kapcsolódás a központi egységhez
        coordinates = getCoordinates(); //koordináták generálása
        out.println("Type: " + SensorType.Types.TemperatureSensor + " x:" + coordinates[0] + " y:"+ coordinates[1]);  //a központi egység értesítése a sensor típusáról és a koordinátákról

        /*Mérési adatok generálása, majd továbbítása a központi egység fel*/
        for (int i = 0; i < 10 ; i++) {
            try{
                double value = measureTemperature();
                System.out.println(value);
                out.println(value);
                Thread.sleep(1000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /*Központi egység értesítése a lekapcsolódásról*/
        out.println("VEGE");
    }

    public double measureTemperature(){
        return  minValue + (maxValue-minValue) * random.nextDouble();  //random szám generálása 2 érték között
    }
}
