package roguelike.engine.world;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

import roguelike.engine.asset.AssetManager;
import roguelike.engine.world.tile.Tile;
import roguelike.engine.world.tile.TileConstants;
import roguelike.engine.world.tile.TileFactory;
import roguelike.exceptions.AssetInitializationException;
import roguelike.exceptions.MapIndexOutOfBoundsException;
import roguelike.exceptions.UninitializedAssetManagerException;

/**
 * Contains the representation of the map and internal boundaries 
 * (e.g. walls). However, it does not handle the locations of entities,
 * this is handled by World. 
 */

public class Map 
{
	public static final int DEFAULT_MAP_WIDTH = 20;
	public static final int DEFAULT_MAP_HEIGHT = 20;

	private int width;
	private int height;
	
	private Tile[][] map;
	
	AssetManager<Integer, Tile> tiles;
	
	public Map() throws UninitializedAssetManagerException, IOException, AssetInitializationException
	{
		this(DEFAULT_MAP_WIDTH, DEFAULT_MAP_HEIGHT);
	}
	
	public Map(int width, int height) throws UninitializedAssetManagerException, IOException, AssetInitializationException
	{
		tiles = new AssetManager<Integer, Tile>(new TileFactory(), "assets/graphics/tiles/tile.txt");
		
		this.width = width;
		this.height = height;
		
		//TODO: Generate map or load from file.
		map = new Tile[width][height];
		

		//Set entire map to null
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				map[i][j] = tiles.getDefaultAsset();
			}
		}
		
		for(int i = 1; i < width-1; i++)
			for(int j = 1; j < height-1; j++)
				map[i][j] = tiles.getAsset(TileConstants.FLOOR_GREY);
		
		for(int i = 1; i < width - 1; i++)
		{
			map[i][0] = tiles.getAsset(TileConstants.BOTTOM_BOUNDARY_WALL);
			map[i][height-1] = tiles.getAsset(TileConstants.TOP_BOUNDARY_WALL);
		}
		
		for(int j = 1; j < height - 1; j++)
		{
			map[0][j] = tiles.getAsset(TileConstants.RIGHT_BOUNDARY_WALL);
			map[width-1][j] = tiles.getAsset(TileConstants.LEFT_BOUNDARY_WALL);
		}
	
		map[0][0] = tiles.getAsset(TileConstants.FLOOR_BLACK);
		map[width-1][0] = tiles.getAsset(TileConstants.FLOOR_BLACK);
		map[0][height-1] = tiles.getAsset(TileConstants.FLOOR_BLACK);
		map[width-1][height-1] = tiles.getAsset(TileConstants.FLOOR_BLACK);
	}
	
	public Tile get(int x, int y) throws MapIndexOutOfBoundsException
	{
		if(x < 0 || x >= width || y < 0 || y >= height)
		{
			String errorMessage = "Attempt to access outside " +
					"of the bounds of the Map.";
			errorMessage += "\nMap Bounds: " + width + ", " + height;
			errorMessage += "\nAccess Location: " + x + "," + y;
			throw new MapIndexOutOfBoundsException(errorMessage);
		}
		
		else
		{
			return map[x][y];
		}
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