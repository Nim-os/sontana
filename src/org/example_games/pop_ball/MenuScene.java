package org.example_games.pop_ball;

import org.minueto.MinuetoColor;
import org.sontana.Scene;

public class MenuScene extends Scene
{

	public MenuScene()
	{
		super("MenuScene", MinuetoColor.BLUE);
	}

	@Override
	public void initialiseScene()
	{
		/*
		 * I explain most of the inner Scene workings inside MainScene, go check it out!
		 * 
		 * Here, we are just creating Actors to use as UI.
		 */
		
		new PlayButtonUI();
		
	}

}