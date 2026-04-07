package command;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import geometry.Line;
import geometry.Point;
import mvc.DrawingModel;

class RemoveLineCmdTest {
    private RemoveLineCmd removeLineCmd;
    private DrawingModel model;
    private Line line;

    @BeforeEach
    void setUp() {
        model = new DrawingModel();
        // Kreiramo liniju i dodajemo je u model pre početka testa
        line = new Line(new Point(10, 10), new Point(20, 20));
        model.add(line);
        
        removeLineCmd = new RemoveLineCmd(line, model);
    }

    @Test
    void testExecuteRemovesLineFromModel() {
        // Pre izvršenja, linija se nalazi u modelu
        assertTrue(model.getShapes().contains(line), "Linija treba da bude u modelu pre brisanja.");
        assertEquals(1, model.getShapes().size(), "Model treba da ima jedan element.");
        
        // Izvršavanje komande
        removeLineCmd.execute();
        
        // Asertacija
        assertFalse(model.getShapes().contains(line), "Linija treba da bude uklonjena iz modela.");
        assertEquals(0, model.getShapes().size(), "Model treba da bude prazan nakon brisanja.");
    }

    @Test
    void testUnexecuteAddsLineBackToModel() {
        // Prvo obrišemo liniju
        removeLineCmd.execute();
        
        // Poništavanje brisanja (Undo)
        removeLineCmd.unexecute();
        
        // Asertacija
        assertTrue(model.getShapes().contains(line), "Linija treba da bude vraćena u model nakon unexecute.");
        assertEquals(1, model.getShapes().size(), "Model treba ponovo da ima jedan element.");
    }
}