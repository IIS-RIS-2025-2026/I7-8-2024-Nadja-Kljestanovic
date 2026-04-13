package helpers;

import java.util.Stack;
import geometry.Shape;
import mvc.DrawingFrame;
import geometry.HexagonAdapter;

public class LogHandler {
	private DrawingFrame frame;
	private Stack<String> undoStack = new Stack<>();
	private Stack<String> redoStack = new Stack<>();

	public LogHandler(DrawingFrame frame) {
		this.frame = frame;
	}

	public void logAction(String action, Shape shape) {
		String name = (shape instanceof HexagonAdapter) ? "Hexagon" : shape.getClass().getSimpleName();
		String msg = action + " " + name + " " + shape.toString() + "\n";
		frame.getArea().append(msg);
		undoStack.push(msg);
	}

	public void logUndo() {
	    if (!undoStack.isEmpty()) {
	        String lastLog = undoStack.pop();
	        frame.getArea().append("Undo " + lastLog);
	        redoStack.push(lastLog);
	    }
	}

	public void logRedo() {
		if (!redoStack.isEmpty()) {
			String msg = redoStack.pop();
			frame.getArea().append("Redo " + msg);
			undoStack.push(msg);
		}
	}

	public void logText(String text) {
		String msg = text + "\n";
		frame.getArea().append(msg);
		undoStack.push(msg);
	}

	public void clearRedo() {
		redoStack.clear();
	}
	
	public void logModification(Shape oldShape) {
	    String name = (oldShape instanceof HexagonAdapter) ? "Hexagon" : oldShape.getClass().getSimpleName();
	    String msg = "Modified " + name + " " + oldShape.toString() + "\n";
	    frame.getArea().append(msg);
	    undoStack.push(msg);
	}

	public void logDeletion(Shape shape) {
		String name = (shape instanceof HexagonAdapter) ? "Hexagon" : shape.getClass().getSimpleName();
		String msg = "Deleted " + name + " " + shape.toString() + "\n";
		frame.getArea().append(msg);
		undoStack.push(msg);
	}

	public Stack<String> getUndoStack() {
		return undoStack;
	}

	public Stack<String> getRedoStack() {
		return redoStack;
	}
}