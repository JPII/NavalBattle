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

public class Command implements Comparable<Command> {

	private String command;
	private String args;
	private String description;
	private CommandAction commandAction;

	/**
	 * Construct a <code>Command</code>.
	 * 
	 * @param command
	 * @param args
	 * @param description
	 * @param commandAction
	 */
	public Command(String command, String args, String description, CommandAction commandAction) {
		this.command = command;
		this.args = args;
		this.description = description;
		this.commandAction = commandAction;
	}

	/**
	 * Get <code>Command</code>.
	 * @return
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * Set <code>Command</code>.
	 * @param command
	 */
	public void setCommand(String command) {
		this.command = command;
	}

	/**
	 * Get <code>Command</code> arguments.
	 * @return
	 */
	public String getArgs() {
		return args;
	}

	/**
	 * Set <code>Command</code> arguments.
	 * @param args
	 */
	public void setArgs(String args) {
		this.args = args;
	}

	/**
	 * Get <code>Command</code> description.
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set <code>Command</code> description.
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get <code>CommandAction</code>.
	 * @return
	 */
	public CommandAction getCommandAction() {
		return commandAction;
	}

	/**
	 * Set <code>CommandAction</code>.
	 * @param commandAction
	 */
	public void setCommandAction(CommandAction commandAction) {
		this.commandAction = commandAction;
	}

	/**
	 * Compare two <code>Commands</code>.
	 * @param cmd
	 */
	public int compareTo(Command cmd) {
		return this.getCommand().compareTo(cmd.toString());
	}
}