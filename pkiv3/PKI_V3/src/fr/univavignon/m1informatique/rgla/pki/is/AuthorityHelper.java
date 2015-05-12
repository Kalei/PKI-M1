package fr.univavignon.m1informatique.rgla.pki.is;

import fr.univavignon.m1informatique.rgla.directory.DirectoryException;
import fr.univavignon.m1informatique.rgla.directory.DistinguishedName;
import fr.univavignon.m1informatique.rgla.isarchi.ISArchiException;
import fr.univavignon.m1informatique.rgla.pki.PKIException;
import fr.univavignon.m1informatique.rgla.pki.types.Period;
import fr.univavignon.m1informatique.rgla.isarchi.ISComponentType;

public class AuthorityHelper {
	
	
	
	public static void createAuthoritiesForUnit(DistinguishedName unitName,
			DistinguishedName hashName, Period initialPeriod)
			throws DirectoryException, PKIException, ISArchiException {
		CertificationAuthority certificationAuthority=new CertificationAuthority(unitName, hashName, initialPeriod);
		
		RegistrationAuthority humanRegistrationAuthority =new RegistrationAuthority(unitName, hashName, ISComponentType.HUMAN, initialPeriod);
		RegistrationAuthority hardwareRegistrationAuthority =new RegistrationAuthority(unitName, hashName, ISComponentType.HARDWARE, initialPeriod);
		RegistrationAuthority softwareRegistrationAuthority =new RegistrationAuthority(unitName, hashName, ISComponentType.SOFTWARE, initialPeriod);
		/*
		ValidationAuthority humanValidationAuthority=new ValidationAuthority(unitName, ISComponentType.HUMAN);
		ValidationAuthority hardwareValidationAuthority=new ValidationAuthority(unitName, ISComponentType.HARDWARE);
		ValidationAuthority softwareValidationAuthority=new ValidationAuthority(unitName, ISComponentType.SOFTWARE);
		*/
	}
}
