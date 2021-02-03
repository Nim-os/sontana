package org.sontana;

import org.minueto.image.MinuetoImage;
import org.sontana.components.AbstractCollider;

/**
 * <code>UIActor</code> objects are objects in an <code>AbstractScene</code> that are rendered and react to input.
 * Unlike <code>Actor</code> objects, they do not react to the position of <code>Camera</code>.
 * @author Christophe Simon
 *
 */
public class UIActor extends Actor
{
	protected UIActor(String actorName, MinuetoImage actorSprite, AbstractCollider actorCollider, boolean startEnabled)
	{
		this(actorName, "", actorSprite, actorCollider, startEnabled);
	}
	
	protected UIActor(String actorName, String actorTag, MinuetoImage actorSprite, AbstractCollider actorCollider, boolean startEnabled)
	{
		super(actorName, actorTag, actorSprite, actorCollider, startEnabled);
		
		setSortPosition(-100);
	}
}
