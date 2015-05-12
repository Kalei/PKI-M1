package fr.univavignon.m1informatique.rgla.security;

import java.io.Serializable;

import fr.univavignon.m1informatique.rgla.directory.DistinguishedName;
import fr.univavignon.m1informatique.rgla.security.AbstractKey;

/**
 * L'interface <code>IAsymetricAlgorithm</code> doit être implémentée par les algorithmes asymétriques
 * 
 * @author mb
 */
public interface IAsymetricAlgorithm extends Serializable
{
	/**
	 * to encrypt a byte array
	 * 
	 * @param original byte array to encrypt
	 * @param key used to encrypt
	 * @return encrypted byte array
	 */
	public byte[] encrypt(byte[] original, AbstractKey key);

	/**
	 * to decrypt a byte array
	 * 
	 * @param encrypted byte array to decrypt
	 * @param key used to decrypt
	 * @return decrypted byte array
	 */
	public byte[] decrypt(byte[] encrypted, AbstractKey key);

	/**
	 * gives the distinguished name of algorithm
	 * 
	 * @return the distinguished name of algorithm
	 */
	public DistinguishedName getDistinguishedName();
}
