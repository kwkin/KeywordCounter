package structure;
/**
 * A Fibonacci heap node.
 * <p>
 * The main purpose of this class is to define the required node structure for
 * creating a Fibonacci heap. That is, paramaters are defined for the degree,
 * child, left sibling, right sibling, data, parent, and child cut parameters.
 * Additional functionality is provided for setting the relationships between
 * instances of this class.
 * <p>
 *
 * @param <T> The data type contained within this node
 */
public class FibonacciHeapNode<T>
{
	public T data;

	FibonacciHeapNode<T> parent;
	FibonacciHeapNode<T> child;
	FibonacciHeapNode<T> left;
	FibonacciHeapNode<T> right;

	double key;
	int degree;
	public boolean childCut;

	/**
	 * Constructs an instance of a Fibonacci Heap Node with the data set to the
	 * provided argument
	 * <p>
	 * 
	 * @param data The data to be contained within the node
	 */
	public FibonacciHeapNode(T data)
	{
		this.data = data;
		this.degree = 0;
		this.childCut = false;
	}

	/**
	 * Constructs an instance of a Fibonacci Heap Node with the data set to null
	 */
	public FibonacciHeapNode()
	{
		this.data = null;
		this.degree = 0;
		this.childCut = false;
	}

	/**
	 * Gets the degree of this node
	 * <p>
	 * The degree indicates the number of children that this node has
	 * <p>
	 * 
	 * @return The degree of this node
	 */
	public int getDegree()
	{
		return this.degree;
	}

	/**
	 * Gets the key of this node
	 * <p>
	 * 
	 * @return The key of this node
	 */
	public double getKey()
	{
		return this.key;
	}

	/**
	 * Gets the child cut value
	 * <p>
	 * The child cut value indicates if this node has lost a child since it became a
	 * child of its current parent.
	 * <p>
	 * 
	 * @return The child cut value
	 */
	public boolean getChildCut()
	{
		return this.childCut;
	}

	/**
	 * Gets the node with the largest key value among itself and its siblings.
	 * <p>
	 * The runtime complexity is O(n), where n is the number of nodes in the list.
	 * The list consists of this node and all of its siblings.
	 * <p>
	 * 
	 * @return The node with the largest key value within the siblings list
	 */
	public FibonacciHeapNode<T> getLargestKey()
	{
		FibonacciHeapNode<T> largestNode = this;
		FibonacciHeapNode<T> currentNode = this.right;
		while (currentNode != this)
		{
			if (currentNode.key > largestNode.key)
			{
				largestNode = currentNode;
			}
			currentNode = currentNode.right;
		}
		return largestNode;
	}

	/**
	 * Gets the number of siblings
	 * <p>
	 * The runtime complexity is O(n), where n is the number of siblings. This node
	 * is not counted.
	 * <p>
	 * 
	 * @return The number of siblings
	 */
	public int getNumberOfSiblings()
	{
		int numberOfNodes = 0;
		FibonacciHeapNode<T> currentNode = this.right;
		while (currentNode != this)
		{
			numberOfNodes++;
			currentNode = currentNode.right;
		}
		return numberOfNodes;
	}

	/**
	 * Returns a string representing the node's key
	 * <p>
	 * The string is formatted as: node_key
	 * <p>
	 * 
	 * @return A string representing the node's key
	 */
	@Override
	public String toString()
	{
		return Double.toString(this.key);
	}

	/**
	 * Returns a string representing the node's key and data
	 * <p>
	 * The string is formatted as:
	 * <p>
	 * key_value:data
	 * <p>
	 * 
	 * @return A string representing the node's key and data
	 */
	public String toStringValue()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(this.key);
		builder.append(":");
		builder.append(this.data);
		return builder.toString();
	}

	// @formatter:off
	/**
	 * Returns a string representing the node's key, relationships, and values.
	 * <p>
	 * The string is formatted as:
	 * <p>
	 * parent_key:data 
	 * left_sib_key:data -> key:data -> right_sib_key:data
	 * child_key:data
	 * <p>
	 * 
	 * @return A string representing the node's key, relationships, and values
	 */
	// @formatter:on
	public String toStringDetailed()
	{
		StringBuilder builder = new StringBuilder();
		if (this.parent != null)
		{
			builder.append(this.parent.key);
			builder.append(":");
			builder.append(this.parent.data);
		}
		else
		{
			builder.append("null");
		}
		builder.append("\n");
		builder.append(this.left.key);
		builder.append(":");
		builder.append(this.left.data);
		builder.append(" -> ");
		builder.append(this.key);
		builder.append(":");
		builder.append(this.data);
		builder.append(" -> ");
		builder.append(this.right.key);
		builder.append(":");
		builder.append(this.right.data);
		builder.append("\n");
		if (this.child != null)
		{
			builder.append(this.child.key);
			builder.append(":");
			builder.append(this.child.data);
		}
		else
		{
			builder.append("null");
		}
		return builder.toString();
	}

	/**
	 * Removes this node from its siblings and parent.
	 * <p>
	 * The sibling node's left and right siblings are updated.
	 */
	void removeFromList()
	{
		this.right.left = this.left;
		this.left.right = this.right;
		this.right = this;
		this.left = this;
		this.parent = null;
	}

	/**
	 * Melds the node and its siblings to this node and this node's siblings
	 * <p>
	 * The node and its siblings are inserted to the right of this node.
	 * <p>
	 * The runtime complexity of this operation O(1).
	 * <p>
	 * 
	 * @param heap Node to meld with
	 */
	void meld(FibonacciHeapNode<T> node)
	{
		node.left.right = this.right;
		this.right.left = node.left;
		this.right = node;
		node.left = this;
	}

	/**
	 * Adds the node as this node's child.
	 * <p>
	 * This operation assumes that the parameter node has no siblings. The node is
	 * inserted as the immediate child of this node. The child siblings and parent
	 * pointers are updated accordingly.
	 * <p>
	 * The runtime complexity of this operation is O(1).
	 * <p>
	 * 
	 * @param node The node to add as a child
	 */
	void addAsChild(FibonacciHeapNode<T> node)
	{
		if (this.child != null)
		{
			this.child.addAsLeftSibling(node);
		}
		node.parent = this;
		this.child = node;
		node.childCut = false;
		this.degree++;
	}

	/**
	 * Adds the node as this node's right sibling.
	 * <p>
	 * This operation assumes that the parameter node has no siblings.
	 * <p>
	 * The runtime complexity of this operation is O(1).
	 * <p>
	 * 
	 * @param node The node to add as a sight sibling
	 */
	void addAsRightSibling(FibonacciHeapNode<T> node)
	{
		node.left = this;
		node.right = this.right;
		this.right.left = node;
		this.right = node;
	}

	/**
	 * Adds the node as this node's left sibling.
	 * <p>
	 * This operation assumes that the node has no siblings.
	 * <p>
	 * The runtime complexity of this operation is O(1).
	 * <p>
	 * 
	 * @param node The node to add as a left sibling
	 */
	void addAsLeftSibling(FibonacciHeapNode<T> node)
	{
		node.right = this;
		node.left = this.left;
		node.left.right = node;
		this.left = node;
	}
}
