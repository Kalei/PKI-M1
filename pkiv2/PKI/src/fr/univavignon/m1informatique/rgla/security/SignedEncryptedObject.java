package fr.univavignon.m1informatique.rgla.security;

import java.io.Serializable;

import fr.univavignon.m1informatique.rgla.directory.DistinguishedName;

/**
 * La classe <code>SignedEncryptedObject</code> permet de sécurisé un objet en le cryptant et en signant l'objet crypté.
 * 
 * <pre>
 * // in Bob class
 * Message messageToAlice = new Message(&quot;I love you&quot;);
 * SignedEncryptedObject signedEncryptedMessage = new SignedEncryptedObject(Message, alicePublicKey, new AsymetricAlgorithm(), bobPrivateKey, Hash.buildHash()
 * 		.getDistinguishedName());
 * 
 * // in Alice class
 * 
 * // I am sure is from Bob
 * if (signedEncryptedMessage.isAuthentic(publicBobKey))
 * {
 * 	Message messageFromBob = (Message) signedEncryptedMessage.getObject(alicePrivateKey);
 * 	messageFromBob.read();
 * }
 * // is not from Bob
 * {
 * 	signedEncryptedMessage.toTrash();
 * }
 * </pre>
 * 
 * @author mb
 */
public class SignedEncryptedObject extends SignedObject
{

	private static final long serialVersionUID = 1L;

	/**
	 * @param object to secure
	 * @param receiverPublicKey used to encrypt
	 * @param senderPrivateKey used to sign
	 * @param hashName name of the hash algorithm used to sign
	 * @throws SecurityException if encryption algorithm is not found from the key
	 */
	public SignedEncryptedObject(Serializable object, AbstractKey receiverPublicKey, AbstractKey senderPrivateKey, DistinguishedName hashName)
			throws SecurityException
	{
		super(new EncryptedObject(object, receiverPublicKey), senderPrivateKey, hashName);

	}

	/**
	 * get the object
	 * 
	 * @param receiverPrivateKey used to decrypt
	 * @return the object
	 * @throws SecurityException if encryption algorithm is not found from the key
	 */
	public Object getObject(AbstractKey receiverPrivateKey) throws SecurityException
	{

		return ((EncryptedObject) super.getObject()).getObject(receiverPrivateKey);
	}

}
