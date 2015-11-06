import java.awt.*;

import javax.swing.*;
 
 
public class GUI extends JFrame {
        
        public GUI(String title) {
    	    super(title);
            setSize(350, 400);           
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
            setLayout(null);
            getContentPane().setBackground( Color.BLACK );
            setResizable(false);
            setVisible(true);
        }
        
        public void addComponent(Component c){
        	add(c);
        }
        
       
}