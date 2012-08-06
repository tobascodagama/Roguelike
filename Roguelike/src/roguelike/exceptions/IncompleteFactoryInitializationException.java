package roguelike.exceptions;

public class IncompleteFactoryInitializationException extends RuntimeException
{
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
