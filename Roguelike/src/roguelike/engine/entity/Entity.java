package roguelike.engine.entity;

import java.awt.Image;
import java.awt.Point;

public class Entity
{
	// used for identifying the entity in the game
	private final int id;
	private Image image;
	private Point position;

	public Entity(int id, Point position, Image image)
	{
		this.id = id;
		this.position = position;
		this.image = image;
	}

	public int getID()
	{
		return id;
	}

	public Image getImage()
	{
		return image;
	}

	public Point getPosition()
	{
		return position;
	}

	public void setImage(Image image)
	{
		if (image != null)
		{
			this.image = image;
		}

		else
		{
			throw new IllegalArgumentException("Cannot set the entity" + " "
					+ getID() + "'s image to null!");
		}
	}

	public void setPosition(Point position)
	{
		if (position != null)
		{
			this.position = position;
		}

		else
		{
			throw new IllegalArgumentException("Cannot set the entity" + " "
					+ getID() + "'s position to null!");
		}
	}
}
