package fr.univavignon.m1informatique.rgla.pki;

import fr.univavignon.m1informatique.rgla.pki.types.Certificate;

public interface ICertificationAuthority extends IValidationAuthority, IRegistrationAuthority{
	boolean isCertificateValid(Certificate certificate) throws PKIException;
}
