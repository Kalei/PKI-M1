package fr.univavignon.m1informatique.rgla.security;

import java.io.Serializable;

import fr.univavignon.m1informatique.rgla.util.SerializationUtil;

/**
 * La classe <code>EncryptedObject</code> permet d'implémenter le concept d'objet encrypté.
 * <p>
 * Le constructeur {@link EncryptedObject#EncryptedObject(Serializable, AbstractKey)} crypte l'objet en prenant en paramètre l'objet à encrypté qui doit être
 * sérialisable, l'algorithme d'encryptage et la clé publique d'encryptage du destinataire du message
 * <p>
 * La méthode {@link EncryptedObject#getObject(AbstractKey)} permet de décriptage l'objet avec la clé privée d'encryptage du destinataire.
 * 
 * <pre>
 * // in Bob class
 * Message messageToAlice = new Message(&quot;I love you&quot;);
 * EncryptedObject encryptedMessage = new encryptedObject(messageToAlice, alicePublicKey);
 * 
 * // in Alice class
 * Message messageFromBob = (Message) encryptedMessage.getObject(alicePrivateKey);
 * messageFromBob.read();
 * 
 * </pre>
 * 
 * @author mb
 */
public class EncryptedObject implements Serializable
{

	private static final long serialVersionUID = 1L;

	private byte[] encrypted;

	/**
	 * @param object to encrypt
	 * @param receiverPublicKey used to encrypt
	 * @throws SecurityException if encryption algorithm is not found from the key
	 */
	public EncryptedObject(Serializable object, AbstractKey receiverPublicKey) throws SecurityException
	{
		try
		{
			encrypted = receiverPublicKey.getAsymetricAlgorithm().encrypt(SerializationUtil.serialize(object), receiverPublicKey);
		}
		catch (Exception e)
		{
			throw new SecurityException(e);
		}

	}

	/**
	 * @param receiverPrivateKey used to decrypt.
	 * @return decrypted object
	 * @throws SecurityException if encryption algorithm is not found from the key
	 */
	public Object getObject(AbstractKey receiverPrivateKey) throws SecurityException
	{
		try
		{
			return SerializationUtil.deserialize(receiverPrivateKey.getAsymetricAlgorithm().decrypt(encrypted, receiverPrivateKey));
		}

		catch (Exception e)
		{
			throw new SecurityException(e);
		}
	}
}
