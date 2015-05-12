package fr.univavignon.m1informatique.rgla.security.dummyasymetric;

import fr.univavignon.m1informatique.rgla.directory.DirectoryException;
import fr.univavignon.m1informatique.rgla.directory.DistinguishedName;
import fr.univavignon.m1informatique.rgla.directory.DistinguishedNamedObject;
import fr.univavignon.m1informatique.rgla.directory.DistinguishedNamedObjectServer;
import fr.univavignon.m1informatique.rgla.security.AbstractKey;
import fr.univavignon.m1informatique.rgla.security.IAsymetricAlgorithm;

public class AsymetricAlgorithm extends DistinguishedNamedObject implements IAsymetricAlgorithm
{
	private static final long serialVersionUID = 1L;
	
	private final static String algorithmName= "dummy asymetric algorithm";
	
	public static IAsymetricAlgorithm buildAlgorithm() throws DirectoryException
	{
		IAsymetricAlgorithm algorithm = (IAsymetricAlgorithm)DistinguishedNamedObjectServer.getDistinguishedNamedObject(algorithmName);
		
		if (algorithm==null)
			algorithm = new AsymetricAlgorithm();
		
		return algorithm;
	}
	
	private AsymetricAlgorithm() throws DirectoryException
	{
		super(algorithmName);
		
	}

	public byte[] encrypt(byte[] original, AbstractKey key)
	{
		return original;
	}

	public byte[] decrypt(byte[] crypted, AbstractKey key)
	{

		return crypted;
	}

	@Override
	public DistinguishedName getDistinguishedName() {
		return DistinguishedNamedObjectServer.getDistinguishedNamedObject(algorithmName).getDN();
	}

}
