//Shayan Salsabilian
//ssalsabi
//3/3/19
//12B
//Test code for Queue.java
//QueueTest.java
public class QueueTest
{
	public static void main (String[]args)
	{
		Queue A = new Queue ();//creating a new Queue to test on
		System.out.println ("Beginning isEmpty() Tests");
		System.out.println
			("	Does isEmpty return correctly when we have nothing in the Queue?");
		boolean check = A.isEmpty ();//running is empty when we have an empty Queue
		if (check == true) //if we do have an empty queue print passed
		{
			System.out.println ("		Passed");
		}
		else
		{
			System.out.println ("		Failed");
		}
		A.enqueue (1);//inserting into the queue
		check=A.isEmpty();//checking if we have an empty queue when we know we dont
		System.out.println
			("	Does isEmpty return correctly when we have something in the Queue?");
		if (check == false)//if we dont have an empty queue print passed
		{
			System.out.println ("		Passed");
		}
		else
		{
			System.out.println ("		Failed");
		}
		System.out.println ("Beginning length() Tests:");
		A.enqueue (4);//inserting 4 more items into the list
		A.enqueue (7);
		A.enqueue (12);
		A.enqueue (2);
		System.out.println
			("	Does length () return the correct size with 5 things in the list");
		int size = A.length ();
		if (size == 5)//checking if we have a size 5 queue
		{
			System.out.println ("		Passed");
		}
		else
		{
			System.out.println ("		Failed");
		}
		A.dequeueAll ();//Empty the entire list
		System.out.println
			("	Does length() return the correct size with nothing in the list");
		size = A.length ();//getting the size of the queue
		if (size == 0)//checking if the size is 0
		{
			System.out.println ("		Passed");
		}
		else
		{
			System.out.println ("		Failed");
		}
		System.out.println ("Beginning enqueue() Tests:");
		A.enqueue (86);//inserting to the list
		System.out.println ("	Does enqueue() enqueue one item correctly?");
		Object check1 = A.peek ();//peeking to see if the right value is at the front of the queue
		Object response = 86;//the number that should be at the front of the queue
		if (check1 == response)//check if they match, if they do enqueue is working right
		{
			System.out.println ("		Passed");
		}
		else
		{
			System.out.println ("		Failed");
		}
		A.enqueue(22);//enqueueing multiple items
		A.enqueue ("The Bolshevliks are coming");
		A.enqueue ("Foolish mortals, you can't stop me!!");
		A.enqueue ("The coding god frowns upon you :(");
		System.out.println
			("		Does enqueue() enqueue multiple items correctly?");
		check1 = A.peek ();//peeking to see if we still have the right value at the front
		if (check1 == response)//check if they match, if they do enqueue is working right
		{
			System.out.println ("		Passed");
		}
		else
		{
			System.out.println ("		Failed");
		}
		System.out.println ("Beginning dequeue() Tests:");
		Object check2= A.dequeue();//removing the first item in the queue (since thats what queue's do)
		System.out.println ("	Does dequeue() dequeue an item correctly?");
		if (check2 == response)//check if they match, if they do dequeue is working correctly
		{
			System.out.println ("		Passed");
		}
		else
		{
			System.out.println ("		Failed");
		}
		check2=A.dequeue();//removing the first item in the queue (since thats what queue's do)
		response=22;//should match this
		System.out.println("	Does dequeue() dequeue an item correctly?");
		if(check2==response){//check if they match, if they do dequeue is working correctly
			System.out.println("		Passed");
		}else{
			System.out.println("		Failed");
		}
		A.dequeueAll();//empty the queue
		/* System.out.println("	Does dequeue() produce an error when we have an empty queue?");
		   A.dequeue();*/
		System.out.println("Beginning peek() Tests:");
		System.out.println("		Does peek() grab the right item when theres only one item in the list?");
		check1=19;//create something to put in our now empty queue
		A.enqueue(check1);//inserting into our empty queue
		check2=A.peek();//peeking to see the front of the queue
		if(check1==check2){//see if it matches what we just inserted, if it does peek() is working correctly
			System.out.println("		Passed");
		}else{
			System.out.println("		Failed");
		}
		A.enqueue(43);//inserting two more things into the queue
		A.enqueue(54);
		System.out.println("	Does peek() still peek correctly when theres multiple items in the list?");
		if(check1==check2){//check if the first item in the queue still matches what we inserted, if it does peek() is working correctly
			System.out.println("		Passed");
		}else{
			System.out.println("		Failed");
		}
		System.out.println("Beginning dequeueAll() Tests:");
		A.dequeueAll();//empty the queue
		size=A.length();//check the length
		System.out.println("		Does dequeueAll() have the right size of zero when its done?");
		if(size==0){//if the length is now zero after emptying the queue the queue is working correctly
			System.out.println("		Passed");
		}else{
			System.out.println("		Failed");
		}
		/*System.out.println(" Does dequeue() produce an error when we have an empty queue?");
		  A.dequeueAll();*/
		System.out.println("Beginning toString() Tests:");
		A.enqueue("ABC as easy as 123");//inserting a bunch of strings into the queue
		A.enqueue("Don't Stop me Now!!!");
		A.enqueue("I Object Strongly to this Programming Assignment");
		A.enqueue("Get it, cause were working with Objects? I'll see myself out");
		System.out.println("The following test should print:");
		System.out.print("ABC as easy as 123");
		System.out.print(" Don't Stop me Now!!!");
		System.out.print(" I Object Strongly to this Programming Assignment");
		System.out.print(" Get it, cause were working with Objects? I'll see myself out");
		System.out.println("\nOutput:");
		System.out.println(A);//printing the queue out, if I wrote my toString() function correctly this should print out normally
	}
}
