package fr.univavignon.m1informatique.rgla.pki.types;

import java.io.Serializable;
import java.util.Calendar;

import fr.univavignon.m1informatique.rgla.directory.DistinguishedName;
import fr.univavignon.m1informatique.rgla.pki.PKIException;
import fr.univavignon.m1informatique.rgla.security.AbstractKey;

/**
 * La classe <code>CertificateSigningRequestData</code> contient les données de la requête de signature d'un élément de certificat.
 * 
 * @author mb
 *
 */
public class CertificateSigningRequestData implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Period validityPeriod;

	private AbstractKey publicKey;

	private DistinguishedName hashName;

	private DistinguishedName subjectName;

	private CertificateRight certificateRight;

	/**
	 * 
	 * @param subjectName of the certificate owner
	 * @param publicKey of the certificate owner
	 * @param hashName name of the hash algorithm
	 * @param validityPeriod of the certificate
	 * @param certificateRight
	 * @throws PKIException if the validity period is no correct
	 */
	public CertificateSigningRequestData(DistinguishedName subjectName, AbstractKey publicKey, DistinguishedName hashName, Period validityPeriod,
			CertificateRight certificateRight) throws PKIException
	{
		long currentTime = Calendar.getInstance().getTimeInMillis();

		if (currentTime > validityPeriod.getEnd())
			throw new PKIException("period " + validityPeriod + " is not valid");

		this.subjectName = subjectName;
		this.publicKey = publicKey;
		this.hashName = hashName;
		this.validityPeriod = validityPeriod;
		this.certificateRight = certificateRight;
	}

	
	boolean isValid()
	{
		long currentTime = Calendar.getInstance().getTimeInMillis();

		return validityPeriod.getStart() < currentTime && currentTime < validityPeriod.getEnd();
	}

	AbstractKey getPublicKey()
	{
		return publicKey;
	}

	Period getValidityPeriod()
	{
		return validityPeriod;
	}

	CertificateRight getCertificateRight()
	{
		return certificateRight;
	}

	DistinguishedName getSubjectName()
	{
		return subjectName;
	}

	DistinguishedName getHashName()
	{
		return hashName;
	}

	@Override
	public String toString()
	{
		StringBuffer result = new StringBuffer();

		result.append("Subject : ");
		result.append(subjectName);

		result.append("\nValidity Period :\n");
		result.append(validityPeriod);

		result.append("\nRight :\n");
		result.append(certificateRight);

		result.append("\nPublic Key Info :\n");
		result.append(publicKey);

		result.append("\nHash :\n");
		result.append(hashName);

		return result.toString();
	}
}
