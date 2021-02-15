package org.sontana;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.minueto.MinuetoColor;
import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.sontana.engine.SceneObserver;

/**
 * <code>AbstractScene</code> objects represents different game stages.
 * @author Christophe Simon
 *
 */
public abstract class AbstractScene
{
	private String sceneName;
	private MinuetoColor sceneColour = MinuetoColor.WHITE;
	private MinuetoImage sceneBackground = null;
	
	private List<GameSystem> sceneSystems;
	private List<Pawn> scenePawns;
	
	
	/**
	 * Create a new <code>AbstractScene</code>.
	 * @param pSceneName the <code>AbstractScene</code> name.
	 */
	public AbstractScene(String pSceneName)
	{		
		this(pSceneName, MinuetoColor.WHITE, null);
	}
	
	/**
	 * Create a new <code>AbstractScene</code>.
	 * @param pSceneName the <code>AbstractScene</code> name.
	 * @param pSceneColour the <code>AbstractScene</code> background colour.
	 */
	public AbstractScene(String pSceneName, MinuetoColor pSceneColour)
	{
		this(pSceneName, pSceneColour, null);
	}
	
	/**
	 * Create a new <code>AbstractScene</code>.
	 * @param pSceneName the <code>AbstractScene</code> name.
	 * @param pSceneColour the <code>AbstractScene</code> background colour.
	 * @param pSceneBackground the <code>AbstractScene</code> background image.
	 */
	public AbstractScene(String pSceneName, MinuetoColor pSceneColour, MinuetoImage pSceneBackground)
	{
		sceneName = pSceneName;
		
		sceneColour = pSceneColour;
		
		sceneBackground = pSceneBackground;
		
		
		sceneSystems = new ArrayList<>();
		scenePawns = new ArrayList<>();
	}
	
	
	/**
	 * Get the name of an <code>AbstractScene</code>.
	 * @return the name.
	 */
	public final String getName()
	{
		return sceneName;
	}
	
	/**
	 * Get the background colour of an <code>AbstractScene</code>.
	 * @return the background colour.
	 */
	public final MinuetoColor getBackgroundColour()
	{
		return sceneColour;
	}
	
	/**
	 * Get the background image of an <code>AbstractScene</code>.
	 * @return the background image.
	 */
	public final MinuetoImage getBackgroundImage()
	{
		return sceneBackground;
	}
	

	/**
	 * Command method for when an <code>AbstractScene</code> is initialising. Do not call this method normally.
	 * @throws MinuetoFileException if broken file paths exist within the scene.
	 */
	public final void initialiseScene() throws MinuetoFileException
	{
		sceneSystems.clear();
		scenePawns.clear();
		
		initialise();
	}
	
	/**
	 * Code that is run when an <code>AbstractScene</code> is first loaded.
	 * @throws MinuetoFileException if broken file paths exist within the scene.
	 */
	protected abstract void initialise() throws MinuetoFileException;

	
	/**
	 * Add a <code>GameSystem</code> to the <code>AbstractScene</code>. Do not call this method normally.
	 * @param pSystem the <code>GameSystem</code>.
	 */
	final void addSystem(GameSystem pSystem)
	{
		sceneSystems.add(pSystem);
		
		observers.forEach(observer -> observer.onSystemAdded(pSystem));
	}
	
	/**
	 * Get a <code>List</code> of the <code>AbstractScene</code> object's <code>GameSystem</code> objects.
	 * @return the <code>List</code> of <code>GameSystem</code> objects.
	 */
	public final List<GameSystem> getSystems()
	{
		return Collections.unmodifiableList(sceneSystems);
	}
	
	/**
	 * Add a <code>Pawn</code> to the <code>AbstractScene</code>. Do not call this method normally.
	 * @param pPawn the <code>Pawn</code>.
	 */
	final void addPawn(Pawn pPawn)
	{
		scenePawns.add(pPawn);

		observers.forEach(observer -> observer.onPawnAdded(pPawn));
	}
	
	/**
	 * Get a <code>List</code> of the <code>AbstractScene</code> object's <code>Pawn</code> objects.
	 * @return the <code>List</code> of <code>Pawn</code> objects.
	 */
	public final List<Pawn> getPawns()
	{
		return Collections.unmodifiableList(scenePawns);
	}
	
	/**
	 * Get an <code>AbstractScene</code> object's <code>Behaviour</code> by name.
	 * @param pName the <code>Behaviour</code> object's name.
	 * @return the <code>Behaviour</code>.
	 */
	public final Behaviour findBehaviourByName(String pName)
	{
		
		for(Behaviour bh : sceneSystems)
		{
			if(bh.name.equals(pName))
			{
				return bh;
			}
		}
		
		for(Behaviour bh : scenePawns)
		{
			if(bh.name.equals(pName))
			{
				return bh;
			}
		}
		
		return null; // TODO Null problem
	}
	
	/**
	 * Get a <code>List</code> of <code>Behaviour</code> objects by tag.
	 * @param pTag the <code>Behaviour</code> object's tag.
	 * @return the <code>List</code>.
	 */
	public final List<Behaviour> findBehavioursByTag(String pTag)
	{
		List<Behaviour> lst = new ArrayList<>();
		
		for(Behaviour bh : sceneSystems)
		{
			if(bh.tag.equals(pTag))
			{
				lst.add(bh);
			}
		}
		
		for(Behaviour bh : scenePawns)
		{
			if(bh.tag.equals(pTag))
			{
				lst.add(bh);
			}
		}
		
		return lst;
	}
	
		
	/*
	 * Observer
	 */
	
	
	private static ArrayList<SceneObserver> observers = new ArrayList<>();
	
	/**
	 * 
	 * @param pObserver
	 */
	public static void registerSceneObserver(SceneObserver pObserver)
	{
		observers.add(pObserver);
	}
	
	/**
	 * 
	 * @param pObserver
	 */
	public static void unregisterSceneObserver(SceneObserver pObserver)
	{
		observers.remove(pObserver);
	}
}
