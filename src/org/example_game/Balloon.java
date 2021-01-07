package org.example_game;

import org.minueto.MinuetoColor;
import org.minueto.handlers.MinuetoMouse;
import org.minueto.image.MinuetoCircle;
import org.sontana.components.Position;
import org.sontana.components.RectCollider;
import org.sontana.engine.Core;
import org.sontana.engine.SceneManager;
import org.sontana.game.Actor;
import org.sontana.game.Behaviour;

/**
 * This is an example class of an Actor in a Scene which reacts to input.
 * 
 * In here, I describe the properties of a Pawn/ Actor.
 */
public class Balloon extends Actor
{
	/*
	 * Unlike Scenes, you are welcome to put stuff inside the constructor!
	 * However, like Scenes, you can use initialise() for code that needs to be run once the scene is loaded.
	 * 
	 * Though that last part is still a TODO for me..
	 */
	public Balloon(String balloonName, Position pos, MinuetoColor minuetoColor)
	{
		/*
		 * This line right here is just to make it so that when the object is created it starts enabled - AKA able to be rendered and run logic.
		 */
		super(balloonName, 
				new MinuetoCircle(24, 26, minuetoColor, true),
				new RectCollider(new Position(24, 26)),
				true);
		
		/*
		 * The Pawn class (which Actor derives from) has the properties Layer layer, Position position, MinuetoImage sprite, and (int) sortPosition.
		 * 
		 * (Don't worry about Layer for now, it may not become of anything)
		 * 
		 * 
		 * Position will represent where the MinuetoImage will be rendered.
		 * 
		 * MinuetoImage is just one of the different MinuetoImage types that will represent the look of the Pawn/ Actor.
		 * 
		 * sortPosition represents WHEN the object should be drawn. Currently, a higher number will be rendered sooner.
		 * Think of sortPosition as a sort of Z value where the lower it is, the closer it is to the camera, and vice versa.
		 * 
		 * 
		 * I might make it so that you need to supply the above (super) constructor 
		 * with these so that we are 99% sure they are good to go and not empty.
		 * 
		 */
		position = pos;
		
		collider.draw();
		/*
		 * This makes so that this object listens for mouse input.
		 * Don't worry about this piece of code right away, it may change if I create a helper library.
		 */
		registerMouseInput();
	}

	
	
	/*
	 * This method is from the Behaviour class. It is a method that is called every frame of the Core.
	 * If you want to update an object after everything else, you can use lateUpdate instead.
	 * 
	 * Depending on whether or not I see its use, I may add a fixedUpdate which would be called a predetermined number of times per frame.
	 * 
	 * The frequency of update is largely based on the speed of a computer and varies from system to system.
	 * 
	 * This method, like many other Behaviour methods, will need to be overrided (not explicitly thankfully).
	 */
	public void update()
	{
		
		
		/*
		 * This is a little example of getting the balloons to move.
		 * 
		 * 
		 * I'll explain Time.getDeltaTime() (which is not used here atm) at a later time, but know it is something that
		 * can be used to make things look smooth regardless of system.
		 * 
		 * Right now, the balloons will move 60 times per second because that is the framerate cap,
		 * but if we were to run this on a computer which could only run the program at 20 frames per second, well below the cap,
		 * the balloons will only move at 20 times per second for them.
		 */
		position.setX(position.getX() + 1);
		
		if(position.getX() > Core.windowWidth)
		{
			position.setX(0 - sprite.getWidth());
		}
		
	}

	
	@Override
	public void onMousePressChecked(Position pPos, int button)
	{
		/*
		 * Again, this input thing isn't necessary to understand right now.
		 * 
		 * If I do end up creating a library, the syntax will be somewhat similar, except
		 * checking if the mouse position is on the sprite will hopefully be handled.
		 */
		if(MinuetoMouse.MOUSE_BUTTON_LEFT == button)
		{
			/*
			 * Example of how I used the Console class to log the happenings when Balloons could be clicked after disabling.
			 */
			//Console.log(name + " : " + isEnabled());
			
			disable();
		}
		
	}
	
	
	/*
	 * This method will be called after we disable something!
	 * 
	 * It's handy for keeping code clean and if we were calling disable outside the class.
	 */
	public void onDisable()
	{
		/*
		 * Ideally, you should try and avoid finding a Behaviour in a Scene as much as possible and instead cache it in variables.
		 * 
		 * However, this is just an example of how it can be used to find things!
		 * 
		 * The syntax is a bit bizarre because we need to cast it to the right class, but the idea is there
		 */
		
		Behaviour sys = SceneManager.getActiveScene().findBehaviourByName("ScoreSystem");
		
		if(sys == null)
			return;
		
		((ScoreSystem)sys).addPoint();
		
		
	}

}
