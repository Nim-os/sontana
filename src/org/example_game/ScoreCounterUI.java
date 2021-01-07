package org.example_game;

import org.minueto.MinuetoColor;
import org.minueto.image.MinuetoFont;
import org.minueto.image.MinuetoText;
import org.sontana.UIActor;
import org.sontana.components.Position;
import org.sontana.engine.Core;

public class ScoreCounterUI extends UIActor
{

	/*
	 * This is just a quick demonstration of how to create UI pawns
	 */
	public ScoreCounterUI()
	{
		super("ScoreCounter", 
				new MinuetoText("Score: 0", new MinuetoFont(MinuetoFont.Serif, 18, true, false), MinuetoColor.BLACK), 
				null, 
				true);
		
		position = new Position(10,10);
		
	}
	
	/*
	 * This method is really inefficient but the MinuetoText class is really basic..
	 * 
	 * Probably will come to a better way to do this during games, I might even be using the wrong thing!
	 */
	public void updateScore(int score)
	{
		if(score < 0)
		{
			sprite = new MinuetoText("You win!", new MinuetoFont(MinuetoFont.Serif, 18, true, false), MinuetoColor.BLACK, true);
			position.set(Core.windowWidth/2 - sprite.getWidth()/2, Core.windowHeight/2 - sprite.getHeight()/2);
			return;
		}
		
		sprite = new MinuetoText("Score: " + score, new MinuetoFont(MinuetoFont.Serif, 18, true, false), MinuetoColor.BLACK, true);
	}

}
