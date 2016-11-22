import java.io.BufferedReader;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Components {
		
		
		/*both the streams*/
		 BufferedReader br[]=new BufferedReader[10];
		 PrintWriter pw[]=new PrintWriter[10];
		 
		 /*question*/
		 String question[][];
		    String answers[][];
		    String correctanswers[]= {"A","B","C","D","A","B","C","D","A","B"};
                 int cli=1;
                int marks[]=new int[cli];
		   /* correctanswers[1]="B";
		    correctanswers[2]="C";
		    correctanswers[3]="D";
		 	correctanswers[4]="A";
		    correctanswers[5]="B";
		    correctanswers[6]="C";
		    correctanswers[7]="D";
		    correctanswers[8]="A";
		    correctanswers[9]="B";
		    */
		  public void  ExamQuestion()
		    {
		        question=new String[10][5];
		        answers=new String[10][4];
		        for(int i=0;i<10;i++)
		        {
		            question[i][0]="Make a choice?";
		            question[i][1]="option A";
		            question[i][2]="option B";
		            question[i][3]="option C";
		            question[i][4]="option D";
		            for(int j=0;j<4;j++){
		               answers[i][j]="";
		               
		            }
		        }
		    }
	    
	    JTextArea display;
	    JTextField insert;
	    JButton b;
	    JFrame jf;
	    JScrollPane jsp;
	  Components()
	    {
                   
                for(int i=0;i<cli;i++)
                {
                    marks[i]=0;
                }
	        jf=new JFrame();
	        jf.setTitle("Exam Server");
	        
	        display=new JTextArea();
	        insert=new JTextField();
	        b=new JButton("Broadcast");
	        jf.setSize(262,530);
	        jf.setVisible(true);
	        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
	        jf.getContentPane().setLayout(null);
	        jsp=new JScrollPane(display); 
	        jsp.setVerticalScrollBarPolicy (ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	        jsp.setBounds(10,0,240,400);
	        jf.getContentPane().add(jsp);
	        insert.setBounds(10,428,240,39);
	        jf.getContentPane().add(insert);
	        b.setBounds(74,479,106,39);
	        jf.getContentPane().add(b);
	        
	        JLabel lblMessageToStudents = new JLabel("Message to students :");
	        lblMessageToStudents.setBounds(10, 412, 241, 15);
	        jf.getContentPane().add(lblMessageToStudents);
	      	       
	    }
	 
	}
	
	
	
