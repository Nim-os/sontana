package org.sontana.game;

import org.minueto.handlers.MinuetoKeyboardHandler;
import org.minueto.handlers.MinuetoMouseHandler;
import org.minueto.image.MinuetoImage;
import org.sontana.Console;
import org.sontana.InputManager;
import org.sontana.components.Collider;
import org.sontana.components.Position;

/**
 * <code>Actor</code>s are objects in a <code>Scene</code> that are rendered and react to input.
 * @author Christophe Simon
 *
 */
public class Actor extends Pawn implements MinuetoMouseHandler, MinuetoKeyboardHandler
{
	protected Collider collider;
	
	protected Actor(String actorName, MinuetoImage actorSprite, Collider actorCollider, boolean startEnabled)
	{
		this(actorName, "", actorSprite, actorCollider, startEnabled);
	}
	
	protected Actor(String actorName, String actorTag, MinuetoImage actorSprite, Collider actorCollider, boolean startEnabled)
	{
		super(actorName, actorTag, actorSprite, startEnabled);
		
		collider = actorCollider;
		
		if(collider != null)
		{
			collider.setHook(this);
		}
		else
		{
			Console.logWarning("No collider set for Actor " + actorName);
		}
	}
	
	/**
	 * Get the <code>Collider</code> for this <code>Actor</code>.
	 * @return the <code>Collider</code>.
	 */
	public final Collider getCollider()
	{
		return collider;
	}
	
	
	/*
	 * Input registration
	 */
	
	/**
	 * Register this <code>Actor</code> with the <code>InputManager</code> for Mouse Input.
	 */
	protected final void registerMouseInput()
	{
		InputManager.registerMouse(this);
	}
	
	/**
	 * Unregister this <code>Actor</code> with the <code>InputManager</code> for Mouse Input.
	 */
	protected final void unregisterMouseInput()
	{
		InputManager.unregisterMouse(this);
	}
	
	/**
	 * Register this <code>Actor</code> with the <code>InputManager</code> for Keyboard Input.
	 */
	protected final void registerKeyboardInput()
	{
		InputManager.registerKeyboard(this);
	}
	
	/**
	 * Unregister this <code>Actor</code> with the <code>InputManager</code> for Keyboard Input.
	 */
	protected final void unregisterKeyboardInput()
	{
		InputManager.unregisterKeyboard(this);
	}

	
	/*
	 * Virtual Input Methods
	 */
	
	
	protected void onKeyPress(int value) {}
	
	protected void onKeyRelease(int value) {}
	
	protected void onKeyType(char key) {}
	
	protected void onMousePress(Position pos, int button) {}
	
	protected void onMouseRelease(Position pos, int button) {}
	
	protected void onMouseMove(Position pos) {}
	
	/*
	 * Checked input checks intersection with the Collider first.
	 */
	
	protected void onMousePressChecked(Position pos, int button) {}
	
	protected void onMouseReleaseChecked(Position pos, int button) {}
	
	protected void onMouseMoveChecked(Position pos) {}
	
	
	
	/*
	 * Minueto Input Methods
	 */
	
	@Override
	public final void handleKeyPress(int value)
	{
		if(isEnabled())
		{
			onKeyPress(value);
		}
		
	}

	@Override
	public final void handleKeyRelease(int value)
	{
		if(isEnabled())
		{
			onKeyRelease(value);
		}
		
	}

	@Override
	public final void handleKeyType(char keyChar)
	{
		if(isEnabled())
		{
			onKeyType(keyChar);
		}
		
	}

	@Override
	public final void handleMousePress(int x, int y, int button)
	{
		if(isEnabled())
		{
			Position mousePos = new Position(x, y);
			
			onMousePress(mousePos, button);
			
			if(collider.testPoint(mousePos))
			{
				onMousePressChecked(mousePos, button);
			}
		}
		
	}

	@Override
	public final void handleMouseRelease(int x, int y, int button)
	{
		if(isEnabled())
		{
			Position mousePos = new Position(x, y);
			
			onMouseRelease(mousePos, button);
			
			if(collider.testPoint(mousePos))
			{
				onMouseReleaseChecked(mousePos, button);
			}
		}
		
	}

	@Override
	public final void handleMouseMove(int x, int y)
	{
		if(isEnabled())
		{
			Position mousePos = new Position(x, y);
			
			onMouseMove(mousePos);
			
			if(collider.testPoint(mousePos))
			{
				onMouseMoveChecked(mousePos);
			}
		}
		
	}
	
}
