package generator;

public class Keyword implements Entry
{
	public String word;
	public int frequency;
	
	public Keyword(String word, int frequency)
	{
		this.word = word;
		this.frequency = frequency;
	}
	
	@Override
	public String getSearchEntry()
	{
		StringBuilder searchEntry = new StringBuilder();
		searchEntry.append('$');
		searchEntry.append(word);
		searchEntry.append(' ');
		searchEntry.append(frequency);
		return searchEntry.toString();
	}
}
