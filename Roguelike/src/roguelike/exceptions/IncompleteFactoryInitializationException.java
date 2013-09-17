package roguelike.exceptions;

public class IncompleteFactoryInitializationException extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7154971039116680322L;

	public IncompleteFactoryInitializationException()
	{

	}

	public IncompleteFactoryInitializationException(String message)
	{
		super(message);
	}

	public IncompleteFactoryInitializationException(Throwable message)
	{
		super(message);
	}

	public IncompleteFactoryInitializationException(String message,
			Throwable cause)
	{
		super(message, cause);
	}
}
