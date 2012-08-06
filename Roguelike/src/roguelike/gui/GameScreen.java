package roguelike.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import roguelike.engine.entity.Entity;
import roguelike.engine.world.Map;
import roguelike.engine.world.World;
import roguelike.engine.world.tile.Tile;

public class GameScreen extends JPanel
{
	private final Dimension bounds = new Dimension(
			GraphicConstants.SCREEN_WIDTH, GraphicConstants.SCREEN_HEIGHT);
	World world;
	Dimension tileSize;

	public GameScreen(World world)
	{
		this.world = world;

		setMinimumSize(bounds);
		setMaximumSize(bounds);
		setPreferredSize(bounds);

		int tileWidth = (int) ((bounds.getWidth() - 2 * GraphicConstants.SCREEN_BORDER) / world
				.getMap().getWidth());
		int tileHeight = (int) ((bounds.getHeight() - 2 * GraphicConstants.SCREEN_BORDER) / world
				.getMap().getHeight());

		this.setFocusable(true);
		this.addKeyListener(world.getController());
		this.requestFocusInWindow();

		tileSize = new Dimension(tileWidth, tileHeight);

		setBackground(Color.black);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Map map = world.getMap();

		Entity player = world.getPlayer();

		for (int i = 0; i < map.getWidth(); i++)
		{
			for (int j = 0; j < map.getHeight(); j++)
			{

				int x = (int) (20 + (i * tileSize.getWidth()));
				int y = (int) (20 + (j * tileSize.getHeight()));

				Tile tile = map.get(i, j);

				if (tile != null)
				{
					// Draw background tile
					if (tile.getBackgroundTile() != null)
					{
						g.drawImage(tile.getBackgroundTile().getImage(), x, y,
								(int) tileSize.getWidth(),
								(int) tileSize.getHeight(), null);
					}

					// Draw main tile
					g.drawImage(tile.getImage(), x, y,
							(int) tileSize.getWidth(),
							(int) tileSize.getHeight(), null);

					// System.out.printf("%5d ", tile.getTileID());
				}

				int playerX = (int) player.getPosition().getX();
				int playerY = (int) player.getPosition().getY();
				if (player != null && playerX == i && playerY == j)
				{
					g.drawImage(player.getImage(), x, y,
							(int) tileSize.getWidth(),
							(int) tileSize.getHeight(), null);
				}
			}

			// System.out.println("\n\n");
		}
	}
}
