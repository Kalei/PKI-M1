package fr.univavignon.m1informatique.rgla.pki.is;

import fr.univavignon.m1informatique.rgla.directory.DirectoryException;
import fr.univavignon.m1informatique.rgla.directory.DistinguishedName;
import fr.univavignon.m1informatique.rgla.isarchi.ISArchiException;
import fr.univavignon.m1informatique.rgla.isarchi.ISComponent;
import fr.univavignon.m1informatique.rgla.isarchi.ISComponentType;
import fr.univavignon.m1informatique.rgla.pki.ISecureEntity;
import fr.univavignon.m1informatique.rgla.pki.PKIException;
import fr.univavignon.m1informatique.rgla.pki.types.Certificate;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateRight;
import fr.univavignon.m1informatique.rgla.pki.types.Period;
import fr.univavignon.m1informatique.rgla.security.AbstractKey;

public class SecureComponent extends ISComponent implements ISecureEntity {
	private AbstractKey privateKey;
	private Certificate certificate;

	public SecureComponent(DistinguishedName unitName, ISComponentType type,
			java.lang.String simpleName, DistinguishedName hashName,
			Period initialPeriod) throws DirectoryException, ISArchiException,
			PKIException {
		super(unitName,type,simpleName);

	}

	public boolean isCertificateValid(Certificate certificate)
			throws PKIException {
		return false;

	}

	protected AbstractKey getPrivateKey() {
		return privateKey;
	}

	public void revokeCertificate() throws PKIException {

	}

	public Certificate getCertificate() {
		return certificate;
	}

	public void createCertificate(Period period,
			CertificateRight certificateRight, DistinguishedName hashName)
			throws PKIException {

	}

	public void renewCertificate(long expire) throws PKIException {

	}
}
