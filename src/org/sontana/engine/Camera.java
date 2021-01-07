package org.sontana.engine;

import org.sontana.components.Position;

/**
 * <code>Camera</code> acts as a pseudo-camera that defines where the origin of a frame is.
 * @author Christophe Simon
 *
 */
public class Camera
{	
	private static Position cameraPosition = new Position(0,0);
	
	private Camera() {}
	
	/**
	 * Shorthand for <code>setPosition(new Position(0,0))</code>.
	 */
	public static void resetPosition()
	{
		setPosition(Position.ZERO);
	}
	
	/**
	 * Moves the <code>Camera</code> to a new <code>Position</code>.
	 * @param pNewPosition the new <code>Position</code>.
	 */
	public static void setPosition(Position pNewPosition)
	{
		cameraPosition.set(pNewPosition);
	}
	
	/**
	 * Gets the <code>Position</code> of the <code>Camera</code>.
	 * @return
	 */
	public static Position getPosition()
	{
		return new Position(cameraPosition);
	}
}
