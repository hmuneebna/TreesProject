package TreePackage;
import java.util.Iterator;

public class BinarySearchTree<T extends Comparable<? super T>> extends BinaryTree<T>
		implements SearchTreeInterface<T>
{
	public BinarySearchTree()
	{
		super();
	} //end of default constructor
	public BinarySearchTree(T rootEntry)
	{
		super();	
		setRootNode(new BinaryNode<>(rootEntry));
} //end constructor

@Override
	public void setTree(T rootData)
	{
		throw new UnsupportedOperationException();
	} //end setTree
	@Override
	public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(T entry) {
		return getEntry(entry)!=null;
	} //end contains

	@Override
	public T getEntry(T entry) {
		return findEntry(getRootNode(), entry);
	} // end getEntry

	private T findEntry(BinaryNodeInterface<T> rootNode, T entry) {
		T result = null;
		if (rootNode != null) {
			T rootEntry = rootNode.getData();
			if (entry.equals(rootEntry))
				result = rootEntry;
			else if (entry.compareTo(rootEntry) < 0)
				result = findEntry(rootNode.getLeftChild(), entry);
			else
				result = findEntry(rootNode.getRightChild(), entry);
		} // end if
		return result;
	} // end findEntry


@Override
	public T add(T newEntry) {
	T result = null;
	if (isEmpty())
		setRootNode(new BinaryNode<>(newEntry));
	else
		result = addEntry(getRootNode(), newEntry);
	return result;
	}

	private T addEntry(BinaryNodeInterface<T> rootNode, T newEntry) {
		assert rootNode != null;
		T result = null;
		int comparison = newEntry.compareTo(rootNode.getData());
		if (comparison == 0) {
			result = rootNode.getData();
			rootNode.setData(newEntry);
		}
		else if (comparison < 0)
		{
			if (rootNode.hasLeftChild())
				result = addEntry(rootNode.getLeftChild(), newEntry);
			else
				rootNode.setLeftChild(new BinaryNode<>(newEntry));
		}
		else
		{
			assert comparison > 0;
			if (rootNode.hasRightChild())
				result = addEntry(rootNode.getRightChild(), newEntry);
			else
				rootNode.setRightChild(new BinaryNode<>(newEntry));
		} // end if return result;
		return result;
	} // end addEntry


// Removes an entry from the tree rooted at a given node. 
// rootNode is a reference to the root of a tree.
// entry is the object to be removed.
// oldEntry is an object whose data field is null.
// Returns the root node of the resulting tree; if entry matches
//         an entry in the tree, oldEntry's data field is the entry
// that was removed from the tree; otherwise it is null.

	private BinaryNodeInterface<T> removeEntry(BinaryNodeInterface<T> rootNode,
           T entry, ReturnObject oldEntry) {
		if (rootNode != null) {
			T rootData = rootNode.getData();
			int comparison = entry.compareTo(rootData);
			if (comparison == 0) // entry == root entry 
			{
				oldEntry.set(rootData);
				rootNode = removeFromRoot(rootNode);
			}
			else if (comparison < 0) // entry < root entry 
			{
				BinaryNodeInterface<T> leftChild = rootNode.getLeftChild();
				BinaryNodeInterface<T> subtreeRoot = removeEntry(leftChild, entry, oldEntry);
				rootNode.setLeftChild(subtreeRoot);
			}
			else // entry > root entry 
			{
				BinaryNodeInterface<T> rightChild = rootNode.getRightChild();
				rootNode.setRightChild(removeEntry(rightChild, entry, oldEntry));
			} // end if
		} // end if

		return rootNode;

	} // end removeEntry

	private BinaryNodeInterface<T> removeFromRoot(BinaryNodeInterface<T> rootNode) {
		// Case 1: rootNode has two children
		if (rootNode.hasLeftChild() && rootNode.hasRightChild()) {
			// find node with largest entry in left subtree
			BinaryNodeInterface<T> leftSubtreeRoot = rootNode.getLeftChild(); BinaryNodeInterface<T> largestNode = findLargest(leftSubtreeRoot);
			// replace entry in root
			rootNode.setData(largestNode.getData());
			// remove node with largest entry in left subtree
			rootNode.setLeftChild(removeLargest(leftSubtreeRoot));
		} // end if
		// Case 2: rootNode has at most one child
		else if (rootNode.hasRightChild())
			rootNode = rootNode.getRightChild();
		else
			rootNode = rootNode.getLeftChild();
		// Assertion: if rootNode was a leaf, it is now null
	return rootNode; 
	} // end removeEntry

	// Removes the node containing the largest entry in a given tree.
	// rootNode is the root node of the tree.
	// Returns the root node of the revised tree.
	private BinaryNodeInterface<T> removeLargest(BinaryNodeInterface<T> rootNode) {
		if (rootNode.hasRightChild()) {
			BinaryNodeInterface<T> rightChild = rootNode.getRightChild();
			BinaryNodeInterface<T> root = removeLargest(rightChild);
			rootNode.setRightChild(root);
	}
	
		else
			rootNode = rootNode.getLeftChild(); return rootNode;
	} // end removeLargest
	
	
	private BinaryNodeInterface<T> findLargest(BinaryNodeInterface<T> rootNode) {
		if (rootNode.hasRightChild())
			rootNode = findLargest(rootNode.getRightChild());
		return rootNode;
	} // end findLargest
	
	@Override
	public T remove(T entry) {
		ReturnObject oldEntry = new ReturnObject(null);
		BinaryNodeInterface<T> newRoot = removeEntry(getRootNode(), entry, oldEntry);
		return oldEntry.get();
	}
	
	private class ReturnObject implements java.io.Serializable {
		private T item;		
		private ReturnObject(T entry)
	{
			item = entry;
	} // end constructor
	public T get()
	{
		return item;
	} // end get
	
	public void set(T entry)
	{
		item = entry;
	} // end set
  } // end ReturnObject
	
}//end BinarySearchTree}//end BinarySearchTree}//end