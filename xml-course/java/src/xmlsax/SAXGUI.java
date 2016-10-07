package xmlsax;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class SAXGUI extends Frame implements ActionListener{
   
    private static final long serialVersionUID = 1L;
    public  Panel p1;
    public   Button button1;
    public   Button button2;
    public   Button button3;
    public   Button button4;
    public   Button button5;
    public   Button button6;
    public   TextArea text;
    public   int count=1;

 private void generateFlowLayoutPanel() {
       Color color1 = new Color(240,250,240);
       p1 = new Panel();
       p1.setLayout(null);
       p1.setBackground(color1);
       
       button1 = new Button("按评分从高到低");
       button2 = new Button("按时间从短到长");
       button3 = new Button("发布日期从早到晚");
       button4 = new Button("2010以后发布的歌曲并按评分降序");
       button5 = new Button("查询时长大于4分钟的前三首国语歌");
       button6 = new Button("时长小于5分钟的评分最高的前五首民谣歌");
       text=new TextArea();
       //layout      
       button1.setBounds(10, 10, 200, 30);
       button2.setBounds(10, 50, 200, 30);
       button3.setBounds(10, 90, 200, 30);
       button4.setBounds(10, 130, 200, 30);
       button5.setBounds(10, 170, 200, 30);
       button6.setBounds(10, 210, 200, 30);
       text.setBounds(240, 10, 500, 700);
       
       
       //add components
       
       p1.add(button1);
       p1.add(button2);
       p1.add(button3);
       p1.add(button4);
       p1.add(button5);
       p1.add(button6);
       p1.add(text);
   
       
    }
 
public SAXGUI(String panelName) {
       super("JAVA_SAX");
       generateFlowLayoutPanel(); 
       add(p1);
       setSize(800, 1000);   
       setLocation(100,100);
//按钮监听响应

       button1.addActionListener(this);  
       button2.addActionListener(this);  
       button3.addActionListener(this);  
       button4.addActionListener(this);  
       button5.addActionListener(this);  
       button6.addActionListener(this);  
      
//窗口响应
addWindowListener(new WindowAdapter(){
           public void windowClosing(WindowEvent arg0) {
        	   System.exit(0);
           }
       });
    }

public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	if(e.getSource().equals(button1)){
	  	String filename="output_3a_1_1.xml";
		    if ( filename.length() == 1 ) {
		      System.out.println("input: java MySAXApp ");
		      System.exit(0);
		    }
		    try {
		        // 初始化reader
		        XMLReader reader = XMLReaderFactory.createXMLReader
		                          ("org.apache.xerces.parsers.SAXParser") ;
		        // 创建ContentHandler的实例
		        ContentHandler contentHandler = new MyContentHandler();
		        // 在reader中注册实例化的ContentHandler
		        reader.setContentHandler( contentHandler );
		        // 开始解析文档
		        reader.parse(filename);
		    } catch ( IOException e1 ) {
		        System.out.println("error in reading xmldocument: " + e1.getMessage());
		    } catch ( SAXException e1 ) {
		        System.out.println("error in parsing xmldocument: " + e1.getMessage());
		    }
		    
		    count=1;
		  text.setText(output(new File ("output_1.txt")));
	}
	if(e.getSource().equals(button2)){
	  	String filename="output_3a_1_2.xml";
		    if ( filename.length() == 1 ) {
		      System.out.println("input: java MySAXApp ");
		      System.exit(0);
		    }
		    try {
		        // 初始化reader
		        XMLReader reader = XMLReaderFactory.createXMLReader
		                          ("org.apache.xerces.parsers.SAXParser") ;
		        // 创建ContentHandler的实例
		        ContentHandler contentHandler = new MyContentHandler();
		        // 在reader中注册实例化的ContentHandler
		        reader.setContentHandler( contentHandler );
		        // 开始解析文档
		        reader.parse(filename);
		    } catch ( IOException e1 ) {
		        System.out.println("error in reading xmldocument: " + e1.getMessage());
		    } catch ( SAXException e1 ) {
		        System.out.println("error in parsing xmldocument: " + e1.getMessage());
		    }
	
		    count=2;
		   text.setText(output(new File ("output_2.txt")));
	}
	if(e.getSource().equals(button3)){
	  	String filename="output_3a_1_3.xml";
		    if ( filename.length() == 1 ) {
		      System.out.println("input: java MySAXApp ");
		      System.exit(0);
		    }
		    try {
		        // 初始化reader
		        XMLReader reader = XMLReaderFactory.createXMLReader
		                          ("org.apache.xerces.parsers.SAXParser") ;
		        // 创建ContentHandler的实例
		        ContentHandler contentHandler = new MyContentHandler();
		        // 在reader中注册实例化的ContentHandler
		        reader.setContentHandler( contentHandler );
		        // 开始解析文档
		        reader.parse(filename);
		    } catch ( IOException e1 ) {
		        System.out.println("error in reading xmldocument: " + e1.getMessage());
		    } catch ( SAXException e1 ) {
		        System.out.println("error in parsing xmldocument: " + e1.getMessage());
		    }
		    
		    count=3;
		    text.setText(output(new File ("output_3.txt")));
	}
	if(e.getSource().equals(button4)){
	  	String filename="output_3a_2_1.xml";
		    if ( filename.length() == 1 ) {
		      System.out.println("input: java MySAXApp ");
		      System.exit(0);
		    }
		    try {
		        // 初始化reader
		        XMLReader reader = XMLReaderFactory.createXMLReader
		                          ("org.apache.xerces.parsers.SAXParser") ;
		        // 创建ContentHandler的实例
		        ContentHandler contentHandler = new MyContentHandler();
		        // 在reader中注册实例化的ContentHandler
		        reader.setContentHandler( contentHandler );
		        // 开始解析文档
		        reader.parse(filename);
		    } catch ( IOException e1 ) {
		        System.out.println("error in reading xmldocument: " + e1.getMessage());
		    } catch ( SAXException e1 ) {
		        System.out.println("error in parsing xmldocument: " + e1.getMessage());
		    }
		    
		    count=4;
		   text.setText(output(new File ("output_4.txt")));
	}
	if(e.getSource().equals(button5)){
	  	String filename="output_3a_2_2.xml";
		    if ( filename.length() == 1 ) {
		      System.out.println("input: java MySAXApp ");
		      System.exit(0);
		    }
		    try {
		        // 初始化reader
		        XMLReader reader = XMLReaderFactory.createXMLReader
		                          ("org.apache.xerces.parsers.SAXParser") ;
		        // 创建ContentHandler的实例
		        ContentHandler contentHandler = new MyContentHandler();
		        // 在reader中注册实例化的ContentHandler
		        reader.setContentHandler( contentHandler );
		        // 开始解析文档
		        reader.parse(filename);
		    } catch ( IOException e1 ) {
		        System.out.println("error in reading xmldocument: " + e1.getMessage());
		    } catch ( SAXException e1 ) {
		        System.out.println("error in parsing xmldocument: " + e1.getMessage());
		    }
		    
		    count=5;
		    text.setText(output(new File ("output_5.txt")));
	}
	if(e.getSource().equals(button6)){
	  	String filename="output_3a_2_3.xml";
		    if ( filename.length() == 1 ) {
		      System.out.println("input: java MySAXApp ");
		      System.exit(0);
		    }
		    try {
		        // 初始化reader
		        XMLReader reader = XMLReaderFactory.createXMLReader
		                          ("org.apache.xerces.parsers.SAXParser") ;
		        // 创建ContentHandler的实例
		        ContentHandler contentHandler = new MyContentHandler();
		        // 在reader中注册实例化的ContentHandler
		        reader.setContentHandler( contentHandler );
		        // 开始解析文档
		        reader.parse(filename);
		    } catch ( IOException e1 ) {
		        System.out.println("error in reading xmldocument: " + e1.getMessage());
		    } catch ( SAXException e1 ) {
		        System.out.println("error in parsing xmldocument: " + e1.getMessage());
		    }
		    
		    count=6;
		   text.setText(output(new File ("output_6.txt")));
	}
		  }



public void input(File f) throws IOException{
	f.createNewFile();
    FileOutputStream fileOutputStream = new FileOutputStream(f);
    PrintStream printStream = new PrintStream(fileOutputStream);
    System.setOut(printStream);
   
}
public String output(File f){
	 StringBuffer contents = new StringBuffer();//StringBuffer类 字符串变量
     BufferedReader reader = null;//BufferedReader字符流  BufferedInputStream字节流  
     try {
         reader = new BufferedReader(new FileReader(f));
         String text = null;
         
         //repeat until all lines is read
         while ((text = reader.readLine()) != null) {
             contents.append(text);
             contents.append(System.getProperty("line.separator"));//读完一行增加一个换行符
         }
     } catch (FileNotFoundException e) {
         e.printStackTrace();
     } catch (IOException e) {
         e.printStackTrace();
     } finally {
         try {
             if (reader != null) {
                 reader.close();
             }
         }catch (IOException e) {
             e.printStackTrace();
         }
     } 
     return contents.toString();
   
  }

public static void main(String[] args) throws IOException, InterruptedException {
	SAXGUI yourFrame = new SAXGUI("sax of xml");
    yourFrame.setVisible(true);
    String filename ="out_1.txt";
    File file=new File(filename);
	yourFrame.input(file);
	Thread.currentThread().sleep(1500);
	yourFrame.text.setText(yourFrame.output(file));

  
}


}






































































































