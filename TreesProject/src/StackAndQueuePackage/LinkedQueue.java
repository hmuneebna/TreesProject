package StackAndQueuePackage;

import java.util.*;


public class LinkedQueue<T> implements QueueInterface<T> {
	private Node firstNode;
	private Node lastNode;
	
	public LinkedQueue()
	{
		firstNode=null;
		lastNode=null;
		
	}
	public void enqueue(T newEntry)
	{
		Node newNode=new Node(newEntry, null);
		if(isEmpty()) 
			firstNode=newNode;
		else 
			lastNode.setNextNode(newNode);
		
		lastNode=newNode;
	}
	public T getFront()
	{
		T front=null;
		if(!isEmpty()) 
			front=firstNode.getData();
		return front;
	}
	public T dequeue()
	{
		T front=null;
		if(!isEmpty())
		{
			front=firstNode.getData();
			firstNode=firstNode.getNextNode();
			if(firstNode==null)
				lastNode=null;
			
		}
		return front;
	}
	public boolean isEmpty()
	{
		return(firstNode==null)&&(lastNode==null);
	}
	public void clear()
	{
		firstNode=null;
		lastNode=null;
	}
	private class Node
	{
		private T data;
		private Node next;
		
		private Node(T dataPortion, Node nextNode)
        {
            data = dataPortion;
            next = nextNode;
        } 
        
        private T getData()
        {
            return data;
        }
        
        private void setData(T newData)
        {
        	data=newData;
        }
        private Node getNextNode()
        {
            return next;
        }
        
        private void setNextNode(Node nextNode)
        {
            next = nextNode;
        } 
	}
	
	
}
