package fr.univavignon.m1informatique.rgla.directory;

import fr.univavignon.m1informatique.rgla.directory.DirectoryException;

public class DistinguishedName {


	//private DistinguishedNamedObject dno;
	private String name;
	public String getName(){
		return name;
	}
	
	public DistinguishedNamedObject getObject(){
		return DistinguishedNamedObjectServer.getDistinguishedNamedObject(name);
		
	}
	
	public DistinguishedName(String name) throws DirectoryException {
		this.name=name;
		if(DistinguishedNamedObjectServer.staticExistDno(this)){
			throw new DirectoryException(name);
		}
	}
}
