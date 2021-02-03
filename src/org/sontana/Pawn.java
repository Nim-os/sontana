package org.sontana;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.minueto.image.MinuetoImage;
import org.sontana.components.*;
import org.sontana.tools.Console;

/**
 * <code>Pawn</code> objects are objects in an <code>AbstractScene</code> that are rendered.
 * @author Christophe Simon
 *
 */
public class Pawn extends Behaviour
{	
	
	/**
	 * Concrete <code>Comparator</code> that returns the 'closer' <code>Pawn</code>.
	 */
	public static final Comparator<Pawn> BY_SORT_POSITION_COMPARATOR =
			(pawnA, pawnB) -> 
	{
		/*
		 * Pawns with a higher sorting position will be rendered first
		 */
		if(pawnA.getSortPosition() > pawnB.getSortPosition())
		{
			return -1;
		}
		
		return 1;
	};
	
	/**
	 * <code>MinuetoImage</code> of the <code>Pawn</code>.
	 */
	protected MinuetoImage sprite;
	
	/**
	 * <code>Position</code> of the <code>Pawn</code>.
	 */
	protected Position position;
	
	/**
	 * 
	 */
	protected ArrayList<Component> components;
	
	/*
	 * Sort position of the Pawn. Defines order to sort.
	 * Higher numbers are drawn first, lower numbers are drawn last.
	 */
	private int sortPosition;

	
	protected Pawn(String pawnName, MinuetoImage pawnSprite, boolean startEnabled)
	{
		this(pawnName, "", pawnSprite, startEnabled);
	}
	
	protected Pawn(String pawnName, String pawnTag, MinuetoImage pawnSprite, boolean startEnabled)
	{
		super(pawnName, pawnTag, startEnabled);
		
		sprite = pawnSprite;
		
		
		position = new Position(0,0);
		
		components = new ArrayList<>();
		
		sortPosition = 0;
		
		if(pawnSprite == null)
		{
			Console.logError(pawnName + " has null sprite, disabling to prevent crash.");
			disable();
		}
	}
	
	
	/*
	 * Class Methods.
	 */
	
	
	/**
	 * Gets the <code>Pawn</code> object's sprite.
	 * @return the sprite as <code>MinuetoImage</code>.
	 */
	public final MinuetoImage getSprite()
	{
		return sprite;
	}
	
	/**
	 * Gets the <code>Pawn</code> object's <code>Position</code>.
	 * @return the <code>Position</code>.
	 */
	public final Position getPosition()
	{
		return position;
	}
	
	/**
	 * Gets the <code>Pawn</code> object's <code>Component</code> objects.
	 * @return a <code>List</code> of <code>Component</code> objects.
	 */
	public final List<Component> getComponents()
	{
		return components;
	}
	
	/**
	 * Gets the <code>Pawn</code> object's sorting position.
	 * @return the sorting position.
	 */
	public final int getSortPosition()
	{
		return sortPosition;
	}
	
	/**
	 * Set the <code>Pawn</code> object's sorting position.
	 * @param newSortPosition the sorting position.
	 */
	protected final void setSortPosition(int newSortPosition)
	{
		sortPosition = newSortPosition;
	}
}
