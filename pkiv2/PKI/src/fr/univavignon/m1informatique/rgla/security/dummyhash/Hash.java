package fr.univavignon.m1informatique.rgla.security.dummyhash;

import fr.univavignon.m1informatique.rgla.directory.DirectoryException;
import fr.univavignon.m1informatique.rgla.directory.DistinguishedName;
import fr.univavignon.m1informatique.rgla.directory.DistinguishedNamedObject;
import fr.univavignon.m1informatique.rgla.directory.DistinguishedNamedObjectServer;
import fr.univavignon.m1informatique.rgla.security.IHash;

public class Hash extends DistinguishedNamedObject implements IHash
{
	private static final long serialVersionUID = 1L;

	private final static String algorithmName = "dummy hash algorithm";

	public static IHash buildHash() throws DirectoryException
	{
		IHash hash = (IHash) DistinguishedNamedObjectServer.getDistinguishedNamedObject(algorithmName);

		if (hash == null)
			hash = new Hash();

		return hash;
	}

	private Hash() throws DirectoryException
	{
		super(algorithmName);
		// TODO Auto-generated constructor stub
	}

	public byte[] code(byte[] original)
	{
		return original;
	}

	@Override
	public DistinguishedName getDistinguishedName() {
		return getDistinguishedName();
	}

}
