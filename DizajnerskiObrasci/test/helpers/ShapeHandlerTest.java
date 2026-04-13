package helpers;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import mvc.DrawingFrame;
import geometry.*;

class ShapeHandlerTest {

    private ShapeHandler shapeHandler;
    private DrawingFrame frame;

    @BeforeEach
    void setUp() {
        frame = new DrawingFrame();
        shapeHandler = new ShapeHandler(frame);
    }

    @Test
    void testPrepareNewShape_InvalidSelection() {
        MouseEvent e = new MouseEvent(new JButton(), 0, 0, 0, 10, 10, 1, false);
        Shape result = shapeHandler.prepareNewShape("NonExistent", e, null);
        
        assertNull(result, "Trebalo bi da vrati null za nepoznat tip oblika");
    }

    @Test
    void testPrepareNewShape_LineWithoutStartPoint() {
        MouseEvent e = new MouseEvent(new JButton(), 0, 0, 0, 10, 10, 1, false);
        Shape result = shapeHandler.prepareNewShape("Line", e, null);
        
        assertNull(result, "Linija ne može biti kreirana bez početne tačke");
    }

    @Test
    void testPrepareUpdateShape_Null() {
        Shape result = shapeHandler.prepareUpdateShape(null);
        assertNull(result);
    }

    @Test
    void testExceptionHandling() {
        try {
            Shape result = shapeHandler.prepareNewShape(null, null, null);
            assertNull(result);
        } catch (Exception ex) {
            fail("Metoda nije smela da baci Exception, već da ga uhvati u try-catch bloku");
        }
    }
}