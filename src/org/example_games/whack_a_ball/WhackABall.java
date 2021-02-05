package org.example_games.whack_a_ball;

import java.util.ArrayList;
import java.util.List;

import org.sontana.AbstractScene;
import org.sontana.engine.Core;
import org.sontana.engine.SceneManagerException;

public class WhackABall
{

	public static void main(String[] args)
	{
		List<AbstractScene> scenes = new ArrayList<AbstractScene>();

		scenes.add(new MenuScene());
		scenes.add(new GameScene());
		
		try
		{
			Core.Run(scenes);
		} catch (SceneManagerException e)
		{
			e.printStackTrace();
		}
		
	}
}
