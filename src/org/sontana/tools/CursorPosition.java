package org.sontana.tools;

import org.sontana.components.Position;
import org.sontana.engine.Camera;

/**
 * <code>CursorPosition</code> allows for ease-of-computation for cursor position between the screen and the game world.
 * @author Christophe Simon
 *
 */
public class CursorPosition extends Position
{

	/**
	 * Initialise a <code>CursorPosition</code> using coordinates.
	 * @param pX the X coordinate.
	 * @param pY the Y coordinate.
	 */
	public CursorPosition(float pX, float pY)
	{
		super(pX, pY);
	}

	/**
	 * Get the position of the mouse cursor on the screen.
	 * @return the cursor <code>Position</code>.
	 */
	public Position getScreenPosition()
	{
		return new Position(this);
	}
	
	/**
	 * Get the position of the mouse cursor inside the game.
	 * @return the cursor <code>Position</code>.
	 */
	public Position getWorldPosition()
	{
		return new Position(this.getX() + Camera.getPosition().getX(), this.getY() + Camera.getPosition().getY());
	}

}
