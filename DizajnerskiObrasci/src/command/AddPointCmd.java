package command;

import geometry.Point;
import mvc.DrawingModel;

public class AddPointCmd implements Command {

	private Point point;
	private DrawingModel model;
	
	
	
	public AddPointCmd(Point point, DrawingModel model) {
		//super();
		this.point = point;
		this.model = model;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		model.add(point);
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.remove(point);
	}

}
