package org.sontana.engine;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.minueto.MinuetoFileException;
import org.sontana.*;
import org.sontana.tools.Console;

/**
 * <code>SceneManager</code> handles the switching, loading, and referencing of <code>AbstractScene</code> objects.
 * @author Christophe Simon
 *
 */
public class SceneManager 
{
	/*
	 * FLYWEIGHT design to prevent duplicate scenes and ambiguity.
	 */
	private static HashMap<String, AbstractScene> scenes = new HashMap<>();
	
	private static AbstractScene activeScene;
	
	
	private SceneManager(){}
	
	
	/**
	 * Add multiple <code>AbstractScene</code> objects to the <code>SceneManager</code>.
	 * @param pScenes the <code>List</code> of <code>AbstractScene</code> objects.
	 * @throws SceneManagerException if scene already exists.
	 */
	public static void addScenes(List<AbstractScene> pScenes) throws SceneManagerException
	{
		
		for(AbstractScene sc : pScenes)
		{
			addScene(sc);
		}
	}
	
	/**
	 * Add an <code>AbstractScene</code> to the <code>SceneManager</code>.
	 * @param pScene the <code>AbstractScene</code>.
	 * @throws SceneManagerException if scene already exists.
	 */
	public static void addScene(AbstractScene pScene) throws SceneManagerException
	{
		validateScene(pScene);
		
		
		scenes.put(pScene.getName(), pScene);
	}
	
	/**
	 * Validate an <code>AbstractScene</code> to check for inconsistencies.
	 * @param pScene the <code>AbstractScene</code>.
	 */
	private static void validateScene(AbstractScene pScene) throws SceneManagerException
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
		
		/*
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
		}*/
		
	}
	
	/**
	 * Get an <code>AbstractScene</code> from its name.
	 * @param pName the <code>AbstractScene</code> name.
	 * @return the <code>AbstractScene</code>.
	 */
	public static AbstractScene getScene(String pName)
	{
		AbstractScene scene = scenes.get(pName);
		
		return scene;
	}
	
	
	/**
	 * Load an <code>AbstractScene</code> of a given name into memory.
	 * @param pSceneName the <code>AbstractScene</code> object's name.
	 * @throws SceneManagerException if <code>AbstractScene</code> does not exist.
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
	 * Reload the current active <code>AbstractScene</code>.
	 * @throws SceneManagerException if a scene does not exist or is null.
	 */
	public static void reloadScene() throws SceneManagerException
	{
		loadScene(activeScene.getName());
	}
	
	/**
	 * Unload an <code>AbstractScene</code> from memory.
	 * @param pScene the scene.
	 */
	private static void unloadScene(AbstractScene pScene)
	{
		// TODO Optimise garbage collection for faster load speed
		
		// Remove all pawns and behaviours
		
		InputManager.flush();
	}
	
	/**
	 * Get the current active <code>AbstractScene</code>.
	 * @return the active <code>AbstractScene</code>.
	 */
	public static AbstractScene getActiveScene()
	{
		return activeScene;
	}
	
	/**
	 * Get a <code>Collection</code> of the <code>AbstractScene</code> objects.
	 * @return the <code>Collection</code> of <code>AbstractScene</code> objects.
	 */
	public static Collection<AbstractScene> getScenes()
	{
		return Collections.unmodifiableCollection(scenes.values());
	}
}
