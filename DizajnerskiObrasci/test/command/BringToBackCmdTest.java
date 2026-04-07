package command;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import geometry.Point;
import geometry.Shape;
import mvc.DrawingModel;

class BringToBackCmdTest {
    private BringToBackCmd bringToBackCmd;
    private DrawingModel model;
    private Point shapeToMove;
    private Point otherShape;
    private ArrayList<Shape> selectedShapes;

    @BeforeEach
    void setUp() {
        model = new DrawingModel();
        selectedShapes = new ArrayList<>();
        
        // Kreiramo dva oblika i dodajemo ih u model
        otherShape = new Point(10, 10);
        shapeToMove = new Point(20, 20);
        
        model.add(otherShape);
        model.add(shapeToMove);
        
        // shapeToMove je na indeksu 1, vraća se na indeks 0
        bringToBackCmd = new BringToBackCmd(model, selectedShapes, 1, 1);
    }

    @Test
    void testExecuteMovesShapeToBack() {
        assertEquals(1, model.getShapes().indexOf(shapeToMove), "Oblik treba inicijalno da bude na indeksu 1.");
        
        bringToBackCmd.execute();
        
        // Asertacija
        assertEquals(0, model.getShapes().indexOf(shapeToMove), "Oblik treba da se pomeri nazad na indeks 0.");
        assertEquals(otherShape, model.getShapes().get(1), "Drugi oblik treba da se pomeri napred na indeks 1.");
    }

    @Test
    void testUnexecuteMovesShapeToOriginalPosition() {
        bringToBackCmd.execute();
        // Poništavanje komande
        bringToBackCmd.unexecute();
        
        // Asertacija
        assertEquals(1, model.getShapes().indexOf(shapeToMove), "Oblik treba da se vrati na indeks 1.");
        assertEquals(otherShape, model.getShapes().get(0), "Drugi oblik treba da se vrati na indeks 0.");
    }
}