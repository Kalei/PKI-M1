package fr.univavignon.m1informatique.rgla.security;

/**
 * L'exception <code>SecurityException</code> est levée pour mettre en évidence un défaut de sécurité.
 * 
 * @author mb
 */
public class SecurityException extends Exception
{

	private static final long serialVersionUID = 1L;

	/**
	 * @see Exception#Exception()
	 */
	public SecurityException()
	{

	}

	/**
	 * @param message
	 * @see Exception#Exception(String)
	 */
	public SecurityException(String message)
	{
		super(message);

	}

	/**
	 * @param cause
	 * @see Exception#Exception(Throwable)
	 */
	public SecurityException(Throwable cause)
	{
		super(cause);

	}

	/**
	 * @param message
	 * @param cause
	 * @see Exception#Exception(String, Throwable)
	 */
	public SecurityException(String message, Throwable cause)
	{
		super(message, cause);

	}

}
