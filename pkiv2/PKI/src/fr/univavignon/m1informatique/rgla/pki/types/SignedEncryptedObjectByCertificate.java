/**
 * 
 */
package fr.univavignon.m1informatique.rgla.pki.types;

import java.io.Serializable;

import fr.univavignon.m1informatique.rgla.pki.PKIException;
import fr.univavignon.m1informatique.rgla.security.AbstractKey;
import fr.univavignon.m1informatique.rgla.security.EncryptedObject;
import fr.univavignon.m1informatique.rgla.security.SecurityException;

/**
 * La classe <code>SignedEncryptedObjectByCertificate</code> permet de sécurisé un objet en le cryptant et en signant l'objet crypté avec un certificat
 * 
 * @author mb
 */
public class SignedEncryptedObjectByCertificate extends SignedObjectByCertificate
{

	/**
	 * 
	 * @param object to sign
	 * @param senderPrivateKey used to sign
	 * @param senderCertificate used to find the hash algorithm
	 * @param receiverCertificate used to encrypt
	 * @throws SecurityException if object cannot be encrypted
	 * @throws PKIException if the certificate is erroneous
	 */
	public SignedEncryptedObjectByCertificate(Serializable object, AbstractKey senderPrivateKey, Certificate senderCertificate, Certificate receiverCertificate)
			throws SecurityException, PKIException
	{
		super(new EncryptedObject(object, receiverCertificate.getLastCertificateElement().getPublicKey()), senderPrivateKey, senderCertificate);

	}
	
	/**
	 * get the object
	 * 
	 * @param receiverPrivateKey used to decrypt
	 * @return the object
	 * @throws SecurityException if encryption algorithm is not found from the key
	 */
	public Object getObject(AbstractKey receiverPrivateKey) throws SecurityException
	{

		return ((EncryptedObject) super.getObject()).getObject(receiverPrivateKey);
	}

}
