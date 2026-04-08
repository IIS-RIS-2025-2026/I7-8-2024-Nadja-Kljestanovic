package gui;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;

class DlgCircleTest {
	private DlgCircle dialog;

	@BeforeEach
	void setUp() {
		dialog = new DlgCircle();
	}

	@Test
	void testCheckIfNumberWithValidInput() {
		assertTrue(dialog.CheckIfNumber("10"), "Treba da vrati true za validan ceo broj.");
		assertTrue(dialog.CheckIfNumber("0"), "Treba da vrati true za nulu.");
		assertTrue(dialog.CheckIfNumber("-5"), "Treba da vrati true za negativan broj.");
	}

	@Test
	void testCheckIfNumberWithInvalidInput() {
		assertFalse(dialog.CheckIfNumber("abc"), "Treba da vrati false za tekst.");
		assertFalse(dialog.CheckIfNumber(""), "Treba da vrati false za prazan string.");
		assertFalse(dialog.CheckIfNumber(null), "Treba da vrati false za null.");
		assertFalse(dialog.CheckIfNumber("10.5"), "Treba da vrati false za decimalni broj.");
	}

	@Test
	void testCancelClickedState() {
		assertFalse(dialog.isCancelClicked(), "Inicijalno cancelClicked treba da bude false.");
		
		dialog.setCancelClicked(true);
		
		assertTrue(dialog.isCancelClicked(), "Nakon klika na Cancel, stanje treba da bude true.");
	}

	@Test
	void testColorSettersAndGetters() {
		dialog.setC1(Color.RED);
		dialog.setC2(Color.BLUE);
		
		assertEquals(Color.RED, dialog.getC1(), "Boja ivice (c1) treba da bude crvena.");
		assertEquals(Color.BLUE, dialog.getC2(), "Boja unutrašnjosti (c2) treba da bude plava.");
	}

	@Test
	void testInitialTextFieldValues() {
		assertEquals("", dialog.getTxtX().getText());
		assertEquals("", dialog.getTxtY().getText());
		assertEquals("", dialog.getTxtRadius().getText());
	}
	
	@Test
	void testTextFieldInput() {
		dialog.getTxtX().setText("100");
		dialog.getTxtY().setText("200");
		dialog.getTxtRadius().setText("50");
		
		assertEquals("100", dialog.getTxtX().getText());
		assertEquals("200", dialog.getTxtY().getText());
		assertEquals("50", dialog.getTxtRadius().getText());
	}
}