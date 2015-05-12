package fr.univavignon.m1informatique.rgla.pki.is;

import java.util.ArrayList;
import java.util.List;

import fr.univavignon.m1informatique.rgla.directory.DirectoryException;
import fr.univavignon.m1informatique.rgla.directory.DistinguishedName;
import fr.univavignon.m1informatique.rgla.isarchi.ISArchiException;
import fr.univavignon.m1informatique.rgla.isarchi.ISComponent;
import fr.univavignon.m1informatique.rgla.isarchi.ISComponentType;
import fr.univavignon.m1informatique.rgla.isarchi.OrganizationalUnit;
import fr.univavignon.m1informatique.rgla.pki.ICertificationAuthority;
import fr.univavignon.m1informatique.rgla.pki.PKIException;
import fr.univavignon.m1informatique.rgla.pki.types.Certificate;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateElement;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateElementData;
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

public class CertificationAuthority extends ISComponent implements
		ICertificationAuthority {
	protected Certificate certificate;
	protected DistinguishedName hashName;
	protected Period initialPeriod;
	protected AsymetricKey authorityKey;
	protected long serialNumber;
	protected List<Certificate> certificateRevocationList;

	public CertificationAuthority(DistinguishedName unitName,
			DistinguishedName hashName, Period initialPeriod)
			throws DirectoryException, PKIException, ISArchiException {
		super(unitName, ISComponentType.AUTHORITY, "CA");
		this.hashName = hashName;
		this.initialPeriod = initialPeriod;
		authorityKey = new AsymetricKey();
		createCertificate(initialPeriod,
				new CertificateRight(true, true, true), hashName);
		certificateRevocationList = new ArrayList<Certificate>();
	}

	public boolean isCertificateValid(Certificate certificate)
			throws PKIException {
		return certificate.getLastAuthorityCertificateElement().isValid();
	}

	protected AbstractKey getPrivateKey() {
		return authorityKey.getPrivateKey();
	}

	public Certificate signCertificate(
			CertificateSigningRequest certificateSigningRequest)
			throws PKIException {
		CertificateSigningRequestData certificateSigningRequestData = certificateSigningRequest
				.getObject();
		CertificateElementData certificateElementData = new CertificateElementData(
				serialNumber++, certificateSigningRequestData);
		Certificate certificateOfSon = new Certificate(certificate);
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
		certificateRevocationList.add(certificateRevocationRequest
				.getCertificate());
	}

	public void revokeCertificate() throws PKIException {
		certificateRevocationList.add(certificate);
	}

	public void createCertificate(Period period,
			CertificateRight certificateRight, DistinguishedName hashName)
			throws PKIException {
		CertificateSigningRequestData certificateSigningRequestData = new CertificateSigningRequestData(
				this.getDN(), authorityKey.getPublicKey(), hashName, period,
				certificateRight);
		try {
			CertificateSigningRequest certificateSigningRequest = new CertificateSigningRequest(
					certificateSigningRequestData, getPrivateKey(), hashName);
			if (certificate == null) {
				certificate = new Certificate(
						signCertificate(certificateSigningRequest));
			} else {
				CertificationAuthority myLastAuthority = (CertificationAuthority) certificate
						.getLastAuthorityCertificateElement().getSubjectName()
						.getObject();
				certificate = myLastAuthority
						.signCertificate(certificateSigningRequest);
			}

		} catch (SecurityException e) {
			throw new PKIException(e);
		}
	}

	public void renewCertificate(long expire) throws PKIException {
		CertificateRenewalRequestData certificateRenewalRequestData = new CertificateRenewalRequestData(
				expire, certificate);
		try {
			CertificateRenewalRequest certificateRenewalRequest = new CertificateRenewalRequest(
					certificateRenewalRequestData, getPrivateKey(), certificate);
			CertificationAuthority myAuthority = (CertificationAuthority) certificate
					.getLastAuthorityCertificateElement().getSubjectName()
					.getObject();
			myAuthority.renewCertificate(certificateRenewalRequest);
		} catch (SecurityException e) {
			throw (new PKIException(e));
		}

	}

	@Override
	public Certificate renewCertificate(
			CertificateRenewalRequest certificateRenewalRequest)
			throws PKIException {
		Certificate oldCertificate = certificateRenewalRequest.getCertificate();
		CertificateElement oldCertificateElement = oldCertificate
				.getLastCertificateElement();
		Period newCertificatePeriod = new Period(System.currentTimeMillis(),
				certificateRenewalRequest.getExpire());
		CertificateSigningRequestData certificateSigningRequestData = new CertificateSigningRequestData(
				oldCertificateElement.getSubjectName(),
				oldCertificateElement.getPublicKey(),
				oldCertificateElement.getHashName(), newCertificatePeriod,
				oldCertificateElement.getCertificateRight());
		CertificateElementData renewCertificateElementData = new CertificateElementData(
				oldCertificateElement.getSerialNumber(),
				certificateSigningRequestData);
		try {
			CertificateElement renewCertificateElement = new CertificateElement(
					renewCertificateElementData, getPrivateKey(), hashName);
			Certificate renewedCertificate = oldCertificate.reduceChain();
			renewedCertificate.addCertificateElement(renewCertificateElement);
			return renewedCertificate;
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			throw (new PKIException(e));
		}
	}

}
