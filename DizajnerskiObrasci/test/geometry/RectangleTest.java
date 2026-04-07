package geometry;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;

class RectangleTest {
	private Rectangle rectangle;
	private Point upperLeftPoint;

	@BeforeEach
	void setUp() {
		upperLeftPoint = new Point(10, 10);
		rectangle = new Rectangle(upperLeftPoint, 50, 30);
	}

	@Test
	void testAreaAndCircumference() {
		// Površina: 50 * 30 = 1500
		assertEquals(1500, rectangle.area(), "Površina pravougaonika treba da bude 1500.");
		
		// Obim: 2 * (50 + 30) = 160
		assertEquals(160, rectangle.circumference(), "Obim pravougaonika treba da bude 160.");
	}

	@Test
	void testContainsPoint() {
		// Tačka unutar pravougaonika
		assertTrue(rectangle.contains(20, 20), "Tačka (20,20) treba da bude unutar pravougaonika.");
		
		// Tačka na samoj ivici
		assertTrue(rectangle.contains(10, 10), "Gornja leva tačka treba da bude unutar pravougaonika.");
		
		// Tačka van pravougaonika
		assertFalse(rectangle.contains(5, 5), "Tačka (5,5) treba da bude van pravougaonika.");
		assertFalse(rectangle.contains(70, 50), "Tačka (70,50) treba da bude van pravougaonika.");
	}

	@Test
	void testMoveToAndMoveBy() {
		// Test moveTo
		rectangle.moveTo(50, 50);
		assertEquals(50, rectangle.getUpperLeftPoint().getX(), "X koordinata treba da bude 50 nakon moveTo.");
		
		// Test moveBy
		rectangle.moveBy(10, 5);
		assertEquals(60, rectangle.getUpperLeftPoint().getX(), "X koordinata treba da bude 60 nakon moveBy.");
		assertEquals(55, rectangle.getUpperLeftPoint().getY(), "Y koordinata treba da bude 55 nakon moveBy.");
	}

	@Test
	void testEquals() {
		Rectangle sameRectangle = new Rectangle(new Point(10, 10), 50, 30);
		Rectangle differentRectangle = new Rectangle(new Point(10, 10), 40, 30);
		
		assertTrue(rectangle.equals(sameRectangle), "Pravougaonici sa istim parametrima treba da budu jednaki.");
		assertFalse(rectangle.equals(differentRectangle), "Pravougaonici sa različitom širinom ne treba da budu jednaki.");
	}

	@Test
	void testClone() {
		Rectangle cloned = new Rectangle(new Point(0, 0), 1, 1);
		rectangle.setBorderColor(Color.RED);
		rectangle.setFillColor(Color.BLUE);
		
		rectangle.clone(cloned);
		
		assertEquals(rectangle.getWidth(), cloned.getWidth(), "Širina kloniranog pravougaonika treba da bude ista.");
		assertEquals(rectangle.getUpperLeftPoint().getX(), cloned.getUpperLeftPoint().getX(), "X koordinata treba da bude ista.");
		assertEquals(Color.RED, cloned.getBorderColor(), "Boja ivice treba da bude ista.");
		assertEquals(Color.BLUE, cloned.getFillColor(), "Boja unutrašnjosti treba da bude ista.");
	}

	@Test
	void testCompareTo() {
		// Veći pravougaonik (površina 2000)
		Rectangle bigger = new Rectangle(new Point(0, 0), 40, 50);
		// Manji pravougaonik (površina 200)
		Rectangle smaller = new Rectangle(new Point(0, 0), 10, 20);
		
		assertTrue(rectangle.compareTo(bigger) < 0, "Originalni pravougaonik (1500) treba da bude manji od bigger (2000).");
		assertTrue(rectangle.compareTo(smaller) > 0, "Originalni pravougaonik (1500) treba da bude veći od smaller (200).");
		assertEquals(0, rectangle.compareTo(rectangle), "Pravougaonik treba da bude jednak samom sebi.");
	}
}