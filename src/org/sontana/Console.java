package org.sontana;

/**
 * The <code>Console</code> class helps log information to the console to aid with communication and problems.
 * @author Christophe Simon
 *
 */
public class Console
{
	/**
	 * Prints a message to STDOUT.
	 * @param message the message.
	 */
	public static void log(String message)
	{
		System.out.println("Console Log:\t" + message); // TODO Find a better term than Console Log?
	}
	
	/**
	 * Prints a warning message to STDOUT.
	 * @param message the message.
	 */
	public static void logWarning(String message)
	{
		System.out.println("Warning Log:\t" + getLogMessage(new Exception().getStackTrace(), message));
	}
	
	/**
	 * Prints an error message to STDERR.
	 * @param message the message.
	 */
	public static void logError(String message)
	{		
		System.err.println("Error Log:\t" + getLogMessage(new Exception().getStackTrace(), message));
	}
	
	/*
	 * Helpers
	 */
	
	private static String getLogMessage(StackTraceElement[] trace, String message)
	{
		String logMsg = "";
		boolean first = false;
		
		for (StackTraceElement traceElement : trace)
        {
        	if((first = !first)) // TODO Possibly print last two stack traces for call to log position and previous function
        		continue;
            logMsg = message + "\t at " + traceElement; // Can maybe optimise with String.concat
        	break;
        }
		
		return logMsg;
	}
}
