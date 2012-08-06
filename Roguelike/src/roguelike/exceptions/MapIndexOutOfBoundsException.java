package roguelike.exceptions;

public class MapIndexOutOfBoundsException extends RuntimeException
{
	public MapIndexOutOfBoundsException(String errorMessage)
	{
		super(errorMessage);
	}
}
