/*
 * Copyright (C) 2012 JPII and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

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
	}
	
	/**
	 * Register command.
	 * 
	 * @param command
	 */
	public void registerCommand(Command command) {
		commands.add(command);
		sort(commands);
	}
	
	/**
	 * Parse command.
	 * 
	 * @param command
	 */
	public void parseCommand(String command) {
		boolean isCommand = false;
		
		String[] cmd = command.split(" ");
		String[] args = new String[cmd.length - 1];
		int count = 0;
		
		command = cmd[0];
		
		for(String s : cmd) {
			if(count != 0) {
				args[count-1] = s;
			}
			
			count++;
		}
		
		for(Command c: commands) {
			if(c.getCommand().equalsIgnoreCase(cmd[0])) {
				c.getCommandAction().onRun(c, args);
				isCommand = true;
			}
		}
		
		if(!isCommand) {
			NavalBattle.getDebugWindow().printError("'" + command + "' is not a registered command");
		}
	}
	
	private void sort(ArrayList<Command> commands) {
		for(int index = 1; index < commands.size(); index++) {
			for(int count = 0; count < commands.size()-index; count++) {	
					if(commands.get(count).getCommand().compareTo(commands.get(count+1).getCommand())>=0){
						Command switcher = commands.get(count);
						commands.set(count, commands.get(count+1));
						commands.set(count+1, switcher);
					}
				}
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
