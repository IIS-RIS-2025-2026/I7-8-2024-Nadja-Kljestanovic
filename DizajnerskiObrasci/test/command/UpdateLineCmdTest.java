package command;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import geometry.Line;
import geometry.Point;
import java.awt.Color;

class UpdateLineCmdTest {
	private UpdateLineCmd updateLineCmd;
	private Line originalLine;
	private Line newStateLine;

	@BeforeEach
	void setUp() {
		originalLine = new Line(new Point(10, 10), new Point(20, 20));
		originalLine.setBorderColor(Color.BLACK);
		newStateLine = new Line(new Point(50, 50), new Point(100, 100));
		newStateLine.setBorderColor(Color.RED);
		updateLineCmd = new UpdateLineCmd(originalLine, newStateLine);
	}

	@Test
	void testExecuteUpdatesLineProperties() {
		assertEquals(10, originalLine.getStartPoint().getX(), "X koordinata početne tačke treba da bude 10 pre execute.");
		assertEquals(Color.BLACK, originalLine.getBorderColor(), "Boja linije treba da bude crna pre execute.");
		
		// Izvršavanje komande
		updateLineCmd.execute();
		
		// Asertacija
		assertEquals(50, originalLine.getStartPoint().getX(), "X koordinata početne tačke treba da bude ažurirana na 50.");
		assertEquals(100, originalLine.getEndPoint().getY(), "Y koordinata krajnje tačke treba da bude ažurirana na 100.");
		assertEquals(Color.RED, originalLine.getBorderColor(), "Boja linije treba da bude ažurirana na crvenu.");
	}

	@Test
	void testUnexecuteRestoresOriginalProperties() {
		updateLineCmd.execute();
		// Poništavanje komande
		updateLineCmd.unexecute();
		
		// Asertacija
		assertEquals(10, originalLine.getStartPoint().getX(), "X koordinata početne tačke treba da bude vraćena na 10.");
		assertEquals(20, originalLine.getEndPoint().getY(), "Y koordinata krajnje tačke treba da bude vraćena na 20.");
		assertEquals(Color.BLACK, originalLine.getBorderColor(), "Boja linije treba da bude vraćena na crnu.");
	}
}