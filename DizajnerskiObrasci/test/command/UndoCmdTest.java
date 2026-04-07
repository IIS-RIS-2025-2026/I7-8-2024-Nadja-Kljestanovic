package command;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Stack;

import geometry.Line;
import geometry.Point;
import mvc.DrawingController;
import mvc.DrawingModel;

class UndoCmdTest {
    private UndoCmd undoCmd;
    private ArrayList<Command> commandList;
    private Stack<Command> savedCommands;
    private AddLineCmd testCommand;
    private DrawingModel model;

    @BeforeEach
    void setUp() {
        commandList = new ArrayList<>();
        savedCommands = new Stack<>();
        model = new DrawingModel();
        
        // Kreira se pomoćna komanda koja će se poništiti
        Line line = new Line(new Point(0, 0), new Point(10, 10));
        testCommand = new AddLineCmd(line, model);
        
        // Simulira se da je komanda već izvršena i dodata u listu
        testCommand.execute();
        commandList.add(testCommand);
        
        undoCmd = new UndoCmd(testCommand, commandList, savedCommands);
    }

    @Test
    void testExecutePopsCommandFromListToStack() {
        assertTrue(commandList.contains(testCommand), "Lista treba da sadrži komandu pre undo operacije.");
        assertTrue(savedCommands.isEmpty(), "Stack (redo lista) treba da bude prazan pre undo operacije.");
        assertEquals(1, model.getShapes().size(), "Model treba da sadrži jedan oblik pre undo operacije.");

        // Izvršavanje Undo komande
        undoCmd.execute();

        // Asertacija
        assertFalse(commandList.contains(testCommand), "Komanda treba da bude uklonjena iz liste izvršenih komandi.");
        assertEquals(testCommand, savedCommands.peek(), "Komanda treba da bude prebačena na stack za redo.");
        assertEquals(0, model.getShapes().size(), "Model treba da bude prazan jer je AddLineCmd poništen (unexecute).");
    }

    @Test
    void testExecuteWithSelectCmd() {
        // Inicijalizacija zavisnosti
        ArrayList<geometry.Shape> selectedShapes = new ArrayList<>();
        Point shape = new Point(5, 5);
        DrawingController mockController = new DrawingController(model, null); 
        SelectCmd selectCmd = new SelectCmd(shape, selectedShapes, mockController, true);
       
        shape.setSelected(true);
        selectedShapes.add(shape);
        commandList.add(selectCmd);
        
        UndoCmd undoSelect = new UndoCmd(selectCmd, commandList, savedCommands);
        
        // Izvršavanje Undo operacije
        undoSelect.execute();
        
        // Asertacija
        assertFalse(commandList.contains(selectCmd), "SelectCmd treba da bude uklonjen iz liste.");
        assertEquals(selectCmd, savedCommands.peek(), "SelectCmd treba da bude prebačen na stack (redo listu).");
        assertFalse(shape.isSelected(), "Status oblika treba da bude promenjen (deselektovan).");
    }
}