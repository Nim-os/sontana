package org.sontana.engine;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.minueto.MinuetoFileException;
import org.sontana.*;
import org.sontana.tools.Console;

/**
 * <code>SceneManager</code> handles the switching, loading, and referencing of <code>Scene</code>s.
 * @author Christophe Simon
 *
 */
public class SceneManager 
{
	/*
	 * FLYWEIGHT design to prevent duplicate scenes and ambiguity.
	 */
	private static HashMap<String, Scene> scenes = new HashMap<>();
	
	private static Scene activeScene;
	
	
	private SceneManager(){}
	
	
	/**
	 * Add multiple <code>Scene</code>s to the <code>SceneManager</code>.
	 * @param pScenes the <code>List</code> of <code>Scene</code>s.
	 * @throws SceneManagerException if scene already exists.
	 */
	public static void addScenes(List<Scene> pScenes) throws SceneManagerException
	{
		
		for(Scene sc : pScenes)
		{
			addScene(sc);
		}
	}
	
	/**
	 * Add a <code>Scene</code> to the <code>SceneManager</code>.
	 * @param pScene the <code>Scene</code>.
	 * @throws SceneManagerException if scene already exists.
	 */
	public static void addScene(Scene pScene) throws SceneManagerException
	{
		validateScene(pScene);
		
		
		scenes.put(pScene.getName(), pScene);
	}
	
	/**
	 * Validate a <code>Scene</code> to check for inconsistencies.
	 * @param pScene the <code>Scene</code>.
	 */
	private static void validateScene(Scene pScene) throws SceneManagerException
	{
		
		if(scenes.containsKey(pScene.getName())) // Key already exists in Map
		{
			throw new SceneManagerException("Error: Cannot add scene " + pScene.getName() +
			", scene of same name already exists.");
		}
		else if(scenes.containsValue(pScene)) // Scene already exists in Map
		{
			throw new SceneManagerException("Error: Cannot add scene " + pScene.getName() +
					", scene already exists under different key.");
		}
		
		
		try
		{
			Console.suppressLogs(true);
			
			activeScene = pScene;
			
			pScene.initialiseScene();
			
			Console.suppressLogs(false);
			
			List<Pawn> pawns = pScene.getPawns();
			List<GameSystem> systems = pScene.getSystems();
			
			for (int i = 0; i < pawns.size() + systems.size(); i++)
			{
				Behaviour cur;
				
				if (i < pawns.size())
				{
					cur = pawns.get(i);
				}
				else
				{
					cur = systems.get(i - pawns.size());
				}
				
				for (int j = i + 1; j < pawns.size() + systems.size(); j++)
				{
					Behaviour compare;
					
					if (j < pawns.size())
					{
						compare = pawns.get(j);
					}
					else
					{
						compare = systems.get(j - pawns.size());
					}
					
					if (cur.name.equals(compare.name))
					{
						Console.logWarning("Multiple behaviours named  " + cur.name + "  exist");
						break;
					}
				}
			}
		}
		catch (Exception e)
		{
			Console.logError("Failed to validate Scene " + pScene.getName() + "\n");
			
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Get a <code>Scene</code> from its name.
	 * @param pName the <code>Scene</code> name.
	 * @return the <code>Scene</code>.
	 */
	public static Scene getScene(String pName)
	{
		Scene scene = scenes.get(pName);
		
		return scene;
	}
	
	
	/**
	 * Load a <code>Scene</code> of a given name into memory.
	 * @param pSceneName the <code>Scene</code>'s name.
	 * @throws SceneManagerException if <code>Scene</code> does not exist.
	 */
	public static void loadScene(String pSceneName) throws SceneManagerException // TODO Add overloaded function that takes Scene input?
	{		
		//Core.Stop();
		
		if(!scenes.containsKey(pSceneName))
		{
			throw new SceneManagerException("Error: Scene " + pSceneName + " does not exist.");
		}
		
		unloadScene(activeScene);
		
		activeScene = scenes.get(pSceneName);
		
		if(activeScene == null)
		{
			throw new SceneManagerException("Fatal Error: Scene " + pSceneName + " exists but is assigned to null.");
		}
		
		
		try
		{
			activeScene.initialiseScene();
		} 
		catch (MinuetoFileException e)
		{
			// Catch any broken files
			e.printStackTrace();
		}		
		
		
		Core.sceneChange();
		
		//Core.Continue();
	}
	
	/**
	 * Reload the current active <code>Scene</code>.
	 * @throws SceneManagerException 
	 */
	public static void reloadScene() throws SceneManagerException
	{
		loadScene(activeScene.getName());
	}
	
	/**
	 * Unload a <code>Scene</code> from memory.
	 * @param pScene the scene.
	 */
	private static void unloadScene(Scene pScene)
	{
		// TODO Optimise garbage collection for faster load speed
		
		// Remove all pawns and behaviours
		
		InputManager.flush();
	}
	
	/**
	 * Get the current active <code>Scene</code>.
	 * @return the active <code>Scene</code>.
	 */
	public static Scene getActiveScene()
	{
		return activeScene;
	}
	
	/**
	 * Get a <code>Collection</code> of the <code>Scene</code>s.
	 * @return the <code>Collection</code> of <code>Scene</code>s.
	 */
	public static Collection<Scene> getScenes()
	{
		return Collections.unmodifiableCollection(scenes.values());
	}
}
