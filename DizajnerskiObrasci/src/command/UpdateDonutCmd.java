package command;

import geometry.Donut;

public class UpdateDonutCmd implements Command{
	private Donut donut;
	private Donut newState;
	private Donut original = new Donut();
	
	public UpdateDonutCmd(Donut donut, Donut newState) {
		this.donut = donut;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original = donut.clone(original);
		donut = newState.clone(donut);
	}
	
	@Override
	public void unexecute() {
		donut = original.clone(donut);
	}
}
