import java.io.*;
import java.util.*;

/*
 * Example of how to use Tag as a superclass
 * Defines an empty html tag, can be both extended and implemented for tags img, input, link etc
 * Can be declared abstract if user should not be allowed to instatntiate this.
 */
class EmptyTag extends Tag
{
	String tagstr;  //the html string for tag like img or link
	EmptyTag(String tagstr)
	{
		this.tagstr=tagstr;
	}

	/*
	 * overrides the superclass method 
	 */
	void docString(Buffer buf)
	{
		int len=tagstr.length();
		Buffer inner=new Buffer(100); //create a new buffer
		/*
		 * add tag
		 */
		inner.addString("front","<"+tagstr+" />\n");
		/*
		 * add insertion points to the buffer 
		 */
		inner.addKey("property",len+1);
		/*
		 * add properties by calling superclass method
		 */
		super.docString(inner);
		String str=inner.getBuffer();//get srting from inner
		buf.addString("inner",str);   //add inner content to the buffer
	}
}