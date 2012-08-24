package com.jpii.navalbattle.debug;

public class Command {
	
	private String command;
	private String args;
	private String description;
	private CommandAction commandAction;
	
	/**
	 * Construct a Command.
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
	 * Get command.
	 * 
	 * @return
	 */
	public String getCommand() {
		return command;
	}
	
	/**
	 * Set command.
	 * 
	 * @param command
	 */
	public void setCommand(String command) {
		this.command = command;
	}
	
	/**
	 * Get arguments.
	 * 
	 * @return
	 */
	public String getArgs() {
		return args;
	}
	
	/**
	 * Set arguments.
	 * 
	 * @param args
	 */
	public void setArgs(String args) {
		this.args = args;
	}
	
	/**
	 * Get command description.
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Set command description.
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Get CommandAction.
	 * 
	 * @return
	 */
	public CommandAction getCommandAction() {
		return commandAction;
	}
	
	/**
	 * Set CommandAction
	 * 
	 * @param commandAction
	 */
	public void setCommandAction(CommandAction commandAction) {
		this.commandAction = commandAction;
	}
}