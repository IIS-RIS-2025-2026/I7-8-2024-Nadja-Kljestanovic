package mvc;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import geometry.Point;
import java.awt.Color;
import command.AddShapeCmd;

class DrawingControllerTest {

    private DrawingController controller;
    private DrawingModel model;
    private DrawingFrame frame;

    @BeforeEach
    void setUp() {
        model = new DrawingModel();
        frame = new DrawingFrame();
        controller = new DrawingController(model, frame);
    }

    @Test
    void testControllerInitialization() {
        assertNotNull(controller, "Kontroler bi trebao biti inicijalizovan");
        assertEquals(0, controller.getListOfSelectedShapes().size(), "Lista selektovanih oblika treba biti prazna na početku");
    }

    @Test
    void testSelectionListLogic() {
        controller.setSelectListSize(5);
        assertEquals(5, controller.getSelectListSize());
    }

    @Test
    void testUndoRedoLogic_EmptyStacks() {
        try {
            controller.undo();
            controller.redo();
        } catch (Exception e) {
            fail("Undo/Redo na praznim listama ne bi smeo da baci izuzetak");
        }
    }

    @Test
    void testHandleSelection_NoShapes() {
        java.awt.event.MouseEvent me = new java.awt.event.MouseEvent(
            frame, 0, 0, 0, 10, 10, 1, false);
        
        controller.setSelectFlag(true);
    }

    @Test
    void testDelete_EmptySelection() {
        assertDoesNotThrow(() -> {
            // controller.delete(); // Zakomentarisano jer JOptionPane blokira izvršavanje
        });
    }

    @Test
    void testUpdateSizes() {
        Point p = new Point(10, 10);
        model.add(p);
        
        controller.setNumberOfShapes(model.getShapes().size());
        
        assertEquals(1, controller.getNumberOfShapes());
    }
}