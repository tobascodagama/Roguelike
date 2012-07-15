package roguelike.engine;

import java.io.IOException;

import roguelike.gui.GameFrame;
import roguelike.engine.world.World;
import roguelike.exceptions.AssetInitializationException;
import roguelike.exceptions.UninitializedAssetManagerException;

public class Driver
{
	public static void main(String[] args) throws IOException, UninitializedAssetManagerException, AssetInitializationException
	{
//		AssetManager.initialize();
		GameFrame frame = new GameFrame(new World());
	}
}
