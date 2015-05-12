package fr.univavignon.m1informatique.rgla.is;

import fr.univavignon.m1informatique.rgla.directory.DirectoryException;
import fr.univavignon.m1informatique.rgla.directory.DistinguishedName;
import fr.univavignon.m1informatique.rgla.directory.DistinguishedNamedObjectServer;
import fr.univavignon.m1informatique.rgla.directory.local.LocalDistinguishedNameObjectServer;
import fr.univavignon.m1informatique.rgla.isarchi.ISArchiException;
import fr.univavignon.m1informatique.rgla.isarchi.ISComponent;
import fr.univavignon.m1informatique.rgla.isarchi.ISComponentType;
import fr.univavignon.m1informatique.rgla.isarchi.OrganizationalUnit;
import fr.univavignon.m1informatique.rgla.pki.PKIException;
import fr.univavignon.m1informatique.rgla.pki.is.AuthorityHelper;
import fr.univavignon.m1informatique.rgla.pki.types.Period;
import fr.univavignon.m1informatique.rgla.security.IHash;
import fr.univavignon.m1informatique.rgla.security.dummyhash.Hash;

public class IsArchiTest {

	/**
	 * @param args
	 * @throws ISArchiException 
	 * @throws PKIException 
	 */
	public static void main(String[] args) throws ISArchiException, DirectoryException, PKIException{
		
		
		DistinguishedNamedObjectServer.initialization(new LocalDistinguishedNameObjectServer());
		OrganizationalUnit ArmeMexicaine=new OrganizationalUnit("ArmeMexicaine");
		OrganizationalUnit unite=new OrganizationalUnit(ArmeMexicaine.getDN(),"unite");
		IHash hashName= Hash.buildHash();
		AuthorityHelper.createAuthoritiesForUnit(unite.getDN(), hashName.getDistinguishedName(), new Period(System.currentTimeMillis(), System.currentTimeMillis()*10));
		ISComponent directeur=new ISComponent(unite.getDN(), ISComponentType.HUMAN, "directeur");
		
		try{
			
			@SuppressWarnings("unused")
			ISComponent assistante=new ISComponent(unite.getDN(),ISComponentType.HUMAN, "assitance");
			OrganizationalUnit unitAlpha=new OrganizationalUnit(directeur.getDN(),"unitAlpha");
			OrganizationalUnit unitBeta=new OrganizationalUnit(directeur.getDN(),"unitBeta");
			OrganizationalUnit unitGamma=new OrganizationalUnit(directeur.getDN(),"unitGamme");
			new ISComponent(unitAlpha.getDN(),ISComponentType.HUMAN,"sniper1");
			new ISComponent(unitAlpha.getDN(),ISComponentType.HUMAN,"sniper2");
			new ISComponent(unitAlpha.getDN(),ISComponentType.HUMAN,"sniper3");
			
			new ISComponent(unitBeta.getDN(),ISComponentType.HUMAN,"medecin1");
			new ISComponent(unitBeta.getDN(),ISComponentType.HUMAN,"medecin2");
			new ISComponent(unitBeta.getDN(),ISComponentType.HUMAN,"medecin3");
			
			new ISComponent(unitGamma.getDN(),ISComponentType.HUMAN,"espion1");
			new ISComponent(unitGamma.getDN(),ISComponentType.HUMAN,"espion2");
			new ISComponent(unitGamma.getDN(),ISComponentType.HUMAN,"espion3");
			
		}
		catch(Exception e){
			throw (new ISArchiException(e));
		}	
			
		System.out.println("lancement du programme");
		//System.out.println(medecin1.getDN().getName());
		if(directeur!=null){
			OrganizationalUnit.displayCollaboratorDistinguishedNames(directeur.getDN());
		}
	
	}
	
}
