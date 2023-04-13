//Shayan Salsabilian
//ssalsabi
//2/13/19
//12B
//Creates the exception for when we have a duplicate key
//DuplicateKeyException.java
public class DuplicateKeyException extends RuntimeException
{
  public DuplicateKeyException (String s)
  {
    super (s);
  }
}
