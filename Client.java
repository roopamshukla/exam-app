import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
public class Client extends JFrame{
    JTextField insert;
    JButton b;
    JScrollPane jsp;
    
    BufferedReader rin;
    PrintWriter pw;
    
    String name;
    Socket s;
    private JLabel lblAnswer;
    private JTextArea display;
    Client(String name) throws IOException
    {
        this.name=name;
         
        this.setTitle("Client : "+name);
        insert=new JTextField();
        b=new JButton("Answer");
        
        this.setSize(510,533);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
       
        jsp=new JScrollPane(); 
        jsp.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        jsp.setBounds(12,0,488,460);
        getContentPane().add(jsp);
        
        display = new JTextArea();
        jsp.setViewportView(display);
        insert.setBounds(10,488,340,42);
        getContentPane().add(insert);
         insert.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e)
           {
               b.doClick();
           }
        });
        b.setBounds(350,488,150,41);
        getContentPane().add(b);
        lblAnswer = new JLabel("Answer : ");
        lblAnswer.setBounds(12, 468, 93, 26);
        getContentPane().add(lblAnswer);
        b.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
            String s=insert.getText();
            if(s.equals(""))
                return;
            insert.setText("");
            display.setText(display.getText()+"\nMe : "+s);
            if(s.equals(name))
            {
                pw.println(name);
            	pw.flush();
            }
            else{
            	s=s.trim();
            	char y=s.toLowerCase().charAt(0);
            	if(y=='a'||y=='b'||y=='c'||y=='d')
            	{
            		 pw.println(s.toUpperCase());
            		 pw.flush();
            		 
            	}
            	else
            	{
            		display.setText(display.getText()+"\nOption not available.");
            		return;
            	}
           
            }
            
           
        }
        });
        s=new Socket("localhost",2244);
        rin=new BufferedReader(new InputStreamReader(s.getInputStream()));
        pw=new PrintWriter(s.getOutputStream());
        insert.setText(name);
        
        
        b.doClick();
       while(true){ 
           String instr=rin.readLine();
       if(instr.equals("quit"))
           break;
       if(!instr.equals(""))
        {
    	   display.setText(display.getText()+"\n"+instr);
        }}
       s.close();
    }
 
    public static void main(String args[])throws IOException
    {
        System.out.println("Enter name (without spaces): ");
        String s=new Scanner(System.in).next();
        Client m=new Client(s);
    }  
}
