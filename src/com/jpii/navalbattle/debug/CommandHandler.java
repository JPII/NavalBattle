package com.jpii.navalbattle.debug;

import java.util.ArrayList;

import com.jpii.navalbattle.NavalBattle;

public class CommandHandler {
	
	private ArrayList<Command> commands = new ArrayList<Command>();
	
	/**
	 * Construct a CommandHandler instance
	 * 
	 * @param commands
	 */
	public CommandHandler(ArrayList<Command> commands) {
		for(Command c : commands) {
			registerCommand(c);
		}
		
		NavalBattle.getDebugWindow().printInfo(commands.size() + " commands registered.");
	}
	
	/**
	 * Register command.
	 * 
	 * @param command
	 */
	public void registerCommand(Command command) {
		commands.add(command);
	}
	
	/**
	 * Parse command.
	 * 
	 * @param command
	 */
	public void parseCommand(String command) {
		command.replace("/", "");
		boolean iscommand = false;
		for(Command c: commands) {
			if(c.getCommand().equalsIgnoreCase(command)) {
				c.getCommandAction().onRun();
				iscommand = true;
			}
		}
		
		if(!iscommand) {
			NavalBattle.getDebugWindow().printError("'" + command + "' is not a registered command");
		}
	}
	
	/**
	 * Get commands.
	 * 
	 * @return
	 */
	public ArrayList<Command> getCommands() {
		return commands;
	}
}
