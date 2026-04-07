package command;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import geometry.Point;
import geometry.Shape;
import mvc.DrawingController;

class SelectCmdTest {
    private SelectCmd selectCmd;
    private Point shape;
    private ArrayList<Shape> selectedShapes;
    private DrawingController controller;

    @BeforeEach
    void setUp() {
        shape = new Point(10, 10);
        selectedShapes = new ArrayList<>();
        controller = new DrawingController(null, null);
        selectCmd = new SelectCmd(shape, selectedShapes, controller, true);
    }

    @Test
    void testExecuteSelectsShape() {
        // Inicijalno nije selektovano
        assertFalse(shape.isSelected());
        
        //Selektovanje
        selectCmd.execute();
        
        //Asertacija
        assertTrue(shape.isSelected(), "Oblik treba da bude selektovan.");
        assertTrue(selectedShapes.contains(shape), "Oblik treba da bude u listi selektovanih oblika.");
    }

    @Test
    void testUnexecuteDeselectsShape() {
    	//Prvo se selektuje da bi moglo da se deselektuje
        selectCmd.execute();
        selectCmd.unexecute();
        
        assertFalse(shape.isSelected(), "Oblik treba da bude deselektovan.");
        assertFalse(selectedShapes.contains(shape), "Oblik ne treba da bude u listi selektovanih oblika.");
    }
}