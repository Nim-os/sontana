package org.sontana.components;

import org.minueto.image.MinuetoImage;
import org.sontana.Pawn;
import org.sontana.tools.Console;

/**
 * TODO
 * @author Christophe Simon
 *
 */
public abstract class AbstractCollider
{
	/**
	 * Pawn to 'Hook' onto for updating the Position.
	 */
	private Pawn hook;
	
	/**
	 * Pawn to draw the Collider for debugging purposes.
	 */
	private Debug debug;
	
	
	/**
	 * The origin of the Collider relative to the Pawn's Position.
	 */
	protected Position origin;

	
	private boolean isDrawing = false;
	
	
	protected AbstractCollider(Debug pDebug, Position pOrigin)
	{
		debug = pDebug;
		
		origin = new Position(pOrigin);
	}
	
	/**
	 * Tests if a point intersects with this <code>AbstractCollider</code>.
	 * @param pPoint the point as a <code>Position</code>.
	 * @return if the point intersects.
	 */
	public final boolean testPoint(Position pPoint)
	{
		if(hook == null)
		{ 
			Console.logError("Collider Pawn hook not set.");
			return false; 
		}
		else if(pPoint == null)
		{
			Console.logError("Position cannot be null.");
			return false;
		}
		
		updateColliderPosition();
	
		return testPointIntersection(pPoint);
	}
	
	protected abstract boolean testPointIntersection(Position pPoint);
	
	
	/**
	 * Command helper method that gets the point closest to the <code>AbstractCollider</code>.
	 * @param pPoint the point as a <code>Position</code>.
	 * @return the closest point to the <code>AbstractCollider</code>.
	 */
	protected final Position getClosestPoint(Position pPoint)
	{
		if(hook == null)
		{ 
			Console.logError("Collider Pawn hook not set.");
			return null; 
		}
		else if(pPoint == null)
		{
			Console.logError("Position cannot be null.");
			return null;
		}
		
		updateColliderPosition();
		
		return getPointClosestToPoint(pPoint);
	}
	
	protected abstract Position getPointClosestToPoint(Position pPoint);
	
	
	/**
	 * Tests if this <code>AbstractCollider</code> intersects with another <code>AbstractCollider</code>. 
	 * @param pCollider the other <code>AbstractCollider</code>.
	 * @return if the <code>AbstractCollider</code> intersects.
	 */
	public final boolean testCollider(AbstractCollider pCollider)
	{
		return testPoint(pCollider.getClosestPoint(origin)); // TODO Probably incorrect, need to look into
	}
	
	/**
	 * Draws a Debug image to visually represent the area of the the <code>AbstractCollider</code>.
	 */
	public final void draw()
	{
		if(hook == null)
		{ 
			Console.logError("Collider Pawn hook not set.");
			return; 
		}
		
		updateColliderPosition();
		
		if(isDrawing)
		{
			hook.getComponents().remove(debug);
		}
		else
		{
			hook.getComponents().add(debug);
		}
		
		isDrawing = !isDrawing;
	}
	
	/**
	 * Not meant to be used outside of sontana, necessary to be public due to package limitations.
	 * Used to keep the position of the <code>AbstractCollider</code> updated as well as add the Debug image as a component.
	 * @param pHook the <code>Pawn</code> to hook the <code>AbstractCollider</code>
	 */
	public final void setHook(Pawn pHook)
	{
		hook = pHook;
		
	}
	
	private final void updateColliderPosition()
	{
		origin.set(hook.getPosition());
		
	}
	
	
}

/**
 * Debug <code>DrawComponent</code> used to display <code>AbstractCollider</code> objects.
 * @author Christophe Simon
 *
 */
class Debug extends DrawComponent
{

	public Debug(MinuetoImage pSprite)
	{
		super(pSprite);
		
	}
}