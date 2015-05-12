package fr.univavignon.m1informatique.rgla.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * la classe <code>SerializationUtil</code> permet de sérialiser et de désérialiser des objets en mémoire.
 * 
 * @author mb
 */
public class SerializationUtil
{

	/**
	 * serialize an object in memory.
	 * 
	 * @param object to serialize
	 * @return the serialized object as an array of bytes
	 * @throws SerializationException
	 */
	public static byte[] serialize(Serializable object) throws SerializationException
	{
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

		try
		{
			ObjectOutputStream stream;

			stream = new ObjectOutputStream(byteStream);

			stream.writeObject(object);
			stream.close();

			return byteStream.toByteArray();
		}
		catch (Exception e)
		{
			throw new SerializationException(" problem when serialize " + object);
		}
	}

	/**
	 * deserialize an object in memory
	 * @param serialized object as an array of bytes
	 * @return the object
	 * @throws SerializationException
	 */
	public static Object deserialize(byte[] serialized) throws SerializationException
	{
		try
		{
			ByteArrayInputStream byteStream = new ByteArrayInputStream(serialized);
			Object object = null;

			ObjectInputStream stream = new ObjectInputStream(byteStream);
			object = stream.readObject();
			stream.close();

			return object;
		}
		catch (Exception e)
		{
			throw new SerializationException(" problem when deserialize " + serialized);
		}
	}

}
