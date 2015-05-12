package fr.univavignon.m1informatique.rgla.pki.types;

import java.io.Serializable;

import fr.univavignon.m1informatique.rgla.pki.PKIException;
import fr.univavignon.m1informatique.rgla.security.AbstractKey;
import fr.univavignon.m1informatique.rgla.security.SecurityException;
import fr.univavignon.m1informatique.rgla.security.SignedObject;

/**
 * La classe <code>SignedObjectByCertificate</code> permet de signé un objet avec le certificat de l'expéditeur
 * 
 * @author mb
 *
 */
public class SignedObjectByCertificate
{

	private SignedObject signedObject;

	/**
	 * 
	 * @param object to sign
	 * @param senderPrivateKey used to sign
	 * @param senderCertificate used to find the hash algorithm
	 * @throws SecurityException if object cannot be signed
	 * @throws PKIException if the certificate is erroneous
	 */
	public SignedObjectByCertificate(Serializable object, AbstractKey senderPrivateKey, Certificate senderCertificate) throws SecurityException, PKIException
	{
		this.signedObject = new SignedObject(object, senderPrivateKey, senderCertificate.getLastCertificateElement().getHashName());
	}

	/**
	 * get signed object
	 * 
	 * @return signed object
	 */
	public Serializable getObject()
	{
		return signedObject.getObject();
	}

	/**
	 * verify if signed object is authentic
	 * 
	 * @param senderCertificate uses to authenticate
	 * @return true if is authentic, false otherwise
	 * @throws PKIException if the certificate is erroneous
	 * @throws SecurityException if object cannot be authenticate
	 */
	public boolean isAuthentic(Certificate senderCertificate) throws PKIException, SecurityException
	{
		return signedObject.isAuthentic(senderCertificate.getLastCertificateElement().getPublicKey());
	}
}
