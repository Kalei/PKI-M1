package fr.univavignon.m1informatique.rgla.security;

import java.io.Serializable;

import fr.univavignon.m1informatique.rgla.directory.DirectoryException;
import fr.univavignon.m1informatique.rgla.directory.DistinguishedName;
import fr.univavignon.m1informatique.rgla.security.IAsymetricAlgorithm;

/**
 * La classe <code>AbstractKey</code> permet de définir l'interface des clés de cryptage.
 * <p>
 * La clé est créée en fonction de l'algorithme de cryptage {@link AbstractKey#AbstractKey(DistinguishedName)}.
 * <p>
 * a partir de la clé, on peut accéder à l'algorithme qui y correspond {@link AbstractKey#getAsymetricAlgorithm()}.
 * 
 * @author mb
 */
public abstract class AbstractKey implements Serializable
{
	private static final long serialVersionUID = 1L;

	private DistinguishedName algorithm;

	/**
	 * @param algorithm which uses the key
	 */
	public AbstractKey(DistinguishedName algorithm)
	{
		this.algorithm = algorithm;
	}

	/**
	 * get the asymetric algorithm which uses this key.
	 * 
	 * @return the asymetric algorithm which uses this key
	 * @throws DirectoryException if the algorithm does not exist
	 */
	public IAsymetricAlgorithm getAsymetricAlgorithm() throws DirectoryException
	{
		return (IAsymetricAlgorithm) algorithm.getObject();
	}
}
