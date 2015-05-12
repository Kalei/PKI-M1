package fr.univavignon.m1informatique.rgla.pki.is;

import fr.univavignon.m1informatique.rgla.directory.DirectoryException;
import fr.univavignon.m1informatique.rgla.directory.DistinguishedName;
import fr.univavignon.m1informatique.rgla.isarchi.ISArchiException;
import fr.univavignon.m1informatique.rgla.isarchi.ISComponent;
import fr.univavignon.m1informatique.rgla.isarchi.ISComponentType;
import fr.univavignon.m1informatique.rgla.pki.IValidationAuthority;
import fr.univavignon.m1informatique.rgla.pki.PKIException;
import fr.univavignon.m1informatique.rgla.pki.types.Certificate;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateRenewalRequest;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateRevocationRequest;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateSigningRequest;

public class ValidationAuthority extends ISComponent implements IValidationAuthority {
	public ValidationAuthority(DistinguishedName unitName,
			ISComponentType componentType) throws DirectoryException,
			PKIException, ISArchiException {
		//TODO	
	super(unitName,componentType,"test");
	}

	public boolean isCertificateValid(Certificate certificate)
			throws PKIException {

		return false;

	}

	@Override
	public Certificate signCertificate(
			CertificateSigningRequest certificateSigningRequest)
			throws PKIException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Certificate renewCertificate(
			CertificateRenewalRequest certificateRenewalRequest)
			throws PKIException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void revokeCertificate(
			CertificateRevocationRequest certificateRevocationRequest)
			throws PKIException {
		// TODO Auto-generated method stub
		
	}
}
