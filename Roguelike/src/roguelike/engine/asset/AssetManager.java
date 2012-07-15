package roguelike.engine.asset;

import java.awt.Image;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

import roguelike.exceptions.AssetInitializationException;
import roguelike.exceptions.UninitializedAssetManagerException;

public class AssetManager<K, V> {
	private String fileLoc;
	private Map<K, V> assets;
	private AssetFactory<K, V> factory;
	private String path = "";

	private boolean initialized = false;

	public AssetManager(AssetFactory<K, V> factory, String fileLoc) throws IOException, AssetInitializationException 
	{
		this(factory, fileLoc, true);
	}
	
	public AssetManager(AssetFactory<K, V> factory, String fileLoc, boolean initialize) throws IOException, AssetInitializationException
	{
		this.factory = factory;
		assets = new HashMap<K, V>();
		this.fileLoc = fileLoc;
		if(initialize)
		{
			initialize();
		}
	}

	public V getDefaultAsset()
	{
		return factory.getDefaultAsset();
	}
	
	public V getAsset(K key) throws UninitializedAssetManagerException {
		if (!initialized)
			throw new UninitializedAssetManagerException();
		V asset = assets.get(key);
		if (asset == null)
			asset = factory.getDefaultAsset();
		return asset;
	}

	public void initialize() throws IOException, AssetInitializationException {
		if(!initialized)
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
			throws AssetInitializationException {
		// checks
		// # means the line is a comment
		if (line.startsWith("#")) {
			return true;
		}

		else if (line.startsWith("setpath")) {
			String newPath = line.split(" ")[1];

			// ./filepath - maintains current path
			if (newPath.startsWith("./")) {
				path += newPath.substring(newPath.indexOf(".") + 1);
			}

			// ../filepath - moves up one directory and then goes to the path
			else if (newPath.startsWith("../")) {
				path = path.substring(0, path.lastIndexOf('/'));
				path = path.substring(0, path.lastIndexOf('/'));
				path += newPath.substring(newPath.indexOf("/") + 1);
			}

			else {
				path = newPath;
			}

			return true;
		}

		else if (line.startsWith("default")) {
			line = line.split(" ", 2)[1];
			AssetEntry<K, V> entry = factory.loadAsset(path, lineNumber, line);
			factory.setDefaultAsset(entry.getValue());
			assets.put(entry.getKey(), entry.getValue());
			
			return true;
		}
		
		else
		{
			return false;
		}
	}

	private void loadAssets() throws IOException, AssetInitializationException {
		File file = new File(fileLoc);
		BufferedReader fileStream = new BufferedReader(new FileReader(file));
		String line;

		int lineNumber = 0;

		while ((line = fileStream.readLine()) != null) {
			line = line.trim();
			
			// Line isn't empty
			if (!line.equals("")) {
				lineNumber++;
				if (!parseAsCommand(line, lineNumber)) {
					AssetEntry<K, V> entry = factory.loadAsset(path,
							lineNumber, line);
					assets.put(entry.getKey(), entry.getValue());
				}
			}
		}
	}
	
	public String toString()
	{
		String output = "";
		for(Entry<K, V> entry : assets.entrySet())
		{
			output += entry.getKey() + " - " + entry.getValue() + "\n";
		}
		return output;
	}
}
