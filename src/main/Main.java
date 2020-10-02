package main;

import model.SensorType;
import model.TempSensor;
import model.WaterLevelMeterSensor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        SensorType st = new SensorType();

        /*Sensor típus kiválasztása hogy milyen típusú szenzor induljon el*/
        System.out.println(st.toString());
        System.out.println("Select sensor type:");
        int type = sc.nextInt();

        /*Választott típusnak megfelelő szenzor indítása*/
        if(type == 0){
            TempSensor temp = new TempSensor("127.0.0.1",3000);
            temp.work();
        }
        else if(type == 1){
            WaterLevelMeterSensor wlms = new WaterLevelMeterSensor("127.0.0.1",3000);
            wlms.work();
        }
    }
}
