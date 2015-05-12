package fr.univavignon.m1informatique.rgla.pki.is;

import fr.univavignon.m1informatique.rgla.directory.DirectoryException;
import fr.univavignon.m1informatique.rgla.directory.DistinguishedName;
import fr.univavignon.m1informatique.rgla.directory.DistinguishedNamedObjectServer;
import fr.univavignon.m1informatique.rgla.isarchi.ISArchiException;
import fr.univavignon.m1informatique.rgla.isarchi.ISComponent;
import fr.univavignon.m1informatique.rgla.isarchi.ISComponentType;
import fr.univavignon.m1informatique.rgla.pki.IValidationAuthority;
import fr.univavignon.m1informatique.rgla.pki.PKIException;
import fr.univavignon.m1informatique.rgla.pki.types.Certificate;

public class ValidationAuthority extends ISComponent implements IValidationAuthority {
	protected DistinguishedName unitName;

	public ValidationAuthority(DistinguishedName unitName, ISComponentType componentType) throws DirectoryException,
			PKIException, ISArchiException {
		// TODO
		super(unitName, componentType, "VA" + componentType.toString());

	}

	@Override
	public boolean isCertificateValid(Certificate certificate) throws PKIException {
		CertificationAuthority ca = (CertificationAuthority) DistinguishedNamedObjectServer
				.getDistinguishedNamedObject(getUnitName().getName() + "/" + "CA");
		return ca.isCertificateValid(certificate);
	}
}
