import java.awt.*;

import javax.swing.*;
 
 
public class GUI extends JFrame {
        
        public GUI(String title) {
    	    super(title);
            setSize(300, 300);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
            setLayout(null);
            getContentPane().setBackground( Color.BLACK );
            setResizable(false);
            /*JButton s =new JButton("Server");
    		s.setSize(50,50);
    		s.setLocation(400,400);
    		s.setVisible(true);
    		add(s);*/
            setVisible(true);
        }
        
        public void addComponent(Component c){
        	add(c);
        }
        
       
}