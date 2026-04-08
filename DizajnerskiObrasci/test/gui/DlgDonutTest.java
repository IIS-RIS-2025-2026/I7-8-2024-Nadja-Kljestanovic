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
		assertTrue(dialog.CheckIfNumber("15"), "Treba da vrati true za validan ceo broj.");
		assertTrue(dialog.CheckIfNumber("0"), "Treba da vrati true za nulu.");
	}

	@Test
	void testCheckIfNumberWithInvalidInput() {
		assertFalse(dialog.CheckIfNumber("xyz"), "Treba da vrati false za slova.");
		assertFalse(dialog.CheckIfNumber(""), "Treba da vrati false za prazan string.");
		assertFalse(dialog.CheckIfNumber(null), "Treba da vrati false za null.");
		assertFalse(dialog.CheckIfNumber("20.5"), "Treba da vrati false za decimalni broj.");
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
		dialog.setC1(Color.BLACK);
		dialog.setC2(Color.YELLOW);
		
		assertEquals(Color.BLACK, dialog.getC1(), "Boja ivice treba da bude crna.");
		assertEquals(Color.YELLOW, dialog.getC2(), "Boja unutrašnjosti treba da bude žuta.");
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