package generator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

public class FrequencyCounter
{
	public String completeResultsFile;
	public String resultsFile;
	public BufferedReader reader;
	public BufferedWriter completeWriter;
	public BufferedWriter writer;
	public Hashtable<String, Integer> words;
	
	public FrequencyCounter(String inputFile)
	{
		this.completeResultsFile = "complete_results_" + inputFile;
		this.resultsFile = "results_" + inputFile;
		try
		{
			this.writer = new BufferedWriter(new FileWriter(this.resultsFile));
			this.completeWriter = new BufferedWriter(new FileWriter(this.completeResultsFile));
			this.reader = new BufferedReader(new FileReader(inputFile));
		} catch (IOException e)
		{
		}
		this.words = new Hashtable<String, Integer>();
	}
	
	public void computeResults()
	{
		try
		{
			String line;
			int currentLine = 0;
			while ((line = this.reader.readLine()) != null)
			{
				String[] items = line.split(" ");
				if (items.length == 2)
				{
					String word = items[0].substring(1, items[0].length());
					int frequency = Integer.parseInt(items[1]);
					if (this.words.containsKey(word))
					{
						int currentFrequency = this.words.get(word);
						this.words.replace(word, currentFrequency + frequency);
					}
					else
					{
						this.words.put(word, frequency);
					}					
				}
				else if (items[0].contains("stop"))
				{
					break;
				}
				else
				{
					writeResults(Integer.parseInt(items[0]), currentLine);
				}
				currentLine++;
			}
			this.writer.close();
			this.completeWriter.close();
			this.reader.close();
		} catch (IOException e)
		{
		}
	}
	
	public void writeResults(int numToWrite, int currentLine) throws IOException
	{		
		Set<Map.Entry<String, Integer>> entries = this.words.entrySet();
		List<Map.Entry<String, Integer>> entriesList = new ArrayList<Map.Entry<String, Integer>>();
		entriesList.addAll(entries);
		Collections.sort(entriesList, new SortByFrequency());
		for (int index = 0; index < entriesList.size(); ++index)
		{
			StringBuilder toWrite = new StringBuilder();
			if (index == numToWrite)
			{
				toWrite.append("Others: \n");
			}
			toWrite.append(index + 1);
			toWrite.append(") ");
			toWrite.append(entriesList.get(index).getKey());
			toWrite.append(" with frequency ");
			toWrite.append(entriesList.get(index).getValue());
			toWrite.append('\n');
			this.completeWriter.write(toWrite.toString());
		}
		this.completeWriter.write("------------------\n");
		StringJoiner joiner = new StringJoiner(",");
		for (int index = 0; index < numToWrite; ++index)
		{
			joiner.add(entriesList.get(index).getKey());
		}
		this.writer.write(joiner.toString());
		this.writer.newLine();
	}
	
	class SortByFrequency implements Comparator<Map.Entry<String, Integer>> 
	{ 
	    public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) 
	    { 
	        return b.getValue() - a.getValue(); 
	    } 
	} 
}
