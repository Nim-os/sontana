package org.sontana;

import java.util.ArrayList;

import org.minueto.MinuetoEventQueue;
import org.minueto.handlers.*;
import org.minueto.window.MinuetoWindow;
import org.sontana.game.*;

public class InputManager
{
	private static final ArrayList<Actor> activeInputs = new ArrayList<>();
	private static final MinuetoEventQueue eventQueue = new MinuetoEventQueue();

	private static MinuetoWindow gameWindow;
	/**
	 * Set the hook for the <code>InputManager</code>.
	 * @param window
	 */
	public static void setHook(MinuetoWindow window)
	{
		gameWindow = window;
	}
	
	/*
	 * Registering functions
	 */
	
	public static void registerMouse(MinuetoMouseHandler handler)
	{
		gameWindow.registerMouseHandler(handler, eventQueue);
		
	}
	
	public static void unregisterMouse(MinuetoMouseHandler handler)
	{
		gameWindow.unregisterMouseHandler(handler, eventQueue);
		
	}
	
	public static void registerKeyboard(MinuetoKeyboardHandler handler)
	{
		gameWindow.registerKeyboardHandler(handler, eventQueue);
		
	}
	
	public static void unregisterKeyboard(MinuetoKeyboardHandler handler)
	{
		gameWindow.unregisterKeyboardHandler(handler, eventQueue);
		
	}
	
	public static void executeInput()
	{
		while(eventQueue.hasNext())
		{
			eventQueue.handle();
		}
	}
	
	
	public static void flush()
	{
		activeInputs.clear();
	}
	
}
