package fr.univavignon.m1informatique.rgla.pki.types;

import java.io.Serializable;

import fr.univavignon.m1informatique.rgla.pki.PKIException;

/**
 * La classe <code>CertificateRenewalRequestData</code> contient les données de la requête de renouvellement d'un certificat.
 * 
 * @author mb
 *
 */
public class CertificateRenewalRequestData implements Serializable
{

	private long expire;
	private static final long serialVersionUID = 1L;

	private Certificate certificate;

	/**
	 * 
	 * @param expire the new expiration date of the certificate 
	 * @param certificate
	 * @throws PKIException if the expiration date is not correct
	 */
	public CertificateRenewalRequestData(long expire, Certificate certificate) throws PKIException
	{
		long currentTime = System.currentTimeMillis();

		if (expire < certificate.getLastCertificateElement().getValidityPeriod().getEnd())
			throw new PKIException("expire time " + expire + " is before ecrtification expiration");

		if (expire < currentTime)
			throw new PKIException("expire time " + expire + " is expired");

		this.expire = expire;
		this.certificate = certificate;
	}

	long getExpire()
	{
		return expire;
	}

	Certificate getCertificate()
	{
		return certificate;
	}
}
