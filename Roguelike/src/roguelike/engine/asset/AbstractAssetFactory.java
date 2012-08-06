package roguelike.engine.asset;

import java.util.Map;

import roguelike.exceptions.AssetInitializationException;

public abstract class AbstractAssetFactory<K, V> implements AssetFactory<K, V>
{
	private V defaultAsset;

	public V getDefaultAsset()
	{
		return defaultAsset;
	}

	public void setDefaultAsset(V asset)
	{
		defaultAsset = asset;
	}

	public abstract boolean requiresDefaultAsset();

	public abstract AssetEntry<K, V> loadAsset(String path, int lineNumber,
			String line) throws AssetInitializationException;

	public abstract V getAsset(K key);

	public abstract Map<K, V> getAssets();
}
