package fr.univavignon.m1informatique.rgla.pki;

public class PKIException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public PKIException() {
	  System.err.println("Exeption Forbidden wrong use of PKI");
	  System.exit(0);
	}
	public PKIException(String name) {
		  super(name);
	}
	public PKIException(String name,Throwable cause) {
		  super(name,cause);
	}
	public PKIException(Throwable cause) {
		  super(cause);
	}
}
