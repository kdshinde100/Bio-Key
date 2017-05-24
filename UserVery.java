import java.lang.*;
import java.util.*;
import java.io.*;
import java.awt.Color;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;
import javax.swing.*;

class UserVery extends JFrame implements ActionListener,FocusListener
{

String [][] keystore;//=new String[19][8];
String text = "keystrokedynamics",wordanalyzer="";
int passLength;
long milli1=0,milli2=0,milli3=0,pp=0,rr=0,pt=0,w22,r22,w,fc,lc;
double typingrate;
long chcnt=0,flag=0;	
int totalkeycount=0,bscount=0,errors=0,overallk=0,keypre=-1,keyreal=16,x=0;
public static int keytrackcnt=-1;
JLabel l2,l1,l3,l4,enpassLabel;
JTextField t1,t2,enpassField;
JButton b1,b2,b3,go;
UserVery()
{
super();
setLayout(null);
l1=new JLabel("Username");
l2=new JLabel("Input");
l4=new JLabel("keystrokedynamics");
l4.setForeground(Color.BLACK);
l3=new JLabel("");
b1=new JButton("Check");
b2=new JButton("REAL USER");
b3=new JButton("Try Again");
t1=new JTextField();
t2=new JTextField();
l3.setBounds(120,70,100,30);
l3.setFont(new Font("Times New Roman", Font.BOLD, 18));
add(l3);
l4.setBounds(150,70,300,30);
l4.setFont(new Font("Times New Roman", Font.BOLD, 20));
add(l4);
l1.setBounds(120,130,80,30);
add(l1);
t1.setBounds(220,130,150,30);
add(t1);
l2.setBounds(120,180,80,30);
add(l2);
t2.setBounds(220,180,150,30);
add(t2);
b3.setBounds(420,180,100,30);
add(b3);
b1.setBounds(245,250,100,30);
add(b1);
b2.setBounds(300,250,150,30);
add(b2);
enpassLabel = new JLabel("Password");
enpassField = new JTextField();
go = new JButton("Go");
enpassLabel.setBounds(120,80,80,30);
enpassField.setBounds(220,80,150,30);
go.setBounds(420,80,100,30);
add(enpassField);
add(enpassLabel);
add(go);
go.addActionListener(this);

b1.setEnabled(false);
b1.setVisible(false);
b2.setVisible(false);
l1.setVisible(false);
l2.setVisible(false);
l4.setVisible(false);
b3.setVisible(false);
t1.setVisible(false);
t2.setVisible(false);
b2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		b2.setBackground(Color.YELLOW);
		b2.setEnabled(false);
add(b2);
t1.requestFocus();
b1.addActionListener(this);
b3.addActionListener(this);
t2.addFocusListener(this);
setVisible(true);
setSize(600,350);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setResizable(false);
}
public void focusGained(FocusEvent fe) 
{
       
         t2.addKeyListener(new KeyAdapter() {
				int storecnt;
      				public void keyTyped(KeyEvent e) {
					milli1=System.currentTimeMillis();
					//System.out.println("Inside "+e.getKeyChar()+" Keytyped");
   				}

				public void keyPressed(KeyEvent e) {
						//keypre++;
						System.out.println("Inside "+e.getKeyChar()+" KeyPressed");
					System.out.println(e.getKeyCode());	
					//System.out.println(e.getAscii());
					int ascii_value = (int) e.getKeyChar();
					System.out.println("Ascii value - "+ascii_value);
					wordanalyzer= wordanalyzer+Character.toString(e.getKeyChar());
					//keytrackcnt++;
					//storecnt=keytrackcnt;
					storecnt=keypre;
					overallk++;
					milli2 =System.currentTimeMillis();chcnt++;
					if(milli3==0)
					{
			
						w=0;
						w22=milli2;
						pp=milli2-w22;
						fc=milli2;
                                           					}
					else
					{
						w=milli2-milli3;
						pp=milli2-w22;
					}
		System.out.println();	
		
		if(KeyEvent.getKeyText(e.getKeyCode()).equals("Backspace"))
		{
				System.out.println("KP="+KeyEvent.getKeyText(e.getKeyCode())); // modification 
				//System.out.println("F FT->"+w);
			bscount++;
			if(totalkeycount==0)
			totalkeycount=0;
			else
			totalkeycount=totalkeycount-1;

		}
		else if(ascii_value>=32 && ascii_value<=126)
		{
					System.out.println("KP="+e.getKeyChar()); // modification 
			keytrackcnt++;
			keypre++;

			char temp= e.getKeyChar();
			int askiir = (int) text.charAt(totalkeycount);
			int askiiw = (int) temp;
			//System.out.println("Read - "+askiir+"\t written - "+askiiw);
			try{
			totalkeycount=totalkeycount+1;
			if(askiir==askiiw)
			{
				
			//	highlighter.addHighlight(totalkeycount-1, totalkeycount, painterc );
			}else{

				errors++;
				//reset.setEnabled(true);
			//	highlighter.addHighlight(totalkeycount-1, totalkeycount, painterw );					
			    }	
					}catch(Exception excer){System.out.println("Highlight Painter error - "+excer);}			
			
			

		}
		else
		{
			/*
				System.out.println("KP="+e.getKeyChar()); // modification 
			
			char temp= e.getKeyChar();
			int askiir = (int) str.charAt(totalkeycount);
			int askiiw = (int) temp;
			//System.out.println("Read - "+askiir+"\t written - "+askiiw);
			try{
			totalkeycount=totalkeycount+1;
			if(askiir==askiiw)
			{
				
				highlighter.addHighlight(totalkeycount-1, totalkeycount, painterc );
			}else{

				errors++;
				reset.setEnabled(true);
				highlighter.addHighlight(totalkeycount-1, totalkeycount, painterw );					
			    }	
					}catch(Exception excer){System.out.println("Highlight Painter error - "+excer);}			
				*/			
					
		}		


		} // used for calculating typing speed 

		
		public void keyReleased(KeyEvent e) {
			//keyreal--;
			System.out.println("Inside "+e.getKeyChar()+" KeyReleased");

			milli3 =System.currentTimeMillis();
			long b=milli3-milli2;
			pt=milli3-milli1;
			if(w==0)
			{
				r22=milli3;
				rr=0;
			}
			else{rr=milli3-r22;}
			System.out.println("FT->"+w);
			System.out.println("DT->"+b);
			System.out.println("PT->"+pt);
			System.out.println("PP->"+pp);
			System.out.println("RR->"+rr);
		//				if(wordanalyzer.length()==((t1.getText()).toString()).length())
				//System.out.println("Flag = "+flag);
				System.out.println("Keytrackcnt = "+keytrackcnt);
				
			/*	if((keytrackcnt==(strlen-1))&&(keypre==(strlen-1))&&(keyreal==-1))
				{
					flag=1;
				}*/
							
//				System.out.println("Flag = "+flag+"Keytrackcount "+keytrackcnt+"keypress"+keypre+" keyrelease "+keyreal);
			/*----------------------Keystroke Dynamics Storing Code-------------- */
			

					/**********Finding Index of character***********/
					int ascii_value = (int) e.getKeyChar();
					//text=password;
					if(ascii_value>=32 && ascii_value<=126)
					{
					keyreal--;
					System.out.println("Flag = "+flag+"Keytrackcount "+keytrackcnt+"keypress"+keypre+" keyrelease "+keyreal);
					int chacount;	
					for(int i=0;i<(passLength);i++)
						{	
							//System.out.println(""+text.charAt(i)+"||"+e.getKeyChar());
							if(text.charAt(i)==e.getKeyChar())
							{
								//System.out.println("Match Found - "+i);
								if(keystore[i][0]==null){
						
						keystore[i][0]=Character.toString(e.getKeyChar()); // Key pressed
						//System.out.println("KEY "+keystore[i][0]);
						keystore[i][1]=Long.toString(w); // flight time (FT)
						//System.out.println("FLIGHT TIME "+keystore[i][1]);			
			keystore[i][2]=Long.toString(b); // dwell time (DT)
			keystore[i][3]=Long.toString(pt); // Press time (PT)
			keystore[i][4]=Long.toString(pp); // press to press  time (PP)
			keystore[i][5]=Long.toString(rr); // release to release time (RR)
			break;						}
								else{//System.out.println("Check else");
									}
							}
							
						}  
					}
			w22=milli2;
			r22=milli3;
			
				if((keytrackcnt==(passLength-1))&&(keypre==(passLength-1))&&(keyreal==-1))
				{
					flag=1;
				}
			if(flag==1){
			//System.out.println("Inside Key Released");
		t2.removeKeyListener( t2.getKeyListeners()[ 0 ] );
 		double tyra=(chcnt*1.0/(milli3-fc))*1000;
		int a = (int) (tyra + 0.5);
		System.out.println();
		System.out.println("Typing Rate->"+a+" CPS");
		double accuracyrate= (((overallk-errors)*1.0)/overallk)*100;
		int acr=(int)(accuracyrate + 0.50);
		System.out.println("Accuracy Rate -> "+acr);

/*		
		/***************Calculating Mean & SD***************/
		/**************************************************/


		double sum=0,mean,vari,std;

			for(int i=1;i<6;i++) // vertical individual
			{
				sum=0;
				for(int j=0;j<(passLength);j++)
				{
					sum=sum+(Long.parseLong(keystore[j][i])*1.0);
				}
				mean=sum/(passLength);
				keystore[(passLength)][0]="Mean";
				keystore[(passLength)][i]=Double.toString(mean);
				System.out.println("Mean"+mean);
			}

			sum=0;
			for(int i=1;i<6;i++) // vertical individual
			{
				sum=0;
				for(int j=0;j<(passLength);j++)
				{
					sum=sum+Math.pow((((Long.parseLong(keystore[j][i]))*1.0)-(Double.parseDouble(keystore[(passLength)][i]))),2);
				}
				keystore[(passLength + 1)][0]="STD";
				vari=(sum*1.0)/(passLength -1);
				std=Math.sqrt(vari);
				keystore[(passLength + 1)][i]=Double.toString(std);
				System.out.println("STD"+std);
			}			

			// for horizontal purpose			
			sum=0;mean=0;

				for(int i=0;i<(passLength);i++) // vertical individual
			{
				sum=0;
				for(int j=1;j<6;j++)
				{
					sum=sum+(Long.parseLong(keystore[i][j])*1.0);
				}
				mean=sum/5;
				//keystore[17][0]="Mean";
				keystore[i][6]=Double.toString(mean);
				System.out.println("Mean"+mean);
			}
			
			sum=0;
			for(int i=0;i<(passLength);i++) // vertical individual
			{
				sum=0;
				for(int j=1;j<6;j++)
				{
					sum=sum+Math.pow((((Long.parseLong(keystore[i][j]))*1.0)-(Double.parseDouble(keystore[i][6]))),2);
				}
				//keystore[18][0]="STD";
				vari=(sum*1.0)/4;
				std=Math.sqrt(vari);
				keystore[i][7]=Double.toString(std);
				System.out.println("STD"+std);
			}	










		
		/******************Displaying Data**************/
		System.out.println("\nKey  \tFT  \tDT  \tPT  \tPP  \tRR\tMean \t\tSTD");

			

				for(int i=0;i<(passLength + 2);i++)
				{
					System.out.println();
					for(int j=0;j<8;j++)
					{
					 System.out.print(""+keystore[i][j]+"\t");			
					}
				}
		/***********************************************/
		//System.out.println("Errors = "+errors+" \nTotal Key = "+totalkeycount+"\nBackspace= "+bscount+"\nOverall K ="+overallk);


		//t2.removeKeyListener(t2.getKeyListeners());
		t2.setEditable(false);										
					
					//MAKE BUTTON VISIBLE ACCORDING TO CHARACTER
					if(acr==100)
					{
						//b3.setVisible(true);
						b1.setEnabled(true);
						//b2.setVisible(true);
					}
					else{//reset.setVisible(true);
						//reset.setEnabled(true);
						
					}
						
					}
			

			}

}); }

public void focusLost(FocusEvent fe) {
                
}

public void actionPerformed(ActionEvent ae)
{

if(ae.getActionCommand()=="Check")
{
		b2.setVisible(false);
		try{
				int pos=-1;
				String us="";
				File folder = new File("C:\\Users\\Kaustubh\\Desktop\\MyKData");
				File[] list = folder.listFiles();
				for(int l=0;l<list.length;l++){
						if(list[l].isFile()){System.out.println("File ->"+list[l].getName());
								
							ObjectInputStream in = new ObjectInputStream(new FileInputStream("C:\\Users\\Kaustubh\\Desktop\\MyKData\\"+list[l].getName()));
        String allkeystrokestore[][][]=new String[13][50][8];
		allkeystrokestore= (String[][][]) in.readObject();
        in.close();

	String currentUserPassword = allkeystrokestore[12][0][0];

		String matchedUser="";
		String unmatchedUser="";
		
		
		if(currentUserPassword.equals(text)){
			
					int errcnt=0,matchcnt=0;
		double mean, std,fmin,fmax;
			for(int i=0;i<(passLength);i++)
			{
				for(int j=1;j<6;j++)
				{
					mean = Double.parseDouble(allkeystrokestore[10][i][j]);
					std = Double.parseDouble(allkeystrokestore[11][i][j]);
					fmin = mean-(std*1.5);
					fmax=mean+(std*1.5);
						if((fmin<=(Double.parseDouble(keystore[i][j])))&&((Double.parseDouble(keystore[i][j]))<=fmax))
						{
matchcnt++;
System.out.println("True |Key - "+allkeystrokestore[0][i][0]+"|MeanS ="+allkeystrokestore[10][i][j]+"|std="+allkeystrokestore[11][i][j]+"|Ct"+keystore[i][j]);
						}else{errcnt++;
					matchcnt++;
			System.out.println("False| Key - "+allkeystrokestore[0][i][0]+"|MeanS ="+allkeystrokestore[10][i][j]+"|std="+allkeystrokestore[11][i][j]+"|Ct"+keystore[i][j]);
					}

				}

			}	
	
			//	String matchdata="",unmatchdata="";
				//double accresult = (((matchcnt-errcnt)*1.0)/(matchcnt))*100;
				double accresult = (((matchcnt-errcnt)*1.0)/(matchcnt))*100;
				int result = (int)(accresult+0.5);
				int fare = result+40;
				System.out.println("% Match With Stored Keystroke Dynamics = "+result+" for "+list[l].getName());
				
			if(result>=70)
				{
					//b2.setVisible(true);
					System.out.println("Match found - "+list[l].getName());
					us = us + list[l].getName() +"-> "+result+";";
					matchedUser = matchedUser+list[l].getName() +" -> "+result+" & s";
					pos = us.lastIndexOf(".");
				}else{
					unmatchedUser = unmatchedUser + list[l].getName() +" -> "+result+"% ||";
					
				}				
					System.out.println("\n\nUser Match - "+us);
					System.out.println("\n\nNot Matched User \n- "+unmatchedUser);
					if(us!=null){
						if(pos!=-1){//t1.setText(matchedUser.substring(0,pos));
								t1.setText(us);
							}else{t1.setText("Not Found");}
					} else {//t1.setText("Not Found");
					}


							}else{if(list[l].isDirectory()){System.out.println("Folder ->"+list[l].getName());}
								}
					} 

		}}catch(Exception obex){System.out.println(""+obex);}			


		


		l1.setVisible(true);
		t1.setVisible(true);
		
}


if(ae.getActionCommand()=="Go"){

		text = enpassField.getText();
		go.setVisible(false);
		enpassLabel.setVisible(false);
		enpassField.setVisible(false);
		l4.setText(text);
		l2.setVisible(true);
		l4.setVisible(true);
		t2.setVisible(true);
		b3.setVisible(true);
		b1.setVisible(true);
		
		passLength = text.length();
		keystore=new String[(passLength+2)][8];


		System.out.println("ok");
	try
	{
		//t2.removeKeyListener( t2.getKeyListeners()[ 0 ] );
		
		for(int i=0;i<(passLength + 2);i++)
		{
			for(int j=0;j<8;j++)
			{
				keystore[i][j]=null;
			}
		}
		t2.setEnabled(true);
		t2.setEditable(true);
		//t1.setText("");
		t2.setText("");
		b1.setEnabled(false);
		b2.setVisible(false);
		//t2.addFocusListener(this);
     		milli1=0;milli2=0;milli3=0;pp=0;rr=0;pt=0;
		chcnt=0;flag=0;
		totalkeycount=0;bscount=0;errors=0;overallk=0;keypre=-1;keyreal=(passLength -1);//x=0;
		keytrackcnt=-1;
		t2.requestFocus();
	//	t2.removeKeyListener( t2.getKeyListeners()[ 0 ] );
	// dispose();
	//new UserAuth();
		
	}
	catch(Exception e){}


}

if(ae.getActionCommand()=="Try Again")
{	System.out.println("ok");
	try
	{
		//t2.removeKeyListener( t2.getKeyListeners()[ 0 ] );
		
		for(int i=0;i<(passLength + 2);i++)
		{
			for(int j=0;j<8;j++)
			{
				keystore[i][j]=null;
			}
		}
		t2.setEnabled(true);
		t2.setEditable(true);
		//t1.setText("");
		t2.setText("");
		b1.setEnabled(false);
		b2.setVisible(false);
		//t2.addFocusListener(this);
     		milli1=0;milli2=0;milli3=0;pp=0;rr=0;pt=0;
		chcnt=0;flag=0;
		totalkeycount=0;bscount=0;errors=0;overallk=0;keypre=-1;keyreal=(passLength-1);//x=0;
		keytrackcnt=-1;
		t2.requestFocus();
		//t2.removeKeyListener( t2.getKeyListeners()[ 0 ] );
	// dispose();
	//new UserAuth();
		
	}
	catch(Exception e){}
}
}
public static void main(String args[])
{
new UserVery();
}
}