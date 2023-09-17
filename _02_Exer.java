package chap_000;

public class _02_Exer {
    public static void main(String[] args) {
        Car1 car1 = new Car1(new ElectronicEngine1());
        car1.start();
    }
}
class Car1 {

    private Engine engine;

    public Car1(Engine engine) {
        this.engine = engine;
    }

    public void start() {
        engine.startEngine();
    }
}
interface Engine {
    void startEngine();
}
class OilEngine1 implements Engine {
    @Override
    public void startEngine() {
        System.out.println("start Oil Engine!!");
    }
}
class ElectronicEngine1 implements Engine {
    @Override
    public void startEngine() {
        System.out.println("start Electronic Engine!!");
    }
}