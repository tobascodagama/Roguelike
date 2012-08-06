package roguelike.engine.world.tile;

import java.awt.Image;

public class Tile implements Cloneable
{
	private final int tileID;
	private final int tileType;

	private final Image image;

	// Image to be rendered behind this tile
	Tile backgroundTile;

	public Tile(int tileID, int tileType, Image image)
	{
		this(tileID, tileType, image, null);
	}

	public Tile(int tileID, int tileType, Image image, Tile backgroundTile)
	{
		this.tileID = tileID;
		this.tileType = tileType;
		this.image = image;
		this.backgroundTile = backgroundTile;
	}

	public Tile clone()
	{
		return new Tile(tileID, tileType, image, backgroundTile);
	}

	public Tile getBackgroundTile()
	{
		return backgroundTile;
	}

	public void setBackgroundTile(Tile backgroundTile)
	{
		this.backgroundTile = backgroundTile;
	}

	public int getTileID()
	{
		return tileID;
	}

	public int getTileType()
	{
		return tileType;
	}

	public Image getImage()
	{
		return image;
	}

	public String toString()
	{
		return "Tile:\ntileID: " + tileID + "\ntileType: " + tileType
				+ "\nimage: " + image + "\nbackgroundTile: " + backgroundTile
				+ "\n";
	}
}
