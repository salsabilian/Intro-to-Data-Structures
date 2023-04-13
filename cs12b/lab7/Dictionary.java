//Shayan Salsabilian
//ssalsabi
//3/10/19
//12B
//implementation file for DictionaryInterface.java
//Dictionary.java
import java.io.*;
import java.util.Scanner;
public class Dictionary implements DictionaryInterface{
	private class Node{//produce the private class node with a left and right child as well as a string key and value
		String key;
		String value;
		Node left;
		Node right;
		Node(String k, String v){//left and right child are originially set to null and the string key and value are passed in by reference
			key=k;
			value=v;
			left=null;
			right=null;
		}
	}
	private Node root;//will be the start of the binary tree
	private int numPairs;//will keep track of how large are binary tree is
	public Dictionary(){//produces the Dictionary binary tree with root empty to start with and the number of pairs 0 to start since theres nothing in it at first
		root=null;
		numPairs=0;
	}


	private Node findKey(Node R, String k){//recursively looks through the entire binary tree till we find the node were looking for
		if(R==null||((k.compareTo(R.key))==0))//our base case either we have searched through our entire tree and found nothing or we have found the key either way we return the node where we are.
			return R;
		if((k.compareTo(R.key))<0)//if the key that we currently have is less than the original key will go left since lesser values are to the left in a binary tree
			return findKey(R.left, k);
		else//otherwise go right, since larger values are to the right of a binary tree
			return findKey(R.right, k);
	}
	private Node findParent(Node N, Node R){//finds the parent of N in the subtree root R
		Node P=null;
		if(N!=R){//if the parent of N is the root R then dont do anything and return a null
			P=R;//otherwise have P=R
			while(P.left!=N && P.right!=N){//while the left and right are not N 
				if((N.key.compareTo(P.key))<0)//if its less than N go left
					P=P.left;
				else//otherwise go right
					P=P.right;
			}
		}
		return P;//when N is finally found to be to the left or right of P, then return P cause then we have found the parent
	}
	private Node findLeftmost(Node R){//go through the entire tree and find the leftmost element
		Node L=R;
		if(L!=null) for(; L.left!=null; L=L.left);//until we reach a null keep going left
		return L;//then return L
	}
	private void printInOrder(Node R, StringBuffer sb){//print to the elements in order from whatever Node we want to, pass the stringBUffer in by reference so it saves the string each time
		if(R!=null){//if the root given exists
			printInOrder(R.left, sb);//go left recursively first
			sb.append(R.key).append(" ").append(R.value).append("\n");//add all the keys and values
			printInOrder(R.right, sb);//go right recursively
		}
	}
	void deleteAll(Node N){//empty the binary Tree
		if(N!=null){//until we reach null
			deleteAll(N.left);//delete left, then delete right
			deleteAll(N.right);
		}
	}
	public boolean isEmpty(){//check if the Binary Tree is empty
		return(numPairs==0);//by checking the numpairs
	}
	public int size(){//get the size of the binary tree
		return numPairs;//return the numPairs which contain the size
	}
	public String lookup(String key){//look through the BinaryTree 
		Node N;
		N=findKey(root, key);//recursively go through the tree
		return(N==null ? null : N.value);//if we find the value return it 
	}
	public void insert(String key, String value)throws DuplicateKeyException{//inserts into the binary tree at the next available location with the proper position
		Node N,A,B;//produce some nodes
		if(findKey(root, key)!=null){//if we find a value at that same key produce an error
			throw new DuplicateKeyException("Dictionary Error: cannot insert() duplicate key: \\"+key+"\\"+"\n");
		}
		N=new Node(key, value);//create a new node with the key and value
		B=null;
		A=root;//set A to the root
		while(A!=null){//go through the entire binary tree going left and right until we reach the next available position
			B=A;//set B = to the root as well
			if((key.compareTo(A.key))<0) A=A.left;//check if the key is less than the key were at, if it is go left 
			else A=A.right;//otherwise go right
		}
		if(B==null) root=N;//if b is null meaning that the root is empty, then the root is where we enter the new node
		else if ((key.compareTo(B.key))<0) B.left=N;//otherwise if it is less than the root make it the left node
		else B.right=N;//otherwise the right
		numPairs++;//increment the numpairs
	}
	public void delete(String key) throws KeyNotFoundException{//delete from the binary tree
		Node N, P, S;
		N=findKey(root, key);//first find the key
		if(N==null){//if the key doesn't exist throw an error
			throw new KeyNotFoundException("Dictionary Error: cannot delete() non-existent key: \\"+key+"\\"+"\n");
		}
		if(N.left==null && N.right==null){//if we have nothing to the left or right
			if(N==root){//then just empty the root
				root=null;
			}else{//otherwise find the parent
				P=findParent(N, root);
				if(P.right==N)P.right=N.left;//if its empty to the right, then make the right equal null
				else P.left=N.left;//otherwise make the left equal null
			}
		}else if(N.right==null){//if we have nothing to the right
			if(N==root){//if were at the root
				root=N.left;//then move the root to the left
			}else{
				P=findParent(N, root);//otherwise find the parent
				if(P.right==N) P.right =N.right;///if the right equal the same node as the key then make the right null
				else P.left=N.left;//otherwise make the left null
			}

		}else if(N.left==null){//if we have nothing to the left
			if(N==root){
				root=N.right;//if were at the root then the root equals the right 
			}else{
				P=findParent(N,root);//find the parent 
				if(P.right==N) P.right=N.right;//if the parent matches the same node then make the right equal the right
				else P.left=N.right;	//otherwise make the left equal the right
			}
		}else{
			S=findLeftmost(N.right);//otherwise find the leftmost element
			N.key=S.key;//and make the key equal the leftmost elements key
			N.value=S.value;//and make the value equal the leftmost value
			P=findParent(S, N);//find the parent
			if(P.right==S) P.right=S.right;//check if the right is the same as the leftmost element
			else P.left=S.right;//if it is then the left equals the right
		}
		numPairs--;//decrement the number of Pairs
	}
	public void makeEmpty(){//empty the tree
		deleteAll(root);//delete all the items in the tree
		root=null;//lose the pointer
		numPairs=0;//set the number of pairs to 0
	}
	public String toString(){
		StringBuffer sb=new StringBuffer();//create a new stringbuffer
		printInOrder(root, sb);//get the stringbuffer with the keys and values in the order
		return new String(sb);//cast the stringBuffer to a string and return it
	}
}

