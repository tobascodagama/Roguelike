package roguelike.engine.world.tile;

public class TileConstants
{
	/**
	 * Tile IDs
	 * These are used to represent the different types 
	 * of tiles in the map. The art assets are linked in the 
	 * tile.txt file in assets/graphics.
	 */
		
	//Null tile
	public static final int DEFAULT_TILE = 0;
	
	//Floors
	private static final int DEFAULT_FLOOR = 100;
		public static final int FLOOR_GREY = DEFAULT_FLOOR + 1;
		public static final int FLOOR_BLACK = DEFAULT_FLOOR + 2;
	//Walls
	private static final int DEFAULT_WALL = DEFAULT_FLOOR + 100;
		public static final int HORIZONTAL_WALL = DEFAULT_WALL + 1;
		public static final int VERTICAL_WALL = DEFAULT_WALL + 2;
		public static final int CROSS_WALL = DEFAULT_WALL + 3;
			public static final int TOP_LEFT_CROSS_WALL = CROSS_WALL + 2;
			public static final int TOP_RIGHT_CROSS_WALL = CROSS_WALL + 1;
			public static final int BOTTOM_LEFT_CROSS_WALL = CROSS_WALL + 3;
			public static final int BOTTOM_RIGHT_CROSS_WALL = CROSS_WALL + 4;
		
	private static final int BOUNDARY_WALL = DEFAULT_WALL + 100;
		public static final int LEFT_BOUNDARY_WALL = BOUNDARY_WALL + 1;
		public static final int RIGHT_BOUNDARY_WALL = BOUNDARY_WALL + 2;
		public static final int TOP_BOUNDARY_WALL = BOUNDARY_WALL + 3;
		public static final int BOTTOM_BOUNDARY_WALL = BOUNDARY_WALL + 4;

	
	/**
	 * Tile Types
	 * These are used to represent the different properties of tiles in the map.
	 * It represents how something can be interacted with on the map.
	 */
		
	public static final int NON_INTERACTABLE = 0;
	
	//Describes that the tile cannot be moved onto.
	public static final int NON_CLIPPABLE = 1;
	public static final int MOVABLE = 2;
}