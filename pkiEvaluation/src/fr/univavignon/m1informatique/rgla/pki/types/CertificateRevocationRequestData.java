package fr.univavignon.m1informatique.rgla.pki.types;

import java.io.Serializable;

/**
 * La classe <code>CertificateRevocationRequestData</code> contient les données de la requête de revocation d'un certificat.
 * 
 * @author mb
 */
public class CertificateRevocationRequestData implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Certificate certificate;

	/**
	 * 
	 * @param certificate to revoke
	 */
	public CertificateRevocationRequestData(Certificate certificate)
	{
		this.certificate = certificate;
	}

	Certificate getCertificate()
	{
		return certificate;
	}
}
