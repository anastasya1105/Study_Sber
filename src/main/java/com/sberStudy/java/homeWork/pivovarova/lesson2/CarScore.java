package com.sberStudy.java.homeWork.pivovarova.lesson2;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;

public class CarScore {
    public static Collection<Car> init(Collection<Car> cars) {
        cars.add(new Car("Audi", "sedan"));
        cars.add(new Car("BMW", "sedan"));
        cars.add(new Car("Citroen", "sedan"));
        cars.add(new Car("Hyundai", "sedan"));
        cars.add(new Car("Lexus", "sedan"));
        cars.add(new Car("Mercedes", "sedan"));
        cars.add(new Car("Toyota", "sedan"));
        cars.add(new Car("Porsche", "hatch—back"));
        cars.add(new Car("Renault", "hatch—back"));
        cars.add(new Car("Skoda", "hatch—back"));
        cars.add(new Car("Volkswagen", "hatch—back"));
        cars.add(new Car("KIA", "hatch—back"));
        cars.add(new Car("Alpina", "station wagon"));
        cars.add(new Car("Audi", "station wagon"));
        cars.add(new Car("Lada", "station wagon"));
        cars.add(new Car("Skoda", "station wagon"));
        cars.add(new Car("Audi", "crossover"));
        cars.add(new Car("Bentley", "crossover"));
        cars.add(new Car("BMW", "crossover"));
        cars.add(new Car("Buick", "crossover"));
        cars.add(new Car("Cadillac", "crossover"));
        cars.add(new Car("Chevrolet", "crossover"));
        cars.add(new Car("Citroen", "crossover"));
        cars.add(new Car("Honda", "SUV"));
        cars.add(new Car("Hummer", "SUV"));
        cars.add(new Car("Hyundai", "SUV"));
        cars.add(new Car("Jeep", "SUV"));
        cars.add(new Car("KIA", "SUV"));
        cars.add(new Car("BMW", "minivan"));
        cars.add(new Car("Citroen", "minivan"));
        cars.add(new Car("Mercedes", "minivan"));
        cars.add(new Car("Mercedes", "cabriolet"));
        cars.add(new Car("Porsche", "cabriolet"));
        cars.add(new Car("Mitsubishi", "pickup"));
        cars.add(new Car("Volkswagen", "pickup"));
        cars.add(new Car("Toyota", "pickup"));

        return cars;
    }
    public static Map<Integer,Car> init(Map<Integer, Car> cars) {
        cars.put(1, new Car("Audi", "sedan"));
        cars.put(2, new Car("BMW", "sedan"));
        cars.put(3, new Car("Citroen", "sedan"));
        cars.put(4, new Car("Hyundai", "sedan"));
        cars.put(5, new Car("Lexus", "sedan"));
        cars.put(6, new Car("Mercedes", "sedan"));
        cars.put(7, new Car("Toyota", "sedan"));
        cars.put(8, new Car("Porsche", "hatch—back"));
        cars.put(9, new Car("Renault", "hatch—back"));
        cars.put(10, new Car("Skoda", "hatch—back"));
        cars.put(11, new Car("Volkswagen", "hatch—back"));
        cars.put(12, new Car("KIA", "hatch—back"));
        cars.put(13, new Car("Alpina", "station wagon"));
        cars.put(14, new Car("Audi", "station wagon"));
        cars.put(15, new Car("Lada", "station wagon"));
        cars.put(16, new Car("Skoda", "station wagon"));
        cars.put(17, new Car("Audi", "crossover"));
        cars.put(18, new Car("Bentley", "crossover"));
        cars.put(19, new Car("BMW", "crossover"));
        cars.put(20, new Car("Buick", "crossover"));
        cars.put(21, new Car("Cadillac", "crossover"));
        cars.put(22, new Car("Chevrolet", "crossover"));
        cars.put(23, new Car("Citroen", "crossover"));
        cars.put(24, new Car("Honda", "SUV"));
        cars.put(25, new Car("Hummer", "SUV"));
        cars.put(26, new Car("Hyundai", "SUV"));
        cars.put(27, new Car("Jeep", "SUV"));
        cars.put(28, new Car("KIA", "SUV"));
        cars.put(29, new Car("BMW", "minivan"));
        cars.put(30, new Car("Citroen", "minivan"));
        cars.put(31, new Car("Mercedes", "minivan"));
        cars.put(32, new Car("Mercedes", "cabriolet"));
        cars.put(33, new Car("Porsche", "cabriolet"));
        cars.put(34, new Car("Mitsubishi", "pickup"));
        cars.put(35, new Car("Volkswagen", "pickup"));
        cars.put(36, new Car("Toyota", "pickup"));
        return cars;
    }


        public static void print(Collection<Car> cars) {
        Iterator<Car> it = cars.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
    public static void print(Map<Integer,Car> cars){

        Iterator it = cars.entrySet().iterator();

        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
//    public static void Backward(Collection<Car> cars) {
//        ListIterator<String> it = cars.listIterator(obj.size());
//        while (it.hasPrevious())
//            System.out.println(it.previous());
//    }

}
