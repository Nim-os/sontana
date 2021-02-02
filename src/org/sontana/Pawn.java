package org.sontana;

import java.util.ArrayList;
import java.util.List;

import org.minueto.image.MinuetoImage;
import org.sontana.components.Component;
import org.sontana.components.Layer;
import org.sontana.components.Position;
import org.sontana.tools.Console;

/**
 * <code>Pawn</code>s are objects in a <code>Scene</code> that are rendered.
 * @author Christophe Simon
 *
 */
public class Pawn extends Behaviour
{	
	/**
	 * Layer of the <code>Pawn</code>.
	 */
	protected Layer layer; // TODO Needed?
	
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
		
		
		layer = new Layer();
		
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
	 * Gets the <code>Pawn</code>'s <code>Layer</code>.
	 * @return the <code>Layer</code>.
	 */
	public final Layer getLayer()
	{
		return layer;
	}
	
	/**
	 * Gets the <code>Pawn</code>'s sprite.
	 * @return the sprite as <code>MinuetoImage</code>.
	 */
	public final MinuetoImage getSprite()
	{
		return sprite;
	}
	
	/**
	 * Gets the <code>Pawn</code>'s <code>Position</code>.
	 * @return the <code>Position</code>.
	 */
	public final Position getPosition()
	{
		return position;
	}
	
	/**
	 * Gets the <code>Pawn</code>'s <code>Component</code>s.
	 * @return a <code>List</code> of <code>Component</code>s.
	 */
	public final List<Component> getComponents()
	{
		return components;
	}
	
	/**
	 * Gets the <code>Pawn</code>'s sorting position.
	 * @return the sorting position.
	 */
	public final int getSortPosition()
	{
		return sortPosition;
	}
	
	/**
	 * Set the <code>Pawn</code>'s sorting position.
	 * @param newSortPosition the sorting position.
	 */
	protected final void setSortPosition(int newSortPosition)
	{
		sortPosition = newSortPosition;
	}
}
