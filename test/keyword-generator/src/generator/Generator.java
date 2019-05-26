package generator;

public class Generator
{
	public static void main(String[] args)
	{				
		String inputName = "input1.txt";
		int numUniqueWords = 30;
		int numKeywords = 500;

		FileGenerator generator = new FileGenerator(inputName, numUniqueWords, numKeywords);
		generator.writeKeyWords();
		
		FrequencyCounter counter = new FrequencyCounter(inputName);
		counter.computeResults();
		System.out.println("completed");
	}
}
