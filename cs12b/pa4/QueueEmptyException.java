//-----------------------------------------------------------------------------
// QueueEmptyException.java
// Shayan Salsabilian
// ssalsabi
// Error message that is thrown when we have an empty Queue
// 2/20/19
// 12B
//-----------------------------------------------------------------------------
public class QueueEmptyException extends RuntimeException{
	public QueueEmptyException(String s){
	super(s);
	}
}
