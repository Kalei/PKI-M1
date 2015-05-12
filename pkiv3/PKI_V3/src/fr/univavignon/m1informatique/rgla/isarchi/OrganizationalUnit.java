package fr.univavignon.m1informatique.rgla.isarchi;

import java.util.ArrayList;
import java.util.List;

import fr.univavignon.m1informatique.rgla.directory.DirectoryException;
import fr.univavignon.m1informatique.rgla.directory.DistinguishedName;
import fr.univavignon.m1informatique.rgla.directory.DistinguishedNamedObject;

public class OrganizationalUnit extends DistinguishedNamedObject {
	/**
	 * @uml.property name="fatherUnitName"
	 * @uml.associationEnd readOnly="true" inverse=
	 *                     "organizationalUnit:fr.univavignon.m1informatique.rgla.directory.DistinguishedName"
	 */
	private DistinguishedName fatherUnitName;
	private String simpleName;
	private List<DistinguishedName> sonUnitNames;
	private List<DistinguishedName> components;
	private int level;

	public OrganizationalUnit(DistinguishedName fatherUnitName, String simpleName)                   
					throws DirectoryException, ISArchiException {
		super(fatherUnitName.getName() + "/" + simpleName);
		sonUnitNames = new ArrayList<DistinguishedName>();
		components = new ArrayList<DistinguishedName>();
		this.simpleName = simpleName;
		this.fatherUnitName = fatherUnitName;
		level = unitFromName(fatherUnitName).getLevel() + 1;
		unitFromName(fatherUnitName).addSon(this.getDN());
		
	}

	public void addSon(DistinguishedName unitSon) throws ClassCastException, DirectoryException {
		this.sonUnitNames.add(unitSon);
	}

	public void addComponent(DistinguishedName component){
		this.components.add(component);
	}

	public OrganizationalUnit(String simpleName) 
			throws DirectoryException, ISArchiException{
		super(simpleName);
		this.level = 0;
		this.simpleName = simpleName;
		fatherUnitName = null;
		sonUnitNames = new ArrayList<DistinguishedName>();
		components=new ArrayList<DistinguishedName>();
	}

	/**
	 * Getter of the property <tt>fatherUnitName</tt>
	 * 
	 * @return Returns the fatherUnitName.
	 */
	public DistinguishedName getFatherUnitName() {
		return fatherUnitName;
	}

	public String getSimpleName() {
		return simpleName;
	}

	public int getLevel() {
		return level;
	}

	// TODO
	public List<DistinguishedName> getSonUnitNames() {
		return sonUnitNames;
	}

	public List<DistinguishedName> getComponentNames() {
		return components;
	}

	public static OrganizationalUnit unitFromName(DistinguishedName dn)
			throws DirectoryException, java.lang.ClassCastException{
		if (dn.getObject() instanceof OrganizationalUnit) {
			return (OrganizationalUnit) dn.getObject();

		} else {

			ISComponent component = (ISComponent) dn.getObject();
			return (OrganizationalUnit) (component.unitName.getObject());

		}
	}

	public static void displayCollaboratorDistinguishedNames(
			DistinguishedName unitName) throws ClassCastException, DirectoryException {
		
			OrganizationalUnit unit=unitFromName(unitName);
			
			for(int i=0;i<unit.getSonUnitNames().size();i++){
				
				OrganizationalUnit unitFromList=unitFromName(unit.getSonUnitNames().get(i));
				if(unitFromList.getSonUnitNames().size()==0){
					for(int j=0;j<unitFromList.getComponentNames().size();j++){
						
						ISComponent Isc=(ISComponent) unitFromList.getComponentNames().get(j).getObject();
						String nameToDel;
						if(!(unitName.getObject() instanceof OrganizationalUnit)){
						nameToDel=ISComponent.componentFromName(unitName).getSimpleName();	
						}else{
							nameToDel=unitFromName(unitName).getSimpleName();
						}
						String parsedName=Isc.getDN().getName().substring(Isc.getDN().getName().indexOf(nameToDel));
						System.out.println(parsedName);
					}
				}
				else{
					for(int j=0;j<unitFromList.getSonUnitNames().size();j++){
						OrganizationalUnit subUnit=(OrganizationalUnit) unitFromList.getSonUnitNames().get(j).getObject();
						for(int k=0;k<subUnit.getComponentNames().size();k++){
							ISComponent Isc=(ISComponent) subUnit.getComponentNames().get(k).getObject();
							System.out.println(Isc.getDN().getName());
							//System.out.println(Isc.getDN().getName().indexOf(unitFromName(unitName).getSimpleName()));
							String test=Isc.getDN().getName().substring(Isc.getDN().getName().indexOf(unitName.getName()));
							System.out.println(test);
						}
					}
				}
				
			
		}
		
		
	}
	
}
