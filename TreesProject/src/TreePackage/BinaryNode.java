package TreePackage;

public class BinaryNode<T> implements BinaryNodeInterface<T> {
    private T data;
    private BinaryNode<T> leftChild;
    private BinaryNode<T> rightChild;
	
    public BinaryNode()
    {
	this(null); //call next constructor
    } //end of default constructor
	
    public BinaryNode(T dataPortion)
    {
	this(dataPortion, null, null);
    }
	
    public BinaryNode(T dataPortion, BinaryNode<T> newLeftChild, 
				     BinaryNode<T> newRightChild)
    {
        data = dataPortion;
	leftChild = newLeftChild;
	rightChild = newRightChild;
    } //end constructor
	
    public T getData()
    {
	return data;
    } //end getData
	
    public void setData(T newData)
    {
	data = newData;
    } //end setData
	
    public BinaryNode<T> getLeftChild()
    {
	return leftChild;
    } //end getLeftChild
	
    public void setLeftChild(BinaryNodeInterface<T> newLeftChild)
    {
	leftChild = (BinaryNode<T>)newLeftChild;
    } //end setLeftChild
	
    public boolean hasLeftChild()
    {
	return leftChild!=null;
    } //end hasLeftChild
	
    public boolean isLeaf()
    {
	return (leftChild == null) && (rightChild == null);
    } //end isLeaf
	
    public void setRightChild(BinaryNodeInterface<T> newRightChild)
    {
	rightChild = (BinaryNode<T>)newRightChild;
    } //end setRightChild
    
    public BinaryNode<T> getRightChild()
    {
        return rightChild;
    }
	
    public boolean hasRightChild()
	{
		return rightChild!=null;
	} //end hasRightChild
	
    public BinaryNode<T> copy()
    {
        BinaryNode<T> newRoot = new BinaryNode<>(data);
	if (leftChild!=null)
            newRoot.setLeftChild(leftChild.copy());
	if (rightChild!=null)
            newRoot.setRightChild(rightChild.copy());
	return newRoot;
    } // end copy
	
    public T getLeftmostData()
    {
        if(leftChild==null)
            return data;
	else
            return leftChild.getLeftmostData();
    } //end getLeftMostData
	
    public T getRightmostData()
    {
        if(rightChild == null)
            return data;
	else
            return rightChild.getRightmostData();
    } //end getRightMostData

    @Override
    public int getHeight()
    {
        return getHeight(this); // call private getHeight
    } //end getHeight	
    
    private int getHeight(BinaryNode<T> node)
    {
        int height = 0;
        if (node != null)
            height = 1 + Math.max(getHeight(node.getLeftChild()),getHeight(node.getRightChild()));
        return height;
    } //end getHeight
    
    @Override
    public int getNumberOfNodes()
    {
        int leftNumber = 0;
	int rightNumber = 0;
        if(leftChild!=null)
            leftNumber = leftChild.getNumberOfNodes();
	if(rightChild!=null)
            rightNumber = rightChild.getNumberOfNodes();
	return 1 + leftNumber + rightNumber;
    } // end getNumberOfNodes
} //BinaryNode