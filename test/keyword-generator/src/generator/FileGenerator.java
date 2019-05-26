package generator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class FileGenerator
{
	public BufferedWriter writer;
	public int numUniqueWords;
	public int numKeywords;
	public List<String> wordList;
	public RandomWordGenerator generator;
	public int maxFrequency;
	public int searchChance;

	public FileGenerator(String fileName, int numUniqueWords, int numKeywords)
	{
		this.numUniqueWords = numUniqueWords;
		this.numKeywords = numKeywords;
		this.generator = new RandomWordGenerator();
		this.wordList = this.generator.generateWords(this.numUniqueWords);
		try
		{
			this.writer = new BufferedWriter(new FileWriter(fileName));
		} catch (IOException e)
		{

		}
		this.maxFrequency = 10;
		this.searchChance = 5;
	}

	public void writeKeyWords()
	{
		Random random = new Random();
		try
		{
			for (int entriesWritten = 0; entriesWritten < this.numKeywords; ++entriesWritten)
			{
				int wordIndex = getGaussianIndex();
				String word = this.wordList.get(wordIndex);
				int frequency = getFrequency();
				Entry entry = new Keyword(word, frequency);
				writeLine(entry);
				if (shouldSearch())
				{
					int searchLength = random.nextInt(this.maxFrequency - 1) + 1;
					searchLength = Math.min(entriesWritten, searchLength);
					Entry search = new Search(searchLength);
					writeLine(search);
				}
			}
			int searchLength = random.nextInt(this.maxFrequency - 1) + 1;
			Entry search = new Search(searchLength);
			writeLine(search);
			this.writer.write("stop");
			this.writer.close();
		} catch (IOException e)
		{

		}
	}

	public int getGaussianIndex()
	{
		Random random = new Random();
		double gaussian = random.nextGaussian();
		gaussian /= 3;
		gaussian *= (this.numUniqueWords / 2);
		gaussian += (this.numUniqueWords / 2);
		gaussian = Math.max(gaussian, 0);
		gaussian = Math.min(gaussian, this.numUniqueWords - 1);
		return (int) gaussian;
	}

	public int getFrequency()
	{
		Random random = new Random();
		int frequency = random.nextInt(this.maxFrequency - 1) + 1;
		return frequency;
	}

	public boolean shouldSearch()
	{
		Random random = new Random();
		int chance = random.nextInt(100);
		return chance <= this.searchChance;
	}

	public void writeLine(Entry entry) throws IOException
	{
		String toWrite = entry.getSearchEntry();
//		System.out.println(toWrite);
		this.writer.write(toWrite + "\n");
	}
}
