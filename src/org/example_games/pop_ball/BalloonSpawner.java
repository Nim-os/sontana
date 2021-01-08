package org.example_games.pop_ball;

import java.util.Random;

import org.minueto.MinuetoColor;
import org.sontana.GameSystem;
import org.sontana.components.Position;
import org.sontana.engine.Core;

public class BalloonSpawner extends GameSystem
{
	private Random r = new Random();

	public BalloonSpawner()
	{
		super("", false);
		
	}
	
	
	public Balloon SpawnBalloon(String balloonName)
	{
		int xPos = r.nextInt(Core.windowWidth);
		int yPos = r.nextInt(Core.windowHeight);
		
		int weight = r.nextInt(3);
		
		/*
		 * This is just a really bad algorithm to get a vibrant colour
		 */
		int red = r.nextInt(128) + (int)(128.0 * (weight == 0 ? 1 : 0.33));
		int green = r.nextInt(128) + (int)(128.0 * (weight == 1 ? 1 : 0.33));
		int blue = r.nextInt(128) + (int)(128.0 * (weight == 2 ? 1 : 0.33));
		
		/*
		 * This is spawning the Balloon in a position with the specific colour
		 */
		Balloon b = new Balloon(balloonName,
				new Position(xPos, yPos), 
				new MinuetoColor(red > 255 ? 255 : red, green > 255 ? 255 : green, blue > 255 ? 255 : blue));
		
		//Balloon b = new Balloon(balloonName, new Position(xPos, yPos), MinuetoColor.RED); // Ugly red balloons yuck
		
		return b;
	}
	
	
	/*
	 * Here are the Behaviour methods!
	 * Hover over the names for some info :) 
	 */

	
	public void initialise()
	{
		/*
		 * This code is ran when a Scene begins
		 * 
		 * This may seem redundant, and maybe it is in some cases, but if you have a dependency on something that will only be there
		 * once the Scene is fully loaded, you will want to put that code here for efficiency.
		 */
		
	}

	public void update()
	{
		/*
		 * This code is ran every frame
		 */
		
	}

	public void lateUpdate()
	{
		/*
		 * This code is ran at the end of every frame
		 */
		
	}

	protected void onEnable()
	{
		/*
		 * This code is ran whenever you enable something
		 */
		
	}

	protected void onDisable()
	{
		/*
		 * This code is ran whenever you disable something
		 */
		
	}

}
