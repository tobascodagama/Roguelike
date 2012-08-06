package roguelike.engine.world.tile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import roguelike.engine.asset.AbstractAssetFactory;
import roguelike.engine.asset.AssetEntry;
import roguelike.exceptions.AssetInitializationException;

public class TileFactory extends AbstractAssetFactory<Integer, Tile>
{
	private Map<Integer, Tile> assets = new HashMap<Integer, Tile>();

	public AssetEntry<Integer, Tile> loadAsset(String path, int lineNumber,
			String line) throws AssetInitializationException
	{
		String[] map = line.split(" ");
		try
		{
			int tileID = 0;
			int tileType = 0;

			try
			{
				tileID = Integer.parseInt(map[0]);
				tileType = Integer.parseInt(map[1]);
			}
			catch (Exception e)
			{
				System.err.println("Path, lineNumber, line\n" + path + "\n"
						+ lineNumber + "\n" + line);
			}
			File file = new File(path + map[2]);

			if (!file.exists())
			{
				throw new AssetInitializationException(
						new FileNotFoundException("File " + file.getPath()
								+ " specified in line " + lineNumber
								+ " cannot be found!"));
			}

			BufferedImage image = ImageIO.read(file);

			Tile tile = new Tile(tileID, tileType, image);

			assets.put(tileID, tile);

			return new AssetEntry<Integer, Tile>(tileID, tile);

		}
		catch (NumberFormatException ex)
		{
			throw new AssetInitializationException(ex);
		}
		catch (IOException ex)
		{
			throw new AssetInitializationException(ex);
		}
	}

	public Tile getAsset(Integer key)
	{
		return assets.get(key).clone();
	}

	public Map<Integer, Tile> getAssets()
	{
		return assets;
	}

	public boolean requiresDefaultAsset()
	{
		return true;
	}
}
