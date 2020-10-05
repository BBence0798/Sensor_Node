package model;

import java.util.Random;

/*
* Páratartalom mérő sensor
* */
public class HumiditySensor extends Sensor {

    private double value;
    private double minValue = 0;
    private double maxValue = 100;
    private Random random;

    public HumiditySensor(String address, int port) {
        super(address, port);
        random = new Random();
    }

    @Override
    public void work() {
        //Csatlakozás a központi egységhez
        connect();

        out.println("Type:" + SensorType.Types.HumiditySensor);  //A szerver értesítése a csatlakozott eszköz típusáról

        for(int i = 0;i < 10;i++){
            try {
                value = minValue + (maxValue-minValue) * random.nextDouble();  //páratartalom érték generálása 0 - 100 közötti %-os érték
                out.println(value);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        out.println("VEGE");
    }
}
