import java.util.*;
import java.io.*;
class Style
{
	String tag;
	ArrayList<String> attr,val;
	
	//creating arraylist for storing attributes and their values
	Style(String tag)
	{
	    this.tag = tag;
	    attr = new ArrayList<String>();
	    val = new ArrayList<String>();
	}
	
	//storing the values for attributes
	void setAttr(String prop[],String detail[])
	{
	    int i=0;
	    for(i=0;i<prop.length;i++)
	    {
	        attr.add(prop[i]);
	        val.add(detail[i]);
	    }
	}
	    
	
	//integrating all the styles into a stylesheet and including it in the css file
	void makeStyle(Buffer buff)
	{
	    String txt=tag+"\n{\n";;
	    buff.addString("inner",txt);
	    int i=0;
	    for(i=0;i<attr.size();i++)
	    	buff.addString("inner",attr.get(i)+":"+val.get(i)+";\n");
	    
	    buff.addString("inner","\n}\n");
	}
}	