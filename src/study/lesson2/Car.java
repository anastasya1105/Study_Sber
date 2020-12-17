package study.lesson2;

public class Car implements Comparable<Car> {
    String model;
    String type;
    public Car(String model, String type) {
        this.model = model;
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    @Override
    public String toString() {
        return "ModelCar = " + this.model + "; TypeCar = " + this.type;
    }
    @Override
    public boolean equals(Object obj) {
        return this.getModel().equals(((Car)obj).getModel());
    }

    @Override
    public int compareTo(Car o) {
        return 0;
    }
}
