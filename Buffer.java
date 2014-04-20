import java.io.*;
import java.util.*;

/*
 * Define a named insertion point in the buffer, where text can be inserted.
 * The index of the key/insertion point is updated as text is added.
 */
class Key
{
	/*
 	* index value for key
 	*/
	int kv;
	Key(int kv)
	{
		this.kv=kv;
	}

	/*
 	* get and update methods for index
 	*/
	int getValue()
	{
		return kv;
	}

	void update(int kv)
	{
		this.kv=kv;
	}

}

/*
 * create a buffer class for string handling in the framework
 */

public class Buffer
{
	/*
 	* buffer string, size attribute and a map of insertion points
	*/
	String buffer;
	int size;
	
	Map<String,Key> insert;
	
	Buffer(int size)
	{
		this("",size);
	}
	
	Buffer(String buffer)
	{
		this(buffer,buffer.length());
	}
	
	Buffer(String input,int length)
	{
		size=length;
		insert=new TreeMap<String,Key>();
		buffer=input;
		insert.put("front",new Key(-1));
		insert.put("rear",new Key(0));
	}

	
	/*
 	* add a new named insertion point to the buffer
 	*/
	void addKey(String key,int val)
	{
		insert.put(key,new Key(val));
	}

	/*
 	* add text to the given named insertion point
 	*/
	void addString(String key,String txt)
	{
		Key id=insert.get(key);
		String newbuffer="";
		if(id.getValue()<0)
			newbuffer = txt + buffer; //front insertion
		else 
			newbuffer = buffer.substring(0,id.getValue()) + txt + buffer.substring(id.getValue()); //insertion

		if(!key.equals("rear"))
			updateMap (id.getValue(),txt.length()); // If its a front insertion, then push the rear key backwards to make more room.

		buffer=newbuffer;
	}

	/*
 	* update insertion points when a new text is added to the buffer
 	*/
	void updateMap(int id,int offset)
	{
		ArrayList<Key> val=new ArrayList<Key>(insert.values());
		for(int i=0;i<val.size();i++)
		{
			int cv = (val.get(i)).getValue();
			
			if(cv<0)       
				continue;
			
			if(cv>=id)  // When a something is to be inserted between the existing tags, the rear key is pushed back to make room.
				(val.get(i)).update(cv+offset);
		}
	}

	/*
 	* return buffer string 
  	*/
	String getBuffer()
	{
		return buffer;
	}
}