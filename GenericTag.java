import java.io.*;
import java.util.*;

/*
 * Example of how to use Tag as a superclass
 * Defines a generic html tag to contain text elements, can be both extended and implemented for tags like head, body, div etc
 * Can be declared abstract if user should not be allowed to instantiate this.
 */
class GenericTag extends Tag
{
	String tagstr;  //the html string for tag like head or div
	GenericTag(String tagstr)
	{
		this.tagstr=tagstr;
	}

	/*
 	* overrides the superclass method 
 	*/
	void docString(Buffer buf)
	{
		int len=tagstr.length();
		Buffer inner=new Buffer(100);//create a new buffer
		/*
		 * add opening and closing tag
		 */
		inner.addString("front","<"+tagstr+">\n");
		inner.addString("rear","</"+tagstr+">\n");
		/*
	 	* add insertion points for property and inner content to the buffer 
		 */
		inner.addKey("property",len+1);
		inner.addKey("inner",len+3);
		/*
		 * add properties and inner tags by calling superclass method
		 */
		super.docString(inner);
		String str=inner.getBuffer();//get srting from inner
		buf.addString("inner",str);   //add inner content to the buffer
	}
}