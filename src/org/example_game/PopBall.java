package org.example_game;

import java.util.ArrayList;
import java.util.List;

import org.sontana.*;
import org.sontana.game.*;

public class PopBall
{
	public static void main(String[] args)
	{
		List<Scene> scenes = new ArrayList<Scene>();

		/*
		 * We need to add all our Scenes to the game here!
		 * 
		 * You don't need to worry about this too much yet, but know that the first scene added will be the first to play.
		 * 
		 * While it's not done here, know that we should aim to create packages for each Scene and their objects inside.
		 */
		scenes.add(new MenuScene());
		scenes.add(new GameScene());
		
		try
		{
			/*
			 * Here we are starting up the Core class which will handle the logic, rendering, and input for all our objects.
			 * We need to initialise it with a list of Scenes so that we can load into one.
			 * Scenes will represent our game's 'stages'. This can be things like the main menu, lobby, and etc.
			 * 
			 * Scenes will NOT represent things like the pause menu or any UI elements. They are purely here to encapsulate
			 * and split up points in the game's execution for readability and memory efficiency.
			 * 
			 * Scenes will NOT be instantiated through any other method than putting them inside the Core.Run method
			 * unless entirely necessary.
			 * 
			 * 
			 * The SceneManager will take care of loading and unloading Scenes.
			 * We can change Scene's through SceneManager.loadScene(<scene name>).
			 */
			Core.Run(scenes);
		}
		catch (SceneManagerException e)
		{
			/*
			 * This is here just because I made an exception which may or may not stay, we'll see
			 */
			e.printStackTrace();
		}
	}
}
