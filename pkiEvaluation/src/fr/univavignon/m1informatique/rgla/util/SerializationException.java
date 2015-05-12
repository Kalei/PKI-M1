/**
 * 
 */
package fr.univavignon.m1informatique.rgla.util;

/**
 * @author mb
 *
 */
public class SerializationException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @see RuntimeException#RuntimeException()
	 */
	public SerializationException()
	{
		
	}

	/**
	 * @param message
	 * @param cause
	 * 
	 * @see RuntimeException#RuntimeException(String, Throwable)
	 */
	public SerializationException(String message, Throwable cause)
	{
		super(message, cause);
		
	}

	/**
	 * @param message
	 * 
	 * @see RuntimeException#RuntimeException(String)
	 */
	public SerializationException(String message)
	{
		super(message);
		
	}

	/**
	 * @param cause
	 * 
	 * @see RuntimeException#RuntimeException(Throwable)
	 */
	public SerializationException(Throwable cause)
	{
		super(cause);
		
	}

}
