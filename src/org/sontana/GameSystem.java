package org.sontana;

/**
 * <code>GameSystem</code> objects are objects in an <code>AbstractScene</code> that are not rendered.
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
	}
	
}
