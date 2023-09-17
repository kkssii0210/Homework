package chap_000;

import java.util.Scanner;

public class _01_Exer {
    public static void main(String[] args) {
        Car car = new Car();
        car.start();

    }
}
class Car {
    private ElectronicEngine engine = new ElectronicEngine();
    public void start() {
        engine.startElectronicEngine();
    }
}
class OilEngine {
    public void startOilEngine(){
        System.out.println("start Oil engine");
    }
}
class ElectronicEngine {
    public void startElectronicEngine(){
        System.out.println("start Electronic engine");
    }
}