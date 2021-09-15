/* Eunbee Na (20383329)
 * CIS 22C Data Structure
 * Oct 16 2020
 */


package Social_Media;


import java.util.LinkedList;


public final class LinkedStack<T> implements StackInterface<T>
{

	private Node topNode; // References the first node in the chain
	
	
	public LinkedStack()
	{
		topNode = null;
	}
	
	
	public class Node <T>
	{
	    private T   data;
	    private Node<T> next;

	    private Node(T dataPortion)
	    {
	        this(dataPortion, null);
	    }

	    private Node(T dataPortion, Node<T> nextNode)
	    {
	        data = dataPortion;
	        next = nextNode;
	    }

	    private  T getData()
	    {
	        return data;
	    }

	    private void setData(T newData)
	    {
	        data = newData;
	    }

	    private Node<T> getNextNode()
	    {
	        return next;
	    }

	    private void setNextNode(Node<T> nextNode)
	    {
	        next = nextNode;
	    }
	
	}
	

	@Override
	public void push(T newEntry) 
	{
		Node newNode = new Node(newEntry, topNode);
		topNode = newNode;
		
	}

	
	@Override
	public T pop() {
		T top = peek();
		assert topNode != null;
		topNode = topNode.getNextNode();
		return top;
	}
	

	@Override
	public T peek() {
		if(isEmpty())
			throw new EmptyStackException();
		else
			return (T) topNode.getData();
	}
	

	@Override
	public boolean isEmpty() {
		return topNode == null;
	}
	
	

	@Override
	public void clear() {
		topNode = null;
	}// end LinkedStack
}