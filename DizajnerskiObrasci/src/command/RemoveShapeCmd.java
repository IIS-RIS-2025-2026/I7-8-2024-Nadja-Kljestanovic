package command;

import java.util.ArrayList;

import geometry.Shape;
import mvc.DrawingModel;

public class RemoveShapeCmd implements Command {
	private Shape shape;
	private DrawingModel model;
	private ArrayList<Shape> listOfSelectedShapes;
	private int index;
	
	public RemoveShapeCmd (Shape shape, DrawingModel model, ArrayList<Shape> listOfSelectedShapes, int index) {
		this.shape = shape;
		this.model = model;
		this.listOfSelectedShapes = listOfSelectedShapes;
		this.index = index;
	}
	
	public void execute() {
		model.remove(shape);
		listOfSelectedShapes.remove(shape);
	}
	
	public void unexecute() {
		model.addWithIndex(index, shape);
		listOfSelectedShapes.add(shape);
	}
}
