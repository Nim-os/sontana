package org.example_game;

import org.minueto.MinuetoColor;
import org.sontana.game.*;

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
