package org.sontana.components;

/**
 * The <code>Position</code> class represents a point in 2D space.
 * @author Christophe Simon
 *
 */
public class Position
{
	public static final Position ZERO = new Position(0,0);
	
	private float x;
	private float y;
	
	/**
	 * Initialise a <code>Position</code> using coordinates.
	 * @param pX the x coordinate.
	 * @param pY the y coordinate.
	 */
	public Position(float pX, float pY)
	{
		x = pX;
		y = pY;
	}
	
	/**
	 * Initialise a <code>Position</code> using another <code>Position</code>.
	 * @param pPosition the other <code>Position</code>.
	 */
	public Position(Position pPosition)
	{
		x = pPosition.x;
		y = pPosition.y;
	}
	
	/**
	 * Get the <code>Position</code>'s X coordinate.
	 * @return the X coordinate.
	 */
	public float getX()
	{
		return x;
	}
	
	/**
	 * Set the <code>Position</code>'s X coordinate.
	 * @param pX the new X position.
	 */
	public void setX(float pX)
	{
		x = pX;
	}
	
	/**
	 * Get the <code>Position</code>'s Y coordinate.
	 * @return the Y coordinate.
	 */
	public float getY()
	{
		return y;
	}

	/**
	 * Set the <code>Position</code>'s Y coordinate.
	 * @param pY the new Y position.
	 */
	public void setY(float pY)
	{
		y = pY;
	}
	
	/**
	 * Set the <code>Position</code>'s X and Y coordinates.
	 * @param pX the new X coordinate.
	 * @param pY the new Y coordinate.
	 */
	public void set(float pX, float pY)
	{
		x = pX;
		y = pY;
	}
	
	/**
	 * 
	 * @param pPosition
	 */
	public void set(Position pPosition)
	{
		x = pPosition.x;
		y = pPosition.y;
	}
	
	/**
	 * Move the <code>Position</code> relative to its current position.
	 * @param pX the amount to move the X coordinate.
	 * @param pY the amount to move the Y coordinate.
	 */
	public void move(float pX, float pY)
	{
		x += pX;
		y += pY;
	}
	
	/**
	 * Move the <code>Position</code> relative to its current position.
	 * @param pPosition the <code>Position</code> to move the <code>Position</code> by.
	 */
	public void move(Position pPosition)
	{
		x += pPosition.x;
		y += pPosition.y;
	}
	
	@Override
	public String toString()
	{
		return "(" + x + ", " + y + ")";
	}
	
	
	/*
	 * Helper Functions
	 */
	
	
	/**
	 * Get the distance between two <code>Position</code>s.
	 * @param a the first <code>Position</code>.
	 * @param b the second <code>Position</code>.
	 * @return the distance between a and b.
	 */
	public static float distance(Position a, Position b)
	{
		float dstX = a.x - b.x;
		float dstY = a.y - b.y;
		
		float ret = (float)Math.sqrt(dstX * dstX + dstY * dstY);
		
		return ret;
	}
	
	/**
	 * Linearly interpolates between start and end by ratio.
	 * @param start the starting <code>Position</code> at interpolate ratio 0.
	 * @param end the ending <code>Position</code> at interpolate ratio 1.
	 * @param ratio the interpolate ratio between 0 and 1.
	 * @return a value between start and end.
	 */
	public static Position lerp(Position start, Position end, float ratio)
	{
		if(ratio >= 1f)
		{
			return end;
		}
		else if(ratio <= 0f)
		{
			return start;
		}
		
		Position ret = new Position(start.x + (end.x - start.x) * ratio, start.y + (end.y - start.y) * ratio);
		
		return ret;
	}
	
	/**
	 * Linearly interpolates between start and end by an unbounded ratio.
	 * @param start the starting <code>Position</code> at interpolate ratio 0.
	 * @param end the ending <code>Position</code> at interpolate ratio 1.
	 * @param ratio the interpolate ratio.
	 * @return a value relative to the distance between start and end.
	 */
	public static Position lerpUnclamped(Position start, Position end, float ratio)
	{
		Position ret = new Position(start.x + (end.x - start.x) * ratio, start.y + (end.y - start.y) * ratio);
		
		return ret;
	}
	
	/**
	 * Add two <code>Position</code>s together.
	 * @param pFirst the first <code>Position</code>.
	 * @param pSecond the second <code>Position</code>.
	 * @return the new <code>Position</code>.
	 */
	public static Position add(Position pFirst, Position pSecond)
	{
		Position ret = new Position(pFirst);

		ret.x += pSecond.x;
		ret.y += pSecond.y;
		
		return ret;
	}
	
	/**
	 * Subtract two <code>Position</code>s.
	 * @param pFirst the first <code>Position</code>.
	 * @param pSecond the second <code>Position</code>.
	 * @return the new <code>Position</code>.
	 */
	public static Position sub(Position pFirst, Position pSecond)
	{
		Position ret = new Position(pFirst);
		
		ret.x -= pSecond.x;
		ret.y -= pSecond.y;
		
		return ret;
	}
}
