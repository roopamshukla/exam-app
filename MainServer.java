import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {

    ServerSocket ss;
    Socket s;
    int clients=1;
    void MyServer() throws IOException
    {
        boolean finish=false;
        Components c=new Components();
        
        c.ExamQuestion();
     
        
        ss=new ServerSocket(2244);
        ServerThread sm[]=new ServerThread[10];
       for(int j=0;j<clients;j++){
          
        s=ss.accept();
      
         c.br[j]=new BufferedReader(new InputStreamReader(s.getInputStream()));
        c.pw[j]=new PrintWriter(s.getOutputStream());
        
        sm[j]=new ServerThread(s,c);
        sm[j].setName(Integer.toString(j));
        sm[j].start();
         
        
        }
       while(true){
          finish=true;
       for(int p=0;p<clients;p++)
       {
           if(sm[p].isAlive())
               finish=false;
          
       }
       if(finish)
           break;
       }
       int ans;
       int flag=0;
       for(int i=0;i<10;i++)
       {
       		ans=c.correctanswers[i].charAt(0)%65;
                
       		
           for(int k=0;k<clients;k++){
           		
           		
           		if(c.answers[i][ans].length()==0)
           		{
           			c.pw[k].println("QUESTION "+(i+1)+" Incorrect.");
           			c.pw[k].println("Correct Answer : "+c.correctanswers[i]);
                             	c.pw[k].flush();
           			
           		}
           		else
           		{
                                
		       		for(int j=0;j<c.answers[i][ans].length();j++)
		       		{
                                    int c1=(int)c.answers[i][ans].charAt(j)%48;
                                    
                                    
                                    
		       			if(c1==k)
		       			{
		       				flag=1;
		       				break;
						}
						else
						{
		       				flag=0;
						}
					
		       		}
		       		if(flag==1)
		       		{
		       			c.pw[k].println("QUESTION "+(i+1)+" Correct.");
                                        c.marks[k]++;
                                        System.out.println(c.marks[k]);
                                        c.pw[k].flush();
		       		}
		       		else 
		       		{
		       			c.pw[k].println("QUESTION "+(i+1)+" Incorrect.");
		       			c.pw[k].println("Correct Answer : "+c.correctanswers[i]);
                                        c.pw[k].flush();
		       		}
                                
                        }
                       
           }
           
       }
      for(int k=0;k<clients;k++){
          
           c.pw[k].println("Marks Obtained : "+c.marks[k]);
           c.pw[k].flush();
       }
       
    }
    public static void main(String args[])throws IOException
    {
       MainServer ex=new MainServer();
       ex.MyServer();
    }
}
  

