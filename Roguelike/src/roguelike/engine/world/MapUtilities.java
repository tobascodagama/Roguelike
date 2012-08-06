package roguelike.engine.world;

import java.awt.Point;

public class MapUtilities
{
	// Prevent instantiation
	private MapUtilities()
	{
		throw new UnsupportedOperationException("Not allowed to "
				+ "instantiate MapUtilities!");

	}

	public static Point displacePoint(Point point, int xDisplace, int yDisplace)
	{
		Point newPoint = new Point(point);

		newPoint.x += xDisplace;
		newPoint.y += yDisplace;

		return newPoint;
	}
}
