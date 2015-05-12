package fr.univavignon.m1informatique.rgla.pki.types;

import fr.univavignon.m1informatique.rgla.pki.PKIException;
import fr.univavignon.m1informatique.rgla.security.AbstractKey;
import fr.univavignon.m1informatique.rgla.security.SecurityException;

/**
 *  La classe <code>CertificateRevocationRequest</code> contient les données signées de la requête de évocation du certificat.
 * 
 * @author mb
 *
 */
public class CertificateRevocationRequest extends SignedObjectByCertificate
{

	/**
	 * 
	 * @param certificateRevocationRequestData
	 * @param privateKey used to sign
	 * @param certificate used to sign
	 * @throws SecurityException if the algorithms used to sign are unknown
	 * @throws PKIException if the certificate used to sign is no valid
	 */
	public CertificateRevocationRequest(CertificateRevocationRequestData certificateRevocationRequestData, AbstractKey privateKey, Certificate certificate) throws SecurityException, PKIException
	{
		super(certificateRevocationRequestData, privateKey, certificate);
	}

	/**
	 * check if the revocation request is authentic
	 * 
	 * @throws PKIException if the renewal request is not authentic 
	 */
	public void verify() throws PKIException
	{
		try 
		{
		if (!isAuthentic(getCertificate()))
			throw new PKIException("certificate revocation request " + this + "is not authentic");
		}
		catch (Exception e)
		{
			throw new PKIException(e);
		}
	}

	/**
	 * override to cast Object
	 */
	@Override
	public CertificateRevocationRequestData getObject()
	{
		return (CertificateRevocationRequestData) super.getObject();
	}

	/**
	 * get the certificate to revoke.
	 * 
	 * @return the certificate to revoke
	 */
	public Certificate getCertificate()
	{
		return getObject().getCertificate();
	}
}
