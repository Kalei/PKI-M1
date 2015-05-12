package fr.univavignon.m1informatique.rgla.pki;

import fr.univavignon.m1informatique.rgla.pki.types.Certificate;

public interface IUser {
	boolean isCertificateValid(Certificate certificate)
    throws PKIException;
}
