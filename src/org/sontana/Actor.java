package org.sontana;

import org.minueto.handlers.MinuetoKeyboardHandler;
import org.minueto.handlers.MinuetoMouseHandler;
import org.minueto.image.MinuetoImage;
import org.sontana.components.AbstractCollider;
import org.sontana.engine.InputManager;
import org.sontana.tools.Console;
import org.sontana.tools.CursorPosition;

/**
 * <code>Actor</code> objects are objects in an <code>AbstractScene</code> that are rendered and react to input.
 * @author Christophe Simon
 *
 */
public class Actor extends Pawn implements MinuetoMouseHandler, MinuetoKeyboardHandler
{
	protected AbstractCollider collider;
	
	protected Actor(String actorName, MinuetoImage actorSprite, AbstractCollider actorCollider, boolean startEnabled)
	{
		this(actorName, "", actorSprite, actorCollider, startEnabled);
	}
	
	protected Actor(String actorName, String actorTag, MinuetoImage actorSprite, AbstractCollider actorCollider, boolean startEnabled)
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
	 * Get the <code>AbstractCollider</code> for this <code>Actor</code>.
	 * @return the <code>AbstractCollider</code>.
	 */
	public final AbstractCollider getCollider()
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
	
	protected void onMousePress(CursorPosition pos, int button) {}
	
	protected void onMouseRelease(CursorPosition pos, int button) {}
	
	protected void onMouseMove(CursorPosition pos) {}
	
	/*
	 * Checked input checks intersection with the Collider first.
	 */
	
	protected void onMousePressChecked(CursorPosition pos, int button) {}
	
	protected void onMouseReleaseChecked(CursorPosition pos, int button) {}
	
	protected void onMouseMoveChecked(CursorPosition pos) {}
	
	
	
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
			CursorPosition mousePos = new CursorPosition(x, y);
			
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
			CursorPosition mousePos = new CursorPosition(x, y);
			
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
			CursorPosition mousePos = new CursorPosition(x, y);
			
			onMouseMove(mousePos);
			
			if(collider.testPoint(mousePos))
			{
				onMouseMoveChecked(mousePos);
			}
		}
		
	}
	
}
