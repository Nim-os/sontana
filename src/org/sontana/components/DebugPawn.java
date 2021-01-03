package org.sontana.components;

import org.minueto.image.MinuetoImage;
import org.sontana.game.Pawn;

public class DebugPawn extends Pawn
{

	protected DebugPawn(MinuetoImage pSprite)
	{
		super("DebugPawn", pSprite, false);
		
		setSortPosition(-10000);
	}

}
