package fr.univavignon.m1informatique.rgla.pki.is;

import fr.univavignon.m1informatique.rgla.directory.DirectoryException;
import fr.univavignon.m1informatique.rgla.directory.DistinguishedName;
import fr.univavignon.m1informatique.rgla.directory.DistinguishedNamedObjectServer;
import fr.univavignon.m1informatique.rgla.isarchi.ISArchiException;
import fr.univavignon.m1informatique.rgla.isarchi.ISComponent;
import fr.univavignon.m1informatique.rgla.isarchi.ISComponentType;
import fr.univavignon.m1informatique.rgla.pki.ISecureEntity;
import fr.univavignon.m1informatique.rgla.pki.PKIException;
import fr.univavignon.m1informatique.rgla.pki.types.Certificate;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateRenewalRequest;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateRenewalRequestData;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateRevocationRequest;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateRevocationRequestData;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateRight;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateSigningRequest;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateSigningRequestData;
import fr.univavignon.m1informatique.rgla.pki.types.Period;
import fr.univavignon.m1informatique.rgla.security.AbstractKey;
import fr.univavignon.m1informatique.rgla.security.SecurityException;
import fr.univavignon.m1informatique.rgla.security.dummyasymetric.AsymetricKey;

public class SecureComponent extends ISComponent implements ISecureEntity {
	protected Certificate certificate;
	protected RegistrationAuthority registrationAuthority;
	protected DistinguishedName hashName;
	private Period initialPeriod;
	protected AsymetricKey compKey;
	protected DistinguishedName unitName;

	public SecureComponent(DistinguishedName unitName, ISComponentType type, String simpleName,
			DistinguishedName hashName, Period initialPeriod) throws DirectoryException, ISArchiException, PKIException {
		super(unitName, type, simpleName);
		compKey=new AsymetricKey();
		registrationAuthority = (RegistrationAuthority) DistinguishedNamedObjectServer
				.getDistinguishedNamedObject(unitName.getName() + "/" + "RA" + type.toString());
		this.unitName = unitName;
		createCertificate(initialPeriod, new CertificateRight(true, true, true), hashName);
		this.initialPeriod=initialPeriod;

	}

	public boolean isCertificateValid(Certificate certificate) throws PKIException {
		ValidationAuthority validationAuthority = (ValidationAuthority) DistinguishedNamedObjectServer
				.getDistinguishedNamedObject(unitName.getName() + "/" + "VA"+getType().toString());
		return validationAuthority.isCertificateValid(certificate);
	}

	public AbstractKey getPrivateKey() {
		return compKey.getPrivateKey();
	}

	public void revokeCertificate() throws PKIException {
		CertificateRevocationRequestData certificateRevocationRequestData = new CertificateRevocationRequestData(
				certificate);
		CertificateRevocationRequest certificateRevocationRequest;
		try {
			certificateRevocationRequest = new CertificateRevocationRequest(certificateRevocationRequestData,
					getPrivateKey(), certificate);
			registrationAuthority.revokeCertificate(certificateRevocationRequest);
		} catch (SecurityException e) {
			throw (new PKIException(e));
		}

	}

	public Certificate getCertificate() {
		return certificate;
	}

	public void createCertificate(Period period, CertificateRight certificateRight, DistinguishedName hashName)
			throws PKIException {
		System.out.println(compKey);
		CertificateSigningRequestData certificateSigningRequestData = new CertificateSigningRequestData(this.getDN(),
				compKey.getPublicKey(), hashName, period, certificateRight);
		try {
			CertificateSigningRequest certificateSigningRequest = new CertificateSigningRequest(
					certificateSigningRequestData, getPrivateKey(), hashName);
			this.certificate = new Certificate(registrationAuthority.signCertificate(certificateSigningRequest));

		} catch (SecurityException e) {
			throw new PKIException(e);
		}
	}

	public void renewCertificate(long expire) throws PKIException {
		CertificateRenewalRequestData certificateRenewalRequestData = new CertificateRenewalRequestData(expire,
				certificate);
		try {
			CertificateRenewalRequest certificateRenewalRequest = new CertificateRenewalRequest(
					certificateRenewalRequestData, getPrivateKey(), certificate);
			registrationAuthority.renewCertificate(certificateRenewalRequest);
		} catch (SecurityException e) {
			throw (new PKIException(e));
		}
	}

	public Period getInitialPeriod() {
		return initialPeriod;
	}

	public void setInitialPeriod(Period initialPeriod) {
		this.initialPeriod = initialPeriod;
	}
	public AbstractKey getPublicKey(){
		return compKey.getPublicKey();
	}
}
