package roguelike.engine.entity;

import java.awt.Image;
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

public class EntityImageFactory extends AbstractAssetFactory<Integer, Image>
{
	private Map<Integer, Image> assets = new HashMap<Integer, Image>();

	public AssetEntry<Integer, Image> loadAsset(String path, int lineNumber,
			String line) throws AssetInitializationException
	{
		String[] map = line.split(" ");
		try
		{
			int entityID = 0;

			try
			{
				entityID = Integer.parseInt(map[0]);
			}

			catch (Exception e)
			{
				System.err.println("Path, lineNumber, line\n" + path + "\n"
						+ lineNumber + "\n" + line);
			}

			File file = new File(path + map[1]);

			if (!file.exists())
			{
				throw new AssetInitializationException(
						new FileNotFoundException("File " + file.getPath()
								+ " specified in line " + lineNumber
								+ " cannot be found!"));
			}

			BufferedImage image = ImageIO.read(file);

			assets.put(entityID, image);

			return new AssetEntry<Integer, Image>(entityID, image);
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

	public Image getAsset(Integer key)
	{
		return assets.get(key);
	}

	public Map<Integer, Image> getAssets()
	{
		return assets;
	}

	public boolean requiresDefaultAsset()
	{
		return true;
	}
}
