package org.sontana.engine;

import java.util.ArrayList;

import org.minueto.MinuetoEventQueue;
import org.minueto.window.MinuetoWindow;
import org.sontana.Actor;

/**
 * The <code>InputManager</code> class manages the inputs for <code>Actor</code> objects.<br>
 * This class should not be interacted with normally.
 * @author Christophe Simon
 *
 */
public class InputManager
{
	private static final ArrayList<Actor> waitingQueueMouse = new ArrayList<>();
	private static final ArrayList<Actor> waitingQueueKeyboard = new ArrayList<>();
	private static final ArrayList<Actor> activeInputs = new ArrayList<>();
	private static final MinuetoEventQueue eventQueue = new MinuetoEventQueue();

	private static MinuetoWindow gameWindow;
	
	/*
	 * Registering functions
	 */
	
	public static void registerMouse(Actor handler)
	{
		waitingQueueMouse.add(handler);
	}
	
	public static void unregisterMouse(Actor handler)
	{
		gameWindow.unregisterMouseHandler(handler, eventQueue);

		activeInputs.remove(handler);
	}
	
	public static void registerKeyboard(Actor handler)
	{		
		waitingQueueKeyboard.add(handler);
		
	}
	
	public static void unregisterKeyboard(Actor handler)
	{
		gameWindow.unregisterKeyboardHandler(handler, eventQueue);
		
		activeInputs.remove(handler);
		
	}
	
	/*
	 * Package functions
	 */
	
	static void setHook(MinuetoWindow window)
	{
		gameWindow = window;
	}
	
	static void executeInput()
	{
		while(eventQueue.hasNext())
		{
			eventQueue.handle();
		}
		
		/*
		 * Register all input after previous input has executed.
		 * Prevents activating something with input and having an object enabled on the input from recieving input as well.
		 */
		
		waitingQueueMouse.stream()
			.forEach(actor -> 
			{
				registerMouseInternal(actor);
				activeInputs.add(actor);
			});
		
		waitingQueueKeyboard.stream()
			.forEach(actor -> 
			{
				registerKeyboardInternal(actor);
				activeInputs.add(actor);
			});

		waitingQueueMouse.clear();
		waitingQueueKeyboard.clear();
	}
	
	
	static void flush()
	{
		for(Actor actor : activeInputs)
		{
			gameWindow.unregisterMouseHandler(actor, eventQueue);
			gameWindow.unregisterKeyboardHandler(actor, eventQueue);
		}
		
		waitingQueueMouse.clear();
		waitingQueueKeyboard.clear();
		
		activeInputs.clear();
	}
	
	
	private static void registerMouseInternal(Actor handler)
	{
		gameWindow.registerMouseHandler(handler, eventQueue);
		
	}
	
	private static void registerKeyboardInternal(Actor handler)
	{
		gameWindow.registerKeyboardHandler(handler, eventQueue);
		
	}
}
