package geometry;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;

class DonutTest {
	private Donut donut;
	private Point center;

	@BeforeEach
	void setUp() {
		center = new Point(10, 10);
		donut = new Donut(center, 10, 5);
	}

	@Test
	void testArea() {
		// Površina prstena
		double expectedArea = (Math.pow(10, 2) * Math.PI) - (Math.pow(5, 2) * Math.PI);
		assertEquals(expectedArea, donut.area(), 0.0001, "Površina krofne treba da bude 75 * PI.");
	}

	@Test
	void testContains() {
		// Tačka u samom prstenu (distanca 7 od centra)
		assertTrue(donut.contains(17, 10), "Tačka na distanci 7 treba da bude unutar krofne.");
		
		// Tačka u rupi (distanca 3 od centra)
		assertFalse(donut.contains(13, 10), "Tačka unutar unutrašnjeg radijusa (rupa) ne treba da bude detektovana.");
		
		// Tačka van krofne (distanca 15 od centra)
		assertFalse(donut.contains(25, 10), "Tačka van spoljašnjeg radijusa ne treba da bude detektovana.");
		
		// Granični slučajevi (na ivicama)
		assertTrue(donut.contains(15, 10), "Tačka na unutrašnjoj ivici treba da bude detektovana.");
		assertTrue(donut.contains(20, 10), "Tačka na spoljašnjoj ivici treba da bude detektovana.");
	}

	@Test
	void testEquals() {
		Donut sameDonut = new Donut(new Point(10, 10), 10, 5);
		Donut diffInner = new Donut(new Point(10, 10), 10, 4);
		
		assertTrue(donut.equals(sameDonut), "Krofne sa istim parametrima moraju biti jednake.");
		assertFalse(donut.equals(diffInner), "Krofne sa različitim unutrašnjim radijusom ne smeju biti jednake.");
	}

	@Test
	void testMoveToAndMoveBy() {
		// Test moveTo
		donut.moveTo(50, 50);
		assertEquals(50, donut.getCenter().getX(), "Centar X treba da bude 50.");
		
		// Test moveBy
		donut.moveBy(10, 10);
		assertEquals(60, donut.getCenter().getX(), "Centar X treba da bude 60.");
	}

	@Test
	void testCompareTo() {
		// Veća krofna
		Donut biggerDonut = new Donut(new Point(0, 0), 20, 5);
		
		assertTrue(donut.compareTo(biggerDonut) < 0, "Originalna krofna treba da bude manja od biggerDonut.");
		assertEquals(0, donut.compareTo(donut), "Poređenje krofne sa samom sobom treba da vrati 0.");
	}

	@Test
	void testClone() {
		Donut target = new Donut(new Point(0,0), 1, 1);
		donut.setBorderColor(Color.BLACK);
		donut.setFillColor(Color.YELLOW);
		
		donut.clone(target);
		
		assertEquals(donut.getRadius(), target.getRadius(), "Spoljašnji radijus mora biti prekopiran.");
		assertEquals(donut.getInnerRadius(), target.getInnerRadius(), "Unutrašnji radijus mora biti prekopiran.");
		assertEquals(Color.YELLOW, target.getFillColor(), "Boja unutrašnjosti mora biti ista.");
	}
}