package gui;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;

class DlgLineTest {
	private DlgLine dialog;

	@BeforeEach
	void setUp() {
		dialog = new DlgLine();
	}

	@Test
	void testCheckIfNumberWithValidInput() {
		assertTrue(dialog.CheckIfNumber("50"), "Treba da vrati true za validan ceo broj.");
		assertTrue(dialog.CheckIfNumber("0"), "Treba da vrati true za nulu.");
	}

	@Test
	void testCheckIfNumberWithInvalidInput() {
		assertFalse(dialog.CheckIfNumber("text"), "Treba da vrati false za tekst.");
		assertFalse(dialog.CheckIfNumber(""), "Treba da vrati false za prazan string.");
		assertFalse(dialog.CheckIfNumber(null), "Treba da vrati false za null.");
		assertFalse(dialog.CheckIfNumber("12.3"), "Treba da vrati false za decimalni broj.");
	}

	@Test
	void testInitialState() {
		assertFalse(dialog.isCancelClicked(), "Inicijalno cancelClicked treba da bude false.");
		assertNull(dialog.getC1(), "Inicijalna boja c1 treba da bude null.");
	}

	@Test
	void testSetCancelClicked() {
		dialog.setCancelClicked(true);
		assertTrue(dialog.isCancelClicked(), "Stanje cancelClicked treba da bude true nakon setovanja.");
	}

	@Test
	void testColorGetterSetter() {
		dialog.setC1(Color.BLUE);
		assertEquals(Color.BLUE, dialog.getC1(), "Boja treba da bude ispravno postavljena i pročitana.");
	}

	@Test
	void testTextFieldValues() {
		dialog.getTxtX1().setText("10");
		dialog.getTxtY1().setText("20");
		dialog.getTxtX2().setText("30");
		dialog.getTxtY2().setText("40");
		
		assertEquals("10", dialog.getTxtX1().getText());
		assertEquals("20", dialog.getTxtY1().getText());
		assertEquals("30", dialog.getTxtX2().getText());
		assertEquals("40", dialog.getTxtY2().getText());
	}

	@Test
	void testInitialFieldState() {
		assertEquals("", dialog.getTxtX1().getText());
		assertEquals("", dialog.getTxtY1().getText());
		assertEquals("", dialog.getTxtX2().getText());
		assertEquals("", dialog.getTxtY2().getText());
	}
}