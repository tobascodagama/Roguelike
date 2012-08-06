package roguelike.engine.asset;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map.Entry;

import roguelike.exceptions.AssetInitializationException;
import roguelike.exceptions.IncompleteFactoryInitializationException;
import roguelike.exceptions.UninitializedAssetManagerException;

public class AssetManager<K, V>
{
	private String fileLoc;
	private AssetFactory<K, V> factory;
	private String path = "";

	private boolean initialized = false;

	public AssetManager(AssetFactory<K, V> factory, String fileLoc)
			throws IOException, AssetInitializationException
	{
		this(factory, fileLoc, true);
	}

	public AssetManager(AssetFactory<K, V> factory, String fileLoc,
			boolean initialize) throws IOException,
			AssetInitializationException
	{
		this.factory = factory;
		this.fileLoc = fileLoc;
		if (initialize)
		{
			initialize();
		}
	}

	public V getDefaultAsset() throws UninitializedAssetManagerException
	{
		if (!initialized)
			throw new UninitializedAssetManagerException();

		V asset = factory.getDefaultAsset();

		if (asset == null && factory.requiresDefaultAsset())
		{
			throw new IncompleteFactoryInitializationException(
					"Factory does not have a default asset.");
		}

		return asset;
	}

	public V getAsset(K key) throws UninitializedAssetManagerException
	{
		if (!initialized)
			throw new UninitializedAssetManagerException();
		V asset = factory.getAsset(key);
		if (asset == null)
			asset = getDefaultAsset();
		return asset;
	}

	public void initialize() throws IOException, AssetInitializationException
	{
		if (!initialized)
		{
			loadAssets();
			initialized = true;
		}
	}

	/**
	 * attempts to match a line to one of the defined commands. If the line
	 * matches, then the command is executed and a value of true is returned
	 * immediately afterward, otherwise a value of false is returned.
	 * 
	 * @param line
	 *            the command to be checked and possibly executed.
	 * @return true if a command and executed, false otherwise.
	 * @throws AssetInitializationException
	 */
	private boolean parseAsCommand(String line, int lineNumber)
			throws AssetInitializationException
	{
		// checks
		// # means the line is a comment
		if (line.startsWith("#"))
		{
			return true;
		}

		else if (line.startsWith("load"))
		{
			line = line.split(" ", 2)[1].trim();

			if (line.startsWith("default"))
			{
				line = line.split(" ", 2)[1].trim();
				factory.setDefaultAsset(factory.loadAsset(path, lineNumber,
						line).getValue());
			}

			else
			{
				factory.loadAsset(path, lineNumber, line);
			}

			return true;
		}

		else if (line.startsWith("setpath"))
		{
			String newPath = line.split(" ")[1];

			// ./filepath - maintains current path
			if (newPath.startsWith("./"))
			{
				path += newPath.substring(newPath.indexOf(".") + 1);
			}

			// ../filepath - moves up one directory and then goes to the path
			else if (newPath.startsWith("../"))
			{
				path = path.substring(0, path.lastIndexOf('/'));
				path = path.substring(0, path.lastIndexOf('/'));
				path += newPath.substring(newPath.indexOf("/") + 1);
			}

			else
			{
				path = newPath;
			}

			return true;
		}

		else
		{
			return false;
		}
	}

	private void loadAssets() throws IOException, AssetInitializationException
	{
		File file = new File(fileLoc);
		BufferedReader fileStream = new BufferedReader(new FileReader(file));
		String line;

		int lineNumber = 0;

		while ((line = fileStream.readLine()) != null)
		{
			line = line.trim();
			// Line isn't empty
			if (!line.equals(""))
			{
				lineNumber++;

				if (!parseAsCommand(line, lineNumber))
				{
					throw new AssetInitializationException(
							"Unable to parse line " + lineNumber + " in file "
									+ file.getPath());
				}
			}
		}
	}

	public String toString()
	{
		String output = "";
		for (Entry<K, V> entry : factory.getAssets().entrySet())
		{
			output += entry.getKey() + " - " + entry.getValue() + "\n";
		}
		return output;
	}
}
