package command;

import geometry.Shape;
import mvc.DrawingModel;

public class AddShapeCmd implements Command {
	
	private Shape shape;
	private DrawingModel model;
	
	
	
	public AddShapeCmd(Shape shape, DrawingModel model) {
		//super();
		this.shape = shape;
		this.model = model;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		model.add(shape);
		//System.out.println("Added " + shape.getClass().getSimpleName() + " " + shape);
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.remove(shape);
	}

}
