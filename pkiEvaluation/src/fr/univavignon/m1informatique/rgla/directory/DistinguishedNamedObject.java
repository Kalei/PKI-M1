/**
 * 
 */
package fr.univavignon.m1informatique.rgla.directory;

public class DistinguishedNamedObject {

	private DistinguishedName dn;

	/**
	 * 
	 */
	public DistinguishedNamedObject(String name) {
		try {
			dn = new DistinguishedName(name);
		} catch (DirectoryException e) {
			e.printStackTrace();
		}

		DistinguishedNamedObjectServer.staticAddDno(this);
	}

	public DistinguishedName getDN() {
		return dn;
	}

}
