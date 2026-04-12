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
    void testIsNumericValid() {
        // Metoda je promenjena iz checkIfNumber u isNumeric
        assertTrue(dialog.isNumeric("100"), "Treba da vrati true za validan broj.");
        assertTrue(dialog.isNumeric("0"), "Treba da vrati true za nulu.");
    }

    @Test
    void testIsNumericInvalid() {
        assertFalse(dialog.isNumeric("rect"), "Treba da vrati false za tekst.");
        assertFalse(dialog.isNumeric(""), "Treba da vrati false za prazan string.");
        assertFalse(dialog.isNumeric(null), "Treba da vrati false za null.");
    }

    @Test
    void testInitialState() {
        assertFalse(dialog.isCancelClicked(), "Inicijalno cancelClicked treba da bude false.");
        
        // U refaktorisanoj klasi smo inicijalizovali boje, pa proveravamo te vrednosti
        assertEquals(Color.BLACK, dialog.getBorderColor(), "Inicijalna boja ivice treba da bude crna.");
        assertEquals(Color.WHITE, dialog.getFillColor(), "Inicijalna boja unutrašnjosti treba da bude bela.");
    }

    @Test
    void testSetCancelClicked() {
        dialog.setCancelClicked(true);
        assertTrue(dialog.isCancelClicked(), "Stanje cancelClicked treba da bude true nakon setovanja.");
    }

    @Test
    void testColorSettersAndGetters() {
        dialog.setBorderColor(Color.RED);
        dialog.setFillColor(Color.BLUE);
        
        assertEquals(Color.RED, dialog.getBorderColor(), "Boja ivice (borderColor) treba da bude crvena.");
        assertEquals(Color.BLUE, dialog.getFillColor(), "Boja unutrašnjosti (fillColor) treba da bude plava.");
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
        // Proveravamo da li su polja prazna pri pokretanju dijaloga
        assertEquals("", dialog.getTxtX().getText());
        assertEquals("", dialog.getTxtY().getText());
        assertEquals("", dialog.getTxtWidth().getText());
        assertEquals("", dialog.getTxtHeight().getText());
    }
}