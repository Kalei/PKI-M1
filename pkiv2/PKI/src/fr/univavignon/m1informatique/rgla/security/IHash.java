package fr.univavignon.m1informatique.rgla.security;

import java.io.Serializable;

import fr.univavignon.m1informatique.rgla.directory.DistinguishedName;

/**
 * L'interface <code>IHash</code> doit être implémentée par les algorithmes de hachage.
 * 
 * @author mb
 */
public interface IHash extends Serializable
{

	/**
	 * to hash original byte array
	 * 
	 * @param original byte array to hash
	 * @return hashcode of byte array
	 */
	public byte[] code(byte[] original);

	/**
	 * gives the distinguished name of hash
	 * 
	 * @return the distinguished name of hash
	 */
	public DistinguishedName getDistinguishedName();
}
