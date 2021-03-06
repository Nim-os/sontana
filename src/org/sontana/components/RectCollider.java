package org.sontana.components;


import org.minueto.MinuetoColor;
import org.minueto.image.MinuetoRectangle;
import org.sontana.tools.Toolbox;

/**
 * <code>RectCollider</code> objects represent <code>AbstractCollider</code> objects with a rectangular shape.
 * @author Christophe Simon
 *
 */
public class RectCollider extends AbstractCollider
{
	private Position offset;
	
	public RectCollider(Position pOffset)
	{
		this(new Position(0,0), pOffset);
	}
	
	public RectCollider(Position pOrigin, Position pOffset)
	{
		super(new Debug(new MinuetoRectangle((int)pOffset.getX(), (int)pOffset.getY(), MinuetoColor.RED, false)), pOrigin);
		
		offset = new Position(pOffset);
	}

	@Override
	protected boolean testPointIntersection(Position pPoint)
	{
		float pointX = pPoint.getX();
		float pointY = pPoint.getY();
		
		
		if(pointX >= origin.getX() && pointX <= origin.getX() + offset.getX()
				&& pointY >= origin.getY() && pointY <= origin.getY() + offset.getY())
		{
			return true;
		}
			
			
		return false;
	}

	@Override
	protected Position getPointClosestToPoint(Position pPoint)
	{		
		float pointX, pointY;
		
		pointX = Toolbox.clamp(pPoint.getX(), origin.getX(), offset.getY());
		
		pointY = Toolbox.clamp(pPoint.getY(), origin.getY(), offset.getY());
		
		return new Position(pointX, pointY);
	}


}
