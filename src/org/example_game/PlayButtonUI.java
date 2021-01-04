package org.example_game;

import org.minueto.MinuetoColor;
import org.minueto.handlers.MinuetoMouse;
import org.minueto.image.MinuetoFont;
import org.minueto.image.MinuetoRectangle;
import org.minueto.image.MinuetoText;
import org.sontana.Core;
import org.sontana.SceneManager;
import org.sontana.SceneManagerException;
import org.sontana.components.DrawComponent;
import org.sontana.components.Position;
import org.sontana.components.RectCollider;
import org.sontana.game.UIActor;

/*
 * You can safely ignore this class for now, it is just a simple class that brings us to the next scene when clicked!
 * 
 * Reason I say it can be ignored is because I'm using an Actor class for it because Actors will usually be acted upon,
 * but I think a different class would be better for naming clarity.
 */
public class PlayButtonUI extends UIActor
{

	public PlayButtonUI()
	{
		super("PlayButton", 
				new MinuetoRectangle(128,64,MinuetoColor.WHITE, true), 
				new RectCollider(new Position(128,64)), 
				true);
		
		position = new Position(Core.windowWidth/2 - sprite.getWidth()/2, Core.windowHeight/2 - sprite.getHeight()/2);
		
		//new PlayButtonTextUI(new Position(	position.getX() + sprite.getWidth()/2, 
		//									position.getY() + sprite.getHeight()/2));
		
		DrawComponent text = new DrawComponent(
				new MinuetoText("Play", new MinuetoFont("Arial",16,true,false), 
						MinuetoColor.BLACK));
		
		components.add(text);
		
		text.getPosition().set(64 - text.getSprite().getWidth()/2, 32 - text.getSprite().getHeight()/2);
		
		
		registerMouseInput();
	}
	
	@Override
	protected void onMousePressChecked(Position mousePos, int button)
	{
		if(button == MinuetoMouse.MOUSE_BUTTON_LEFT)
		{
			try
			{
				/*
				 * Here we are loading the next Scene!
				 */
				SceneManager.loadScene("GameScene");
				
				
			} catch (SceneManagerException e)
			{
				e.printStackTrace();
			}
		}
	}
	
}
