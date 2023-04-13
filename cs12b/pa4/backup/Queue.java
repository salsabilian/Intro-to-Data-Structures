//-----------------------------------------------------------------------------
//   Queue.java
//   Implementation file for Queue ADT
//   Shayan Salsabilian
//   ssalsabi
//   2/20/18
//   12B
//-----------------------------------------------------------------------------

public class Queue		//implements QueueInterface
{

	// NodeObj
	private class Node
	{
		Object item;
		Node next;

		Node (Object x)
		{
			item = x;
			next = null;
		}
	}

	// Fields for the Queue class
	private Node front;
	private Node back;
	private int numItems;

	//Queue()
	//constructor for the Queue class
	public Queue ()
	{
		front = null;
		numItems = 0;
		back = null;
	}
	//isEmpty()
	//pre:none
	//return: returns true if the Queue is empty, false otherwise
	public boolean isEmpty ()
	{
		return (numItems == 0);
	}
	//length()
	//pre:none
	//return: returns the number of elements in this Queue
	public int length ()
	{
		return numItems;
	}
	//enqueue()
	//adds x to back of the Queue
	//pre:none
	//return: none
	public void enqueue (Object x)
	{
		if (numItems == 0)
		{
			back = new Node (x);
			front = back;
		}
		else
		{
			back.next = new Node (x);
			back=back.next;
		}
		numItems++;
	}
	//dequeue()
	//deletes and returns item from from front of the Queue
	//pre: !isEmpty()
	//return: returns the item deleted
	public Object dequeue () throws QueueEmptyException
	{
		if (numItems == 0)
		{				//if the number of items is 0 then we cant delete so we throw an error
			throw new QueueEmptyException ("cannot dequeue() empty queue");
		}
		Object dequeued = front.item;	//save the item in the head for returning
		front = front.next;		//increment the head removing that first item
		numItems--;			//reduce the number of items
		return dequeued;		//return the item deleted
	}
	//peek()
	//pre: !isEmpty()
	//return: returns item at the front of the queue
	public Object peek () throws QueueEmptyException
	{
		if (numItems == 0)
		{				//if the number of items is 0 then we cant peek so we throw an error
			throw new QueueEmptyException ("cannot peek() empty queue");
		}
		Object head = front.item;	//get the first item in the head for returning
		return head;		//return the front of the queue
	}
	//dequeueAll()
	//sets this Queue to the empty state
	//pre: !isEmpty()
	//return: none
	public void dequeueAll () throws QueueEmptyException
	{
		if (numItems == 0)
		{				//when we have 0 items throw an error because you cant empty an empty queue
			throw new QueueEmptyException ("cannot dequeueAll() empty queue");
		}
		front = null;		//lose the pointer
		back = null;
		numItems = 0;		//set the numItems to 0
	}
	//toString()
	//overrides Object's to string method
	//pre:none
	//return a string holding all the objects in the queue printed to stdout
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ();
		for (Node N = front; N != null; N = N.next)
		{				//go until we hit the end of the linked list
			sb.append (N.item).append (" ");	//make the string on each line to be this
		}
		return new String (sb);	//return the string 
	}
}
