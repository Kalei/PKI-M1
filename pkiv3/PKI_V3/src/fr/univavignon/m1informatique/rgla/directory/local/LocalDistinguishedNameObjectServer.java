package fr.univavignon.m1informatique.rgla.directory.local;

import java.util.ArrayList;
import java.util.List;

import fr.univavignon.m1informatique.rgla.directory.*;

public class LocalDistinguishedNameObjectServer extends DistinguishedNamedObjectServer{
	private List<DistinguishedNamedObject> dnoList;
	
	public LocalDistinguishedNameObjectServer(){
		dnoList = new ArrayList<DistinguishedNamedObject>();
	}
	
	
	public void addDno(DistinguishedNamedObject dno){
		dnoList.add(dno);
	}
	
	public boolean existDno(DistinguishedName dn){
		for(int i=0;i<dnoList.size();i++){
			if(dn.getName().equals(dnoList.get(i).getDN().getName())){
				return true;
			}
		}
		return false;
	}
	
	public DistinguishedNamedObject getDno(String name){
		for(int i=0;i<dnoList.size();i++){
			if(name.equals(dnoList.get(i).getDN().getName())){
				return dnoList.get(i);
			}
		}
		return null;
	}
}
