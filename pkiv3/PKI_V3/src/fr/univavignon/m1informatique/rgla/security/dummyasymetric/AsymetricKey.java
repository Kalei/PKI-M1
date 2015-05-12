package fr.univavignon.m1informatique.rgla.security.dummyasymetric;

import fr.univavignon.m1informatique.rgla.directory.DirectoryException;
import fr.univavignon.m1informatique.rgla.security.AbstractAsymetricKey;

public class AsymetricKey extends AbstractAsymetricKey
{
	private static final long serialVersionUID = 1L;

	/**
	 * build pair of keys used by {@link AsymetricAlgorithm}.
	 * 
	 * @throws DirectoryException
	 */
	public AsymetricKey() throws DirectoryException
	{
		super(AsymetricAlgorithm.buildAlgorithm().getDistinguishedName());

		privateKey = new Key(algorithm);
		publicKey = new Key(algorithm);

	}

}
