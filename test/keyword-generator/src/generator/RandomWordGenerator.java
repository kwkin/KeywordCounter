package generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomWordGenerator
{
	public String validCharacters;
	public int minWordLength = 1;
	public int maxWordLength = 25;
	public int minWords = 1;
	public int maxWords = 100;

	public RandomWordGenerator()
	{
		this.validCharacters = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM_!@#$%^&*()-=+_1234567890[];',.\\/<>?|`~";
	}
	
	public RandomWordGenerator(String validCharacters)
	{
		this.validCharacters = validCharacters;
	}

	public String generateWord()
	{
		Random random = new Random();
		int wordLength = random.nextInt(this.maxWordLength - this.minWordLength + 1) + this.minWordLength;
		return generateWord(wordLength);
	}

	public String generateWord(int length)
	{
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(length);
		for (int index = 0; index < length; index++)
		{
			int randomIndex = random.nextInt(this.validCharacters.length() - 1);
			buffer.append(this.validCharacters.charAt(randomIndex));
		}
		return buffer.toString();
	}

	public List<String> generateWords()
	{
		Random rand = new Random();
		int numWords = rand.nextInt(this.maxWords - this.minWords) + this.minWords;
		return generateWords(numWords);
	}

	public List<String> generateWords(int numWords)
	{
		if (numWords <= 0)
		{
			String error = "The number of words cannot be " + numWords
					+ ". The number of words must be greater than 0.";
			throw new IllegalArgumentException(error);
		}
		List<String> words = new ArrayList<String>();
		for (int index = 0; index < numWords; ++index)
		{
			words.add(generateWord());
		}
		return words;
	}
}
