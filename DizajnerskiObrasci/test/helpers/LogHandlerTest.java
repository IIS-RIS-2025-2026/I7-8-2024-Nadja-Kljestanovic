package helpers;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mvc.DrawingFrame;
import geometry.Point;
import java.util.Stack;

class LogHandlerTest {

    private LogHandler logHandler;
    private DrawingFrame frame;

    @BeforeEach
    void setUp() {
        frame = new DrawingFrame();
        logHandler = new LogHandler(frame);
    }

    @Test
    void testLogActionAndUndo() {
        Point p = new Point(10, 10);
        logHandler.logAction("Added", p);

        // Provera da li je u undoStack-u
        assertFalse(logHandler.getUndoStack().isEmpty(), "Undo stack ne bi smeo biti prazan nakon akcije");
        assertTrue(logHandler.getUndoStack().peek().contains("Added Point"), "Log poruka bi trebala da sadrži naziv klase");

        // Testiranje Undo-a
        logHandler.logUndo();
        assertTrue(logHandler.getUndoStack().isEmpty(), "Undo stack bi trebao biti prazan nakon pop-a");
        assertFalse(logHandler.getRedoStack().isEmpty(), "Redo stack bi trebao dobiti poruku iz undo-a");
    }

    @Test
    void testLogRedo() {
        logHandler.logText("Neka komanda");
        logHandler.logUndo(); // Prebacuje u redoStack
        
        logHandler.logRedo();
        
        assertTrue(logHandler.getRedoStack().isEmpty(), "Redo stack bi trebao biti prazan nakon logRedo");
        assertEquals(1, logHandler.getUndoStack().size(), "Poruka bi trebala da se vrati u Undo stack");
        assertTrue(logHandler.getUndoStack().peek().contains("Neka komanda"));
    }

    @Test
    void testLogDeletion() {
        Point p = new Point(5, 5);
        logHandler.logDeletion(p);
        
        String lastMsg = logHandler.getUndoStack().peek();
        assertTrue(lastMsg.startsWith("Deleted Point"), "Log treba da počne sa 'Deleted'");
    }

    @Test
    void testClearRedo() {
        logHandler.logText("Test");
        logHandler.logUndo(); 
        assertFalse(logHandler.getRedoStack().isEmpty());

        logHandler.clearRedo();
        assertTrue(logHandler.getRedoStack().isEmpty(), "Redo stack bi morao biti obrisan");
    }

    @Test
    void testLogModification() {
        Point p = new Point(1, 1);
        logHandler.logModification(p);
        
        String lastMsg = logHandler.getUndoStack().peek();
        assertTrue(lastMsg.contains("Modified Point"), "Log treba da sadrži 'Modified'");
    }
}