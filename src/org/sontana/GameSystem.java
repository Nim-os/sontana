package org.sontana;

import org.sontana.engine.SceneManager;

/**
 * <code>GameSystem</code>s are objects in a <code>Scene</code> that are not rendered.
 * @author Christophe Simon
 *
 */
public class GameSystem extends Behaviour
{
	protected GameSystem(String gameSystemName, boolean startEnabled)
	{
		this(gameSystemName, "", startEnabled);
	}
	
	protected GameSystem(String gameSystemName, String gameSystemTag, boolean startEnabled)
	{
		super(gameSystemName, gameSystemTag, startEnabled);
		
		SceneManager.getActiveScene().addSystem(this);
	}
	
}