package model;

public abstract class Actuator extends Sensor {

    public Actuator(String address, int port) {
        super(address, port);
    }
}
