package model;

import java.io.IOException;
import java.util.Random;

public class IrrigationNode extends  Actuator {

    private String turnSignal;
    private int value;
    private int minValue = 1;
    private int maxValue = 8;
    private Random random;
    private double[] coordinates;

    public IrrigationNode(String address, int port) {
        super(address, port);
        random = new Random();
    }

    @Override
    public void work() {
        //csatlakozás a központi egységhez
        connect();
        coordinates = getCoordinates(); //koordináták generálása
        //A központi egység értesítése a node típusáról és a koordinátákról
        out.println("Type: "+ SensorType.Types.Irrigation + " x:" +coordinates[0] + " ,y:" +coordinates[1]);

        while(true){
            try {
                turnSignal = input.readLine();  //vár egy jelet a központi egységtől

                //Ha ez a jel Turn on akkor bekapcsol az öntöző egység, egyébként alvó üzemódban van.
                if(turnSignal.equals("Turn on")){
                    value = measureHumidity();  //megméri a talajnedvességet 1-8 skála (1=nagyon száraz, 8=nagyon nedves)
                    examineHumidityValue(value);
                }
                else{
                    System.out.println("Sleep mode");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    //Nedvesség mérése
    private int measureHumidity(){
        return  minValue + (maxValue-minValue) * random.nextInt();
    }

    //A  nedvesség érték kiértékelése
    private void examineHumidityValue(int h){
        if(h>=1 && h<=3)
            System.out.println("Working on hard mode");
        else if(h>=4 && h<=6)
            System.out.println("Working on medium mode");
        else if(h>=7)
            System.out.println("Working on slow mode");
    }
}
