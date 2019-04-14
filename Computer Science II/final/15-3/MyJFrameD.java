// Eugene Triguba
// ytribuba17@ole.augie.edu
// MyJFrameD.java
// 15-3 WindowEventsNestedClasses

import javax.swing.*;								
import java.awt.event.*; 								
public class MyJFrameD extends JFrame			
{
    public MyJFrameD()							
    {
        registerListeners();
        setTitle("Event handling");						
        setSize(250,100); 						
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	//line 12
        setVisible(true);							
    }		

    public void registerListeners()
    {
        addWindowListener(
            new WindowAdapter()
            {
                public void windowClosing(WindowEvent e)
                {
                    String s;
                    s = JOptionPane.showInputDialog(null, "Do you really want to exit? y/n");
                    if (s==null) return;
                    if (s.equals("y")) System.exit(0);	
                }
            });			
    }			

    public static void main(String[] args)
    {
        MyJFrame f=new MyJFrame();					
    }
}