package main;

import model.TempSensor;

public class Main {
    public static void main(String[] args) {
        TempSensor tempSensor = new TempSensor("127.0.0.1",3000);
        tempSensor.work();
    }
}
