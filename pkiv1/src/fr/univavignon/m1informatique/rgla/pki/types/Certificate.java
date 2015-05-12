package fr.univavignon.m1informatique.rgla.pki.types;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import fr.univavignon.m1informatique.rgla.pki.PKIException;
import fr.univavignon.m1informatique.rgla.security.SecurityException;
import fr.univavignon.m1informatique.rgla.pki.is.SecureComponent;

/**
 * La classe <code>Certificate</code> implémente la notion de certificat.
 * <p>
 * Un certificat est une liste chainée d'élémente de certificat. Chaque élément signant l'élément inférieur.
 * 
 * @author mb
 */
public class Certificate implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	
	private List<CertificateElement> certificateElements;

	/**
	 * build a certificate from another
	 * 
	 * @param certificate the certificate copied
	 */
	public Certificate(Certificate certificate)
	{
		this(certificate == null ? null : certificate.certificateElements);
	}

	/**
	 * build a certificate with a list of certificate elements
	 * 
	 * @param listOfCertificateElements the list of certificate elements
	 */
	public Certificate(List<CertificateElement> listOfCertificateElements)
	{
		this.certificateElements = listOfCertificateElements != null
				? new LinkedList<CertificateElement>(listOfCertificateElements)
				: new LinkedList<CertificateElement>();
	}

	/**
	 * gives the size of certificate elements list.
	 * 
	 * @return the size of certificate elements list
	 */
	public int size()
	{
		return certificateElements.size();
	}

	/**
	 * verify certificate : test if data are valid and certificate is authentic
	 * 
	 * @throws PKIException if not valid or not authentic
	 * @throws SecurityException if certificate cannot be verified
	 */
	public void verify() throws PKIException, SecurityException
	{
		if (!isAuthentic())
			throw new PKIException("certificates chain " + this + "is not authentic");

		if (!isValid())
			throw new PKIException("certificates chain " + this + "is not valid");
	}

	private boolean isAuthentic() throws SecurityException
	{
		boolean authentic = true;

		CertificateElement certificateUsedToSign = null;

		for (CertificateElement certificateElement : certificateElements)
		{
			if (certificateUsedToSign == null)
				certificateUsedToSign = certificateElement;
			else
			{
				if (!certificateUsedToSign.verify(certificateElement))
				{
					authentic = false;
					break;
				}
				certificateUsedToSign = certificateElement;
			}
		}

		return authentic;
	}

	private boolean isValid()
	{
		boolean valid = true;

		for (CertificateElement certificateElement : certificateElements)
		{
			if (!certificateElement.isValid())
			{
				valid = false;
				break;
			}
		}

		return valid;
	}

	/**
	 * 
	 */
	@Override
	public String toString()
	{
		StringBuffer result = new StringBuffer();

		for (CertificateElement certificateElement : certificateElements)
		{
			result.append("\n\n------------------------------------------------------\n");
			result.append(certificateElement);
		}

		return result.toString();
	}

	/**
	 * add a certificate element
	 * 
	 * @param certificateElement to add
	 */
	public void addCertificateElement(CertificateElement certificateElement)
	{
		certificateElements.add(certificateElement);
	}

	/**
	 * get the last certificate element.
	 * 
	 * @return the last certificate element
	 * @throws PKIException if certificate empty
	 */
	public CertificateElement getLastCertificateElement() throws PKIException
	{
		if (certificateElements.size() < 1)
			throw new PKIException("bad formed certificates chain " + this);

		return certificateElements.get(certificateElements.size() - 1);
	}

	/**
	 * get the certificate of the authority which sign the last certificate element
	 * 
	 * @return the certificate of the authority which sign the last certificate element
	 * @throws PKIException if the certificate is empty
	 */
	public CertificateElement getLastAuthorityCertificateElement() throws PKIException
	{
		CertificateElement lastAuthorityCertificate;

		if (certificateElements.size() == 1)

			lastAuthorityCertificate = certificateElements.get(certificateElements.size() - 1);

		else if (certificateElements.size() > 1)
			lastAuthorityCertificate = certificateElements.get(certificateElements.size() - 2);

		else
			throw new PKIException("bad formed certificates chain " + this);

		return lastAuthorityCertificate;
	}

	/**
	 * reduce the certificate in removing the last certificate element
	 * 
	 * @return reduced certificate
	 */
	public Certificate reduceChain()
	{
		return new Certificate(certificateElements.subList(0, certificateElements.size() - 1));
	}

	/**
	 * @uml.property  name="secureComponent"
	 * @uml.associationEnd  inverse="certificate1:fr.univavignon.m1informatique.rgla.pki.is.SecureComponent"
	 */
	private SecureComponent secureComponent;

	/**
	 * Getter of the property <tt>secureComponent</tt>
	 * @return  Returns the secureComponent.
	 * @uml.property  name="secureComponent"
	 */
	public SecureComponent getSecureComponent() {
		return secureComponent;
	}

	/**
	 * Setter of the property <tt>secureComponent</tt>
	 * @param secureComponent  The secureComponent to set.
	 * @uml.property  name="secureComponent"
	 */
	public void setSecureComponent(SecureComponent secureComponent) {
		this.secureComponent = secureComponent;
	}
}
