package geometry;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;

class CircleTest {
	private Circle circle;
	private Point center;

	@BeforeEach
	void setUp() {
		center = new Point(10, 10);
		circle = new Circle(center, 10);
	}

	@Test
	void testAreaAndCircumference() {
		// Površina
		assertEquals(Math.PI * 100, circle.area(), 0.0001, "Površina kruga treba da bude 100 * PI.");
		
		// Obim
		assertEquals(Math.PI * 20, circle.circumference(), 0.0001, "Obim kruga treba da bude 20 * PI.");
	}

	@Test
	void testContains() {
		// Tačka na distanci 5 od centra (unutar kruga)
		assertTrue(circle.contains(10, 15), "Tačka (10,15) treba da bude unutar kruga.");
		
		// Tačka na samoj ivici (distanca 10)
		assertTrue(circle.contains(20, 10), "Tačka na ivici (20,10) treba da bude unutar kruga.");
		
		// Tačka van kruga (distanca > 10)
		assertFalse(circle.contains(25, 25), "Tačka (25,25) treba da bude van kruga.");
	}

	@Test
	void testSetRadiusException() {
		// Provera da li se baca izuzetak za negativan poluprečnik ili nulu
		assertThrows(Exception.class, () -> circle.setRadius(-5), "Treba baciti izuzetak za negativan poluprečnik.");
		assertThrows(Exception.class, () -> circle.setRadius(0), "Treba baciti izuzetak za poluprečnik 0.");
	}

	@Test
	void testMoveToAndMoveBy() {
		// Test moveTo
		circle.moveTo(30, 30);
		assertEquals(30, circle.getCenter().getX(), "Centar X treba da bude 30.");
		
		// Test moveBy
		circle.moveBy(10, 5);
		assertEquals(40, circle.getCenter().getX(), "Centar X treba da bude 40.");
		assertEquals(35, circle.getCenter().getY(), "Centar Y treba da bude 35.");
	}

	@Test
	void testEquals() {
		Circle sameCircle = new Circle(new Point(10, 10), 10);
		Circle differentCircle = new Circle(new Point(10, 10), 5);
		
		assertTrue(circle.equals(sameCircle), "Krugovi sa istim centrom i poluprečnikom treba da budu jednaki.");
		assertFalse(circle.equals(differentCircle), "Krugovi sa različitim poluprečnikom ne treba da budu jednaki.");
	}

	@Test
	void testClone() {
		Circle target = new Circle(new Point(0, 0), 1);
		circle.setBorderColor(Color.RED);
		circle.setFillColor(Color.GREEN);
		
		circle.clone(target);
		
		assertEquals(circle.getRadius(), target.getRadius(), "Poluprečnik mora biti prekopiran.");
		assertEquals(circle.getCenter().getX(), target.getCenter().getX(), "Pozicija centra mora biti prekopirana.");
		assertEquals(Color.RED, target.getBorderColor(), "Boja ivice mora biti prekopirana.");
		assertEquals(Color.GREEN, target.getFillColor(), "Boja unutrašnjosti mora biti prekopirana.");
	}

	@Test
	void testCompareTo() {
		// Veći krug
		Circle bigger = new Circle(new Point(0, 0), 20);
		
		assertTrue(circle.compareTo(bigger) < 0, "Originalni krug treba da bude manji od bigger kruga.");
		assertEquals(0, circle.compareTo(new Circle(new Point(5, 5), 10)), "Krugovi sa istim r treba da vrate 0.");
	}
}