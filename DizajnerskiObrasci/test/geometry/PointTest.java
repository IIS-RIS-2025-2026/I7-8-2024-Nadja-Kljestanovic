package geometry;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;

class PointTest {
	private Point point;

	@BeforeEach
	void setUp() {
		// Inicijalizacija tačke na (10, 10)
		point = new Point(10, 10);
	}

	@Test
	void testDistance() {
		// Distanca od (10,10) do (13,14) je 5
		double dist = point.distance(13, 14);
		assertEquals(5.0, dist, 0.0001, "Distanca između (10,10) i (13,14) treba da bude 5.");
	}

	@Test
	void testContains() {
		// Tačka (11,11) je na distanci ~1.41, što je <= 2
		assertTrue(point.contains(11, 11), "Tačka (11,11) treba da bude unutar opsega detekcije.");
		
		// Tačka (13,10) je na distanci 3, što je > 2
		assertFalse(point.contains(13, 10), "Tačka (13,10) treba da bude van opsega detekcije.");
		
		// Provera preklopljene metode sa objektom Point
		Point clickPoint = new Point(10, 12);
		assertTrue(point.contains(clickPoint), "Metoda treba ispravno da detektuje Point objekat na distanci 2.");
	}

	@Test
	void testMoveToAndMoveBy() {
		// Test moveTo
		point.moveTo(50, 50);
		assertEquals(50, point.getX(), "X treba da bude 50.");
		assertEquals(50, point.getY(), "Y treba da bude 50.");
		
		// Test moveBy
		point.moveBy(5, -10);
		assertEquals(55, point.getX(), "X treba da bude 55 (50 + 5).");
		assertEquals(40, point.getY(), "Y treba da bude 40 (50 - 10).");
	}

	@Test
	void testEquals() {
		Point samePoint = new Point(10, 10);
		Point differentPoint = new Point(11, 10);
		
		assertTrue(point.equals(samePoint), "Tačke sa istim koordinatama treba da budu jednake.");
		assertFalse(point.equals(differentPoint), "Tačke sa različitim koordinatama ne treba da budu jednake.");
		assertFalse(point.equals(new Object()), "Poređenje sa objektom koji nije Point treba da vrati false.");
	}

	@Test
	void testCompareTo() {
		// Tačka na (0,0) ima distancu 0 od koordinatnog početka
		Point center = new Point(0, 0);
		// Tačka na (20,20) je dalje od (10,10)
		Point furtherPoint = new Point(20, 20);
		
		assertTrue(point.compareTo(center) > 0, "Tačka (10,10) je dalje od početka nego (0,0).");
		assertTrue(point.compareTo(furtherPoint) < 0, "Tačka (10,10) je bliže početku nego (20,20).");
		assertEquals(0, point.compareTo(new Point(10, 10)), "Tačke na istoj udaljenosti treba da vrate 0.");
	}

	@Test
	void testClone() {
		Point target = new Point();
		point.setBorderColor(Color.RED);
		
		point.clone(target);
		
		assertEquals(point.getX(), target.getX(), "X koordinata mora biti prekopirana.");
		assertEquals(point.getY(), target.getY(), "Y koordinata mora biti prekopirana.");
		assertEquals(Color.RED, target.getBorderColor(), "Boja mora biti prekopirana.");
	}
}