package fr.univavignon.m1informatique.rgla.pki.types;

import java.io.Serializable;

import fr.univavignon.m1informatique.rgla.pki.types.CertificateSigningRequestData;

/**
 * la classe <code>CertificateElementData</code> contient les données d'un élément de certificat.
 * <p>
 * Ces données correspondent à celle de la requête de signature du certificat {@link CertificateRenewalRequestData} plus le serialNumber qui est ajouté par
 * l'autorité de certification.
 * 
 * @author mb
 */
public class CertificateElementData implements Serializable
{
	private static final long serialVersionUID = 1L;

	private long serialNumber;

	/**
	 * @uml.property  name="certificateSigningRequestData"
	 * @uml.associationEnd  inverse="certificateElementData:fr.univavignon.m1informatique.rgla.pki.types.CertificateSigningRequestData"
	 */
	private CertificateSigningRequestData certificateSigningRequestData;

	/**
	 * 
	 * @param serialNumber added by certification authority
	 * @param certificateSigningRequestData copied from the request
	 */
	public CertificateElementData(long serialNumber, CertificateSigningRequestData certificateSigningRequestData)
	{
		this.serialNumber = serialNumber;
		this.certificateSigningRequestData = certificateSigningRequestData;
	}

	long getSerialNumber()
	{
		return serialNumber;
	}

	CertificateSigningRequestData getCertificateSigningRequestData()
	{
		return certificateSigningRequestData;
	}

	@Override
	public String toString()
	{
		StringBuffer result = new StringBuffer();

		result.append("Certificate :\n");

		result.append("Serial Number : ");
		result.append(serialNumber);

		result.append("\n");

		result.append(getCertificateSigningRequestData().toString());

		return result.toString();
	}

	
}
