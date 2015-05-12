package fr.univavignon.m1informatique.rgla.pki.is;

import fr.univavignon.m1informatique.rgla.directory.DirectoryException;
import fr.univavignon.m1informatique.rgla.directory.DistinguishedName;
import fr.univavignon.m1informatique.rgla.isarchi.ISArchiException;
import fr.univavignon.m1informatique.rgla.isarchi.ISComponent;
import fr.univavignon.m1informatique.rgla.pki.ICertificationAuthority;
import fr.univavignon.m1informatique.rgla.pki.PKIException;
import fr.univavignon.m1informatique.rgla.pki.types.Certificate;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateRenewalRequest;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateRevocationRequest;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateRight;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateSigningRequest;
import fr.univavignon.m1informatique.rgla.pki.types.Period;
import fr.univavignon.m1informatique.rgla.security.AbstractKey;

public class CertificationAuthority extends ISComponent implements
		ICertificationAuthority {
	private AbstractKey privateKey;
	private Certificate certificate;

	public CertificationAuthority(DistinguishedName unitName,
			DistinguishedName hashName, Period initialPeriod)
			throws DirectoryException, PKIException, ISArchiException {
		super(unitName, ISComponent.componentFromName(unitName).getType(),hashName.getName());
		
	}

	public boolean isCertificateValid(Certificate certificate)
			throws PKIException {
		return false;

	}

	public Certificate signCertificate(
			CertificateSigningRequest certificateSigningRequest)
			throws PKIException {
		return null;

	}

	public void revokeCertificate(
			CertificateRevocationRequest certificateRevocationRequest)
			throws PKIException {

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

	@Override
	public fr.univavignon.m1informatique.rgla.pki.types.Certificate renewCertificate(
			CertificateRenewalRequest certificateRenewalRequest)
			throws PKIException {
		// TODO Auto-generated method stub
		return null;
	}

}
