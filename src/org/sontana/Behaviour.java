package org.sontana;

/**
 * The <code>Behaviour</code> class is what objects in a <code>Scene</code> derive from.
 * <p>
 * Don't create a child of this class. Instead, use <code>Pawn</code>, <code>Actor</code>, or <code>GameSystem</code>.
 * @author Christophe Simon
 *
 */
public class Behaviour
{
	/**
	 * Name of the <code>Behaviour</code>.
	 */
	public final String name;
	
	/**
	 * Tag of the <code>Behaviour</code>.
	 */
	public final String tag;
	
	private boolean enabled;
	
	
	/*
	 * Virtual Methods
	 */
	
	
	/**
	 * Code that runs when the object is created or when the scene begins.
	 */
	public void initialise() {}
	
	/**
	 * Code that runs every frame while the object is enabled.
	 */
	public void update() {}
	
	/**
	 * Code that runs at the end of every frame while the object is enabled.
	 */
	public void lateUpdate() {}
	
	/**
	 * Code that runs after enabling an object.
	 */
	protected void onEnable() {}
	
	/**
	 * Code that runs after disabling an object.
	 */
	protected void onDisable() {}
	
	
	/*
	 * Class Methods.
	 */
	
	protected Behaviour(String behaviourName, String behaviourTag, boolean startEnabled)
	{
		name = behaviourName;
		
		tag = behaviourTag;
		
		enabled = startEnabled;
		
	}
	
	/**
	 * Enable a <code>Behaviour</code> so that its logic executes.
	 */
	public final void enable()
	{
		enabled = true;
		
		onEnable();
	}
	
	/**
	 * Disable a <code>Behaviour</code> to prevent its logic from executing.
	 */
	public final void disable()
	{
		enabled = false;
		
		onDisable();
	}
	
	/**
	 * Check if a <code>Behaviour</code> is enabled or not.
	 * @return true if enabled.
	 */
	public final boolean isEnabled()
	{
		return enabled;
	}
	
	// Keeping name and tag public for now, no serious need to have them be protected. Can discuss
//	/**
//	 * Returns the Behaviour's name.
//	 * @return the name.
//	 */
//	public String getName()
//	{
//		return name;
//	}
//	
//	/**
//	 * Returns the Behaviour's tag.
//	 * @return the tag.
//	 */
//	public String getTag()
//	{
//		return tag;
//	}
	
}
