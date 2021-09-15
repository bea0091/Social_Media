package Social_Media;
/* Team name : A-Yo BJ
 * Team Members : Eunbee Na (20383329) & Jaeeun Jung (20198902)
 * CIS 22C Data Structure
 * Nov 07 2020
 */


public class LList<T> implements ListInterface<T>
{

    private Node<T> firstNode; // Reference to first node of chain
    private int numberOfEntries;

    public LList()
    {
    	initializeDataFields();
    }
    
    @Override
    public void add(T newEntry) 
    {
        Node<T> newNode = new Node<T>(newEntry);
        if (isEmpty())
            firstNode = newNode;
        else								// Add to end of non-empty list
        {
            Node<T> lastNode = getNodeAt(numberOfEntries);           
            lastNode.setNextNode(newNode);	// Make last node reference new node
        }
        numberOfEntries++;
    }
    

    @Override
    public void add(int newPosition, T newEntry) 
    {
        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1))
        {
            Node<T> newNode = new Node<T>(newEntry);
            if (newPosition == 1)	// Case 1
            {
                newNode.setNextNode(firstNode);
                firstNode = newNode;
            }
            else					// Case 2 : List is not empty
            {						// and newPosition > 1
                Node<T> nodeBefore = getNodeAt(newPosition - 1);
                Node<T> nodeAfter = nodeBefore.getNextNode();
                newNode.setNextNode(nodeAfter);
                nodeBefore.setNextNode(newNode);
            }
            numberOfEntries++;
        }
        else
            throw new IndexOutOfBoundsException("Illegal position given to add operation.");
    }
    

    @Override
    public T remove(int givenPosition) 
    {
        T result = null; 
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
        {
            assert !isEmpty();
            if (givenPosition == 1)
            {
                result = firstNode.getData();
                firstNode = firstNode.getNextNode();
            }
            else
            {
                Node<T> nodeBefore = getNodeAt(givenPosition - 1);
                Node<T> nodeToRemove = nodeBefore.getNextNode();
                result = nodeToRemove.getData();
                Node<T> nodeAfter = nodeToRemove.getNextNode();
                nodeBefore.setNextNode(nodeAfter);
            }
            numberOfEntries--;
            return result;
        }
        else
            throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
    }
    

    @Override
    public void clear() 
    {
    	initializeDataFields();
    }

    @Override
    public T replace(int givenPosition, T newEntry) 
    {
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
        {
            assert !isEmpty();
            Node<T> desireNode = getNodeAt(givenPosition);
            T originalEntry = desireNode.getData();
            desireNode.setData(newEntry);
            return originalEntry;
        }
        else
            throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
    }

    
    @Override
    public T getEntry(int givenPosition) 
    {
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
        {
            assert !isEmpty();
            return getNodeAt(givenPosition).getData();
        }
        else
            throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
    }

    
    @Override
    public boolean contains(T anEntry) 
    {
        boolean found = false;
        Node<T> currentNode = firstNode;

        while (!found && (currentNode != null))
        {
            if (anEntry.equals(currentNode.getData()))
                found = true;
            else
                currentNode = currentNode.getNextNode();
        }

        return found;
    }

    
    @Override
    public int getLength() 
    {
        return numberOfEntries;
    }

    
    @Override
    public boolean isEmpty() 
    {
        boolean result;
        if (numberOfEntries == 0)
        {
            assert  firstNode == null;
            result = true;
        }
        else
        {
            assert  firstNode != null;
            result = false;
        }
        return result;
    }

    
    @Override
    public T[] toArray() 
    {
        @SuppressWarnings("unchecked")
                T[] result = (T[])new Object[numberOfEntries];
        int index = 0;
        Node<T> currentNode = firstNode;
        while ((index < numberOfEntries) && (currentNode != null))
        {
            result[index] = currentNode.getData();
            currentNode = currentNode.getNextNode();
            index++;
        }
        return result;
    }


    // Initializes the class's data fields to indicate an empty list.
    private void initializeDataFields()
    {
        firstNode = null;
        numberOfEntries = 0;
    } // end initializeDataFields


    // Returns a reference to the node at a given position.
    // Precondition: The chain is not empty;
    // 1 <= givenPosition <= numberOfEntries.
    private Node<T> getNodeAt(int givenPosition)
    {
        assert !isEmpty() && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
        Node currentNode = firstNode;

    // Traverse the chain to locate the desired node
    // (skipped if givenPosition is 1)
        for (int counter = 1; counter < givenPosition; counter++)
            currentNode = currentNode.getNextNode();
        assert currentNode != null;

        return currentNode;
    } // end getNodeAt
    

    private class Node <T>
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
}