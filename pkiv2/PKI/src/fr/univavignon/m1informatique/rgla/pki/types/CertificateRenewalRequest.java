package fr.univavignon.m1informatique.rgla.pki.types;

import fr.univavignon.m1informatique.rgla.pki.PKIException;
import fr.univavignon.m1informatique.rgla.security.AbstractKey;
import fr.univavignon.m1informatique.rgla.security.SecurityException;

/**
 * La classe <code>CertificateRenewalRequest</code> contient les données signées de la requête de renouvellement du certificat.
 * 
 * @author mb
 *
 */
public class CertificateRenewalRequest extends SignedObjectByCertificate
{

	/**
	 * 
	 * @param certificateRenewalRequestData
	 * @param privateKey used to sign
	 * @param certificate used to sign
	 * @throws PKIException if the certificate used to sign is no valid
	 * @throws SecurityException if the algorithms used to sign are unknown
	 */
	public CertificateRenewalRequest(CertificateRenewalRequestData certificateRenewalRequestData, AbstractKey privateKey, Certificate certificate)
			throws PKIException, SecurityException
	{
		super(certificateRenewalRequestData, privateKey, certificate);
	}

	/**
	 * get the new expiration date.
	 * 
	 * @return the new expiration date
	 */
	public long getExpire()
	{
		return getObject().getExpire();
	}

	/**
	 * check if the renewal request is authentic
	 * 
	 * @throws PKIException if the renewal request is not authentic 
	 */
	public void verify() throws PKIException
	{
		try
		{
			if (!isAuthentic(getCertificate()))
				throw new PKIException("certificate renewal request " + this + "is not authentic");
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
	public CertificateRenewalRequestData getObject()
	{
		return (CertificateRenewalRequestData) super.getObject();
	}

	/**
	 * get the certificate to renew.
	 * 
	 * @return the certificate to renew
	 */
	public Certificate getCertificate()
	{
		return getObject().getCertificate();
	}
}
