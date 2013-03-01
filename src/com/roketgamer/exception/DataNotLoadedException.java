package com.roketgamer.exception;

public class DataNotLoadedException extends Exception {

	private static final long serialVersionUID = 5128551570511484471L;

	/**
	 * Called when data that has not been loaded is accessed.
	 * @param message
	 */
	public DataNotLoadedException(String message) {
        super(message);
    }

	/**
	 * Called when data that has not been loaded is accessed.
	 * @param message
	 */
    public DataNotLoadedException(String message, Throwable throwable) {
        super(message, throwable);
    }

}