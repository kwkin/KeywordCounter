package structure;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// @formatter:off
/**
 * A max Fibonacci heap data structure
 * <p>
 * This class implements all Fibonacci heap algorithms discussed in class.
 * Specifically: insert, remove max, meld, remove, and increase key.
 * <p>
 * The actual and amortized complexities for each are:
 * <p>
 * Function Actual Amortized 
 * 1) Insert 		O(1) 	O(1) 
 * 2) RemoveMax 	O(n) 	O(log(n)) 
 * 3) Meld 			O(1) 	O(1) 
 * 4) Remove 		O(n) 	O(log(n)) 
 * 5) IncreaseKey 	O(n) 	O(1)
 * <p>
 * 
 * @param <T> The data type contained within a node
 */
//@formatter:on
public class MaxFibonacciHeap<T>
{
	FibonacciHeapNode<T> maxNode;

	int numberOfNodes;

	/**
	 * Constructs an empty heap
	 */
	public MaxFibonacciHeap()
	{
		this.maxNode = null;
		this.numberOfNodes = 0;
	}

	/**
	 * Constructs a heap with the provided node as the max
	 * <p>
	 * 
	 * @param maxNode The initial max node
	 */
	public MaxFibonacciHeap(FibonacciHeapNode<T> node)
	{
		this.maxNode = node;
		this.numberOfNodes = 1;
	}

	/**
	 * Checks if the heap is empty
	 * <p>
	 * The runtime complexity of this operation is O(1)
	 * <p>
	 * 
	 * @return True if the heap is empty
	 */
	public boolean isEmpty()
	{
		return this.numberOfNodes == 0;
	}

	/**
	 * Gets the number of nodes in this heap.
	 * <p>
	 * The runtime complexity of this operation is O(1).
	 * <p>
	 * 
	 * @return The number of nodes in this heap.
	 */
	public int getNumberOfNodes()
	{
		return this.numberOfNodes;
	}

	/**
	 * Sets the node to the provided key and inserts the node into the heap
	 * <p>
	 * The node is inserted to the left of the max node. If the heap is empty the
	 * node left and right sibling's are set to itself. The max node is updated if
	 * the node is greater than the current max node.
	 * <p>
	 * The runtime complexity of this operation is O(1).
	 * <p>
	 * 
	 * @param node Node to insert
	 * @param key  Key value of the node
	 */
	public void insert(FibonacciHeapNode<T> node, double key)
	{
		node.key = key;
		node.degree = 0;
		node.childCut = false;

		// Insert to the top level list if not empty; otherwise initialize list
		if (this.maxNode != null)
		{
			this.maxNode.addAsRightSibling(node);
			if (key > this.maxNode.key)
			{
				this.maxNode = node;
			}
		}
		else
		{
			node.left = node;
			node.right = node;
			this.maxNode = node;
		}
		this.numberOfNodes++;
	}

	/**
	 * Inserts the node into the heap
	 * <p>
	 * 
	 * @param node
	 */
	public void insert(FibonacciHeapNode<T> node)
	{
		insert(node, node.key);
	}

	/**
	 * Returns the node with the largest key value.
	 * <p>
	 * The max value is returned without removing it from the heap.
	 * <p>
	 * The runtime complexity of this operation is O(1).
	 * <p>
	 * 
	 * @return The node with the largest key value
	 */
	public FibonacciHeapNode<T> getMax()
	{
		return this.maxNode;
	}

	/**
	 * Returns, removes, and updates the largest key value.
	 * <p>
	 * The current max is removed and returned. The new max value is set upon
	 * completion of this operation. The children of the old max is added to the
	 * top-level list, and a pairwise combine is performed.
	 * <p>
	 * The actual complexity of this operation is O(n) and the amortized cost is
	 * O(log(n)).
	 * 
	 * @return The removed max node
	 */
	public FibonacciHeapNode<T> removeMax()
	{
		FibonacciHeapNode<T> maxNode = this.maxNode;
		if (maxNode == null)
		{
			return null;
		}
		this.numberOfNodes--;

		// If not empty, move child of max to top level list and perform a pairwise
		// combined. If empty, set maxNode to null
		if (this.numberOfNodes > 0)
		{
			if (this.maxNode.child != null)
			{
				// Add children to the top-level list
				FibonacciHeapNode<T> currentNode = this.maxNode.child;
				do
				{
					currentNode.parent = null;
					currentNode = currentNode.right;
				} while (currentNode != this.maxNode.child);
				this.maxNode.meld(this.maxNode.child);
			}

			// Perform a pairwise combine
			FibonacciHeapNode<T> rightSibling = this.maxNode.right;
			this.maxNode.removeFromList();
			this.maxNode = rightSibling;
			pairwiseCombine();
		}
		else
		{
			this.maxNode = null;
		}
		maxNode.child = null;
		return maxNode;
	}

	/**
	 * Removes the node from the heap
	 * <p>
	 * This operation assumes the node is in the heap. That is, the node's siblings,
	 * parent, and child node's are correctly set. If the node is the max node, then
	 * the removeMax function is called instead. If the node has children, they are
	 * inserted to the left of the max. A cascading cut is performed.
	 * <p>
	 * The actual runtime complexity is O(n), and the amortized complexity is
	 * O(log(n)).
	 * <p>
	 * 
	 * @param node The node to remove
	 */
	public void remove(FibonacciHeapNode<T> node)
	{
		if (node == this.maxNode)
		{
			removeMax();
		}
		else
		{
			this.numberOfNodes--;

			// Add children to the top-level list
			if (node.child != null)
			{
				FibonacciHeapNode<T> currentNode = node.child;
				do
				{
					currentNode.parent = null;
					currentNode = currentNode.right;
				} while (currentNode != node.child);
				this.maxNode.meld(node.child);
			}

			// Update parent node and perform a cascading cut
			if (node.parent != null)
			{
				FibonacciHeapNode<T> parentNode = node.parent;
				parentNode.degree--;
				if (parentNode.degree > 0)
				{
					parentNode.child = node.right;
				}
				else
				{
					parentNode.child = null;
				}
				node.removeFromList();
				cascadingCut(parentNode);
			}
			else
			{
				node.removeFromList();
			}
		}
		node.child = null;
	}

	/**
	 * Increases the node's key value by the provided increment
	 * <p>
	 * If the node increase by a value that is greater than the parent, then a
	 * cascading cut is performed at the provided node.
	 * <p>
	 * The actual runtime complexity is O(n), and the amortized complexity is O(1).
	 * <p>
	 * 
	 * @param node      The node whom's key to increase
	 * @param increment The value to increase the key by
	 */
	public void increaseKey(FibonacciHeapNode<T> node, double increment)
	{
		node.key += increment;
		if (node.parent != null)
		{
			if (node.key > node.parent.key)
			{
				// Add children to the top-level list
				FibonacciHeapNode<T> parentNode = node.parent;
				node.parent = null;
				parentNode.degree--;
				if (parentNode.degree > 0)
				{
					parentNode.child = node.right;
				}
				else
				{
					parentNode.child = null;
				}
				node.removeFromList();
				this.maxNode.addAsRightSibling(node);
				cascadingCut(parentNode);
			}
		}

		if (node.key > this.maxNode.key)
		{
			this.maxNode = node;
		}
	}

	/**
	 * Melds the provided heap with this heap.
	 * <p>
	 * The two heaps will be combined at the root level list. The max node of the
	 * parameter heap is inserted to the left of this heap. The max pointers of both
	 * heaps are updated.
	 * <p>
	 * 
	 * @param heap Heap to meld this heap with
	 */
	public void meld(MaxFibonacciHeap<T> heap)
	{
		FibonacciHeapNode<T> otherMaxNode = heap.getMax();
		this.maxNode.meld(otherMaxNode);
		if (otherMaxNode.key > this.maxNode.key)
		{
			this.maxNode = otherMaxNode;
		}
		else
		{
			heap.maxNode = this.maxNode;
		}
		this.numberOfNodes += heap.numberOfNodes;
		heap.numberOfNodes = this.numberOfNodes;
	}

	/**
	 * Returns a string representing the heap.
	 * <p>
	 * Each new line in the string is a new level in the heap. Each level contains
	 * each all nodes at that level. Additionally, each parent displays its link to
	 * the child node. The corresponding child element is in the format:
	 * <p>
	 * (parent) -> child_key -> sib1_key -> sib2_key -> ... -> (child_key)
	 * <p>
	 * 
	 * @return String representing the heap
	 */
	@Override
	public String toString()
	{
		if (isEmpty())
		{
			return "";
		}
		StringBuilder builder = new StringBuilder();

		// Keep track of the immediate children using a breadth first search
		Queue<FibonacciHeapNode<T>> firstNodes = new LinkedList<FibonacciHeapNode<T>>();
		int nodesPerNextLevel = 0;
		int nodePerCurrentLevel = this.maxNode.getNumberOfSiblings() + 1;
		firstNodes.add(this.maxNode);

		do
		{
			FibonacciHeapNode<T> currentNode = firstNodes.remove();
			FibonacciHeapNode<T> siblingNode = currentNode;
			if (currentNode.parent != null)
			{
				builder.append("(");
				builder.append(currentNode.parent.toString());
				builder.append("): ");
			}
			else
			{
				builder.append("(root): ");
			}

			// Iterate through siblings and add key values to string
			do
			{
				nodePerCurrentLevel--;
				if (siblingNode.child != null)
				{
					nodesPerNextLevel += siblingNode.degree;
					firstNodes.add(siblingNode.child);
				}
				builder.append(siblingNode.toString());
				builder.append(" -> ");
				siblingNode = siblingNode.right;
			} while (siblingNode != currentNode);
			builder.append("(");
			builder.append(currentNode.toString());
			builder.append(")    ");

			// Add a new line if on a new level
			if (nodePerCurrentLevel == 0)
			{
				nodePerCurrentLevel = nodesPerNextLevel;
				nodesPerNextLevel = 0;
				builder.append("\n");
			}
		} while (!firstNodes.isEmpty());
		return builder.toString();
	}

	/**
	 * Returns a string representing the node and its list of siblings.
	 * <p>
	 * The string follows the format:
	 * <p>
	 * node_key -> sibling1_key -> sibling2_key -> ... -> (node_key)
	 * <p>
	 * 
	 * @param node Node to convert to a string along with its siblings
	 * @return String representing the node and its list of siblings
	 */
	public String toStringSiblings(FibonacciHeapNode<T> node)
	{
		StringBuilder builder = new StringBuilder();
		FibonacciHeapNode<T> currentNode = node;
		do
		{
			builder.append(currentNode.toString());
			builder.append(" -> ");
			currentNode = currentNode.right;
		} while (node != currentNode);
		builder.append("(");
		builder.append(node.toString());
		builder.append(")");
		return builder.toString();
	}

	/**
	 * Returns a string representing the node and its list of children
	 * <p>
	 * (node_key): child1_key -> child2_key -> ... -> (child1_key)
	 * <p>
	 * 
	 * @param node Node to convert to a string along with its children
	 * @return String representing the node and its list of children
	 */
	public String toStringChildren(FibonacciHeapNode<T> node)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("(");
		builder.append(node.toString());
		builder.append("): ");
		builder.append(toStringSiblings(node.child));
		return builder.toString();
	}

	private void pairwiseCombine()
	{
		// Construct a table to merge heaps of like-degrees
		int tableSize = ((int) Math.floor(Math.log(this.numberOfNodes) * 3)) + 1;
		List<FibonacciHeapNode<T>> table = new ArrayList<FibonacciHeapNode<T>>();
		for (int tableIndex = 0; tableIndex < tableSize; tableIndex++)
		{
			table.add(null);
		}
		int topListSize = this.maxNode.getNumberOfSiblings() + 1;

		// Begin pairwise combining all top-level heaps
		FibonacciHeapNode<T> currentNode = this.maxNode;
		do
		{
			int degree = currentNode.degree;
			if (currentNode.key >= this.maxNode.key)
			{
				this.maxNode = currentNode;
			}

			FibonacciHeapNode<T> tableNode = table.get(degree);
			if (tableNode == null)
			{
				// If current degree in table is empty, place root of heap into table
				table.set(degree, currentNode);
				currentNode = currentNode.right;
				topListSize--;
			}
			else if (tableNode != currentNode)
			{
				// If there is an existing degree, add the smaller one as a child of the node
				// with the larger key.
				table.set(degree, null);
				if (tableNode.key > currentNode.key)
				{
					currentNode.removeFromList();
					tableNode.addAsChild(currentNode);
					currentNode = tableNode;
				}
				else
				{
					tableNode.removeFromList();
					currentNode.addAsChild(tableNode);
				}
			}
			else
			{
				// Continue if the current node is already in the table
				currentNode = currentNode.right;
			}
		} while (topListSize > 0);
	}

	private void cascadingCut(FibonacciHeapNode<T> node)
	{
		// Continue up the heap for each node with its childCut value set to true.
		while (node.childCut && node.parent != null)
		{
			FibonacciHeapNode<T> parentNode = node.parent;
			parentNode.degree--;

			// If there are more child nodes, set the parent's child to that. Otherwise set
			// the child to null
			if (parentNode.degree > 0)
			{
				parentNode.child = node.right;
			}
			else
			{
				parentNode.child = null;
			}
			node.removeFromList();

			// Meld the cut node with the top level-list
			this.maxNode.meld(node);
			node = parentNode;
		}
		node.childCut = true;
	}
}
