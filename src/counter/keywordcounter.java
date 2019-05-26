package counter;

/**
 * Launches the keyword counter program.
 * <p>
 * This class declares the main function. Moreover, the command line arguments
 * are parsed and passed into the main processing class for reading the keywords
 * file and writing the results file.
 */
public class keywordcounter
{
	// @formatter:off
	/**
	 * Processes the command line arguments and calls the main keyword counter class.
	 * <p>
	 * Usage:
	 * <p>
	 * java -jar keywordcounter <file_name> 
	 * - file_name: name and path to the input file
	 * <p>
	 * 
	 * @param args Arguments to the keywordcounter program. This should only contain
	 *             the keywords input file.
	 */
	// @formatter:on
	public static void main(String[] args)
	{
		if (args.length < 1)
		{
			// @formatter:off
			System.out.println(
					  "The input file is not provided. Usage:\n"
					+ "\n"
					+ "  java keywordcounter <file_name>\n"
					+ "    - file_name: name and path to the input file\n");
			// @formatter:on
			return;
		}
		try
		{
			String inputFile = args[0];
			KeywordProcessor counter = new KeywordProcessor();
			counter.updateKeywords(inputFile);
			System.out.println("Finished... Output written to " + KeywordProcessor.OUTPUT_FILE);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
