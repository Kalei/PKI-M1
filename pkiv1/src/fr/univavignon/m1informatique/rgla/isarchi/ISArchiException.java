package fr.univavignon.m1informatique.rgla.isarchi;

import java.io.Serializable;

public class ISArchiException extends Exception implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ISArchiException() {
		System.err.println("Exeption Forbidden caracters detected!");
		System.exit(0);
	}
	public ISArchiException(String message) {
		super(message);
	}
	public ISArchiException(String message,Throwable cause) {
		super(message,cause);
	}
	public ISArchiException(Throwable cause) {
	super (cause);
	}

}
