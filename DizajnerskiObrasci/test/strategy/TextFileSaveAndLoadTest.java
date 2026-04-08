package strategy;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import mvc.DrawingFrame;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

class TextFileSaveAndLoadTest {
    private TextFileSaveAndLoad saverLoader;
    private DrawingFrame frame;
    private ArrayList<String> logList;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        frame = new DrawingFrame();
        logList = new ArrayList<>();
        saverLoader = new TextFileSaveAndLoad(logList);
    }

    @Test
    void testSaveFile() {
        TextFileSaveAndLoad saver = new TextFileSaveAndLoad(frame);
        String testContent = "Add Point(10,10)\nSelect Point(10,10)";
        frame.getArea().setText(testContent);
        
        File tempFile = tempDir.resolve("testLog").toFile();
        String filePath = tempFile.getAbsolutePath();

        // Izvršavanje
        saver.saveFile(filePath);

        // Asertacija
        File expectedFile = new File(filePath + ".txt");
        assertTrue(expectedFile.exists(), "Tekstualni fajl bi trebalo da bude kreiran.");
    }

    @Test
    void testLoadFile() {
        TextFileSaveAndLoad saver = new TextFileSaveAndLoad(frame);
        frame.getArea().setText("Linija 1\nLinija 2");
        File tempFile = tempDir.resolve("loadTest").toFile();
        saver.saveFile(tempFile.getAbsolutePath());

        // Učitavanje u listu
        saverLoader.loadFile(tempFile.getAbsolutePath() + ".txt");

        // Asertacija
        assertEquals(2, logList.size(), "Lista bi trebalo da ima 2 linije.");
        assertEquals("Linija 1", logList.get(0));
        assertEquals("Linija 2", logList.get(1));
    }
}