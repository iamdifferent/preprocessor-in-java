import java.io.*;
import java.util.*;

/*
 * Example of how to use Tag as a superclass
 * Defines a html tag to contain text elements, can be both extended and implemented for tags like h1-h6,p,span,a etc
 * Can be declared abstract if user should not be allowed to instatntiate this.
 */
class TextTag extends Tag
{

    /*
     * cue defines the order of text and the inner tags enclosed
     * The variables defined in superclass are also available here
     */
    int cue=1;
    static final int CUE_AFTER=1,CUE_BEFORE=-1;
    String txt,tagstr; //txt-> text content, tagstr-> the html string for tag like h1 or p
    
    TextTag(String tagstr)
    {
        this(tagstr,"");
    }
    
    TextTag(String tagstr,String txt)
    {
        this(tagstr,txt,1);
    }
    
    TextTag(String tagstr,String txt,int cue)
    {
        super();
        this.cue=cue;
        this.txt=txt;
        this.tagstr=tagstr;
    }

    /*
    * get, set and update methods for text content
    */
    String text()
    {
        return txt;
    }
    
    void text(String txt)
    {
        this.txt=txt;
    }
    
    void appendText(String add)
    {
        txt=txt+add;
    }

    /*
    * overrides the superclass method 
    */
    void docString(Buffer buf)
    {
        int len=tagstr.length();
        Buffer inner=new Buffer(100);   //create a new buffer
        /*
         * opening and closing tag
         */
        inner.addString("front","<"+tagstr+">\n");    
        inner.addString("rear","</"+tagstr+">\n");
        /*
         * add insertion points to the buffer 
         */
        inner.addKey("property",len+1);
        inner.addKey("inner",len+3);
        /*
         * add properties, text and inner tags according to cue
         * inner tags added by calling superclass method
         */
        if(cue<0)
        {
            super.docString(inner);
            inner.addString("inner",txt+"\n");
        }
        else
        {
            inner.addString("inner",txt+"\n");
            super.docString(inner);
        }
    
        String str=inner.getBuffer();   //Get string from inner
        buf.addString("inner",str);   //add inner content to the buffer
    }       
}