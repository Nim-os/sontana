package org.sontana;

import org.minueto.image.MinuetoImage;
import org.sontana.components.Collider;

/**
 * <code>UIActor</code>s are objects in a <code>Scene</code> that are rendered and react to input.
 * Unlike <code>Actor</code>s, they do not react to the position of <code>Camera</code>.
 * @author Christophe Simon
 *
 */
public class UIActor extends Actor
{
	protected UIActor(String actorName, MinuetoImage actorSprite, Collider actorCollider, boolean startEnabled)
	{
		this(actorName, "", actorSprite, actorCollider, startEnabled);
	}
	
	protected UIActor(String actorName, String actorTag, MinuetoImage actorSprite, Collider actorCollider, boolean startEnabled)
	{
		super(actorName, actorTag, actorSprite, actorCollider, startEnabled);
		
		setSortPosition(-100);
	}
}
