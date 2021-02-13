package org.sontana.engine;

import org.sontana.GameSystem;
import org.sontana.Pawn;

public interface SceneObserver
{

	void onSystemAdded(GameSystem pSystem);
	
	void onPawnAdded(Pawn pPawn);

}
