package geometry;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;
import hexagon.Hexagon;

class HexagonAdapterTest {
	private HexagonAdapter hexagonAdapter;
	private Hexagon innerHexagon;

	@BeforeEach
	void setUp() {
		innerHexagon = new Hexagon(10, 10, 20);
		hexagonAdapter = new HexagonAdapter(innerHexagon, false, Color.BLACK, Color.WHITE);
	}

	@Test
	void testMoveTo() {
		hexagonAdapter.moveTo(50, 50);
		
		assertEquals(50, hexagonAdapter.getHexagon().getX(), "X koordinata unutrašnjeg heksagona treba da bude 50.");
		assertEquals(50, hexagonAdapter.getHexagon().getY(), "Y koordinata unutrašnjeg heksagona treba da bude 50.");
	}

	@Test
	void testMoveBy() {
		hexagonAdapter.moveBy(5, 10);
		
		assertEquals(15, hexagonAdapter.getHexagon().getX(), "X koordinata treba da bude 15 (10 + 5).");
		assertEquals(20, hexagonAdapter.getHexagon().getY(), "Y koordinata treba da bude 20 (10 + 10).");
	}

	@Test
	void testContains() {
		assertTrue(hexagonAdapter.contains(12, 12), "Tačka (12,12) treba da bude unutar heksagona.");
		assertFalse(hexagonAdapter.contains(100, 100), "Tačka (100,100) treba da bude van heksagona.");
	}

	@Test
	void testSetColors() {
		hexagonAdapter.setBorderColor(Color.RED);
		hexagonAdapter.setFillColor(Color.BLUE);
		
		assertEquals(Color.RED, hexagonAdapter.getBorderColor(), "Border color u adapteru treba da bude crvena.");
		assertEquals(Color.RED, hexagonAdapter.getHexagon().getBorderColor(), "Border color u biblioteci treba da bude crvena.");
		assertEquals(Color.BLUE, hexagonAdapter.getHexagon().getAreaColor(), "Fill color u biblioteci treba da bude plava.");
	}

	@Test
	void testCompareTo() {
		// Veći heksagon (r=30)
		Hexagon largerHex = new Hexagon(0, 0, 30);
		HexagonAdapter largerAdapter = new HexagonAdapter(largerHex, false, Color.BLACK, Color.WHITE);
		
		// Manji heksagon (r=10)
		Hexagon smallerHex = new Hexagon(0, 0, 10);
		HexagonAdapter smallerAdapter = new HexagonAdapter(smallerHex, false, Color.BLACK, Color.WHITE);
		
		assertTrue(hexagonAdapter.compareTo(largerAdapter) < 0, "Originalni heksagon (r=20) treba da bude manji od (r=30).");
		assertTrue(hexagonAdapter.compareTo(smallerAdapter) > 0, "Originalni heksagon (r=20) treba da bude veći od (r=10).");
		assertEquals(0, hexagonAdapter.compareTo(hexagonAdapter), "Poređenje sa samim sobom treba da vrati 0.");
	}

	@Test
	void testClone() {
		HexagonAdapter target = new HexagonAdapter(new Hexagon(0, 0, 0), false, Color.GRAY, Color.GRAY);
		
		hexagonAdapter.setBorderColor(Color.GREEN);
		hexagonAdapter.clone(target);
		
		assertEquals(hexagonAdapter.getHexagon().getX(), target.getHexagon().getX(), "X koordinata mora biti prekopirana.");
		assertEquals(hexagonAdapter.getHexagon().getR(), target.getHexagon().getR(), "Radijus mora biti prekopiran.");
		assertEquals(Color.GREEN, target.getBorderColor(), "Boja ivice mora biti prekopirana.");
	}
}