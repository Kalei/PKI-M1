package fr.univavignon.m1informatique.rgla.pki.types;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import fr.univavignon.m1informatique.rgla.pki.PKIException;

/**
 * la classe <code>Period</code> implément le concept de période temporelle
 * 
 * @author mb
 */
public class Period implements Serializable
{
	private static final long serialVersionUID = 1L;

	private long start;

	private long end;

	/**
	 * @param start period
	 * @param end period
	 * @throws PKIException if start larger than end
	 */
	public Period(long start, long end) throws PKIException
	{
		if (start > end)
			throw new PKIException("bad period" + this);
		this.start = start;
		this.end = end;
	}

	/**
	 * @return start period
	 */
	public long getStart()
	{
		return start;
	}

	/**
	 * @return en period
	 */
	public long getEnd()
	{
		return end;
	}

	@Override
	public String toString()
	{
		StringBuffer result = new StringBuffer();
		result.append("start : ");
		result.append(displayDate(start));

		result.append("\nend : ");
		result.append(displayDate(end));

		return result.toString();
	}

	private String displayDate(long date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		Date d = calendar.getTime();
		return DateFormat.getInstance().format(d);
	}
}
