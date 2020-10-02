package model;

public class SensorType {
    public enum Types {
        TemperatureSensor,
        WaterLevelMeterSensor,
        HumiditySensor,
        Irrigation
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("0 - ").append(Types.TemperatureSensor+"\n")
                .append("1 - ").append(Types.WaterLevelMeterSensor+"\n")
                .append("2 - ").append(Types.HumiditySensor+"\n")
                .append("3 - ").append(Types.Irrigation+"\n");

        return sb.toString();
    }
}
