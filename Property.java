import java.io.*;
import java.util.*;

/*
 * Defines a property for a tag, can be extended to create more specialized properties.
 */
class Property
{

	/*
 	* name=value format property
 	*/

	String name,value;
	Property(String name,String value)
	{
		this.name=name;
		this.value=value;
	}

	/*
 	* return the string name="value" for the property
 	*/
	public String toString()
	{
		String str=" "+name+"=\""+value+"\"";
		return str;
	}
}