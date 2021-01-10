package org.example_games.pop_ball;

import org.minueto.MinuetoColor;
import org.minueto.image.MinuetoFont;
import org.minueto.image.MinuetoText;
import org.sontana.UIActor;
import org.sontana.components.Position;
import org.sontana.components.RectCollider;
import org.sontana.engine.Camera;
import org.sontana.engine.Core;
import org.sontana.tools.Console;

public class ScoreCounterUI extends UIActor
{

	/*
	 * This is just a quick demonstration of how to create UI pawns
	 */
	public ScoreCounterUI()
	{
		super("ScoreCounter", 
				new MinuetoText("Score: 0", new MinuetoFont(MinuetoFont.Serif, 18, true, false), MinuetoColor.BLACK), 
				new RectCollider(new Position(60,25)), 
				true);
		
		position = new Position(10,10);
		
		collider.draw();
		
		registerKeyboardInput();
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
	
	@Override
	protected void onKeyType(char letter)
	{
		switch(letter)
		{
			case 'i':
				Camera.setPosition(Position.add(Camera.getPosition(), new Position(0,3)));
				break;
			case 'k':
				Camera.setPosition(Position.add(Camera.getPosition(), new Position(0,-3)));
				break;
			case 'j':
				Camera.setPosition(Position.add(Camera.getPosition(), new Position(3,0)));
				break;
			case 'l':
				Camera.setPosition(Position.add(Camera.getPosition(), new Position(-3,0)));
				break;
			default:
				Console.log("Key pressed: " + letter);
		}
	}

}
