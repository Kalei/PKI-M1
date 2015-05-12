package fr.univavignon.m1informatique.rgla.isarchi;

import fr.univavignon.m1informatique.rgla.directory.DirectoryException;
import fr.univavignon.m1informatique.rgla.directory.DistinguishedName;
import fr.univavignon.m1informatique.rgla.directory.DistinguishedNamedObject;

public class ISComponent extends DistinguishedNamedObject {
	
	/**
	 * @uml.property name="unitName"
	 * @uml.associationEnd readOnly="true" inverse=
	 *                     "iSComponent:fr.univavignon.m1informatique.rgla.directory.DistinguishedName"
	 */
	DistinguishedName unitName;
	String simpleName;
	/**
	 * @uml.property name="type"
	 * @uml.associationEnd readOnly="true" inverse=
	 *                     "iSComponent:fr.univavignon.m1informatique.rgla.isarchi.ISComponentType"
	 */
	private ISComponentType type;
	
	public ISComponent(DistinguishedName unitName, ISComponentType type, String simpleName)
		            throws DirectoryException, ISArchiException{
		super(unitName.getName()+"/"+simpleName);
		
		if(simpleName.indexOf("/")!=-1)
		{
			throw new ISArchiException();
		}
		else{
			this.unitName = unitName;
			this.type = type;
			this.simpleName = simpleName;
			((OrganizationalUnit)unitName.getObject()).addComponent(this.getDN());
		}
		
	}
	
	public static ISComponent componentFromName(DistinguishedName componentName) {
		DistinguishedNamedObject componentDNO=componentName.getObject();
		if(componentName.getObject() instanceof ISComponent){
			return (ISComponent) componentDNO;
		}
		if(((OrganizationalUnit)componentDNO).getFatherUnitName()==null)return null;

		
		return componentFromName(((OrganizationalUnit)componentDNO).getFatherUnitName());
	}

	public DistinguishedName getUnitName() {
		return unitName;
	}

	public String getSimpleName() {
		return simpleName;
	}

	/**
	 * Getter of the property <tt>type</tt>
	 * 
	 * @return Returns the type.
	 */
	public ISComponentType getType() {
		return type;
	}
	
}
