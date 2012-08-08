package roguelike.engine.world;

import java.awt.Point;
import java.io.IOException;

import roguelike.engine.asset.AssetManager;
import roguelike.engine.world.tile.Tile;
import roguelike.engine.world.tile.TileConstants;
import roguelike.engine.world.tile.TileFactory;
import roguelike.exceptions.AssetInitializationException;
import roguelike.exceptions.MapIndexOutOfBoundsException;
import roguelike.exceptions.UninitializedAssetManagerException;

/**
 * Contains the representation of the map and internal boundaries (e.g. walls).
 * However, it does not handle the locations of entities, this is handled by
 * World.
 */

public class Map
{
	public static final int DEFAULT_MAP_WIDTH = 20;
	public static final int DEFAULT_MAP_HEIGHT = 20;

	private int width;
	private int height;

	private Tile[][] map;

	AssetManager<Integer, Tile> tiles;

	public Map() throws UninitializedAssetManagerException, IOException,
			AssetInitializationException
	{
		this(DEFAULT_MAP_WIDTH, DEFAULT_MAP_HEIGHT);
	}

	public Map(int width, int height)
			throws UninitializedAssetManagerException, IOException,
			AssetInitializationException
	{
		tiles = new AssetManager<Integer, Tile>(new TileFactory(),
				"assets/graphics/tiles/tile.txt");

		this.width = width;
		this.height = height;

		// TODO: Generate map or load from file.
		map = new Tile[width][height];

		// Set entire map to null
		fillRectWithTile(TileConstants.DEFAULT_TILE, 0, 0, width - 1,
				height - 1);

		// fill walls
		fillRectWithTile(TileConstants.BOTTOM_BOUNDARY_WALL, 1, 0, width - 1, 0);
		fillRectWithTile(TileConstants.TOP_BOUNDARY_WALL, 1, height - 1,
				width - 1, height - 1);

		fillRectWithTile(TileConstants.RIGHT_BOUNDARY_WALL, 0, 1, 0, height - 1);
		fillRectWithTile(TileConstants.LEFT_BOUNDARY_WALL, width - 1, 0,
				width - 1, height - 1);

		// fill center
		fillRectWithTile(TileConstants.FLOOR_GREY, 1, 1, width - 2, height - 2);

		// set corners
		setTile(TileConstants.FLOOR_BLACK, 0, 0);
		setTile(TileConstants.FLOOR_BLACK, 0, height - 1);
		setTile(TileConstants.FLOOR_BLACK, width - 1, 0);
		setTile(TileConstants.FLOOR_BLACK, width - 1, height - 1);

	}

	public void setTile(Tile tile, int x, int y)
	{
		map[x][y] = tile;
	}

	public void setTile(int tileID, int x, int y)
			throws UninitializedAssetManagerException
	{
		map[x][y] = tiles.getAsset(tileID);
	}

	public void fillRectWithTile(int tileID, int startX, int startY, int endX,
			int endY) throws UninitializedAssetManagerException
	{
		for (int x = startX; x <= endX; x++)
		{
			for (int y = startY; y <= endY; y++)
			{
				setTile(tileID, x, y);
			}
		}
	}

	public Tile get(int x, int y) throws MapIndexOutOfBoundsException
	{
		if (x < 0 || x >= width || y < 0 || y >= height)
		{
			String errorMessage = "Attempt to access outside "
					+ "of the bounds of the Map.";
			errorMessage += "\nMap Bounds: " + width + ", " + height;
			errorMessage += "\nAccess Location: " + x + "," + y;
			throw new MapIndexOutOfBoundsException(errorMessage);
		}

		else
		{
			return map[x][y];
		}
	}

	public Tile get(Point point) throws MapIndexOutOfBoundsException
	{
		return get(point.x, point.y);
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}
}