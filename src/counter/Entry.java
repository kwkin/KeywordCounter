package counter;
/**
 * An entry represents an operation in the keyword input file.
 * <p>
 * There are three entry types in the keyword input file: search, query, and
 * stop. A search represents the keyword and frequency line in the input file. A
 * query represents when the most popular keywords should be obtained. A stop is
 * simply "stop".
 */
public enum Entry
{
	QUERY, SEARCH, STOP;

	/**
	 * Factory method which parses and creates entries based upon an input string.
	 * <p>
	 * Entries can either be a query a search or a stop. A query will consist only
	 * of an integer. A search will follow the format:
	 * <p>
	 * $[keyword] [count]
	 * <p>
	 * where keyword is the searched keyword and count is the number of searches for
	 * that keyword. A stop is simply "stop".
	 * <p>
	 * @param text Text to parse
	 * @return A query or search entry. Null can be returned if the text does not
	 *         match any entry type.
	 */
	public static Entry createEntry(String text)
	{
		Entry entry = null;
		
		// Try to match either a search, query, or stop line
		try
		{
			if (text.matches("\\$.+ \\d+"))
			{
				entry = SEARCH;
			}
			else if (text.matches("\\d+"))
			{
				entry = QUERY;
			}
			else if (text.equalsIgnoreCase("stop"))
			{
				entry = STOP;
			}
		}
		catch (Exception e)
		{

		}
		return entry;
	}
}
