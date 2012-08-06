package roguelike.engine;

import java.io.IOException;

import roguelike.engine.world.World;
import roguelike.exceptions.AssetInitializationException;
import roguelike.exceptions.UninitializedAssetManagerException;
import roguelike.gui.GameFrame;

public class Driver
{
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
			world.getController().processKeyEvents();
			frame.repaint();

			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException e)
			{
			}
		}

		closeGame(world);
	}

	public static void closeGame(World world)
	{
		// do things like saving the map, etc
	}
}