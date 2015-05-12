package fr.univavignon.m1informatique.rgla.pki.is;

import fr.univavignon.m1informatique.rgla.directory.DirectoryException;
import fr.univavignon.m1informatique.rgla.directory.DistinguishedName;
import fr.univavignon.m1informatique.rgla.directory.DistinguishedNamedObjectServer;
import fr.univavignon.m1informatique.rgla.isarchi.ISArchiException;
import fr.univavignon.m1informatique.rgla.isarchi.ISComponent;
import fr.univavignon.m1informatique.rgla.isarchi.ISComponentType;
import fr.univavignon.m1informatique.rgla.pki.IRegistrationAuthority;
import fr.univavignon.m1informatique.rgla.pki.PKIException;
import fr.univavignon.m1informatique.rgla.pki.types.Certificate;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateElement;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateElementData;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateRenewalRequest;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateRenewalRequestData;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateRevocationRequest;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateRight;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateSigningRequest;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateSigningRequestData;
import fr.univavignon.m1informatique.rgla.pki.types.Period;
import fr.univavignon.m1informatique.rgla.security.AbstractKey;
import fr.univavignon.m1informatique.rgla.security.SecurityException;
import fr.univavignon.m1informatique.rgla.security.dummyasymetric.AsymetricKey;

public class RegistrationAuthority extends ISComponent implements
		IRegistrationAuthority {

	protected Certificate certificate;
	protected CertificationAuthority certificationAuthority;
	protected DistinguishedName hashName;
	protected Period initialPeriod;
	protected AsymetricKey registrationKey;
	protected long serialNumber;

	public RegistrationAuthority(DistinguishedName unitName,
			DistinguishedName hashName, ISComponentType componentType,
			Period initialPeriod) throws DirectoryException, PKIException,
			ISArchiException {
		super(unitName, componentType, "RA" + componentType.toString());
		this.hashName = hashName;
		this.initialPeriod = initialPeriod;
		registrationKey = new AsymetricKey();
		certificationAuthority = (CertificationAuthority) DistinguishedNamedObjectServer
				.getDistinguishedNamedObject(unitName.getName() + "/" + "CA");
		createCertificate(initialPeriod,
				new CertificateRight(true, true, true), hashName);
	}

	public Certificate signCertificate(
			CertificateSigningRequest certificateSigningRequest)
			throws PKIException {
		Certificate delivryCertificate = certificationAuthority
				.signCertificate(certificateSigningRequest);

		CertificateSigningRequestData certificateSigningRequestData = certificateSigningRequest
				.getObject();
		CertificateElementData certificateElementData = new CertificateElementData(
				serialNumber++, certificateSigningRequestData);
		Certificate certificateOfSon = new Certificate(delivryCertificate);
		try {
			CertificateElement certificateElement = new CertificateElement(
					certificateElementData, getPrivateKey(), hashName);
			certificateOfSon.addCertificateElement(certificateElement);
			return certificateOfSon;
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			throw (new PKIException(e));
		}

	}

	public void revokeCertificate(
			CertificateRevocationRequest certificateRevocationRequest)
			throws PKIException {

	}

	public Certificate renewCertificate(
			CertificateRenewalRequest certificateRenewalRequest)
			throws PKIException {
		try {
			certificateRenewalRequest.isAuthentic(certificateRenewalRequest
					.getCertificate());
			return certificationAuthority
					.renewCertificate(certificateRenewalRequest);
		} catch (SecurityException e) {
			throw (new PKIException(e));
		}

	}

	public void renewCertificate(long expire) throws PKIException {
		CertificateRenewalRequestData certificateRenewalRequestData = new CertificateRenewalRequestData(
				expire, certificate);
		try {
			CertificateRenewalRequest certificateRenewalRequest = new CertificateRenewalRequest(
					certificateRenewalRequestData, getPrivateKey(), certificate);
			renewCertificate(certificateRenewalRequest);
		} catch (SecurityException e) {
			throw (new PKIException(e));
		}
	}

	protected AbstractKey getPrivateKey() {
		return registrationKey.getPrivateKey();
	}

	public void revokeCertificate() throws PKIException {
	}

	public Certificate getCertificate() {
		return certificate;
	}

	public void createCertificate(Period period,
			CertificateRight certificateRight, DistinguishedName hashName)
			throws PKIException {
		CertificateSigningRequestData certificateSigningRequestData = new CertificateSigningRequestData(
				this.getDN(), registrationKey.getPublicKey(), hashName, period,
				certificateRight);
		try {
			CertificateSigningRequest certificateSigningRequest = new CertificateSigningRequest(
					certificateSigningRequestData, getPrivateKey(), hashName);
			this.certificate = new Certificate(certificationAuthority
					.signCertificate(certificateSigningRequest));

		} catch (SecurityException e) {
			throw new PKIException(e);
		}
	}

}
