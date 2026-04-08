package gui;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;

class DlgRectangleTest {
	private DlgRectangle dialog;

	@BeforeEach
	void setUp() {
		dialog = new DlgRectangle();
	}

	@Test
	void testCheckIfNumberValid() {
		assertTrue(dialog.CheckIfNumber("100"), "Treba da vrati true za validan broj.");
		assertTrue(dialog.CheckIfNumber("0"), "Treba da vrati true za nulu.");
	}

	@Test
	void testCheckIfNumberInvalid() {
		assertFalse(dialog.CheckIfNumber("rect"), "Treba da vrati false za tekst.");
		assertFalse(dialog.CheckIfNumber(""), "Treba da vrati false za prazan string.");
		assertFalse(dialog.CheckIfNumber(null), "Treba da vrati false za null.");
	}

	@Test
	void testInitialState() {
		assertFalse(dialog.isCancelClicked(), "Inicijalno cancelClicked treba da bude false.");
		assertNull(dialog.getC1(), "Inicijalna boja ivice treba da bude null.");
		assertNull(dialog.getC2(), "Inicijalna boja unutrašnjosti treba da bude null.");
	}

	@Test
	void testSetCancelClicked() {
		dialog.setCancelClicked(true);
		assertTrue(dialog.isCancelClicked(), "Stanje cancelClicked treba da bude true nakon setovanja.");
	}

	@Test
	void testColorSettersAndGetters() {
		dialog.setC1(Color.RED);
		dialog.setC2(Color.BLUE);
		
		assertEquals(Color.RED, dialog.getC1(), "Boja ivice (c1) treba da bude crvena.");
		assertEquals(Color.BLUE, dialog.getC2(), "Boja unutrašnjosti (c2) treba da bude plava.");
	}

	@Test
	void testTextFieldValues() {
		dialog.getTxtX().setText("10");
		dialog.getTxtY().setText("20");
		dialog.getTxtWidth().setText("100");
		dialog.getTxtHeight().setText("50");
		
		assertEquals("10", dialog.getTxtX().getText());
		assertEquals("20", dialog.getTxtY().getText());
		assertEquals("100", dialog.getTxtWidth().getText());
		assertEquals("50", dialog.getTxtHeight().getText());
	}

	@Test
	void testInitialFieldState() {
		assertEquals("", dialog.getTxtX().getText());
		assertEquals("", dialog.getTxtY().getText());
		assertEquals("", dialog.getTxtWidth().getText());
		assertEquals("", dialog.getTxtHeight().getText());
	}
}