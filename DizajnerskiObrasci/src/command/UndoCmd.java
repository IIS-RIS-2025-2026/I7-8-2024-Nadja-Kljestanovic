package command;

import java.util.ArrayList;
import java.util.Stack;

public class UndoCmd implements Command {

	private ArrayList<Command> commandList;
	private Stack<Command> savedCommands;
	private Command command;
	
	public UndoCmd(Command command,ArrayList<Command> commandList,Stack<Command> savedCommands){
		this.command = command; 
		this.commandList = commandList;
		this.savedCommands = savedCommands;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
		
		
		if (command instanceof SelectCmd) {
			command.execute();
			
		}
		else {
			command.unexecute();
		}
		
		
		 
		
		commandList.remove(command);
		savedCommands.push(command);
		
		}
	

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		//commandList.remove(command);
	}

}
