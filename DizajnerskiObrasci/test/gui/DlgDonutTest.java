package gui;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;

class DlgDonutTest {
	private DlgDonut dialog;

	@BeforeEach
	void setUp() {
		dialog = new DlgDonut();
	}

	@Test
	void testCheckIfNumberWithValidInput() {
		assertTrue(dialog.isNumeric("15"), "Treba da vrati true za validan ceo broj.");
		assertTrue(dialog.isNumeric("0"), "Treba da vrati true za nulu.");
	}

	@Test
	void testCheckIfNumberWithInvalidInput() {
		assertFalse(dialog.isNumeric("xyz"), "Treba da vrati false za slova.");
		assertFalse(dialog.isNumeric(""), "Treba da vrati false za prazan string.");
		assertFalse(dialog.isNumeric(null), "Treba da vrati false za null.");
		assertFalse(dialog.isNumeric("20.5"), "Treba da vrati false za decimalni broj.");
	}

	@Test
	void testCancelClickedInitialState() {
		assertFalse(dialog.isCancelClicked(), "Inicijalno cancelClicked treba da bude false.");
	}

	@Test
	void testSetCancelClicked() {
		dialog.setCancelClicked(true);
		assertTrue(dialog.isCancelClicked(), "Stanje cancelClicked treba da bude true nakon setovanja.");
	}

	@Test
	void testColorSettersAndGetters() {
		dialog.setBorderColor(Color.BLACK);
		dialog.setFillColor(Color.YELLOW);
		
		assertEquals(Color.BLACK, dialog.getBorderColor(), "Boja ivice treba da bude crna.");
		assertEquals(Color.YELLOW, dialog.getFillColor(), "Boja unutrašnjosti treba da bude žuta.");
	}

	@Test
	void testTextFieldValues() {
		dialog.getTxtX().setText("50");
		dialog.getTxtY().setText("50");
		dialog.getTxtOuterRadius().setText("20");
		dialog.getTxtInnerRadius().setText("10");
		
		assertEquals("50", dialog.getTxtX().getText());
		assertEquals("50", dialog.getTxtY().getText());
		assertEquals("20", dialog.getTxtOuterRadius().getText());
		assertEquals("10", dialog.getTxtInnerRadius().getText());
	}

	@Test
	void testInitialFieldState() {
		assertEquals("", dialog.getTxtX().getText());
		assertEquals("", dialog.getTxtY().getText());
		assertEquals("", dialog.getTxtOuterRadius().getText());
		assertEquals("", dialog.getTxtInnerRadius().getText());
	}
}