package m23_oop_overloading_overriding_abstract.practice.task07;

import java.util.ArrayList;

public class ShapeUtils {
    public static double totalArea(ArrayList<Shape> shapes) {
        double totalAreaOfAllShapes = 0;
        for (Shape shape : shapes) {
            totalAreaOfAllShapes = totalAreaOfAllShapes + shape.area();
        }
        return totalAreaOfAllShapes;
    }

    public static Shape largestAreaOfShape(ArrayList<Shape> shapes) {
        if (shapes == null || shapes.isEmpty()) {
            return null; // или бросить исключение, если это недопустимо
        }

        Shape maxShape = shapes.get(0);
        for (int i = 1; i < shapes.size(); i++) {
            Shape current = shapes.get(i);
            if (current.isLargerThan(maxShape)) {
                maxShape = current;
            }
        }
        return maxShape;
    }
}
