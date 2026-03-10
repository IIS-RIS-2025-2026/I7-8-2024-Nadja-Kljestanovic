package command;

import geometry.Point;
import mvc.DrawingModel;

public class RemovePointCmd implements Command {

	private Point point;
	private DrawingModel model;
	
	
	
	public RemovePointCmd(Point point, DrawingModel model) {
		
		this.point = point;
		this.model = model;
	}

	@Override
	public void execute() {
		
		model.remove(point);
	} // iste su za sve shapeove, ovo i AddPointCmd

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.add(point);

	}

}
