package org.sontana;

public class Toolbox
{
	
	/**
	 * Clamps a value between two numbers.
	 * @param value the clamped value.
	 * @param min the minimum possible value.
	 * @param max the maximum possible value.
	 * @return the clamped value between min and max.
	 */
	public static int clamp(int value, int min, int max)
	{
		return clamp(value, min, max, false);
	}
	
	/**
	 * Clamps a value between two numbers.
	 * @param value the clamped value.
	 * @param min the minimum possible value.
	 * @param max the maximum possible value.
	 * @param loop if values under min or over max should wrap back around.
	 * @return the clamped value between min and max.
	 */
	public static int clamp(int value, int min, int max, boolean loop) // TODO Loop
	{
		if(value < min)
		{
			return min;
		}
		else if(value > max)
		{
			return max;
		}
		
		return value;
	}
	
	/**
	 * Clamps a value between two numbers.
	 * @param value the clamped value.
	 * @param min the minimum possible value.
	 * @param max the maximum possible value.
	 * @return the clamped value between min and max.
	 */
	public static float clamp(float value, float min, float max)
	{
		return clamp(value, min, max, false);
	}
	
	/**
	 * Clamps a value between two numbers.
	 * @param value the clamped value.
	 * @param min the minimum possible value.
	 * @param max the maximum possible value.
	 * @param loop if values under min or over max should wrap back around.
	 * @return the clamped value between min and max.
	 */
	public static float clamp(float value, float min, float max, boolean loop) // TODO Loop
	{
		if(value < min)
		{
			return min;
		}
		else if(value > max)
		{
			return max;
		}
		
		return value;
	}
	
	
	
	/**
	 * Linearly interpolates between start and end by ratio.
	 * @param start the starting point at interpolate ratio 0.
	 * @param end the ending point at interpolate ratio 1.
	 * @param ratio the interpolate ratio between 0 and 1.
	 * @return a value between start and end.
	 */
	public static float lerp(float start, float end, float ratio)
	{
		if(ratio >= 1f)
		{
			return end;
		}
		else if(ratio <= 0f)
		{
			return start;
		}
		
		float ret = start + (end - start) * ratio;
		
		return ret;
	}
	
	/**
	 * Linearly interpolates between start and end by an unbounded ratio.
	 * @param start the starting point at interpolate ratio 0.
	 * @param end the ending point at interpolate ratio 1.
	 * @param ratio the interpolate ratio.
	 * @return a value relative to the distance between start and end.
	 */
	public static float lerpUnclamped(float start, float end, float ratio)
	{
		float ret = start + (end - start) * ratio;
		
		return ret;
	}
}
