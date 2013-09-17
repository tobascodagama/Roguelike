package roguelike.exceptions;

public class MapIndexOutOfBoundsException extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4767298029879359057L;

	public MapIndexOutOfBoundsException(String errorMessage)
	{
		super(errorMessage);
	}
}
