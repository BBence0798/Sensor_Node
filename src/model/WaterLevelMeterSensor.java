package model;

import java.util.Random;

public class WaterLevelMeterSensor extends Sensor {

    private double waterLevel = 20; //tartályban lévő vízszint milliméterben
    private double minValue = 0;
    private double maxValue = 100;
    Random random;

    public WaterLevelMeterSensor(String address,int port) {
        super(address,port);
        random = new Random();
    }

    @Override
    public void work() {
        System.out.println(address+ " : " + port);
        //connect();  //kapcsolódás a központi egységhez
        //out.println("Type:" + SensorType.Types.WaterLevelMeterSensor);  //a központi egység értesítése a sensor típusáról

        /*Mérési adatok generálása, majd továbbítása a központi egység fel*/
        for (int i = 0; i < 10 ; i++) {
            try{
                double value = minValue + (maxValue-minValue) * random.nextDouble();  //random szám generálása 2 érték között
                waterLevel += value;
                System.out.println(waterLevel);
                //out.println(value);
                Thread.sleep(1000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /*Központi egység értesítése a lekapcsolódásról*/
        //out.println("VEGE");
    }
}
