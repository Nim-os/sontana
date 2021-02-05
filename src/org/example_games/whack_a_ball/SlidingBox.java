package org.example_games.whack_a_ball;

import org.minueto.MinuetoColor;
import org.minueto.image.MinuetoRectangle;
import org.sontana.Pawn;
import org.sontana.components.Position;
import org.sontana.engine.Core;

public class SlidingBox extends Pawn
{
	private int speed;

	public SlidingBox(String pName, Position pStartingPosition, int pSizeX, int pSizeY, MinuetoColor pColour, int pSpeed)
	{
		super(pName,
				new MinuetoRectangle(pSizeX, pSizeY, pColour, true),
				true);
		
		position.set(pStartingPosition);
		
		speed = pSpeed;
	}
	
	
	@Override
	public void update()
	{
		position.move(speed, 0);
		
		if (position.getX() > Core.windowWidth * 2 - sprite.getWidth())
		{
			position.setX(0 - sprite.getWidth());
		}
	}
}
