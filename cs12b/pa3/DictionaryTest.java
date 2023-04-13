//Shayan Salsabilian
//ssalsabi
//2/13/19
//12B
//Tests the basic functions of our Dictionary Linked Lists
//DictionaryTest.java
public class DictionaryTest
{
  public static void main (String[]args)
  {
    String x;
    Dictionary B = new Dictionary ();	//declaring a Dictionary node
      System.out.println ("Beginning isEmpty Tests:");
    Boolean Check = B.isEmpty ();	//checking while isEmpty() on a empty Dictionary
      System.out.
      println
      ("	Test 1: Does isEmpty handle a Empty Dictionary correctly?");
    if (Check == true)
      {
	System.out.println ("		Passed");
      }
    else
      {
	System.out.println ("		Failed");
      }
    System.out.
      println
      ("	Test 2: Does isEmpty() handle a Dictionary with an item correctly?");
    B.insert ("1", "A");	//inserting an item in the list
    Boolean Check2 = B.isEmpty ();	//Checking that isEmpty() returns false when we have an item
    if (Check2 == false)
      {
	System.out.println ("		Passed");
      }
    else
      {
	System.out.println ("		Failed");
      }
    System.out.println ("Beginning Size Tests:");
    B.delete ("1");		//deleting an item from the list
    System.out.
      println
      ("	Test 1: Does size handle an empty dictionary correctly?");
    if (B.size () == 0)
      {				//checking it returns correctly when our Dictionary is size 0
	System.out.println ("		Passed");
      }
    else
      {
	System.out.println ("		Failed");
      }
    B.insert ("1", "ABC");	//inserting two items into the list
    B.insert ("82", "IAMIRONMAN");
    System.out.
      println
      ("	Test 2: Does size handle a dictionary of size 2 correctly?");
    if (B.size () == 2)
      {				//checking the dictionary is size 2
	System.out.println ("		Passed");
      }
    else
      {
	System.out.println ("		Failed");
      }
    System.out.println ("Beginning Lookup Tests:");
    B.insert ("5", "what did the fox Say");	//inserting another item in the last
    System.out.
      println
      ("	Test 1: Does lookup get the right value from the end of the dictionary?");
    x = B.lookup ("5");		//looking up key 5
    if (x == "what did the fox Say")
      {				//checking if the value matches
	System.out.println ("		Passed");
      }
    else
      {
	System.out.println ("		Failed");
      }
    System.out.
      println
      ("	Test 2: Does lookup get the right value from the beginning of the dictionary?");
    x = B.lookup ("1");		//looking up key 1
    if (x == "ABC")
      {				//checking if the value comes back correctly
	System.out.println ("		Passed");
      }
    else
      {
	System.out.println ("		Failed");
      }
    System.out.
      println
      ("	Test 3: Does lookup return null when the key doesnt exist?");
    x = B.lookup ("AAAAAAAAAAAAAAAAAAAAAAAAAA");	//looking up a nonexistent key
    if (x == null)
      {				//checking if the value comes back null
	System.out.println ("		Passed");
      }
    else
      {
	System.out.println ("		Failed");
      }
    System.out.println ("Beginning Insert Tests:");
    B.insert ("7", "123");	//inserting a key and value into the list
    x = B.lookup ("7");		//looking up to see the value at the key is correct
    System.out.
      println ("	Test 1: Does insert properly insert onto the list?");
    if (x == "123")
      {				//checking it to see that it matches the value it needs to be
	System.out.println ("		Passed");
      }
    else
      {
	System.out.println ("		Failed");
      }
    System.out.
      println
      ("	Test 2: Does insert properly insert to the front of the dictionary?");
    x = B.lookup ("1");		//looking up the first value we entered
    if (x == "ABC")
      {				//checking to see if it matches the value that it should be
	System.out.println ("		Passed");
      }
    else
      {
	System.out.println ("		Failed");
      }
    /*System.out.println("  Test 3: Testing DuplicateKeyException, this should produce a DuplicateKeyException error message and crash the program");
       B.insert("7", "123"); //we already inserted this key so should produce an error
     */
    System.out.println ("Beginning Delete Tests:");
    System.out.
      println
      ("	Test 1: Does delete properly remove from the front of the list?");
    B.delete ("1");		//deleting the first item in the list
    x = B.lookup ("1");		//checks to make sure that key 1 is now holding value null
    if (x == null)
      {				//it should be null, if its not then deletes not working properly
	System.out.println ("		Passed");
      }
    else
      {
	System.out.println ("		Failed");
      }
    B.delete ("7");		//delete the last item in the list
    x = B.lookup ("7");		//the key should no longer have the value
    System.out.
      println
      ("	Test 2: does delete properly remove from the end of the list?");
    if (x == null)
      {				//it should be null, if its not then deletes not working properly
	System.out.println ("		Passed");
      }
    else
      {
	System.out.println ("		Failed");
      }
    /*System.out.println("  Test 3: Testing KeyNotFoundException, this should produce error message involving KeyNotFoundException and crash the program");
       B.delete("8888"); //this key doesn't exist, so the KeyNotFoundException error should be produced
     */
    System.out.println ("Beginning makeEmpty Tests:");
    B.makeEmpty ();		//empties the dictionary
    System.out.
      println ("	Test 1: Does makeEmpty make the dictionary empty?");
    if (B.size () == 0)
      {				//checks to make sure the size of the dictionary is 0
	System.out.println ("		Passed");
      }
    else
      {
	System.out.println ("		Failed");
      }
    System.out.println ("Beginning toString Tests:");
    System.out.println ("The following test should match this:");
    System.out.println ("1 82");	//the values that we will be inserting into the dictionary
    System.out.println ("$ 76");
    System.out.println ("T 3.14");
    System.out.println ("Output:");
    B.insert ("1", "82");	//inserting into the dictionary
    B.insert ("$", "76");
    B.insert ("T", "3.14");
    System.out.println (B);	//printing out the entire dictionary

  }
}
