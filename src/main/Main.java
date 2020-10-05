package main;

import model.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        SensorType st = new SensorType();
        String address = "127.0.0.1";
        int port = 3000;

        /*Sensor típus kiválasztása hogy milyen típusú szenzor induljon el*/
        System.out.println(st.toString());
        System.out.println("Select sensor type:");
        int type = sc.nextInt();

        /*Választott típusnak megfelelő szenzor indítása*/
        if(type == 0){
            TempSensor tempSensor = new TempSensor(address,port);
            tempSensor.work();
        }
        else if(type == 1){
            WaterLevelMeterSensor waterLevelMeterSensor = new WaterLevelMeterSensor(address,port);
            waterLevelMeterSensor.work();
        }
        else if(type == 2){
            HumiditySensor humiditySensor = new HumiditySensor(address,port);
            humiditySensor.work();
        }
        else if(type == 3){
            IrrigationNode irrigationNode = new IrrigationNode(address,port);
            irrigationNode.work();
        }
    }
}
