package fr.univavignon.m1informatique.rgla.pki.is;

import fr.univavignon.m1informatique.rgla.directory.DirectoryException;
import fr.univavignon.m1informatique.rgla.directory.DistinguishedName;
import fr.univavignon.m1informatique.rgla.isarchi.ISArchiException;
import fr.univavignon.m1informatique.rgla.isarchi.ISComponentType;
import fr.univavignon.m1informatique.rgla.pki.PKIException;
import fr.univavignon.m1informatique.rgla.pki.types.Certificate;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateRenewalRequest;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateRevocationRequest;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateRight;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateSigningRequest;
import fr.univavignon.m1informatique.rgla.pki.types.Period;
import fr.univavignon.m1informatique.rgla.security.AbstractKey;

public class RegistrationAuthority {

	private AbstractKey privateKey;
	private Certificate certificate;

	public RegistrationAuthority(DistinguishedName unitName,
			DistinguishedName hashName, ISComponentType componentType,
			Period initialPeriod) throws DirectoryException, PKIException,
			ISArchiException {

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

	public Certificate renewCertificate(
			CertificateRenewalRequest certificateRenewalRequest)
			throws PKIException {
		return null;

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
