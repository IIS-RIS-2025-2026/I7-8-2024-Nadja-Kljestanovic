package helpers;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import geometry.*;
import java.awt.Color;

class LogParserTest {

    private LogParser logParser;

    @BeforeEach
    void setUp() {
        logParser = new LogParser();
    }

    @Test
    void testParseNumbers_Extraction() {
        String log = "Added Point Point: x=10, y=20, color=java.awt.Color[r=0,g=0,b=0]";
        int[] result = logParser.parseNumbers(log);
        
        assertNotNull(result);
        assertEquals(12, result.length);
        
        assertEquals(10, result[0], "X treba da bude 10");
        assertEquals(20, result[1], "Y treba da bude 20");
        assertEquals(0, result[3], "R komponenta treba da bude 0");
        assertEquals(0, result[4], "G komponenta treba da bude 0");
        assertEquals(0, result[5], "B komponenta treba da bude 0");
    }

    

    @Test
    void testCreatePoint() {
        int[] n = {10, 20, 255, 0, 0, 0, 0};
        Shape shape = logParser.createShape("Point", n);
        
        assertTrue(shape instanceof Point);
        Point p = (Point) shape;
        assertEquals(10, p.getX());
        assertEquals(20, p.getY());
        assertEquals(Color.RED, p.getBorderColor());
    }

    @Test
    void testCreateLine() {
        int[] n = {0, 0, 100, 100, 0, 255, 0};
        Shape shape = logParser.createShape("Line", n);
        
        assertTrue(shape instanceof Line);
        Line l = (Line) shape;
        assertEquals(0, l.getStartPoint().getX());
        assertEquals(100, l.getEndPoint().getX());
        assertEquals(Color.GREEN, l.getBorderColor());
    }
    
    @Test
    void testCreateRectangle() {
        int[] n = {10, 20, 50, 80, 0, 0, 0, 255, 255, 255};
        Shape shape = logParser.createShape("Rectangle", n);
        
        assertNotNull(shape);
        assertTrue(shape instanceof Rectangle);
        Rectangle r = (Rectangle) shape;

        assertEquals(80, r.getHeight(), "Height bi trebao biti 80");
        assertEquals(50, r.getWidth(), "Width bi trebao biti 50");
        
        assertEquals(Color.BLACK, r.getBorderColor());
        assertEquals(Color.WHITE, r.getFillColor());
    }

    @Test
    void testCreateCircle() {
        int[] n = {100, 100, 40, 255, 0, 0, 0, 0, 255};
        Shape shape = logParser.createShape("Circle", n);
        
        assertTrue(shape instanceof Circle);
        Circle c = (Circle) shape;
        assertEquals(40, c.getRadius());
        assertEquals(Color.RED, c.getBorderColor());
        assertEquals(Color.BLUE, c.getFillColor());
    }

    @Test
    void testCreateDonut() {
        int[] n = {150, 150, 60, 30, 0, 0, 0, 255, 255, 0};
        Shape shape = logParser.createShape("Donut", n);
        
        assertTrue(shape instanceof Donut);
        Donut d = (Donut) shape;
        assertEquals(60, d.getRadius());
        assertEquals(30, d.getInnerRadius());
        assertEquals(Color.YELLOW, d.getFillColor());
    }

    @Test
    void testCreateHexagon() {
        int[] n = {200, 200, 100, 0, 255, 0, 0, 0, 0};
        Shape shape = logParser.createShape("Hexagon", n);
        
        assertNotNull(shape);
        assertTrue(shape instanceof HexagonAdapter);
        assertEquals(Color.GREEN, shape.getBorderColor());
    }

    @Test
    void testCreateShape_InvalidType() {
        int[] n = new int[12];
        Shape shape = logParser.createShape("InvalidType", n);
        assertNull(shape, "Za nepoznat tip oblika treba da vrati null");
    }
}