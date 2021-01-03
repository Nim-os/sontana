package org.example_game;

import org.minueto.MinuetoStopWatch;
import org.sontana.Console;
import org.sontana.SceneManager;
import org.sontana.SceneManagerException;
import org.sontana.game.*;

public class ScoreSystem extends GameSystem
{
	private ScoreCounterUI counter;
	private int score;
	
	private int maxScore;

	public ScoreSystem(ScoreCounterUI pCounter, int pMaxScore)
	{
		super("ScoreSystem", true);
		
		counter = pCounter;
		
		score = 0;
		
		maxScore = pMaxScore;
		
	}
	
	private boolean startedEndSequence = false;
	
	private MinuetoStopWatch watch;
	
	public void update()
	{
		/*
		 * This is just a simple demonstration of using the MinuetoStopWatch to wait to initiate an event
		 */
		if(score >= maxScore)
		{
			
			if(!startedEndSequence)
			{
				startedEndSequence = true;
				
				watch = new MinuetoStopWatch();
				
				
				watch.start();
			}
			
			/*
			 * Remember watch returns time in milliseconds!
			 */
			if(watch.getTime() > 2000)
			{
				watch.stop();
				
				try
				{
					SceneManager.loadScene("MenuScene");
				} catch (SceneManagerException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
	
	
	public void addPoint()
	{
		Console.log("Added point.");
		
		score += 1;
		
		counter.updateScore(score);
		
		if(score >= maxScore)
		{
			Console.log("Great job!");
			
			counter.updateScore(-1);
		}
	}

}
