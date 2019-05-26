package generator;

public class Search implements Entry
{
	public int amount;
	
	public Search(int amount)
	{
		this.amount = amount;
	}
	
	@Override
	public String getSearchEntry()
	{
		return Integer.toString(amount);
	}
}