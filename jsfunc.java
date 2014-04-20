/*
 * This class allows for the embedding of time and date
 * properties to a webpage. Since both of these properties
 * are embedded after reading from their respective
 * text files, they can be altered as and how the user desires.
 */
import java.util.*;
import java.io.*;
class jsfunc
{
    // Creating the necessary objects for inclusion in the html page.
    Tag jscript;
    String fname;
    Buffer buf;
    
    
    jsfunc(String fname) throws Exception
    {
        this(fname,true);
    }
    
    jsfunc(String sname,boolean value) throws Exception
    {
        FileReader fr=new FileReader(sname+".txt");
        BufferedReader buf=new BufferedReader(fr);
        
        //reading from a file.
        String read=" ",txt="\n";
        while((read=buf.readLine()) != null)
        {
            txt=txt+read+"\n";
        }
        System.out.println(txt);
        
        //Including the javascript code in the body tag.
        jscript=new TextTag("p",txt);
    }
}