import java.io.*;
import java.util.*;

/*
 * create a new web project
 */
public class Project
{
	/*
 	* specify the project directory, logfile, stylesheet and a list of html documents in the project
 	*/
	String dir,log;
	StyleSheet s;
	ArrayList<HTMLDoc> docs;
	
	Project(String dir)
	{
		this.dir=dir;
		docs=new ArrayList<HTMLDoc>();
		HTMLDoc index;
		index=new HTMLDoc(this,"index.html");
		docs.add(index);
		log="log.txt";
	}

	/*
 	* set methods for directory and stylesheet
 	*/
	void setDir(String dir)
	{
		this.dir=dir;
	}
	
	void setStylesheet(StyleSheet s)
	{
		this.s=s;
		for(int i=0;i<docs.size();i++)
		{
			(docs.get(i)).setStyleSheet(s);
		}
	}

	/*
 	 * add html document to the project
 	 */
	void addDoc(HTMLDoc htd)
	{
		docs.add(htd);
		htd.setStyleSheet(s);
	}

	/*
	 * get index page for the web project
 	 */
	HTMLDoc getIndex()
	{
		return docs.get(0);
	}

	/*
 	* build the web project
 	* create directories, document files, stylesheet files
 	* return an appropriate value
 	*/
	int build() throws Exception
	{
		File f1=new File(dir+"/");
		File f2=new File(dir+"/css");
		if(f1.mkdirs()|f2.mkdirs())
			System.out.println("Building Up......\n");
		
		for(int i=0;i<docs.size();i++)
		{
			HTMLDoc htd=docs.get(i);
			htd.buildDocument();
		}
		s.make();
		return 0;
	}
}
