package org.sontana.components;

import org.minueto.image.MinuetoImage;

/**
 * <code>DrawComponent</code> objects are <code>Component</code> objects that render images over their parent <code>Pawn</code> object.
 * @author Christophe Simon
 *
 */
public class DrawComponent extends Component
{
	private MinuetoImage sprite;
	
	private Position position;
	
	public DrawComponent(MinuetoImage pSprite)
	{
		sprite = pSprite;
		
		position = new Position(0,0);
	}
	
	/**
	 * Gets the <code>DrawableComponent</code> object's sprite.
	 * @return the sprite as <code>MinuetoImage</code>.
	 */
	public final MinuetoImage getSprite()
	{
		return sprite;
	}
	
	/**
	 * Gets the <code>DrawableComponent</code> object's <code>Position</code>.
	 * @return the <code>Position</code>.
	 */
	public final Position getPosition()
	{
		return position;
	}

}
