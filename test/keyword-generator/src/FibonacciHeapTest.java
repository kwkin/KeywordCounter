import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import structure.FibonacciHeapNode;
import structure.MaxFibonacciHeap;

/**
 * Unit tests for the Fibonacci heap and node
 *
 * The tests were developed using junit.
 */
class FibonacciHeapTest
{
	// Precision error when testing doubles
	public static double EPSILON = 0.00001;

	/**
	 * Tests inserting and removing from the heap.
	 */
	@Test
	void InsertAndRemoveTest1()
	{
		MaxFibonacciHeap<String> heap = new MaxFibonacciHeap<String>();
		Assert.assertEquals("Heap is not empty", true, heap.isEmpty());

		FibonacciHeapNode<String> node10 = new FibonacciHeapNode<String>();
		heap.insert(node10, 10);
		Assert.assertEquals("Heap is empty", false, heap.isEmpty());
		Assert.assertEquals("Key is != 10", 10, heap.getMax().getKey(), EPSILON);

		FibonacciHeapNode<String> maxNode10 = heap.removeMax();
		Assert.assertEquals("Heap is not empty", true, heap.isEmpty());
		Assert.assertEquals("Key is != 10", 10, maxNode10.getKey(), EPSILON);

		FibonacciHeapNode<String> node20 = new FibonacciHeapNode<String>();
		heap.insert(node20, 20);
		FibonacciHeapNode<String> node25 = new FibonacciHeapNode<String>();
		heap.insert(node25, 25);
		FibonacciHeapNode<String> node30 = new FibonacciHeapNode<String>();
		heap.insert(node30, 30);
		FibonacciHeapNode<String> node15 = new FibonacciHeapNode<String>();
		heap.insert(node15, 15);
		FibonacciHeapNode<String> node5 = new FibonacciHeapNode<String>();
		heap.insert(node5, 5);
		Assert.assertEquals("Number of nodes != 5", 5, heap.getNumberOfNodes());

		FibonacciHeapNode<String> maxNode30 = heap.removeMax();
		Assert.assertEquals("Key is != 30", 30, maxNode30.getKey(), EPSILON);
		Assert.assertEquals("Number of nodes != 4", 4, heap.getNumberOfNodes());

		FibonacciHeapNode<String> maxNode25 = heap.removeMax();
		Assert.assertEquals("Key is != 25", 25, maxNode25.getKey(), EPSILON);
		Assert.assertEquals("Number of nodes != 3", 3, heap.getNumberOfNodes());

		FibonacciHeapNode<String> maxNode20 = heap.removeMax();
		Assert.assertEquals("Key is != 20", 20, maxNode20.getKey(), EPSILON);
		Assert.assertEquals("Number of nodes != 2", 2, heap.getNumberOfNodes());

		FibonacciHeapNode<String> maxNode15 = heap.removeMax();
		Assert.assertEquals("Key is != 15", 15, maxNode15.getKey(), EPSILON);
		Assert.assertEquals("Number of nodes != 1", 1, heap.getNumberOfNodes());

		FibonacciHeapNode<String> maxNode5 = heap.removeMax();
		Assert.assertEquals("Key is != 5", 5, maxNode5.getKey(), EPSILON);
		Assert.assertEquals("Heap is not empty", true, heap.isEmpty());
	}

	/**
	 * Tests inserting and removing from the heap.
	 */
	@Test
	void InsertAndRemoveTest2()
	{
		MaxFibonacciHeap<String> heap = new MaxFibonacciHeap<String>();
		FibonacciHeapNode<String> node21 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node20 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node19 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node18 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node17 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node16 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node15 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node14 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node13 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node12 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node11 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node10 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node9 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node8 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node7 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node6 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node5 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node25 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node30 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node28 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node100 = new FibonacciHeapNode<String>();
		heap.insert(node21, 21);
		heap.insert(node20, 20);
		heap.insert(node19, 19);
		heap.insert(node18, 18);
		heap.insert(node17, 17);
		heap.insert(node16, 16);
		heap.insert(node15, 15);
		heap.insert(node14, 14);
		heap.insert(node13, 13);
		heap.insert(node12, 12);
		heap.insert(node11, 11);
		heap.insert(node10, 10);
		heap.insert(node9, 9);

		Assert.assertEquals("Key is != 21", 21, heap.removeMax().getKey(), EPSILON);
		Assert.assertEquals("Number of nodes != 12", 12, heap.getNumberOfNodes());
		Assert.assertEquals("Degree of 16 != 3", 3, node16.getDegree());

		Assert.assertEquals("Key is != 20", 20, heap.removeMax().getKey(), EPSILON);
		Assert.assertEquals("Number of nodes != 11", 11, heap.getNumberOfNodes());

		Assert.assertEquals("Key is != 19", 19, heap.removeMax().getKey(), EPSILON);
		Assert.assertEquals("Number of nodes != 10", 10, heap.getNumberOfNodes());

		Assert.assertEquals("Key is != 18", 18, heap.removeMax().getKey(), EPSILON);
		Assert.assertEquals("Number of nodes != 9", 9, heap.getNumberOfNodes());

		Assert.assertEquals("Key is != 17", 17, heap.removeMax().getKey(), EPSILON);
		Assert.assertEquals("Number of nodes != 8", 8, heap.getNumberOfNodes());

		heap.insert(node8, 8);
		heap.insert(node7, 7);
		heap.insert(node6, 6);
		heap.insert(node5, 5);
		Assert.assertEquals("Number of nodes != 12", 12, heap.getNumberOfNodes());

		Assert.assertEquals("Key is != 16", 16, heap.removeMax().getKey(), EPSILON);
		Assert.assertEquals("Number of nodes != 11", 11, heap.getNumberOfNodes());

		heap.insert(node25, 25);
		heap.insert(node30, 30);
		heap.insert(node28, 28);
		heap.insert(node100, 100);
		Assert.assertEquals("Number of nodes != 15", 15, heap.getNumberOfNodes());

		Assert.assertEquals("Key is != 100", 100, heap.removeMax().getKey(), EPSILON);
		Assert.assertEquals("Key is != 30", 30, heap.removeMax().getKey(), EPSILON);
		Assert.assertEquals("Key is != 28", 28, heap.removeMax().getKey(), EPSILON);
		Assert.assertEquals("Key is != 25", 25, heap.removeMax().getKey(), EPSILON);
		Assert.assertEquals("Key is != 15", 15, heap.removeMax().getKey(), EPSILON);
		Assert.assertEquals("Key is != 14", 14, heap.removeMax().getKey(), EPSILON);
		Assert.assertEquals("Key is != 13", 13, heap.removeMax().getKey(), EPSILON);
		Assert.assertEquals("Key is != 12", 12, heap.removeMax().getKey(), EPSILON);
		Assert.assertEquals("Key is != 11", 11, heap.removeMax().getKey(), EPSILON);
		Assert.assertEquals("Key is != 10", 10, heap.removeMax().getKey(), EPSILON);
		Assert.assertEquals("Key is != 9", 9, heap.removeMax().getKey(), EPSILON);
		Assert.assertEquals("Key is != 8", 8, heap.removeMax().getKey(), EPSILON);
		Assert.assertEquals("Key is != 7", 7, heap.removeMax().getKey(), EPSILON);
		Assert.assertEquals("Key is != 6", 6, heap.removeMax().getKey(), EPSILON);
		Assert.assertEquals("Key is != 5", 5, heap.removeMax().getKey(), EPSILON);
		Assert.assertEquals("Heap is not empty", true, heap.isEmpty());
	}

	/**
	 * Tests inserting and removing from the heap.
	 */
	@Test
	void InsertAndRemoveTest3()
	{
		MaxFibonacciHeap<String> heap = new MaxFibonacciHeap<String>();
		Integer[] keyArray = new Integer[]
		{
				4, 9, 9, 5, 2, 9, 4, 3, 4, 0
		};
		for (Integer key : keyArray)
		{
			FibonacciHeapNode<String> node = new FibonacciHeapNode<String>();
			heap.insert(node, key);
		}

		List<Integer> keyList = Arrays.asList(keyArray);
		Collections.sort(keyList, Collections.reverseOrder());
		for (Integer expectedKey : keyList)
		{
			FibonacciHeapNode<String> maxNode = heap.removeMax();
			Assert.assertEquals("Key ain't good", expectedKey, maxNode.getKey(), EPSILON);
		}
		Assert.assertEquals("Heap is not empty", true, heap.isEmpty());
	}

	/**
	 * Tests inserting and removing a large number of items from the heap.
	 * 
	 * Items are inserted with keys in ascending order
	 */
	@Test
	void InsertAndRemoveABunchTest1()
	{
		int numNodes = 10000;
		MaxFibonacciHeap<String> heap = new MaxFibonacciHeap<String>();

		// Insert in ascending order
		for (int index = 0; index < numNodes; ++index)
		{
			FibonacciHeapNode<String> node = new FibonacciHeapNode<String>();
			heap.insert(node, index);
		}

		// Verify key
		for (int index = numNodes - 1; index >= 0; --index)
		{
			Assert.assertEquals("Key ain't good", index, heap.removeMax().getKey(), EPSILON);
		}
		Assert.assertEquals("Heap is not empty", true, heap.isEmpty());
	}

	/**
	 * Tests inserting and removing a large number of items from the heap.
	 * 
	 * Items are inserted with keys in descending order
	 */
	@Test
	void InsertAndRemoveABunchTest2()
	{
		int numNodes = 1000000;
		MaxFibonacciHeap<String> heap = new MaxFibonacciHeap<String>();

		// Insert in descending order
		for (int index = numNodes - 1; index >= 0; --index)
		{
			FibonacciHeapNode<String> node = new FibonacciHeapNode<String>();
			heap.insert(node, index);
		}

		// Verify key
		for (int index = numNodes - 1; index >= 0; --index)
		{
			Assert.assertEquals("Key ain't good", index, heap.removeMax().getKey(), EPSILON);
		}
		Assert.assertEquals("Heap is not empty", true, heap.isEmpty());
	}

	/**
	 * Tests inserting and removing a large number of items from the heap.
	 * 
	 * Items are inserted in a random order. The inserted items are placed in a
	 * list, sorted using the Collections library, and compared with consecutive
	 * calls from the Fibonacci heap.
	 */
	@Test
	void InsertAndRemoveABunchTest3()
	{
		MaxFibonacciHeap<String> heap = new MaxFibonacciHeap<String>();
		Random random = new Random();
		int numNodes = 10000;

		// Generate random numbers
		ArrayList<Integer> randomNumbers = new ArrayList<Integer>(numNodes);
		for (int index = 0; index < numNodes; ++index)
		{
			FibonacciHeapNode<String> node = new FibonacciHeapNode<String>();
			int randomNumber = random.nextInt(numNodes);
			heap.insert(node, randomNumber);
			randomNumbers.add(randomNumber);
		}
		Collections.sort(randomNumbers, Collections.reverseOrder());

		// Verify
		for (Integer expectedKey : randomNumbers)
		{
			FibonacciHeapNode<String> maxNode = heap.removeMax();
			Assert.assertEquals("Key ain't good", expectedKey, maxNode.getKey(), EPSILON);
		}
		Assert.assertEquals("Heap is not empty", true, heap.isEmpty());
	}

	/**
	 * Tests melding two Fibonacci heaps
	 */
	@Test
	public void meldTest()
	{
		MaxFibonacciHeap<String> heap1 = new MaxFibonacciHeap<String>();

		FibonacciHeapNode<String> node1 = new FibonacciHeapNode<String>();
		heap1.insert(node1, 5);
		FibonacciHeapNode<String> node2 = new FibonacciHeapNode<String>();
		heap1.insert(node2, 10);
		FibonacciHeapNode<String> node3 = new FibonacciHeapNode<String>();
		heap1.insert(node3, 20);

		MaxFibonacciHeap<String> heap2 = new MaxFibonacciHeap<String>();
		FibonacciHeapNode<String> node4 = new FibonacciHeapNode<String>();
		heap2.insert(node4, 25);
		FibonacciHeapNode<String> node5 = new FibonacciHeapNode<String>();
		heap2.insert(node5, 15);
		FibonacciHeapNode<String> node6 = new FibonacciHeapNode<String>();
		heap2.insert(node6, 8);

		heap1.meld(heap2);

		Assert.assertEquals("Number of nodes != 6", 6, heap1.getNumberOfNodes());
		Assert.assertEquals("Number of nodes != 6", 6, heap2.getNumberOfNodes());
		Assert.assertEquals("Max Key != 25", 25, heap1.getMax().getKey(), EPSILON);
		Assert.assertEquals("Max Key != 25", 25, heap1.getMax().getKey(), EPSILON);
	}

	/**
	 * Tests removing arbitrary nodes in the Fibonacci heap.
	 * 
	 * This test also checks for updated child cut values and the cascading cut
	 * algorithm
	 */
	@Test
	public void arbitraryRemoveTest1()
	{
		MaxFibonacciHeap<String> heap = new MaxFibonacciHeap<String>();
		FibonacciHeapNode<String> node21 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node20 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node19 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node18 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node17 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node16 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node15 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node14 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node13 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node12 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node11 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node10 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node9 = new FibonacciHeapNode<String>();
		heap.insert(node21, 21);
		heap.insert(node20, 20);
		heap.insert(node19, 19);
		heap.insert(node18, 18);
		heap.insert(node17, 17);
		heap.insert(node16, 16);
		heap.insert(node15, 15);
		heap.insert(node14, 14);
		heap.insert(node13, 13);
		heap.insert(node12, 12);
		heap.insert(node11, 11);
		heap.insert(node10, 10);
		heap.insert(node9, 9);
		Assert.assertEquals("Max Key != 21", 21, heap.removeMax().getKey(), EPSILON);
		Assert.assertEquals("Number of nodes != 12", 12, heap.getNumberOfNodes());
		Assert.assertEquals("Degree of 20 != 2", 2, node20.getDegree());
		Assert.assertEquals("Degree of 16 != 3", 3, node16.getDegree());
		Assert.assertEquals("Degree of 12 != 2", 2, node12.getDegree());

		// 12, 14, and 15 should be child of 16
		// Removing 16 should result in 12, 14, 15, and 20 in the top level list
		heap.remove(node16);
		Assert.assertEquals("Node 20 != 3 siblings", 3, node20.getNumberOfSiblings());

		// 20 should have 18 and 19 as children
		Assert.assertEquals("Node 20 child cut is true", false, node20.getChildCut());
		heap.remove(node18);
		Assert.assertEquals("Node 20 != 1 child", 1, node20.getDegree());
		Assert.assertEquals("Node 20 child cut is false", true, node20.getChildCut());

		// 14 should have child with key 13
		Assert.assertEquals("Node 14 child cut is true", false, node14.getChildCut());
		heap.remove(node13);
		Assert.assertEquals("Node 14 != 0 children", 0, node13.getDegree());
		Assert.assertEquals("Node 14 child cut is false", true, node14.getChildCut());
		Assert.assertEquals("Number of nodes != 9", 9, heap.getNumberOfNodes());

		// Should remove the max
		Assert.assertEquals("Max Key != 20", 20, heap.getMax().getKey(), EPSILON);
		heap.remove(node20);
		Assert.assertEquals("Max Key != 19", 19, heap.getMax().getKey(), EPSILON);
		Assert.assertEquals("Number of nodes != 8", 8, heap.getNumberOfNodes());
	}

	/**
	 * Tests removing arbitrary nodes in the Fibonacci heap.
	 * 
	 * This test also checks for updated child cut values and the cascading cut
	 * algorithm
	 */
	@Test
	public void arbitraryRemoveTest2()
	{
		MaxFibonacciHeap<String> heap = new MaxFibonacciHeap<String>();
		FibonacciHeapNode<String> node21 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node20 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node19 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node18 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node17 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node16 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node15 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node14 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node13 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node12 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node11 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node10 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node9 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node8 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node7 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node6 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node5 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node4 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node3 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node2 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node1 = new FibonacciHeapNode<String>();
		heap.insert(node21, 21);
		heap.insert(node20, 20);
		heap.insert(node19, 19);
		heap.insert(node18, 18);
		heap.insert(node17, 17);
		heap.insert(node16, 16);
		heap.insert(node15, 15);
		heap.insert(node14, 14);
		heap.insert(node13, 13);
		heap.insert(node12, 12);
		heap.insert(node11, 11);
		heap.insert(node10, 10);
		heap.insert(node9, 9);
		heap.insert(node8, 8);
		heap.insert(node7, 7);
		heap.insert(node6, 6);
		heap.insert(node5, 5);
		heap.insert(node4, 4);
		heap.insert(node3, 3);
		heap.insert(node2, 2);
		heap.insert(node1, 1);
		Assert.assertEquals("Number of nodes != 21", 21, heap.getNumberOfNodes());
		heap.remove(node20);
		Assert.assertEquals("Number of nodes != 20", 20, heap.getNumberOfNodes());
		heap.remove(node21);
		Assert.assertEquals("Number of nodes != 19", 19, heap.getNumberOfNodes());
		// @formatter:off
		// 19 ------------------> 16 ---------------->18
		//            /         /    \    \           |
		//        8            12      14    15       17
		//    /   |   \       /  \     |
		//  4     6     7   10   11    13
		// / \    |          |
		// 2  3   5          9
		// |
		// 1		
		// @formatter:on
		Assert.assertEquals("Number of children for 8 != 3", 3, node8.getDegree());
		heap.remove(node7);
		Assert.assertEquals("Number of children for 8 != 2", 2, node8.getDegree());
		Assert.assertEquals("Number of nodes != 18", 18, heap.getNumberOfNodes());
		Assert.assertEquals("Number of siblings != 2", 2, node18.getNumberOfSiblings());

		Assert.assertEquals("Number of children for 4 != 2", 2, node4.getDegree());
		heap.remove(node3);
		Assert.assertEquals("Number of children for 4 != 1", 1, node4.getDegree());
		Assert.assertEquals("Number of nodes != 17", 17, heap.getNumberOfNodes());
		Assert.assertEquals("Number of siblings != 2", 2, node18.getNumberOfSiblings());

		// Cascading cut should occur here. 1, 4, then 8 should be moved to top level
		// list. Hence the top level list should contain 19, 16, 18, 8, 4, and 1
		Assert.assertEquals("Number of children for 4 != 1", 1, node4.getDegree());
		heap.remove(node2);
		Assert.assertEquals("Number of children for 4 != 0", 0, node4.getDegree());
		Assert.assertEquals("Number of children for 8 != 1", 1, node8.getDegree());
		Assert.assertEquals("Number of children for 16 != 3", 3, node16.getDegree());
		Assert.assertEquals("Number of nodes != 16", 16, heap.getNumberOfNodes());
		Assert.assertEquals("Number of siblings != 5", 5, node18.getNumberOfSiblings());
	}

	/**
	 * Tests increasing the key of nodes in the Fibonacci heap.
	 * 
	 * This test also checks for updated child cut values and the cascading cut
	 * algorithm
	 */
	@Test
	public void increaseKeyTest1()
	{
		MaxFibonacciHeap<String> heap = new MaxFibonacciHeap<String>();
		FibonacciHeapNode<String> node20 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node19 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node18 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node17 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node16 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node15 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node14 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node13 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node12 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node11 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node10 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node9 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node8 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node7 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node6 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node5 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node4 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node3 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node2 = new FibonacciHeapNode<String>();
		FibonacciHeapNode<String> node1 = new FibonacciHeapNode<String>();
		heap.insert(node20, 20);
		heap.insert(node19, 19);
		heap.insert(node18, 18);
		heap.insert(node17, 17);
		heap.insert(node16, 16);
		heap.insert(node15, 15);
		heap.insert(node14, 14);
		heap.insert(node13, 13);
		heap.insert(node12, 12);
		heap.insert(node11, 11);
		heap.insert(node10, 10);
		heap.insert(node9, 9);
		heap.insert(node8, 8);
		heap.insert(node7, 7);
		heap.insert(node6, 6);
		heap.insert(node5, 5);
		heap.insert(node4, 4);
		heap.insert(node3, 3);
		heap.insert(node2, 2);
		heap.insert(node1, 1);
		heap.removeMax();
		// @formatter:off
		// 19 ------------------> 16 ---------------->18
		//            /         /    \    \           |
		//        8            12      14    15       17
		//    /   |   \       /  \     |
		//  4     6     7   10   11    13
		// / \    |          |
		// 2  3   5          9
		// |
		// 1		
		Assert.assertEquals("Number of children for 8 != 3", 3, node8.getDegree());
		heap.increaseKey(node7, 13);
		Assert.assertEquals("Number of children for 8 != 2", 2, node8.getDegree());
		Assert.assertEquals("Number of nodes != 19", 19, heap.getNumberOfNodes());
		Assert.assertEquals("Number of siblings != 3", 3, node18.getNumberOfSiblings());
		Assert.assertEquals("Max Key != 20", 20, heap.getMax().getKey(), EPSILON);
		
		Assert.assertEquals("Number of children for 4 != 2", 2, node4.getDegree());
		heap.increaseKey(node3, 22);
		Assert.assertEquals("Number of children for 4 != 1", 1, node4.getDegree());
		Assert.assertEquals("Number of siblings != 4", 4, node18.getNumberOfSiblings());
		Assert.assertEquals("Max Key != 25", 25, heap.getMax().getKey(), EPSILON);

		// Cascading cut should occur here. 1, 4, then 8 should be moved to top level
		// list. The top level list should contain 19, 16, 20, 25 35, 1, 4, and 8
		Assert.assertEquals("Number of children for 4 != 1", 1, node4.getDegree());
		heap.increaseKey(node2, 33);
		Assert.assertEquals("Number of children for 4 != 0", 0, node4.getDegree());
		Assert.assertEquals("Number of children for 8 != 1", 1, node8.getDegree());
		Assert.assertEquals("Number of children for 16 != 3", 3, node16.getDegree());
		Assert.assertEquals("Number of siblings != 7", 7, node18.getNumberOfSiblings());
		Assert.assertEquals("Max Key != 35", 35, heap.getMax().getKey(), EPSILON);
	}
}
