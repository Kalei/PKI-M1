package fr.univavignon.m1informatique.rgla.security;

import java.io.Serializable;
import java.util.Arrays;

import fr.univavignon.m1informatique.rgla.directory.DirectoryException;
import fr.univavignon.m1informatique.rgla.directory.DistinguishedName;
import fr.univavignon.m1informatique.rgla.util.SerializationUtil;

/**
 * La classe <code>SignedObject</code> permet d'implémenter le concept d'objet
 * signé.
 * <p>
 * Le constructeur
 * {@link SignedObject#SignedObject(Serializable, AbstractKey, DistinguishedName)}
 * signe l'objet en prenant en paramètre l'objet à signer, la clé privé de celui
 * qui signe l'objet et le nom de l'algorithmes de hachage.
 * <p>
 * la méthode {@link SignedObject#isAuthentic(AbstractKey)} permet de vérifier
 * la signature avec la clé publique du signataire.
 * 
 * <pre>
 * // in Bob class
 * Message messageToAlice = new Message(&quot;I love you&quot;);
 * SignedObject signedMessage = new SignedObject(messageToAlice, bobPrivateKey, Hash.buildHas@link SignedObject#SignedObject(Serializable, AbstractKey, Distinguisheh().getDistinguishedName());
 * 
 * // in Alice class
 * 
 * // I am sure is from Bob
 * if (signedMessage.isAuthentic(bobPublicKey))
 * {
 * 	Message messageFromBob = (Message) signedMessage.getObject();
 * 	messageFromBob.read();
 * }
 * // is not from Bob
 * {
 * 	signedMessage.toTrash();
 * }
 * </pre>
 * 
 * @author mb
 */
public class SignedObject implements Serializable {

	private byte signing[];
	private static final long serialVersionUID = 1L;
	private DistinguishedName hashName;

	/**
	 * @uml.property name="object"
	 * @uml.associationEnd inverse="signedObject:java.io.Serializable"
	 */
	private Serializable object;

	/**
	 * @param object
	 *            to sign
	 * @param senderPrivateKey
	 *            used to sign
	 * @param hashName
	 *            name of the hash algorithm used to sign
	 * @throws SecurityException
	 *             if encryption algorithm is not found from the key
	 */
	public SignedObject(Serializable object, AbstractKey senderPrivateKey,
			DistinguishedName hashName) throws SecurityException {
		try {
			this.object = object;
			this.hashName = hashName;

			signing = senderPrivateKey.getAsymetricAlgorithm().encrypt(
					getHash().code(SerializationUtil.serialize(object)),
					senderPrivateKey);
		} catch (Exception e) {
			throw new SecurityException(e);
		}
	}

	private IHash getHash() throws DirectoryException {
		return (IHash) hashName.getObject();
	}

	/**
	 * get signed object
	 * 
	 * @return signed object
	 */
	public Serializable getObject() {
		return object;
	}

	/**
	 * verify if signed object is authentic
	 * 
	 * @param senderPublicKey
	 *            uses to authenticate
	 * @return true if is authentic, false otherwise
	 * @throws SecurityException
	 *             if object cannot be authenticate
	 */
	public boolean isAuthentic(AbstractKey senderPublicKey)
			throws SecurityException {
		try {
			byte[] objectHash = getHash().code(
					SerializationUtil.serialize(object));
			byte[] signHash;

			signHash = senderPublicKey.getAsymetricAlgorithm().decrypt(signing,
					senderPublicKey);

			return Arrays.equals(objectHash, signHash);
		} catch (DirectoryException e) {
			throw new SecurityException(e);
		}
	}

}
