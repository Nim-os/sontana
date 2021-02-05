package org.example_games.whack_a_ball;

import org.minueto.MinuetoColor;
import org.minueto.image.MinuetoFont;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoRectangle;
import org.minueto.image.MinuetoText;
import org.sontana.UIActor;
import org.sontana.components.AbstractCollider;
import org.sontana.components.DrawComponent;
import org.sontana.components.Position;
import org.sontana.components.RectCollider;
import org.sontana.engine.Core;
import org.sontana.engine.SceneManager;
import org.sontana.engine.SceneManagerException;
import org.sontana.tools.CursorPosition;

public class MenuButtonUI extends UIActor
{
	private static final int speed = 4;

	public MenuButtonUI(String pName, Position pStartingPosition)
	{
		super(pName, 
				new MinuetoRectangle(120, 55, MinuetoColor.WHITE, true), 
				new RectCollider(new Position(24,24)), 
				true);
		
		position.set(pStartingPosition);
		
		registerMouseInput();
	}
	
	@Override
	public void initialise()
	{
		DrawComponent text = new DrawComponent(new MinuetoText("Play", new MinuetoFont(MinuetoFont.Serif, 18, true, false), MinuetoColor.BLACK));
		
		text.getPosition().set(sprite.getWidth()/2 - text.getSprite().getWidth()/2, 
				sprite.getHeight()/2 - text.getSprite().getHeight()/2);
		
		components.add(text);
	}
	
	@Override
	public void update()
	{
		position.move(speed, 0);
		
		if (position.getX() > Core.windowWidth * 2 + sprite.getWidth())
		{
			position.setX(0 - sprite.getWidth());
		}
	}
	
	@Override
	public void onMousePressChecked(CursorPosition pos, int button)
	{
		try
		{
			SceneManager.loadScene("GameScene");
		} catch (SceneManagerException e)
		{
			e.printStackTrace();
		}
	}

}
