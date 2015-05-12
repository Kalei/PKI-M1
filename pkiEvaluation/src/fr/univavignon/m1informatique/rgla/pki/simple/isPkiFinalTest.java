package fr.univavignon.m1informatique.rgla.pki.simple;

import fr.univavignon.m1informatique.rgla.directory.DirectoryException;
import fr.univavignon.m1informatique.rgla.isarchi.ISArchiException;
import fr.univavignon.m1informatique.rgla.pki.PKIException;
import fr.univavignon.m1informatique.rgla.security.SecurityException;

public class isPkiFinalTest {
	public static void main(String[] args) throws PKIException,
			SecurityException, InterruptedException {
		try {
			StaffComponents myFactory = new StaffComponents("MyFactory");
			myFactory.addStaff("toto");
			myFactory.addStaff("tata");
			myFactory.addStaff("titi");
			myFactory.readBuletin();
		} catch (DirectoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ISArchiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
