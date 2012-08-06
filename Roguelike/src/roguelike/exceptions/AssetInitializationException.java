package roguelike.exceptions;

public class AssetInitializationException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public AssetInitializationException()
	{
		super();
	}

	public AssetInitializationException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public AssetInitializationException(String message)
	{
		super(message);
	}

	public AssetInitializationException(Throwable cause)
	{
		super(cause);
	}
}
