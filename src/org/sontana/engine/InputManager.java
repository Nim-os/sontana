package org.sontana.engine;

import java.util.ArrayList;

import org.minueto.MinuetoEventQueue;
import org.minueto.handlers.MinuetoKeyboardHandler;
import org.minueto.handlers.MinuetoMouseHandler;
import org.minueto.window.MinuetoWindow;
import org.sontana.Actor;

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
	
	public static void registerMouse(Actor handler)
	{
		gameWindow.registerMouseHandler(handler, eventQueue);
		
		activeInputs.add(handler);
		
	}
	
	public static void unregisterMouse(Actor handler)
	{
		gameWindow.unregisterMouseHandler(handler, eventQueue);

		activeInputs.remove(handler);
	}
	
	public static void registerKeyboard(Actor handler)
	{
		gameWindow.registerKeyboardHandler(handler, eventQueue);
		
		activeInputs.add(handler);
		
	}
	
	public static void unregisterKeyboard(Actor handler)
	{
		gameWindow.unregisterKeyboardHandler(handler, eventQueue);
		
		activeInputs.remove(handler);
		
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
		for(Actor actor : activeInputs)
		{
			gameWindow.unregisterMouseHandler(actor, eventQueue);
			gameWindow.unregisterKeyboardHandler(actor, eventQueue);
		}
		
		activeInputs.clear();
	}
	
}
