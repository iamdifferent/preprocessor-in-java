import java.io.*;
import java.util.*;

/*
 * a sample project created via this framework
 */
class Sample
{
	public static void main(String args[])throws Exception
	{
		Project p=new Project("./CS 698Y/"); //create a new project in a directory called sample in current directory
		HTMLDoc htd=p.getIndex();   //get index page of the project
		Tag head=htd.getHead();     //obtain head tag of index page     
		Tag body=htd.getBody();     //obtain body tag of index page
		head.inner(new TextTag("title","TOOLi Project")); //add <title>Sample Document</title> to head
		Tag w=new TextTag("p","Yeah !!<br /><br /> Yeah my code works !!! ......"); //create <p>It works!</p> as w
		w.prop("name","works"); //change w to <p name="works">It works!</p>
		body.inner(w);          //add w inside body tag
		
		//Adding an image to the webpage
		Tag img=new EmptyTag("img");
		img.prop(new String[]{"src","height","width"},new String[]{"java.jpg","200","300"});
		body.inner(img);
		
		//Adding time & date to webpage
		body.Add("time");
		body.Add("date");
		
		//customizing the stylesheet and styles within
		StyleSheet s=new StyleSheet(p);
		Style style=new Style("body");
		style.setAttr(new String[]{"color","background-color","font-size","font-family"},new String[]{"black","white","40px","monotype-corsiva"});
		s.insert(style);
		p.build();//build the project
	}
}