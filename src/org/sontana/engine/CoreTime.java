package org.sontana.engine;

/**
 * The <code>Time</code> class represents the details of the <code>Core</code>'s CPU time.
 * <p>
 * This is NOT the same as MinuetoStopWatch and is not intended to be used outside of the <code>Core</code>.
 * @author Christophe Simon
 *
 */
public class CoreTime
{	
	/**
	 * Time since program start.
	 */
	private static float programTime = 0;
	
	/**
	 * Frame time.
	 */
	private static float deltaTime = 0;
	
	/**
	 * Previous frame time.
	 */
	private static float lastDeltaTime = 0;
	
	private static long startFrameTime = 0;
	private static long endFrameTime = 0;

	private CoreTime() {}
	
	/**
	 * Starts a timer for the current frame.
	 */
	public static void beginFrame()
	{
		startFrameTime = System.nanoTime();
	}
	
	/**
	 * Ends timer for the current frame.
	 * @return time to complete the frame.
	 */
	public static float endFrame()
	{
		
		endFrameTime = System.nanoTime();
		
		float time = (float) (((double)(endFrameTime - startFrameTime))/1E9);
		
		lastDeltaTime = deltaTime;
		
		deltaTime = time;
		
		programTime += time;
		
		return time;
	}
	
	/**
	 * Get the runtime in seconds since the <code>Core</code> began running.
	 * @return program time.
	 */
	public static float getProgramTime()
	{
		return programTime;
	}
	
	/**
	 * Get the time in seconds that it took to complete the current frame.
	 * @return frame delta time.
	 */
	public static float getDeltaTime()
	{
		return deltaTime;
	}
	
	/**
	 * Get the time in seconds that it took to complete the last frame.
	 * @return frame delta time.
	 */
	public static float getLastDeltaTime()
	{
		return lastDeltaTime;
	}
}
