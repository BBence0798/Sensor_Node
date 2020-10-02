package model;

import java.util.Random;

/*
 * Sensor példány , Temperature Sensor, örökli az Abstract sensor osztály tulajdonságait
 * */
public class TempSensor extends Sensor {

    private  double minValue = 20;
    private double maxValue = 40;
    Random random;

    public TempSensor(String address, int port) {
        super(address,port);
        random = new Random();
    }

    @Override
    public void work() {
        System.out.println(address+ " : " + port);
        connect();  //kapcsolódás a központi egységhez
        out.println("Type:" + SensorType.Types.TemperatureSensor);  //a központi egység értesítése a sensor típusáról

        /*Mérési adatok generálása, majd továbbítása a központi egység fel*/
        for (int i = 0; i < 10 ; i++) {
            try{
                double value = minValue + (maxValue-minValue) * random.nextDouble();  //random szám generálása 2 érték között
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
}
