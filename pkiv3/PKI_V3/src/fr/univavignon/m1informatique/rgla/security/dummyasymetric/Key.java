package fr.univavignon.m1informatique.rgla.security.dummyasymetric;

import java.io.Serializable;

import fr.univavignon.m1informatique.rgla.directory.DistinguishedName;
import fr.univavignon.m1informatique.rgla.security.AbstractKey;

/**
 * @author mb
 */
public class Key extends AbstractKey implements Serializable
{

	private static final long serialVersionUID = 1L;

	/**
	 * build key used by {@link AsymetricAlgorithm}.
	 * 
	 * @param algorithmName name of algorithm which uses key
	 */
	
	public Key(DistinguishedName algorithmName)
	{
		super(algorithmName);
	}

}
