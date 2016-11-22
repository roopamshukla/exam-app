
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class ServerThread extends Thread {
     Components c;
   
   
    
    Socket s;
    ServerThread(Socket s,Components c)
    {
        this.s=s;
        this.c=c;
    }
      public void run()
    {
        
        int cno=Integer.parseInt(this.getName());
       
        try{
        
       
         c.insert.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e)
           {
               c.b.doClick();
           }
        });
        
        c.b.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
 
            String s=c.insert.getText();
            if(s.equals(""))
                return;
            c.insert.setText("");
             c.display.setText(c.display.getText()+"\nMe :"+s);
        
            try{
                for(int i=0;i<10;i++){
             c.pw[i].println("Server:"+s);
            c.pw[i].flush();   
                }
            }catch(Exception ex){}
        }
        });
       
        String cname=c.br[cno].readLine();
         c.display.setText(c.display.getText()+"Connected : "+cname);
       
         for(int i=0;i<10;i++){
         c.pw[cno].println("Question "+(i+1));
        c.pw[cno].println(c.question[i][0]);
        c.pw[cno].println(c.question[i][1]);
        c.pw[cno].println(c.question[i][2]);
        c.pw[cno].println(c.question[i][3]);
        c.pw[cno].println(c.question[i][4]);
        c.pw[cno].flush();

        String instr=c.br[cno].readLine();
        if(!instr.equals(""))
        {
           char ans=instr.charAt(0);
           int ind=ans%65; 
           c.answers[i][ind]=c.answers[i][ind]+cno;
           
           
        }
        c.pw[cno].println("Response Recorded\n ");
         this.sleep(200);
         }
    
       c.pw[cno].println("\n\nThank You. Wait for others to get finished.\n");
        this.sleep(2000);
       this.stop();
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
