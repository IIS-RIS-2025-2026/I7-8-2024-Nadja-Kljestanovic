package command;

import geometry.Circle;

public class UpdateCircleCmd implements Command{
	private Circle circle;
	private Circle newState;
	private Circle original = new Circle();
	
	public UpdateCircleCmd(Circle circle, Circle newState) {
		this.circle = circle;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original = circle.clone(original);
		circle = newState.clone(circle);
	}

	@Override
	public void unexecute() {
		circle = original.clone(circle);
	}
}
