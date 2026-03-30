package command;

import geometry.HexagonAdapter;

public class UpdateHexagonCmd implements Command {
	private HexagonAdapter hexagonAdap;
	private HexagonAdapter newState;
	private HexagonAdapter original = new HexagonAdapter();
	
	public UpdateHexagonCmd(HexagonAdapter hexagonAdap, HexagonAdapter newState) {
		this.hexagonAdap = hexagonAdap;
		this.newState = newState;
	}
	
	@Override
	public void execute() {
		original = hexagonAdap.clone(original);
		hexagonAdap = newState.clone(hexagonAdap);
	}

	@Override
	public void unexecute() {
		hexagonAdap = original.clone(hexagonAdap);
	}
}
