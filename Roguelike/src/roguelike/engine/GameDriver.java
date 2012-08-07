package roguelike.engine;

import java.io.IOException;

import roguelike.engine.world.World;
import roguelike.exceptions.AssetInitializationException;
import roguelike.exceptions.UninitializedAssetManagerException;
import roguelike.gui.GameFrame;

public class GameDriver
{
	public static boolean debugging = false;

	public static boolean running = true;

	public static void main(String[] args) throws IOException,
			UninitializedAssetManagerException, AssetInitializationException
	{
		startGame();
	}

	public static void startGame() throws AssetInitializationException,
			UninitializedAssetManagerException, IOException
	{
		World world = new World();
		GameFrame frame = new GameFrame(world);

		while (running)
		{
			processInput(world);
			drawWorld(world, frame);

			try
			{
				Thread.sleep(200);
			}
			catch (InterruptedException e)
			{
			}
		}

		closeGame(world);
	}

	public static void processInput(World world)
	{
		world.getController().processKeyEvents();
	}

	public static void drawWorld(World world, GameFrame frame)
	{
		frame.repaint();
	}

	public static void closeGame(World world)
	{
		// do things like saving the map, etc
	}
}