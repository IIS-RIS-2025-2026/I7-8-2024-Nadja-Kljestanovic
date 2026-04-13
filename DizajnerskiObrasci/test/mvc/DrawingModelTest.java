package mvc;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import geometry.Point;

class DrawingModelTest {

	private DrawingModel model;
	private Point testPoint;

	@BeforeEach
	void setUp() {
		model = new DrawingModel();
		testPoint = new Point(10, 10);
	}

	@Test
	void testAddShape() {
		model.add(testPoint);
		assertEquals(1, model.size());
		assertEquals(testPoint, model.get(0));
	}

	@Test
	void testRemoveShape() {
		model.add(testPoint);
		model.remove(testPoint);
		assertTrue(model.isEmpty());
	}

	@Test
	void testAddAtIndex() {
		Point secondPoint = new Point(20, 20);
		model.add(testPoint);
		model.addAtIndex(0, secondPoint);
		
		assertEquals(secondPoint, model.get(0));
		assertEquals(testPoint, model.get(1));
		assertEquals(2, model.size());
	}

	@Test
	void testGetShapesList() {
		model.add(testPoint);
		assertNotNull(model.getShapes());
		assertEquals(1, model.getShapes().size());
	}
}