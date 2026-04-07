package command;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import geometry.Line;
import geometry.Point;
import mvc.DrawingModel;

class AddLineCmdTest {
    private AddLineCmd addLineCmd;
    private DrawingModel model;
    private Line line;

    @BeforeEach
    void setUp() {
        model = new DrawingModel();
        Point p1 = new Point(10, 10);
        Point p2 = new Point(20, 20);
        line = new Line(p1, p2);
        
        addLineCmd = new AddLineCmd(line, model);
    }

    @Test
    void testExecuteAddsLineToModel() {
        //Prazna lista na početku
        assertEquals(0, model.getShapes().size(), "Model treba da bude prazan pre izvršenja.");
        
        addLineCmd.execute();
        
        // Asertacija
        assertTrue(model.getShapes().contains(line), "Linija treba da bude dodata u model.");
        assertEquals(1, model.getShapes().size(), "Broj oblika u modelu treba da bude 1.");
    }

    @Test
    void testUnexecuteRemovesLineFromModel() {
        addLineCmd.execute();
        // Poništavanje komande
        addLineCmd.unexecute();
        
        // Asertacija
        assertFalse(model.getShapes().contains(line), "Linija treba da bude uklonjena iz modela.");
        assertEquals(0, model.getShapes().size(), "Model treba da ostane prazan nakon unexecute-a.");
    }
}