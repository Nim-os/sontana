package org.sontana.components;

import org.minueto.image.MinuetoImage;
import org.sontana.Pawn;
import org.sontana.tools.Console;

/**
 * TODO
 * @author Christophe Simon
 *
 */
public abstract class Collider // AbstractCollider or BaseCollider?
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
	
	
	protected Collider(Debug pDebug, Position pOrigin)
	{
		debug = pDebug;
		
		origin = new Position(pOrigin);
	}
	
	
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
	
	
	
	public final boolean testCollider(Collider pCollider)
	{
		return testPoint(pCollider.getClosestPoint(origin));
	}
	
	
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
	
	public final void setHook(Pawn pHook)
	{
		hook = pHook;
		
		//debugPawn.getPosition().set(hook.getPosition());
		
	}
	
	private final void updateColliderPosition()
	{
		origin.set(hook.getPosition());
			
		
		//debug.getPosition().set(hook.getPosition());
		
	}
	
	
}

/**
 * 
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