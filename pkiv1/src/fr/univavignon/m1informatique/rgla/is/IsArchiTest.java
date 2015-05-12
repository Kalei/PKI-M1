package fr.univavignon.m1informatique.rgla.is;

import fr.univavignon.m1informatique.rgla.directory.DirectoryException;
import fr.univavignon.m1informatique.rgla.directory.DistinguishedNamedObjectServer;
import fr.univavignon.m1informatique.rgla.directory.local.LocalDistinguishedNameObjectServer;
import fr.univavignon.m1informatique.rgla.isarchi.ISArchiException;
import fr.univavignon.m1informatique.rgla.isarchi.ISComponent;
import fr.univavignon.m1informatique.rgla.isarchi.ISComponentType;
import fr.univavignon.m1informatique.rgla.isarchi.OrganizationalUnit;

public class IsArchiTest {

	/**
	 * @param args
	 * @throws ISArchiException 
	 */
	public static void main(String[] args) throws ISArchiException, DirectoryException{
		DistinguishedNamedObjectServer.initialization(new LocalDistinguishedNameObjectServer());
		OrganizationalUnit ArmeMexicaine=new OrganizationalUnit("ArmeMexicaine");
		OrganizationalUnit unite=new OrganizationalUnit(ArmeMexicaine.getDN(),"unite");
		ISComponent directeur = null;
		
		try{
			directeur=new ISComponent(unite.getDN(), ISComponentType.HUMAN, "directeur");
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
		catch(Exception e){}	
			
		System.out.println("lancement du programme");
		//System.out.println(medecin1.getDN().getName());
		if(directeur!=null){
			OrganizationalUnit.displayCollaboratorDistinguishedNames(directeur.getDN());
		}
	
	}
}
