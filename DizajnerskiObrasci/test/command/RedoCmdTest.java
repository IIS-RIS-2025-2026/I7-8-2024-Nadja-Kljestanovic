package command;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Stack;

import geometry.Point;
import mvc.DrawingModel;

class RedoCmdTest {
	private RedoCmd redoCmd;
	private ArrayList<Command> commandList;
	private Stack<Command> savedCommands;
	private AddShapeCmd testCommand;
	private DrawingModel model;

	@BeforeEach
	void setUp() {
		commandList = new ArrayList<>();
		savedCommands = new Stack<>();
		model = new DrawingModel();
		Point point = new Point(10, 10);
		testCommand = new AddShapeCmd(point, model);
		savedCommands.push(testCommand);
		
		redoCmd = new RedoCmd(testCommand, commandList, savedCommands);
	}

	@Test
	void testExecutePopsCommandFromStackToList() {
		assertFalse(savedCommands.isEmpty(), "Stack ne sme biti prazan pre redo operacije.");
		assertEquals(0, commandList.size(), "Lista komandi treba da bude prazna pre redo operacije.");
		assertEquals(0, model.getShapes().size(), "Model treba da bude prazan pre redo operacije.");

		// Izvršavanje Redo komande
		redoCmd.execute();

		// Asertacija
		assertTrue(commandList.contains(testCommand), "Komanda treba da bude vraćena u listu aktivnih komandi.");
		assertTrue(savedCommands.isEmpty(), "Stack treba da bude prazan nakon redo operacije.");
		assertEquals(1, model.getShapes().size(), "Model treba ponovo da sadrži oblik jer je AddShapeCmd ponovo izvršen.");
	}

	@Test
	void testExecuteRedoesCorrectCommand() {
		Point secondPoint = new Point(20, 20);
		AddShapeCmd secondCommand = new AddShapeCmd(secondPoint, model);
		savedCommands.push(secondCommand);
		
		redoCmd.execute();

		// Asertacija
		assertTrue(commandList.contains(secondCommand), "Redo treba da izvrši komandu koja je bila na vrhu stack-a.");
		assertEquals(1, model.getShapes().size(), "Samo jedan oblik treba da bude u modelu (onaj sa vrha stack-a).");
	}
}