package problem2;

public class Main {

    public static void main(String[] args) {
        Point topLeft = new Point(0, 2);
        Point topRight = new Point(2, 2);
        Point bottomRight = new Point(2, 0);

        Rectangle rectangle = new Rectangle(topLeft, topRight, bottomRight);

        Point point = new Point(1, 1);
        if (rectangle.contains(point)) {
            System.out.println("The rectangle contains the point.");
        } else {
            System.out.println("The rectangle does not contain the point.");
        }
    }
}