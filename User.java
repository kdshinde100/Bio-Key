import javax.swing.*;
import java.awt.event.*;
import java.lang.*;
import javax.swing.text.*;
import java.util.*;
import java.io.*;
class User extends JFrame implements ActionListener,FocusListener
{
JLabel l1,passLabel;
JTextField t,passField;
JButton b1;
public String uname,password;
User()
{
super();
 
setLayout(null);
l1=new JLabel("Username");
passLabel = new JLabel("Password");
b1=new JButton("Next");
t=new JTextField();
passField=new JTextField();
l1.setBounds(120,50,80,30);
add(l1);
passLabel.setBounds(120,125,80,30);
add(passLabel);
t.setBounds(60,75,200,30);
add(t);
passField.setBounds(60,150,200,30);
add(passField);
b1.setBounds(120,200,80,30);
add(b1);
t.requestFocus();
b1.addActionListener(this);
//t.addFocusListener(this);
passField.addFocusListener(this);
setVisible(true);
setSize(300,300);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setResizable(false);
this.setTitle("Account Creation");
}


public void focusGained(FocusEvent fe) 
{
	passField.addKeyListener(new KeyAdapter() {
		
			public void keyPressed(KeyEvent e) {
			
				if ((e.getKeyCode()==KeyEvent.VK_ENTER) && ((t.getText().toString().trim()!="")&&(passField.getText().toString().trim()!=""))){
            			
			System.out.println("Hello");
			uname=t.getText();
			password = passField.getText();
			dispose();
			new KeyStroke(uname,password);
        	}				
			


			}
	});
 
}

public void focusLost(FocusEvent fe) {
                
}

public void actionPerformed(ActionEvent ae)
{

if(ae.getActionCommand()=="Next")
{
if(t.getText()!="")
{
dispose();
uname=t.getText();
password = passField.getText();
new KeyStroke(uname,password);
}
else
{
}
}
if(ae.getSource()==t)
{
if(t.getText()!="")
{
b1.setVisible(true);
}
else
{
b1.setVisible(false);
}
}
}
public static void main(String args[])
{
new User();
}
}