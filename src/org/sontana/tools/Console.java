package org.sontana.tools;

/**
 * The <code>Console</code> class helps log information to the console to aid with communication and problems.
 * @author Christophe Simon
 *
 */
public class Console
{
	private static boolean suppressed = false;
	
	/**
	 * Prints a message to STDOUT.
	 * @param message the message.
	 */
	public static void log(String message)
	{
		if (suppressed)
		{
			return;
		}
		
		System.out.println("Console Log:\t" + message); // TODO Find a better term than Console Log?
	}
	
	/**
	 * Prints a warning message to STDOUT.
	 * @param message the message.
	 */
	public static void logWarning(String message)
	{
		if (suppressed)
		{
			return;
		}
		
		System.out.println("Warning Log:\t" + getLogMessage(new Exception().getStackTrace(), message));
	}
	
	/**
	 * Prints an error message to STDERR.
	 * @param message the message.
	 */
	public static void logError(String message)
	{
		if (suppressed)
		{
			return;
		}
		
		System.err.println("Error Log:\t" + getLogMessage(new Exception().getStackTrace(), message));
	}
	
	/**
	 * Suppresses future logs. Useful for testing or release builds.
	 * @param val true to suppress.
	 */
	public static void suppressLogs(boolean val)
	{		
		suppressed = val;
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
