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
		new CertificationAuthority(unitName, hashName, initialPeriod);
		
		new RegistrationAuthority(unitName, hashName, ISComponentType.HUMAN, initialPeriod);
		new RegistrationAuthority(unitName, hashName, ISComponentType.HARDWARE, initialPeriod);
		new RegistrationAuthority(unitName, hashName, ISComponentType.SOFTWARE, initialPeriod);
		
		new ValidationAuthority(unitName, ISComponentType.HUMAN);
		new ValidationAuthority(unitName, ISComponentType.HARDWARE);
		new ValidationAuthority(unitName, ISComponentType.SOFTWARE);
		
	}
}
