package command;

import geometry.Rectangle;

public class UpdateRectangleCmd  implements Command{
	private Rectangle rectangle;
	private Rectangle newState;
	private Rectangle original = new Rectangle();
	
	public UpdateRectangleCmd(Rectangle rectangle, Rectangle newState) {
		this.rectangle = rectangle;
		this.newState = newState;
	}
	
	@Override
	public void execute() {
		original = rectangle.clone(original);
		rectangle = newState.clone(rectangle);
	}
	
	@Override
	public void unexecute() {
		rectangle = original.clone(rectangle);
	}
}
