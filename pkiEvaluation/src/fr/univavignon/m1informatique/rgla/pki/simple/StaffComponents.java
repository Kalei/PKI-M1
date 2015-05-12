package fr.univavignon.m1informatique.rgla.pki.simple;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import fr.univavignon.m1informatique.rgla.directory.DirectoryException;
import fr.univavignon.m1informatique.rgla.directory.DistinguishedNamedObjectServer;
import fr.univavignon.m1informatique.rgla.directory.local.LocalDistinguishedNameObjectServer;
import fr.univavignon.m1informatique.rgla.isarchi.ISArchiException;
import fr.univavignon.m1informatique.rgla.isarchi.ISComponentType;
import fr.univavignon.m1informatique.rgla.isarchi.OrganizationalUnit;
import fr.univavignon.m1informatique.rgla.pki.PKIException;
import fr.univavignon.m1informatique.rgla.pki.is.AuthorityHelper;
import fr.univavignon.m1informatique.rgla.pki.is.RegistrationAuthority;
import fr.univavignon.m1informatique.rgla.pki.is.ValidationAuthority;
import fr.univavignon.m1informatique.rgla.pki.types.CertificateRight;
import fr.univavignon.m1informatique.rgla.pki.types.Period;
import fr.univavignon.m1informatique.rgla.security.IHash;
import fr.univavignon.m1informatique.rgla.security.SecurityException;
import fr.univavignon.m1informatique.rgla.security.SignedEncryptedObject;
import fr.univavignon.m1informatique.rgla.security.SignedObject;
import fr.univavignon.m1informatique.rgla.security.dummyhash.Hash;
import java.util.Collection;

public class StaffComponents {

	private Map<String, Staff> connectedLogin = new HashMap<String, Staff>();
	private List<Staff> staffList = new ArrayList<Staff>();
	private OrganizationalUnit entreprise;
	private IHash hashName;
	private OrganizationalUnit factory;

	public StaffComponents(String orgaName) throws DirectoryException,
			ISArchiException, PKIException {

		DistinguishedNamedObjectServer
				.initialization(new LocalDistinguishedNameObjectServer());
		entreprise = new OrganizationalUnit(orgaName);
		factory = new OrganizationalUnit(entreprise.getDN(), "Staff");
		hashName = Hash.buildHash();
		Period initialPeriod=new Period(
				System.currentTimeMillis(),
				System.currentTimeMillis() * 10);
		AuthorityHelper.createAuthoritiesForUnit(factory.getDN(), hashName.getDistinguishedName(), initialPeriod);

		try {
			Staff director = new Staff(this.factory.getDN(),
					ISComponentType.HUMAN, "director",
					this.hashName.getDistinguishedName(), new Period(
							System.currentTimeMillis(),
							System.currentTimeMillis() * 5));
			director.createCertificate(director.getInitialPeriod(),
					new CertificateRight(false, true, true),
					hashName.getDistinguishedName());
			staffList.add(director);
			initAuth("director");
		} catch (DirectoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ISArchiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PKIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readBuletin() {
		Staff connectedStaff = null;
		Scanner sc = new Scanner(System.in);
		String choix;

		while (connectedStaff == null) {
			connectedStaff = Connect();
		}

		if (connectedStaff != null) {
			while (connectedLogin.get(connectedStaff.toString()) != null) {
				if (connectedStaff.getSimpleName() == "director") {
					System.out.print("\n");
					System.out.println("Envoyer un bulletin de paie [1]");
					System.out.println("Deconnexion [0]");
					choix = sc.nextLine();
					if (choix.equals("1")) {
						sendPayCheck(connectedStaff);
					} else {
						disconectStaff(connectedStaff.toString());
					}
				} else {
					System.out.print("\n");
					System.out.println("Consulter bulletin de paie [1]");
					System.out.println("Deconnexion [0]");
					choix = sc.nextLine();
					if (choix.equals("1")) {
						connectedStaff.read();
					} else {
						disconectStaff(connectedStaff.toString());
					}
				}
			}
		}

		System.out.println("\nS'identifier sous un autre pseudo [1]");
		System.out.println("Quitter [0]");
		choix = sc.nextLine();

		if (choix.equals("1")) {
			readBuletin();
		} else {
			System.out.println("Adieu !");
			System.exit(1);
		}
	}

	private void disconectStaff(String login) {
		connectedLogin.remove(login);
		System.out.println(login + " a été déconnecté.");
	}

	public Staff Connect() {
		System.out.print("\n");
		System.out.println("_. Indentification ._");
		System.out.print("Login : ");
		Scanner sc = new Scanner(System.in);
		String login = sc.nextLine();
		System.out.print("mot de passe : ");
		String mdp = sc.nextLine();
		System.out.print("\n");

		if (logStaff(login, mdp)) {
			return connectedLogin.get(login);
		}
		return null;
	}

	public boolean logStaff(String login, String mdp) {
		for (Staff staff : staffList) {
			if (staff.auth(login, mdp)) {
				connectedLogin.put(login, staff);
				return true;
			}
		}
		System.out.println("Identifiant incorrect.");
		return false;
	}

	public Staff findStaff(String name) {
		if (!staffList.isEmpty()) {
			for (Staff staff : staffList) {
				if (staff.toString() != null && staff.toString().equals(name)) {
					return staff;
				}
			}
		}
		return null;
	}

	public void sendPayCheck(Staff directorStaff) {
		String sendTo = null;
		Scanner sc = new Scanner(System.in);

		System.out
				.println("\n------------ Saisie bulletin de paie ------------");
		while (findStaff(sendTo) == null) {
			System.out.println("Bultin en destination de : ");

			sendTo = sc.nextLine();
			if (findStaff(sendTo) == null) {
				System.out.println("Destinataire non trouvé...");
			}
		}

		int montant = -999;

		while (montant <= 0) {
			try {
				System.out.println("Saisir montant salaire : ");
				montant = Integer.parseInt(sc.nextLine());
				Date date = new Date(System.currentTimeMillis());
				SimpleDateFormat sdf = new SimpleDateFormat(
						"dd/MM/yyyy hh:mm:ss");
				String bulletinMessage = "\n-------------------------------------\nDate d'envoi : "
						+ sdf.format(date)
						+ "\n\nBonjour,\n\n\tLe montant de votre paie est de | "
						+ montant
						+ "$ |\n\n Cordialement le directeur général.\n-------------------------------------\n";
				SignedObject signedbulletin = new SignedObject(bulletinMessage,
						directorStaff.getPrivateKey(),
						hashName.getDistinguishedName());
				Staff destStaff = findStaff(sendTo);
				SignedEncryptedObject signedEncryptedbulletin = new SignedEncryptedObject(
						signedbulletin, destStaff.getPublicKey(),
						directorStaff.getPrivateKey(),
						hashName.getDistinguishedName());
				destStaff.addBulletin(signedEncryptedbulletin);
			} catch (NumberFormatException e) {
				System.out.println("Erreur, nombre invalide.");
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void addStaff(String simpleName) {
		try {
			Staff enr = new Staff(this.factory.getDN(), ISComponentType.HUMAN,
					simpleName, this.hashName.getDistinguishedName(),
					new Period(System.currentTimeMillis(),
							System.currentTimeMillis() * 5));
			enr.createCertificate(enr.getInitialPeriod(), new CertificateRight(false,
					false, false), hashName.getDistinguishedName());
			staffList.add(enr);
			initAuth(simpleName);
		} catch (DirectoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ISArchiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PKIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initAuth(String simpleName) {
		String login = null;
		Scanner sc = new Scanner(System.in);

		while (findStaff(login) == null) {
			System.out.println("Nouvel enregistrement ( " + simpleName
					+ " ) veuillez préciser ses identifiants.");
			System.out.print("Login : ");
			login = sc.nextLine();
			System.out.print("mot de passe : ");
			String mdp = sc.nextLine();

			if (findStaff(login) == null) {
				for (Staff staff : staffList) {
					if (staff.getSimpleName().equals(simpleName)) {
						staff.initLogInfo(login, mdp);
						return;
					}
				}
			} else if (findStaff(login) != null) {
				System.out.println("Ce login n'est pas unique.");
				login = null;
			}
		}

		System.out
				.println("Erreur, unité non crée. Impossible d'assigner les identifiants.");
	}

	/**
	 * @uml.property  name="staffList"
	 * @uml.associationEnd  multiplicity="(1 -1)" aggregation="composite" inverse="staffComponents:fr.univavignon.m1informatique.rgla.pki.simple.Staff"
	 */
	private Collection list = new java.util.ArrayList();

	/**
	 * Getter of the property <tt>staffList</tt>
	 * @return  Returns the list.
	 * @uml.property  name="staffList"
	 */
	public Collection getStaffList() {
		return list;
	}

	/**
	 * Setter of the property <tt>staffList</tt>
	 * @param staffList  The list to set.
	 * @uml.property  name="staffList"
	 */
	public void setStaffList(Collection staffList) {
		list = staffList;
	}

}