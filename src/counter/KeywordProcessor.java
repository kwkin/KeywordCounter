package counter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.StringJoiner;

import structure.FibonacciHeapNode;
import structure.MaxFibonacciHeap;
 
/**
 * Reads the keywords file, processes the text, and writes the results.
 * <p>
 * The keywords file consists of a search, query, and a stop.
 * <p>
 * When a search is read, the keyword is inserted into the hash table and
 * Fibonacci heap if it is the first time the keyword has appeared. If the
 * keyword has already appeared, the Fibonacci node is obtained from the hash
 * table, and the increase key method is used to update the frequency count of
 * the keyword.
 * <p>
 * When a query is read, the N most popular keywords are obtained, where N is
 * the number of requested top keywords to obtain. The removeMax operation is
 * used on the Fibonnaci heap N times to obtain the nodes. The nodes are then
 * reinserted into the Fibonnaci heap.
 * <p>
 * When a stop is read, the program exits and the input and output file are
 * closed. The output file is output.txt.
 */
public class KeywordProcessor
{
	public final static String OUTPUT_FILE = "output.txt";

	// Hash table with keys and values as keywords and Fibonacci nodes respectively
	private Hashtable<String, FibonacciHeapNode<String>> keywords;

	// Max Fibonacci heap used to track the frequencies of keywords
	private MaxFibonacciHeap<String> frequencies;

	// Reads the input file
	private BufferedReader reader;

	// Writes the OUTPUT_FILE
	private BufferedWriter writer;

	/**
	 * Construct an instance of the KeywordCounter
	 * <p>
	 * The hash table and max Fibonacci heap are initialized.
	 */
	public KeywordProcessor()
	{
		this.keywords = new Hashtable<String, FibonacciHeapNode<String>>();
		this.frequencies = new MaxFibonacciHeap<String>();
	}

	/**
	 * Reads the given keywords file and appends the result to output.txt
	 * <p>
	 * This operation process the entire keywords files, and writes the output as
	 * queries are reached.
	 * <p>
	 * 
	 * @param file Keywords input file
	 * @throws IOException If an error occurs with reading or writing
	 */
	public void updateKeywords(String file) throws IOException
	{
		// Create a buffered reader and writer
		Path inputPath = Paths.get(file);
		if (Files.notExists(inputPath))
		{
			throw new IOException("Input file, " + file + ", does not exist");
		}
		Path outputPath = Paths.get(OUTPUT_FILE);
		this.reader = new BufferedReader(new FileReader(file.toString()));
		this.writer = new BufferedWriter(new FileWriter(outputPath.toString()));

		// Parse the input file and perform the corresponding action, one line at a time
		String line = "";
		while ((line = this.reader.readLine()) != null && !line.equalsIgnoreCase("stop"))
		{
			Entry entry = Entry.createEntry(line);
			switch (entry)
			{
			case SEARCH:
				processSearch(line);
				break;
			case QUERY:
				processQuery(line);
				break;
			default:
				break;
			}
		}
		this.reader.close();
		this.writer.close();
	}

	/**
	 * Adds the keyword to the hash table and Fibonacci heap
	 * <p>
	 * If the keyword is not in the hash table, a Fibonacci node is created and
	 * inserted as the value to the hash table, with the keyword string being the
	 * key. Furthermore, the node is inserted into the Max Fibonacci Heap. The run
	 * time complexity of this operation is O(1).
	 * <p>
	 * If the keyword is in the hash table, then the value will point to the
	 * corresponding entry in the heap. The increase key function is called on the
	 * heap at that node. The key is increased by the frequency. The amortized
	 * complexity of this operation is O(log(b))
	 * <p>
	 * 
	 * @param text Text formatted as a search line
	 */
	private void processSearch(String text)
	{
		// Parse the line to get keyword and frequency
		String[] items = text.split(" ");
		String keyword = items[0].substring(1, items[0].length());
		int frequency = Integer.parseInt(items[1]);

		// Update hash table and Fibonacci heap
		FibonacciHeapNode<String> node = keywords.get(keyword);
		if (node == null)
		{
			// If this is the first instance of the keyword, add to table and Fibonacci heap
			node = new FibonacciHeapNode<String>(keyword);
			keywords.put(keyword, node);
			frequencies.insert(node, frequency);
		}
		else
		{
			// If there is already an instance of the keyword, increase the key
			frequencies.increaseKey(node, frequency);
		}
	}

	/**
	 * Writes the provided number of top keywords beginning from the most requested.
	 * <p>
	 * This operation will use a Max Fibonacci heap to remove the provided number of
	 * nodes. The items removed will be in descending order. Only the requested
	 * number of items will be removed from the heap. Once the requested number of
	 * nodes are removed, each node is reinserted into the Fibonacci heap.
	 * <p>
	 * The amortized complexity of this operation is O(log(n) * m), where n is the
	 * number of nodes in the Fibonacci heap, and m is the number of top keywords
	 * that need to be displayed.
	 * <p>
	 * 
	 * @param text Text formatted as a query line
	 * @throws IOException If an error occurs with writing the results
	 */
	private void processQuery(String text) throws IOException
	{
		// Parse the line to get the number of top keywords to get
		int toQuery = Math.min(Integer.parseInt(text), this.frequencies.getNumberOfNodes());

		// Remove the top keywords from the heap
		List<FibonacciHeapNode<String>> topKeywords = new ArrayList<FibonacciHeapNode<String>>(toQuery);
		for (int count = 0; count < toQuery; count++)
		{
			topKeywords.add(frequencies.removeMax());
		}

		// Reinsert the nodes and create a string with the keywords
		StringJoiner output = new StringJoiner(",");
		for (FibonacciHeapNode<String> nodeKeywords : topKeywords)
		{
			output.add(nodeKeywords.data);
			frequencies.insert(nodeKeywords);
		}
		
		// Write to the output file
		this.writer.append(output.toString());
		this.writer.newLine();
	}

}
