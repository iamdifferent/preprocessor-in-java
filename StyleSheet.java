import java.util.*;
import java.io.*;
class StyleSheet
{
	ArrayList<Style> style;
	String fname;
	Project proj;
	
	StyleSheet(Project proj)
	{
	    this(proj,"style.css");
	}
	
	StyleSheet(Project proj,String filename)
	{
	    fname=filename;
	    this.proj=proj;
	    style=new ArrayList<Style>();
	    proj.setStylesheet(this);
	}
	
	//adding a style object to the stylesheet.
	void insert(Style s)
	{
	    style.add(s);
	}
	
	//writing the stylesheet code to a css file as well as the terminal.
	void make()throws Exception
	{
	    Buffer buff=new Buffer(1000);
		buff.addKey("inner",0);
	    int i=0;
	    for(i=0;i<style.size();i++)
	    	(style.get(i)).makeStyle(buff);
	    
	    System.out.println(buff.getBuffer());
	    
	    PrintWriter writer=new PrintWriter(new BufferedWriter(new FileWriter("./CS 698Y/css/style.css")));
	    writer.print(buff.getBuffer());
	    writer.close();
	}
	
	
}