package TreePackage;
import java.util.Iterator;
import java.util.NoSuchElementException;
import StackAndQueuePackage.*;

public class BinaryTree<T> implements BinaryTreeInterface<T>
{
	private BinaryNode<T> root;
	
	public BinaryTree()
	{
		root = null;
	} //end default constructor
	
	public BinaryTree(T rootData)
	{
		root = new BinaryNode<>(rootData);
	} // end constructor
	
	public BinaryTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree)
	{
		privateSetTree(rootData, leftTree, rightTree);
	} // end constructor

	public void setTree(T rootData)
	{
		root = new BinaryNode<T>(rootData);
	} //end setTree

	public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
					BinaryTreeInterface<T> rightTree)
	{
		privateSetTree(rootData, (BinaryTree<T>)leftTree,
					 (BinaryTree<T>)rightTree);
	} //end setTree
	
	private void privateSetTree(T rootData, BinaryTree<T> leftTree, 
						BinaryTree<T> rightTree)
	{
		root = new BinaryNode<> (rootData);
		
		if ((leftTree!= null) && !leftTree.isEmpty())
			root.setLeftChild(leftTree.root);
		if ((rightTree!=null) && !rightTree.isEmpty())
		{
			if(rightTree!=leftTree)
				root.setRightChild(rightTree.root);
			else
				root.setRightChild(rightTree.root.copy());
		}
		if ((leftTree!=null) && (leftTree != this))
			leftTree.clear();
		if ((rightTree!=null) && (rightTree!= this))
			rightTree.clear();
	} //end privateSetTree

	public T getRootData()
	{
		T rootData = null;
		if (root!=null)
			rootData = root.getData();
		return rootData;
	} //end getRootData

	protected void setRootData(T rootData)
	{
		root.setData(rootData);
	}//end setRootData

	protected void setRootNode(BinaryNodeInterface<T> rootNode)
	{
		root = (BinaryNode<T>) rootNode;
	} //end setRootNode

	protected BinaryNodeInterface<T> getRootNode()
	{
		return root;
	}//end getRootNode

	public int getNumberOfNodes()
	{
		return root.getNumberOfNodes();
	} //end getNumberOfNodes

	public boolean isEmpty()
	{
		return root == null;
	}

	public void clear()
	{
		root = null;
	}

	public int getHeight() 
	{
		return root.getHeight();
	}// end getHeight

	private int getHeight(BinaryNode<T> node)
	{
		int height = 0;
		if(node!=null)
			height = 1 + Math.max(getHeight(node.getLeftChild()),
					getHeight(node.getRightChild()));
		return height;
	}

	public void inorderTraverse()
	{
		inorderTraverse(root);
	} // end inorderTraverse

	private void inorderTraverse(BinaryNodeInterface<T> node)
	{
		if(node!=null)
		{
			inorderTraverse(node.getLeftChild());
			System.out.print(node.getData()+" ");
			inorderTraverse(node.getRightChild());
			
		} //end if
	}// end inorderTraverse

       public void preorderTraverse(){
           preorderTraverse(root);
       }
       
       private void preorderTraverse(BinaryNodeInterface<T> node)
       {
           if(node!=null)
           {
               System.out.print(node.getData()+" ");
               preorderTraverse(node.getLeftChild());
               preorderTraverse(node.getRightChild());
           } //
       } //
       
       public void postorderTraverse(){
           postorderTraverse(root);
       }
       
       private void postorderTraverse(BinaryNodeInterface<T> node){
           if(node!=null)
           {
               postorderTraverse(node.getLeftChild());
               postorderTraverse(node.getRightChild());
               System.out.print(node.getData()+" ");
           }
       }
       
       
    public Iterator<T> getPreorderIterator()
    {
	return new PreorderIterator();	
    } // end getPreorderIterator

    private class PreorderIterator implements Iterator<T>
    {
	private StackInterface1<BinaryNodeInterface<T>> nodeStack;
		
	public PreorderIterator()
	    {
		nodeStack = new LinkedStack<BinaryNodeInterface<T>>();
		if (root != null)
		    nodeStack.push(root);
	    } // end default constructor
		
	public boolean hasNext() 
	{
	    return !nodeStack.isEmpty();
	} // end hasNext
		
	public T next()
	{
	    BinaryNodeInterface<T> nextNode;
			
	    if (hasNext())
		{
		    nextNode = nodeStack.pop();
		    BinaryNodeInterface<T> leftChild = nextNode.getLeftChild();
		    BinaryNodeInterface<T> rightChild = nextNode.getRightChild();
				
		    // push into stack in reverse order of recursive calls
		    if (rightChild != null)
			nodeStack.push(rightChild);
					
		    if (leftChild != null)
			nodeStack.push(leftChild);
		}
	    else
		{
		    throw new NoSuchElementException();
		}
		
	    return nextNode.getData();
	} // end next
	
	public void remove()
	{
	    throw new UnsupportedOperationException();
	} // end remove
    } // end PreorderIterator
    
    public Iterator<T> getInorderIterator()
    {
	return new InorderIterator();	
    } // end getInorderIterator
    
    private class InorderIterator implements Iterator<T>
    {
	private StackInterface1<BinaryNodeInterface<T>> nodeStack;
	private BinaryNodeInterface<T> currentNode;
	  
	public InorderIterator()
	    {
			nodeStack = new LinkedStack<BinaryNodeInterface<T>>();
			currentNode = root;
	    } // end default constructor
	  
	public boolean hasNext() 
	{
	    return !nodeStack.isEmpty() || (currentNode != null);
	} // end hasNext
	  
	public T next()
	{
	    BinaryNodeInterface<T> nextNode = null;
	    
	    // find leftmost node with no left child
	    while (currentNode != null)
		{
		    nodeStack.push(currentNode);
		    currentNode = currentNode.getLeftChild();
		} // end while
	    
	    // get leftmost node, then move to its right subtree
	    if (!nodeStack.isEmpty())
		{
		    nextNode = nodeStack.pop();
		    assert nextNode != null; // since nodeStack was not empty
		    // before the pop
		    currentNode = nextNode.getRightChild();
		}
	    else
		throw new NoSuchElementException();
	      
	    return nextNode.getData(); 
	} // end next
	  
	public void remove()
	{
	    throw new UnsupportedOperationException();
	} // end remove
    } // end InorderIterator
	
    public Iterator<T> getPostorderIterator()
    {
	return new PostorderIterator();	
    } // end getPostorderIterator
    
    private class PostorderIterator implements Iterator<T>
    {
	private StackInterface1<BinaryNodeInterface<T>> nodeStack;
	private BinaryNodeInterface<T> currentNode;
		
	public PostorderIterator()
	    {
		nodeStack = new LinkedStack<BinaryNodeInterface<T>>();
		currentNode = root;
	    } // end default constructor
		
	public boolean hasNext() 
	{
	    return !nodeStack.isEmpty() || (currentNode != null);
	} // end hasNext
		
	public T next()
	{
	    boolean foundNext = false;
	    BinaryNodeInterface<T> leftChild, rightChild, nextNode = null;
			
	    // find leftmost leaf
	    while (currentNode != null)
		{
		    nodeStack.push(currentNode);
		    leftChild = currentNode.getLeftChild();
		    if (leftChild == null)	
			currentNode = currentNode.getRightChild();
		    else
			currentNode = leftChild;
		} // end while
			
	    // stack is not empty either because we just pushed a node, or
	    // it wasn't empty to begin with since hasNext() is true.
	    // But Iterator specifies an exception for next() in case
	    // hasNext() is false.
			
	    if (!nodeStack.isEmpty())
		{
		    nextNode = nodeStack.pop();
		    // nextNode != null since stack was not empty before pop

		    BinaryNodeInterface<T> parent = nodeStack.peek();
				
		    if (parent != null && nextNode == parent.getLeftChild())
			currentNode = parent.getRightChild();	
		    else
			currentNode = null;
		}
	    else
		{
		    throw new NoSuchElementException();
		} // end if
			
	    return nextNode.getData();
	} // end next

	public void remove()
	{
	    throw new UnsupportedOperationException();
	} // end remove
    } // end PostorderIterator

    public Iterator<T> getLevelOrderIterator()
    {
	return new LevelOrderIterator();	
    } // end getLevelOrderIterator

    private class LevelOrderIterator implements Iterator<T>
    {
	private QueueInterface<BinaryNodeInterface<T>> nodeQueue;
		
	public LevelOrderIterator()
	    {
		nodeQueue = new LinkedQueue<BinaryNodeInterface<T>>();
		if (root != null)
		    nodeQueue.enqueue(root);
	    } // end default constructor
		
	public boolean hasNext() 
	{
	    return !nodeQueue.isEmpty();
	} // end hasNext
		
	public T next()
	{
	    BinaryNodeInterface<T> nextNode;
			
	    if (hasNext())
		{
		    nextNode = nodeQueue.dequeue();
		    BinaryNodeInterface<T> leftChild = nextNode.getLeftChild();
		    BinaryNodeInterface<T> rightChild = nextNode.getRightChild();
				
		    // add to queue in order of recursive calls
		    if (leftChild != null)
			nodeQueue.enqueue(leftChild);

		    if (rightChild != null)
			nodeQueue.enqueue(rightChild);
		}
	    else
		{
		    throw new NoSuchElementException();
		}
		
	    return nextNode.getData();
	} // end next
	
	public void remove()
	{
	    throw new UnsupportedOperationException();
	} // end remove
    } // end LevelOrderIterator
}//end BinaryTree}//end BinaryTree