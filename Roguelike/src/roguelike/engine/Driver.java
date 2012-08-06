package roguelike.engine;

import java.io.IOException;

import roguelike.engine.world.World;
import roguelike.exceptions.AssetInitializationException;
import roguelike.exceptions.UninitializedAssetManagerException;
import roguelike.gui.GameFrame;

public class Driver
{
	static boolean running = true;

	public static void main(String[] args) throws IOException,
			UninitializedAssetManagerException, AssetInitializationException
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
	}
}