//Shayan Salsabilian
//ssalsabi
//2/13/19
//12B
//Creates a linked list called dictionary and creates basic functions for that linked list
//Dictionary.java
public class Dictionary implements DictionaryInterface
{
	class Node
	{
		String key;			//created two string classes to hold the key value
		String value;
		Node next;			//then create a next node to continue the list

		Node (String x, String y)	//what needs to be placed when creating a new node
		{
			key = x;			//the key will be the x when creating a new node
			value = y;		//the value will be the y when creating a new node
			next = null;		//and the next item will be null till we connect another node
		}
	}

	// Fields for the IntegerList class
	private Node head;		// reference to first Node in List
	private int numItems;		// number of items in this IntegerList

	//IntegerList()
	// constructor for the IntegerList class
	public Dictionary ()
	{
		head = null;		//the class dictionary starts with a empty node with 0 items
		numItems = 0;
	}


	// find() (helper function)
	// returns a reference to the Node at position index in the Dictionary
	private Node find (int index)
	{
		Node N = head;
		for (int i = 1; i < index; i++)
		{				//keep going till we reach the index were looking for
			N = N.next;
		}
		return N;
	}

	// isEmpty() (ADT function)
	// pre: none
	// post: returns true if this Dictionary is empty, false otherwise
	public boolean isEmpty ()
	{
		return (numItems == 0);	//conditional with when numItems is 0
	}

	// size() (ADT function)
	// pre: none
	// post: returns the number of elements in this Dictionary
	public int size ()
	{
		return numItems;		//return the numItems that is the size
	}

	// insert()  (ADT function)
	// pre: key
	// post: returns value at the key in this Dictionary
	public String lookup (String key)
	{
		Node N = head;		//have a Node point at the front of the list
		for (int i = 1; i <= numItems; i++)
		{				//go through each item in the Dictionary till we reach the max number of items
			if ((N.key.compareTo (key)) == 0)	//compare the key to the key in each of those parts of the Dictionary
			{			//when one matches up that means were at the proper key in the Dictionary
				return N.value;	//then we can return the value because were at the proper key
			}
			N = N.next;		// if the key doesnt match up to that part of the Dictionary, go to the next part of the dictionary
		}
		return null;		//if we have gone through the entire list and havent found it return null
	}

	// findIndex (helper function)
	// finds the position of the key within the Dictionary
	// pre: key
	// post: returns an int containing how far away we are from the front of the list
	private int findIndex (String key)
	{
		int count = 0;		//start the count at zero
		Node N = head;		//start the Node at the front of the list
		while (count <= numItems)
		{				//go through the entire Dictionary
			if ((N.key.compareTo (key)) == 0)	//once we find a part of the Dictionary that matches the key 
			{
				count++;		//increment the count (since lists start at 1)
				return count;	//then return the count
			}
			count++;		//otherwise increment the count and go to the next key in the Dictionary
			N = N.next;
		}
		return 0;			//if we dont find the key in the list just return a 0, cant be null because int return
	}
	// insert () (ADT function)
	// inserts new key and value into the dictionary at the end
	// pre: key, value
	// post: none
	public void insert (String key, String value) throws DuplicateKeyException
	{
		if ((lookup (key)) != null)	//looks through the list and makes sure the key doesnt already exist, if it does throw a duplicate key exception
		{
			throw new DuplicateKeyException ("cannot insert duplicate keys");
		}
		if (numItems == 0)		//if we are inserting the very first item in the list
		{
			Node N = new Node (key, value);	// create a new node with a key and value
			head = N;		//make that new node be the head of the Dictionary
		}
		else
		{
			Node P = find(numItems); //find were we are in the dictionary and return that node
			Node N=new Node(key, value);
			//P.next = new Node (key, value);	//make that next node a new node with the key and value needed
			N.next= P.next;
			P.next= N;
			N = N.next;		//then move to the point in the dictionary making it the new end of the list
		}
		numItems++;			//increment the number of items
	}

	// delete() (ADT function)
	// delete node from there position in the Dictionary using the key to determine where that is
	// pre: key
	// post: none
	Node findKey(String key){//didn't use but in case someone else did
		Node R=head;
		while(R.next!=null){
			if((R.next.key.compareTo(key))==0){
				return R; 
			}
			R=R.next; 
		}
		return R;
	}

	public void delete (String key) throws KeyNotFoundException
	{
		if ((lookup (key)) == null)	//check to make sure the key exists
		{
			throw new KeyNotFoundException ("cannot delete non-existent key");	//if it doesnt print an error
		}
		int count = findIndex (key);	//find the position of the key in the dictionary
		if (count==1)		//if we are in position 1 in the dictionary
		{
			head = head.next;	//just move the head to position 2 in the dictionary losing the pointer to the start of the dictionary
		}
		else
		{
			Node P = find(count-1);	//find the node at the position right before the key
			P.next = P.next.next;	//change the node that contains the key, so that it points to the next item after the key (ie N.next since N points at the key)
			
		}
		numItems--;			//decrement the number of items
	}

	// makeEmpty() (ADT function)
	// pre: none
	// post: isEmpty()
	public void makeEmpty ()
	{
		head = null;		//lose the pointer that contains heads
		numItems = 0;		//make the number of items equal to 0
	}

	// toString()
	// pre: none
	// post:  prints current state to stdout
	// Overrides Object's toString() method
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ();
		Node N = head;		//start from the head 
		for (; N != null; N = N.next)
		{				//go through the dictionary
			sb.append (N.key).append (" ").append (N.value).append ("\n");	//make each line have the key with a space then the value followed by the newline
		}
		return new String (sb);	//return the string
	}

}
