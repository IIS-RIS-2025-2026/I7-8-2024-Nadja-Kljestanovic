package strategy;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import geometry.Point;
import mvc.DrawingModel;
import java.io.File;
import java.nio.file.Path;

class ObjectFileSaveLoadTest {
    private ObjectFileSaveLoad saverLoader;
    private DrawingModel model;
    private Point testPoint;

    // JUnit 5 @TempDir kreira folder koji se automatski briše nakon testa
    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        model = new DrawingModel();
        saverLoader = new ObjectFileSaveLoad(model);
        
        testPoint = new Point(10, 20);
        testPoint.setSelected(true);
    }

    @Test
    void testSaveAndLoadFile() {
        //Dodavanje oblika u model
        model.add(testPoint);
        File tempFile = tempDir.resolve("testModel.bin").toFile();
        String filePath = tempFile.getAbsolutePath();

        // Čuvanje fajla
        saverLoader.saveFile(filePath);
        assertTrue(tempFile.exists(), "Fajl bi trebalo da bude kreiran na disku.");

        model.getShapes().clear();
        assertEquals(0, model.getShapes().size(), "Model mora biti prazan pre učitavanja.");

        // Učitavanje fajla
        saverLoader.loadFile(filePath);

        // Asertacija
        assertEquals(1, model.getShapes().size(), "Model bi trebalo da ima jedan oblik nakon učitavanja.");
        Point loadedPoint = (Point) model.getShapes().get(0);
        
        assertEquals(testPoint.getX(), loadedPoint.getX(), "X koordinata mora biti ista.");
        assertEquals(testPoint.getY(), loadedPoint.getY(), "Y koordinata mora biti ista.");
        assertTrue(loadedPoint.isSelected(), "Status selekcije mora biti sačuvan.");
    }

    @Test
    void testLoadNonExistingFile() {
        saverLoader.loadFile("nepostojeci_fajl.bin");
        assertEquals(0, model.getShapes().size(), "Model bi trebalo da ostane prazan ako fajl ne postoji.");
    }
}