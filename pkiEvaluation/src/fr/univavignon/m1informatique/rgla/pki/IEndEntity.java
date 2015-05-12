package fr.univavignon.m1informatique.rgla.pki;

import fr.univavignon.m1informatique.rgla.directory.DistinguishedName;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateRight;
import fr.univavignon.m1informatique.rgla.pki.types.Period;

public interface IEndEntity {
	void createCertificate(Period period, CertificateRight certificateRight,
			DistinguishedName hashName) throws PKIException;

	void revokeCertificate() throws PKIException;

	void renewCertificate(long expire) throws PKIException;
}
