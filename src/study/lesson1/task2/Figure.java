package study.lesson1.task2;

public abstract class Figure {
    double squar;
    double perim;
    public abstract void square();
    public abstract void perimeter();
}
class Circle extends Figure {
    double radius;
    double Pi = 3.14;

    public Circle(double radius) {
        this.radius = radius;
    }
    public void square() {
        squar = Pi * radius * radius;
        System.out.println(radius);
        System.out.println(Pi);
        System.out.printf("%.2f", squar);
    }
    public void perimeter() {
        perim = 2 * Pi * radius;
        System.out.println(perim);
    }
}
class Rectangle extends Figure {
    double width;
    double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    public void square() {
        squar = width * height;
        System.out.println(squar);
    }
    public void perimeter() {
        perim = 2 * (width + height);
        System.out.println(perim);
    }
}
class Triangle extends Figure {
    double a;
    double b;
    double c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public void perimeter() {
        perim = a + b + c;
        System.out.println(perim);
    }
    public void square() {
        double p = perim / 2;
        double sc = p * (p - a) * (p - b) * (p - c);
        squar = Math.sqrt(sc);
        System.out.println(squar);
    }
}
class Square extends Figure {
    double a;

    public Square(double a) {
        this.a = a;
    }

    public void square() {
        squar = a * a;
        System.out.println(squar);
    }

    public void perimeter() {
        perim = 4 * a;
        System.out.println(perim);
    }
}
class Main {
    public static void main(String[] args) {
        Circle circle = new Circle(6);
        circle.perimeter();
        circle.square();
        Rectangle rectangle = new Rectangle(6, 7);
        rectangle.perimeter();
        rectangle.square();
        Triangle triangle = new Triangle(6, 5, 7);
        triangle.perimeter();
        triangle.square();
        Square squares = new Square(6);
        squares.perimeter();
        squares.square();
    }
}
