package helpers;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.MouseEvent;

import javax.swing.JButton;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import geometry.*;
import mvc.DrawingFrame;

class ShapeHandlerTest {

	private ShapeHandler shapeHandler;
	private DrawingFrame frame;
	private MouseEvent mouseEvent;

	@BeforeEach
	void setUp() {
		frame = new DrawingFrame();
		shapeHandler = new ShapeHandler(frame);
		mouseEvent = new MouseEvent(new JButton(), 0, System.currentTimeMillis(), 0, 10, 10, 1, false);
	}

	@Test
	void testPrepareNewShape_InvalidSelection() {
		Shape result = shapeHandler.prepareNewShape("NonExistent", mouseEvent, null);
		assertNull(result);
	}

	@Test
	void testPrepareNewShape_LineWithoutStartPoint() {
		Shape result = shapeHandler.prepareNewShape("Line", mouseEvent, null);
		assertNull(result);
	}

	@Test
	void testPrepareNewShape_Point_Cancel() {
		Shape result = shapeHandler.prepareNewShape("Point", mouseEvent, null);
		assertNull(result);
	}

	@Test
	void testPrepareNewShape_Rectangle_Cancel() {
		Shape result = shapeHandler.prepareNewShape("Rectangle", mouseEvent, null);
		assertNull(result);
	}

	@Test
	void testPrepareNewShape_Circle_Cancel() {
		Shape result = shapeHandler.prepareNewShape("Circle", mouseEvent, null);
		assertNull(result);
	}

	@Test
	void testPrepareNewShape_Donut_Cancel() {
		Shape result = shapeHandler.prepareNewShape("Donut", mouseEvent, null);
		assertNull(result);
	}

	@Test
	void testPrepareNewShape_Hexagon_Cancel() {
		Shape result = shapeHandler.prepareNewShape("Hexagon", mouseEvent, null);
		assertNull(result);
	}

	@Test
	void testPrepareUpdateShape_Point_Cancel() {
		Point p = new Point(10, 10, false, frame.getBorderColor());
		Shape result = shapeHandler.prepareUpdateShape(p);
		assertNull(result);
	}

	@Test
	void testPrepareUpdateShape_Line_Cancel() {
		Line l = new Line(new Point(0, 0), new Point(10, 10), false, frame.getBorderColor());
		Shape result = shapeHandler.prepareUpdateShape(l);
		assertNull(result);
	}

	@Test
	void testPrepareUpdateShape_Rectangle_Cancel() {
		Rectangle r = new Rectangle(new Point(5, 5), 20, 30, false, frame.getBorderColor(), frame.getFillColor());
		Shape result = shapeHandler.prepareUpdateShape(r);
		assertNull(result);
	}

	@Test
	void testPrepareUpdateShape_Circle_Cancel() {
		Circle c = new Circle(new Point(10, 10), 15, false, frame.getBorderColor(), frame.getFillColor());
		Shape result = shapeHandler.prepareUpdateShape(c);
		assertNull(result);
	}

	@Test
	void testPrepareUpdateShape_Donut_Cancel() {
		Donut d = new Donut(new Point(10, 10), 20, 10, false, frame.getBorderColor(), frame.getFillColor());
		Shape result = shapeHandler.prepareUpdateShape(d);
		assertNull(result);
	}

	@Test
	void testPrepareUpdateShape_Hexagon_Cancel() {
		HexagonAdapter h = new HexagonAdapter(new hexagon.Hexagon(10, 10, 20), false, frame.getBorderColor(),
				frame.getFillColor());
		Shape result = shapeHandler.prepareUpdateShape(h);
		assertNull(result);
	}

	@Test
	void testPrepareUpdateShape_Null() {
		Shape result = shapeHandler.prepareUpdateShape(null);
		assertNull(result);
	}

	@Test
	void testPrepareNewShape_ExceptionHandling() {
		Shape result = shapeHandler.prepareNewShape(null, null, null);
		assertNull(result);
	}
}