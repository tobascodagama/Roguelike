package roguelike.gui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import roguelike.engine.asset.AssetManager;
import roguelike.engine.world.Map;
import roguelike.engine.world.World;
import roguelike.engine.world.tile.Tile;
import roguelike.engine.world.tile.TileFactory;
import roguelike.exceptions.AssetInitializationException;
import roguelike.exceptions.MapIndexOutOfBoundsException;
import roguelike.exceptions.UninitializedAssetManagerException;

public class GameScreen extends JPanel 
{
	private final Dimension bounds = new Dimension(
			GraphicConstants.SCREEN_WIDTH, GraphicConstants.SCREEN_HEIGHT);
	World world;
	Dimension tileSize;

	public GameScreen(World world) {
		this.world = world;

		setMinimumSize(bounds);
		setMaximumSize(bounds);
		setPreferredSize(bounds);

		int tileWidth = (int) ((bounds.getWidth() - 2 * GraphicConstants.SCREEN_BORDER) / world
				.getMap().getWidth());
		int tileHeight = (int) ((bounds.getHeight() - 2 * GraphicConstants.SCREEN_BORDER) / world
				.getMap().getHeight());

		tileSize = new Dimension(tileWidth, tileHeight);

		setBackground(Color.black);
	}

	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);

			Map map = world.getMap();
			
			try
			{	
				for (int i = 0; i < map.getWidth(); i++) 
				{
					for (int j = 0; j < map.getHeight(); j++) 
					{
						
						int x = (int) (20 + (i * tileSize.getWidth()));
						int y = (int) (20 + (j * tileSize.getHeight()));
	
						Tile tile = map.get(i, j);
	
						if(tile != null)
						{
							//Draw background tile
							if(tile.getBackgroundTile() != null)
							{
								g.drawImage(tile.getBackgroundTile().getImage(), x, y, 
										(int) tileSize.getWidth(), 
										(int) tileSize.getHeight(), null);
							}
							
							//Draw main tile
							g.drawImage(tile.getImage(), x, y,
									(int) tileSize.getWidth(),
									(int) tileSize.getHeight(), null);
		
							System.out.printf("%5d ", tile.getTileID());
						}
					}
	
					System.out.println("\n\n");
				}
			}

			catch(MapIndexOutOfBoundsException e)
			{
				e.printStackTrace();
			}
	}
}
