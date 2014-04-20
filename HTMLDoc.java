import java.io.*;
import java.util.*;

/*
 * Create an html document
 */
class HTMLDoc
{
	Tag head,body; //head and body tag
	String fname; //filename usually a *.html or *.php
	StyleSheet style=null; //stylesheet for the document
	Project proj; //project the document is part of

	HTMLDoc(Project proj,String fname)
	{
		this.proj=proj;
		this.fname=fname;
		head=new GenericTag("head");
		body=new GenericTag("body");
	}

	/*
 	* get methods for the head and body tags
 	*/
	Tag getHead()
	{
		return head;
	}	

	Tag getBody()
	{
		return body;
	}

	/*
 	* sets the stylesheet for the document by creating a link tag in head, refering to the stylesheet
 	*/
	void setStyleSheet(StyleSheet s)
	{
		this.style=s;
		Tag stl=new EmptyTag("link");
		stl.prop(new String[]{"rel","type","href"},new String[]{"stylesheet","text/css","css/"+s.fname});
		head.inner(stl);
	}

	/*
 	* creates the document and outputs into the file
 	* initializes the buffer,
 	* adds doctype, html open and close tags
 	* recursively optains buffers for head and body
 	* opens the file and writes into it
 	* give an appropriate return value
	 */
	int buildDocument() throws Exception
	{
		Buffer buf=new Buffer(1000);
		buf.addKey("inner",0);
		buf.addString("front","<!DOCTYPE html>\n");
		buf.addString("inner","<html>\n");
		buf.addString("rear","</html>\n");
		//Building head
		head.docString(buf);
		//Building body
		body.docString(buf);
		String str=buf.getBuffer();
		System.out.println(str);
		
		PrintWriter writer=new PrintWriter(new BufferedWriter(new FileWriter(proj.dir+"/"+fname)));
		writer.print(str);
		writer.close();
		return 0;
	}
}