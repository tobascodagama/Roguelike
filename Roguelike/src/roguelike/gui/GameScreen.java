package roguelike.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import roguelike.engine.GameDriver;
import roguelike.engine.entity.Entity;
import roguelike.engine.world.Map;
import roguelike.engine.world.World;
import roguelike.engine.world.tile.Tile;

public class GameScreen extends JPanel
{
	private final Dimension bounds = new Dimension(
			GraphicConstants.PIXEL_SCREEN_WIDTH,
			GraphicConstants.PIXEL_SCREEN_HEIGHT);
	World world;
	Dimension tileSize;

	public GameScreen(World world)
	{
		this.world = world;

		setMinimumSize(bounds);
		setMaximumSize(bounds);
		setPreferredSize(bounds);

		int tileWidth = (int) (bounds.getWidth() / GraphicConstants.TILES_DISPLAYED_WIDTH);
		// ((bounds.getWidth() - 2 * GraphicConstants.SCREEN_BORDER) /
		// GraphicConstants.TILES_DISPLAYED_WIDTH);
		int tileHeight = (int) (bounds.getHeight() / GraphicConstants.TILES_DISPLAYED_HEIGHT);
		// ((bounds.getHeight() - 2 * GraphicConstants.SCREEN_BORDER) /
		// GraphicConstants.TILES_DISPLAYED_HEIGHT);

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
		Point playerPosition = player.getPosition();

		int xCount = 0;

		int maxTileX = playerPosition.x
				+ GraphicConstants.TILES_DISPLAYED_WIDTH / 2;
		int minTileX = playerPosition.x
				- GraphicConstants.TILES_DISPLAYED_WIDTH / 2;

		int currentTileX = minTileX;

		while (xCount < GraphicConstants.TILES_DISPLAYED_WIDTH
				&& currentTileX <= maxTileX)
		{
			if (currentTileX < 0 || currentTileX > map.getWidth() - 1)
			{
				xCount++;
				currentTileX++;
				continue;
			}

			else
			{
				int yCount = 0;

				int maxTileY = playerPosition.y
						+ GraphicConstants.TILES_DISPLAYED_HEIGHT / 2;
				int minTileY = playerPosition.y
						- GraphicConstants.TILES_DISPLAYED_HEIGHT / 2;

				int currentTileY = minTileY;

				while (yCount < GraphicConstants.TILES_DISPLAYED_HEIGHT
						&& currentTileY <= playerPosition.y
								+ GraphicConstants.TILES_DISPLAYED_HEIGHT / 2)
				{
					if (currentTileY < 0 || currentTileY > map.getHeight() - 1)
					{
						yCount++;
						currentTileY++;
						continue;
					}
					else
					{
						int x = (int) (xCount * tileSize.getWidth());
						int y = (int) (yCount * tileSize.getHeight());
						Tile tile = map.get(currentTileX, currentTileY);

						if (tile != null)
						{
							// Draw background tile
							if (tile.getBackgroundTile() != null)
							{
								g.drawImage(
										tile.getBackgroundTile().getImage(), x,
										y, (int) tileSize.getWidth(),
										(int) tileSize.getHeight(), null);
							}

							// Draw main tile
							g.drawImage(tile.getImage(), x, y,
									(int) tileSize.getWidth(),
									(int) tileSize.getHeight(), null);

							if (GameDriver.debugging)
							{
								g.setColor(Color.YELLOW);
								g.drawString(
										currentTileX + ", " + currentTileY,
										(int) (x + tileSize.getWidth() / 2) - 10,
										(int) (y + tileSize.getHeight() / 2));
							}
							// System.out.printf("%5d ", tile.getTileID());
						}

						if (player != null && playerPosition.x == currentTileX
								&& playerPosition.y == currentTileY)
						{
							g.drawImage(player.getImage(), x, y,
									(int) tileSize.getWidth(),
									(int) tileSize.getHeight(), null);
						}

						currentTileY++;
						yCount++;
						// System.out.println();
					}
				}
				currentTileX++;
				xCount++;
				// System.out.println("\n\n");
			}
		}

		/*
		 * for (int i = playerPosition.x -
		 * GraphicConstants.TILES_DISPLAYED_WIDTH / 2; i < playerPosition.x +
		 * GraphicConstants.TILES_DISPLAYED_WIDTH / 2 && xCount <
		 * GraphicConstants.TILES_DISPLAYED_WIDTH; i++) { if (xCount >
		 * GraphicConstants.TILES_DISPLAYED_WIDTH - 1) { break; }
		 * 
		 * else if (i < 0 || i > map.getWidth() - 1) { i--; continue; }
		 * 
		 * else { int yCount = 0; for (int j = playerPosition.y -
		 * GraphicConstants.TILES_DISPLAYED_HEIGHT / 2; j < playerPosition.y +
		 * GraphicConstants.TILES_DISPLAYED_HEIGHT / 2 && yCount <
		 * GraphicConstants.TILES_DISPLAYED_HEIGHT; j++) {
		 * 
		 * if (yCount > GraphicConstants.TILES_DISPLAYED_HEIGHT - 1) { break; }
		 * 
		 * else if (j < 0 || j > map.getHeight() - 1) { j--; continue; }
		 * 
		 * else { int x = (int) (GraphicConstants.SCREEN_BORDER + (xCount *
		 * tileSize .getWidth())); int y = (int) (GraphicConstants.SCREEN_BORDER
		 * + (yCount * tileSize .getHeight()));
		 * 
		 * Tile tile = map.get(i, j);
		 * 
		 * if (tile != null) { // Draw background tile if
		 * (tile.getBackgroundTile() != null) { g.drawImage(
		 * tile.getBackgroundTile().getImage(), x, y, (int) tileSize.getWidth(),
		 * (int) tileSize.getHeight(), null); }
		 * 
		 * // Draw main tile g.drawImage(tile.getImage(), x, y, (int)
		 * tileSize.getWidth(), (int) tileSize.getHeight(), null);
		 * 
		 * // System.out.printf("%5d ", tile.getTileID()); }
		 * 
		 * int playerX = (int) player.getPosition().getX(); int playerY = (int)
		 * player.getPosition().getY(); if (player != null && playerX == xCount
		 * && playerY == yCount) { g.drawImage(player.getImage(), x, y, (int)
		 * tileSize.getWidth(), (int) tileSize.getHeight(), null); }
		 * System.out.println(xCount + " " + yCount + " " + i + " " + j);
		 * 
		 * yCount++;
		 * 
		 * // System.out.println(); }
		 * 
		 * // System.out.println("\n\n"); }
		 * 
		 * xCount++; } }
		 */

		/*
		 * for (int i = 0; i < map.getWidth(); i++) { for (int j = 0; j <
		 * map.getHeight(); j++) {
		 * 
		 * int x = (int) (20 + (i * tileSize.getWidth())); int y = (int) (20 +
		 * (j * tileSize.getHeight()));
		 * 
		 * Tile tile = map.get(i, j);
		 * 
		 * if (tile != null) { // Draw background tile if
		 * (tile.getBackgroundTile() != null) {
		 * g.drawImage(tile.getBackgroundTile().getImage(), x, y, (int)
		 * tileSize.getWidth(), (int) tileSize.getHeight(), null); }
		 * 
		 * // Draw main tile g.drawImage(tile.getImage(), x, y, (int)
		 * tileSize.getWidth(), (int) tileSize.getHeight(), null);
		 * 
		 * // System.out.printf("%5d ", tile.getTileID()); }
		 * 
		 * int playerX = (int) player.getPosition().getX(); int playerY = (int)
		 * player.getPosition().getY(); if (player != null && playerX == i &&
		 * playerY == j) { g.drawImage(player.getImage(), x, y, (int)
		 * tileSize.getWidth(), (int) tileSize.getHeight(), null); }
		 * 
		 * // System.out.println(); }
		 * 
		 * // System.out.println("\n\n"); }
		 */
	}
}
