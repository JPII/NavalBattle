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
		NavalBattle.getDebugWindow().printInfo("Registering commands");
		
		for(Command c : commands) {
			registerCommand(c);
		}
		
		NavalBattle.getDebugWindow().printInfo(commands.size() + " commands registered");
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
		boolean isCommand = false;
		
		String[] s = command.split(" ", 2);
		
		for(Command c: commands) {
			if(c.getCommand().equalsIgnoreCase(s[0])) {
				c.getCommandAction().onRun(c, command);
				isCommand = true;
			}
		}
		
		if(!isCommand) {
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
