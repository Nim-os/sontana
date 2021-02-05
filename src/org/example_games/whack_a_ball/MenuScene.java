package org.example_games.whack_a_ball;

import org.minueto.MinuetoColor;
import org.minueto.MinuetoFileException;
import org.sontana.AbstractScene;
import org.sontana.Behaviour;
import org.sontana.components.Position;
import org.sontana.engine.Core;

public class MenuScene extends AbstractScene
{

	public MenuScene()
	{
		super("MenuScene", MinuetoColor.BLUE); // Add background image
	}

	@Override
	protected void initialise() throws MinuetoFileException
	{
		/*
		 * Add menu button that slides in or moves around
		 */
		
		Behaviour.instantiate(
				new MenuButtonUI("ButtonA", new Position(0, Core.windowHeight / 5 * 2)),
				new MenuButtonUI("ButtonB", new Position(Core.windowWidth, Core.windowHeight / 5 * 2))
				);
	}

}
