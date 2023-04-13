//Shayan Salsabilian
//ssalsabi
//3/2/19
//12B
//Tests file for List.java
//ListTest.java


public class ListTest{
	public static void main(String[] args){
		System.out.println("Beginning ListTests:");
		List<Integer> A=new List<Integer>();//creating a new list of generic type int
		System.out.println("Beginning isEmpty() Tests:");
		System.out.println("	Does isEmpty return correctly when there is nothing in the list?");
		boolean check=A.isEmpty();//checking to see if the list is empty
		if(check==true){ //if it is print passed
			System.out.println("		Passed");
		}else{
			System.out.println("		Failed");
		}
		System.out.println("	Does isEmpty return correctly when there is one item in the list?");
		A.add(1, 6);//adding an item into the 1st position
		check=A.isEmpty();//checking to see if the list is empty now that it is not
		if(check==false){//return true if the list is now empty
			System.out.println("		Passed");
		}else{
			System.out.println("		Failed");
		}
		System.out.println("Beginning size() Tests:");
		A.removeAll();//emptying the list
		System.out.println("	Does size() return correctly when the list is of size 0");	
		int size=A.size();//get the size of the list now that its empty
		if(size==0){//the size should return 0 with an empty size
			System.out.println("		Passed");
		}else{
			System.out.println("		Failed");
		}
		A.add(1, 23);//add two items in the list
		A.add(2, 32);
		size=A.size();//the size should return 2
		System.out.println("	Does size() return correctly when the list is of size 2");
		if(size==2){//get the size of the list when we have two items in the list
			System.out.println("		Passed");
		}else{
			System.out.println("		Failed");
		}
		System.out.println("Beginning get() Tests:");
		size=A.get(1);//getting the value at index 1
		System.out.println("	Does get() get the correct item from index 1");
		if(size==23){//checking if the value is 23
			System.out.println("		Passed");
		}else{
			System.out.println("		Failed");
		}
		System.out.println("	Does get() get the correct item from index 2");
		size=A.get(2);//getting the value at index 2
		if(size==32){//checking if the is 32
			System.out.println("		Passed");
		}else{
			System.out.println("		Failed");
		}
		/*System.out.println("	The following test should produce an error");
		  size=A.get(-2300);//this index is impossible so should produce an error
		  */ 
		System.out.println("Beginning add() Tests:");
		A.add(1, 54);//add an item to the front list
		System.out.println("	Does add() work correctly if we add to the front of the list?");
		size=A.get(1);//gets the value at the front of the list
		if(size==54){//the size should be 54
			System.out.println("		Passed");
		}else{
			System.out.println("		Failed");
		}
		A.add(3, 62);//adds an item to the middle of the list
		System.out.println("	Does add() work correctly if we add at the middle of the list?");
		if((A.get(3))==62){//the value at the middle of the list should be 62	
			System.out.println("		Passed");
		}else{
			System.out.println("		Failed");
		}
		/*System.out.println(" The following the test should produce an error");
		  A.add(-200,12);
		  */
		System.out.println("Beginning remove() Tests:");
		A.remove(1);//removing from the front list
		System.out.println("	Does remove() work correctly if we remove from the front of the list?");
		if((A.get(1))!=54){//checking if the value is no longer in that location of the list
			System.out.println("		Passed");
		}else{
			System.out.println("		Failed");
		}
		System.out.println("	Does remove() work correctly if we remove from the middle of the list?");
		size=A.get(2);//getting the value at index 3
		A.remove(2);//removing the value at index 3
		if(A.get(2)!=size){//checking if the value is no longer at index 3
			System.out.println("		Passed");
		}else{
			System.out.println("		Failed");
		}
		/*System.out.println(" The following test should produce an error");
		  A.remove(-1);
		  */
		System.out.println("Beginning removeAll() Tests:");
		A.removeAll();//emptying the list
		System.out.println("	Does removeAll() set the numItems back to 0");
		if((A.size())==0){//checking to see if the numItems is 0
			System.out.println("		Passed");
		}else{
			System.out.println("		Failed");
		}

	}
}
