package xmlsax;

import java.awt.TextArea;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
public class MyContentHandler implements ContentHandler {
	public TextArea text;
    private StringBuffer buf;
    public void setDocumentLocator( Locator locator ) {
    }
    public void startDocument() throws SAXException {
        buf=new StringBuffer();
        System.out.println("*******start parsing the xml document*******");
       
    }
    public void endDocument() throws SAXException {
        System.out.println("*******end parsing the xml document*******");
    }
    public void processingInstruction( String target, String instruction )
        throws SAXException {
    }
    public void startPrefixMapping( String prefix, String uri ) {
          System.out.println("\n前缀映射: " + prefix +" 开始!"+ "  它的URI是:" + uri);
    }
    public void endPrefixMapping( String prefix ) {
          System.out.println("\n前缀映射: "+prefix+" 结束!");
    }

public void startElement( String namespaceURI, String localName,String fullName, Attributes attributes )throws SAXException {
    System.out.println("element: " + "["+fullName+"]" +" start parsing!");
    // 打印出属性信息
    for ( int i = 0; i < attributes.getLength(); i++ ) {
      System.out.println("attribute_name:" + attributes.getLocalName(i)+ " attribute_value:" + attributes.getValue(i));
}
}
public void endElement( String namespaceURI, String localName,String fullName )throws SAXException {
  //打印出非空的元素内容并将StringBuffer清空                  
  String nullStr="";
  if (!buf.toString().trim().equals(nullStr)){
    System.out.println("element_value: " + buf.toString().trim());
 }
  buf.setLength(0);
  //打印元素解析结束信息
  //System.out.println("element: "+"["+fullName+"]"+" end parsing!");              
}
    public void characters( char[] chars, int start, int length )
                                throws SAXException {
          //将元素内容累加到StringBuffer中                
          buf.append(chars,start,length);
    }
    public void ignorableWhitespace( char[] chars, int start, int length )
                                  throws SAXException {
    }
    public void skippedEntity( String name ) throws SAXException {
    }
}