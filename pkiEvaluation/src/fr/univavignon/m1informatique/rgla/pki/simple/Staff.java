package fr.univavignon.m1informatique.rgla.pki.simple;

import java.util.ArrayList;
import java.util.List;
import fr.univavignon.m1informatique.rgla.directory.DirectoryException;
import fr.univavignon.m1informatique.rgla.directory.DistinguishedName;
import fr.univavignon.m1informatique.rgla.isarchi.ISArchiException;
import fr.univavignon.m1informatique.rgla.isarchi.ISComponentType;
import fr.univavignon.m1informatique.rgla.pki.PKIException;
import fr.univavignon.m1informatique.rgla.pki.is.SecureComponent;
import fr.univavignon.m1informatique.rgla.pki.types.Period;
import fr.univavignon.m1informatique.rgla.security.SecurityException;
import fr.univavignon.m1informatique.rgla.security.SignedEncryptedObject;
import fr.univavignon.m1informatique.rgla.security.SignedObject;

public class Staff extends SecureComponent {

	private String login;
	private String mdp;
	private List<SignedEncryptedObject> bulletins;

	public Staff(DistinguishedName unitName, ISComponentType type,
			String simpleName, DistinguishedName hashName, Period initialPeriod)
			throws DirectoryException, ISArchiException, PKIException {
		super(unitName, type, simpleName, hashName, initialPeriod);
		bulletins = new ArrayList<SignedEncryptedObject>();
	}

	public String toString() {
		return login;
	}

	public void initLogInfo(String login, String mdp) {
		this.login = login;
		this.mdp = mdp;
	}
	
	public void read(){
		if(!bulletins.isEmpty()){
			for(SignedEncryptedObject object : bulletins){
				try {
					read(object);
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}		
		}
		else{
			System.out.println("Vous n'avez aucun buletin de salaire.");
		}
	}

	public void read(SignedEncryptedObject message) throws SecurityException {
		if(!bulletins.isEmpty())
		{
			SignedObject sMessage = (SignedObject) message
					.getObject(getPrivateKey());
			String messageToDisplay = (String) sMessage.getObject();
			System.out.println(messageToDisplay);
		}
		else{
			System.out.println("Vous n'avez aucun nouveau message.");
		}
	}

	public boolean auth(String login, String mdp) {
		if (this.login.equals(login) && this.mdp.equals(mdp)) {
			System.out.println(this.login+" identifiants ok....");
			return true;
		}

		return false;
	}
	
	public void addBulletin(SignedEncryptedObject bulletin) {
		this.bulletins.add(bulletin);
	}
}
