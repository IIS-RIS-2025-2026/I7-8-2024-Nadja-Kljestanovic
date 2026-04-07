package geometry;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;

class LineTest {
	private Line line;
	private Point start;
	private Point end;

	@BeforeEach
	void setUp() {
		start = new Point(10, 10);
		end = new Point(10, 20);
		line = new Line(start, end);
	}

	@Test
	void testLength() {
		assertEquals(10.0, line.length(), 0.0001, "Dužina linije od (10,10) do (10,20) treba da bude 10.");
	}

	@Test
	void testContains() {
		// Tačka tačno na sredini linije (10, 15)
		assertTrue(line.contains(10, 15), "Tačka na sredini linije treba da bude detektovana.");
		
		// Tačka blizu linije (11, 15) - mala devijacija
		assertTrue(line.contains(11, 15), "Tačka blizu linije (1px razmaka) treba da bude detektovana.");
		
		// Tačka daleko od linije
		assertFalse(line.contains(20, 20), "Tačka daleko od linije ne treba da bude detektovana.");
	}

	@Test
	void testMoveTo() {
		// Pomeramo početnu tačku na (50, 50). 
		line.moveTo(50, 50);
		
		assertEquals(50, line.getStartPoint().getX(), "Start tačka X treba da bude 50.");
		assertEquals(50, line.getStartPoint().getY(), "Start tačka Y treba da bude 50.");
		assertEquals(50, line.getEndPoint().getX(), "End tačka X treba da bude 50 (10 + 40).");
		assertEquals(60, line.getEndPoint().getY(), "End tačka Y treba da bude 60 (20 + 40).");
	}

	@Test
	void testMoveBy() {
		line.moveBy(5, 5);
		assertEquals(15, line.getStartPoint().getX(), "Start X treba da bude 15.");
		assertEquals(25, line.getEndPoint().getY(), "End Y treba da bude 25.");
	}

	@Test
	void testEquals() {
		Line sameLine = new Line(new Point(10, 10), new Point(10, 20));
		Line differentLine = new Line(new Point(10, 10), new Point(30, 30));
		
		assertTrue(line.equals(sameLine), "Linije sa istim tačkama treba da budu jednake.");
		assertFalse(line.equals(differentLine), "Linije sa različitim krajnjim tačkama nisu jednake.");
	}

	@Test
	void testCompareTo() {
		Line longerLine = new Line(new Point(0, 0), new Point(0, 50)); // dužina 50
		
		assertTrue(line.compareTo(longerLine) < 0, "Originalna linija (10) je kraća od longerLine (50).");
		assertEquals(0, line.compareTo(new Line(new Point(100, 100), new Point(100, 110))), "Linije iste dužine treba da vrate 0.");
	}

	@Test
	void testClone() {
		Line target = new Line(new Point(0,0), new Point(1,1));
		line.setBorderColor(Color.BLUE);
		
		line.clone(target);
		
		assertEquals(line.getStartPoint().getX(), target.getStartPoint().getX(), "Početna tačka mora biti prekopirana.");
		assertEquals(line.getEndPoint().getY(), target.getEndPoint().getY(), "Krajnja tačka mora biti prekopirana.");
		assertEquals(Color.BLUE, target.getBorderColor(), "Boja mora biti prekopirana.");
	}
}