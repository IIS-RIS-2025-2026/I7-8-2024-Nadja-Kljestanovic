package command;

import geometry.Point;

public class UpdatePointCmd implements Command,Cloneable {

	private Point point = new Point();
	private Point newState = new Point();
	private Point original = new Point();
	
	public UpdatePointCmd(Point point, Point newState) {
		this.point = point;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original = point.clone(original);
		point = newState.clone(point);
	}

	@Override
	public void unexecute() {
		point = original.clone(point);
	}

}