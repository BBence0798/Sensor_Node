package model;

import java.util.Random;

/*
* Páratartalom mérő sensor
* */
public class HumiditySensor extends Sensor {

    private Random random;
    private TempSensor tempSensor;
    private double temperature;
    private double dewPoint;
    private double humidityValue;
    private double[] coordinates;

    public HumiditySensor(String address, int port) {
        super(address, port);
        random = new Random();
        tempSensor = new TempSensor();
    }

    @Override
    public void work() {
        //Csatlakozás a központi egységhez
        connect();
        coordinates = getCoordinates();  //koordináták generálása
        out.println("Type: " + SensorType.Types.HumiditySensor + " x:" + coordinates[0] + " y:"+coordinates[1]);  //A szerver értesítése a csatlakozott eszköz típusáról és a koordinátákról

        for(int i = 0;i < 10;i++){
            try {
                temperature = tempSensor.measureTemperature();
                dewPoint = calculateDewPoint(temperature);
                humidityValue = calculateHumidity(temperature,dewPoint);
                out.println(humidityValue);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        out.println("VEGE");
    }

    //Páratartalom érték kiszámítása
    private double calculateHumidity(double temperature,double dewPoint){
            double es = calculateVaporPressure(temperature); //telített gőznyomás
            double e = calculateVaporPressure(dewPoint); //aktuális gőznyomás

            return (e/es)*100;
    }

    private double calculateVaporPressure(double t){
        return 6.11*10*((7.5*t)/(237.3+t));
    }

    //Harmatpont meghatározása adott hőmérséklethez
    private double calculateDewPoint(double temperature){

        if(temperature>= 2 && temperature<= 11)
            return genDewPoint(-7.7,9.31);
        else if(temperature>=12 && temperature<=20)
            return genDewPoint(0.35,19.18);
        else if(temperature>=21 && temperature<=25)
            return genDewPoint(8.6,24.22);
        else if(temperature>=26 && temperature<=30)
            return genDewPoint(13.15,29.09);

        return genDewPoint(18.52,39.11);
    }

    //random harmatpont generálása
    private double genDewPoint(double min, double max){
        return min + (max-min) * random.nextDouble();
    }
}
