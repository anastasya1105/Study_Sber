package study.lesson3.generics.wildcard;

import study.lesson3.generics.wildcard.model.Animal;
import study.lesson3.generics.wildcard.model.Cat;
import study.lesson3.generics.wildcard.model.Dog;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WildcardBasics2 {
    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog("d1"));
        dogs.add(new Dog("d2"));

        List<Cat> cats = new ArrayList<>();
        cats.add(new Cat("c2"));

        Comparator<Animal> animalComparator = new Comparator<Animal>() {
            @Override
            public int compare(Animal o1, Animal o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        Comparator<Object> objectComparator = new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                return Integer.compare(o1.hashCode(), o2.hashCode());
            }
        };

        max(dogs, animalComparator);
        max(dogs, objectComparator);

        max(dogs, new Comparator<Dog>() {
            @Override
            public int compare(Dog o1, Dog o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

    static <T extends Animal> Animal max(List<? extends T> animals, Comparator<? super T> comparator) {
        if (animals.isEmpty()) return null;

        T max = animals.get(0);
        for (T animal : animals) {
            if (comparator.compare(max, animal) < 0) {
                max = animal;
            }
        }
        return max;
    }
}
