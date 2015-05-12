package fr.univavignon.m1informatique.rgla.directory;

public abstract class DistinguishedNamedObjectServer {

	private static DistinguishedNamedObjectServer instance;

	private static DistinguishedNamedObjectServer getInstance() {
		return instance;
	}

	public abstract void addDno(DistinguishedNamedObject dno);

	public abstract boolean existDno(DistinguishedName dn);

	public abstract DistinguishedNamedObject getDno(String name);

	public static DistinguishedNamedObject getDistinguishedNamedObject(String name) {
		return getInstance().getDno(name);
	}
	
	public static boolean staticExistDno(DistinguishedName dn) {
		return getInstance().existDno(dn);
	}
	public static void staticAddDno(DistinguishedNamedObject dno){
		getInstance().addDno(dno);
	}

	public static void initialization(DistinguishedNamedObjectServer server) {
		if (instance == null)
			instance = server;

	}
}
