package org.example_game;


import org.sontana.game.*;

public class GameScene extends Scene
{

	public GameScene()
	{
		/*
		 * This here is just the name of the Scene for the SceneManager, it won't matter all that much
		 */
		super("GameScene");

		/*
		 * Do not put any code for when the scene loads here otherwise it won't work!
		 * Use initialiseScene() instead!
		 */
	}
	
	/*
	 * You see here that Java will complain that we aren't using the variable, but if you play the game you'll see it works perfectly :)
	 */
	private ScoreSystem score;
	
	private ScoreCounterUI scoreCounter;

	
	/*
	 * This method will run once a Scene is loaded by the SceneManager.
	 * 
	 * It is suited to cache objects when we generate them.
	 */
	@Override
	public void initialiseScene()
	{
		/*
		 * Here we are just creating a GameSystem that will run inside the Scene.
		 * 
		 * It will spawn our Balloon objects, checkout the Balloon class for further reading!
		 */
		BalloonSpawner spawner = new BalloonSpawner();
		
		/*
		 * This is our UI object that is really just a pawn.
		 */
		scoreCounter = new ScoreCounterUI();
		
		
		int balloons = 16;
		
		score = new ScoreSystem(scoreCounter, balloons);
		
		for(int i = 0; i < balloons; i++)
		{
			/*
			 * As you can see, we do not need to save the object created anywhere, the parent class Pawn will handle it.
			 * 
			 * Of course, you may need to save the object's reference locally.
			 * 
			 * If you don't save it locally but want it somewhere else in another class (pre-cache any objects you use in Scene please :^( )
			 * you can use <Scene>.getBehaviourByName() to return a reference. To see an example of this check the end of Balloon!
			 */
			spawner.SpawnBalloon("Balloon: " + i);
		}
		
	}

}
