package org.sontana.engine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import org.minueto.MinuetoColor;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoRectangle;
import org.minueto.window.MinuetoFrame;
import org.minueto.window.MinuetoWindow;

import org.sontana.*;
import org.sontana.components.*;
import org.sontana.tools.*;

/**
 * The <code>Core</code> class handles logic, rendering, and input of a game.
 * @author Christophe Simon
 *
 */
public final class Core
{
	private static Core instance = null;
	
	public static final int maxFrameRate = 60;
	public static int frameRate = 0;
	
	public static final String windowTitle = "Colt Express";
	public static final int windowWidth = 1000, windowHeight = 800;
	
	private static boolean coreRunning = false, corePaused = false;

	
	private final MinuetoWindow gameWindow;
	private final float minDeltaTime;
	
	private ArrayList<GameSystem> systems;
	private TreeSet<Pawn> pawns;
	
	
	/**
	 * Run the <code>Core</code> with pScenes.
	 * @param pScenes the game's <code>Scene</code>s.
	 * @throws SceneManagerException if a scene already exists.
	 */
	public static void Run(List<Scene> pScenes) throws SceneManagerException
	{		
		assert pScenes != null && !pScenes.isEmpty();
		
		if(instance == null)
			instance = new Core();
		
		/*
		 * TODO Scene Validation
		 */
		SceneManager.addScenes(pScenes);
		
		SceneManager.loadScene(pScenes.get(0).getName());
		
		if(!coreRunning)
		{
			coreRunning = true;
			
			instance.mainLoop();
		}
		else
		{
			Console.logError("Cannot run Core, instance already running.");
		}
		
	}
	
	public static void Stop()
	{
		coreRunning = false;
		corePaused = true;
	}
	
	public static void Continue() throws SceneManagerException
	{
		if(instance == null || SceneManager.getScenes().isEmpty())
		{
			Console.logError("Cannot continue Core, Core not initialised.");
			return;
		}
		
		if(coreRunning)
		{
			Console.logError("Cannot continue Core, instance already running.");
		}
		
		coreRunning = true;
		corePaused = false;
		
		instance.mainLoop();
		
	}
	
	public static void Exit()
	{
		if (!coreRunning && corePaused)
		{
			corePaused = false;
			
			instance.gameWindow.close();
		}
		else
		{
			coreRunning = false;
		}
	}
	
	
	
	/**
	 * Updates the <code>GameSystem</code> and <code>Pawn</code> caches for when the <code>Scene</code> changes.
	 */
	static void sceneChange()
	{
		instance.systems = new ArrayList<>(SceneManager.getActiveScene().getSystems());
		
		instance.pawns.clear();
		
		/*
		 * Draw a background scene.
		 */
		instance.sceneBackground = SceneManager.getActiveScene().getBackgroundImage();
		
		instance.sceneColour = SceneManager.getActiveScene().getBackgroundColour();
		
		
		
		/*
		 * Add all Pawns from the new Scene to the cache with respect to the sorting order
		 */
		for(Pawn p : SceneManager.getActiveScene().getPawns())
		{
			instance.pawns.add(p);
		}		
		
	}
	
	private MinuetoImage sceneBackground;
	private MinuetoColor sceneColour;
	
	
	/**
	 * Setup Core
	 */
	private Core()
	{
		gameWindow = new MinuetoFrame(windowWidth, windowHeight, true); // TODO Look into fullscreen

		gameWindow.setVisible(true);
		
		gameWindow.setTitle(windowTitle);
		
		gameWindow.setMaxFrameRate(maxFrameRate);
		
		InputManager.setHook(gameWindow);
		
		minDeltaTime = 1.0f/maxFrameRate;
		
		
		systems = new ArrayList<>();
		
		pawns = new TreeSet<>(new Comparator<Pawn>()
				{

					public int compare(Pawn o1, Pawn o2) // Don't return 0 since it will think they are equal and it's a set
					{
						/*
						 * Pawns with a higher sorting position will be rendered first
						 */
						if(o1.getSortPosition() > o2.getSortPosition())
						{
							return -1;
						}
						return 1;
					}
			
				});
	}
	
	
	/**
	 * Main code that executes logic and renders sprites.
	 * @throws SceneManagerException 
	 */
	private void mainLoop() throws SceneManagerException
	{
		
		while(coreRunning)
		{
			// NO CODE ABOVE THIS LINE
			CoreTime.beginFrame();
			
			/*
			 * Gives us how many frames are being built per second - Only works when frame rate is uncapped?
			 */
			frameRate = (int)(1/CoreTime.getLastDeltaTime());
			
			

			/*
			 * Handles input
			 */
			InputManager.executeInput();
			
			if(!coreRunning)
			{
				break;
			}
			
			/*
			 * Handles Behaviour logic
			 */
			executeLogic();
			
			if(!coreRunning)
			{
				break;
			}
			
			/*
			 * Handles rendering
			 */
			executeRendering();
			
			
			
			/*
			 * Gives us the time that it took to complete this frame
			 */
			//CoreTime.endFrame(); // See comment below
			// NO CODE BELOW THIS LINE
			
			/* Code necessary for if we need to prevent frames from being built too fast.
			 * However, MinuetoBaseWindow handles this inside render() already
			if(deltaTime < minDeltaTime)
			{
				try
				{
					Thread.sleep(0, (int)((minDeltaTime - deltaTime)*1E9));
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}*/
			
			// NO CODE BELOW THIS LINE
		}

		if (!corePaused)
		{
			instance.gameWindow.close();
		}
	}
	
	/**
	 * Executes the logic of <code>GameSystem</code>s and <code>Pawn</code>s in a <code>Scene</code>.
	 */
	private void executeLogic()
	{
		
		/*
		 * Update
		 */
		
		for(Behaviour bh : systems)
		{
			if(!bh.isEnabled())
			{
				continue;
			}
			
			bh.update();
		}
		
		for(Pawn p : pawns)
		{
			if(!p.isEnabled())
			{
				continue;
			}
			
			p.update();
		}
		
		/*
		 * Late Update
		 */
		
		for(Behaviour bh : systems)
		{
			if(!bh.isEnabled())
			{
				continue;
			}
			
			bh.lateUpdate();
		}
		
		for(Pawn p : pawns)
		{
			if(!p.isEnabled())
			{
				continue;
			}
			
			p.lateUpdate();
		}
	}
	
	/**
	 * Renders the <code>Pawn</code>s in a <code>Scene</code>.
	 */
	private void executeRendering()
	{
		/*
		 * Clear game view so that things do not render over one another
		 */
		gameWindow.clear();
		
		/*
		 * Render Scene background.
		 */
		gameWindow.draw(new MinuetoRectangle(windowWidth, windowHeight, sceneColour, true), 0, 0);
		
		if(sceneBackground != null) // This may not be completely optimised.. Might be better to remove down the line
		{
			gameWindow.draw(sceneBackground, 0, 0);
		}
		
		for(Pawn p : pawns)
		{
			/*
			 * Skip rendering Pawn if it is not enabled
			 */
			if(!p.isEnabled())
			{
				continue;
			}
			
			
			drawSprite(p);
		}

		CoreTime.endFrame();
		
		gameWindow.render();
	}
	
	private void drawSprite(Pawn pSprite)
	{
		Position pawnPos = new Position(pSprite.getPosition());
		
		/*
		 * Do not draw Pawn relative to Camera if they are a UI type
		 */
		if(!(pSprite instanceof UIActor))
		{
			pawnPos.move(-Camera.getPosition().getX(), -Camera.getPosition().getY());
		}
		
		
		/*
		 * Render pawn to gameWindow
		 */
		gameWindow.draw(pSprite.getSprite(), 
				(int)pawnPos.getX(), (int)pawnPos.getY());
		
		for(Component c : pSprite.getComponents())
		{
			if(c instanceof DrawComponent)
			{
				DrawComponent drawable = (DrawComponent)c;
				gameWindow.draw(drawable.getSprite(), 
						(int)(drawable.getPosition().getX() + pawnPos.getX()), 
						(int)(drawable.getPosition().getY() + pawnPos.getY()));
			}
		}
	}

}
