package fr.univavignon.m1informatique.rgla.pki.types;

import fr.univavignon.m1informatique.rgla.directory.DistinguishedName;
import fr.univavignon.m1informatique.rgla.pki.PKIException;
import fr.univavignon.m1informatique.rgla.security.AbstractKey;
import fr.univavignon.m1informatique.rgla.security.SecurityException;
import fr.univavignon.m1informatique.rgla.security.SignedObject;

/**
 * La classe <code>CertificateSigningRequest</code> contient les données signées de la requête de signature d'un élément de certificat.
 * 
 * @author mb
 */
public class CertificateSigningRequest extends SignedObject
{

	private static final long serialVersionUID = 1L;

	/**
	 * @param certificateSigningRequestData
	 * @param privateKey used to sign
	 * @param hashName name of the hash algorithm
	 * @throws SecurityException if the data cannot be signed
	 */
	public CertificateSigningRequest(CertificateSigningRequestData certificateSigningRequestData, AbstractKey privateKey, DistinguishedName hashName)
			throws SecurityException
	{
		super(certificateSigningRequestData, privateKey, hashName);
	}

	/**
	 * override to cast Object
	 */
	@Override
	public CertificateSigningRequestData getObject()
	{
		return (CertificateSigningRequestData) super.getObject();
	}

	private AbstractKey getPublicKey()
	{
		return getObject().getPublicKey();
	}

	/**
	 * get the owner of the certificate
	 * 
	 * @return the owner
	 */
	public DistinguishedName getSubjectName()
	{
		return getObject().getSubjectName();
	}

	/**
	 * check the request
	 * 
	 * @throws PKIException if the request is not authentic
	 */
	public void verify() throws PKIException
	{
		try
		{
			if (!isAuthentic(getPublicKey()))
				throw new PKIException("certificate signing request " + this + "is not authentic");
		}
		catch (SecurityException e)
		{
			throw new PKIException("certificate signing request " + this + "is not authentic");
		}
	}
}
