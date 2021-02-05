package org.example_games.whack_a_ball;

import java.util.Random;

import org.minueto.MinuetoColor;
import org.minueto.MinuetoFileException;
import org.sontana.AbstractScene;
import org.sontana.Behaviour;
import org.sontana.components.Position;
import org.sontana.engine.Core;

public class MenuScene extends AbstractScene
{

	private static final int MAX_SLIDERS = 10;
	
	
	public MenuScene()
	{
		super("MenuScene", MinuetoColor.BLUE); // Add background image
	}
	

	@Override
	protected void initialise() throws MinuetoFileException
	{
		Behaviour.instantiate(
				new MenuButtonUI("ButtonA", new Position(0, Core.windowHeight / 5 * 2)),
				new MenuButtonUI("ButtonB", new Position(Core.windowWidth, Core.windowHeight / 5 * 2))
				);
		

		Random r = new Random(); // Java random class is bad, lines come out mildy thin or mildy thick, barely ever mix
		
		for (int i = 0; i < MAX_SLIDERS; i++)
		{
			
			int pos = r.nextInt(Core.windowHeight);
			
			
			int sizeX = r.nextInt(300) + 50, 
					sizeY = r.nextInt(50) + 5;

			
			int weight = r.nextInt(3);
			
			/*
			 * This is just a really bad algorithm to get a vibrant colour
			 */
			int red = r.nextInt(128) + (int)(128.0 * (weight == 0 ? 1 : 0.33));
			int green = r.nextInt(128) + (int)(128.0 * (weight == 1 ? 1 : 0.33));
			int blue = r.nextInt(128) + (int)(128.0 * (weight == 2 ? 1 : 0.33));
			
			MinuetoColor colour = new MinuetoColor(red > 255 ? 255 : red, 
					green > 255 ? 255 : green, 
							blue > 255 ? 255 : blue);
			
			
			int speed = r.nextInt(15) + 5;
			
			
			SlidingBox a = new SlidingBox("Sliding box " + i + "A", new Position(0, pos), sizeX, sizeY, colour, speed);
			SlidingBox b = new SlidingBox("Sliding box " + i + "B", new Position(Core.windowWidth, pos), sizeX, sizeY, colour, speed);
			
			
			Behaviour.instantiate(a, b);
		}
	}

}
