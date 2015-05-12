package fr.univavignon.m1informatique.rgla.pki.types;

import fr.univavignon.m1informatique.rgla.directory.DistinguishedName;
import fr.univavignon.m1informatique.rgla.security.AbstractKey;
import fr.univavignon.m1informatique.rgla.security.SecurityException;
import fr.univavignon.m1informatique.rgla.security.SignedObject;

/**
 * la classe <code>CertificateElement</code> représente l'élément d'un certificat, elle contient les données signées d'un élément de certificat
 * {@link CertificateElementData}.
 * 
 * @author mb
 */
public class CertificateElement extends SignedObject
{
	private static final long serialVersionUID = 1L;

	/**
	 * build a signed certificate element data.
	 * 
	 * @param certificateElementData to sign
	 * @param privateKey used to sign
	 * @param hashName name of hash algorithm
	 * @throws SecurityException if data cannot be signed
	 */
	public CertificateElement(CertificateElementData certificateElementData, AbstractKey privateKey, DistinguishedName hashName) throws SecurityException
	{
		super(certificateElementData, privateKey, hashName);
	}

	/**
	 * verify a signed object sign by this certificate element
	 * 
	 * @param signedObject to verify
	 * @return true if is authentic
	 * @throws SecurityException if signed object cannot be verified
	 */
	public boolean verify(SignedObject signedObject) throws SecurityException
	{
		AbstractKey publicKey = getObject().getCertificateSigningRequestData().getPublicKey();

		return signedObject.isAuthentic(publicKey);
	}

	/**
	 * override to cast Object
	 */
	@Override
	public CertificateElementData getObject()
	{
		return (CertificateElementData)super.getObject();
	}

	/**
	 * get the public key contained in certificate element.
	 * 
	 * @return the public key
	 */
	public AbstractKey getPublicKey()
	{
		return getObject().getCertificateSigningRequestData().getPublicKey();
	}

	/**
	 * get the name of hash algorithm used to sign
	 * 
	 * @return the name of hash algorithm used to sign
	 */
	public DistinguishedName getHashName()
	{
		return getObject().getCertificateSigningRequestData().getHashName();
	}

	/**
	 * get the certificate element validity period.
	 * 
	 * @return the certificate element validity period
	 */
	public Period getValidityPeriod()
	{
		return getObject().getCertificateSigningRequestData().getValidityPeriod();
	}

	/**
	 * check is certificate is always valid. Compare the current date with the validity period
	 * 
	 * @return true if is valid
	 */
	public boolean isValid()
	{
		return getObject().getCertificateSigningRequestData().isValid();
	}

	/**
	 * get the certificate element serial number
	 * 
	 * @return the certificate element serial number
	 */
	public long getSerialNumber()
	{
		return getObject().getSerialNumber();
	}

	/**
	 * get the certificate right
	 * 
	 * @return the certificate right
	 */
	public CertificateRight getCertificateRight()
	{
		return getObject().getCertificateSigningRequestData().getCertificateRight();
	}

	/**
	 * get the subject name who owns the certificate.
	 * 
	 * @return the subject name who owns the certificate
	 */
	public DistinguishedName getSubjectName()
	{
		return getObject().getCertificateSigningRequestData().getSubjectName();
	}

	/**
	 * 
	 */
	@Override
	public String toString()
	{
		StringBuffer result = new StringBuffer();

		result.append("\n Certifiacte \n");

		result.append(getObject().toString());

		result.append("\n Signing not displayed \n");

		return result.toString();
	}

	/**
	 * @uml.property  name="object"
	 * @uml.associationEnd  inverse="certificateElement:fr.univavignon.m1informatique.rgla.pki.types.CertificateElementData"
	 */
	private CertificateElementData object;
}
