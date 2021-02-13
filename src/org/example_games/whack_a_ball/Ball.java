package org.example_games.whack_a_ball;

import org.minueto.image.MinuetoImage;
import org.sontana.Actor;
import org.sontana.components.AbstractCollider;

public class Ball extends Actor
{

	public Ball(String pName)
	{
		super(pName, "balls", 
				null, 
				null, 
				true);
	}

}
