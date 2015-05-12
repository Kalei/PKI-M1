package fr.univavignon.m1informatique.rgla.security;

import java.io.Serializable;

import fr.univavignon.m1informatique.rgla.directory.DistinguishedName;

/**
 * La classe <code>AbstractAsymetricKey</code> permet de définir l'interface des clés asymétriques.
 * <p>
 * Le constructeur à partir de l'algorithme de cryptage construit un couple de couple {@link AbstractAsymetricKey#AbstractAsymetricKey(DistinguishedName)}. Ce
 * couple est accessible à partir des accesseurs {@link AbstractAsymetricKey#getPrivateKey()} {@link AbstractAsymetricKey#getPublicKey()}.
 * 
 * @author mb
 */
public abstract class AbstractAsymetricKey implements Serializable
{
	private static final long serialVersionUID = 1L;

	protected AbstractKey privateKey;

	protected AbstractKey publicKey;

	protected DistinguishedName algorithm;

	/**
	 * 
	 * @param algorithmName name of algorithm which uses the pair of keys
	 */
	public AbstractAsymetricKey(DistinguishedName algorithmName)
	{
		this.algorithm = algorithmName;
	}

	/**
	 * get the private key.
	 * 
	 * @return the private key
	 */
	public AbstractKey getPrivateKey()
	{
		return privateKey;
	}

	/**
	 * get the public key.
	 * 
	 * @return the public key
	 */
	public AbstractKey getPublicKey()
	{
		return publicKey;
	}
}
