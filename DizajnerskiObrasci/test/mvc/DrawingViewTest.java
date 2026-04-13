package mvc;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;

class DrawingViewTest {

    private DrawingView view;
    private DrawingModel model;

    @BeforeEach
    void setUp() {
        view = new DrawingView();
        model = new DrawingModel();
    }

    @Test
    void testInitialBackgroundColor() {
        assertEquals(Color.WHITE, view.getBackground(), "Pozadina panela mora biti bela.");
    }

    @Test
    void testSetAndGetModel() {
        // Provera logike povezivanja Modela i View-a
        view.setModel(model);
        
        assertNotNull(view.getModel(), "Model ne sme biti null nakon postavljanja.");
        assertEquals(model, view.getModel(), "Vraćeni model mora biti identičan kao i postavljeni model.");
    }

    @Test
    void testModelNotNullByDefault() {
        assertNotNull(view.getModel(), "Inicijalni model ne sme da bude null.");
    }
}