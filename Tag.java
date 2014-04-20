import java.util.*;
/*
 * Defines a html tag, can be extended to create new tags.
 */
class Tag
{
	ArrayList<Property> property; // Array for properties of a Tag.
	ArrayList<Tag> tag; // Array for making new tags inside of existing tags.

	Tag()
	{
		property=new ArrayList<Property>();
		tag=new ArrayList<Tag>();
	}

	Tag(String name,String id,String cls)
	{
		property = new ArrayList<Property>();
		tag = new ArrayList<Tag>();
		prop(new String[]{"name","id"},new String[]{name,id});//More items can be added to the String[] argument as per requirement
	}

	/*
 	* add an inner tag and return an appropriate value
 	*/
	int inner(Tag t)
	{
		tag.add(t);
		return 0;
	}

	/*
 	* add a list of inner tags and return an appropriate value
 	* can be overridden in subclasses
 	*/
	int inner(Tag t[])
	{
		for(int i=0;i<t.length;i++)
			tag.add(t[i]);
		return 0;
	}	

	/*
 	* set a property and return an appropriate value
 	* can be overridden in subclasses
 	*/
	int prop(String nam, String val)
	{
		Property p=new Property(nam,val);
		property.add(p);
		return 0;
	}

	/*
 	* set properties and return an appropriate value
 	* can be overridden in subclasses
 	*/
	int prop(String nam[], String val[])
	{
		int rv=0;
		for(int i=0;i<nam.length;i++)
		{
			String v="";
			if(i<val.length)
				v=val[i];
			rv+=prop(nam[i],v);
		}
		return rv;
	}
	
	
	void Add(String sname) throws Exception
	{
	    jsfunc jsobject = new jsfunc(sname);
	    Tag t=jsobject.jscript;
	    inner(t);
	}
	
	/*
 	* Generates the entire html content for the tag, recursively finding content for inner tags
 	* Puts the inner tag content at "inner" insertion point of the input buffer
 	* Puts the propeties content at "property" insertion point of the input buffer
 	*/
	void docString(Buffer buf)
	{
		for(int i=0;i<property.size();i++)
		{
			Property p=property.get(i);
			String str=p.toString();    //get property string
			buf.addString("property",str);    //adds a property content to the buffer
		}
		Buffer inner=new Buffer(100); //create a new buffer to add inner content
		inner.addKey("inner",0);    //add insertion point to the buffer
		for(int i=0;i<tag.size();i++)
		{
			Tag t=tag.get(i);
			t.docString(inner); //generate html for inner tags recursively into inner
		}
		String str=inner.getBuffer();   //Get string from inner
		if(str!=null&&str.length()>0)
			buf.addString("inner",str);  //add inner content to the buffer
	}
}