package org.sontana.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.minueto.MinuetoColor;
import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;

/**
 * The <code>Scene</code> class represents different game stages.
 * @author Christophe Simon
 *
 */
public abstract class Scene
{
	private String sceneName;
	private MinuetoColor sceneColour = MinuetoColor.WHITE;
	private MinuetoImage sceneBackground = null;
	
	private List<GameSystem> sceneSystems;
	private List<Pawn> scenePawns;
	
	
	/**
	 * Create a new <code>Scene</code>.
	 * @param pSceneName the <code>Scene</code> name.
	 */
	public Scene(String pSceneName)
	{		
		this(pSceneName, MinuetoColor.WHITE, null);
	}
	
	/**
	 * Create a new <code>Scene</code>.
	 * @param pSceneName the <code>Scene</code> name.
	 * @param pSceneColour the <code>Scene</code> background colour.
	 */
	public Scene(String pSceneName, MinuetoColor pSceneColour)
	{
		this(pSceneName, pSceneColour, null);
	}
	
	/**
	 * Create a new <code>Scene</code>.
	 * @param pSceneName the <code>Scene</code> name.
	 * @param pSceneColour the <code>Scene</code> background colour.
	 * @param pSceneBackground the <code>Scene</code> background image.
	 */
	public Scene(String pSceneName, MinuetoColor pSceneColour, MinuetoImage pSceneBackground)
	{
		sceneName = pSceneName;
		
		sceneColour = pSceneColour;
		
		sceneBackground = pSceneBackground;
		
		
		sceneSystems = new ArrayList<>();
		scenePawns = new ArrayList<>();
	}
	
	/**
	 * Code that is run when a <code>Scene</code> is first loaded.
	 * @throws MinuetoFileException 
	 */
	public abstract void initialiseScene() throws MinuetoFileException;

	/**
	 * Get the name of a <code>Scene</code>.
	 * @return the name.
	 */
	public final String getName()
	{
		return sceneName;
	}
	
	/**
	 * Get the background colour of a <code>Scene</code>.
	 * @return the background colour.
	 */
	public final MinuetoColor getBackgroundColour()
	{
		return sceneColour;
	}
	
	/**
	 * Get the background image of a <code>Scene</code>.
	 * @return the background image.
	 */
	public final MinuetoImage getBackgroundImage()
	{
		return sceneBackground;
	}
	
	/**
	 * Add a <code>GameSystem</code> to the <code>Scene</code>. Do not call this method normally.
	 * @param pSystem the <code>GameSystem</code>.
	 */
	public final void addSystem(GameSystem pSystem)
	{
		sceneSystems.add(pSystem);
		
		pSystem.initialise();
	}
	
	/**
	 * Get a <code>List</code> of the <code>Scene</code>'s <code>GameSystem</code>s.
	 * @return the <code>List</code> of <code>GameSystem</code>s.
	 */
	public final List<GameSystem> getSystems()
	{
		return Collections.unmodifiableList(sceneSystems);
	}
	
	/**
	 * Add a <code>Pawn</code> to the <code>Scene</code>. Do not call this method normally.
	 * @param pPawn the <code>Pawn</code>.
	 */
	public final void addPawn(Pawn pPawn)
	{
		scenePawns.add(pPawn);
		
		pPawn.initialise();
	}
	
	/**
	 * Get a <code>List</code> of the <code>Scene</code>'s <code>Pawn</code>s.
	 * @return the <code>List</code> of <code>Pawn</code>s.
	 */
	public final List<Pawn> getPawns()
	{
		return Collections.unmodifiableList(scenePawns);
	}
	
	/**
	 * Get a <code>Scene</code>'s <code>Behaviour</code> by name.
	 * @param pName the <code>Behaviour</code>'s name.
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
	 * Get a <code>List</code> of <code>Behaviour</code>s by tag.
	 * @param pTag the <code>Behaviour</code>'s tag.
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
}
