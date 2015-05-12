package fr.univavignon.m1informatique.rgla.pki;

import fr.univavignon.m1informatique.rgla.pki.types.Certificate;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateRenewalRequest;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateRevocationRequest;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateSigningRequest;

public interface IValidationAuthority {
	boolean	isCertificateValid(Certificate certificate) throws PKIException;
}
