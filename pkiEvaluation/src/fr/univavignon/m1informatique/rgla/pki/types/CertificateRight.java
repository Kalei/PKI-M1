package fr.univavignon.m1informatique.rgla.pki.types;

import java.io.Serializable;

/**
 *  La classe <code>CertificateRight</code> définit les droits d'utilisation d'un certificat.
 *  
 * @author mb
 *
 */
public class CertificateRight implements Serializable
{
	private static final long serialVersionUID = 1L;

	private boolean certificationAllowed;

	private boolean signingAllowed;

	private boolean EncryptionAllowed;

	/**
	 * 
	 * @param certificationAllowed
	 * @param signingAllowed
	 * @param encryptionAllowed
	 */
	public CertificateRight(boolean certificationAllowed, boolean signingAllowed, boolean encryptionAllowed)
	{
		this.certificationAllowed = certificationAllowed;
		this.signingAllowed = signingAllowed;
		this.EncryptionAllowed = encryptionAllowed;
	}

	/**
	 * check if certification is allowed.
	 * 
	 * @return true if certification is allowed
	 */
	public boolean isCertificationAllowed()
	{
		return certificationAllowed;
	}

	/**
	 * check if signing is allowed.
	 * 
	 * @return true if signing is allowed
	 */
	public boolean isSigningAllowed()
	{
		return signingAllowed;
	}

	/**
	 * check if encryption is allowed.
	 * 
	 * @return true if encryption is allowed
	 */
	public boolean isEncryptionAllowed()
	{
		return EncryptionAllowed;
	}

	@Override
	public String toString()
	{
		StringBuffer result = new StringBuffer();

		result.append("certification allowed = ");
		result.append(certificationAllowed);

		result.append("\nsigning allowed = ");
		result.append(signingAllowed);

		result.append("\nencryption allowed = ");
		result.append(EncryptionAllowed);

		return result.toString();
	}
}
