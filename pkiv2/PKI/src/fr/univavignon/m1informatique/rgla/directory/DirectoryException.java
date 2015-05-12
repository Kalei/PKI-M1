package fr.univavignon.m1informatique.rgla.directory;

public class DirectoryException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DirectoryException(String name) {
		System.err.println(name+" n'est pas un nom unique !");
		System.exit(0);
	}	
}
