//Shayan Salsabilian
//ssalsabi
//3/2/19
//12B
//Create a Linked List in a generic data type
//List.java
@SuppressWarnings("overrides")
public class List<T> implements ListInterface<T>{//declare the list generic and implement to the list interface
	private class Node{//create a Node private class
		T item;//create a generic item
		Node next;//create a next Node to extend the list

		Node(T x){//place an item whenever you make a new node
			item=x;
			next=null;
		}
	}

	private Node head;//create a head to start the beginning of the list
	private int numItems;//keep a number of items tracker

	public List(){ //make a List called List
		head=null;//when we first start the list the head is null
		numItems=0;//and the number of items is 0
	}
	private Node find(int index){//go through the entire list from the head and find the Node at the index your looking for
		Node N=head;
		for(int i=1; i<index;i++){
			N=N.next;
		}
		return N;
	}

	public boolean isEmpty(){//check the numItems to determine if the list is empty
		return(numItems==0);
	}

	public int size(){//return the numItems which would indicate the size
		return numItems;
	}

	public T get(int index) throws ListIndexOutOfBoundsException{//find the value at a specific index in the list
		if(index<1||index>numItems){//make sure were actually within the list
			throw new ListIndexOutOfBoundsException("get(): invalid index: " + index);
		}
		Node N=find(index);//find the Node where the index we want is
		return N.item;//return that item
	}
	public void add(int index, T newItem) throws ListIndexOutOfBoundsException {//add a value at the list
		if(index<1||index>numItems+1){//make sure where actually in the list
			throw new ListIndexOutOfBoundsException("add(): invalid index: "+index);	
		}
		if(index==1){//if were at the start of the list
			Node N=new Node(newItem);//create a new node
			N.next=head;//make the head the next item in the node
			head=N;//then make the head equal the new node
		}else{
			Node P=find(index-1);//find the item Node where we want to add
			Node C=P.next;//make Node C equal everything after the Node we want to add to
			P.next=new Node(newItem);//add the item at the position index needed
			P=P.next;//move p forward so that item is officially part of the list
			P.next=C;//then make the next item equal everything after that Node (ie Node C)
		}
		numItems++;//increment the number of items
	}
	public void remove(int index) throws ListIndexOutOfBoundsException{//remove an item from the list
		if(index<1||index>numItems){//make sure where actually in the list
			throw new ListIndexOutOfBoundsException("remove(): invalid index: "+index);
		}
		if(index==1){//if were at the start of the list
			Node N=head;//make Node N equal the head
			head=head.next;//move the head to the next item
			N.next=null;//make everything after the head equal null in Node N so we only have the item we removed
		}else{
			Node P=find(index-1);//find the item Node where we want to remove
			Node N=P.next;//have Node N equal everything after the item we want to remove
			P.next=N.next;//make P.next equal everything right after we want to remove therefore we would get rid of the item we want to remove
			N.next=null;//then set the next item to null in Node N, therefore saving the value we removed
		}
		numItems--;//decrement the number of items
	}
	public void removeAll(){//emptying the list
		head=null;//set the head to null therefore losing the list to the garbage collector
		numItems=0;//set the number of items to 0
	}
	public String toString(){
		StringBuffer sb=new StringBuffer();
		Node N=head;//start at the head of the list
		for(; N!=null;N=N.next){//go through the entire list stringing everything together as we go 
			sb.append(N.item).append(" ");
		}
		return new String(sb);//then print the entire new string stdout
	}
	@SuppressWarnings("unchecked")//SuppressWarning
		public boolean equals(Object rhs){
			boolean eq=false;//start the boolean as false
			List<T> R=null;//create a dummy list
			if(this.getClass()== rhs.getClass()){//make sure that both have the same type 
				R=(List<T>) rhs;//fill the dummy list with whats on the right hand side
				eq=(this.head.equals(R.head));//check to see if the ehad matches
			}
			return eq;//return the boolean                                                                                                                                                                                                                          
		}
}
