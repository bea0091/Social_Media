/* Eunbee Na (20383329)
 * CIS 22C Data Structure
 * Oct 23 2020
 */


package Social_Media;

public final class LinkedQueue<T> implements QueueInterface<T> {
	
	private Node firstNode;
	private Node lastNode;
	
	// default constructor
	public LinkedQueue() {
		firstNode = null;
		lastNode = null;
	} 


	public class Node <T> {
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
	public void enqueue(T newEntry) {
		Node newNode = new Node (newEntry, null);
		
		if (isEmpty())
			firstNode = newNode;
		else
			lastNode.setNextNode(newNode);
		
		lastNode = newNode;
	}
	

	@Override
	public T dequeue() {
		T front = getFront();
		assert firstNode != null;
		firstNode.setData(null);
		firstNode = firstNode.getNextNode();
		
		// A queue of one entry
		if (firstNode == null)
			lastNode = null;
		
		return front;
	}

	
	@Override
	public T getFront() {
		if (isEmpty())
			throw new EmptyQueueException();
		else
			return (T) firstNode.getData();
	}

	
	@Override
	public boolean isEmpty() {
		return (firstNode == null) && (lastNode == null);
	}

	
	@Override
	public void clear() {
		firstNode = null;
		lastNode = null;
	}
	
}

